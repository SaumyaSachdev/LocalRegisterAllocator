package alloc;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Alloc {

    static ArrayList<Instruction> input = new ArrayList<>();

    static Map<String, Register> virtualRegs = new HashMap<>();
    // static Map<String, Register> physicalRegs = new HashMap<>();
    static Map<String, String> phyVirMap = new HashMap<>();

    // registers that are spilled start from the below base address. offset 
    static final int BASE_ADDRESS = 1024;
    static int CURR_OFFSET = 0;

    public static void main(String[] args) {
        int registers = 0;
        File inputFile = new File(args[1]);
        File outputFile = new File(args[2]);
        
        try {
            registers = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("First argument passed is not an integer.");
        }
        if (!inputFile.isFile() || !outputFile.isFile()) {
            System.err.println("Input or output file does not exist.");
        } 
        
        // System.out.println("registers: " + registers);

        // Register[] physicalRegs = new Register[registers];
        for (int i=0; i<registers; i++) {
            // add "NULL" as mapping to indicate physical register is empty
            // System.out.println("*************phy reg:  " + "r" + (char)(i+97));
            phyVirMap.put("r" + (char)(i+97), "NULL");
            // physicalRegs.put("r" + (char)(i+97), new Register("r" + (char)(i+97)));
            // physicalRegs[i] = new Register("r" + (char)(i+97));

            // physicalRegs[i].name = "r" + (char)(i+97);
            // System.out.println(physicalRegs[i].name);
        }
        getInputInstructions(inputFile);
        getRegistersAndNextUse();
        getLiveRegs();
        try {
            allocateRegs(inputFile, outputFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.err.println("Output file does not exist.");
            e.printStackTrace();
        }
    }

    private static void getInputInstructions(File inputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("//") && !line.startsWith("#") && !line.isBlank() && !line.isEmpty()) {
                    // System.out.println(line);
                    
                    Instruction temp = Instruction.parseInstruction(line);
                    temp.setLineNumber(i);
                    input.add(temp);
                    i++;
                }               
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private static void getRegistersAndNextUse() {
        for (Instruction inst : input) {
            // System.out.println(inst.toString());
            
            // check if sources and targets exist in virtual regs
            for (int i=0; i<inst.sources.length; i++) {
                String src = inst.sources[i];
                if (src.startsWith("r")  && !virtualRegs.containsKey(src)) {
                    virtualRegs.put(src, new Register(src, inst.lineNumber, inst.lineNumber));
                } else if (virtualRegs.containsKey(src)) {
                    // update next use if register already exists
                    Register reg = virtualRegs.get(src);
                    virtualRegs.remove(src);
                    if (reg.nextUse == 0) {
                        // virtualRegs.remove(src);
                        reg.setNextUse(inst.lineNumber);
                        // virtualRegs.put(src, reg);
                    } 
                    reg.setLastUse(inst.lineNumber);
                    virtualRegs.put(src, reg);
                }
            }

            if (inst.targets != null) {
                for (int i=0; i<inst.targets.length; i++) {
                    String targ = inst.targets[i];
                    if (targ.startsWith("r")  && !virtualRegs.containsKey(targ)) {
                        virtualRegs.put(targ, new Register(targ, inst.lineNumber, inst.lineNumber));
                    } else if (virtualRegs.containsKey(targ)) {
                        // update next use if register already exists
                        Register reg = virtualRegs.get(targ);
                        virtualRegs.remove(targ);
                        if (reg.nextUse == 0) {
                            // virtualRegs.remove(targ);
                            reg.setNextUse(inst.lineNumber);
                            // virtualRegs.put(targ, reg);
                        }
                        reg.setLastUse(inst.lineNumber);
                        virtualRegs.put(targ, reg);
                    }
                }    
            }
        }

        for (var reg : virtualRegs.entrySet()) {
            System.out.println(reg.getValue());
        }
    }

    private static void getLiveRegs() {
        // input.get(0).liveRegs.add("r1");
        // input.get(0).liveRegs.add("r2");
        for (int j=0; j<input.size(); j++) {
            Instruction inst = input.get(j);
            if (j != 0) {
                inst.liveRegs.addAll(input.get(j-1).liveRegs);
            }
            for (int i=0; i<inst.sources.length; i++) {
                if (inst.sources[i].startsWith("r")) {
                    inst.liveRegs.add(inst.sources[i]);
                }
            }
            if (inst.targets != null) {
                for (int i=0; i<inst.targets.length; i++) {
                    if (inst.targets[i].startsWith("r")) {
                        inst.liveRegs.add(inst.targets[i]);
                    }
                }
            }
            
            for (Iterator<String> iterator = inst.liveRegs.iterator(); iterator.hasNext();) {
                String str = iterator.next();
                if (virtualRegs.containsKey(str)) {
                    if (virtualRegs.get(str).lastUse == inst.lineNumber) {
                        iterator.remove();
                    }
                }
            }
        }
        for (Instruction inst : input) {
            System.out.println(inst.toString());;
        }
    }

    private static void allocateRegs(File inputFile, File outputFile) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
        for (Instruction inst : input) {
            // check if all live regs are already allocated
            // phyVirMap contains mapping of phy to vir registers i.e., allocated registers
            for (String register : inst.liveRegs) {
                /** 
                 * if live register not found in allocated regs
                 * three possible reasons
                 * 1. register was spilled earlier (check offset in virtualRegs)
                 * 2. physical regs have space to add register
                 * 3. if no phy regs are free, free one with max nextUse and add spill code
                **/
                if (!phyVirMap.containsValue(register)) {
                    // check if any register is spilled, insert code to load that register in output file
                    Register virtualReg = virtualRegs.get(register);
                    if (virtualReg.offset != Integer.MAX_VALUE) {
                        // implies register was spilled, add instruction to load from spilled location and set in phyVirMap
                        int address = BASE_ADDRESS + virtualReg.offset;
                        String emptyPhyReg = findEmptyPhyReg();
                        if (!emptyPhyReg.equals("NULL")) {
                            // Register virReg = virtualRegs.get(register);
                            virtualReg.allocated = 1;
                            phyVirMap.put(emptyPhyReg, virtualReg.name);
                            // flag = 1;
                            
                        } else {
                            // if no empty phy reg found, find a register with max nextUse to spill and add spill code
                            emptyPhyReg = getMaxNextUseAndSpill(bw);
                        }
                        String loadSpill = "load " + address + " => " + emptyPhyReg;
                        System.out.println("write 1: " + loadSpill);
                        bw.append(loadSpill);
                        bw.newLine();
                    } else {
                        // check if a physical register is empty
                        int flag = 0;
                        String emptyPhyReg = findEmptyPhyReg();
                        System.out.println("////empty reg found: " + emptyPhyReg);
                        if (!emptyPhyReg.equals("NULL")) {
                            Register virReg = virtualRegs.get(register);
                            virReg.allocated = 1;
                            // System.out.println("virtual reg: " + virReg);
                            phyVirMap.put(emptyPhyReg, virReg.name);
                            for (Map.Entry<String, String> entry : phyVirMap.entrySet()) {
                                System.out.print("key: " + entry.getKey() + " value: " + entry.getValue() + " \t");
                            }
                            System.out.println();
                            flag = 1;
                        }

                        // no phy registers are free, spill register and add spill code
                        if (flag == 0) {
                            // find the allocated registers with the latest next use
                            String emptiedReg = getMaxNextUseAndSpill(bw);
                            Register virReg = virtualRegs.get(register);
                            virReg.allocated = 1;
                            phyVirMap.put(emptiedReg, register);
                        }
                    }                   

                    // check if a physical register is empty
                    // int flag = 0;
                    // String emptyPhyReg = findEmptyPhyReg();
                    // System.out.println("////empty reg found: " + emptyPhyReg);
                    // if (!emptyPhyReg.equals("NULL")) {
                    //     Register virReg = virtualRegs.get(register);
                    //     virReg.allocated = 1;
                    //     // System.out.println("virtual reg: " + virReg);
                    //     phyVirMap.put(emptyPhyReg, virReg.name);
                    //     for (Map.Entry<String, String> entry : phyVirMap.entrySet()) {
                    //         System.out.print("key: " + entry.getKey() + " value: " + entry.getValue() + " \t");
                    //     }
                    //     System.out.println();
                    //     flag = 1;
                    // }

                    // // no phy registers are free, spill register and add spill code
                    // if (flag == 0) {
                    //     // find the allocated registers with the latest next use
                    //     String emptiedReg = getMaxNextUseAndSpill(bw);
                    //     Register virReg = virtualRegs.get(register);
                    //     virReg.allocated = 1;
                    //     phyVirMap.put(emptiedReg, register);
                    // }
                }
            }
            // add the instruction to output file with the mappings formed above
            String instruction = inst.opcode + "\t";
            instruction += (inst.sources.length == 2) ? getMappedPhyReg(inst.sources[0]) + ", " + getMappedPhyReg(inst.sources[1]) : getMappedPhyReg(inst.sources[0]);
            instruction += " => ";
            instruction += (inst.targets.length == 2) ? getMappedPhyReg(inst.targets[0]) + ", " + getMappedPhyReg(inst.targets[1]) : getMappedPhyReg(inst.targets[0]);
            System.out.println("write 2: " + instruction);
            bw.append(instruction);
            bw.newLine();         
        }
        bw.close();
    }

    private static String getMappedPhyReg(String virtualReg) {
        // check if virtualReg is a register or memory address
        if (virtualReg.startsWith("r")) {
            for (Map.Entry<String, String> entry : phyVirMap.entrySet()) {
                if (Objects.equals(virtualReg, entry.getValue())) {
                    return entry.getKey();
                }
            }
        } else {
            return virtualReg;
        }
        
        return null;
    }

    private static String getMaxNextUseAndSpill(BufferedWriter bw) throws IOException {
        int maxNextUse = 0;
        Register regToSpill = new Register(null);
        String regName = "";
        for (Map.Entry<String, String> entry : phyVirMap.entrySet()) {
            // String curr = entry.getKey();
            Register currReg = virtualRegs.get(entry.getValue());
            // System.out.println("current reg: " + currReg);
            if (currReg != null) {
                if (currReg.nextUse > maxNextUse) {
                    maxNextUse = currReg.nextUse;
                    regToSpill = currReg;
                    // currently assigned physical name for the register
                    regName = entry.getKey();
                    // System.out.println("reg name update: " + regName);
                }
            }
        }
        // remove chosen register from phyVirMap, add spill code and store offset in virtual reg
        regToSpill.allocated = 0;
        regToSpill.offset = CURR_OFFSET;
        int address = BASE_ADDRESS + CURR_OFFSET;
        CURR_OFFSET += 4;
        String spillCode = "store " + regName + " => " + address;
        System.out.println("write 3: " + spillCode);
        
        bw.append(spillCode);
        bw.newLine();
        phyVirMap.put(regName, "NULL");
        virtualRegs.put(regName, regToSpill);
        return regName;
    }

    // returns name of empty phy reg or returns "NULL"
    private static String findEmptyPhyReg() {
        for (Map.Entry<String, String> entry : phyVirMap.entrySet()) {
            if (entry.getValue().equals("NULL")) {
                return entry.getKey();
            }
        }
        return "NULL";
    }
}

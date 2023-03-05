package alloc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Alloc {

    static ArrayList<Instruction> input = new ArrayList<>();

    static Map<String, Register> virtualRegs = new HashMap<>();
    static Map<String, Register> physicalRegs = new HashMap<>();
    // static Set<Register> virtualRegs = new HashSet<>();
    // static Set<Register> physicalRegs = new HashSet<>();
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
            physicalRegs.put("r" + (char)(i+97), new Register("r" + (char)(i+97)));
            // physicalRegs[i] = new Register("r" + (char)(i+97));

            // physicalRegs[i].name = "r" + (char)(i+97);
            // System.out.println(physicalRegs[i].name);
        }
        getInputInstructions(inputFile);
        getRegistersAndNextUse();
    }

    private static void getInputInstructions(File inputFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                if (!line.startsWith("//") && !line.startsWith("#") && !line.isBlank() && !line.isEmpty()) {
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
            System.out.println(inst.toString());
            
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
}

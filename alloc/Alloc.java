package alloc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Alloc {

    static ArrayList<Instruction> input = new ArrayList<>();
    static Set<Register> virtualRegs = new HashSet<>();
    static Set<Register> physicalRegs = new HashSet<>();
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
        
        System.out.println("registers: " + registers);

        // Register[] physicalRegs = new Register[registers];
        for (int i=0; i<registers; i++) {
            physicalRegs.add(new Register("r" + (char)(i+97)));
            // physicalRegs[i] = new Register("r" + (char)(i+97));

            // physicalRegs[i].name = "r" + (char)(i+97);
            // System.out.println(physicalRegs[i].name);
        }
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

        for (Instruction inst : input) {
            System.out.println(inst.toString());
            
            // check if sources and targets exist in virtual regs
            for (int i=0; i<inst.sources.length; i++) {
                if (inst.sources[i].startsWith("r")  && !virtualRegs.contains(new Register(inst.sources[i]))) {
                    virtualRegs.add(new Register(inst.sources[i], inst.lineNumber));
                }
            }

            if (inst.targets != null) {
                for (int i=0; i<inst.targets.length; i++) {
                    if (inst.targets[i].startsWith("r")  && !virtualRegs.contains(new Register(inst.targets[i]))) {
                        virtualRegs.add(new Register(inst.targets[i], inst.lineNumber));
                    }
                }    
            }
            // for (int i=0; i<inst.targets.length; i++) {
            //     if (inst.targets[i].startsWith("r")  && !virtualRegs.contains(new Register(inst.targets[i]))) {
            //         virtualRegs.add(new Register(inst.targets[i]));
            //     }
            // }
        }

        for (Register reg : virtualRegs) {
            System.out.println(reg);

        }
    }
}

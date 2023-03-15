package alloc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Instruction {
    String opcode;
    String[] sources;
    String[] targets;
    int numRegs;
    int lineNumber;
    Set<String> liveRegs = new HashSet<>();

    Instruction(String opcode, String[] sources, String[] targets, int numRegs) {
        this.opcode = opcode;
        this.sources = sources;
        this.targets = targets;
        this.numRegs = numRegs;
    }

    public String toString() {
        String str = "line number: " + lineNumber + "\topcode: " + opcode + "\tnum regs: " + numRegs + "\tsources: ";
        str += (this.sources.length == 2) ? (this.sources[0] + " " + this.sources[1]) : (this.sources[0]);
        if (this.targets.length > 0) {
            str += "\ttargets: ";
            str += (this.targets.length == 2) ? (this.targets[0] + " " + this.targets[1]) : (this.targets[0]);    
        }
        str += "\t live regs: ";
        for (String s : this.liveRegs) {
            str += s + "\t";
        }
        return str;   
    }

    public static Instruction parseInstruction(String instruction) {
        instruction = instruction.replaceAll(",", "");
        String[] split = instruction.split("\\s+");
        
        String op = split[1];
        ArrayList<String> src = new ArrayList<>();
        ArrayList<String> tar = new ArrayList<>();
        
        int numRegs = 0;
        int flag = 0;

        for (int i=2; i<split.length; i++) {
            if (split[i].equals("=>")) {
                flag = 1;
                continue;
            } else if (flag == 0) {
                src.add(split[i]);
            } else {
                tar.add(split[i]);
            }
        }
        
        String[] sources = src.toArray(new String[0]);
        String[] targs = tar.toArray(new String[0]);

        for (int i=0; i<sources.length; i++) {
            if (sources[i].startsWith("r")) {
                numRegs++;
            }
        }
        if (targs != null) {
            for (int i=0; i<targs.length; i++) {
                if (targs[i].startsWith("r")) {
                    numRegs++;
                }
            }
        } else {
            targs = null;
        }
        
        return new Instruction(op, sources, targs, numRegs);
    }

    public void setLineNumber(int i) {
        this.lineNumber = i;
    }   
}

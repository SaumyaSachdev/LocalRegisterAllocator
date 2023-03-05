package alloc;

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
        if (targets != null) {
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
        String[] split = instruction.split("\t");
        // System.out.println("length: " + split.length);
        String op = split[1];
        String[] sources = {null, null};
        String[] targs = {null, null};
        int numRegs = 0;
        sources = split[2].split(", ");
        if (split.length == 4) {
            targs = split[3].substring(split[3].indexOf('>') + 2).split(", ");
        }
        // System.out.println("opcode: " + op);
        // for (int i=0; i<split.length; i++) {
        //     System.out.println(split[i]);
        // }
        for (int i=0; i<sources.length; i++) {
            if (sources[i].startsWith("r")) {
                numRegs++;
            }
            // System.out.println("sources last char: " + sources[i].charAt(sources[i].length()-1));
        }
        if (split.length == 4) {
            for (int i=0; i<targs.length; i++) {
                // System.out.println("targs length: " + targs.length);
                if (targs[i].startsWith("r")) {
                    numRegs++;
                }
                // System.out.println("targs last char: " + targs[i].charAt(targs[i].length()-1));
            }
        } else {
            targs = null;
        }
        
        // System.out.println("num regs: "+ numRegs);
        return new Instruction(op, sources, targs, numRegs);
    }

    public void setLineNumber(int i) {
        this.lineNumber = i;
    }   
}

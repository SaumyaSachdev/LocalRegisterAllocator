package alloc;

public class Register {
    String name;
    // 0 is free, 1 is assigned, used for physical regs
    int free;
    int firstUse;
    int nextUse;
    int lastUse;
    
    Register(String name) {
        this.name = name;
        this.free = 0;
        this.firstUse = 0;
        this.nextUse = 0;
        this.lastUse = 99999999;
    }

    Register(String name, int firstUse, int lastUse) {
        this.name = name;
        this.firstUse = firstUse;
        this.nextUse = 0;
        this.free = 0;
        this.lastUse = lastUse;
    }

    public String toString() {
        return "name: " + this.name + "\t free: " + this.free + "\t first use: " + this.firstUse + "\t next use: " + this.nextUse + "\t last use: " + this.lastUse;
    }

    @Override
    public int hashCode() {
        int hash = 23;
        hash = 7 * hash + (name == null ? 0 : name.hashCode());
        // hash = 7 * hash + free;
        // hash = 7 * hash + firstUse;
        // hash = 7 * hash + nextUse;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Register reg = (Register) obj;
        if (this.name == null) {
            if (reg.name != null)
                return false;
        }
        return reg.name.equals(this.name);
    }

    void getNextUse() {}

    public void setNextUse(int lineNumber) {
        this.nextUse = lineNumber;
    }

    public void setLastUse(int lineNumber) {
        this.lastUse = lineNumber;
    }
}

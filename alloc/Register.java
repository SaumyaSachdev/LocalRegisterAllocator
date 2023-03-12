package alloc;

public class Register {
    String name;
    // 0 is free, 1 is allocated
    int allocated;
    int firstUse;
    int nextUse;
    int lastUse;
    // stores the offset from base address if the register is spilled
    int offset;
    
    Register(String name) {
        this.name = name;
        this.allocated = 0;
        this.firstUse = 0;
        this.nextUse = 0;
        this.lastUse = Integer.MAX_VALUE;
        // default offset address indicates the register has not been spilled
        this.offset = Integer.MAX_VALUE;
    }

    Register(String name, int firstUse, int lastUse) {
        this.name = name;
        this.firstUse = firstUse;
        this.nextUse = 0;
        this.allocated = 0;
        this.lastUse = lastUse;
        this.offset = Integer.MAX_VALUE;
    }

    public String toString() {
        return "name: " + this.name + "\t allocated: " + this.allocated + "\t first use: " + this.firstUse + "\t next use: " + this.nextUse + "\t last use: " + this.lastUse + "\t offset: " + this.offset;
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

    public void setNextUse(int lineNumber) {
        this.nextUse = lineNumber;
    }

    public void setLastUse(int lineNumber) {
        this.lastUse = lineNumber;
    }
}

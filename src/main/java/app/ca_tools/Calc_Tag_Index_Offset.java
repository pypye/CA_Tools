package app.ca_tools;

public class Calc_Tag_Index_Offset {

    private int cacheSize; // default = byte
    private int blockSize;
    private int addressSize;
    private int reference;

    public int getCacheSize() {
        return cacheSize;
    }

    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getAddressSize() {
        return addressSize;
    }

    public void setAddressSize(int addressSize) {
        this.addressSize = addressSize;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public Calc_Tag_Index_Offset(int cacheSize, int blockSize, int addressSize, int reference) {
        this.cacheSize = cacheSize;
        this.blockSize = blockSize;
        this.addressSize = addressSize;
        this.reference = reference;
    }

    public StringBuilder toBinary() {
        StringBuilder binary = new StringBuilder();
        int index = 0;
        int temp = reference;
        while (temp > 0) {
            binary.append(temp % 2);
            temp = temp / 2;
        }
        binary.reverse();
        StringBuilder tmp = new StringBuilder();
        tmp.append("0".repeat(Math.max(0, 32 - binary.length())));
        return tmp.append(binary);
    }

    public int getTagBits() {
        return addressSize - getIndexBits() - getOffsetBits();
    }

    public int getIndexBits() {
        return Math.round ((float) Math.log(1.0 * cacheSize / blockSize) / (float) Math.log(2));
    }

    public int getOffsetBits() {
        return Math.round ((float) Math.log(blockSize) / (float) Math.log(2));
    }

    public String getTagBinary() {
        StringBuilder tmp = toBinary();
        return tmp.substring(0, getTagBits());
    }

    public String getIndexBinary() {
        StringBuilder tmp = toBinary();
        return tmp.substring(getTagBits(), getTagBits() + getIndexBits());
    }

    public String getOffsetBinary() {
        StringBuilder tmp = toBinary();
        return tmp.substring(getTagBits() + getIndexBits());
    }

    public String getRefTag() {
        return String.valueOf(Integer.parseInt(getTagBinary(), 2));
    }

    public String getRefIndex() {
        return String.valueOf(Integer.parseInt(getIndexBinary(), 2));
    }

    public String getRefOffset() {
        return String.valueOf(Integer.parseInt(getOffsetBinary(), 2));
    }

    public String allBinary() {
        return getTagBinary() + ":" + getIndexBinary() + ":" + getOffsetBinary();
    }

    public String getRatio() {
        return getTagBits() + ":" + getIndexBits() + ":" + getOffsetBits();
    }

    public static void main(String[] args) {
        Calc_Tag_Index_Offset test = new Calc_Tag_Index_Offset((int) (1 * Math.pow(2, 20)), 16, 32,
                142356);
//    System.out.println(test.getTagBits());
//    System.out.println(test.getIndexBits());
//    System.out.println(test.getOffsetBits());
        System.out.println(test.toBinary());
        System.out.println(test.getTagBinary());
        System.out.println(test.getIndexBinary());
        System.out.println(test.getOffsetBinary());
        System.out.println(test.allBinary());
        System.out.println(test.getRatio());
    }
}

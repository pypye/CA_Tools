package app.ca_tools;

public class CalcMIPSComponent {

  /*
   | Dùng để xác định thành phần của câu lệnh MIPS R,I, J type
   | Gồm op, rt, rs ,rd, shamt, function, offset, address
   */
  private char type;
  private String mips;

  public char getType() {
    return type;
  }

  public void setType(char type) {
    this.type = type;
  }

  public String getMips() {
    return mips;
  }

  public void setMips(String mips) {
    this.mips = mips;
  }

  public CalcMIPSComponent(String mips, char type) {
    this.mips = mips;
    this.type = type;
  }

  public int binaryToDec(String binary) {
    int res = 0;
    StringBuilder tmp = new StringBuilder();
    tmp.append(binary);
    StringBuilder reverse = tmp.reverse();
    for (int i = 0; i < reverse.length(); i++) {
      int a = Character.getNumericValue(reverse.charAt(i));
      res += Math.pow(2, i) * a;
    }
    return res;
  }

  public int getOp() {
    String op = mips.substring(0, 6);
    return binaryToDec(op);
  }

  public int getRs() {
    String rs = mips.substring(6, 11);
    return binaryToDec(rs);
  }

  public int getRt() {
    String rt = mips.substring(11, 16);
    return binaryToDec(rt);
  }

  public int getRd() {
    String rd = mips.substring(16, 21);
    return binaryToDec(rd);
  }

  public int getShamt() {
    String shamt = mips.substring(21, 26);
    return binaryToDec(shamt);
  }

  public int getFunct() {
    String funct = mips.substring(26, 32);
    return binaryToDec(funct);
  }

  public int getOffset() {
    String offset = mips.substring(16, 32);
    return binaryToDec(offset);
  }

  public int getAddress() {
    String address = mips.substring(6, 32);
    return binaryToDec(address);
  }

  public static void main(String[] args) {
    CalcMIPSComponent test = new CalcMIPSComponent("00000001001011011101101111011000", 'R');
    System.out.println(test.getRs());
    System.out.println(test.getOp());
    System.out.println(test.getRt());
    System.out.println(test.getShamt());
  }
}

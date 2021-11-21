package app.ca_tools;

public class MIPS_NonPipeline {

  private double _Rtype;
  private double _lw;
  private double _sw;
  private double _branch;
  private double _jump;
  private double _CPU;

  public double get_Rtype() {
    return _Rtype;
  }

  public void set_Rtype(double _Rtype) {
    this._Rtype = _Rtype;
  }

  public double get_lw() {
    return _lw;
  }

  public void set_lw(double _lw) {
    this._lw = _lw;
  }

  public double get_sw() {
    return _sw;
  }

  public void set_sw(double _sw) {
    this._sw = _sw;
  }

  public double get_branch() {
    return _branch;
  }

  public void set_branch(double _branch) {
    this._branch = _branch;
  }

  public double get_jump() {
    return _jump;
  }

  public void set_jump(double _jump) {
    this._jump = _jump;
  }

  public double get_CPU() {
    return _CPU;
  }

  public void set_CPU(double _CPU) {
    this._CPU = _CPU;
  }

  public MIPS_NonPipeline(double _Rtype, double _lw, double _sw, double _branch, double _jump,
      double _CPU) {
    this._Rtype = _Rtype / 100;
    this._lw = _lw / 100;
    this._sw = _sw / 100;
    this._branch = _branch / 100;
    this._jump = _jump / 100;
    this._CPU = _CPU;
  }

  public double getCPI() {
    return _Rtype * 4 + _lw * 5 + _sw * 4 + _branch * 3 + _jump * 3;
  }

  public double getMIPS() {
    return Math.round(_CPU / getCPI() * 1000);
  }

  public static void main(String[] args) {
    MIPS_NonPipeline test = new MIPS_NonPipeline(14, 22, 43, 11, 10, 1.8);
    System.out.println(test.getCPI());
    System.out.println(test.getMIPS());
  }
}

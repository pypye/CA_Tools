package app.ca_tools;

public class MIPS_pipeline {

  private double _Rtype;
  private double _lw;
  private double _sw;
  private double _branch;
  private double _jump;
  private double _lw_2cycle;
  private double _branch_2cycle;
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

  public double get_lw_2cycle() {
    return _lw_2cycle;
  }

  public void set_lw_2cycle(double _lw_2cycle) {
    this._lw_2cycle = _lw_2cycle;
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

  public double get_branch_2cycle() {
    return _branch_2cycle;
  }

  public void set_branch_2cycle(double _branch_2cycle) {
    this._branch_2cycle = _branch_2cycle;
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

  public MIPS_pipeline(double _Rtype, double _lw, double _sw, double _branch, double _jump,
      double _lw_2cycle, double _branch_2cycle, double _CPU) {
    this._Rtype = _Rtype/100;
    this._lw = _lw/100;
    this._sw = _sw/100;
    this._branch = _branch/100;
    this._jump = _jump/100;
    this._lw_2cycle = _lw_2cycle/100;
    this._branch_2cycle = _branch_2cycle/100;
    this._CPU = _CPU;
  }

  public double getCPI() {
    return _Rtype + _lw * (_lw_2cycle * 2 + 1 - _lw_2cycle) + _sw + _branch * (_branch_2cycle * 2 + 1 - _branch_2cycle) + _jump * 2;
  }

  public double getMIPS() {
    return Math.round(_CPU / getCPI() * 1000);
  }

  public static void main(String[] args) {
    MIPS_pipeline test = new MIPS_pipeline(49, 22, 11, 16, 2, 50, 25, 2.4);
    System.out.println(test.getCPI());
    System.out.println(test.getMIPS());
  }
}

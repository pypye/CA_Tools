package app.ca_tools;

public class Cache_L1 {

  private double _lw_sw;
  private double _CPI;
  private double _Icache;
  private double _Dcache;
  private double _missCycle;

  public Cache_L1(double _lw_sw, double _CPI, double _Icache, double _Dcache, double _missCycle) {
    this._lw_sw = _lw_sw / 100;
    this._CPI = _CPI;
    this._Icache = _Icache / 100;
    this._Dcache = _Dcache / 100;
    this._missCycle = _missCycle;
  }

  public double get_lw_sw() {
    return _lw_sw;
  }

  public void set_lw_sw(double _lw_sw) {
    this._lw_sw = _lw_sw;
  }

  public double get_CPI() {
    return _CPI;
  }

  public void set_CPI(double _CPI) {
    this._CPI = _CPI;
  }

  public double get_Icache() {
    return _Icache;
  }

  public void set_Icache(double _Icache) {
    this._Icache = _Icache;
  }

  public double get_Dcache() {
    return _Dcache;
  }

  public void set_Dcache(double _Dcache) {
    this._Dcache = _Dcache;
  }

  public double get_missCycle() {
    return _missCycle;
  }

  public void set_missCycle(double _missCycle) {
    this._missCycle = _missCycle;
  }

  public double getRealCPI() {
    return _lw_sw * _Dcache * _missCycle + _Icache * _missCycle + _CPI;
  }

  public double getSlower() {
    return getRealCPI() / _CPI;
  }

  public static void main(String[] args) {
    Cache_L1 test = new Cache_L1(18, 2, 5, 4, 130);
    System.out.println(test.getRealCPI());
    System.out.println(test.getSlower());
  }
}

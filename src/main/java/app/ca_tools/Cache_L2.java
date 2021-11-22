package app.ca_tools;

public class Cache_L2 {

  private double _IdealCPI;
  private double _CPU;
  private double _missL1;
  private double _timeRam;
  private double _timeL2;
  private double _missTotal;
  private double _missCycle;

  public Cache_L2(double _IdealCPI, double _CPU, double _missL1, double _timeRam, double _timeL2,
      double _missTotal, double _missCycle) {
    this._IdealCPI = _IdealCPI;
    this._CPU = _CPU;
    this._missL1 = _missL1 / 100;
    this._timeRam = _timeRam;
    this._timeL2 = _timeL2;
    this._missTotal = _missTotal / 100;
    this._missCycle = _missCycle;
  }

  // nanosecond
  public double getCPUClockCycle() {
    return 1 / _CPU;
  }

  public double getMissPenalty() {
    return _timeRam / getCPUClockCycle();
  }

  public double getCPIL1() {
    return _IdealCPI + _missL1 * getMissPenalty();
  }

  public double getPrimaryMiss_L2Hit() {
    return _timeL2 / getCPUClockCycle();
  }

  public double getCPIL2() {
    return _IdealCPI + _missL1 * getPrimaryMiss_L2Hit() + _missTotal * _missCycle;
  }

  public double getL2FasterL1() {
    return getCPIL1() / getCPIL2();
  }


  public double get_IdealCPI() {
    return _IdealCPI;
  }

  public void set_IdealCPI(double _IdealCPI) {
    this._IdealCPI = _IdealCPI;
  }

  public double get_CPU() {
    return _CPU;
  }

  public void set_CPU(double _CPU) {
    this._CPU = _CPU;
  }

  public double get_missL1() {
    return _missL1;
  }

  public void set_missL1(double _missL1) {
    this._missL1 = _missL1;
  }

  public double get_timeRam() {
    return _timeRam;
  }

  public void set_timeRam(double _timeRam) {
    this._timeRam = _timeRam;
  }

  public double get_timeL2() {
    return _timeL2;
  }

  public void set_timeL2(double _timeL2) {
    this._timeL2 = _timeL2;
  }

  public double get_missTotal() {
    return _missTotal;
  }

  public void set_missTotal(double _missTotal) {
    this._missTotal = _missTotal;
  }

  public double get_missCycle() {
    return _missCycle;
  }

  public void set_missCycle(double _missCycle) {
    this._missCycle = _missCycle;
  }

  public static void main(String[] args) {
    Cache_L2 test = new Cache_L2(1, 4, 2, 100, 5, 0.5, 500);
    System.out.println(test.getL2FasterL1());
    System.out.println(test.getCPIL1());
    System.out.println(test.getCPIL2());
    System.out.println(test.getCPUClockCycle());
    System.out.println(test.getMissPenalty());
  }
}

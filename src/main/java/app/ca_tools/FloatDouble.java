package app.ca_tools;

public class FloatDouble {
    public static final int BIAS = 1023;
    private boolean sign;
    private String exponent;
    private String fraction;

    public FloatDouble(boolean sign, String exponent, String fraction) {
        this.sign = sign;
        this.exponent = exponent;
        this.fraction = fraction;
    }

    public boolean isSign() {
        return sign;
    }

    public String getExponent() {
        return exponent;
    }

    public String getFraction() {
        return fraction;
    }

    public static String convertNormalizeToDouble(Normalize input) throws Exception {
        int exponent = input.getBase() + FloatDouble.BIAS;
        StringBuilder binExponent = new StringBuilder();
        for (int i = 10; i >= 0; i--) {
            if (exponent < Math.pow(2, i)) {
                binExponent.append("0");
            } else {
                exponent -= Math.pow(2, i);
                binExponent.append("1");
            }
        }
        StringBuilder fraction = new StringBuilder(input.getFraction().substring(2));
        while (fraction.length() < 52) fraction.append("0");
        return String.format("%s%s%s", input.isSign() ? "1" : "0", binExponent, fraction.substring(0, 52));
    }

}

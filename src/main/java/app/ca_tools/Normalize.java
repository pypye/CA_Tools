package app.ca_tools;

public class Normalize {
    private boolean sign;
    private String fraction;
    private int base;

    public Normalize(boolean sign, String text, int base) {
        this.sign = sign;
        this.fraction = text;
        this.base = base;
    }

    public boolean isSign() {
        return sign;
    }

    public String getFraction() {
        return fraction;
    }

    public int getBase() {
        return base;
    }

    public static Normalize convert10ToNormalize(String number) throws Exception {
        double input = Double.parseDouble(number);
        double posInput = Math.abs(input);
        StringBuilder intPart = new StringBuilder();
        for (int i = 63; i >= 0; i--) {
            if (posInput < Math.pow(2, i)) {
                intPart.append('0');
            } else {
                posInput -= Math.pow(2, i);
                intPart.append('1');
            }
        }
        StringBuilder fracPart = new StringBuilder();
        for (int i = 1; i <= 63; i++) {
            if (posInput < Math.pow(2, -i)) {
                fracPart.append('0');
            } else {
                posInput -= Math.pow(2, -i);
                fracPart.append('1');
            }
        }
        String intPartStr = intPart.toString().replaceAll("^0+", "");
        String fracPartStr = fracPart.toString().replaceAll("0*$", "");
        if (intPartStr.length() < 1) {
            int cnt = 0;
            int base = 0;
            while (fracPartStr.charAt(cnt) == '0') {
                cnt++;
                base--;
            }
            String ans = fracPartStr.substring(0, cnt + 1) + "." + fracPartStr.substring(cnt + 1);
            return new Normalize(input < 0, ans.replaceAll("^0+", "").replaceAll("0*$", ""), base - 1);

        } else {
            String ans = intPartStr.charAt(0) + "." + intPartStr.substring(1) + fracPartStr;
            return new Normalize(input < 0, ans.replaceAll("^0+", "").replaceAll("0*$", ""), intPartStr.length() - 1);
        }
    }

    public static Normalize convertSingleToNormalize(FloatSingle input) {
        boolean sign = input.isSign();
        String exponent = input.getExponent();
        String fraction = input.getFraction();
        int base10Exponent = 0;
        for (int i = 0; i < exponent.length(); i++) {
            if (exponent.charAt(i) == '1') {
                base10Exponent += Math.pow(2, exponent.length() - 1 - i);
            }
        }
        base10Exponent -= FloatSingle.BIAS;
        return new Normalize(sign, "1." + fraction.replaceAll("0*$", ""), base10Exponent);
    }

    public static Normalize convertDoubleToNormalize(FloatDouble input) {
        boolean sign = input.isSign();
        String exponent = input.getExponent();
        String fraction = input.getFraction();
        int base10Exponent = 0;
        for (int i = 0; i < exponent.length(); i++) {
            if (exponent.charAt(i) == '1') {
                base10Exponent += Math.pow(2, exponent.length() - 1 - i);
            }
        }
        base10Exponent -= FloatDouble.BIAS;
        return new Normalize(sign, "1." + fraction.replaceAll("0*$", ""), base10Exponent);
    }
}

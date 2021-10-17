package app.ca_tools;

public class Base10 {
    public static String convertNormalizeTo10(Normalize input) throws Exception {
        boolean sign = input.isSign();
        String fraction = input.getFraction().substring(2);
        int base = input.getBase();
        double ans = 1;
        for (int i = 0; i < fraction.length(); i++) {
            if (fraction.charAt(i) == '1') {
                ans += Math.pow(2, -i - 1);
            }
        }
        ans *= Math.pow(2, base);
        return (sign ? "-" : "") + ans;
    }

    public static String convertSingleToBase10(FloatSingle input) throws Exception {
        Normalize output1 = Normalize.convertSingleToNormalize(input);
        return convertNormalizeTo10(output1);
    }

    public static String convertDoubleToBase10(FloatDouble input) throws Exception {
        Normalize output1 = Normalize.convertDoubleToNormalize(input);
        return convertNormalizeTo10(output1);
    }
}

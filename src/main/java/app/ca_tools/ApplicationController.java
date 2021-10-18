package app.ca_tools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class ApplicationController {
    private static final String ERROR = "Đấy là tính năng, không phải lỗi. Nếu bạn nghĩ nó là lỗi thì tự tìm lỗi nhé :)))";

    private void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    //static point 2 to 10
    @FXML
    private TextField staticPoint2to10Int, staticPoint2to10Frac, staticPoint2to10Output;
    @FXML
    private CheckBox staticPoint2to10FirstBit;
    @FXML
    private Label staticPoint2to10Error;

    @FXML
    public void initialize() {
        base10to2ChoiceType.setItems(FXCollections.observableArrayList("Chuẩn bit dấu", "Bù 1", "Bù 2"));
        base10to2ChoiceType.setValue("Bù 2");
        base2to10ChoiceType.setItems(FXCollections.observableArrayList("Chuẩn bit dấu", "Bù 1", "Bù 2"));
        base2to10ChoiceType.setValue("Bù 2");
        normalizeSign.setItems(FXCollections.observableArrayList("+", "-"));
        normalizeSign.setValue("+");
        calcExpression.setItems(FXCollections.observableArrayList("Cộng", "Trừ", "Nhân", "Chia"));
        calcExpression.setValue("Cộng");
        calcSign1.setItems(FXCollections.observableArrayList("+", "-"));
        calcSign1.setValue("+");
        calcSign2.setItems(FXCollections.observableArrayList("+", "-"));
        calcSign2.setValue("+");
    }

    @FXML
    public void onStaticPoint2to10ConvertClick() {
        try {
            String intPart = staticPoint2to10Int.getText().trim();
            String fracPart = staticPoint2to10Frac.getText().trim();
            double ans = 0.0f;
            double v = (intPart.charAt(0) == '0' ? 0 : Math.pow(2, intPart.length() - 1));
            if (staticPoint2to10FirstBit.isSelected()) {
                ans -= v;
            } else {
                ans += v;
            }
            for (int i = 1; i < intPart.length(); i++) {
                ans += (intPart.charAt(i) == '0' ? 0 : Math.pow(2, intPart.length() - 1 - i));
            }
            for (int i = 0; i < fracPart.length(); i++) {
                ans += (fracPart.charAt(i) == '0' ? 0 : Math.pow(2, -i - 1));
            }
            staticPoint2to10Output.setText(String.valueOf(ans));
            staticPoint2to10Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            staticPoint2to10Error.setText(ERROR);
        }

    }

    @FXML
    public void onStaticPoint2to10CopyClick() {
        copyToClipboard(staticPoint2to10Output.getText());
    }

    //static point 10 to 2
    @FXML
    private TextField staticPoint10to2tInput, staticPoint10to2IntCount, staticPoint10to2FracCount, staticPoint10to2Output;
    @FXML
    private Label staticPoint10to2Error;

    @FXML
    public void onStaticPoint10to2ConvertClick() {
        try {
            double input = Double.parseDouble(staticPoint10to2tInput.getText().trim());
            int intCount = Integer.parseInt(staticPoint10to2IntCount.getText().trim());
            int fracCount = Integer.parseInt(staticPoint10to2FracCount.getText().trim());
            StringBuilder output = new StringBuilder();
            if (input < 0) {
                input += Math.pow(2, intCount - 1);
                output.append("1");
            } else {
                output.append("0");
            }
            for (int i = intCount - 2; i >= 0; i--) {
                if (input < Math.pow(2, i)) {
                    output.append("0");
                } else {
                    input -= Math.pow(2, i);
                    output.append("1");
                }
            }
            output.append(".");
            for (int i = 1; i <= fracCount; i++) {
                if (input < Math.pow(2, -i)) {
                    output.append("0");
                } else {
                    input -= Math.pow(2, -i);
                    output.append("1");
                }
            }
            staticPoint10to2Output.setText(output.toString());
            staticPoint10to2Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            staticPoint10to2Error.setText(ERROR);
        }
    }

    @FXML
    public void onStaticPoint10to2CopyClick() {
        copyToClipboard(staticPoint10to2Output.getText());
    }

    //base 2 to 10
    @FXML
    private TextField base2to10Input, base2to10Output;
    @FXML
    private ChoiceBox<String> base2to10ChoiceType;
    @FXML
    private Label base2to10Error;

    @FXML
    public void onBase2to10ConvertClick() {
        try {
            String input = base2to10Input.getText();
            int ans = 0;
            for (int i = 1; i < input.length(); i++) {
                if (input.charAt(i) == '1') {
                    ans += Math.pow(2, input.length() - 1 - i);
                }
            }
            if (input.charAt(0) == '1') {
                if (base2to10ChoiceType.getValue().equals("Chuẩn bit dấu")) {
                    ans = -ans;
                } else {
                    ans -= Math.pow(2, input.length() - 1);
                }
                if (base2to10ChoiceType.getValue().equals("Bù 1")) {
                    ans += 1;
                }
            }
            base2to10Output.setText(String.valueOf(ans));
            base2to10Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            base2to10Error.setText(ERROR);
        }
    }

    @FXML
    public void onBase2to10CopyClick() {
        copyToClipboard(base2to10Output.getText());
    }

    //base 10 to 2
    @FXML
    private TextField base10to2Input, base10to2BitCount, base10to2Output;
    @FXML
    private ChoiceBox<String> base10to2ChoiceType;
    @FXML
    private Label base10to2Error;

    @FXML
    public void onBase10to2ConvertClick() {
        try {
            int input = Integer.parseInt(base10to2Input.getText().trim());
            int bitCount = Integer.parseInt(base10to2BitCount.getText().trim());
            if (base10to2ChoiceType.getValue().equals("Bù 2") && (input >= Math.pow(2, bitCount - 1) || input < -Math.pow(2, bitCount - 1))) {
                base10to2Error.setText(null);
                base10to2Output.setText("tràn số");
                return;
            }
            if ((base10to2ChoiceType.getValue().equals("Chuẩn bit dấu") || base10to2ChoiceType.getValue().equals("Bù 1")) && (input >= Math.pow(2, bitCount - 1) || input <= -Math.pow(2, bitCount - 1))) {
                base10to2Error.setText(null);
                base10to2Output.setText("tràn số");
                return;
            }
            int posInput = Math.abs(input);
            StringBuilder ans = new StringBuilder();
            if (base10to2ChoiceType.getValue().equals("Bù 2") && input < 0) posInput -= 1;
            for (int i = bitCount - 1; i >= 0; i--) {
                if (posInput < Math.pow(2, i)) {
                    ans.append("0");
                } else {
                    posInput -= Math.pow(2, i);
                    ans.append("1");
                }
            }
            if (input < 0) {
                if (base10to2ChoiceType.getValue().equals("Chuẩn bit dấu")) ans.setCharAt(0, '1');
                else {
                    for (int i = 0; i < ans.length(); i++) {
                        ans.setCharAt(i, (ans.charAt(i) == '0') ? '1' : '0');
                    }
                }
            }
            base10to2Output.setText(String.valueOf(ans));
            base10to2Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            base10to2Error.setText(ERROR);
        }

    }

    @FXML
    public void onBase10to2CopyClick() {
        copyToClipboard(base10to2Output.getText());
    }

    //normalize to any
    @FXML
    private ChoiceBox<String> normalizeSign;
    @FXML
    private TextField normalizeFraction, normalizeBase;
    @FXML
    private TextField normalize10Output, normalizeSingleOutput, normalizeDoubleOutput;
    @FXML
    private Label normalizeError;

    @FXML
    public void onNormalizeConvertClick() {
        try {
            boolean _sign = normalizeSign.getValue().equals("-");
            String _fraction = "1." + normalizeFraction.getText();
            int _base = Integer.parseInt(normalizeBase.getText());
            Normalize input = new Normalize(_sign, _fraction, _base);
            normalize10Output.setText(Base10.convertNormalizeTo10(input));
            normalizeSingleOutput.setText(FloatSingle.convertNormalizeToSingle(input));
            normalizeDoubleOutput.setText(FloatDouble.convertNormalizeToDouble(input));
            normalizeError.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            normalizeError.setText(ERROR);
        }
    }

    @FXML
    public void onNormalize10CopyClick() {
        copyToClipboard(normalize10Output.getText());
    }

    @FXML
    public void onNormalizeSingleCopyClick() {
        copyToClipboard(normalizeSingleOutput.getText());
    }

    @FXML
    public void onNormalizeDoubleCopyClick() {
        copyToClipboard(normalizeDoubleOutput.getText());
    }

    //10 to any
    @FXML
    private TextField base10Input, base10NormalizeOutput, base10SingleOutput, base10DoubleOutput;
    @FXML
    private Label base10Error;

    @FXML
    public void onBase10ConvertClick() {
        try {
            Normalize input = Normalize.convert10ToNormalize(base10Input.getText());
            String newAns = String.format("%s%sx2^(%s)\n", input.isSign() ? "-" : "", input.getFraction(), input.getBase());
            base10NormalizeOutput.setText(newAns);
            base10SingleOutput.setText(FloatSingle.convertNormalizeToSingle(input));
            base10DoubleOutput.setText(FloatDouble.convertNormalizeToDouble(input));
            base10Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            base10Error.setText(ERROR);
        }
    }

    @FXML
    public void onBase10NormalizeCopyClick() {
        copyToClipboard(normalize10Output.getText());
    }

    @FXML
    public void onBase10SingleCopyClick() {
        copyToClipboard(normalizeSingleOutput.getText());
    }

    @FXML
    public void onBase10DoubleCopyClick() {
        copyToClipboard(normalizeDoubleOutput.getText());
    }

    //single to any
    @FXML
    private TextField singleInput, singleNormalizeOutput, single10Output, singleDoubleOutput;
    @FXML
    private Label singleError;

    @FXML
    public void onSingleConvertClick() {
        try {
            String input = singleInput.getText();
            FloatSingle inputSingle = new FloatSingle(input.charAt(0) == '1', input.substring(1, 9), input.substring(9));
            Normalize output = Normalize.convertSingleToNormalize(inputSingle);
            String newAns = String.format("%s%sx2^(%s)\n", output.isSign() ? "-" : "", output.getFraction(), output.getBase());
            singleNormalizeOutput.setText(newAns);
            single10Output.setText(Base10.convertSingleToBase10(inputSingle));
            singleDoubleOutput.setText(FloatDouble.convertNormalizeToDouble(output));
            singleError.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            singleError.setText(ERROR);
        }
    }

    @FXML
    public void onSingleNormalizeCopyClick() {
        copyToClipboard(singleNormalizeOutput.getText());
    }

    @FXML
    public void onSingle10CopyClick() {
        copyToClipboard(single10Output.getText());
    }

    @FXML
    public void onSingleDoubleCopyClick() {
        copyToClipboard(singleDoubleOutput.getText());
    }

    //double to any
    @FXML
    private TextField doubleInput, doubleNormalizeOutput, double10Output, doubleSingleOutput;
    @FXML
    private Label doubleError;

    @FXML
    public void onDoubleConvertClick() {
        try {
            String input = doubleInput.getText();
            FloatDouble inputDouble = new FloatDouble(input.charAt(0) == '1', input.substring(1, 12), input.substring(12));
            Normalize output = Normalize.convertDoubleToNormalize(inputDouble);
            String newAns = String.format("%s%sx2^(%s)\n", output.isSign() ? "-" : "", output.getFraction(), output.getBase());
            doubleNormalizeOutput.setText(newAns);
            double10Output.setText(Base10.convertDoubleToBase10(inputDouble));
            doubleSingleOutput.setText(FloatSingle.convertNormalizeToSingle(output));
            doubleError.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            doubleError.setText(ERROR);
        }
    }

    @FXML
    public void onDoubleNormalizeCopyClick() {
        copyToClipboard(doubleNormalizeOutput.getText());
    }

    @FXML
    public void onDouble10CopyClick() {
        copyToClipboard(double10Output.getText());
    }

    @FXML
    public void onDoubleSingleCopyClick() {
        copyToClipboard(doubleSingleOutput.getText());
    }

    //calc normalize
    @FXML
    private ChoiceBox<String> calcSign1, calcSign2, calcExpression;
    @FXML
    private TextField calcFraction1, calcFraction2, calcBase1, calcBase2;
    @FXML
    private TextField calcNormalizeOutput, calc10Output, calcSingleOutput, calcDoubleOutput;
    @FXML
    private Label calcError;

    @FXML
    public void onCalcClick() {
        try {
            boolean sign1 = calcSign1.getValue().equals("-");
            boolean sign2 = calcSign2.getValue().equals("-");
            String fraction1 = "1." + calcFraction1.getText();
            String fraction2 = "1." + calcFraction2.getText();
            int base1 = Integer.parseInt(calcBase1.getText());
            int base2 = Integer.parseInt(calcBase2.getText());
            Normalize input1 = new Normalize(sign1, fraction1, base1);
            Normalize input2 = new Normalize(sign2, fraction2, base2);
            double base10Input1 = Double.parseDouble(Base10.convertNormalizeTo10(input1));
            double base10Input2 = Double.parseDouble(Base10.convertNormalizeTo10(input2));
            double base10Output = 0;
            if (calcExpression.getValue().equals("Cộng")) base10Output = base10Input1 + base10Input2;
            else if (calcExpression.getValue().equals("Trừ")) base10Output = base10Input1 - base10Input2;
            else if (calcExpression.getValue().equals("Nhân")) base10Output = base10Input1 * base10Input2;
            else base10Output = base10Input1 / base10Input2;
            String string10Output = String.valueOf(base10Output);
            Normalize normalizeOutput = Normalize.convert10ToNormalize(string10Output);
            String newAns = String.format("%s%sx2^(%s)\n", normalizeOutput.isSign() ? "-" : "", normalizeOutput.getFraction(), normalizeOutput.getBase());
            String singleOutput = FloatSingle.convertNormalizeToSingle(normalizeOutput);
            String doubleOutput = FloatDouble.convertNormalizeToDouble(normalizeOutput);
            calcNormalizeOutput.setText(newAns);
            calc10Output.setText(string10Output);
            calcSingleOutput.setText(singleOutput);
            calcDoubleOutput.setText(doubleOutput);
            calcError.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            calcError.setText(ERROR);
        }
    }

    @FXML
    public void onCalcCopyNormalizeClick() {
        copyToClipboard(calcNormalizeOutput.getText());
    }

    @FXML
    public void onCalcCopy10Click() {
        copyToClipboard(calc10Output.getText());
    }

    @FXML
    public void onCalcCopySingleClick() {
        copyToClipboard(calcSingleOutput.getText());
    }

    @FXML
    public void onCalcCopyDoubleClick() {
        copyToClipboard(calcDoubleOutput.getText());
    }

    //other problem
    @FXML
    private TextField otherBusCount, otherBusRW, otherBusFreq, otherBusOutput, otherBusOutputGB;
    @FXML
    private TextField otherAddressInput, otherAddressOutput;
    @FXML
    private TextField otherAddress2Size, otherAddress2Count, otherAddress2Output;

    @FXML
    public void onBusClick() {
        try {
            double count = Double.parseDouble(otherBusCount.getText());
            double RW = Double.parseDouble(otherBusRW.getText());
            double freq = Double.parseDouble(otherBusFreq.getText());
            double RWPerSec = (freq * 1000000) / RW;
            double bytes = count / 8;
            double speed = (RWPerSec * bytes) / 1048576;
            otherBusOutput.setText(String.valueOf(speed));
            otherBusOutputGB.setText(String.valueOf(speed / 1024));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onAddressClick() {
        try {
            double input = Double.parseDouble(otherAddressInput.getText());
            int output = (int) Math.ceil(Math.log(input) / Math.log(2));
            otherAddressOutput.setText(String.valueOf(output));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void onAddress2Click() {
        try {
            double size = Double.parseDouble(otherAddress2Size.getText());
            double count = Double.parseDouble(otherAddress2Count.getText());
            double output = (size * 1024 * 1024 * 1024) / (count / 8);
            int line = (int) Math.ceil(Math.log(output) / Math.log(2));
            otherAddress2Output.setText(String.valueOf(line));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
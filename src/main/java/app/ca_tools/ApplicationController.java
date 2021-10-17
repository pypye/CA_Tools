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
            staticPoint2to10Error.setText(null);
            staticPoint2to10Output.setText(String.valueOf(ans));
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
            staticPoint10to2Error.setText(null);
            staticPoint10to2Output.setText(output.toString());
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
            base10to2Error.setText(null);
            base10to2Output.setText(String.valueOf(ans));
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
    private TextField normalizeSign, normalizeFraction, normalizeBase;
    @FXML
    private TextField normalize10Output, normalizeSingleOutput, normalizeDoubleOutput;
    @FXML
    private Label normalizeError;

    @FXML
    public void onNormalizeConvertClick() {
        try {
            boolean _sign = normalizeSign.getText().equals("-");
            String _fraction = "1." + normalizeFraction.getText();
            int _base = Integer.parseInt(normalizeBase.getText());
            Normalize input = new Normalize(_sign, _fraction, _base);
            normalize10Output.setText(Base10.convertNormalizeTo10(input));
            normalizeSingleOutput.setText(FloatSingle.convertNormalizeToSingle(input));
            normalizeDoubleOutput.setText(FloatDouble.convertNormalizeToDouble(input));
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
    private TextField singleInput, singleNormalizeOutput, single10Output;
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

    //double to any
    @FXML
    private TextField doubleInput, doubleNormalizeOutput, double10Output;
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

}
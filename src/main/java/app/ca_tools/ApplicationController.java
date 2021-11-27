package app.ca_tools;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ApplicationController {
    private static final String ERROR = "Sai syntax rồi?";
    //chapter 2 static point 2 to 10
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
        tioChoiceBox.setItems(FXCollections.observableArrayList("byte", "KB", "MB", "slots"));
        tioChoiceBox.setValue("KB");
    }

    @FXML
    public void onStaticPoint2to10ConvertClick() {
        try {
            String intPart = staticPoint2to10Int.getText().trim();
            String fracPart = staticPoint2to10Frac.getText().trim();
            double ans = 0.0f;
            double v = (intPart.charAt(0) == '0' ? 0 : Math.pow(2, intPart.length() - 1));
            if (staticPoint2to10FirstBit.isSelected()) ans -= v;
            else ans += v;
            for (int i = 1; i < intPart.length(); i++)
                ans += (intPart.charAt(i) == '0' ? 0 : Math.pow(2, intPart.length() - 1 - i));
            for (int i = 0; i < fracPart.length(); i++) ans += (fracPart.charAt(i) == '0' ? 0 : Math.pow(2, -i - 1));
            staticPoint2to10Output.setText(String.valueOf(ans));
            staticPoint2to10Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            staticPoint2to10Error.setText(ERROR);
        }
    }

    //chapter 2 static point 10 to 2
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
            } else output.append("0");
            for (int i = intCount - 2; i >= 0; i--) {
                if (input < Math.pow(2, i)) output.append("0");
                else {
                    input -= Math.pow(2, i);
                    output.append("1");
                }
            }
            output.append(".");
            for (int i = 1; i <= fracCount; i++) {
                if (input < Math.pow(2, -i)) output.append("0");
                else {
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

    //chapter 2 base 2 to 10
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
            for (int i = 1; i < input.length(); i++)
                if (input.charAt(i) == '1') ans += Math.pow(2, input.length() - 1 - i);
            if (input.charAt(0) == '1') {
                if (base2to10ChoiceType.getValue().equals("Chuẩn bit dấu")) ans = -ans;
                else ans -= Math.pow(2, input.length() - 1);
                if (base2to10ChoiceType.getValue().equals("Bù 1")) ans += 1;
            }
            base2to10Output.setText(String.valueOf(ans));
            base2to10Error.setText(null);
        } catch (Exception e) {
            e.printStackTrace();
            base2to10Error.setText(ERROR);
        }
    }

    //chapter 2 base 10 to 2
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
                if (posInput < Math.pow(2, i)) ans.append("0");
                else {
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

    //chapter 2 normalize to any
    @FXML
    private ChoiceBox<String> normalizeSign;
    @FXML
    private TextField normalizeFraction, normalizeBase, normalize10Output, normalizeSingleOutput, normalizeDoubleOutput;
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

    //chapter 2 10 to any
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

    //chapter 2 single to any
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

    //chapter 2 double to any
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

    //chapter 2 calc normalize
    @FXML
    private ChoiceBox<String> calcSign1, calcSign2, calcExpression;
    @FXML
    private TextField calcFraction1, calcFraction2, calcBase1, calcBase2, calcNormalizeOutput, calc10Output, calcSingleOutput, calcDoubleOutput;
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
            double base10Output;
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

    //chapter 2 other problem
    @FXML
    private TextField otherBusCount, otherBusRW, otherBusFreq, otherBusOutput, otherBusOutputGB, otherAddressInput, otherAddressOutput, otherAddress2Size, otherAddress2Count, otherAddress2Output;
    @FXML
    private Label otherError;

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
            otherError.setText(null);
        } catch (Exception e) {
            otherError.setText(ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onAddressClick() {
        try {
            double input = Double.parseDouble(otherAddressInput.getText());
            int output = (int) Math.ceil(Math.log(input) / Math.log(2));
            otherAddressOutput.setText(String.valueOf(output));
            otherError.setText(null);
        } catch (Exception e) {
            otherError.setText(ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onAddress2Click() {
        try {
            double size = Double.parseDouble(otherAddress2Size.getText());
            double count = Double.parseDouble(otherAddress2Count.getText());
            double output = (size * 1024 * 1024 * 1024) / (count / 8);
            int line = (int) Math.ceil((float) Math.log(output) / (float) Math.log(2));
            otherAddress2Output.setText(String.valueOf(line));
            otherError.setText(null);
        } catch (Exception e) {
            otherError.setText(ERROR);
            e.printStackTrace();
        }
    }

    //mips components
    @FXML
    private TextField mipsInput, mips_R_Op, mips_R_Rs, mips_R_Rt, mips_R_Rd, mips_R_Shamt, mips_R_Func, mips_I_Op, mips_I_Rs, mips_I_Rt, mips_I_Offset, mips_J_Op, mips_J_Address;
    @FXML
    private Label mipsError;

    @FXML
    public void onMipsClick() {
        try {
            String input = mipsInput.getText();
            CalcMIPSComponent compR = new CalcMIPSComponent(input, 'R');
            CalcMIPSComponent compI = new CalcMIPSComponent(input, 'I');
            CalcMIPSComponent compJ = new CalcMIPSComponent(input, 'J');
            mips_R_Op.setText(String.valueOf(compR.getOp()));
            mips_R_Rs.setText(String.valueOf(compR.getRs()));
            mips_R_Rt.setText(String.valueOf(compR.getRt()));
            mips_R_Rd.setText(String.valueOf(compR.getRd()));
            mips_R_Shamt.setText(String.valueOf(compR.getShamt()));
            mips_R_Func.setText(String.valueOf(compR.getFunct()));
            mips_I_Op.setText(String.valueOf(compI.getOp()));
            mips_I_Rs.setText(String.valueOf(compI.getRs()));
            mips_I_Rt.setText(String.valueOf(compI.getRt()));
            mips_I_Offset.setText(String.valueOf(compI.getOffset()));
            mips_J_Op.setText(String.valueOf(compJ.getOp()));
            mips_J_Address.setText(String.valueOf(compJ.getAddress()));
            mipsError.setText(null);
        } catch (Exception e) {
            mipsError.setText(ERROR);
            e.printStackTrace();
        }
    }

    //tag index offset
    @FXML
    private ChoiceBox<String> tioChoiceBox;
    @FXML
    private TextField cacheSizeTioInput, slotSizeTioInput, addressRegTioInput, RefTioInput, tagTioOutput, indexTioOutput, offsetTioOutput, allTioOutput, rateTioOutput;
    @FXML
    private Label tioError;

    @FXML
    public void onTioClick() {
        try {
            int input = Integer.parseInt(cacheSizeTioInput.getText());
            int slotSize = Integer.parseInt(slotSizeTioInput.getText());
            int addressReg = Integer.parseInt(addressRegTioInput.getText());
            int ref;
            if (RefTioInput.getText() == null || RefTioInput.getText().trim().equals("")) ref = -1;
            else ref = Integer.parseInt(RefTioInput.getText());
            if (tioChoiceBox.getValue().equals("KB")) input *= 1024;
            else if (tioChoiceBox.getValue().equals("MB")) input *= 1024 * 1024;
            else if (tioChoiceBox.getValue().equals("slots")) input *= slotSize;
            Calc_Tag_Index_Offset calc = new Calc_Tag_Index_Offset(input, slotSize, addressReg, ref);
            if (ref == -1) {
                tagTioOutput.setText("null");
                indexTioOutput.setText("null");
                offsetTioOutput.setText("null");
                allTioOutput.setText("null");
            } else {
                tagTioOutput.setText(calc.getRefTag());
                indexTioOutput.setText(calc.getRefIndex());
                offsetTioOutput.setText(calc.getRefOffset());
                allTioOutput.setText(calc.allBinary());
            }
            rateTioOutput.setText(String.valueOf(calc.getRatio()));

        } catch (Exception e) {
            tioError.setText(ERROR);
            e.printStackTrace();
        }
    }

    //multi-cycled + pipeline

    @FXML
    private TextField mul_R_per, mul_lw_per, mul_sw_per, mul_branch_per, mul_jump_per, mul_clock_rate, mul_cpi, mul_mips;
    @FXML
    private TextField pip_R_per, pip_lw_per, pip_sw_per, pip_branch_per, pip_jump_per, pip_lw_miss, pip_lw_miss_cyc, pip_branch_miss, pip_branch_miss_cyc, pip_jump_always_cyc, pip_clock_rate, pip_cpi, pip_mips, pip_times;
    @FXML
    private Label mul_pip_error;

    @FXML
    public void onMultiCycleClick() {
        try {
            int _R_type = Integer.parseInt(mul_R_per.getText());
            int _lw = Integer.parseInt(mul_lw_per.getText());
            int _sw = Integer.parseInt(mul_sw_per.getText());
            int _branch = Integer.parseInt(mul_branch_per.getText());
            int _jump = Integer.parseInt(mul_jump_per.getText());
            double _clock_rate;
            if (mul_clock_rate.getText() == null || mul_clock_rate.getText().trim().equals("")) _clock_rate = -1;
            else _clock_rate = Double.parseDouble(mul_clock_rate.getText());
            MIPS_NonPipeline multiCycled = new MIPS_NonPipeline(_R_type, _lw, _sw, _branch, _jump, _clock_rate);
            mul_cpi.setText(String.valueOf(multiCycled.getCPI()));
            if (_clock_rate == -1) mul_mips.setText("null");
            else mul_mips.setText(String.valueOf(multiCycled.getMIPS()));
            mul_pip_error.setText(null);
        } catch (Exception e) {
            mul_pip_error.setText(ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onPipelinedClick() {
        try {
            int _R_type = Integer.parseInt(pip_R_per.getText());
            int _lw = Integer.parseInt(pip_lw_per.getText());
            int _sw = Integer.parseInt(pip_sw_per.getText());
            int _branch = Integer.parseInt(pip_branch_per.getText());
            int _jump = Integer.parseInt(pip_jump_per.getText());
            int _lw_miss = Integer.parseInt(pip_lw_miss.getText());
            int _lw_miss_cyc = Integer.parseInt(pip_lw_miss_cyc.getText());
            int _branch_miss = Integer.parseInt(pip_branch_miss.getText());
            int _branch_miss_cyc = Integer.parseInt(pip_branch_miss_cyc.getText());
            int _jump_always_cyc = Integer.parseInt(pip_jump_always_cyc.getText());
            double _clock_rate;
            if (pip_clock_rate.getText() == null || pip_clock_rate.getText().trim().equals("")) _clock_rate = -1;
            else _clock_rate = Double.parseDouble(pip_clock_rate.getText());
            MIPS_pipeline pipeline = new MIPS_pipeline(_R_type, _lw, _sw, _branch, _jump, _lw_miss, _lw_miss_cyc, _branch_miss, _branch_miss_cyc, _jump_always_cyc, _clock_rate);
            MIPS_NonPipeline multiCycled = new MIPS_NonPipeline(_R_type, _lw, _sw, _branch, _jump, _clock_rate);
            pip_cpi.setText(String.format("%.2f", pipeline.getCPI()));
            if (_clock_rate == -1) pip_mips.setText("null");
            else pip_mips.setText(String.valueOf(pipeline.getMIPS()));
            pip_times.setText(String.format("%.2f", multiCycled.getCPI() / pipeline.getCPI()));
            mul_pip_error.setText(null);
        } catch (Exception e) {
            mul_pip_error.setText(ERROR);
            e.printStackTrace();
        }
    }

    //cache level1 and level2
    @FXML
    private TextField l1_lw_sw_per, l1_cpi_ideal, l1_i_miss_per, l1_d_miss_per, l1_miss_cost, l1_cpi, l1_slower;
    @FXML
    private TextField l2_lw_sw_per, l2_cpi_ideal, l2_l1_miss, l2_ram_access_time, l2_access_time, l2_global_miss, l2_cost, l2_cpi, l2_faster_l1;
    @FXML
    private Label l1_l2_error;

    @FXML
    public void onL1Click() {
        try {
            double _lw_sw = Double.parseDouble(l1_lw_sw_per.getText());
            double _cpi_ideal = Double.parseDouble(l1_cpi_ideal.getText());
            double _i_miss = Double.parseDouble(l1_i_miss_per.getText());
            double _d_miss = Double.parseDouble(l1_d_miss_per.getText());
            double _miss_cost = Double.parseDouble(l1_miss_cost.getText());
            Cache_L1 l1 = new Cache_L1(_lw_sw, _cpi_ideal, _i_miss, _d_miss, _miss_cost);
            l1_cpi.setText(String.format("%.2f", l1.getRealCPI()));
            l1_slower.setText(String.format("%.2f", l1.getSlower()));
            l1_l2_error.setText(null);
        } catch (Exception e) {
            l1_l2_error.setText(ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    public void onL2Click() {
        try {
            double _lw_sw = Double.parseDouble(l2_lw_sw_per.getText());
            double _cpi_ideal = Double.parseDouble(l2_cpi_ideal.getText());
            double _l1_miss = Double.parseDouble(l2_l1_miss.getText());
            double _ram_access_time = Double.parseDouble(l2_ram_access_time.getText());
            double _access_time = Double.parseDouble(l2_access_time.getText());
            double _global_miss = Double.parseDouble(l2_global_miss.getText());
            double _cost = Double.parseDouble(l2_cost.getText());
            Cache_L2 l2 = new Cache_L2(_lw_sw, _cpi_ideal, _l1_miss, _ram_access_time, _access_time, _global_miss, _cost);
            l2_cpi.setText(String.format("%.2f", l2.getCPIL2()));
            l2_faster_l1.setText(String.format("%.2f", l2.getL2FasterL1()));
            l1_l2_error.setText(null);
        } catch (Exception e) {
            l1_l2_error.setText(ERROR);
            e.printStackTrace();
        }
    }
}
package com.example.calculator_akhtakhanov;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView previousText;
    private EditText display;
    private Double memory = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        previousText = findViewById(R.id.PreviousText);
        display = findViewById(R.id.EditText);
        display.setShowSoftInputOnFocus(false);
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(display.getText().toString().equals("")){
            display.setText(strToAdd);
        }
        else{
            display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
        }
        display.setSelection(cursorPos + strToAdd.length());
    }

    public void btnClear(View view){
        display.setText("");
        previousText.setText("");
    }

    public void btnSkobky(View view){
        int cursorPos = display.getSelectionStart();
        int openSkb = 0;
        int closedSkb = 0;
        int textLen = display.getText().length();

        for (int i=0; i<cursorPos; i++){
            if (display.getText().toString().substring(i, i+1).equals("(")){
                openSkb++;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")){
                closedSkb++;
            }
        }

        if (openSkb == closedSkb || display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");
        }
        else if (closedSkb < openSkb && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);

    }

    public void btnStepen(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("^");
        }
    }

    public void btnDelenie(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("/");
        }
    }

    public void btnSeven(View view){
        updateText("7");
    }

    public void btnEight(View view){
        updateText("8");
    }

    public void btnNine(View view){
        updateText("9");
    }

    public void btnUmnozhenie(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("x");
        }
    }

    public void btnFour(View view){
        updateText("4");
    }

    public void btnFive(View view){
        updateText("5");
    }

    public void btnSix(View view){
        updateText("6");
    }

    public void btnMinus(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("-");
        }
    }

    public void btnOne(View view){
        updateText("1");
    }

    public void btnTwo(View view){
        updateText("2");
    }

    public void btnThree(View view){
        updateText("3");
    }

    public void btnPlus(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText("+");
        }
    }

    public void btnPlusMinus(View view){
        int cursorPos = display.getSelectionStart();
        String oldStr = display.getText().toString();
        String minus = "-";
        if(display.getText().toString().equals("")){
            display.setText("-");
            display.setSelection(display.getText().length());
        }
        else if (oldStr.charAt(0)=='-'){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(0, 1,"");
            display.setSelection(display.getText().length());
        }
        else{
            display.setText(String.format("%s%s",minus,oldStr));
            display.setSelection(display.getText().length());
        }
    }

    public void btnZero(View view){
        updateText("0");
    }

    public void btnZapyataya(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0) {
            updateText(",");
        }
    }

    public void btnRavno(View view){
        String userExp = display.getText().toString();

        //userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("x", "*");

        previousText.setText(userExp);
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void btnBackspace(View view){
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos!=0 && textLen != 0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

    public void btnPercent (View view){
        String str = display.getText().toString();
        Expression exp = new Expression(str+"/100");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        updateText(result);
    }

    public void btnMC (View view){
        memory = 0.0;
    }

    public void btnMplus (View view){
        String str = display.getText().toString();
        Double a = Double.parseDouble(str);
        memory += a;
    }

    public void btnMminus (View view){
        String str = display.getText().toString();
        Double a = Double.parseDouble(str);
        memory -= a;
    }

    public void btnMR (View view){
        display.setText(""+memory);
        previousText.setText("");
    }

    public void btnXSquared (View view){
        String str = display.getText().toString();
        Expression exp = new Expression(str+"^2");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        updateText(result);
    }

    public void btnXCube (View view){
        String str = display.getText().toString();
        Expression exp = new Expression(str+"^3");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        updateText(result);
    }

    public void btnEPowX (View view){
        updateText("e^");
    }

    public void btnTenPowX (View view){
        updateText("10^");
    }

    public void btnFact (View view){
        String str = display.getText().toString();
        Expression exp = new Expression(str+"!");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        updateText(result);
    }

    public void btnOneSplashX (View view){
        String str = display.getText().toString();
        Expression exp = new Expression("1/"+str);
        String result = String.valueOf(exp.calculate());
        display.setText("");
        updateText(result);
    }

    public void btnSquareRoot (View view){
        String str = display.getText().toString();
        Expression exp = new Expression("sqrt("+str+")");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        updateText(result);
    }

    public void btnLn (View view){
        updateText("ln(");
    }

    public void btnLogTen (View view){
        updateText("log10(");
    }

    public void btnLogTwo (View view){
        updateText("log2(");
    }

    public void btnSin (View view){
        updateText("sin(");
    }

    public void btnCos (View view){
        updateText("cos(");
    }

    public void btnTan (View view){
        updateText("tan(");
    }

    public void btnMod (View view){
        updateText("mod(");
    }

    public void btnLog (View view){
        updateText("log(");
    }

    public void btnArcSin (View view){
        updateText("asin(");
    }

    public void btnArcCos (View view){
        updateText("acos(");
    }

    public void btnArcTan (View view){
        updateText("atan(");
    }

    public void btnExp (View view){
        Expression exp = new Expression("e");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        previousText.setText("e");
        updateText(result);
    }

    public void btnPi (View view){
        Expression exp = new Expression("pi");
        String result = String.valueOf(exp.calculate());
        display.setText("");
        previousText.setText("π");
        updateText(result);
    }

}

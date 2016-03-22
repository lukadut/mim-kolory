package c.c.colors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import c.c.colors.Utils.InputFilterMinMax;
import c.c.colors.Utils.TextUpdateListener;

public class MainActivity extends AppCompatActivity {
    EditText editTextHsvH,editTextHsvS,editTextHsvV,editTextRgbaR,editTextRgbaB,editTextRgbaG,editTextAlpha,editTextRgbaHex;
    private Button buttonClearForm;
    EditText[] editTexts;
    ColorFormater colorFormater;
    View colorBox;
    private float[] hsv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colorFormater = new ColorFormater();
        hsv = new float[3];
        buttonClearForm = (Button)findViewById(R.id.buttonClearForm);
        colorBox = findViewById(R.id.colorBox);
        editTextHsvH=(EditText)findViewById(R.id.editTextHsvH);
        editTextHsvS      =(EditText)findViewById(R.id.editTextHsvS      );
        editTextHsvV      =(EditText)findViewById(R.id.editTextHsvV      );
        editTextRgbaR     =(EditText)findViewById(R.id.editTextRgbaR     );
        editTextRgbaB     =(EditText)findViewById(R.id.editTextRgbaB     );
        editTextRgbaG     =(EditText)findViewById(R.id.editTextRgbaG     );
        editTextAlpha     =(EditText)findViewById(R.id.editTextAlpha     );
        editTextRgbaHex   =(EditText)findViewById(R.id.editTextRgbaHex   );

        // ustawianie filtrów dla edittekst
        editTextAlpha.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        editTextRgbaR.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        editTextRgbaB.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        editTextRgbaG.setFilters(new InputFilter[]{new InputFilterMinMax(0, 255)});
        editTextHsvH.setFilters(new InputFilter[]{new InputFilterMinMax(0, 360)});
        editTextHsvS.setFilters(new InputFilter[]{new InputFilterMinMax(0, 1)});
        editTextHsvV.setFilters(new InputFilter[]{new InputFilterMinMax(0, 1)});
        editTexts = new EditText[]{editTextHsvH,editTextHsvS,editTextHsvV,editTextRgbaR,editTextRgbaB,editTextRgbaG,editTextAlpha,editTextRgbaHex};

        //przycisk czyszczący formatkę
        buttonClearForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(EditText editText:editTexts){
                    editText.setText("");
                }
            }
        });

        //ustawianie listenerów do reagowania na zmianę wartości w zadanym polu, z rozróznieniem RGB i HSV
        editTextAlpha.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                editTextAlpha.setSelection(editTextAlpha.getText().length());
                super.action();
            }
        });

        editTextRgbaR.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                colorFormater.convertRGBtoHSV();
                fillEditTexts();
                editTextRgbaR.setSelection(editTextRgbaR.getText().length());
                super.action();
            }
        });
        editTextRgbaB.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                colorFormater.convertRGBtoHSV();
                fillEditTexts();
                editTextRgbaB.setSelection(editTextRgbaB.getText().length());
            }
        });
        editTextRgbaG.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                colorFormater.convertRGBtoHSV();
                fillEditTexts();
                editTextRgbaG.setSelection(editTextRgbaG.getText().length());
            }
        });

        editTextHsvH.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                colorFormater.convertHSVtoRGB();
                fillEditTexts();
                editTextHsvH.setSelection(editTextHsvH.getText().length());
            }
        });

        editTextHsvS.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                colorFormater.convertHSVtoRGB();
                fillEditTexts();
                editTextHsvS.setSelection(editTextHsvS.getText().length());
            }
        });

        editTextHsvV.addTextChangedListener(new TextUpdateListener() {
            @Override
            public void action() {
                readEditTexts();
                colorFormater.convertHSVtoRGB();
                fillEditTexts();
                editTextHsvV.setSelection(editTextHsvV.getText().length());
                super.action();
            }
        });
    }

    /**
     * wypisywanie wartości na formatce
     */
    protected void fillEditTexts() {
        editTextHsvH.setText(colorFormater.getHsv()[0] + "");
        editTextHsvS.setText(colorFormater.getHsv()[1] + "");
        editTextHsvV.setText(colorFormater.getHsv()[2] + "");
        editTextRgbaR.setText(colorFormater.getRed() + "");
        editTextRgbaB.setText(colorFormater.getBlue() + "");
        editTextRgbaG.setText(colorFormater.getGreen() + "");
        editTextAlpha.setText(colorFormater.getAlpha() + "");
        colorBox.setBackgroundColor(Color.argb(colorFormater.getAlpha(), colorFormater.getRed(), colorFormater.getGreen(), colorFormater.getBlue()));
        editTextRgbaHex.setText(colorFormater.getHex());
    }

    /**
     * przekazywanie wartości do konwertera
     */
    protected void readEditTexts(){
        colorFormater.setAlpha(parseEditTextToInt(editTextAlpha,255));
        colorFormater.setBlue(parseEditTextToInt(editTextRgbaB));
        colorFormater.setGreen(parseEditTextToInt(editTextRgbaG));
        colorFormater.setRed(parseEditTextToInt(editTextRgbaR));
        hsv[0] = parseEditTextToInt(editTextHsvH)*1.0F;
        hsv[1] = parseEditTextToFloat(editTextHsvS);
        hsv[2] = parseEditTextToFloat(editTextHsvV);
        colorFormater.setHsv(hsv);
    }

    // funkcje do parsowania do typów liczbowych

    int parseEditTextToInt(EditText editText){
        return parseEditTextToInt(editText,0);
    }
    int parseEditTextToInt(EditText editText, int defaultValue){
        int i=defaultValue;
        try {
            i = Integer.parseInt(editText.getText().toString());
        } catch (Exception e){}
        return i;
    }

    float parseEditTextToFloat(EditText editText){
        float i=0.0F;
        try {
            i = Float.parseFloat(editText.getText().toString());
        } catch (Exception e){}
        return i;
    }
}

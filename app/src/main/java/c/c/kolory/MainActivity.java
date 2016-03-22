package c.c.kolory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button changeColorButton;
    private View mainView;
    private int[] avaibleColors;
    private int currentColor=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeColorButton = (Button) findViewById(R.id.button);
        mainView = findViewById(R.id.mainView);
        avaibleColors = getResources().getIntArray(R.array.colors);

        changeColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeBackgroundColor();
            }
        });
    }

    private void changeBackgroundColor(){
        currentColor = (++currentColor)%avaibleColors.length;
        Log.d("changeColor","currentColorIndex: " + currentColor);
        Log.d("changeColor","avaibleColors[" + currentColor + "]: " + avaibleColors[currentColor]);
        mainView.setBackgroundColor(avaibleColors[currentColor]);
        Log.d("arraycolor", "size: " + avaibleColors.length);
    }
}

package de.ardunoid.schwalbe;

import android.app.Activity;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.*;
import android.widget.TextView;


public class Gemischrechner extends Activity {

    private SeekBar mixBar;
    private SeekBar gasolineBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gemischrechner);


        mixBar = (SeekBar) findViewById(R.id.seekMix);
        gasolineBar = (SeekBar) findViewById(R.id.seekGasoline);

        OnSeekBarChangeListener seekbarChangeListener = new OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar mix) {}
            @Override
            public void onStartTrackingTouch(SeekBar mix) {}

            @Override
            public void onProgressChanged(SeekBar mix, int progress,boolean fromUser) {
                calculateMix();
            }
        };
        mixBar.setOnSeekBarChangeListener(seekbarChangeListener);
        gasolineBar.setOnSeekBarChangeListener(seekbarChangeListener);

    }
    public void calculateMix(){
        int progressMix = mixBar.getProgress();
        int progressGasInt = gasolineBar.getProgress();
        float progressGas = (float)progressGasInt;
        progressGas = progressGas/100;
        TextView mixLabel = (TextView)findViewById(R.id.textMixLabel);
        TextView gasolineLabel = (TextView)findViewById(R.id.textGasolineLiter);

        mixLabel.setText("1:" +  Integer.toString(progressMix));
        gasolineLabel.setText(Float.toString(progressGas) + " Liter");

        Log.d("Schwalbe", "Mix: " + String.valueOf(progressMix) + "Gasoline: " + String.valueOf(progressGas));
        TextView textResult = (TextView) findViewById(R.id.textResult);
        if(progressMix > 0 && progressGas > 0){
            int oil = progressGasInt / progressMix;
            float oilFloat = (float)oil;
            oilFloat = oilFloat/100;
            Log.d("Schwalbe", "Oil: " + Float.toString( oilFloat ));
            textResult.setText(Float.toString(oilFloat) + "Liter");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gemischrechner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

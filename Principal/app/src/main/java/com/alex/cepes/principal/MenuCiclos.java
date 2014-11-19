package com.alex.cepes.principal;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MenuCiclos extends Activity {


    TextView numberView;
    private NumberPicker numberPicker;
    private NumberPicker numberPickerS;
    private NumberPicker numberPickerT;
    private NumberPicker numberPickerST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ciclos);


        numberPicker = (NumberPicker) findViewById(R.id.numberpicker);
        numberPicker.setMaxValue(59);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(true);

        numberPickerS = (NumberPicker) findViewById(R.id.numberpickerS);
        numberPickerS.setMaxValue(59);
        numberPickerS.setMinValue(0);
        numberPickerS.setWrapSelectorWheel(true);

        numberPickerT = (NumberPicker) findViewById(R.id.numberpickerT);
        numberPickerT.setMaxValue(59);
        numberPickerT.setMinValue(0);
        numberPickerT.setWrapSelectorWheel(true);

        numberPickerST = (NumberPicker) findViewById(R.id.numberpickerST);
        numberPickerST.setMaxValue(59);
        numberPickerST.setMinValue(0);
        numberPickerST.setWrapSelectorWheel(true);
    }

        public void comienzaCiclo(View view){

            Intent i = new Intent(this, Ciclos.class);
            i.putExtra("minutos", numberPicker.getValue());
            i.putExtra("segundos", numberPickerS.getValue());
            i.putExtra("minutosT", numberPickerT.getValue());
            i.putExtra("segundosT", numberPickerST.getValue());


            if (numberPicker.getValue() == 0 && numberPickerS.getValue() == 0 && numberPickerT.getValue() == 0 && numberPickerST.getValue() == 0){
                Toast.makeText(getBaseContext(), "Datos no validos",
                        Toast.LENGTH_LONG).show();
            }
            else {
                if (numberPicker.getValue()+numberPickerS.getValue() > numberPickerT.getValue()+numberPickerST.getValue()){
                    Toast.makeText(getBaseContext(), "Datos no validos",
                            Toast.LENGTH_LONG).show();
                }else {
                    startActivity(i);
                }
            }



        }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_ciclos, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

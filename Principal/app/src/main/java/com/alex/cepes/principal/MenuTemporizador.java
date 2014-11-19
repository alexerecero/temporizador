package com.alex.cepes.principal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.Toast;


public class MenuTemporizador extends Activity {

    NumberPicker numberPicker;
    NumberPicker numberPickerS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_temporizador);


        numberPicker = (NumberPicker) findViewById(R.id.numberpicker);
        numberPicker.setMaxValue(59);
        numberPicker.setMinValue(0);
        numberPicker.setWrapSelectorWheel(true);

        numberPickerS = (NumberPicker) findViewById(R.id.numberpickerS);
        numberPickerS.setMaxValue(59);
        numberPickerS.setMinValue(0);
        numberPickerS.setWrapSelectorWheel(true);



    }
    public void comienzaTemporizador(View view){

        Intent i = new Intent(this, Temporizador.class);
        i.putExtra("minutos", numberPicker.getValue());
        i.putExtra("segundos", numberPickerS.getValue());

        if (numberPicker.getValue() == 0){
            if ( numberPickerS.getValue() != 0){
                startActivity(i);
            } else {
                Toast.makeText(getBaseContext(), "Datos no validos",
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_temporizador, menu);
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

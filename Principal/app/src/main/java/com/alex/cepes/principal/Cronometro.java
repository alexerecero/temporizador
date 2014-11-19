package com.alex.cepes.principal;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;


public class Cronometro extends Activity {

    Chronometer cronometro;
    Button btnEmpezar;
    Button btnDetener;
    Long memoCronometro;
    String estado="inactivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);


        cronometro = (Chronometer)findViewById(R.id.cronometro);
        btnDetener = (Button)findViewById(R.id.btnDetener);
        btnEmpezar = (Button)findViewById(R.id.btnEmpezar);

        btnEmpezar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (estado == "inactivo"){

                    cronometro.start();
                    estado = "activo";
                    btnEmpezar.setText("Pausar");
                    return;
                }
                if (estado == "activo"){
                    memoCronometro = SystemClock.elapsedRealtime();
                    cronometro.stop();
                    estado = "pausado";
                    btnEmpezar.setText("Continuar");
                    return;
                }else {
                    cronometro.setBase(cronometro.getBase() + SystemClock.elapsedRealtime() - memoCronometro);
                    cronometro.start();
                    estado = "activo";
                    btnEmpezar.setText("Pausar");
                }
            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cronometro.setBase(0);
                btnEmpezar.setText("Iniciar");
                estado = "inactivo";
            }
        });








    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
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

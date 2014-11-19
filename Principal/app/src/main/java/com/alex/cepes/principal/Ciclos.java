package com.alex.cepes.principal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;
import android.os.CountDownTimer;


public class Ciclos extends Activity {

    Chronometer cronometro;
    Button btnEmpezar;
    Button btnDetener;
    Long memoCronometro;
    String estado="inactivo";
    int minutos;
    int segundos;
    int minutosT;
    int segundosT;
    Bundle bundle;
    String tiempoM;
    String tiempoS;
    String tiempoMT;
    String tiempoST;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclos);

        bundle = getIntent().getExtras();
        cronometro = (Chronometer)findViewById(R.id.cronometro);
        btnDetener = (Button)findViewById(R.id.btnDetener);
        btnEmpezar = (Button)findViewById(R.id.btnEmpezar);
        minutos = bundle.getInt("minutos");
        segundos = bundle.getInt("segundos");
        tiempoM = Integer.toString(minutos);
        tiempoS = Integer.toString(segundos);

        minutosT = bundle.getInt("minutosT");
        segundosT = bundle.getInt("segundosT");
        tiempoMT = Integer.toString(minutosT);
        tiempoST = Integer.toString(segundosT);

        if (minutos<10){
            tiempoM = "0"+minutos;
        }
        if (segundos<10){
            tiempoS = "0"+segundos;
        }

        if (minutosT<10){
            tiempoMT = "0"+minutosT;
        }
        if (segundosT<10){
            tiempoST = "0"+segundosT;
        }



        btnEmpezar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                if (estado == "inactivo"){
                    cronometro.setBase(SystemClock.elapsedRealtime());
                    cronometro.start();
                    estado = "activo";
                    btnEmpezar.setText("Pausar");
                    //////////////////////////
                    cronometro.setBase(SystemClock.elapsedRealtime());
                    Chronometer mChronometer = (Chronometer) findViewById(R.id.cronometro);
                    mChronometer.start();


                    //IF you want to stop your chrono after X seconds or minutes.
                    mChronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                        public void onChronometerTick(Chronometer chronometer) {



                            if (chronometer.getText().toString().equalsIgnoreCase(tiempoM+":"+tiempoS)) {
                                //Define here what happens when the Chronometer reaches the time above.
                                Toast.makeText(getBaseContext(), ""+cronometro.getBase(),
                                        Toast.LENGTH_LONG).show();
                                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(50);
                            }



                            if (chronometer.getText().toString().equalsIgnoreCase(tiempoMT+":"+tiempoST)) {
                                //Define here what happens when the Chronometer reaches the time above.
                                chronometer.stop();
                                Toast.makeText(getBaseContext(), "El tiempo ha finalizado",
                                        Toast.LENGTH_LONG).show();
                                estado = "inactivo";
                                btnEmpezar.setText("Iniciar");
                                Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                vibrator.vibrate(50);
                            }

                        }
                    });
                    ///////////////////////////
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
                cronometro.stop();
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

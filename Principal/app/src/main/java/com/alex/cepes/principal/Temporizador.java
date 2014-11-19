package com.alex.cepes.principal;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Temporizador extends Activity {

    TextView texto;
    Bundle bundle;
    int minutos;
    int segundos;
    int tiempo1;
    long sec, min;
    Button btnEmpezar;
    Button btnDetener;
    String estado = "inactivo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temporizador);

        btnEmpezar = (Button)findViewById(R.id.btnEmpezar);
        btnDetener = (Button)findViewById(R.id.btnDetener);

        bundle = getIntent().getExtras();
        minutos = bundle.getInt("minutos");
        segundos = bundle.getInt("segundos");
        tiempo1 = ((minutos*60)+segundos)*1000;
        TextView tiempo = (TextView) findViewById(R.id.tiempo);
        tiempo.setText(minutos+":"+segundos);

        final CountDownTimer cont1 = new CountDownTimer(tiempo1, 1000) {
            TextView tiempo = (TextView) findViewById(R.id.tiempo);

            public void onTick(long millisUntilFinished) {

                if(estado == "inactivo"){
                    min = (millisUntilFinished / 1000) / 60;
                    sec = (millisUntilFinished / 1000) % 60;
                }


                tiempo.setText("Tiempo restante: " + min + ":" + sec);
            }

            public void onFinish() {
                TextView tiempo = (TextView) findViewById(R.id.tiempo);
                tiempo.setText("done!");
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
            }
            public void onStop(){

            }
        };


        btnEmpezar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (estado == "inactivo") {
                    cont1.start();
                btnEmpezar.setText("Pausar");
                    estado = "activo";
                    return;
                }
                    if(estado == "activo"){
                        cont1.cancel();
                        estado = "pausado";
                        btnEmpezar.setText("Reanudar");
                        return;
                    }else{
                        cont1.start();
                        estado = "activo";
                    }

            }
        });
        btnDetener.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnEmpezar.setText("Iniciar");
                estado = "inactivo";
                cont1.cancel();

            }
        });





    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_temporizador, menu);
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

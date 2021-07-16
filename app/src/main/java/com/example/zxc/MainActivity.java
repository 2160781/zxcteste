package com.example.zxc;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.Normalizer;
import java.util.ArrayList;

//import android.support.v7.app.AppCompatActivity;


public class MainActivity<respuestas> extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 2;
    private static final int RECONOCEDOR_VOZ = 7;
    private ArrayList <Respuestas> respuestas;
    private TextToSpeech leer;
    private Object TextView;
    ListView listViewInicial;

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        inicializar();
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult( requestCode, resultCode, data );

        if (resultCode == RESULT_OK && requestCode == RECONOCEDOR_VOZ) {
            ArrayList <String> reconocido =
                    data.getStringArrayListExtra( RecognizerIntent.EXTRA_RESULTS );
            String escuchado = reconocido.get( 0 );
            prepararRespuesta( escuchado );
        }
    }

    private void prepararRespuesta ( String escuchado ) {
        String normalizar = Normalizer.normalize( escuchado, Normalizer.Form.NFD );
        String sintilde = normalizar.replaceAll( "[^\\p{ASCII}]", "" );

        //    int resultado;
        //  String respuesta = respuest.get(0).getRespuestas();
        for (int i = 0; i < respuestas.size(); i++) {
            int resultado = sintilde.toLowerCase().indexOf( respuestas.get( i ).getCuestion() );
            if (resultado != -1) {
                //         respuesta = respuestas.get(i);
              //  responder( respuestas.get( i ) );
                return;
            }
        }

    }

   /* private void responder ( Respuestas respuesta ) {
        startActivity(listviewInicial());


    }

*/
    private void inicializar () {

        respuestas = proveerDatos();

        leer = new TextToSpeech( this, this );
    }

    private ArrayList <Respuestas> proveerDatos () {
        ArrayList <Respuestas> respuestas = new ArrayList <>();
        respuestas.add( new Respuestas( "tarefas",
                " ", listviewInicial()));

        return respuestas;
    }


    public void hablar ( View v ) {
        Intent hablar = new Intent( RecognizerIntent.ACTION_RECOGNIZE_SPEECH );
        hablar.putExtra( RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX" );
        startActivityForResult( hablar, RECONOCEDOR_VOZ );
    }

    public boolean listviewInicial() {

        setContentView(R.layout.activity_lisviewinicial);

        listViewInicial = findViewById(R.id.listviewinicial);

        String[] values = new String[] {
                "Leitura de Qr Code",
                "Lista planos",
                "Microfone"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,values);

        listViewInicial.setAdapter(adapter);


        listViewInicial.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                position=position;
                if(position == 0){

                }

                if (position == 1) {

                }
                if (position == 2) {

                }
            }
        });

        return true;
    }
    @Override
    public void onInit ( int status ) {

    }
}
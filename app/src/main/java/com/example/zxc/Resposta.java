package com.example.zxc;

import android.content.Intent;

/**
 * Created by josue on 29/05/2017.
 */

public class Resposta {
    private String cuestion;
    private String respuestas;
    private Boolean intent;

    public Resposta(String cuestion, String respuestas, Boolean intent ) {
        this.cuestion = cuestion;
        this.respuestas = respuestas;
        this.intent = intent;

    }


    public String getCuestion () {
        return cuestion;
    }

    public String getRespuestas () {
        return respuestas;
    }

    public Boolean getIntent () {
        return intent;
    }
}

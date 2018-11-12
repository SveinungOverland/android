package com.example.tdat1337.ovelse1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Øvingen er å lage en Android-applikasjon som viser en meny med to valg.
            Valgende skal hhv. være ditt fornavn og etternavn.
            Applikasjonen din skal også skrive ut hva som velges i menyen til loggen.
         */
    }

    public void fornavnHandler(View v) {
        Log.i("Fornavn", "Sveinung");
    }

    public void etternavnHandler(View v) {
        Log.i("Etternavn", "Øverland");
    }
}

package com.example.tdat1337.ovelse6;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.function.Consumer;
import java.util.function.Function;

public class MainActivity extends Activity {

    Client client;
    final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void radioButtonsOnClick(View view) {

        Button connectToServerButton = findViewById(R.id.connectToServerButton);
        Button startServerButton = findViewById(R.id.startServerButton);

        switch (view.getId()) {
            case R.id.clientRadioButton:
                connectToServerButton.setVisibility(View.VISIBLE);
                startServerButton.setVisibility(View.INVISIBLE);
                break;

            case R.id.serverRadioButton:
                connectToServerButton.setVisibility(View.INVISIBLE);
                startServerButton.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void startServerButtonOnClick(View view) {

        TextView statusTextView = findViewById(R.id.statusTextView);
        Button startServerButton = findViewById(R.id.startServerButton);
        RadioGroup serviceRadioGroup = findViewById(R.id.serviceRadioGroup);

        statusTextView.setText("Hello sir/mam, is your server running?");
        startServerButton.setVisibility(View.INVISIBLE);
        serviceRadioGroup.setVisibility(View.INVISIBLE);

        Server server = new Server();
        server.start();

    }

    public void connectToServerButtonOnClick(View view) {

        TextView statusTextView = findViewById(R.id.statusTextView);
        Button connectToServerButton = findViewById(R.id.connectToServerButton);
        RadioGroup serviceRadioGroup = findViewById(R.id.serviceRadioGroup);
        LinearLayout layout = findViewById(R.id.layout);

        statusTextView.setText("Connecting to server... Connected");
        connectToServerButton.setVisibility(View.INVISIBLE);
        serviceRadioGroup.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.VISIBLE);

    }

    public void calculateSumButtonOnClick(View view) {
        EditText numeroUnoEditText = findViewById(R.id.numeroUnoEditText);
        EditText numerusDuoEditText = findViewById(R.id.numerusDuoEditText);

        int nr1 = Integer.parseInt(numeroUnoEditText.getText().toString());
        int nr2 = Integer.parseInt(numerusDuoEditText.getText().toString());

        new Client(callback, nr1, nr1).start();
    }

    Consumer<Integer> callback = (sum) -> {
        TextView sumTextView = findViewById(R.id.sumTextView);
        sumTextView.setVisibility(View.VISIBLE);
        sumTextView.setText("Rett svar er " + sum);
    };
}

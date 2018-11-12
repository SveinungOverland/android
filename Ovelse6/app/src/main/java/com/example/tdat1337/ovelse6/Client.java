package com.example.tdat1337.ovelse6;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread {

    final String TAG = "Client";
    final String IP_ADDRESS = "192.168.0.3";
    final int PORT_NUMBER = 8080;

    Consumer<Integer> callback;

    int nr1;
    int nr2;


    public Client(Consumer<Integer> callback, int nr1, int nr2) {
        this.nr1 = nr1;
        this.nr2 = nr2;
        this.callback = callback;
    }


    @Override
    public void run() {
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {

            socket = new Socket(IP_ADDRESS, PORT_NUMBER);
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            printWriter = new PrintWriter(socket.getOutputStream());

            Log.i(TAG, "Connected to server: " + socket);

            printWriter.println(nr1 + ":" + nr2);

            printWriter.flush();

            long counter = System.currentTimeMillis();

            while (!bufferedReader.ready()) {
                Log.i(TAG, "Time waited " + (System.currentTimeMillis() - counter));
            }
            String response = bufferedReader.readLine();

            Log.i(TAG, "Response from server: " + response);

            callback.accept(Integer.parseInt(response));

        } catch (Exception err) {
            err.printStackTrace();
        } finally {

            try {
                if (printWriter != null) printWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
                if (socket != null) socket.close();
            } catch (Exception err) {
                err.printStackTrace();
            }

        }
    }
}

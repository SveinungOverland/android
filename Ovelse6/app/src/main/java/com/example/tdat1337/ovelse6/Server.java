package com.example.tdat1337.ovelse6;


import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Server extends Thread {

    final String TAG = "Server";
    final int PORT_NUMBER = 8080;

    @Override
    public void run() {
        Socket socket = null;
        ServerSocket serverSocket = null;

        try {

            serverSocket = new ServerSocket(PORT_NUMBER);
            Log.i(TAG, "Server listening on port" + PORT_NUMBER);

            while (true) {
                socket = serverSocket.accept();
                Log.i(TAG, "Accepted new client creating requesthandler");
                RequestHandler client = new RequestHandler(socket);
                client.start();
                Log.i(TAG, "New client: " + socket.toString());
            }

        } catch (Exception err) {
            err.printStackTrace();
        } finally {

            try {
                if (serverSocket != null) serverSocket.close();
                if (socket != null) socket.close();
            } catch (Exception err) {
                err.printStackTrace();
            }

        }
    }
}

class RequestHandler extends Thread {

    final String TAG = "RequestHandler";
    Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        Log.i(TAG, "Running requesthandler thread!");
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        PrintWriter printWriter = null;

        try {

            Log.i(TAG, "Trying to initialze everything");
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            Log.i(TAG, "Initialized InputStreamReader");
            bufferedReader = new BufferedReader(inputStreamReader);
            Log.i(TAG, "Initialized BufferedReader");
            printWriter = new PrintWriter(socket.getOutputStream());
            Log.i(TAG, "Initialized PrintWriter");

            while (bufferedReader.ready()) {

                String inputString = bufferedReader.readLine();
                Log.i(TAG, "Read line " + inputString);

                int sum = Arrays.stream(inputString.split(":")).mapToInt(Integer::parseInt).sum();

                Log.i(TAG, "RequestHandler: Runner " + sum);

                printWriter.println(sum);
                printWriter.flush();
            }

        } catch (Exception err) {
            Log.e(TAG, err.getMessage());
        } finally {

            try {
                if (printWriter != null) printWriter.close();
                if (bufferedReader != null) bufferedReader.close();
                if (inputStreamReader != null) inputStreamReader.close();
            } catch (Exception err) {
                Log.e(TAG, err.getMessage());
            }

        }
    }

}
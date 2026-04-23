package org.academiadecodigo.loopeytunes.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection implements Runnable {

    private final Socket clientSocket;
    private final Server server;
    private BufferedReader in;
    private PrintWriter out;
    private String message = "";
    private final int playerNumber;
    private volatile boolean hasOpponent;
    private final static Object key = new Object();

    public ServerConnection(Socket clientSocket, Server server) {
        this.clientSocket = clientSocket;
        this.server = server;
        this.playerNumber = server.getConnectionCounter();
        this.hasOpponent = playerNumber % 2 == 0;

        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {
        try {
            message = in.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(message != null) {
            server.broadcast(message, this);
        }
    }

    public void respond(String message){
        out.println(message);
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    private void opponentArrival(){
        synchronized (key) {
            key.notifyAll();
        }
    }

    private void waitForOpponent(){
        synchronized (key) {
            while (!hasOpponent) {
                try {
                    key.wait();
                    hasOpponent = true;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void run() {

        waitForOpponent();
        opponentArrival();

        if (playerNumber % 2 != 0) {
            out.println("a");
        } else {
            out.println("b");
        }

        while (!message.equals("/q")) {
            start();
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

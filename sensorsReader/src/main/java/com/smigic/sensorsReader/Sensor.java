package com.smigic.sensorsReader;

import com.smigic.sensorsReader.stream.MessageStream;
import com.smigic.sensorsReader.stream.StreamClient;
import com.smigic.sensorsReader.stream.Subject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Sensor {
    private static ExecutorService executor = Executors.newSingleThreadExecutor();

    private static final long PROCESS_TEMINATION_TIMEOUT = 5 * 60 * 60;
    private static final int BUFFER_SIZE = 1024;

    public Sensor(String command, ServerSocket ss){
        new Thread(() -> {
            do {
                try(Socket sck = ss.accept()) {
                    Process proc = null;
                    String sensorCmd = "";
                    String sensorName = "";
                    try {

                        try {
                            sensorCmd = command.split("--")[0];
                            sensorName = command.split("--")[1];
                        } catch (Exception e) {
                            sensorCmd = command;
                        }
                        proc = Runtime.getRuntime().exec(sensorCmd);
                        InputStream err = proc.getErrorStream();
                        InputStream in = proc.getInputStream();
                        OutputStream out = proc.getOutputStream();
                        Subject<String> sbj = new MessageStream<>();
                        StreamClient<String> streamClient = new StreamClient<>(sbj);
                        if (in != null && streamClient != null) {
                            String finalSensorName = sensorName;
                            executor.execute(() -> new MessageEmitter(BUFFER_SIZE, in, streamClient, finalSensorName, sck).run());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        terminateProcessGracefully(proc);
                    }
                    try {
                        Thread.sleep(getRandomIntegerBetweenRange(200, 1000));
                    } catch (InterruptedException e) {
                        // Write in error log file
                        //e.printStackTrace();
                    }
                    //System.out.println("Thread for: " + sensorName);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            while(true);
        }).start();
    }

    private static void terminateProcessGracefully(Process proc) {
        if (proc != null) {
            try {
                proc.destroy();
                proc.waitFor();
            } catch (InterruptedException e) {
                // TODO Add logger, for now only native logging
                e.printStackTrace();
            }
        }
    }

    private static int getRandomIntegerBetweenRange(int min, int max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return (int)x;
    }
}

package com.company;

public class Deadlock {
    private Object process = new Object();
    private boolean stopped = true;

    public void run() {
        synchronized (process) {
            isStopped();
        }
    }

    private synchronized boolean isStopped() {
        return stopped;
    }

    public void stopProcess() {
        synchronized (process) {
            System.out.println("Stopping...");
        }
    }

    public static void main(String[] args) {
        Deadlock deadlock = new Deadlock();

        Thread threadA = new Thread(() -> {
            deadlock.run();
            System.out.println("Running...");
        });
        Thread threadB = new Thread(() -> {
            deadlock.stopProcess();
            System.out.println("Stopped...");
        });
        threadA.start();
        threadB.start();
    }
}


package app.util;

import app.Fila;
import app.IDados;
import app.Processo;

import java.util.Random;

import app.Escalonador;

public class executaThread extends Thread {
    Fila fila;
    Escalonador escalonador;
    String indentificador;

    public executaThread(Escalonador escalonador, String indentificador){
        this.escalonador = escalonador;
        this.indentificador = indentificador;
    }

    public void run() {
        // while(!Thread.currentThread().isInterrupted()){
        this.fila = escalonador.choiceQueueToExecute();
            
        Random random = new Random();
        System.out.println("Iniciando processamento... ("+this.indentificador+")");
        while (fila.primeiro.proximo != null && !escalonador.getEnd()) {
            if(!escalonador.getStop()){
                int timeProcess = random.nextInt((60000 - 0) + 1) + 0;
                int priorityToSub = 0;
                try {
                    priorityToSub = escalonador.getPriorityToSubtract(timeProcess);
                    // System.out.println("Executando");
                    // System.out.println(thread);
                    if(priorityToSub == -1){
                        escalonador.killProcess(fila);
                    } else if(priorityToSub == 0){
                        Thread.sleep(timeProcess);
                        escalonador.endProcess(fila);
                    } else {
                        IDados dado = fila.retirar();
                        if(dado != null){
                            escalonador.changePriority((Processo)dado, priorityToSub, false);
                        }
                    }
                } catch (InterruptedException ex) {
                    System.out.println("Thread acordada!");
                }
            }
        }
    }
}
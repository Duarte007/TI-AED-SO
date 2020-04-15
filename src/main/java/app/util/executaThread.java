package app.util;

import app.Fila;
import app.Escalonador;

public class executaThread implements Runnable {
    Fila[] filas;
    Thread thread;
    Escalonador escalonador;
    String indentificador;

    public executaThread(Fila[] filas, Thread thread, Escalonador escalonador, String indentificador){
        this.filas = filas;
        this.thread = thread;
        this.escalonador = escalonador;
        this.indentificador = indentificador;
    }

    public void run() {
        try{
            //isInterrupted nao funcionaaaaa
            while(thread != null && !thread.isInterrupted()){
                for (Fila fila : filas) {
                    escalonador.executaProcesso(fila, indentificador);
                }
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
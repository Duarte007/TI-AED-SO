package app.util;

import app.Fila;
import app.Escalonador;

public class executaThread extends Thread {
    Fila[] filas;
    Escalonador escalonador;
    String indentificador;

    public executaThread(Fila[] filas, Escalonador escalonador, String indentificador){
        this.filas = filas;
        this.escalonador = escalonador;
        this.indentificador = indentificador;
    }

    public void run() {
        // while(!Thread.currentThread().isInterrupted()){
        for (Fila fila : this.filas) {
            this.escalonador.executaProcesso(fila, this.indentificador);
        }
    }
}
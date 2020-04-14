package app;
import java.util.Random;

public class Escalonador{
    Fila[] processos;
    PoliticaPrioridade politica;

    public Escalonador(Fila[] processos){
        //@TODO pegar dados do arquivo usando o Scanner que o Jhonny criar
        //@TODO colocar os processos na fila de acordo com a prioridade
        this.processos = processos;
        this.politica = new PoliticaPrioridade();
    }

    //@TODO rotina para alterar a prioridade do processo caso necessario
    public void changePriority(Processo process, int priorityToSub, Boolean add){
        if(!add)
            this.processos[process.getPriority() - 1 + priorityToSub].adicionar(process);
        else
            this.processos[priorityToSub - 1].adicionar(process);
    }
    
    //@TODO rotina para matar processo caso necessario
    public void killProcess(Fila fila){
        fila.retirar();
    }

    //@TODO rotina para finalizar processo normalmente apos tempo de execução
    public void endProcess(Fila fila){
        fila.retirar();
    }

    public void executaProcesso(Fila fila) {
        Random random = new Random();

        while (fila.primeiro.proximo != null) {
            int timeProcess = random.nextInt((250000 - 0) + 1) + 0;
            int priorityToSub = 0;
            try {
                priorityToSub = this.politica.getPriorityToSubtract(fila.primeiro);
                if(priorityToSub == -1){
                    this.killProcess(fila);
                } else if(priorityToSub == 0){
                    Thread.sleep(timeProcess);
                    this.endProcess(fila);
                } else {
                    IDados dado = fila.retirar();
                    if(dado != null){
                        this.changePriority((Processo)dado, priorityToSub, false);
                    }
                }
            } catch (InterruptedException ex) {
                System.out.println("Thread acordada!");
            }
        }
    }
}
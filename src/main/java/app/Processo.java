package app;

import java.time.LocalTime;

public class Processo implements IDados {
    
    private int pid; 
    private String name; 
    private int priority; 
    private int qttyExecutionCycles; 
    private LocalTime time; 


    public Processo(int pid, String name, int priority, int qttyExecutionCycles) throws IllegalArgumentException{
        if(pid < 0)
            throw new IllegalArgumentException("PID inválido");
        if(priority < 1 || priority > 20)
            throw new IllegalArgumentException("Prioridade inválida");
        if(qttyExecutionCycles < 0)
            throw new IllegalArgumentException("Quantidade de ciclos de execução inválido");
        this.pid = pid;
        this.name = name;
        this.priority = priority;
        this.qttyExecutionCycles = qttyExecutionCycles;
        this.time = LocalTime.now();
    }

    public String getName(){
        return this.name;
    }

    public int getPriority(){
        return this.priority;
    }

    public void setPriority(int priority){
        this.priority = priority;
    }

    public int getQttyExecutionCycles(){
        return this.qttyExecutionCycles;
    }

    public int getExecutionTime(){
        return LocalTime.now().minusNanos(this.time.getNano()).getNano();
    }
     
    @Override
    public int getID() {
        return this.pid;
    }

}
package app;

public class Escalonador extends PoliticaPrioridade{
    Fila[] processos;
    Boolean stop = false, end = false;

    public Escalonador(Fila[] processos){
        //@TODO pegar dados do arquivo usando o Scanner que o Jhonny criar
        //@TODO colocar os processos na fila de acordo com a prioridade
        this.processos = processos;
        this.stop = false;
    }

    public Boolean getEnd(){
        return this.end;
    }

    public Boolean getStop(){
        return this.stop;
    }

    //@TODO rotina para alterar a prioridade do processo caso necessario
    public void changePriority(Processo process, int priorityToSub, Boolean add){
        if(!add)
            this.processos[process.getPriority() - 1 + priorityToSub].adicionar(process);
        else
            this.processos[priorityToSub - 1].adicionar(process);

            System.out.println("Alterando prioridade do processo: "+process.getID());
    }
    
    //@TODO rotina para matar processo caso necessario
    public void killProcess(Fila fila){
        IDados process = fila.retirar();
        System.out.println("Processo finalizado por kill: "+process.getID());
    }

    //@TODO rotina para finalizar processo normalmente apos tempo de execução
    public void endProcess(Fila fila){
        IDados process = fila.retirar();
        System.out.println("Processo concluido: "+process.getID());
    }

    public Fila choiceQueueToExecute(){
        for (Fila fila : this.processos) {
            if(fila.primeiro.proximo != null){
                return fila;
            }
        }
        return new Fila();
    }

    public void toogleStop(){
        this.stop = !this.stop;
        if(this.stop){
            System.out.println("Processamento pausado!");
        } else {
            System.out.println("Processamento retomado!");
        }
    }

    public void end(){
        this.end = true;
    }
}
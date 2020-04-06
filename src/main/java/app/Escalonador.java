package app;

public class Escalonador{
    Fila processos;
    PoliticaPrioridade politica;

    public Escalonador(){
        //@TODO pegar dados do arquivo usando o Scanner que o Jhonny criar
        //@TODO colocar os processos na fila de acordo com a prioridade
        this.processos = new Fila();
        this.politica = new PoliticaPrioridade();
    }

    //@TODO rotina para alterar a prioridade do processo caso necessario
    public void changePriority(){}
    
    //@TODO rotina para matar processo caso necessario
    public void killProcess(){}

    //@TODO rotina para finalizar processo normalmente apos tempo de execução
    public void endProcess(){}
}
package app;

public class PoliticaPrioridade{
    private final double LIMIT_TIME = Math.pow(10, 9);

    public int getPriorityToSubtract(Processo processo){
        if (processo.getExecutionTime() - this.LIMIT_TIME < 0) return 0;
        // 1 segundo
        if (processo.getExecutionTime() - this.LIMIT_TIME > this.LIMIT_TIME) return 1;
        // 10 segundos
        if (processo.getExecutionTime() - this.LIMIT_TIME > Math.pow(10, 10)) return 2;
        // 100 segundos
        if (processo.getExecutionTime() - this.LIMIT_TIME > Math.pow(10, 11)) return 3;
        // se passar de 100 segundos nos removemos o processo da lista
        return -1;
    }

}
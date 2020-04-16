package app;

public class PoliticaPrioridade{
    private final double LIMIT_TIME = 30000;

    public int getPriorityToSubtract(double timeProcess){
        if (timeProcess - this.LIMIT_TIME < 0) return 0;
        // 1 segundo
        if (timeProcess - this.LIMIT_TIME > this.LIMIT_TIME) return 1;
        // 10 segundos
        if (timeProcess - this.LIMIT_TIME > 10000) return 2;
        // 100 segundos
        if (timeProcess - this.LIMIT_TIME > 20000) return 3;

        if (timeProcess - this.LIMIT_TIME > 30000) return 4;
        // se passar de 100 segundos nos removemos o processo da lista
        return -1;
    }

}
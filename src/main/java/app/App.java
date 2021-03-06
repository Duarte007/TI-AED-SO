package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.util.executaThread;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class App {

    public static Fila[] filasPrioridades = new Fila[20];
    public static Escalonador escalonador = new Escalonador(filasPrioridades);
    public static List<Processo> processos = new ArrayList<Processo>();
    public static int i = 1;
    static Thread t;
    static Thread t2;

    public static void addQueue(Processo processo) {
        if (filasPrioridades[processo.getPriority() - 1] != null)
            filasPrioridades[processo.getPriority() - 1].adicionar(processo);
        else{
            filasPrioridades[processo.getPriority() - 1] = new Fila();
            filasPrioridades[processo.getPriority() - 1].adicionar(processo);
        }
    }

    public static void createProcess() {
        String currentDirectory = System.getProperty("user.dir");

        try {
            FileReader file = new FileReader(currentDirectory + "\\app\\processos.txt");

            BufferedReader readerFile = new BufferedReader(file);
            String row = "";
            String[] data;
            try {
                row = readerFile.readLine();
                while (row != null) {
                    if(row != null){
                        data = row.split(";");
                        Processo currentProcess = new Processo(
                            Integer.parseInt(data[0]),
                            data[1],
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3])
                        );
                        processos.add(currentProcess);
                        addQueue(currentProcess);
                        row = readerFile.readLine();
                    }
                }
                file.close();
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possivel ler o arquivo.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado.");
        }

    }

    public static void changePriorities(Integer pid, Integer priority){
        if(priority >= 1 && priority <= 20){
            Processo filteredProcesso = null;
            for (Processo processo : processos) {
                if(processo.getID() == pid){
                    filteredProcesso = processo;
                    break;
                }
            }
            if(filteredProcesso != null){
                escalonador.changePriority(filteredProcesso, priority, true);
                System.out.println("Prioridade alterada com sucesso!");
            } else
                System.out.println("Processo nao encontrado!");
        }
    }

    public static void executaThreads() {
        t.start();
        // t2.start();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String lerNum = "0";
        t = new Thread(new executaThread(escalonador, "Thread 1"));
        // t2 = new Thread(new executaThread(filasPrioridades, escalonador, "Thread 2"));
        createProcess();

        while (Integer.parseInt(lerNum) != 4) {
            System.out.println("========================================");
            System.out.println("1 - Modificar prioridade do processo.");
            System.out.println("2 - Suspender/Retomar processo.");
            System.out.println("3 - Iniciar novo ciclo.");
            System.out.println("4 - Sair\n");
            System.out.print("Escolha sua opção: ");
            lerNum = sc.nextLine();
            if(lerNum.length() > 0)
                switch (lerNum) {
                    case "1":
                        System.out.println("Informe o Id do processo (PID): ");
                        Integer pid = sc.nextInt();
                        System.out.println("Informe o novo número de prioridade (1 a 20): ");
                        Integer priority = sc.nextInt();
                        changePriorities(pid, priority);
                        
                        break;

                    case "2":
                        escalonador.toogleStop();
                        break;
                    case "3":
                        executaThreads();
                        break;

                    case "4":
                        escalonador.end();
                        if(t != null){
                            t.interrupt();
                        }
                        if(t2 != null){
                            t2.interrupt();
                        }
                        System.out.println("Finalizando...");
                        break;

                    default:
                        System.out.println("Opção inválida.");
                        break;
                }

        }

        sc.close();

    }
}

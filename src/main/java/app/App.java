package app;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class App {

    public static Fila[] filasPrioridades = new Fila[20];
    public static Escalonador escalonador = new Escalonador(filasPrioridades);
    public static ArrayList<Processo> process;

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
                    row = readerFile.readLine();
                    if(row != null){
                        data = row.split(";");
                        Processo currentProcess = new Processo(
                        Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
                        Integer.parseInt(data[3]));
                        process.add(currentProcess);
                        addQueue(currentProcess);
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

    public static void choiceQueueToExecute(){
        for (Fila fila : filasPrioridades) {
            executaThreads(fila);
        }
    }

    public static void changePriorities(Integer pid, Integer priority){
        if(priority >= 1 && priority <= 20){

            Processo filteredProcesso = process.stream()
            .filter(processos -> process.getID() == pid)
            .findAny()
            .orElse(null);
            escalonador.changePriority(filteredProcesso, priority);
        }
        

    }

    // public static void executaProcesso(Fila fila) {

    //     while (fila.primeiro.proximo != null) {

    //         int numAleatorio = aleatoriar(0, 250000);
    //         try {
    //             Thread.sleep(numAleatorio);
    //             fila.retirar();
    //         } catch (InterruptedException ex) {
    //             System.out.println("Thread acordada!");
    //         }
    //     }
    // }

    public static void executaThreads(Fila fila) {
        executaThread1(fila);
        executaThread2(fila);
    }

    public static void executaThread1(Fila fila) {
        escalonador.executaProcesso(fila);
    }

    public static void executaThread2(Fila fila) {
        escalonador.executaProcesso(fila);
    }

    public static int aleatoriar(int minimo, int maximo) {
        Random random = new Random();
        return random.nextInt((maximo - minimo) + 1) + minimo;
        // metodo para gerar número aleatorio
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(); // Criação da primeira thread
        Thread t2 = new Thread(); // Criação da segunda thread

        t1.start();// Rodando a thread 1
        t2.start();// Rodando a thread 2

        System.out.println(aleatoriar(0, 250000)); // pega um número aleatorio no intervalo de 0 a 250 segundos
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("========================================");
            System.out.println("1 - Modificar prioridade do processo.");
            System.out.println("2 - Suspender ou retomar processo.");
            System.out.println("3 - Iniciar novo ciclo.");
            System.out.println("4 - Sair\n");
            System.out.print("Escolha sua opção: ");
            String lerNum = sc.nextLine();
            opcao = Integer.parseInt(lerNum);

            switch (opcao) {
                case 1:
                    System.out.println("Informe o Id do processo (PID): ");
                    Integer pid = sc.nextInt();
                    System.out.println("Informe o novo número de prioridade (1 a 20): ");
                    Integer priority = sc.nextInt();
                    changePriorities(pid, priority);
                    
                    break;

                case 2:
                    System.out.println("1 - Suspender processo.");
                    System.out.println("2 - Retomar processo.\n");
                    System.out.print("Escolha sua opção: ");
                    String lerNumCase2 = sc.nextLine();
                    int opcaoCase2 = Integer.parseInt(lerNumCase2);

                    if (opcaoCase2 == 1) {
                        
                    }

                case 3:
                    choiceQueueToExecute();
                    break;

                case 4:
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

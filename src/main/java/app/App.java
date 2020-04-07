package app;

import java.util.Scanner;

public class App {
    public static void main (String [] args) {

         Thread t1 =  new Thread(); //Criação da primeira thread
         Thread t2 = new Thread(); //Criação da segunda thread 

         t1.start();//Rodando a thread 1
         t2.start();//Rodando a thread 2

        Scanner sc = new Scanner (System.in);
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("========================================");
            System.out.println("1 - Modificar prioridade de acesso.");
            System.out.println("2 - Suspender ou retomar processo.");
            System.out.println("3 - Iniciar novo ciclo.");
            System.out.println("4 - Sair\n");
            System.out.print("Escolha sua opção: ");
            String lerNum = sc.nextLine();
            opcao = Integer.parseInt(lerNum);

            switch (opcao) {
                case 1:
                    
                break;
                    
                case 2: 
                System.out.println("1 - Suspender processo.");
                System.out.println("2 - Retomar processo.\n");
                System.out.print("Escolha sua opção: ");
                String lerNumCase2 = sc.nextLine();
                int opcaoCase2 = Integer.parseInt(lerNumCase2);

                case 3:
                    //todo
                break;

                case 4: 
                    System.out.println("Finalizando..."); 
                break;

                default:
                    System.out.println("Opção inválida."); 
                break;

            }

        }        

       







    }
}

package app;

import java.util.Scanner;

public class App
{
    public static void main (String [] args)
    {
        Scanner sc = new Scanner (System.in);
        int opcao = 0;

        while (opcao != 4)
        {
            System.out.println("========================================");
            System.out.println("1 - Modificar prioridade de acesso.");
            System.out.println("2 - Suspender ou retomar processo.");
            System.out.println("3 - Iniciar novo ciclo.");
            System.out.println("4 - Sair");
            System.out.println(" ");
            System.out.print("Escolha sua opção: ");
            String lerNum = sc.nextLine();
            opcao = Integer.parseInt(lerNum);

            if (opcao == 1)
            {
                
            }

            if (opcao == 2)
            {
                
            }

            if (opcao == 3)
            {
                
            }

        }        

       







    }
}

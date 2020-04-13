package app.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;;

public final class Reader {

    private Reader(){
        
    }

    public static String lerArquivo(String caminho) {

        String conteudo = "";

        try {
            FileReader arquivo = new FileReader(caminho);

            BufferedReader lerArq = new BufferedReader(arquivo);
            String linha = "";
            try {
                linha = lerArq.readLine();

                while (linha != null) {
                    conteudo += linha+"\n";
                    linha = lerArq.readLine();
                }
                arquivo.close();
            } catch (IOException ex) {
                System.out.println("Erro: Não foi possivel ler o arquivo.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Erro: Arquivo não encontrado.");
        }

        if (conteudo.contains("Erro")) {
            return "";
        } else {
            return conteudo;
        }
    }

}

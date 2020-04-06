package app;

public class Elemento{
    public IDados dado;
    public Elemento proximo;

    public Elemento(IDados dado){
        this.dado = dado;
        this.proximo=null;
    }
}
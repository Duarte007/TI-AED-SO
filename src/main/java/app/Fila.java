package app;
class Fila{

    public Elemento primeiro, ultimo;

    public Fila(){
        this.primeiro = new Elemento(null);
        this.ultimo = this.primeiro;
    }

    public void adicionar(IDados novo) {
        Elemento novoEl = new Elemento(novo);
        this.ultimo.proximo = novoEl;
        this.ultimo = novoEl;
    }

    public IDados retirar(){
        if(this.primeiro == this.ultimo) return null;

        Elemento retirada =  this.primeiro.proximo;
        this.primeiro.proximo = retirada.proximo;
        if(retirada == this.ultimo)
            this.ultimo = this.primeiro;

        return retirada.dado;

    }

    @Override
    public String toString(){
        StringBuilder resposta = new StringBuilder();
        Elemento aux = this.primeiro.proximo;
        while(aux!=null){
            resposta.append(aux.dado);
            resposta.append("\n");
            aux = aux.proximo;
        }
        return resposta.toString();
    }


}
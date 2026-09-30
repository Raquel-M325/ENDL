package ArvoreB;

public class arvoreB implements arvoreBInterface{
    private No root;

    public arvoreB(){
        this.root = null;
    }

    public No find(Object o) throws Correcao{
        if (isEmpty()){
            throw new Correcao("A árvore está vazia");
        }

        return 
    }

    public void insert(Object o){

    }

    public void remove(Object o) throws Correcao{
        if (isEmpty()){
            throw new Correcao("A árvore está vazia");
        }
    }

    public String mostrar(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("A árvore está vazia");
        }
    }

    public boolean isEmpty(){
        return root == null;
    }

    //para insercao
    protected void fusao(){

    }

    //para remocao
    protected void divisao(){

    }
    
}

public class arvore implements arvoreAVL {
    private int size;
    private No root;

    public arvore(){
        this.size = 0;
        this.root = null;
    } 

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public boolean isAVL() throws Correcao{
        if (isEmpty()){
            return true;
        }

        if
        
    }

    public boolean isRoot(){
        if (isEmpty()){
            return false;
        }

        return true;
    }

    public No getRoot(){
        return root;
    }


    public void insert(No node, Object o){
        node.setElement(o, chave);

        //cria um novo root 
        if (isEmpty()){
            root = node;
            size++;
            return;
        }

        
    }
    

    public No remove() throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }


    }

    public No find() throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

        
    }

    
    public int balancing(No node){

    }

    public int height(No node) throws Correcao{
        if (isRoot() == false) {
            throw new Correcao("Está vazia");
        }



    }

    public No rotationEsq(No node){

    }

    public No rotationDir(No node){

    }
}
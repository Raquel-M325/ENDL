public class arvore implements arvoreAVL {
    private int size, chave;
    private No root, filhoDir, filhoEsq;

    public arvore(){
        this.size = 0;
        this.chave = 0;
        this.root = null;
        this.filhoDir = null;
        this.filhoEsq = null;
    } 

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public boolean isAVL(){
        //TEM QUE VER SE CADA NÓ ESTA COM BALANCA ENTRE 1 E -1 APENAS

        
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

    public boolean isfilhoDir(){

    }

    public boolean isfilhoEsq(){

    }

    public No getfilhoDir(){

    }

    public No getfilhoEsq(){

    }

    public void insert(No node){

    }

    public No remove(){

    }

    public No find(){

    }

    
    public int balancing(No node){

    }

    public int height(No node){

    }

    public No rotationEsq(No node){

    }

    public No rotationDir(No node){

    }
}
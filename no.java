public class No{
    private Object elemento; 
    private No root, filhoDir, filhoEsq;
    private int chave;

    public No(Object elemento, int chave){
        this.elemento = elemento;
        this.chave = chave;
        this.root = null;
        this.filhoDir = null;
        this.filhoEsq = null;
    }

    public void setElement(Object o){
        this.elemento = o;
    }

    public Object getElement(){
        return elemento;
    }

    public void setRoot(No node){
        this.root = node;
    }

    public No getRoot(){
        return root;
    }

    public void setChave(int o){
        this.chave = o;
    }

    public int getChave(){
        return chave;
    }

    public void setfilhoDir(No node){
        this.filhoDir = node;

    }

    public No getfilhoDir(){
        return filhoDir;
    }

    public void setfilhoEsq(No node){
        this.filhoEsq = node;

    }
    
    public No getfilhoEsq(){
        return filhoEsq;
    }


}
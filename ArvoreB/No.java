package ArvoreB;

public class No{
    private Object[] chaves;
    private No[] filhos;
    private int t;
    private No pai;
    private int maximoT, minimoT, maximoFilho;

    public No(int t){
        this.t = t;
        this.chaves = new Object[2 * t - 1];
        this.filhos = new No[2 * t];
        this.pai = null;
        this.maximoT = 2 * t - 1;
        this.minimoT = t - 1;
        this.maximoFilho = 2 * t;
    }

    public int getMaximoT(){ 
        return maximoT; 
    }

    public int getMinimoT(){ 
        return minimoT; 
    }

    public int getMaximoFilho(){ 
        return maximoFilho;
     }

    public int getT(){
        return t; 
    }

    public void setPai(No node){
        this.pai = node; 
    }

    public No getPai(){
        return pai; 
    }

    public void setChaves(Object[] chaves){ 
        this.chaves = chaves;
     }

    public Object[] getChaves(){
        return chaves; 
    }

    public void setFilhos(No[] filhos){ 
        this.filhos = filhos; 
    }

    public No[] getFilhos(){ 
        return filhos; 

    }
}
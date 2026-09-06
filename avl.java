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

        No atual = root;

        while (true){
            
            //enquanto for maior que a raiz
            if (atual.getChave() < node.getChave()){

                if (atual.getfilhoDir() == null){
                    atual.setfilhoDir(node);
                    break;
                }              

                //se não for vazio, continuará no loop
                atual = atual.getfilhoDir();
            } 
            
            //se for menor que a raiz
            else {

                if (atual.getfilhoEsq() == null){
                    atual.setfilhoEsq(node);
                    break;
                }

                //se não for vazio, continuará no loop
                atual = atual.getfilhoEsq();
            }
        }

        size++;
    }
    

    public No remove(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

        No atual = root;
        No removido = null;

        //se for somente raiz existente sem filhos
        if (atual.getfilhoDir() == null && atual.getfilhoEsq() == null){
            removido = root;    
            root = null;
            size--;
            return removido;
        }
        
        //enquanto forem diferentes, procure!
        while (atual.getChave() != node.getChave()){
        
            //se a raiz for menor
            if (atual.getChave() < node.getChave()){
                
                if (atual.getfilhoDir() == null){
                    throw new Correcao("Nó não encontrado");
                    break;

                }

                

            //se a raiz for maior   
            } else{
                if (atual.getfilhoEsq() == null){
                    throw new Correcao("Nó não encontrado");
                    break;

                }
            }

        }

        size--;
        return removido;

    }

    public No find(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

        No atual = root;

        while (atual.getChave() != node.getChave()){
            
            //se for a raiz for menor
            if (atual.getChave() < node.getChave()){

                //se for nulo
                if (atual.getfilhoDir() == null){
                    throw new Correcao("Nó não encontrado");
                    break;
                }

                //continue andando para direita até achar igual
                atual = atual.getfilhoDir();

            } else {

                //se for nulo
                if (atual.getfilhoEsq() == null){
                    throw new Correcao("Nó não encontrado");
                    break;
                }

                //continue andando para esquerda até achar igual
                atual = atual.getfilhoEsq();
            }
        }

        return atual;
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
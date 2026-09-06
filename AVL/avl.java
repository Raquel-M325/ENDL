package AVL;

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

    public boolean isAVL(){
        if (isEmpty()){
            return true;
        }

        //onde ele começa a verificar a partir da raiz se está fora do balanço
        if (balancing(root) < -1 || balancing(root) > 1){
            return false;
        }
        
        return true;
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
        No pai = null;

        //se for somente raiz existente sem filhos
        if (atual.getfilhoDir() == null && atual.getfilhoEsq() == null){
            removido = root;    
            root = null;
            size--;
            return removido;
        }
        
        //se for querer tirar root, mesmo com os filhos
        if (atual.getChave() == node.getChave()){

            while (true){
                pai = atual;
                atual = atual.getfilhoDir(); //anda

                //se já achou
                if (atual.getfilhoEsq() == null){
                    break;

                } else {
                    pai = atual;
                    atual = atual.getfilhoEsq(); //continua por outro caminho salvando
                }
            }

            //sempre o direito fica no lugar
            if (pai.getfilhoDir() == atual){
                pai.setfilhoDir(atual.getfilhoDir());
                root.setElement(atual.getElement()); //não quero ter trabalho de trocar nó
                root.setChave(atual.getChave()); //então só basta trocar o elemento e chave
            } 

            if (pai.getfilhoEsq() == atual){
                pai.setfilhoEsq(atual.getfilhoDir());
                root.setElement(atual.getElement());
                root.setChave(atual.getChave());
            }

            size--;
            return root;
        }

        //enquanto forem diferentes, procure!
        while (atual.getChave() != node.getChave()){
        
            //se a raiz for menor
            if (atual.getChave() < node.getChave()){
                
                if (atual.getfilhoDir() == null){
                    throw new Correcao("Nó não encontrado");
                    break;

                }

                //precisa dizer quem será pai e filho antes de retirar
                pai = atual;
                atual = atual.getfilhoDir(); 

            //se a raiz for maior   
            } else{
                if (atual.getfilhoEsq() == null){
                    throw new Correcao("Nó não encontrado");
                    break;
                }

                pai = atual; //grava quem era o pai
                atual = atual.getfilhoEsq(); //aqui ele substitui do atual para filho

            }

        }

        removido = atual; 
        
        //preciso remover, mas preciso saber quem do atual peguei para desligar
        if (pai.getfilhoDir() == atual){
            pai.setfilhoDir(null);
            removido = atual;
        }

        if (pai.getfilhoEsq() == atual){
            pai.setfilhoEsq(null);
            removido = atual;
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
        if (isEmpty()){
            return 0;
        }

        
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
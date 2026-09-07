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
        
        return isAVL(root);
    }

    private boolean isAVL(No atual){
        if (atual == null){
            return true;
        }

        //onde ele começa a verificar se está fora do balanço em toda arvore, senao retorna true se está ok
        if (balancing(atual) < -1 || balancing(atual) > 1){
            return false;
        }

        return isAVL(atual.getfilhoEsq()) && isAVL(atual.getfilhoDir());

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
        node.setElement(o);

        root = verificarNoInsert(root, node); //faz a insercao de forma recursiva para ficar log
        size++;
    }

    private No verificarNoInsert(No atual, No node){
        //precisa chegar ate filho que seja null, para depois quando voltar, comeca a verificar em cada No
        //precisa chamar o balanceamento, verificacao de sinais e rotacoes

        if (atual == null){
            return node; //se achar qualquer um vazio, já coloca
        }

        //lembre-se que é diferente do while, é recursivo!
        if (atual.getChave() > node.getChave()){
            atual.setfilhoEsq(verificarNoInsert(atual.getfilhoEsq()), node); //além de colocar um novo no, irá andar recursivamente para o proximo

        } else {
            atual.setfilhoDir(verificarNoInsert(atual.getfilhoDir()), node); //segue ainda a logica do direito para maior, esquerda o menor que a raiz
        }

        atualizaAltura(atual);

        int balanca = balancing(atual); //irá definir quem está com maior peso da balanca que é o "pai"

        //caso do LR
        //tem muito filho esquerdo
        if (balanca > 1){
            int balancaFilhoEsq = balancing(atual.getfilhoEsq());

            //tem muito filho direito
            if (balancaFilhoEsq < 0){
                atual.setfilhoEsq(rotationEsq(atual.getfilhoEsq()));
            }

            return rotationDir(atual);
        } 

        //caso do RL
        //tem muito filho direito
        if (balanca < -1){
            int balancaFilhoDir = balancing(atual.getfilhoDir());

            //tem muito filho esquerdo
            if (balancaFilhoDir > 0){
                atual.setfilhoDir(rotationDir(atual.getfilhoDir()));
            }

            return rotationEsq(atual);

        }

        return atual;
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

        root = verificarNo(root, node); 
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

    
    public int balancing(){
        if (isEmpty()){
            return 0;
        }

        return balancing(root);
        
    }

    private int balancing(No atual){
        
        //esquerda - direita, ficará subindo contando das alturas e faz a subtração
        return altura(atual.getfilhoEsq()) - altura(atual.getfilhoDir());
    }

    //pega a partir da raiz
    public int height() throws Correcao{
        if (isRoot() == false) {
            throw new Correcao("Está vazia");
        }

        return root.getAltura();
    }

    //ve o atual da altura
    private int altura(No atual){
        if (atual == null){ //não tiver raiz
            return -1;
        }

        return atual.getAltura();
    }

    //calcula a altura 
    private void atualizaAltura(No atual){
        atual.setAltura(1 + Math.max((
            altura(atual.getfilhoEsq())), 
            altura(atual.getfilhoDir()
        )));
    }


    public No rotationEsq(No node){
        No filho = node.getfilhoDir();
        node.setfilhoDir(filho.getfilhoEsq()); //lembrando que há irmao
        filho.setfilhoEsq(node); 
        
        //olhar sempre a altura e atualizar 
        atualizaAltura(node);
        atualizaAltura(filho); 

        return filho;
    }

    public No rotationDir(No node){
        No filho = node.getfilhoEsq();
        node.setfilhoEsq(filho.getfilhoDir()); //trocar de lugar
        filho.setfilhoDir(node);

        atualizaAltura(node); 
        atualizaAltura(filho); 

        return filho; 
    }

    public String mostrar(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Árvore vazia");
        }

        if (node == null){
            return ""; 
        }

        return mostrar(node.getfilhoEsq()) + node.getChave() + mostrar(node.getfilhoDir()); 
    }
}
package AVL;

public class arvore implements arvoreABP{ 
    protected int size;
    protected  No root;

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

    public boolean isRoot(){
        if (isEmpty()){
            return false;
        }

        return true;
    }

    public No getRoot(){
        return root;
    }

    //sem balanceamento
    public void insert(No node, Object o){
        node.setElement(o);

        root = verificarNoInsert(root, node); //faz a insercao de forma recursiva para ficar log
        size++;
    }

    protected No verificarNoInsert(No atual, No node){
        //precisa chegar ate filho que seja null, para depois quando voltar, comeca a verificar em cada No
        //precisa chamar o balanceamento, verificacao de sinais e rotacoes

        if (atual == null){
            return node; //se achar qualquer um vazio, já coloca
        }

        //lembre-se que é diferente do while, é recursivo!
        if (atual.getChave() > node.getChave()){
            atual.setfilhoEsq(verificarNoInsert(atual.getfilhoEsq(), node)); //além de colocar um novo no, irá andar recursivamente para o proximo

        } else {
            atual.setfilhoDir(verificarNoInsert(atual.getfilhoDir(), node)); //segue ainda a logica do direito para maior, esquerda o menor que a raiz
        }

        return atual;
    }
    

    public No remove(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

    
        No removido = find(node);
        root = verificarNoRemove(root, node); 
        size--;
        return removido;
    }

    protected No verificarNoRemove(No atual, No node){
        //preciso usar a forma recursiva para acessar o No e retirar para depois voltar

        //se achar que está null, entao fara nada alem de null, pois não foi encontrado
        if (atual == null){
            return null;
        }

        if (atual.getChave() > node.getChave()){
            atual.setfilhoEsq(verificarNoRemove(atual.getfilhoEsq(), node));

        } 
        
        else if (atual.getChave() < node.getChave()){
            atual.setfilhoDir(verificarNoRemove(atual.getfilhoDir(), node));
        } 
        
        //caso forem iguais, achando o que quer eliminar, precisa ver se há algum irmao, para que a recursao faça a ligacao
        else {
            if (atual.getfilhoDir() == null){
                return atual.getfilhoEsq();
            }

            if (atual.getfilhoEsq() == null){
                return atual.getfilhoDir();
            }

            //caso tiverem ambos os irmaos, o menor numero filho direita tera que sair e ficar no lugar da raiz
            if (atual.getfilhoDir() != null && atual.getfilhoEsq() != null){
                No sucessor = menor(atual.getfilhoDir());

                atual.setChave(sucessor.getChave());
                atual.setElement(sucessor.getElement());

                atual.setfilhoDir(verificarNoRemove(atual.getfilhoDir(), sucessor));                
            }

        }

        return atual;
    }

    protected No menor(No atual){
        if (atual.getfilhoEsq() == null){
            return atual; //já sendo direito
        }

        return menor(atual.getfilhoEsq());
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
                }

                //continue andando para direita até achar igual
                atual = atual.getfilhoDir();

            } else {

                //se for nulo
                if (atual.getfilhoEsq() == null){
                    throw new Correcao("Nó não encontrado");
                }

                //continue andando para esquerda até achar igual
                atual = atual.getfilhoEsq();
            }
        }

        return atual;
    }

    //pega a partir da raiz
    public int height() throws Correcao{
        if (isRoot() == false) {
            throw new Correcao("Está vazia");
        }

        return root.getAltura();
    }

    //ve o atual da altura
    protected int altura(No atual){
        if (atual == null){ //não tiver raiz
            return -1;
        }

        return atual.getAltura();
    }

    //calcula a altura 
    protected void atualizaAltura(No atual){
        atual.setAltura(1 + Math.max((
            altura(atual.getfilhoEsq())), 
            altura(atual.getfilhoDir()
        ))); 
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
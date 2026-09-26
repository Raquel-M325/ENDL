package RubroNegro;

import AVL.arvoreAVLInterface;

public class arvoreRubro extends arvore implements arvoreRubroInterface{

    public int alturaNegro(No atual){ //conta os no negro e diz sua altura
        if (atual == null){
            return 0;
        }

        int esquerda = alturaNegro(atual.getfilhoEsq());
        int direita = alturaNegro(atual.getfilhoDir());

        if (esquerda != direita){

        }

        if (atual.getCor().equals("Negro")){
            return esquerda + 1;
        } else {
            return esquerda;
        }
    }


    public No rotationEsq(No node){
        No filho = node.getfilhoDir();
        node.setfilhoDir(filho.getfilhoEsq()); //lembrando que há irmao
        filho.setfilhoEsq(node); 
        node.setPai(filho);
         
        return filho;
    }

    public No rotationDir(No node){
        No filho = node.getfilhoEsq();
        node.setfilhoEsq(filho.getfilhoDir()); //trocar de lugar
        filho.setfilhoDir(node);
        node.setPai(filho);

        return filho; 
    }

    @Override 
    public void verificarAntesInsert(No node, Object o){
        node.setElement(o);

        root = InsertNo(root, node); //faz a insercao de forma recursiva para ficar log
        size++;
    }

    protected No InsertNo(No atual, No node){

        if (atual == null){
            return node; //se achar qualquer um vazio, já coloca e sendo rubro, inclusive a raiz precisa existir primeiro
        }

        if (atual.getCor().equals("Rubro") && atual == root){
            node.setCorPreto(); //caso da raiz que precisa ser negro
            return node;
        }

        //lembre-se que é diferente do while, é recursivo!
        if (atual.getChave() > node.getChave()){

            atual.setfilhoEsq(InsertNo(atual.getfilhoEsq(), node)); //além de colocar um novo no, irá andar recursivamente para o proximo

            //verifica se o nó atual e o nó a ser inserido são rubros
            if (atual.getCor().equals("Rubro") && node.getCor().equals("Rubro")){
                No avo = atual.getPai().getPai();
                No tio;

                //o tio pode ser tanto filhodir ou filhoesq do avo
                if (avo.getfilhoDir() == atual){
                    tio = avo.getfilhoEsq();
                } else {
                    tio = avo.getfilhoDir();
                }

                //se tiver tio e mesma cor rubro, será recoloracao
                if (tio != null && tio.getCor().equals("Rubro")) {
                    tio.setCorPreto();
                    atual.setCorPreto(); 
                    avo.setCorRubro();
                } 

                //caso do tio negro, precisa de rotação
                else if (tio != null && tio.getCor().equals("Negro") || tio == null) {

                    //caso RR
                    if (node == atual.getfilhoEsq() && atual == avo.getfilhoEsq()){
                        rotationDir(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        atual.setCorPreto();

                    }

                    //caso LL
                    if (node == atual.getfilhoDir() && atual == avo.getfilhoDir()){
                        rotationEsq(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        atual.setCorPreto();

                    }

                    //caso LR
                    if (node == atual.getfilhoDir() && atual == avo.getfilhoEsq()){
                        rotationEsq(atual);
                        rotationDir(avo);
                        
                        avo.setCorRubro();
                        atual.setCorRubro();
                        node.setCorPreto();
                    }

                    //caso RL
                    if (node == atual.getfilhoEsq() && atual == avo.getfilhoDir()){
                        rotationDir(atual); //movera com o filho esquerdo
                        rotationEsq(avo);

                        avo.setCorRubro();
                        atual.setCorRubro();
                        node.setCorPreto(); 
                        
                    }
                }
            } 
            
        } else {
            atual.setfilhoDir(InsertNo(atual.getfilhoDir(), node)); //segue ainda a logica do direito para maior, esquerda o menor que a raiz
            
            if (atual.getCor().equals("Rubro") && node.getCor().equals("Rubro")){
                No avo = atual.getPai().getPai();
                No tio;

                //o tio pode ser tanto filhodir ou filhoesq do avo
                if (avo.getfilhoDir() == atual){
                    tio = avo.getfilhoEsq();
                } else {
                    tio = avo.getfilhoDir();
                }

                //se tiver tio e mesma cor rubro, será recoloracao
                if (tio != null && tio.getCor().equals("Rubro")) {
                    tio.setCorPreto();
                    atual.setCorPreto(); 
                    avo.setCorRubro();
                } 

                //caso do tio negro, precisa de rotação
                else if (tio != null && tio.getCor().equals("Negro") || tio == null) {

                    //caso RR
                    if (node == atual.getfilhoEsq() && atual == avo.getfilhoEsq()){
                        rotationDir(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        atual.setCorPreto();

                    }

                    //caso LL
                    if (node == atual.getfilhoDir() && atual == avo.getfilhoDir()){
                        rotationEsq(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        atual.setCorPreto();

                    }

                    //caso LR
                    if (node == atual.getfilhoDir() && atual == avo.getfilhoEsq()){
                        rotationEsq(atual);
                        rotationDir(avo);
                        
                        avo.setCorRubro();
                        atual.setCorRubro();
                        node.setCorPreto();
                    }

                    //caso RL
                    if (node == atual.getfilhoEsq() && atual == avo.getfilhoDir()){
                        rotationDir(atual); //movera com o filho esquerdo
                        rotationEsq(avo);

                        avo.setCorRubro();
                        atual.setCorRubro();
                        node.setCorPreto(); 
                        
                    }
                }
            }
        }
        
        return atual;
    }
    
    @Override //já usando o metodo principal da arvore, mas com balanceamento
    public No verificarAntesRemove(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

        No removido = find(node);
        root = RemoveNo(root, node); 
        size--;
        return removido;
    }

    protected No RemoveNo(No atual, No node){
        //preciso usar a forma recursiva para acessar o No e retirar para depois voltar

        //se achar que está null, entao fara nada alem de null, pois não foi encontrado
        if (atual == null){
            return null;
        }

        if (atual.getChave() > node.getChave()){
            atual.setfilhoEsq(RemoveNo(atual.getfilhoEsq(), node));

        } 
        
        else if (atual.getChave() < node.getChave()){
            atual.setfilhoDir(RemoveNo(atual.getfilhoDir(), node));
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

                atual.setfilhoDir(RemoveNo(atual.getfilhoDir(), sucessor));                
            }

        }

        
        return atual;
    }
}

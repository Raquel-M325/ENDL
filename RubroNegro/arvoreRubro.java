package RubroNegro;

import AVL.arvoreAVLInterface;

public class arvoreRubro extends arvore implements arvoreRubroInterface{

    public int balancing(No node){ //balancea os nó negro

    }

     public No rotationEsq(No node){
        No filho = node.getfilhoDir();
        node.setfilhoDir(filho.getfilhoEsq()); //lembrando que há irmao
        filho.setfilhoEsq(node); 
        
        return filho;
    }

    public No rotationDir(No node){
        No filho = node.getfilhoEsq();
        node.setfilhoEsq(filho.getfilhoDir()); //trocar de lugar
        filho.setfilhoDir(node);

        return filho; 
    }

    @Override 
    public void insert(No node, Object o){
        node.setElement(o);

        root = verificarNoInsert(root, node); //faz a insercao de forma recursiva para ficar log
        size++;
    }

    protected No verificarNoInsert(No atual, No node){

        if (atual == null){
            return node; //se achar qualquer um vazio, já coloca e sendo rubro, inclusive a raiz precisa existir primeiro
        }

        if (atual.getCor().equals("Rubro") && atual == root){
            node.setCorPreto(); //caso da raiz que precisa ser negro
            return node;
        }

        //lembre-se que é diferente do while, é recursivo!
        if (atual.getChave() > node.getChave()){
            No avo = atual; //nao esta totalmente certo

            atual.setfilhoEsq(verificarNoInsert(atual.getfilhoEsq(), node)); //além de colocar um novo no, irá andar recursivamente para o proximo

            if (atual.getCor().equals("Rubro") == node.getCor().equals("Rubro")){

                No tio = avo.getfilhoDir();

                //se tiver tio e mesma cor rubro, será recoloracao
                if (tio != null && tio.getCor().equals("Rubro")) {
                    tio.setCorPreto();
                    atual.setCorPreto(); 
                    avo.setCorRubro();
                } 

                //caso do tio negro, precisa de rotação
                else if (tio != null && tio.getCor().equals("Negro") || tio == null) {

                    //caso RR
                    if (balancing(avo) > 1){
                        rotationDir(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        atual.setCorPreto();

                    }

                    //caso LL
                    if (balancing(avo) < -1){
                        rotationEsq(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        atual.setCorPreto();

                    }

                    //caso LR
                    if (balancing(avo) ){
                        rotationEsq(atual);
                        rotationDir(avo);
                        avo.setCorRubro();
                        atual.setCorRubro();
                        atual.getfilhoDir().setCorPreto();
                    }

                    //caso RL
                    if (){
                        rotationDir(atual); //movera com o filho esquerdo
                        rotationEsq(avo);
                        avo.setCorRubro();
                        atual.setCorRubro();
                        atual.getfilhoEsq().setCorPreto(); //nao esta totalmente seguro, pois o filho pode ter se perdido
                        
                    }


                }

            }
            
        } else {
            atual.setfilhoDir(verificarNoInsert(atual.getfilhoDir(), node)); //segue ainda a logica do direito para maior, esquerda o menor que a raiz
            
            if (atual.getCor().equals("Rubro") == node.getCor().equals("Rubro")){
                
            }
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
    
    @Override //já usando o metodo principal da arvore, mas com balanceamento
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

        atualizaAltura(atual);

        int balanca = balancing(atual);

        //balanca maior que 1, tendo demais a esquerda
        if (balanca > 1){

            int balancaFilhoEsq = balancing(atual.getfilhoEsq());

            //sendo negativo, maior prioridade
            if (balancaFilhoEsq < 0){
                atual.setfilhoEsq(rotationEsq(atual.getfilhoEsq()));
            }

            return rotationDir(atual);
        }

        //muito filho direito
        if (balanca < -1){

            int balancaFilhoDir = balancing(atual.getfilhoDir());

            //sendo positivo, tendo rotacao dupla
            if (balancaFilhoDir > 0){
                atual.setfilhoDir(rotationDir(atual.getfilhoDir())); 
            }

            return rotationEsq(atual);
        }
        
        return atual;
    }
}

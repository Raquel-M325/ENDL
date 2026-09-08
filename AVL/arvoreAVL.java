package AVL;

public class arvoreAVL extends arvore implements arvoreAVLInterface{ 
    public boolean isAVL(){
        if (isEmpty()){
            return true;
        }
        
        return isAVL(root);
    }

    protected boolean isAVL(No atual){
        if (atual == null){
            return true;
        }

        //onde ele começa a verificar se está fora do balanço em toda arvore, senao retorna true se está ok
        if (balancing(atual) < -1 || balancing(atual) > 1){
            return false;
        }

        return isAVL(atual.getfilhoEsq()) && isAVL(atual.getfilhoDir());

    }
    
    @Override 
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

    protected No menor(No atual){
        if (atual.getfilhoEsq() == null){
            return atual; //já sendo direito
        }

        return menor(atual.getfilhoEsq());
    }
    
    public int balancing(){
        if (isEmpty()){
            return 0;
        }

        return balancing(root);
        
    }

    @Override 
    public int balancing(No atual){
        
        //esquerda - direita, ficará subindo contando das alturas e faz a subtração
        return altura(atual.getfilhoEsq()) - altura(atual.getfilhoDir());
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

    @Override
    public String mostrar(No node) throws Correcao {

        if (isEmpty()) {
            throw new Correcao("Árvore vazia");
        }

        if (node == null) {
            return "";
        }

        int linhas = height() + 1;
        int larguraNo = 8;
        int colunas = (int) Math.pow(2, linhas) * larguraNo;

        char[][] matriz = new char[linhas][colunas];

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {
                matriz[i][j] = ' ';
            }
        }

        preencherMatriz(node, matriz, 0, colunas / 2, linhas);

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < linhas; i++) {

            int ultimo = colunas - 1;

            while (ultimo >= 0 && matriz[i][ultimo] == ' ') {
                ultimo--;
            }

            for (int j = 0; j <= ultimo; j++) {
                resultado.append(matriz[i][j]);
            }

            resultado.append("\n");
        }

        return resultado.toString();
    }

    protected void preencherMatriz(No node, char[][] matriz, int linha, int coluna, int altura) {
        if (node == null) {
            return;
        }

        String valor = node.getChave() + " [" + balancing(node) + "]";

        int inicio = coluna - valor.length() / 2;

        for (int i = 0; i < valor.length(); i++) {

            int posicao = inicio + i;

            if (posicao >= 0 && posicao < matriz[linha].length) {
                matriz[linha][posicao] = valor.charAt(i);
            }
        }

        if (linha == altura - 1) {
            return;
        }

        int distancia = (int) Math.pow(2, altura - linha - 2) * 4;

        if (node.getfilhoEsq() != null) {

            preencherMatriz(
                node.getfilhoEsq(),
                matriz,
                linha + 1,
                coluna - distancia,
                altura
            );
        }

        if (node.getfilhoDir() != null) {

            preencherMatriz(
                node.getfilhoDir(),
                matriz,
                linha + 1,
                coluna + distancia,
                altura
            );
        }
    }
}
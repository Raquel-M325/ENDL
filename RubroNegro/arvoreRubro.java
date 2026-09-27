package RubroNegro;

public class arvoreRubro extends arvore implements arvoreRubroInterface{

    public int alturaPreto(No atual){ //conta os no Preto e diz sua altura
        if (atual == null){
            return 0;
        }

        int esquerda = alturaPreto(atual.getfilhoEsq());
        int direita = alturaPreto(atual.getfilhoDir());
        int altura = 0;

        if (esquerda < 0 || direita < 0){
            return -1; //ha problema por ser negativo
        }

        if (esquerda != direita){
            return -1; //esta desiquilibrado, precisará corrigir 
        }

        //comeca a soma da altura dos caminhos esquerda e direita
        if (atual.getCor().equals("Preto") && atual.getfilhoEsq() != null){
            altura += 1 + esquerda;
        } 
        
        else if (atual.getCor().equals("Rubro") && atual.getfilhoEsq() != null) {
            altura += esquerda;
        } 
        
        else if (atual.getCor().equals("Preto") && atual.getfilhoDir() != null) {
            altura += 1 + direita;
        }

        else if (atual.getCor().equals("Rubro") && atual.getfilhoDir() != null) {
            altura += direita;
        }

        else if (atual.getCor().equals("Preto") && atual.getfilhoEsq() == null && atual.getfilhoDir() == null) {
            altura += 1;
        }

        else if (atual.getCor().equals("Rubro") && atual.getfilhoEsq() == null && atual.getfilhoDir() == null) {
            altura += 0;
        }

        else {
            return -1; //caso não seja nenhum dos casos, é desiquilibrado
        }
        
        return altura;
    }


    public No rotationEsq(No node){
        No avo = node;
        No paiDoAvo = avo.getPai();
        No filho = avo.getfilhoDir();

        avo.setfilhoDir(filho.getfilhoEsq());

        if (avo.getfilhoDir() != null){
            avo.getfilhoDir().setPai(avo);
        }

        //realiza a rotação para a esquerda
        filho.setfilhoEsq(avo);

        //atualiza os pais dos nós envolvidos na rotação
        filho.setPai(paiDoAvo);
        avo.setPai(filho);

        //verifica se o avo é a raiz da árvore
        if (paiDoAvo == null){
            root = filho;
        } 

        //caso de rotacao dupla
        else if (paiDoAvo.getfilhoEsq() == avo){
            paiDoAvo.setfilhoEsq(filho);
        } 
        else {
            paiDoAvo.setfilhoDir(filho);
        }

        return filho;
    }

    public No rotationDir(No node){
        No avo = node;
        No paiDoAvo = avo.getPai();
        No filho = avo.getfilhoEsq();

        avo.setfilhoEsq(filho.getfilhoDir());

        if (avo.getfilhoEsq() != null){
            avo.getfilhoEsq().setPai(avo);
        }

        filho.setfilhoDir(avo);

        filho.setPai(paiDoAvo);
        avo.setPai(filho);

        if (paiDoAvo == null){
            root = filho;
        } 

        //caso de rotacao dupla
        else if (paiDoAvo.getfilhoEsq() == avo){
            paiDoAvo.setfilhoEsq(filho);
        } 
        else {
            paiDoAvo.setfilhoDir(filho);
        }

        return filho;
    }

    @Override 
    public void verificarAntesInsert(No node, Object o) throws Correcao{
        node.setElement(o);

        if (isEmpty()){
            node.setCorPreto();
        }

        root = InsertNo(root, node); //faz a insercao de forma recursiva para ficar log
        
        balanceamentoRubroNegro(node);

        root.setCorPreto(); //garante que a raiz sempre será preta
        int altura = alturaPreto(root);

        if (altura == -1){
            throw new Correcao("A árvore não está balanceada");
        }

        size++;
    }

    @Override
    protected No InsertNo(No atual, No node){

        if (atual == null){
            return node; //se achar qualquer um vazio, já coloca e sendo rubro, inclusive a raiz precisa existir primeiro
        }

        //lembre-se que é diferente do while, é recursivo!
        if (atual.getChave() > node.getChave()){

            atual.setfilhoEsq(InsertNo(atual.getfilhoEsq(), node)); //além de colocar um novo no, irá andar recursivamente para o proximo
            
            if (atual.getfilhoEsq() != null){
                atual.getfilhoEsq().setPai(atual); //atualiza pai
            }
            
        } else {
            atual.setfilhoDir(InsertNo(atual.getfilhoDir(), node)); //segue ainda a logica do direito para maior, esquerda o menor que a raiz
            
            if (atual.getfilhoDir() != null){
                atual.getfilhoDir().setPai(atual); //atualiza pai
            }
        }

        return atual;
    }

    protected No balanceamentoRubroNegro(No node){

        if (node.getPai() != null && node.getPai().getPai() != null) {

            //verifica se o nó atual e o nó a ser inserido são rubros
            if (node.getPai().getCor().equals("Rubro") && node.getCor().equals("Rubro")){
                No avo = node.getPai().getPai();
                No tio;

                //o tio pode ser tanto filhodir ou filhoesq do avo
                if (avo.getfilhoDir() == node.getPai()){
                    tio = avo.getfilhoEsq();
                } else {
                    tio = avo.getfilhoDir();
                }

                //se tiver tio e mesma cor rubro, será recoloracao
                if (tio != null && tio.getCor().equals("Rubro")) {
                    tio.setCorPreto();
                    node.getPai().setCorPreto(); 
                    avo.setCorRubro();
                } 

                //caso do tio Preto, precisa de rotação
                else if (tio != null && tio.getCor().equals("Preto") || tio == null) {

                    //caso RR
                    if (node == node.getPai().getfilhoEsq() && node.getPai() == avo.getfilhoEsq()){
                        No novoTopo = rotationDir(avo); //a rotacao já move junto com pai atual, e retorna o filho
                        avo.setCorRubro();
                        novoTopo.setCorPreto();

                        return novoTopo;
                    }

                    //caso LL
                    if (node == node.getPai().getfilhoDir() && node.getPai() == avo.getfilhoDir()){
                        No novoTopo = rotationEsq(avo); //a rotacao já move junto com pai atual
                        avo.setCorRubro();
                        novoTopo.setCorPreto();

                        return novoTopo;
                    }

                    //caso LR
                    if (node == node.getPai().getfilhoDir() && node.getPai() == avo.getfilhoEsq()){
                        No pai = node.getPai();
                        
                        rotationEsq(pai); //movera com o filho direito
                        No novoTopo = rotationDir(avo);
                        
                        avo.setCorRubro();
                        pai.setCorRubro(); 
                        novoTopo.setCorPreto();

                        return novoTopo;
                    }

                    //caso RL
                    if (node == node.getPai().getfilhoEsq() && node.getPai() == avo.getfilhoDir()){
                        No pai = node.getPai();
                       
                        rotationDir(pai); //movera com o filho esquerdo
                        No novoTopo = rotationEsq(avo);
                        
                        avo.setCorRubro();
                        pai.setCorRubro(); 
                        novoTopo.setCorPreto(); 
                        
                        return novoTopo;
                    }
                }
            }
        } 

        return node;
    }
    
    @Override //já usando o metodo principal da arvore, mas com balanceamento
    public No verificarAntesRemove(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

        root = RemoveNo(root, node); 

        if (root != null) {
            root.setCorPreto(); //garante que a raiz sempre será preta
        }

        int altura = alturaPreto(root);

        if (altura == -1){
            throw new Correcao("A árvore não está balanceada");
        }
        
        size--;
        return node;
    }

    @Override
    protected No RemoveNo(No atual, No node){
        //preciso usar a forma recursiva para acessar o No e retirar para depois voltar

        //se achar que está null, entao fara nada alem de null, pois não foi encontrado
        if (atual == null){
            return null;
        }

        //aqui ele procura para achar o no que quer remover
        if (atual.getChave() > node.getChave()){
            atual.setfilhoEsq(RemoveNo(atual.getfilhoEsq(), node));

        } 
        
        else if (atual.getChave() < node.getChave()){
            atual.setfilhoDir(RemoveNo(atual.getfilhoDir(), node));
        } 
        
        //caso forem iguais, achando o que quer eliminar, precisa ver se há algum irmao, para que a recursao faça a ligacao
        else {

            //caso seja rubro e nao tenha filhos, pode remover direto, é impossivel ter dois rubros
            if (atual.getCor().equals("Rubro") && atual.getfilhoDir() == null && atual.getfilhoEsq() == null){
                return null; 
            }

            //caso seja negro e tenha um filho rubro, pode remover e o filho rubro vira preto
            if (atual.getCor().equals("Preto") && atual.getfilhoEsq() != null && atual.getfilhoEsq().getCor().equals("Rubro")){
                atual.getfilhoEsq().setCorPreto();
                return atual.getfilhoEsq();
            } 

            if (atual.getCor().equals("Preto") && atual.getfilhoDir() != null && atual.getfilhoDir().getCor().equals("Rubro")){
                atual.getfilhoDir().setCorPreto();
                return atual.getfilhoDir();
            }

            //caso de folha
            if (atual.getfilhoEsq() == null && atual.getfilhoDir() == null){
                
                //folha rubro nao conta
                if (atual.getCor().equals("Rubro")){
                    return null;
                }

                //entra caso de rubro negro
                balanceamentoRemocaoDuploNegro(atual);

                return null;

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

    protected No balanceamentoRemocaoDuploNegro(No atual){
        
        No pai = atual.getPai();

        if (pai == null){
            return atual;
        }

        if (pai.getfilhoEsq() == atual){
               
            //caso 1 - irmao é rubro -> recoloração e rotacao
            if (pai.getfilhoDir() != null && pai.getfilhoDir().getCor().equals("Rubro")) {
                pai.getfilhoDir().setCorPreto();
                pai.setCorRubro();

                rotationEsq(pai);
            } 

            //caso 2a - irmao é negro e pai negro -> recoloracao e atualizacao do pai
            else if (pai.getCor().equals("Preto") && (pai.getfilhoDir() == null || pai.getfilhoDir().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoEsq() == null || pai.getfilhoDir().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoDir() == null || pai.getfilhoDir().getfilhoDir().getCor().equals("Preto"))){
                if (pai.getfilhoDir() != null){
                    pai.getfilhoDir().setCorRubro();
                }

                atual = pai;

            }
                            
            //caso 2b - irmao é negro e pai rubro -> recoloracao do irmao para rubro e pai em negro
            else if (pai.getCor().equals("Rubro") && (pai.getfilhoDir() == null || pai.getfilhoDir().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoEsq() == null || pai.getfilhoDir().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoDir() == null || pai.getfilhoDir().getfilhoDir().getCor().equals("Preto"))){
                if (pai.getfilhoDir() != null){
                    pai.getfilhoDir().setCorRubro();
                }

                pai.setCorPreto();

            }

            //caso 3 - irmao é negro e filhoEsq como rubro do irmao negro -> rotacao e recoloracao
            else if (pai.getfilhoDir() != null && pai.getfilhoDir().getCor().equals("Preto") && pai.getfilhoDir().getfilhoEsq() != null && pai.getfilhoDir().getfilhoEsq().getCor().equals("Rubro") && (pai.getfilhoDir().getfilhoDir() == null || pai.getfilhoDir().getfilhoDir().getCor().equals("Preto"))){
                pai.getfilhoDir().getfilhoEsq().setCorPreto();
                pai.getfilhoDir().setCorRubro();
                rotationDir(pai.getfilhoDir());

            }

            //caso 4 - irmao é negro e filhodir como rubro do irmao negro - rotacao e recoloracao
            else if (pai.getfilhoDir() != null && pai.getfilhoDir().getCor().equals("Preto") && pai.getfilhoDir().getfilhoDir() != null && pai.getfilhoDir().getfilhoDir().getCor().equals("Rubro") && (pai.getfilhoDir().getfilhoEsq() == null || pai.getfilhoDir().getfilhoEsq().getCor().equals("Preto"))){
                if (pai.getCor().equals("Rubro")){
                    pai.getfilhoDir().setCorRubro();
                }

                pai.setCorPreto();
                pai.getfilhoDir().getfilhoDir().setCorPreto();

                rotationEsq(pai);
            }

        }

        else if (pai.getfilhoDir() == atual){

            //caso 1 - irmao é rubro -> recoloração e rotaca
            if (pai.getfilhoEsq() != null && pai.getfilhoEsq().getCor().equals("Rubro")){
                pai.getfilhoEsq().setCorPreto();
                pai.setCorRubro();

                rotationDir(pai);
            }

            //caso 2a - irmao é negro e pai negro -> recoloracao e atualizacao do pai
            else if (pai.getCor().equals("Preto") && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoDir() == null || pai.getfilhoEsq().getfilhoDir().getCor().equals("Preto"))){
                if (pai.getfilhoEsq() != null){
                    pai.getfilhoEsq().setCorRubro();
                }

                atual = pai;

            }

            //caso 2b - irmao é negro e pai rubro -> recoloracao do irmao para rubro e pai em negro
            else if (pai.getCor().equals("Rubro") && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoDir() == null || pai.getfilhoEsq().getfilhoDir().getCor().equals("Preto"))){
                if (pai.getfilhoEsq() != null){
                    pai.getfilhoEsq().setCorRubro();
                }

                pai.setCorPreto();

            }

            //caso 3 - irmao é negro e filhoEsq como rubro do irmao negro -> rotacao e recoloracao
            else if (pai.getfilhoEsq() != null && pai.getfilhoEsq().getCor().equals("Preto") && pai.getfilhoEsq().getfilhoEsq() != null && pai.getfilhoEsq().getfilhoEsq().getCor().equals("Rubro") && (pai.getfilhoEsq().getfilhoDir() == null || pai.getfilhoEsq().getfilhoDir().getCor().equals("Preto"))){
                pai.getfilhoEsq().getfilhoEsq().setCorPreto();
                pai.getfilhoEsq().setCorRubro();
                rotationDir(pai.getfilhoEsq());

            }

            //caso 4 - irmao é negro e filhodir como rubro do irmao negro - rotacao e recoloracao
            else if (pai.getfilhoEsq() != null && pai.getfilhoEsq().getCor().equals("Preto") && pai.getfilhoEsq().getfilhoDir() != null && pai.getfilhoEsq().getfilhoDir().getCor().equals("Rubro") && (pai.getfilhoEsq().getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq().getCor().equals("Preto"))){

                if (pai.getCor().equals("Rubro")){
                    pai.getfilhoEsq().setCorRubro();
                }

                pai.setCorPreto();
                pai.getfilhoEsq().getfilhoDir().setCorPreto();
                
                rotationEsq(pai);
            }
        }

        return atual;
    }

    @Override
    public String mostrar(No node) throws Correcao {

        if (isEmpty()) {
            throw new Correcao("Árvore vazia");
        }

        if (node == null) {
            return "";
        }

        int linhas = altura(node);
        int larguraNo = 12;
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

        String valor = node.getChave() + " [" + node.getCor() + "]";

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

        int distancia = (int) Math.pow(2, altura - linha - 2) * 8;

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

package RubroNegro;

public class arvoreRubro extends arvore implements arvoreRubroInterface{

    public int alturaPreto(No atual){ //conta os No Preto e diz sua altura
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

        //senao, liga o filho no lugar do avo
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

        //todo no novo que nao for a raiz entra como rubro
        else {
            node.setCorRubro();
        }

        root = InsertNo(root, node); //faz a insercao de forma recursiva para ficar log
        root.setPai(null);
        
        balanceamentoRubroNegro(node);

        root.setCorPreto(); //garante que a raiz sempre será preta
        root.setPai(null);
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

            //verifica se o nó atual e o pai dele são rubros
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

                    //o avo ficou rubro e pode ter quebrado a regra com o bisavo, entao sobe
                    return balanceamentoRubroNegro(avo);
                } 

                //caso do tio Preto, precisa de rotação
                else if (tio == null || tio.getCor().equals("Preto")) {

                    //caso LL (pai e filho na esquerda)
                    if (node == node.getPai().getfilhoEsq() && node.getPai() == avo.getfilhoEsq()){
                        No novoTopo = rotationDir(avo);
                        avo.setCorRubro();
                        novoTopo.setCorPreto();

                        return novoTopo;
                    }

                    //caso RR (pai e filho na direita)
                    if (node == node.getPai().getfilhoDir() && node.getPai() == avo.getfilhoDir()){
                        No novoTopo = rotationEsq(avo);
                        avo.setCorRubro();
                        novoTopo.setCorPreto();

                        return novoTopo;
                    }

                    //caso LR (pai na esquerda e filho na direita)
                    if (node == node.getPai().getfilhoDir() && node.getPai() == avo.getfilhoEsq()){
                        No pai = node.getPai();
                        
                        rotationEsq(pai);
                        No novoTopo = rotationDir(avo);
                        
                        avo.setCorRubro();
                        pai.setCorRubro(); 
                        novoTopo.setCorPreto();

                        return novoTopo;
                    }

                    //caso RL (pai na direita e filho na esquerda)
                    if (node == node.getPai().getfilhoEsq() && node.getPai() == avo.getfilhoDir()){
                        No pai = node.getPai();
                       
                        rotationDir(pai);
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
    
    @Override
    public No verificarAntesRemove(No node) throws Correcao{
        if (isEmpty()){
            throw new Correcao("Está vazia");
        }

        //antes de remover, procura para ver se o no existe
        No procurado = root;
        boolean achou = false;

        while (procurado != null && !achou){
            if (procurado.getChave() > node.getChave()){
                procurado = procurado.getfilhoEsq();
            } 
            else if (procurado.getChave() < node.getChave()){
                procurado = procurado.getfilhoDir();
            } 
            else {
                achou = true;
            }
        }

        if (!achou){
            throw new Correcao("Nó não encontrado");
        }

        root = RemoveNo(root, node); 

        if (root != null) {
            root.setCorPreto();
            root.setPai(null);
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

        //se achar que está null, entao fara nada, pois não foi encontrado
        if (atual == null){
            return root;
        }

        //aqui ele procura para achar o no que quer eliminar
        if (atual.getChave() > node.getChave()){
            return RemoveNo(atual.getfilhoEsq(), node);
        } 
        
        if (atual.getChave() < node.getChave()){
            return RemoveNo(atual.getfilhoDir(), node);
        } 
        
        //caso forem iguais, achou o que quer eliminar

        //caso tenha ambos os filhos, o menor numero da direita copia para o lugar e ele passa a ser o no eliminado
        if (atual.getfilhoDir() != null && atual.getfilhoEsq() != null){
            No sucessor = menor(atual.getfilhoDir());

            atual.setChave(sucessor.getChave());
            atual.setElement(sucessor.getElement());

            atual = sucessor;
        }

        //a partir daqui o no a eliminar tem no maximo um filho
        No pai = atual.getPai();

        //caso seja rubro e nao tenha filhos, pode remover direto
        if (atual.getCor().equals("Rubro") && atual.getfilhoDir() == null && atual.getfilhoEsq() == null){

            if (pai == null){
                root = null;
            } 
            else if (pai.getfilhoEsq() == atual){
                pai.setfilhoEsq(null);
            } 
            else {
                pai.setfilhoDir(null);
            }

            return root; 
        }

        //caso seja negro e tenha somente um filho rubro a esquerda, pode remover e o filho rubro vira preto
        if (atual.getCor().equals("Preto") && atual.getfilhoEsq() != null && atual.getfilhoEsq().getCor().equals("Rubro") && atual.getfilhoDir() == null){
            atual.getfilhoEsq().setCorPreto();
            atual.getfilhoEsq().setPai(pai);

            if (pai == null){
                root = atual.getfilhoEsq();
            } 
            else if (pai.getfilhoEsq() == atual){
                pai.setfilhoEsq(atual.getfilhoEsq());
            } 
            else {
                pai.setfilhoDir(atual.getfilhoEsq());
            }

            return root;
        } 

        //caso seja negro e tenha somente um filho rubro a direita, pode remover e o filho rubro vira preto
        if (atual.getCor().equals("Preto") && atual.getfilhoDir() != null && atual.getfilhoDir().getCor().equals("Rubro") && atual.getfilhoEsq() == null){
            atual.getfilhoDir().setCorPreto();
            atual.getfilhoDir().setPai(pai);

            if (pai == null){
                root = atual.getfilhoDir();
            } 
            else if (pai.getfilhoEsq() == atual){
                pai.setfilhoEsq(atual.getfilhoDir());
            } 
            else {
                pai.setfilhoDir(atual.getfilhoDir());
            }

            return root;
        }

        //caso de folha preta
        if (atual.getfilhoEsq() == null && atual.getfilhoDir() == null){

            //se for a raiz, a arvore fica vazia
            if (pai == null){
                root = null;
                return root;
            }

            boolean esquerda = pai.getfilhoEsq() == atual;

            //primeiro corrige com a folha ainda ligada (ela faz o papel do duplo negro)
            balanceamentoRemocaoDuploNegro(pai, esquerda);

            //depois de corrigir, o pai pode ter mudado por causa das rotacoes, entao pega de novo
            pai = atual.getPai();

            if (pai.getfilhoEsq() == atual){
                pai.setfilhoEsq(null);
            } 
            else {
                pai.setfilhoDir(null);
            }

            return root;
        }

        return root;
    }

    protected No balanceamentoRemocaoDuploNegro(No pai, boolean esquerda){
        
        if (pai == null){
            return null;
        }

        if (esquerda){
               
            //caso 1 - irmao é rubro -> recoloração e rotacao
            if (pai.getfilhoDir() != null && pai.getfilhoDir().getCor().equals("Rubro")){
                pai.getfilhoDir().setCorPreto();
                pai.setCorRubro();

                rotationEsq(pai);

                //depois da rotacao, o irmao passa a ser o novo filho direito e cai nos outros casos
                return balanceamentoRemocaoDuploNegro(pai, true);
            } 

            //caso 2a - irmao é negro e pai negro -> recoloracao e atualizacao do pai
            else if (pai.getCor().equals("Preto") && (pai.getfilhoDir() == null || pai.getfilhoDir().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoEsq() == null || pai.getfilhoDir().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoDir() == null || pai.getfilhoDir().getfilhoDir().getCor().equals("Preto"))){
                
                if (pai.getfilhoDir() != null){
                    pai.getfilhoDir().setCorRubro();
                }

                No avo = pai.getPai();

                if (avo == null){
                    return pai;
                }

                esquerda = avo.getfilhoEsq() == pai;

                return balanceamentoRemocaoDuploNegro(avo, esquerda);
            }
                            
            //caso 2b - irmao é negro e pai rubro -> recoloracao do irmao para rubro e pai em negro
            else if (pai.getCor().equals("Rubro") && (pai.getfilhoDir() == null || pai.getfilhoDir().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoEsq() == null || pai.getfilhoDir().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoDir() == null || pai.getfilhoDir().getfilhoDir() == null || pai.getfilhoDir().getfilhoDir().getCor().equals("Preto"))){
                
                if (pai.getfilhoDir() != null){
                    pai.getfilhoDir().setCorRubro();
                }

                pai.setCorPreto();
            }

            //caso 3 e 4 - irmao é negro e tem pelo menos um filho rubro
            else {

                //caso 3 - filhoEsq do irmao é rubro e o filhoDir é negro -> rotacao e recoloracao, vira o caso 4
                if (pai.getfilhoDir().getfilhoDir() == null || pai.getfilhoDir().getfilhoDir().getCor().equals("Preto")){
                    pai.getfilhoDir().getfilhoEsq().setCorPreto();
                    pai.getfilhoDir().setCorRubro();

                    rotationDir(pai.getfilhoDir());
                }

                //caso 4 - filhoDir do irmao é rubro, o irmao fica com a cor do pai
                if (pai.getCor().equals("Rubro")){
                    pai.getfilhoDir().setCorRubro();
                } else {
                    pai.getfilhoDir().setCorPreto();
                }

                pai.setCorPreto();
                pai.getfilhoDir().getfilhoDir().setCorPreto();

                rotationEsq(pai);
            }

        }

        else {

            //caso 1 - irmao é rubro -> recoloração e rotacao
            if (pai.getfilhoEsq() != null && pai.getfilhoEsq().getCor().equals("Rubro")){
                pai.getfilhoEsq().setCorPreto();
                pai.setCorRubro();

                rotationDir(pai);

                //depois da rotacao, o irmao passa a ser o novo filho esquerdo e cai nos outros casos
                return balanceamentoRemocaoDuploNegro(pai, false);
            }

            //caso 2a - irmao é negro e pai negro -> recoloracao e atualizacao do pai
            else if (pai.getCor().equals("Preto") && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoDir() == null || pai.getfilhoEsq().getfilhoDir().getCor().equals("Preto"))){
                
                if (pai.getfilhoEsq() != null){
                    pai.getfilhoEsq().setCorRubro();
                }

                No avo = pai.getPai();

                if (avo == null){
                    return pai;
                }

                esquerda = avo.getfilhoEsq() == pai;

                return balanceamentoRemocaoDuploNegro(avo, esquerda);
            }

            //caso 2b - irmao é negro e pai rubro -> recoloracao do irmao para rubro e pai em negro
            else if (pai.getCor().equals("Rubro") && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq().getCor().equals("Preto")) && (pai.getfilhoEsq() == null || pai.getfilhoEsq().getfilhoDir() == null || pai.getfilhoEsq().getfilhoDir().getCor().equals("Preto"))){
                
                if (pai.getfilhoEsq() != null){
                    pai.getfilhoEsq().setCorRubro();
                }

                pai.setCorPreto();
            }

            //caso 3 e 4 - irmao é negro e tem pelo menos um filho rubro
            else {

                //caso 3 - filhoDir do irmao é rubro e o filhoEsq é negro -> rotacao e recoloracao, vira o caso 4
                if (pai.getfilhoEsq().getfilhoEsq() == null || pai.getfilhoEsq().getfilhoEsq().getCor().equals("Preto")){
                    pai.getfilhoEsq().getfilhoDir().setCorPreto();
                    pai.getfilhoEsq().setCorRubro();

                    rotationEsq(pai.getfilhoEsq());
                }

                //caso 4 - filhoEsq do irmao é rubro, o irmao fica com a cor do pai
                if (pai.getCor().equals("Rubro")){
                    pai.getfilhoEsq().setCorRubro();
                } else {
                    pai.getfilhoEsq().setCorPreto();
                }

                pai.setCorPreto();
                pai.getfilhoEsq().getfilhoEsq().setCorPreto();

                rotationDir(pai);
            }
        }

        return pai;
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
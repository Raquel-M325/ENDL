package ArvoreB;

public class arvoreB implements arvoreBInterface{
    No root;
    private int t;

    public arvoreB(int t){
        //não pode ser null, tem que ter pelo menos uma raiz =
        this.t = t;
        root = new No(t);
    }

    public No find(Object o) throws Correcao{
        No atual = root;

        while (atual != null){
            for (int i = 0; i < atual.getChaves().length; i++){
                if (atual.getChaves()[i] == null){
                    atual = atual.getFilhos()[i]; //prosseguir para o próximo
                    break;
                }

                int comparacao = ((Comparable) o).compareTo(atual.getChaves()[i]);

                if (comparacao == 0){
                    return atual;
                } else if (comparacao < 0){
                    atual = atual.getFilhos()[i];
                    break;
                } else if (comparacao > 0 && i == atual.getChaves().length - 1){
                    atual = atual.getFilhos()[i + 1]; //prosseguir por ser maior
                    break;
                }
            }
        }

        return null;
    }

    public void insert(Object o){
        No atual = root;

        while (atual != null){
            int qtd = quantidadeChaves(atual);

            // se o nó está cheio, divide antes de continuar
            // (divisao devolve o pai, então a busca recomeça a partir dele)
            if (qtd == atual.getMaximoT()){
                atual = divisao(atual);
                continue;
            }

            //começa a procurar a sua posição
            for (int i = 0; i < atual.getChaves().length; i++){
                if (atual.getChaves()[i] == null){
                    // se não existe filho, o atual é uma folha: insere na posição vazia
                    if (atual.getFilhos()[i] == null){
                        atual.getChaves()[i] = o;
                        return;
                    }

                    atual = atual.getFilhos()[i]; // prosseguir para o próximo
                    break;
                }

                int comparacao = ((Comparable) o).compareTo(atual.getChaves()[i]);

                if (comparacao == 0){
                    return; // sem chave repetida
                } else if (comparacao < 0){
                    // verifica se é folha
                    if (atual.getFilhos()[i] == null){
                        // desloca os elementos para a direita
                        // para abrir espaço para o novo elemento e ordenação
                        for (int j = qtd; j > i; j--){
                            atual.getChaves()[j] = atual.getChaves()[j - 1];
                        }

                        atual.getChaves()[i] = o; // inserir na posição correta
                        return;
                    } else {
                        // ainda não é folha, então desce
                        atual = atual.getFilhos()[i];
                        break;
                    }
                }
            }
        }
    }

    public void remove(Object o) throws Correcao{
        No no = find(o);

        if (no == null){
            throw new Correcao("Chave não encontrada!");
        } else{
            t = 0;

            for (int i = 0; i < no.getChaves().length; i++){
                if (no.getChaves()[i] != null){
                    t++;
                }
            }

            //se for folha, remove
            if (no.getFilhos()[0] == null){
                for (int i = 0; i < no.getChaves().length; i++){
                    if (no.getChaves()[i] != null && ((Comparable) o).compareTo(no.getChaves()[i]) == 0){
                        //desloca os elementos para a esquerda
                        for (int j = i; j < no.getChaves().length - 1; j++){
                            no.getChaves()[j] = no.getChaves()[j + 1];
                        }

                        no.getChaves()[no.getChaves().length - 1] = null;
                        t--;
                        break;
                    }
                }

                //ficou abaixo do mínimo
                if (t < no.getMinimoT()){
                    fusao(no);
                }
            } else{
                //se não for folha, procura a chave predecessora
                for (int i = 0; i < no.getChaves().length; i++){
                    if (no.getChaves()[i] != null && no.getChaves()[i].equals(o)){
                        No predecessor = no.getFilhos()[i];

                        //vai até o último filho
                        while (predecessor.getFilhos()[0] != null){
                            int ultimoFilho = 0;

                            for (int j = 0; j < predecessor.getFilhos().length; j++){
                                if (predecessor.getFilhos()[j] != null){
                                    ultimoFilho = j;
                                }
                            }

                            predecessor = predecessor.getFilhos()[ultimoFilho];
                        }

                        int ultimaChave = 0;

                        for (int j = 0; j < predecessor.getChaves().length; j++){
                            if (predecessor.getChaves()[j] != null){
                                ultimaChave = j;
                            }
                        }

                        //substitui pela chave predecessora
                        no.getChaves()[i] = predecessor.getChaves()[ultimaChave];

                        //remove a chave do predecessor
                        for (int j = ultimaChave; j < predecessor.getChaves().length - 1; j++){
                            predecessor.getChaves()[j] = predecessor.getChaves()[j + 1];
                        }

                        predecessor.getChaves()[predecessor.getChaves().length - 1] = null;

                        //verifica se ficou abaixo do mínimo
                        if (quantidadeChaves(predecessor) < predecessor.getMinimoT()){
                            fusao(predecessor);
                        }

                        break;
                    }
                }
            }
        }
    }

    public String mostrar(No node){
        if (node == null){
            return "";
        }

        String resultado = "";

        for (int i = 0; i < node.getChaves().length; i++){

            if (node.getFilhos()[i] != null){
                resultado += mostrar(node.getFilhos()[i]);
            }

            if (node.getChaves()[i] != null){
                resultado += node.getChaves()[i] + " ";
            }
        }

        //último filho
        if (node.getFilhos()[node.getFilhos().length - 1] != null){
            resultado += mostrar(node.getFilhos()[node.getFilhos().length - 1]);
        }

        return resultado;
    }

    private int quantidadeChaves(No node){
        int quantidade = 0;

        for (int i = 0; i < node.getChaves().length; i++){
            if (node.getChaves()[i] != null){
                quantidade++;
            }
        }

        return quantidade;
    }

    //para remocao
    protected void fusao(No atual){
        if (atual == root){
            if (quantidadeChaves(atual) == 0 && atual.getFilhos()[0] != null){
                root = atual.getFilhos()[0];
                root.setPai(null);
            }
            return;
        }

        No pai = atual.getPai();
        int posicao = 0;

        //descobre em qual posição o nó está no pai
        for (int i = 0; i < pai.getFilhos().length; i++){
            if (pai.getFilhos()[i] == atual){
                posicao = i;
                break;
            }
        }

        //verifica se o irmão esquerdo pode emprestar
        if (posicao > 0){
            No esquerdo = pai.getFilhos()[posicao - 1];

            if (quantidadeChaves(esquerdo) > esquerdo.getMinimoT()){
                int quantidade = quantidadeChaves(atual);

                //abre espaço no atual
                for (int i = quantidade; i > 0; i--){
                    atual.getChaves()[i] = atual.getChaves()[i - 1];
                }

                //a chave do pai desce
                atual.getChaves()[0] = pai.getChaves()[posicao - 1];

                //a última chave do irmão sobe
                int ultima = quantidadeChaves(esquerdo) - 1;
                pai.getChaves()[posicao - 1] = esquerdo.getChaves()[ultima];
                esquerdo.getChaves()[ultima] = null;

                //se não for folha, o último filho do irmão vira o primeiro do atual
                if (esquerdo.getFilhos()[0] != null){
                    for (int i = quantidade + 1; i > 0; i--){
                        atual.getFilhos()[i] = atual.getFilhos()[i - 1];
                    }

                    atual.getFilhos()[0] = esquerdo.getFilhos()[ultima + 1];
                    atual.getFilhos()[0].setPai(atual);
                    esquerdo.getFilhos()[ultima + 1] = null;
                }
                return;
            }
        }

        //verifica se o irmão direito pode emprestar
        if (posicao + 1 < pai.getFilhos().length && pai.getFilhos()[posicao + 1] != null){
            No direita = pai.getFilhos()[posicao + 1];

            if (quantidadeChaves(direita) > direita.getMinimoT()){
                int quantidade = quantidadeChaves(atual);
                boolean temFilhos = direita.getFilhos()[0] != null;

                //a chave do pai desce
                atual.getChaves()[quantidade] = pai.getChaves()[posicao];

                //a primeira chave do irmão sobe
                pai.getChaves()[posicao] = direita.getChaves()[0];

                //desloca as chaves do irmão
                for (int i = 0; i < direita.getChaves().length - 1; i++){
                    direita.getChaves()[i] = direita.getChaves()[i + 1];
                }

                direita.getChaves()[direita.getChaves().length - 1] = null;

                //se não for folha, o primeiro filho do irmão vira o último do atual
                if (temFilhos){
                    atual.getFilhos()[quantidade + 1] = direita.getFilhos()[0];
                    atual.getFilhos()[quantidade + 1].setPai(atual);

                    for (int i = 0; i < direita.getFilhos().length - 1; i++){
                        direita.getFilhos()[i] = direita.getFilhos()[i + 1];
                    }

                    direita.getFilhos()[direita.getFilhos().length - 1] = null;
                }
                return;
            }
        }

        //não conseguiu emprestar, então faz fusão com o irmão esquerdo
        if (posicao > 0){
            No esquerdo = pai.getFilhos()[posicao - 1];

            int quantidade = quantidadeChaves(esquerdo);
            int inicioFilhos = quantidade + 1;

            //desce a chave do pai
            esquerdo.getChaves()[quantidade] = pai.getChaves()[posicao - 1];
            quantidade++;

            //passa as chaves do atual para o irmão esquerdo
            for (int i = 0; i < atual.getChaves().length; i++){
                if (atual.getChaves()[i] != null){
                    esquerdo.getChaves()[quantidade] = atual.getChaves()[i];
                    quantidade++;
                }
            }

            //passa os filhos do atual para o irmão esquerdo
            for (int i = 0; i < atual.getFilhos().length; i++){
                if (atual.getFilhos()[i] != null){
                    esquerdo.getFilhos()[inicioFilhos + i] = atual.getFilhos()[i];
                    atual.getFilhos()[i].setPai(esquerdo);
                }
            }

            //remove a chave do pai
            for (int i = posicao - 1; i < pai.getChaves().length - 1; i++){
                pai.getChaves()[i] = pai.getChaves()[i + 1];
            }

            pai.getChaves()[pai.getChaves().length - 1] = null;

            //remove o filho do pai
            for (int i = posicao; i < pai.getFilhos().length - 1; i++){
                pai.getFilhos()[i] = pai.getFilhos()[i + 1];
            }

            pai.getFilhos()[pai.getFilhos().length - 1] = null;
        } else{
            //faz fusão com o irmão direito
            No direita = pai.getFilhos()[posicao + 1];
            int quantidade = quantidadeChaves(atual);
            int inicioFilhos = quantidade + 1;

            //desce a chave do pai
            atual.getChaves()[quantidade] = pai.getChaves()[posicao];
            quantidade++;

            //passa as chaves do irmão direito para o atual
            for (int i = 0; i < direita.getChaves().length; i++){
                if (direita.getChaves()[i] != null){
                    atual.getChaves()[quantidade] = direita.getChaves()[i];
                    quantidade++;
                }
            }

            //passa os filhos do irmão direito para o atual
            for (int i = 0; i < direita.getFilhos().length; i++){
                if (direita.getFilhos()[i] != null){
                    atual.getFilhos()[inicioFilhos + i] = direita.getFilhos()[i];
                    direita.getFilhos()[i].setPai(atual);
                }
            }

            //remove a chave do pai
            for (int i = posicao; i < pai.getChaves().length - 1; i++){
                pai.getChaves()[i] = pai.getChaves()[i + 1];
            }

            pai.getChaves()[pai.getChaves().length - 1] = null;

            //remove o filho direito
            for (int i = posicao + 1; i < pai.getFilhos().length - 1; i++){
                pai.getFilhos()[i] = pai.getFilhos()[i + 1];
            }

            pai.getFilhos()[pai.getFilhos().length - 1] = null;
        }

        //verifica se o pai ficou abaixo do mínimo
        if (pai != root && quantidadeChaves(pai) < pai.getMinimoT()){
            fusao(pai);
        }

        //se a raiz ficou vazia, o primeiro filho vira a nova raiz
        if (pai == root && quantidadeChaves(pai) == 0){
            root = pai.getFilhos()[0];
            root.setPai(null);
        }
    }

    //para insercao
    protected No divisao(No atual){
        int meio = atual.getT() - 1;
        Object chaveMeio = atual.getChaves()[meio];

        No direita = new No(atual.getT());

        //passa as chaves da direita
        int posicao = 0;

        for (int i = meio + 1; i < atual.getChaves().length; i++){
            if (atual.getChaves()[i] != null){
                direita.getChaves()[posicao] = atual.getChaves()[i];
                posicao++;
            }
        }

        //remove as chaves que foram para a direita
        for (int i = meio; i < atual.getChaves().length; i++){
            atual.getChaves()[i] = null;
        }

        //se não for folha, passa os filhos da direita
        if (atual.getFilhos()[0] != null){
            int posicaoFilho = 0;

            for (int i = atual.getT(); i < atual.getFilhos().length; i++){

                if (atual.getFilhos()[i] != null){

                    direita.getFilhos()[posicaoFilho] = atual.getFilhos()[i];
                    atual.getFilhos()[i].setPai(direita);
                    atual.getFilhos()[i] = null;
                    posicaoFilho++;
                }
            }
        }

        //se era a raiz, cria uma nova raiz
        if (atual == root){
            No novaRaiz = new No(atual.getT());

            novaRaiz.getChaves()[0] = chaveMeio;
            novaRaiz.getFilhos()[0] = atual;
            novaRaiz.getFilhos()[1] = direita;

            atual.setPai(novaRaiz);
            direita.setPai(novaRaiz);

            root = novaRaiz;

            return novaRaiz;
        }

        //se não era raiz, coloca a chave no pai
        No pai = atual.getPai();
        int posicaoFilho = 0;

        for (int i = 0; i < pai.getFilhos().length; i++){
            if (pai.getFilhos()[i] == atual){
                posicaoFilho = i;
                break;
            }
        }

        int quantidadePai = quantidadeChaves(pai);

        //abre espaço para a chave no pai
        for (int i = quantidadePai; i > posicaoFilho; i--){
            pai.getChaves()[i] = pai.getChaves()[i - 1];
        }

        pai.getChaves()[posicaoFilho] = chaveMeio;

        //abre espaço para o novo filho
        for (int i = pai.getFilhos().length - 1; i > posicaoFilho + 1; i--){
            pai.getFilhos()[i] = pai.getFilhos()[i - 1];
        }

        pai.getFilhos()[posicaoFilho + 1] = direita;
        direita.setPai(pai);

        return pai;
    }
}
package ArvoreB;

public class arvoreB implements arvoreBInterface{
    private No root;
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
                
                }
            }
        }

        throw new Correcao("Chave não encontrada!");
    }

    public void insert(Object o){

        No atual = root;

        while (atual != null){

            t = 0;

            for (int i = 0; i < atual.getChaves().length; i++){

                // verifica quantos elementos existem na chave
                if (atual.getChaves()[i] == null){

                    // se não existe filho, o atual é uma folha
                    if (atual.getFilhos()[i] == null){
                        break;
                    }

                    atual = atual.getFilhos()[i]; // prosseguir para o próximo
                    break;
                }

                int comparacao = ((Comparable) o).compareTo(atual.getChaves()[i]);

                if (comparacao == 0){
                    return; // sem chave repetida
                }

                else if (comparacao < 0){

                    // verifica quantas chaves existem
                    for (int k = 0; k < atual.getChaves().length; k++){
                        if (atual.getChaves()[k] != null){
                            t++;
                        }
                    }

                    // se já está cheio, divide
                    if (t == atual.getMaximoT()){
                        divisao();
                    }

                    else{

                        // verifica se é folha
                        if (atual.getFilhos()[i] == null){

                            // desloca os elementos para a direita
                            // para abrir espaço para o novo elemento
                            for (int j = t; j > i; j--){
                                atual.getChaves()[j] = atual.getChaves()[j - 1];
                            }

                            atual.getChaves()[i] = o; // inserir na posição correta

                            t++;

                            // verifica se ficou cheio
                            if (t == atual.getMaximoT()){
                                divisao();
                            }

                            break;
                        }

                        else{
                            // ainda não é folha, então desce
                            atual = atual.getFilhos()[i];
                            break;
                        }
                    }
                }

                else if (comparacao > 0 && i == atual.getChaves().length - 1){

                    // verifica quantas chaves existem
                    for (int k = 0; k < atual.getChaves().length; k++){
                        if (atual.getChaves()[k] != null){
                            t++;
                        }
                    }

                    // se já está cheio, divide
                    if (t == atual.getMaximoT()){
                        divisao();
                    }

                    else{

                        // quando o elemento é maior que todas as chaves,
                        // o filho correspondente é o último
                        if (atual.getFilhos()[i + 1] == null){

                            // como é maior que todas,
                            // entra na próxima posição vazia
                            atual.getChaves()[t] = o;

                            t++;

                            // verifica se ficou cheio
                            if (t == atual.getMaximoT()){
                                divisao();
                            }

                            break;
                        }

                        else{
                            // desce para o último filho
                            atual = atual.getFilhos()[i + 1];
                            break;
                        }
                    }
                }
            }
        }
    }

    public void remove(Object o) throws Correcao{
        if (find(o) == null) {
            throw new Correcao("Chave não encontrada!");
        }
        
        else{

            //se for folha, remove
            if (find(o).getFilhos()[0] == null){

                for (int i = 0; i < find(o).getChaves().length; i++){

                    if (find(o).getChaves()[i] == null && ((Comparable) o).compareTo(find(o).getChaves()[i]) == 0){
                        find(o).getChaves()[i] = null;
                        break;
                    }
                }
            } 
            
            else {

                if (){
                    fusao();

                }


            }
        }
    }

    public String mostrar(No node){
        if (node == null) {
            return "";
        }

        String resultado = "";

        for (int i = 0; i < node.getChaves().length; i++) {
            if (node.getChaves()[i] != null) {
                resultado += node.getChaves()[i] + " ";
            }
        }

        return resultado;
    }


    //para remocao
    protected void fusao(){


    }

    //para insercao
    protected void divisao(){

    }
    
}

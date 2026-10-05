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
        //ele ve o menor de todos, o primeiro filho
        No atual = root;
        

        while (atual != null){

            int tamanhoAtual = 0;

            
            for (int i = 0; i < atual.getChaves().length; i++){
                
                if (atual.getChaves()[i] == null){
                    atual = atual.getFilhos()[i]; //prosseguir para o próximo
                    break;
                }

                int comparacao = ((Comparable) o).compareTo(atual.getChaves()[i]);

                if (comparacao == 0){
                    return; //sem chave repetida, o que importa é diferença
                } 

                else if (comparacao < 0){
                    atual = atual.getFilhos()[i];
                    break;

                } else if (comparacao > 0 && i == atual.getChaves().length - 1){
                    atual = atual.getFilhos()[i + 1]; //prosseguir por ser maior
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

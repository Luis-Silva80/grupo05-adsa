package b.com.tothlibs.apitothlib.listas;

public class ListaObj<T> {

    private T[] vetor;
    private int nroElem;

    public ListaObj(int tam) {
        this.vetor = (T[]) new Object[tam];
        this.nroElem = 0;
    }

    public Boolean adicionaElemento(T elemento) {
        Boolean sucess = false;

        if (nroElem != vetor.length) {
            vetor[nroElem++] = elemento;
            sucess = true;
        }

        return sucess;
    }

    public void exibeLista() {
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    public Integer buscar(T ele) {

        Integer posicao = -1;

        System.out.println("\nExibindo elemento procurado: ");

        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(ele)) {
                posicao = i;
            }
        }
        return posicao;
    }
    /*

*
* public Boolean removePeloIndice (int indice) {
        if (indice < 0 || indice >= nroElem) {
            System.out.println("Índice inválido!");
            return false;
        }
        else {
            // Loop para "deslocar para a esquerda" os valores
            // do vetor sobrescrevendo o valor removido
            for (int i = indice; i < nroElem-1; i++) {
                vetor[i] = vetor[i+1];
            }
            // outra forma de fazer esse loop
//            for (int i = indice + 1; i < nroElem; i++) {
//                vetor[i-1] = vetor[i];
//            }
            nroElem--;
            return true;
        }
    }
    *
    * */


    public Boolean removePeloIndice(int indice) {

        if (indice < 0 || indice >= nroElem) {
            System.out.println("Índice inválido!");
            return false;
        } else {
            // Loop para "deslocar para a esquerda" os valores
            // do vetor sobrescrevendo o valor removido
            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }
            // outra forma de fazer esse loop
//            for (int i = indice + 1; i < nroElem; i++) {
//                vetor[i-1] = vetor[i];
//            }
            nroElem--;
            return true;
        }

    }

    public Boolean removePeloElemento(T elemento) {

        int indice = buscar(elemento);

        System.out.println("\nindice: " + indice);

        return removePeloIndice(indice);
    }


    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {   // se índice inválido
            return null;                        // então retorna null
        } else {
            return vetor[indice];
        }
    }


    public void adicionaNoIndice(Integer indice, T empresa) {

        if (indice == nroElem) {
            System.out.println("lista cheia");
        } else {
            for (int i = vetor.length - 1; i > -1; i--) {
                if (i == nroElem) {
                    vetor[i+1] = vetor[i];
                        vetor[indice] = empresa;
//                    if (i == indice) {
//                        System.out.println("aqui tbm");
//                    }
                }
            }

            nroElem++;
        }
    }

    /* limpa() - limpa a lista */
    public void limpa() {
        nroElem = 0;
    }


}
package b.com.tothlibs.apitothlib.listas;

public class PilhaObj<T> {

    private       T[] pilha;
    private int   topo;
    private int   tamanhoPilha;

    public PilhaObj(int tamanhoPilha) {
        this.tamanhoPilha = tamanhoPilha;
        pilha = (T[]) new Object[tamanhoPilha];
        topo = -1;
    }

    public Boolean isEmpty(){

        return topo == -1;
    }

    public Boolean isFull(){

        return topo == pilha.length - 1;
    }

    public void push(T t){

        if(!isFull()) {

            topo += 1;
            pilha[topo] = t ;

        } else {

            System.out.println("A pilha está cheia!");

        }


    }

    public  T pop(){

        if(!isEmpty()){

            T valorPop = pilha[topo--];

            return valorPop;

        }

        return  null;

    }

    public T peek(){

        if(!isEmpty()){

            return pilha[topo];

        }

        return null;

    }

    public void exibe() {

        if(isEmpty()){

            System.out.println("Lista está vazia!");
        } else{

            int c = topo;

            for( ; c >= 0; c --){

                System.out.println(pilha[c]);

            }

        }

    }

    public T[] getPilha() {
        return pilha;
    }

    public T getTopo() {
        return pilha[topo];
    }

    public int getTamanhoPilha() {
        return tamanhoPilha;
    }


}

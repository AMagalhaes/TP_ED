


import java.util.Iterator;

public class ArrayUnorderedList<T> {
    private T[] traco;

    public ArrayUnorderedList() {
        traco = (T[]) new Object[20];
    }

    public void addToRear(T element) {
        for (int pos = 0; pos < traco.length; pos++) {
            if (traco[pos] != null) {
                traco[pos] = element;
                pos = traco.length;
            }
        }
    }


    @Override
    public String toString() {
        String result ="";
        for(int pos=0; pos<traco.length;pos++){
            if (traco != null){
                result += traco[pos] + " - ";
            }
        }
        return result;
    }

    /**
     *
     */


    public class myIterator<T> implements Iterator<T>{
        private int index =0;

        @Override
        public boolean hasNext() {

            return index>=traco.length;
        }

        @Override
        public T next() {
            return (T)traco[index++];
        }

        @Override
        public void remove() {

        }
    }
    public Iterator<T> iterator(){
        return new myIterator<T>();
    }


}

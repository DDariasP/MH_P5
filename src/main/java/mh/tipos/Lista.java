package mh.tipos;

import java.util.ArrayList;

/**
 *
 * @author diego
 * @param <T>
 */
public class Lista<T> {

    public ArrayList<T> lista;

    public Lista() {
        lista = new ArrayList<>();
    }

    public Lista(Lista n) {
        int size = n.size();
        lista = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            T tmp = (T) (n.get(i));
            lista.add(tmp);
        }
    }

    public void add(T obj) {
        lista.add(obj);
    }

    public void add(int index, T obj) {
        lista.add(index, obj);
    }

    public void remove(int index) {
        lista.remove(index);
    }

    public void remove(T obj) {
        lista.remove(obj);
    }

    public T get(int index) {
        return lista.get(index);
    }

    public T head() {
        return lista.get(0);
    }

    public T tail() {
        return lista.get(lista.size() - 1);
    }

    public void replace(int i, T obj) {
        lista.set(i, obj);
    }

    public boolean contains(T obj) {
        return lista.contains(obj);
    }

    public int count(T obj) {
        int size = lista.size();
        int n = 0;
        for (int i = 0; i < size; i++) {
            if (obj.equals(lista.get(i))) {
                n++;
            }
        }
        return n;
    }

    public int position(T obj) {
        int pos = lista.indexOf(obj);
        return pos;
    }

    public int size() {
        return lista.size();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    @Override
    public String toString() {
        int size = lista.size();
        String output = "[";
        for (int i = 0; i < size; i++) {
            output = output + lista.get(i).toString() + " ";
        }
        output = output.trim() + "]";
        return output;
    }

}

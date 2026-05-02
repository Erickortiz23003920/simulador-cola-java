package src;

public class Pila {
    private Nodo tope;

    public void push(Object dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
    }

    public Object pop() {
        if (estaVacia())
            return null;
        Object dato = tope.dato;
        tope = tope.siguiente;
        return dato;
    }

    public Object peek() {
        return estaVacia() ? null : tope.dato;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public void mostrar() {
        Nodo aux = tope;
        while (aux != null) {
            System.out.println(aux.dato);
            aux = aux.siguiente;
        }
    }
}

package src;

public class Cola {
    private Nodo frente, finalCola;

    public void encolar(Object dato) {
        Nodo nuevoNodo = new Nodo(dato);
        if (estaVacia()) {
            frente = finalCola = nuevoNodo;
        }
        else {
            finalCola.siguiente = nuevoNodo;
            finalCola = nuevoNodo;
        }
    }
    public Object desencolar() {
        if (estaVacia())
            return null;
        Object dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            finalCola = null;
        }
        return dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void mostrar() {
        Nodo aux = frente;
        while (aux != null) {
            System.out.println(aux.dato);
            aux = aux.siguiente;
        }
    }
}

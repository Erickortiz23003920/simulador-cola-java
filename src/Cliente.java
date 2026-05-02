package src;
public class Cliente {
    private String id;
    private String nombre;
    private String servicio;


    public Cliente(String id, String nombre, String servicio) {
        this.id = id;
        this.nombre = nombre;
        this.servicio = servicio;
    }

    @Override

    public String toString() {
        return id + " - " + nombre + " ( " + servicio + " )";
    }
}

package uy.edu.um.prog2.adt.Entities;

public class Piloto implements Comparable<Piloto> {

    private String nombre;
    private int menciones;

    public Piloto(String nombre) {
        this.nombre = nombre;
        this.menciones = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getMenciones() {
        return menciones;
    }

    public void setMenciones(int menciones) {
        this.menciones = menciones;
    }

    public boolean equals(Piloto obj) {
        return this.nombre.equals(obj.nombre);
    }

    @Override
    public int compareTo(Piloto o) {
        return (this.menciones-o.getMenciones());
    }
}

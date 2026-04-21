package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Materia {

    private String nombre;
    private String codigo;
    private int cargaHoraria;
    private int cuatrimestre;
    private boolean esObligatoria;
    private List<Materia> correlativas;

    public Materia(String codigo, String nombre, int cargaHoraria, int cuatrimestre, boolean esObligatoria){
        this.codigo = codigo;
        this.nombre = nombre;
        this.cargaHoraria = cargaHoraria;
        this.cuatrimestre = cuatrimestre;
        this.correlativas = new ArrayList<>();
        this.esObligatoria = esObligatoria;
    }

    public void agregarCorrelativas(Materia m){
        this.correlativas.add(m);
    }

    public boolean esObligatoria() {
        return esObligatoria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public int getCuatrimestre() {
        return cuatrimestre;
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    @Override
    public String toString() {
        return this.codigo + " - " + this.nombre;
    }
}

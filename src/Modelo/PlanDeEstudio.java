package Modelo;

import java.util.ArrayList;
import java.util.List;

public class PlanDeEstudio {
    private String nombre;
    private List<Materia> obligatorias;
    private List<Materia> optativas;
    private int cantidadOptativas;
    private ICondicionInscripcion estrategia;

    //Constructor protected para que solo pueda llamarlo el builder
    //que se encuentra en el mismo paquete
     protected PlanDeEstudio(PlanDeEstudioBuilder builder){
        this.nombre = builder.getNombre();
        this.obligatorias = new ArrayList<>(builder.getObligatorias());
        this.optativas = new ArrayList<>(builder.getOptativas());
        this.cantidadOptativas = builder.getCantidadOptativas();
        this.estrategia = builder.getEstrategia();
    }

    public List<Materia> getTodasMaterias(){
         List<Materia> todas = new ArrayList<>(this.obligatorias);
         todas.addAll(this.optativas);
         return todas;
    }

    public ICondicionInscripcion getEstrategia(){
         return this.estrategia;
    }

    public List<Materia> getOptativas() {
        return optativas;
    }

    public List<Materia> getObligatorias() {
        return obligatorias;
    }

    public int getCantidadOptativas() {
        return cantidadOptativas;
    }
}

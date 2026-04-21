package Modelo;

import java.util.ArrayList;
import java.util.List;

public class PlanDeEstudioBuilder implements IPlanDeEstudio {

    private String nombre;
    private List<Materia> obligatorias = new ArrayList<>();
    private List<Materia> optativas = new ArrayList<>();
    private int cantidadOptativas;
    private ICondicionInscripcion estrategia;

    @Override
    public IPlanDeEstudio setNombreCarrera(String carrera) {
        this.nombre = carrera;
        return this;
    }

    @Override
    public IPlanDeEstudio addMateriaObligatoria(Materia obligatoria) {
        this.obligatorias.add(obligatoria);
        return this;
    }

    @Override
    public IPlanDeEstudio addMateriaOptativa(Materia optativa) {
        this.optativas.add(optativa);
        return this;
    }

    @Override
    public IPlanDeEstudio setCantidadOptativas(int cantidadOptativas) {
        this.cantidadOptativas = cantidadOptativas;
        return this;
    }

    @Override
    public IPlanDeEstudio setEstrategiaInscripcion(ICondicionInscripcion estrategia) {
        this.estrategia = estrategia;
        return this;
    }



    @Override
    public PlanDeEstudio build() {
        //No tendria sentido que un plan de estudio no tenga nombre o estragia
        if (nombre == null || estrategia == null){
            throw new IllegalStateException("Faltan datos criticos para el plan de estudio.");
        }
        return new PlanDeEstudio(this);
    }

    public String getNombre() {
        return nombre;
    }

    public List<Materia> getObligatorias() {
        return obligatorias;
    }

    public List<Materia> getOptativas() {
        return optativas;
    }

    public int getCantidadOptativas() {
        return cantidadOptativas;
    }

    public ICondicionInscripcion getEstrategia() {
        return estrategia;
    }
}

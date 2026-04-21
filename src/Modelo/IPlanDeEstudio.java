package Modelo;

public interface IPlanDeEstudio {

    //Interfaz fluida de patron builder
    IPlanDeEstudio setNombreCarrera(String carrera);
    IPlanDeEstudio addMateriaOptativa(Materia optativa);
    IPlanDeEstudio addMateriaObligatoria(Materia obligatoria);
    IPlanDeEstudio setCantidadOptativas(int cantidad);
    IPlanDeEstudio setEstrategiaInscripcion(ICondicionInscripcion estrategia);

    PlanDeEstudio build();
}

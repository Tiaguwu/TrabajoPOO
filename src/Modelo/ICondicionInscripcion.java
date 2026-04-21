package Modelo;

public interface ICondicionInscripcion {

    boolean puedeInscribirse(Alumno alumno, Materia materia, PlanDeEstudio plan);
}

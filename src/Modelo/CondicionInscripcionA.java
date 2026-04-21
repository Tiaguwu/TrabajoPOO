package Modelo;

public class CondicionInscripcionA implements ICondicionInscripcion {

    @Override
    public boolean puedeInscribirse(Alumno alumno, Materia materia, PlanDeEstudio plan) {
        for (Materia correlativa : materia.getCorrelativas()){
            if (!alumno.tieneCursadaAprobada(correlativa)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condicion de Inscripcion A";
    }
}

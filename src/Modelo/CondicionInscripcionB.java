package Modelo;

public class CondicionInscripcionB implements ICondicionInscripcion {

    @Override
    public boolean puedeInscribirse(Alumno alumno, Materia materia, PlanDeEstudio plan) {

        for (Materia correlativa : materia.getCorrelativas()){
            if (!alumno.tieneFinalAprobado(correlativa)){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condicion de Inscripcion B";
    }
}

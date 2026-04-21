package Modelo;

import java.util.List;

public class CondicionInscripcionD implements ICondicionInscripcion {

    @Override
    public boolean puedeInscribirse(Alumno alumno, Materia materia, PlanDeEstudio plan) {
        //Lo primero es validar correlativas
        for (Materia correlativa : materia.getCorrelativas()){
            if (!alumno.tieneCursadaAprobada(correlativa)){
                return false;
            }
        }

        //Despues validar materias de 3 cuatrimestres anteriores
        //Condicion que sea mayor a 0, sino no tendria sentido
        int cuatrimestreObjetivo = materia.getCuatrimestre() - 3;

        if (cuatrimestreObjetivo > 0){
            List<Materia> todasMaterias = plan.getTodasMaterias();

            for (Materia m : todasMaterias){
                if (m.getCuatrimestre() == cuatrimestreObjetivo){
                    if (!alumno.tieneFinalAprobado(m)){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Condicion de Inscripcion D";
    }
}

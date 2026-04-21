package Modelo;

import java.util.List;

public class CondicionInscripcionE implements ICondicionInscripcion {

    @Override
    public boolean puedeInscribirse(Alumno alumno, Materia materia, PlanDeEstudio plan) {

        for (Materia finales : materia.getCorrelativas()){
            if (!alumno.tieneFinalAprobado(finales)){
                return false;
            }
        }

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
        return "Condicion de Inscripcion E";
    }
}

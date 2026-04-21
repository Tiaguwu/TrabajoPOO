package Modelo;

public class EstadoFinalAprobado implements IEstadoCursada {

    @Override
    public void evaluarParcial(Cursada cursada) {
        throw new IllegalStateException("La materia ya está aprobada y cerrada.");
    }

    @Override
    public void finalizarCursada(Cursada cursada) {
        throw new IllegalStateException("La materia ya está aprobada y cerrada.");
    }

    @Override
    public void rendirFinal(Cursada cursada) {
        throw new IllegalStateException("El alumno ya aprobó el examen final de esta materia.");

    }

    @Override
    public String getNombreEstado() {
        return "Final Aprobado";
    }
}

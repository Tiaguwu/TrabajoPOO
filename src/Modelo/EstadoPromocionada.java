package Modelo;

public class EstadoPromocionada implements IEstadoCursada {

    @Override
    public void evaluarParcial(Cursada cursada) {
        // La cursada ya terminó con éxito total
        throw new IllegalStateException("Acción denegada: La materia ya fue promocionada.");
    }

    @Override
    public void finalizarCursada(Cursada cursada) {
        // Ya pasó por aquí para llegar a este estado
        throw new IllegalStateException("Acción denegada: La cursada ya se encuentra finalizada.");
    }

    @Override
    public void rendirFinal(Cursada cursada) {
        // El gran beneficio de promocionar es justamente no rendir final
        throw new IllegalStateException("Acción denegada: Un alumno promocionado no debe rendir examen final.");
    }

    @Override
    public String getNombreEstado() {
        return "Promocionada";
    }
}

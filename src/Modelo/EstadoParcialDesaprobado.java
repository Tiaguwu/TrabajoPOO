package Modelo;

public class EstadoParcialDesaprobado implements IEstadoCursada {


    @Override
    public void evaluarParcial(Cursada cursada) {
        throw new IllegalStateException("Accion denegada: El parcial ya fue desaprobado y no hay instancias de recuperacion");
    }

    @Override
    public void finalizarCursada(Cursada cursada) {
        Alumno a = cursada.getAlumno();

        a.removerCursadaActual(cursada);

        cursada.setEstado(new EstadoCursadaDesaprobada());
    }

    @Override
    public void rendirFinal(Cursada cursada) {
        throw new IllegalStateException("Accion denegada: No se puede rendir final de una materia desaprobada.");
    }

    @Override
    public String getNombreEstado() {
        return "Parcial Desaprobado";
    }
}

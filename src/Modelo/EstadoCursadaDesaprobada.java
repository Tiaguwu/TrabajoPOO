package Modelo;

public class EstadoCursadaDesaprobada implements IEstadoCursada {

    @Override
    public void evaluarParcial(Cursada c) { throw new IllegalStateException("Cursada desaprobada. Debe recursar."); }
    @Override
    public void finalizarCursada(Cursada c) { throw new IllegalStateException("La cursada ya finalizó como desaprobada."); }
    @Override
    public void rendirFinal(Cursada c) { throw new IllegalStateException("No puede rendir final de una materia desaprobada."); }

    @Override
    public String getNombreEstado() {
        return "Desaprobada / Debe Recursar";
    }
}

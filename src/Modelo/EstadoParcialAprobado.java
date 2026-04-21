package Modelo;

public class EstadoParcialAprobado implements IEstadoCursada {

    @Override
    public void evaluarParcial(Cursada cursada) {
        throw new IllegalStateException("El parcial ya fue aprobado con " + cursada.getNotaParcial());
    }

    @Override
    public void finalizarCursada(Cursada cursada) {
        double nota = cursada.getNotaParcial();
        Alumno a = cursada.getAlumno();
        Materia m = cursada.getMateria();
        if (nota >= 7.0){

            //PARCIAL APROBADO -> PROMOCIONADA
            //Agregar a lista de cursadas regularizada
            a.agregarCursadaAprobada(m);
            //Agregar a lista y map de materia promocionada y final aprobado
            a.registrarFinalAprobado(m,nota, "PROMOCIÓN");
            a.removerCursadaActual(cursada);
            cursada.setEstado(new EstadoPromocionada());
        } else {
            //PARCIAL APROBADO -> CURSADA APROBADA
            a.agregarCursadaAprobada(m);
            cursada.setEstado(new EstadoCursadaAprobada());
        }
    }

    @Override
    public void rendirFinal(Cursada cursada) {
        throw new IllegalStateException("Acción denegada: Debe finalizar la cursada para poder rendir final.");
    }

    @Override
    public String getNombreEstado() {
        return "Parcial: Aprobado";
    }
}

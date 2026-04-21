package Modelo;

public class EstadoCursadaAprobada implements IEstadoCursada {
    @Override
    public void evaluarParcial(Cursada cursada) {
        throw new IllegalStateException("La cursada ya fue aprobada. No se puede volver a rendir el parcial.");
    }

    @Override
    public void finalizarCursada(Cursada cursada) {
        throw new IllegalStateException("La cursada ya está finalizada y aprobada.");
    }

    @Override
    public void rendirFinal(Cursada cursada) {
        double notaF = cursada.getNotaFinal();
        Alumno alumno = cursada.getAlumno();
        Materia materia = cursada.getMateria();

        if (notaF < 0 || notaF > 10) {
            throw new IllegalArgumentException("Error: La nota " + notaF + " es inválida. Debe estar entre 0 y 10.");
        }

        if(notaF >= 4.0){
            alumno.registrarFinalAprobado(materia,notaF, "EXAMEN FINAL REGULAR");
            alumno.removerCursadaActual(cursada);
            cursada.setEstado(new EstadoFinalAprobado());
        } else {
            throw new IllegalStateException("Final desaprobado con " + notaF + ". El alumno debe volver a rendir.");
        }
    }

    @Override
    public String getNombreEstado() {
        return "Cursada Aprobada (Pendiente de final)";
    }
}

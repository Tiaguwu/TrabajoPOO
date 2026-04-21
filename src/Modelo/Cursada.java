package Modelo;

public class Cursada {

    private Alumno alumno;
    private Materia materia;
    private IEstadoCursada estado;
    private double notaParcial;
    private double notaFinal;

    public Cursada(Alumno alumno, Materia materia){
        this.alumno = alumno;
        this.materia = materia;
        this.estado = new EstadoInscripto();
    }

    public void finalizarCursada(){
        estado.finalizarCursada(this);
    }

    public void rendirFinal(double notaFinal){
        this.notaFinal = notaFinal;
        estado.rendirFinal(this);
    }

    public void evaluarParcial(double nota){
        this.notaParcial = nota;
        estado.evaluarParcial(this);
    }

    public void setEstado(IEstadoCursada nuevoEstado){
        this.estado = nuevoEstado;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public Materia getMateria() {
        return materia;
    }

    public String getNombreEstado(){
        return estado.getNombreEstado();
    }

    public double getNotaParcial() {
        return notaParcial;
    }

    public void setNotaParcial(double notaParcial) {
        this.notaParcial = notaParcial;
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
    }

    @Override
    public String toString() {
        return this.materia.getNombre();
    }
}

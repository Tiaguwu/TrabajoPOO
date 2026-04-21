package Modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Alumno {
    private String legajo;
    private String nombre;

    private List<Carrera> carrerasInscriptas;

    private List<Materia> finalesAprobados;
    private List<Materia> cursadasAprobadas;

    private List<Cursada> cursadasActuales;

    private Map<Materia, ResultadoFinal> notasFinales;

    public Alumno(String legajo, String nombre){
        this.legajo = legajo;
        this.nombre = nombre;
        this.carrerasInscriptas = new ArrayList<>();
        this.finalesAprobados = new ArrayList<>();
        this.cursadasAprobadas = new ArrayList<>();
        this.cursadasActuales = new ArrayList<>();
        this.notasFinales = new HashMap<>();
    }

    public void inscribirACarrera(Carrera carrera) {
        if (carrera != null && !carrerasInscriptas.contains(carrera)) {
            carrerasInscriptas.add(carrera);
        }
    }

    public List<Carrera> getCarrerasInscriptas() {
        return carrerasInscriptas;
    }

    public void inscribirMateria(Materia m ){
        if (!estaCursando(m)){
            Cursada nuevaCursada = new Cursada(this, m);
            this.cursadasActuales.add(nuevaCursada);
        }
    }

    public boolean tieneFinalAprobado(Materia m){
        return finalesAprobados.contains(m);
    }

    public boolean tieneCursadaAprobada(Materia m){
        return cursadasAprobadas.contains(m) || finalesAprobados.contains(m) ;
    }

    public boolean estaCursando(Materia m) {
        for (Cursada c : cursadasActuales) {
            if (c.getMateria().equals(m)){
                return true;
            }
        }
        return false;
    }

    public List<Materia> getFinalesAprobados() {
        return finalesAprobados;
    }

    public void agregarCursadaAprobada(Materia m){
        if (!cursadasAprobadas.contains(m) && !finalesAprobados.contains(m)){
            cursadasAprobadas.add(m);
        }
    }

    public void registrarFinalAprobado(Materia m, double nota, String condicion){
        if (!finalesAprobados.contains(m)) {
            finalesAprobados.add(m);
            cursadasAprobadas.remove(m);
            notasFinales.put(m, new ResultadoFinal(nota,condicion));
        }
    }

    public ResultadoFinal getResultadoDeMateria(Materia m) {
        return notasFinales.get(m);
    }

    public void removerCursadaActual(Cursada c){
        cursadasActuales.remove(c);
    }

    public double getNotaFinal(Materia m) {
        ResultadoFinal res = notasFinales.get(m);

        if (res != null) {
            return res.getNota();
        } else {
            return 0.0;
        }
    }

    public List<Cursada> getCursadasActuales() {
        return cursadasActuales;
    }

    public ResultadoFinal getResultadoDetallado(Materia m) {
        return notasFinales.get(m); // Devuelve el objeto con Nota + Condición
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return this.nombre + " (Legajo: " + this.legajo + ")";
    }
}


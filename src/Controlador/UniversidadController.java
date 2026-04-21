package Controlador;

import Modelo.*;

import java.util.List;

public class UniversidadController {
    private Universidad universidad;

    public UniversidadController(Universidad universidad){
        this.universidad = universidad;
    }

    public void crearCarrera(String nombre, PlanDeEstudio plan){
        Carrera carrera = new Carrera(nombre,plan);
        universidad.agregarCarrera(carrera);
    }

    public void registrarAlumno(String legajo, String nombre){
        Alumno alumno = new Alumno(legajo,nombre);
        universidad.agregarAlumno(alumno);
    }

    public List<Materia> obtenerMateriasHabilitadas (Alumno alumno, Carrera carrera, int periodo){
        return universidad.obtenerMateriasHabilitadas(alumno,carrera,periodo);
    }

    public void inscribirAlumnoACarrera(Alumno alumno, Carrera carrera){
        if (alumno != null && carrera != null) {
            alumno.inscribirACarrera(carrera);
        }
    }

    public String inscribirAlumnoEnMateria(Alumno alumno, Materia materia, Carrera carrera){
        try {
            universidad.inscribirAlumnoAMateria(alumno,materia,carrera);
            return "Inscripción confirmada en " + materia.getNombre();
        } catch (IllegalStateException e){
            return e.getMessage();
        }
    }

    public void cargarNotaParcial(Cursada cursada, double nota){
        cursada.evaluarParcial(nota);
    }

    public void registrarFinal(Cursada cursada, double nota){
        cursada.rendirFinal(nota);
    }

    public boolean verificarEgreso(Alumno alumno, Carrera carrera){
        return universidad.verificarGraduacion(alumno,carrera);
    }

    public List<Carrera> getCarreras(){
        return universidad.getCarreras();
    }

    public List<Alumno> getAlumnos(){
        return universidad.getAlumnos();
    }
}

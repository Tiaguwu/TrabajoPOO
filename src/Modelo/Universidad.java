package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Universidad {
    private List<Carrera> carreras;
    private List<Alumno> alumnos;

    public Universidad(){
        this.alumnos = new ArrayList<>();
        this.carreras = new ArrayList<>();
    }

    // 1. Agregar carreras
    public void agregarCarrera(Carrera carrera){
        this.carreras.add(carrera);
    }

    // 3. Inscripción de alumnos a la universidad
    public void agregarAlumno(Alumno alumno){
        this.alumnos.add(alumno);
    }

    // 6. Informar materias habilitadas
    public List<Materia> obtenerMateriasHabilitadas(Alumno alumno, Carrera carrera, int cuatriDeseado){

        List<Materia> habilitadas = new ArrayList<>();
        PlanDeEstudio plan = carrera.getPlan();
        ICondicionInscripcion estrategia = plan.getEstrategia();

        for (Materia m : carrera.getPlan().getTodasMaterias()){

            if((m.getCuatrimestre() % 2) == (cuatriDeseado % 2)) {


                //Filtro de que no la tenga aprobada o no este cursando
                if (!alumno.tieneFinalAprobado(m) && !alumno.estaCursando(m)) {

                    //Usar strategy de la condicion
                    if (estrategia.puedeInscribirse(alumno, m, plan)) {
                        habilitadas.add(m);
                    }
                }
            }
        }
        return habilitadas;
    }

    // 5. Inscribir si puede cursar
    public void inscribirAlumnoAMateria(Alumno alumno, Materia materia, Carrera carrera){
        PlanDeEstudio plan = carrera.getPlan();

        if (plan.getEstrategia().puedeInscribirse(alumno,materia,plan)){
            alumno.inscribirMateria(materia);
        } else {
            throw new IllegalStateException("Requisitos no cumplidos para " + materia.getNombre());
        }
    }

    // 7. Verificar si termino la carrera
    public boolean verificarGraduacion(Alumno alumno, Carrera carrera){
        return carrera.verificarEgreso(alumno);
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public List<Carrera> getCarreras() {
        return carreras;
    }
}

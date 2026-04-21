package Modelo;

public class Carrera {
    private String nombre;
    private PlanDeEstudio plan;

    public Carrera(String nombre, PlanDeEstudio plan){
        this.nombre = nombre;
        this.plan = plan;
    }

    public String getNombre() {
        return nombre;
    }

    public PlanDeEstudio getPlan() {
        return plan;
    }

    public boolean verificarEgreso(Alumno alumno){
        PlanDeEstudio p = this.getPlan();
        int aprobadas = 0;

        for(Materia m : p.getObligatorias()){
            if (!alumno.tieneFinalAprobado(m)){
                return false;
            }
        }

        for (Materia m : p.getOptativas()){
            if (alumno.tieneFinalAprobado(m)){
                aprobadas++;
            }
        }

        return aprobadas >= p.getCantidadOptativas();
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}

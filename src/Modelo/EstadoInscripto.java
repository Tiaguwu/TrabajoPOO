package Modelo;

public class EstadoInscripto implements IEstadoCursada {

    @Override
    public void evaluarParcial(Cursada cursada) {

        if (cursada.getNotaParcial() < 0 || cursada.getNotaParcial() > 10) {
            throw new IllegalArgumentException("Error: La nota " + cursada.getNotaParcial() + " es inválida. Debe estar entre 0 y 10.");
        }

        if (cursada.getNotaParcial() >= 4.0){
            // INSCRIPTO -> PARCIAL APROBADO
            cursada.setEstado(new EstadoParcialAprobado());
        } else {
            //INSCRIPTO -> PARCIAL DESAPROBADO
            cursada.setEstado(new EstadoParcialDesaprobado());
        }
    }

    @Override
    public void finalizarCursada(Cursada cursada) {
        //Exception que va a agarrar el controlador en el MVC
        throw new IllegalStateException("No se puede finalizar: el parcial aún no fue evaluado.");
    }

    @Override
    public void rendirFinal(Cursada cursada) {
        //Exception que va a agarrar el controlador en el MVC
        throw new IllegalStateException("No se puede rendir final: el alumno solo está inscripto.");
    }

    @Override
    public String getNombreEstado() {
        return "Inscripto";
    }
}

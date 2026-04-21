package Modelo;

public interface IEstadoCursada {

    //Transicion de parcial
    void evaluarParcial(Cursada cursada);

    //Cierre de cursada
    void finalizarCursada(Cursada cursada); //Para regularizar la materia

    //Transicion de final
    void rendirFinal(Cursada cursada);

    String getNombreEstado();
}

package Modelo;

public class ResultadoFinal {
    private double nota;
    private String condicion; // Para identificar si es final regular o promocion

    public ResultadoFinal(double nota, String condicion) {
        this.nota = nota;
        this.condicion = condicion;
    }

    public double getNota() { return nota; }
    public String getCondicion() { return condicion; }

    @Override
    public String toString() {
        return nota + " (" + condicion + ")";
    }
}

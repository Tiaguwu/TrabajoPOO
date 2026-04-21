package Vista;

import Controlador.UniversidadController;
import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

 /*
    Panel que muestra la historia academica de un alumno y una carrera.
    Tambien muestra el promedio y estado de la carrera.
 */

public class PanelHistorial extends JPanel {

    private UniversidadController controlador;

    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<Carrera> comboCarreras;

    private JTextArea areaHistorial;

    private JLabel lblEstadoEgreso;
    private JLabel lblFotoEgreso;

    public PanelHistorial(UniversidadController controlador){
        this.controlador = controlador;

        setLayout(new BorderLayout(15,15));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //ARRIBA: Seleccion de alumno y carrera
        JPanel panelBusqueda = new JPanel(new GridLayout(2,2,10,10));
        panelBusqueda.setBorder(BorderFactory.createTitledBorder("Seleccionar Alumno y Carrera"));

        panelBusqueda.add(new JLabel("Alumno:"));
        comboAlumnos = new JComboBox<>();
        panelBusqueda.add(comboAlumnos);

        panelBusqueda.add(new JLabel("Carrera a consultar:"));
        comboCarreras = new JComboBox<>();
        panelBusqueda.add(comboCarreras);

        add(panelBusqueda, BorderLayout.NORTH);

        //CENTRO: Todas las materias del plan de estudio con su calificacion y estado, junto a un promedio general
        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Monospaced", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(areaHistorial);
        scroll.setBorder(BorderFactory.createTitledBorder("Detalle de Cursadas y Calificaciones"));
        add(scroll, BorderLayout.CENTER);

        //ABAJO: Estado de la  carrera
        JPanel panelEgreso = new JPanel();
        panelEgreso.setLayout(new BoxLayout(panelEgreso, BoxLayout.Y_AXIS)); // Apilado vertical
        panelEgreso.setBackground(new Color(236, 240, 241));
        panelEgreso.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        //Imagen en el estado de la carrera
        lblFotoEgreso = new JLabel();
        lblFotoEgreso.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEgreso.add(lblFotoEgreso);

        panelEgreso.add(Box.createVerticalStrut(10)); // Espacio entre foto y texto


        lblEstadoEgreso = new JLabel("ESTADO: SELECCIONE UN ALUMNO");
        lblEstadoEgreso.setFont(new Font("Arial", Font.BOLD, 18));
        lblEstadoEgreso.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelEgreso.add(lblEstadoEgreso);

        add(panelEgreso, BorderLayout.SOUTH);

        // --- LOGICA ---
        comboAlumnos.addActionListener(e -> actualizarCarrerasDeAlumno());
        comboCarreras.addActionListener(e -> mostrarHistorialYVerificarEgreso());
    }

    //Para que tenga los dats actualizados del controlador
    private void actualizarCarrerasDeAlumno(){
        comboCarreras.removeAllItems();
        Alumno a = (Alumno) comboAlumnos.getSelectedItem();
        if (a != null){
            for (Carrera c : a.getCarrerasInscriptas()){
                comboCarreras.addItem(c);
            }
        }
    }

    public void actualizarCombos(){
        comboAlumnos.removeAllItems();
        for (Alumno a : controlador.getAlumnos()){
            comboAlumnos.addItem(a);
        }
        // Limpiamos visuales al resetear
        lblFotoEgreso.setIcon(null);
        lblEstadoEgreso.setText("ESTADO: CONSULTANDO...");
    }

    //Dinamica del funcionamiento
    private void mostrarHistorialYVerificarEgreso(){
        Alumno a = (Alumno) comboAlumnos.getSelectedItem();
        Carrera c = (Carrera) comboCarreras.getSelectedItem();

        if (a == null || c == null){
            areaHistorial.setText("");
            lblFotoEgreso.setIcon(null);
            return;
        }

        PlanDeEstudio plan = c.getPlan();
        areaHistorial.setText("");

        areaHistorial.append("ALUMNO: " + a.getNombre() + "\n");
        areaHistorial.append("CARRERA: " + c.getNombre() + "\n");
        areaHistorial.append("-".repeat(71) + "\n");
        areaHistorial.append(String.format("%-48s | %-12s | %-10s\n", "MATERIA", "ESTADO", "NOTA"));
        areaHistorial.append("-".repeat(71) + "\n");

        List<Materia> todas = plan.getTodasMaterias();
        double sumaNotas = 0;
        int materiasAprobadasCont = 0;

        //Configuracion del detalle de la cursada de las materias
        for (Materia m : todas){
            String nombre = m.getNombre();
            String estado = "No inscripto";
            String nota = "-";

            if (a.tieneFinalAprobado(m)){
                estado = "APROBADA";
                double notaFinal = a.getNotaFinal(m);
                nota = String.valueOf(notaFinal);
                sumaNotas += notaFinal;
                materiasAprobadasCont++;
            } else if (a.tieneCursadaAprobada(m)) {
                estado = "REGULAR";
            } else if (a.estaCursando(m)) {
                for (Cursada cur : a.getCursadasActuales()){
                    if (cur.getMateria().equals(m)){
                        estado = cur.getNombreEstado();
                        break;
                    }
                }
            }
            areaHistorial.append(String.format("%-48s | %-12s | %-5s\n", nombre, estado, nota));
        }

        double promedio = (materiasAprobadasCont > 0) ? sumaNotas / materiasAprobadasCont : 0;

        areaHistorial.append("-".repeat(71) + "\n");
        areaHistorial.append(String.format("PROMEDIO GENERAL: %.2f\n", promedio));
        areaHistorial.append("-".repeat(71) + "\n");

        if (c.verificarEgreso(a)){
            lblEstadoEgreso.setText("¡ALUMNO EGRESADO! :D");
            lblEstadoEgreso.setForeground(new Color(39,174,96));
            lblFotoEgreso.setIcon(crearIcono("/iconos/Egresado.png", 80, 80));
        } else {
            lblEstadoEgreso.setText("ESTADO: EN CARRERA");
            lblFotoEgreso.setIcon(crearIcono("/iconos/Estudiando.png", 80, 80));
            lblEstadoEgreso.setForeground(Color.RED);
        }
    }

    //Funcion para llamar a los iconos en base sea necesario el estado que se encuentra
    private Icon crearIcono(String ruta, int ancho, int largo){
        try {
            java.net.URL imgURL = getClass().getResource(ruta);
            if (imgURL != null) {
                ImageIcon imagenOriginal = new ImageIcon(imgURL);
                Image imagenEscala = imagenOriginal.getImage().getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
                return new ImageIcon(imagenEscala);
            }
            return null;
        } catch (Exception e) {
            System.err.println("Error al crear el icono " + ruta);
            return null;
        }
    }
}

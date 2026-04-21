package Vista;

import Controlador.UniversidadController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private UniversidadController controlador;
    private JTabbedPane pestañas;

    private PanelAdministracion panelAdmin;
    private PanelAlumnos panelAlumnos;
    private PanelNotas panelNotas;
    private PanelMateria panelMateria;
    private PanelHistorial panelHistorial;

    public VentanaPrincipal (UniversidadController controlador){
        this.controlador = controlador;

        setTitle("Sistema de Gestión Académica - Universidad");
        setSize(1000,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //Para centrar la ventana

        setLayout(new BorderLayout());

        //ENCABEZADO
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(72, 52, 117));
        JLabel lblTitulo = new JLabel("GESTION UNIVERSITARIA");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        panelHeader.add(lblTitulo);
        add(panelHeader, BorderLayout.NORTH);


        Icon iconAdmin = crearIcono("/iconos/admin.png", 20, 20);
        Icon iconAlumnos = crearIcono("/iconos/alumnos.png", 20, 20);
        Icon iconMaterias = crearIcono("/iconos/materias.png", 20, 20);
        Icon iconNotas = crearIcono("/iconos/notas.png", 20, 20);
        Icon iconHistorial = crearIcono("/iconos/historial.png", 20, 20);

        panelAdmin = new PanelAdministracion(controlador);
        panelAlumnos = new PanelAlumnos(controlador);
        panelNotas = new PanelNotas(controlador);
        panelMateria = new PanelMateria(controlador);
        panelHistorial = new PanelHistorial(controlador);

        //CONTENEDOR DE PESTAÑAS
        pestañas = new JTabbedPane();
        pestañas.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        //PESTAÑAS
        pestañas.addTab("Administración", iconAdmin, panelAdmin);
        pestañas.addTab("Alumnos e Inscripción", iconAlumnos, panelAlumnos);
        pestañas.addTab("Inscripción de materia", iconMaterias, panelMateria);
        pestañas.addTab("Gestión de Notas", iconNotas, panelNotas);
        pestañas.addTab("Historial y Títulos", iconHistorial, panelHistorial);

        pestañas.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int seleccionada = pestañas.getSelectedIndex();
                switch (seleccionada){
                    case 0:
                        break;
                    case 1:
                        panelAlumnos.actualizarCombos();
                        break;
                    case 2:
                        panelMateria.actualizarCombos();
                        break;
                    case 3:
                        panelNotas.actualizarCombos();
                        break;
                    case 4:
                        panelHistorial.actualizarCombos();
                        break;
                }
            }
        });

        add(pestañas, BorderLayout.CENTER);

        //PIE DE PAGINA
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelFooter.add(new JLabel("Trabajo Final Integrador POO"));
        add(panelFooter, BorderLayout.SOUTH);
    }

    private JPanel crearPanelTemporal(String texto) {
        JPanel p = new JPanel(new GridBagLayout());
        p.add(new JLabel(texto));
        return p;
    }


    private Icon crearIcono(String ruta, int ancho, int largo){
        try {
            ImageIcon imagenOriginal = new ImageIcon(getClass().getResource(ruta));
            Image imagenEscala = imagenOriginal.getImage().getScaledInstance(ancho, largo, Image.SCALE_SMOOTH);
            return new ImageIcon(imagenEscala);
        } catch (Exception e) {
            System.err.println("Error al crear el icono " + ruta);
            return null;
        }
    }

    public void mostrar() {
        setVisible(true);
    }

}

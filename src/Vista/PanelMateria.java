package Vista;

import Controlador.UniversidadController;
import Modelo.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

 /*
    Panel para la inscripcion a materias
 */
public class PanelMateria extends JPanel {

    private UniversidadController controlador;

    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<Carrera> comboCarreras;

    private DefaultListModel<Materia> modeloListaMateria;
    private JList<Materia> listaMateriaHabilitadas;
    private JComboBox<String> comboPeriodo;

    public PanelMateria(UniversidadController controlador){

        this.controlador = controlador;
        this.modeloListaMateria = new DefaultListModel<>();

        setLayout(new BorderLayout(15,15));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //SELECCION ALUMNO Y CARRERA
        JPanel panelNorte = new JPanel(new GridLayout(3,2,10,10));
        panelNorte.setBorder(BorderFactory.createTitledBorder("1. Identificación"));

        panelNorte.add(new JLabel("Alumno:"));
        comboAlumnos = new JComboBox<>();
        panelNorte.add(comboAlumnos);

        panelNorte.add(new JLabel("Carrera:"));
        comboCarreras = new JComboBox<>();
        panelNorte.add(comboCarreras);

        comboPeriodo = new JComboBox<>(new String[]{"1° Cuatrimestre", "2° Cuatrimestre"});
        panelNorte.add(new JLabel("Periodo:"));
        panelNorte.add(comboPeriodo);


        add(panelNorte,BorderLayout.NORTH);

        //MATERIAS HABILITADAS
        listaMateriaHabilitadas = new JList<>(modeloListaMateria);
        JScrollPane scroll = new JScrollPane(listaMateriaHabilitadas);
        scroll.setBorder(BorderFactory.createTitledBorder("2. Materias Disponibles"));
        add(scroll, BorderLayout.CENTER);

        JButton btnInscribir = new JButton("Confirmar Inscripción a Materia");
        btnInscribir.setPreferredSize(new Dimension(0,50));
        btnInscribir.setBackground(new Color(72, 52, 117));
        btnInscribir.setForeground(Color.WHITE);
        add(btnInscribir, BorderLayout.SOUTH);

        comboAlumnos.addActionListener(e -> actualizarCarrerasDelAlumno());
        comboCarreras.addActionListener(e -> actualizarMaterias());
        comboPeriodo.addActionListener(e -> actualizarMaterias());

        //Inscribir al alumno a la materia seleccionada
        btnInscribir.addActionListener(e -> {
            Alumno a = (Alumno) comboAlumnos.getSelectedItem();
            Carrera c = (Carrera) comboCarreras.getSelectedItem();
            Materia m = listaMateriaHabilitadas.getSelectedValue();

            if(a != null &&  c != null && m != null){
                String resultado = controlador.inscribirAlumnoEnMateria(a,m,c);
                JOptionPane.showMessageDialog(this,resultado);
                actualizarMaterias();
            } else {
                JOptionPane.showMessageDialog(this,"Debe seleccionar un alumno, carrera y materia.");
            }
        });
    }

    //Para tener los datos actualizadso
    public void actualizarCombos(){
        comboAlumnos.removeAllItems();
        for (Alumno a : controlador.getAlumnos()){
            comboAlumnos.addItem(a);
        }
    }

    private void actualizarCarrerasDelAlumno(){
        comboCarreras.removeAllItems();
        Alumno seleccionado = (Alumno) comboAlumnos.getSelectedItem();

        if (seleccionado != null){
            for (Carrera c : seleccionado.getCarrerasInscriptas()){
                comboCarreras.addItem(c);
            }
        }
    }

    private void actualizarMaterias(){

        modeloListaMateria.clear();

        Alumno a = (Alumno) comboAlumnos.getSelectedItem();
        Carrera c = (Carrera) comboCarreras.getSelectedItem();

        int periodoSeleccionado = comboPeriodo.getSelectedIndex() + 1;

        if (a != null && c !=null){
            List<Materia> habilitadasa = controlador.obtenerMateriasHabilitadas(a,c,periodoSeleccionado);
            for (Materia m : habilitadasa){
                modeloListaMateria.addElement(m);
            }
        }
    }


}

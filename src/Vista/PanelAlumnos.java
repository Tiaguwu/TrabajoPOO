package Vista;

import Controlador.UniversidadController;
import Modelo.Alumno;
import Modelo.Carrera;

import javax.swing.*;
import java.awt.*;

 /*
    Panel de gestion de alumnos.
    Creacion de alumnos e inscripcion a materias.
 */

public class PanelAlumnos extends JPanel {

    private UniversidadController controlador;

    private JTextField txtNombre, txtLegajo;

    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<Carrera> comboCarreras;

    //Manejar datos de forma dinamica
    private DefaultComboBoxModel<Alumno> modeloComboAlumnos;
    private DefaultComboBoxModel<Carrera> modeloComboCarreras;

    public PanelAlumnos(UniversidadController controlador){
        this.controlador = controlador;
        this.modeloComboAlumnos = new DefaultComboBoxModel<>();
        this.modeloComboCarreras = new DefaultComboBoxModel<>();

        //Configuracion del planel principal
        setLayout(new GridLayout(1,2,20,0));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //COLUMNA IZQ: Registro de alumno
        JPanel panelRegistro = new JPanel();
        panelRegistro.setLayout(new BoxLayout(panelRegistro,BoxLayout.Y_AXIS));
        panelRegistro.setBorder(BorderFactory.createTitledBorder("1. Registrar Nuevo Alumno"));

        panelRegistro.add(Box.createVerticalStrut(15)); //ESPACIO

        // NOMBRE
        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT); //Lo pega a la izq
        panelRegistro.add(lblNombre);

        panelRegistro.add(Box.createVerticalStrut(5));
        txtNombre = new JTextField();
        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        txtNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRegistro.add(txtNombre);

        panelRegistro.add(Box.createVerticalStrut(15));

        // LEGAJO
        JLabel lblLegajo = new JLabel("Legajo:");
        lblLegajo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRegistro.add(lblLegajo);

        panelRegistro.add(Box.createVerticalStrut(5));
        txtLegajo = new JTextField();
        txtLegajo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); //Evita que el campo sea demasiado alto, lo limita
        txtLegajo.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelRegistro.add(txtLegajo);

        panelRegistro.add(Box.createVerticalStrut(25));

        JButton btnRegistrar = new JButton("Registrar en sistema");
        btnRegistrar.setBackground(new Color(72, 52, 117));
        btnRegistrar.setForeground(Color.WHITE);

        //Panel para el boton porque quedaba mal posicionado si se lo agrega de forma comun
        JPanel panelContenedorRegistrar = new JPanel();
        panelContenedorRegistrar.setLayout(new BoxLayout(panelContenedorRegistrar, BoxLayout.X_AXIS));
        panelContenedorRegistrar.setOpaque(false);
        panelContenedorRegistrar.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelContenedorRegistrar.add(Box.createHorizontalGlue()); // Vacio a la izq
        panelContenedorRegistrar.add(btnRegistrar);               // El botón queda en medio
        panelContenedorRegistrar.add(Box.createHorizontalGlue()); // Vacio a la der

        panelRegistro.add(panelContenedorRegistrar);

        //COLUMNA DER: Inscripcion a carrera
        JPanel panelInscripcion = new JPanel();
        panelInscripcion.setLayout(new BoxLayout(panelInscripcion, BoxLayout.Y_AXIS));
        panelInscripcion.setBorder(BorderFactory.createTitledBorder("2. Inscribir Alumno a Carrera"));

        panelInscripcion.add(Box.createVerticalStrut(15));

        //ALUMNO
        JLabel lblAlumno = new JLabel("Seleccionar Alumno:");
        lblAlumno.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- ESTO ES CLAVE
        panelInscripcion.add(lblAlumno);

        panelInscripcion.add(Box.createVerticalStrut(5));

        comboAlumnos = new JComboBox<>(modeloComboAlumnos);
        comboAlumnos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboAlumnos.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- ESTO ES CLAVE
        panelInscripcion.add(comboAlumnos);

        panelInscripcion.add(Box.createVerticalStrut(15));

        //CARRERA
        JLabel lblCarrera = new JLabel("Seleccionar Carrera:");
        lblCarrera.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- AGREGADO (Faltaba este)
        panelInscripcion.add(lblCarrera);

        panelInscripcion.add(Box.createVerticalStrut(5));
        comboCarreras = new JComboBox<>(modeloComboCarreras);
        comboCarreras.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        comboCarreras.setAlignmentX(Component.LEFT_ALIGNMENT); // <-- AGREGADO (Faltaba este)
        panelInscripcion.add(comboCarreras);

        panelInscripcion.add(Box.createVerticalStrut(25));

        JButton btnInscribir = new JButton("Inscribir a carrera");
        btnInscribir.setBackground(new Color(72, 52, 117));
        btnInscribir.setForeground(Color.WHITE);

        JPanel panelContenedoInscribir = new JPanel();
        panelContenedoInscribir.setLayout(new BoxLayout(panelContenedoInscribir, BoxLayout.X_AXIS));
        panelContenedoInscribir.setOpaque(false);
        panelContenedoInscribir.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelContenedoInscribir.add(Box.createHorizontalGlue()); // Vacio a la izq
        panelContenedoInscribir.add(btnInscribir);               // El botón queda en medio
        panelContenedoInscribir.add(Box.createHorizontalGlue()); // Vacio a la der

        panelInscripcion.add(panelContenedoInscribir);

        add(panelRegistro);
        add(panelInscripcion);

        //LOGICA
        //Registrar el alumno
        btnRegistrar.addActionListener(e -> {
            String nom = txtNombre.getText().trim();
            String leg = txtLegajo.getText().trim();
            if (!nom.isEmpty() && !leg.isEmpty()){
                controlador.registrarAlumno(leg,nom);
                JOptionPane.showMessageDialog(this,"Alumno registrado exitosamente.");
                txtNombre.setText("");
                txtLegajo.setText("");
                actualizarCombos();
            }
        });

        //Inscribir a la carrera
        btnInscribir.addActionListener(e -> {
            Alumno seleccionado = (Alumno) comboAlumnos.getSelectedItem();
            Carrera carrera = (Carrera) comboCarreras.getSelectedItem();

            if(seleccionado != null && carrera != null ) {
                if (seleccionado.getCarrerasInscriptas().contains(carrera)) {
                    JOptionPane.showMessageDialog(this,
                            "El alumno " + seleccionado.getNombre() + " ya está inscripto en " + carrera.getNombre(),
                            "Inscripción Duplicada",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    controlador.inscribirAlumnoACarrera(seleccionado, carrera);
                    JOptionPane.showMessageDialog(this, seleccionado.getNombre() + " ahora cursa " + carrera.getNombre());
                }
            }
        });
    }

    //Refresca la info de los combobox con los datos actualizados del controlador
    public void actualizarCombos(){
        modeloComboAlumnos.removeAllElements();
        for (Alumno a : controlador.getAlumnos()){
            modeloComboAlumnos.addElement(a);
        }

        modeloComboCarreras.removeAllElements();
        for (Carrera c : controlador.getCarreras()){
            modeloComboCarreras.addElement(c);
        }
    }


}

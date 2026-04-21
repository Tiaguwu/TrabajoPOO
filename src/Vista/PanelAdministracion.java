package Vista;

import Controlador.UniversidadController;
import Modelo.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

  /*
    Panel para la configuracion de carreras y su planes de estudio.
    Crea materias y asiga correlativas.
 */

public class PanelAdministracion extends JPanel {

    private UniversidadController controlador;
    private PlanDeEstudioBuilder builderTemporal; //PATRON BUILDER
    private DefaultListModel<String> modeloListaMaterias; //Lista de materias del plan

    private JComboBox<ICondicionInscripcion> comboEstrategias; //PATRON STRATEGY

    private List<Materia> materiasCreadasParaElPlan;

    //CAMPOS DE TEXTO Y SELECCION
    private JTextField txtNombreCarrera, txtNombreMateria, txtCodigoMateria;
    private JCheckBox chkEsOpcional;
    private JSpinner spinnerCargaHoraria, spinnerCuatrimestre, spinnerOptativasreq;

    //PARA LAS CORRELATIVAS
    private JList<Materia> listaPosiblesCorrelativas;
    private DefaultListModel<Materia> modeloPosibleCorrelativas;

    public PanelAdministracion(UniversidadController controlador){
        this.controlador = controlador;
        this.builderTemporal = new PlanDeEstudioBuilder();
        this.modeloListaMaterias = new DefaultListModel<>();
        this.materiasCreadasParaElPlan = new ArrayList<>();
        this.modeloPosibleCorrelativas = new DefaultListModel<>();
        comboEstrategias = new JComboBox<>();

        //CONFIG DEL PANEL PRINCIPAL
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        //ARRIBA: DATOS DE LA CARRERA
        JPanel panelNorte = new JPanel(new GridLayout(3,2,5,5));
        panelNorte.setBorder(BorderFactory.createTitledBorder("1. Datos Generales de la Carrera"));

        panelNorte.add(new JLabel("Nombre de la Carrera:"));
        txtNombreCarrera = new JTextField();
        panelNorte.add(txtNombreCarrera);

        panelNorte.add(new JLabel("Cant. Optativas Requeridas:"));
        spinnerOptativasreq = new JSpinner(new SpinnerNumberModel(0, 0 , 10,1));
        panelNorte.add(spinnerOptativasreq);

        //Se cargan las condiciones de inscripcion.
        comboEstrategias.addItem(new CondicionInscripcionA());
        comboEstrategias.addItem(new CondicionInscripcionB());
        comboEstrategias.addItem(new CondicionInscripcionC());
        comboEstrategias.addItem(new CondicionInscripcionD());
        comboEstrategias.addItem(new CondicionInscripcionE());

        panelNorte.add(new JLabel("Condición de Inscripción:"));
        panelNorte.add(comboEstrategias);

        add(panelNorte, BorderLayout.NORTH);

        //CENTRO: ARMADO DE PLAN
        JPanel panelCentro = new JPanel(new GridLayout(1, 3, 15, 0));

        //1ER COLUMNA: Crear materia
        JPanel panelDatosMateria = new JPanel();
        panelDatosMateria.setLayout(new BoxLayout(panelDatosMateria,BoxLayout.Y_AXIS));
        panelDatosMateria.setBorder(BorderFactory.createTitledBorder("2. Nueva Materia"));

        panelDatosMateria.add(Box.createVerticalStrut(15)); //DISTANCIA

        //NOMBRE
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(lblNombre);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        txtNombreMateria = crearTextField();
        txtNombreMateria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(txtNombreMateria);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        //CODIGO
        JLabel lblCodigoMateria = new JLabel("Codigo:");
        lblCodigoMateria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(lblCodigoMateria);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        txtCodigoMateria = crearTextField();
        txtNombreMateria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(txtCodigoMateria);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        //CARGA HORARIA
        JLabel lblCarga = new JLabel("Carga Horaria:");
        lblCarga.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(lblCarga);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        spinnerCargaHoraria = new JSpinner(new SpinnerNumberModel(60,60,165,5));
        configurarComponente(spinnerCargaHoraria);
        spinnerCargaHoraria.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(spinnerCargaHoraria);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        //CUATRIMESTRE
        JLabel lblCuatrimestre =  new JLabel("Cuatrimestre:");
        lblCuatrimestre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(lblCuatrimestre);
        panelDatosMateria.add(Box.createVerticalStrut(5)); //DISTANCIA

        spinnerCuatrimestre = new JSpinner(new SpinnerNumberModel(1,1,12,1));
        configurarComponente(spinnerCuatrimestre);
        spinnerCuatrimestre.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(spinnerCuatrimestre);
        panelDatosMateria.add(Box.createVerticalStrut(10)); //DISTANCIA

        //OPCIONAL
        chkEsOpcional = new JCheckBox("Es opcional");
        chkEsOpcional.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelDatosMateria.add(chkEsOpcional);
        panelDatosMateria.add(Box.createVerticalStrut(10)); //DISTANCIA


        //2DA COLUMNA: Seleccion de correlativas
        JPanel panelCorrelativas = new JPanel(new BorderLayout());
        panelCorrelativas.setBorder(BorderFactory.createTitledBorder("3. Seleccionar Correlativas"));
        listaPosiblesCorrelativas = new JList<>(modeloPosibleCorrelativas);
        listaPosiblesCorrelativas.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        panelCorrelativas.add(new JScrollPane(listaPosiblesCorrelativas), BorderLayout.CENTER);
        panelCorrelativas.add(new JLabel("Ctrl+Click para varias"), BorderLayout.SOUTH);

        //3ER COLUMNA: VISTA PREVIA DEL PLAN
        JList<String> listaPlanFinal = new JList<>(modeloListaMaterias);
        JScrollPane scrollPlan = new JScrollPane(listaPlanFinal);
        scrollPlan.setBorder(BorderFactory.createTitledBorder("4. Plan de Estudio Actual"));

        panelCentro.add(panelDatosMateria);
        panelCentro.add(panelCorrelativas);
        panelCentro.add(scrollPlan);
        add(panelCentro,BorderLayout.CENTER);

        //BOTON AGREGAR MATERIA
        JButton btnAgregar = new JButton("Añadir Materia al Plan");
        btnAgregar.setBackground(new Color(72, 52, 117));
        btnAgregar.setForeground(Color.WHITE);

        JPanel panelContenedorAgregar = new JPanel();
        panelContenedorAgregar.setLayout(new BoxLayout(panelContenedorAgregar, BoxLayout.X_AXIS));
        panelContenedorAgregar.setOpaque(false);
        panelContenedorAgregar.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelContenedorAgregar.add(Box.createHorizontalGlue());
        panelContenedorAgregar.add(btnAgregar);
        panelContenedorAgregar.add(Box.createHorizontalGlue());
        panelDatosMateria.add(panelContenedorAgregar);

        panelDatosMateria.add(Box.createVerticalStrut(10));

        //BOTON FINAL
        JButton btnFinalizar = new JButton("GUARDAR CARRERA COMPLETA");
        btnFinalizar.setPreferredSize(new Dimension(0, 50));
        btnFinalizar.setBackground(new Color(72, 52, 117));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setFont(new Font("Arial", Font.BOLD,14));
        add(btnFinalizar,BorderLayout.SOUTH);

        //LOGICA
        //Agregar materia al plan
        btnAgregar.addActionListener(e -> {
            String nom = txtNombreMateria.getText().trim();
            String cod = txtCodigoMateria.getText().trim();

            if (!nom.isEmpty() && !cod.isEmpty()){
                Materia nuevaM = new Materia(cod, nom,(int)spinnerCargaHoraria.getValue(),
                        (int)spinnerCuatrimestre.getValue(),chkEsOpcional.isSelected());

                //OBTENER CORRELATIVAS SELECCIOANDAS
                List<Materia> seleccionadas = listaPosiblesCorrelativas.getSelectedValuesList();
                for (Materia corr : seleccionadas){
                    nuevaM.agregarCorrelativas(corr);
                }

                //GUARDAR EN BUILDER Y EN LISTAS
                if (chkEsOpcional.isSelected()){
                    builderTemporal.addMateriaOptativa(nuevaM);
                } else {
                    builderTemporal.addMateriaObligatoria(nuevaM);
                }

                materiasCreadasParaElPlan.add(nuevaM);
                modeloPosibleCorrelativas.addElement(nuevaM);
                modeloListaMaterias.addElement(cod + " - "+ nom + " (" + seleccionadas.size() + " corr.)");

                limpiarCamposMateria();
            }
        });

        //Crear carrera
        btnFinalizar.addActionListener(e -> {
            try{
                if(txtNombreCarrera.getText().trim().isEmpty()) {
                    throw new IllegalStateException("El nombre de la carrera no puede estar vacío");
                }

                builderTemporal.setNombreCarrera("Plan " + txtNombreCarrera.getText());
                builderTemporal.setCantidadOptativas((int) spinnerOptativasreq.getValue());

                ICondicionInscripcion seleccionada = (ICondicionInscripcion) comboEstrategias.getSelectedItem();
                builderTemporal.setEstrategiaInscripcion(seleccionada);

                controlador.crearCarrera(txtNombreCarrera.getText(),builderTemporal.build());
                JOptionPane.showMessageDialog(this, "Carrera y plan creados.");
                resetTodo();
            } catch (Exception ex){
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });
    }

    //Metodos de limpieza y diseño
    private JTextField crearTextField(){
        JTextField t = new JTextField();
        t.setMaximumSize(new Dimension(Integer.MAX_VALUE,25));
        return t;
    }

    private void configurarComponente(JComponent c){
        c.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));
    }

    private void limpiarCamposMateria(){
        txtNombreMateria.setText("");
        txtCodigoMateria.setText("");
        chkEsOpcional.setSelected(false);
        listaPosiblesCorrelativas.clearSelection();
    }

    private void resetTodo(){
        txtNombreCarrera.setText("");
        modeloListaMaterias.clear();
        modeloPosibleCorrelativas.clear();
        materiasCreadasParaElPlan.clear();
        builderTemporal = new PlanDeEstudioBuilder();
    }
}


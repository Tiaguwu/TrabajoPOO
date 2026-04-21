package Vista;

import Controlador.UniversidadController;
import Modelo.*;

import javax.swing.*;
import java.awt.*;

 /*
    Panel para agregar la calificacion de parcial o final de una materia de los alumnos.
 */

public class PanelNotas extends JPanel {

    private UniversidadController controlador;

    private JComboBox<Alumno> comboAlumnos;
    private JComboBox<Cursada> comboCursadas;
    private JComboBox<String> comboTipoNota;

    private JTextField txtNota;

    public PanelNotas(UniversidadController controlador){
        this.controlador = controlador;

        setLayout(new BorderLayout(15,15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        //ARRIBA: Seleccion de alumno y materia
        JPanel panelSeleccion = new JPanel(new GridLayout(3,2,10,10));
        panelSeleccion.setBorder(BorderFactory.createTitledBorder("1. Selección de Alumno y Materia"));

        panelSeleccion.add(new JLabel("Alumno:"));
        comboAlumnos = new JComboBox<>();
        panelSeleccion.add(comboAlumnos);

        panelSeleccion.add(new JLabel("Materia / Cursada:"));
        comboCursadas = new JComboBox<>();
        panelSeleccion.add(comboCursadas);

        panelSeleccion.add(new JLabel("Instancia de Evaluacion:"));
        comboTipoNota = new JComboBox<>(new String[]{"PARCIAL","FINAL"});
        panelSeleccion.add(comboTipoNota);

        add(panelSeleccion, BorderLayout.NORTH);

        //CENTRO: Cargar nota
        JPanel panelCarga = new JPanel(new FlowLayout());

        panelCarga.add(new JLabel("Nota (1 - 10):"));
        txtNota = new JTextField(5);
        panelCarga.add(txtNota);

        JButton btnCargar = new JButton("Registrar Calificacion");
        btnCargar.setBackground(new Color(72, 52,117));
        btnCargar.setForeground(Color.WHITE);
        panelCarga.add(btnCargar);

        add(panelCarga, BorderLayout.CENTER);

        //LOGICA
        comboAlumnos.addActionListener(e -> actualizarCursadas());

        //Le asigna la nota a una materia lo cual le da el estado de desaprobada, aprobada o promocionada
        btnCargar.addActionListener(e -> {
            try {
                Cursada cursadaSel = (Cursada) comboCursadas.getSelectedItem();
                double nota = Double.parseDouble(txtNota.getText());
                String tipo = (String) comboTipoNota.getSelectedItem();

                if (cursadaSel != null && nota >= 0 && nota <= 10) {
                    if (tipo.equals("PARCIAL")){
                        controlador.cargarNotaParcial(cursadaSel,nota);

                        cursadaSel.finalizarCursada();
                    } else {
                        controlador.registrarFinal(cursadaSel,nota);
                    }
                    JOptionPane.showMessageDialog(this,"Nota registrada. Estado actual: " +
                                                    cursadaSel.getNombreEstado());
                    actualizarCursadas();
                    txtNota.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar una nota entre 0 y 10.");
                }
            } catch (IllegalStateException ex) {
                JOptionPane.showMessageDialog(this,ex.getMessage());
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(this, "La nota debe ser un número.");
            }
        });
    }

    //Para tener los datos actualizados
    public void actualizarCombos(){
        comboAlumnos.removeAllItems();
        for (Alumno a : controlador.getAlumnos()){
            comboAlumnos.addItem(a);
        }
    }

    private void actualizarCursadas(){
        comboCursadas.removeAllItems();
        Alumno a =(Alumno) comboAlumnos.getSelectedItem();
        if (a != null){
            for (Cursada c : a.getCursadasActuales()){
                comboCursadas.addItem(c);
            }
        }
    }
}

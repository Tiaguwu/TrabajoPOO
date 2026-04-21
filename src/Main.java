import Controlador.UniversidadController;
import Modelo.*;
import Vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Universidad modelo = new Universidad();
            UniversidadController controlador = new UniversidadController(modelo);
            configurarDemo(controlador);
            VentanaPrincipal vista = new VentanaPrincipal(controlador);
            vista.mostrar();
        });
    }

    private static void configurarDemo(UniversidadController ctrl) {

        //Sistemas
        //1er año - 1er cuatri
        Materia m1 = new Materia("IF001", "Elementos de Informática", 105,
                        1, true);
        Materia m2 = new Materia("MA045", "Algebra",
                        135, 1, true);
        Materia m3 = new Materia("IF002", "Expresión de Problemas y" +
                                " Algoritmos", 90, 1, true);

        //1er año - 2do cuatri
        Materia m4 = new Materia("IF003","Algorítmica y Programación I",
                                120, 2, true);
        Materia m5 = new Materia("MA008", "Elementos de Lógica y " +
                                "Matemática Discreta", 120, 2, true);
        Materia m6 = new Materia("MA046", "Análisis Matemático",
                    165, 2, true);

        //2do año - 3er cuatri
        Materia m7 = new Materia("IF004","Sistemas y Organizaciones",
                90,3, true);
        Materia m8 = new Materia("IF005", "Arquitectura de Computadoras",
                                120,3, true);
        Materia m9 = new Materia("IF006","Algorítmica y Programación II",
                                120,3, true);
        Materia m10 = new Materia("MA006", "Estadística",
                                90, 3, true);

        //2do año - 4to cuatri
        Materia m11 = new Materia("IF007", "Bases de Datos I",
                                135,4,true);
        Materia m12 = new Materia("IF030", "Programación y Diseño " +
                "Orientado a Objetos", 120, 4, true);
        Materia m13 = new Materia("IF031", "Ingeniería de Software I",
                                150, 4, true);

        //3er año - 5to cuatri
        Materia m14 = new Materia("IF009","Laboratorio de Programación y " +
                "Lenguajes",90, 5, true);
        Materia m15 = new Materia("IF013", "Fundamentos Teóricos de " +
                "Informática", 120, 5, true);
        Materia m16 = new Materia("IF033", "Ingeniería de Software II",
                                120, 5, true);
        Materia m17 = new Materia("IF038", "Introducción a la Concurrencia",
                                60, 5, true);

        //3er año - 6to cuatri
        Materia m18 = new Materia("IF044", "Bases de Datos II",
                                120,6, true);
        Materia m19 = new Materia("IF037", "Sistemas Operativos",
                                120, 6, true);
        Materia m20 = new Materia("IF055", "Laboratorio de Software ",
                                105, 6, true);
        Materia m21 = new Materia("IF056", "Seminario de Aspectos Legales " +
                "y Profesionales I", 60, 6, true);

        //4to año - 7mo cuatri
        Materia m22 = new Materia("IF019", "Redes y Transmisión de Datos",
                                135, 7, true);
        Materia m23 = new Materia("IF020", "Paradigmas y Lenguajes de " +
                "Programación", 120, 7, true);
        Materia m24 = new Materia("IF017", "Taller de Nuevas Tecnologías",
                                90, 7, true);

        //4to año - 8vo cuatri
        Materia m25 = new Materia("IF022", "Sistemas Distribuidos",
                120, 8, true);
        Materia m26 = new Materia("IF035", "Ingeniería de Software III",
                                120, 8, true);
        Materia m27 = new Materia("IF057", "Seminario de Aspectos Legales " +
                "y Profesionales II",  60, 8, true);
        Materia m28 = new Materia("IF059", "Sistemas Inteligentes",
                                90, 8, true);

        //5to año - 9no cuatri
        Materia m29 = new Materia("IF060","Sistemas de Tiempo Real",
                                105, 9, true);
        Materia m30 = new Materia("IF061", "Sistemas Paralelos",
                        90, 9, true);
        Materia m31 = new Materia("IF062", "Bases de Datos Distribuidas",
                                90, 9, true);
        Materia m32 = new Materia("IF063", "Seminario de Seguridad",
                                60, 9, true);

        //5to año - 10mo cuatri
        Materia m33 = new Materia("IF027", "Modelos y Simulación",
                                90, 10, true);
        Materia m34 = new Materia("IF042", "Proyecto de Software",
                        120, 10, true);

        //Tesina
        Materia m35 = new Materia("IF026", "Tesina",
                200,11, true);

        // Correlatividades
        m4.agregarCorrelativas(m3);
        m8.agregarCorrelativas(m1);
        m9.agregarCorrelativas(m4);
        m9.agregarCorrelativas(m5);
        m10.agregarCorrelativas(m2);
        m10.agregarCorrelativas(m6);
        m11.agregarCorrelativas(m9);
        m12.agregarCorrelativas(m9);
        m13.agregarCorrelativas(m4);
        m13.agregarCorrelativas(m7);
        m14.agregarCorrelativas(m11);
        m15.agregarCorrelativas(m9);
        m15.agregarCorrelativas(m5);
        m16.agregarCorrelativas(m13);
        m16.agregarCorrelativas(m10);
        m17.agregarCorrelativas(m8);
        m17.agregarCorrelativas(m9);
        m18.agregarCorrelativas(m11);
        m19.agregarCorrelativas(m17);
        m20.agregarCorrelativas(m11);
        m20.agregarCorrelativas(m12);
        m20.agregarCorrelativas(m13);
        m21.agregarCorrelativas(m7);
        m22.agregarCorrelativas(m19);
        m23.agregarCorrelativas(m15);
        m23.agregarCorrelativas(m12);
        m24.agregarCorrelativas(m20);
        m25.agregarCorrelativas(m22);
        m26.agregarCorrelativas(m16);
        m27.agregarCorrelativas(m21);
        m28.agregarCorrelativas(m15);
        m29.agregarCorrelativas(m19);
        m30.agregarCorrelativas(m14);
        m30.agregarCorrelativas(m25);
        m31.agregarCorrelativas(m18);
        m31.agregarCorrelativas(m22);
        m32.agregarCorrelativas(m22);
        m33.agregarCorrelativas(m10);
        m33.agregarCorrelativas(m23);
        m34.agregarCorrelativas(m26);
        m35.agregarCorrelativas(m33);
        m35.agregarCorrelativas(m34);

        // Materia optativa
        Materia opt1 = new Materia("FA007", "Acreditación de Idioma Inglés", 100, 2, false);

        // Crear plan de estudio (con Builder)
        PlanDeEstudioBuilder builder = new PlanDeEstudioBuilder();
        PlanDeEstudio planSistemas = builder
                .setNombreCarrera("Licenciatura en Sistemas")
                .addMateriaObligatoria(m1)
                .addMateriaObligatoria(m2)
                .addMateriaObligatoria(m3)
                .addMateriaObligatoria(m4)
                .addMateriaObligatoria(m5)
                .addMateriaObligatoria(m6)
                .addMateriaObligatoria(m7)
                .addMateriaObligatoria(m8)
                .addMateriaObligatoria(m9)
                .addMateriaObligatoria(m10)
                .addMateriaObligatoria(m11)
                .addMateriaObligatoria(m12)
                .addMateriaObligatoria(m13)
                .addMateriaObligatoria(m14)
                .addMateriaObligatoria(m15)
                .addMateriaObligatoria(m16)
                .addMateriaObligatoria(m17)
                .addMateriaObligatoria(m18)
                .addMateriaObligatoria(m19)
                .addMateriaObligatoria(m20)
                .addMateriaObligatoria(m21)
                .addMateriaObligatoria(m22)
                .addMateriaObligatoria(m23)
                .addMateriaObligatoria(m24)
                .addMateriaObligatoria(m25)
                .addMateriaObligatoria(m26)
                .addMateriaObligatoria(m27)
                .addMateriaObligatoria(m28)
                .addMateriaObligatoria(m29)
                .addMateriaObligatoria(m30)
                .addMateriaObligatoria(m31)
                .addMateriaObligatoria(m32)
                .addMateriaObligatoria(m33)
                .addMateriaObligatoria(m34)
                .addMateriaObligatoria(m35)

                .addMateriaOptativa(opt1)
                .setCantidadOptativas(1)
                .setEstrategiaInscripcion(new CondicionInscripcionA())
                .build();

        // Crear carrera
        ctrl.crearCarrera("Licenciatura en Sistemas", planSistemas);

        // Registrar alumnos
        ctrl.registrarAlumno("1001", "Tiago Caranchi");
        //ctrl.registrarAlumno("1002", "Bnuuy Bunnita");
    }
}
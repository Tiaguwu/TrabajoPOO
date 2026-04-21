## Pasos que fui haciendo:

1. Elegir las clases del dominio (Modelo):

* Alumno
* Materia
* Cursada
* Plan de estudio

2. Identificar patrones de diseño:

   1. Builder: ya que la consiga dice " `La creación de la entidad Plan de Estudios es compleja, ya que requiere configurar materias obligatorias, optativas, la
      cantidad mínima de optativas, y la estrategia de inscripción. Se debe aplicar algún mecanismo para facilitar y hacer más
      legible la construcción de los objetos PlanDeEstudios` ".
   Elegi este patron ya que la creacion requiere muchos pasos entonces el patron Builder facilita esto.

   * Clases que se agregan:
     * IPlanDeEstudio (Interfaz de creacion de plan de estudio)
     * PlanDeEstudioBuilder (Clase encargada de la creacion del plan de estudio)

   2. Strategy: ya que las acciones de los cambios puede realizarse con diferentes algoritmo intercambiales.
   * Clases que se agregan:
     * ICondicionInscripcion (Interfaz que especifica la acciones)
     * CondicionInscripcionA (Correlativas cursadas aprobadas)
     * CondicionInscripcionB (Correlativas finales aprobados)
     * CondicionInscripcionC (Correlativas aprobadas + finales 5 cuatrimestre previos)
     * CondicionInscripcionD (Correlativas aprobadas + finales 3 cuatrimestre previos)
     * CondicionInscripcionE (Correlativas finales aprobados + finales 3 cuatrimestre previos)

   3. State: porque el comportamiento del objeto cambia radicalmente segun su estado interno.
      * Clases que se agregan:
        * IEstadoCursada
        * EstadoInscripto
        * ParcialAprobado
        * ParcialDesaprobado
        * CursadaAprobada
        * Promocionada
        * CursadaDesaprobada


3. Solucionar BUGS:
* Anotarse a la misma carrera `LISTO`
* Ver materias de otras carreras a las que no esta anotado `LISTO`
* Nombre en las notas `LISTO`
* Fijarse como funcionan los estados de las cursadas con las notas `LISTO`
  * Inscripto -> Parcial (0-3) -> Desaprobado y tiene que recursar `LISTO`
  * Incripto -> Parcial (4-6) -> Aprobado habilitado para final `LISTO`
  * Inscirpto -> Final -> Error porque primero tiene que rendir parcial `LISTO`
  * 
  * Aprobado -> Parcial -> El parcial ya se rindio y aprobo `LISTO`
  * Aprobado -> Final -> Aprobo y habilita correlativa `LISTO`

  * Puedo poner nota de final sin rendir parcial `LISTO`


* Historial no anda bine `LISTO`
  * que muestre de forma diferente `LISTO`
  * cuando aprobas el final desaparece `LISTO`


Como funciona: 
 1. Darle a ejecutar a la clase Main.
 2. En administracion se puede crear las carreras junto a sus materias.
 3. En alumnos e inscripcion sirve para crear a los alumnos, ver el listado y la inscripcion a carreras.
 4. En inscripcion a materia es para que los alumnos se inscriban a la materias.
 5. En gestion de notas es para que se carguen las notas eligiendo si es un parcial o final. 
 6. En historial y titulos es para ver el estado de todas las materias del alumno.

El programa ya tiene cargado la carrera de Analista en sistemas y tiene un alumno como ejemplo.


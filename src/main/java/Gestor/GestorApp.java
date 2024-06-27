package Gestor;

import java.io.*;
import  java.util.*;

public class GestorApp {

    private List<Empleado> empleados;
    private Scanner menu;

    public GestorApp() {

        this.empleados = new ArrayList<>();
        this.menu = new Scanner(System.in);
        cargarEmpleadosDesdeArchivo();
    }

    public void mostrarMenu() {

        int opcion = 0;

        do {
            System.out.println(" ");
            System.out.println("Menú:");
            System.out.println("1. Nuevo Empleado.");
            System.out.println("2. Buscar Empleado.");
            System.out.println("3. Ver Registro de Empleados.");
            System.out.println("4. Eliminar Registros.");
            System.out.println("5. Salir.");
            System.out.print("Selecciona una opción" + "\n");
            try {
                opcion = menu.nextInt();
                menu.nextLine();


            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    buscarEmpleado();
                    break;
                case 3:
                    verRegistro();
                    break;
                case 4:
                    eliminarRegistros();
                    break;
                case 5:
                    salir();
                    break;

                default:
                    System.out.println("Opción no válida. Selecciona opcion correcta.");
            }

            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                menu.nextLine();
                opcion = 0;
            }

        } while (opcion != 5);

        menu.close();
    }

    private void cargarEmpleadosDesdeArchivo() {

        File archivo = new File("empleados.dat");

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Archivo empleados.dat creado.");
                empleados = new ArrayList<>(); // Inicializar la lista vacía si es un nuevo archivo
            } catch (IOException e) {
                System.out.println("Error al crear el archivo empleados.dat.");
                e.printStackTrace();
            }
        } else {

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleados.dat"))) {

                empleados = (List<Empleado>) ois.readObject();
                System.out.println("Empleados cargados desde el archivo empleados.dat.");

            } catch (IOException | ClassNotFoundException e) {

                System.out.println("Error al cargar los empleados desde el archivo.");
                empleados = new ArrayList<>();
                e.printStackTrace();
            }
        }
    }
    private void eliminarRegistros() {
        // Limpiar la lista de empleados
        empleados.clear();

        // Borrar el archivo de datos
        File archivo = new File("empleados.dat");
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("Archivo de empleados eliminado correctamente.");
            } else {
                System.out.println("Error al eliminar el archivo de empleados.");
            }
        } else {
            System.out.println("No existe el archivo de empleados.");
        }

        System.out.println("Todos los registros de empleados han sido eliminados.");
    }

    private void crearEmpleado() {

        System.out.println("\nCreación de Nuevo Empleado:");

        System.out.print("Ingrese Primer Apellido empleado: ");
        String primerApellido = menu.nextLine().toUpperCase();

        System.out.print("Ingrese Nombre empleado: ");
        String nombre = menu.nextLine().toUpperCase();

        System.out.print("Ingrese DNI empleado: ");
        String dni = menu.nextLine().toUpperCase();

        System.out.print("Ingrese Salario Base empleado: ");
        double salarioBase = menu.nextDouble();

        double irpf = Empleado.calcularTipo();
        menu.nextLine();

        String tipoEmpleado;

        System.out.print("Seleccione el tipo de empleado: (Técnico(T) / Ejecutivo(X): ");
        tipoEmpleado = menu.nextLine().toUpperCase();


        if (tipoEmpleado.equals("T")) {

            String respuesta = "";
            ArrayList<String> cualificaciones = new ArrayList<>();
            System.out.print("Se le van a solicitar las distintas opciones de cualifciacion.\n ");
            System.out.print("Ingrese las cualificaciones especificas de cada tipo (SEPARADAS POR COMA(,):\n ");
            System.out.print("Indice si procede:\n");

            System.out.print("Formacion tecnica NO UNIVERSITARIA:\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }

            System.out.print("Formacion de tipo UNIVERSITARIO:\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }

            System.out.print("Formacion Especializacion (Master):\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }

            System.out.print("Capacitaciones tecnicas Relevantes:\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }

            System.out.print(" Otras Capacitaciones (Diplomas, Carnets, etc:\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }

            System.out.print("Idiomas:\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }

            System.out.print("Convalidacion por experiencia Profesional > 5 años:\n ");
            respuesta = menu.nextLine().toLowerCase();
            if (!respuesta.isEmpty()) {
                cualificaciones.addAll(Arrays.asList(respuesta.split(",\\s*")));
            }
            double complementos = 200.25;
            double plusConvenio = 120.10;

            double salarioMes = Empleado.calcularSalarioMes(salarioBase, complementos, plusConvenio, irpf);
            String puesto = "Tecnico";
            EmpleadoTecnico tecnico = new EmpleadoTecnico(primerApellido, nombre, dni, salarioBase,
                    150.50, 200.35, irpf, salarioMes, puesto, cualificaciones);

            empleados.add(tecnico);
            System.out.println("Empleado Técnico creado exitosamente.\n");

        } else if (tipoEmpleado.equals("X")) {

            System.out.print("Ingrese las comisiones anuales del ejecutivo: ");
            double comisionesAnuales = menu.nextDouble();
            menu.nextLine();

            double complementos = 400.50;
            double plusConvenio = 200.07;

            double comisionesMes = comisionesAnuales / 12;
            double salarioMes = Empleado.calcularSalarioMes(salarioBase, complementos, plusConvenio, irpf) + comisionesMes;

            String puesto = "Ejecutivo";

            EmpleadoEjecutivo ejecutivo = new EmpleadoEjecutivo(primerApellido, nombre, dni, salarioBase,
                    300.50, 400.80, irpf, salarioMes, puesto, comisionesAnuales);

            empleados.add(ejecutivo);
            System.out.println("Empleado Ejecutivo creado exitosamente.\n");
        }

        guardarEmpleadosEnArchivo();

    }

    private void buscarEmpleado() {

        if (empleados.isEmpty()) {
            System.out.println("No hay empleados creados aún.");
            return;
        }

        System.out.print("\nIngrese el DNI o el primer apellido del empleado a buscar: ");
        String parametroBusqueda = menu.nextLine().toUpperCase();

        boolean encontrado = false;

        for (Empleado empleado : empleados) {
            if (empleado.getDni().equals(parametroBusqueda) ||
                    empleado.getPrimerApellido().equals(parametroBusqueda)) {
                empleado.mostrarDetalles();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Empleado no encontrado.");
        }
    }

    private void verRegistro() {
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados creados aún.");
            return;
        }

        System.out.println("\nRegistro de Empleados:");
        for (Empleado empleado : empleados) {
            empleado.mostrarDetalles();
        }
    }

    private void salir() {

        if (empleados.size() < 5) {
            System.out.println("Se precisan un minimo de 5 empleados en el registro.");

            crearEmpleado();

        } else {

            menu.close();
            System.exit(0);
        }
    }

    private void guardarEmpleadosEnArchivo() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("empleados.dat"))) {

            oos.writeObject(empleados);
            System.out.println("Empleados guardados en el archivo empleados.dat.");

        } catch (IOException e) {

            System.out.println("Error al guardar los empleados en el archivo.");
            e.printStackTrace();
        }

    }


}

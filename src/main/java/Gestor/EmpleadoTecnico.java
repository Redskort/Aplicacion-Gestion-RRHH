package Gestor;

import java.io.Serializable;
import java.util.ArrayList;

public class EmpleadoTecnico  extends Empleado implements Serializable {

    private ArrayList<String> cualificaciones;

    public EmpleadoTecnico(String primerApellido, String nombre, String dni, double salarioBase, double complementos,
                           double plusConvenio, double irpf, double salarioMes, String puesto,  ArrayList<String> cualificaciones) {
        super(primerApellido, nombre, dni, salarioBase,complementos,plusConvenio, irpf, salarioMes, puesto);
        this.cualificaciones = cualificaciones;
    }


        @Override
        public void mostrarDetalles () {

            super.mostrarDetalles();
            System.out.print("Cualificaciones: ");
            for (String cualificacion : cualificaciones) {
                System.out.println(cualificacion + ", ");
            }
            System.out.println();
        }

    }


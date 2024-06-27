package Gestor;

public class EmpleadoEjecutivo extends Empleado{

    private double comisionesAnuales;


    public EmpleadoEjecutivo(String primerApellido, String nombre, String dni, double salarioBase,
                             double complementos, double plusConvenio, double irpf, double salarioMes, String puesto,
                             double comisionesAnuales) {
        super(primerApellido,  nombre, dni, salarioBase, complementos, plusConvenio, irpf, salarioMes, puesto);
        this.comisionesAnuales = comisionesAnuales;
    }



    @Override
    public void mostrarDetalles () {

        super.mostrarDetalles();
        System.out.print("Comisiones Anuales: " + String.format("%.2f", comisionesAnuales ) + " €");
        System.out.println();
    }
}

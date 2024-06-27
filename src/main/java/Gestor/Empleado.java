package Gestor;

import java.io.Serializable;
import java.util.Random;

public abstract class Empleado implements Serializable {
    private String dni;
    private String nombre;
    private String primerApellido;
    private String puesto;
    private double salarioMes;
    private double salarioBase;
    private double plusConvenio;
    private double complementos;
    private double irpf;


    public Empleado(String primerApellido, String nombre, String dni, double salarioBase,
                    double complementos, double plusConvenio, double irpf, double salarioMes, String puesto) {

        this.primerApellido = primerApellido;
        this.nombre = nombre;
        this.dni = dni;
        this.salarioBase = salarioBase;
        this.complementos = complementos;
        this.plusConvenio = plusConvenio;
        this.irpf = calcularTipo();
        this.salarioMes = calcularSalarioMes(salarioBase, complementos, plusConvenio, irpf);
        this.puesto = puesto;
    }

    public String getDni() {
        return dni;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public double getSalarioMes() {
        return salarioMes;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double getPlusConvenio() {
        return plusConvenio;
    }

    public double getComplementos() {
        return complementos;
    }

    public double getIrpf() {
        return irpf;
    }

    public void setSalarioMes(double salarioMes) {
        this.salarioMes = salarioMes;
    }

    public String getPuesto() {return puesto;}

    public void setPuesto(String puesto) {this.puesto = puesto;}



    public static double calcularSalarioMes(double salarioBase, double complementos, double plusConvenio, double irpf) {

        double positivos = salarioBase + complementos + plusConvenio;
        irpf = calcularTipo();
         double salarioMes = positivos -(positivos * (irpf/100));

        return Math.round(salarioMes * 100) / 100;

    }

    public static double calcularTipo() {
        Random objetoGenerator = new Random();
        double irpf = 10.0 + objetoGenerator.nextDouble() * 8.0;
        return Math.round(irpf * 100) / 100;
    }


    public void mostrarDetalles() {



        System.out.println(" ");
        System.out.println("Primer Apellido: " + primerApellido);
        System.out.println("Nombre: " + nombre);
        System.out.println("DNI: " + dni);
        System.out.println("Salario Base: " + salarioBase + " €");
        System.out.println("Complementos Salariales: " + complementos + " €");
        System.out.println("Plus Convenio: " + plusConvenio + " €");
        System.out.println("Puesto: " + puesto);
        System.out.println("IRPF: " +  irpf + " %");
        System.out.println("Salario Mensual: " + String.format("%.2f", salarioMes) + " €");
    }
}




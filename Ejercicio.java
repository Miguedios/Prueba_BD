import java.util.Scanner;

class Persona {
    //Atributos.
    int identificacion;
    String nombre;
    String nacimiento;
    String profesion;
    String ingreso;
    String retiro;
    double salario;
    double vacaciones;
    double primas;
    double cesantias;
    double intereses;
    boolean activo;
    boolean contratista;
    Persona siguiente;

    //Constructor.
    Persona(int identificacion,
            String nombre,
            String nacimiento,
            String profesion,
            String ingreso,
            String retiro,
            boolean activo,
            boolean contratista){
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.profesion = profesion;
        this.ingreso = ingreso;
        this.retiro = retiro;
        this.salario = 0;
        this.vacaciones = 0;
        this.primas = 0;
        this.cesantias = 0;
        this.intereses=0;
        this.activo = activo;
        this.contratista = contratista;
        this.siguiente = null;
    }
}

class Lista {
    Persona head;
    Persona tail;
}

class Queue extends Lista {
    public void Enqueue (Persona persona) {
        if (head == null) {
            head = persona;
            tail = persona;
        } else {
            tail.siguiente = persona;
            tail = persona;
        }
    }
    public static void Listar (Queue lista) {
        Persona jefe = lista.head;
        while (jefe != null) {
            System.out.println("---------------------------------");
            System.out.println("Nombre: " + jefe.nombre);
            System.out.println("Id: " + jefe.identificacion);
            System.out.println("Fecha de Nacimiento: " + jefe.nacimiento);
            System.out.println("Profesión: " + jefe.profesion);
            System.out.println("Fecha de Entrada: " + jefe.ingreso);
            System.out.println("Fecha de Salida: " + jefe.retiro);
            System.out.println("Salario: " + jefe.salario);
            System.out.println("Vacaciones: " + jefe.vacaciones);
            System.out.println("primas: " + jefe.primas);
            System.out.println("cesantias: " + jefe.cesantias);
            System.out.println("intereses: " + jefe.intereses);
            System.out.println("Activo: " + jefe.activo);
            System.out.println("Contratista: " + jefe.contratista);
            System.out.println("---------------------------------");
            System.out.println();
            
            jefe = jefe.siguiente;
        }
    }
}

public class Ejercicio {

    public static void main (String [] args) {
        int numero;
        Scanner reader = new Scanner(System.in);
        
        Queue listaGeneral = new Queue();
        Queue listaContratistas = new Queue();
        Queue listaEmpleados = new Queue();
        final int sala = 877803;
        final int honor = 908526;
        
        do {
            System.out.println("*** 1. Ingresar un empleado/contratista");
            System.out.println("*** 2. Mostrar las listas");
            System.out.println("*** 3. Calcular salarios/honorarios/liquidaciones");
            numero = reader.nextInt();
            reader.nextLine();
            switch (numero) {
                case 1: 
                    //Pedir datos.
                    System.out.println("Ingrese el identicicación de la persona: ");
                    int identificacion = reader.nextInt();
                    reader.nextLine();
                    System.out.println("Ingrese el nombre y apellido de la persona: ");
                    String nombre = reader.nextLine();
                    System.out.println("Ingrese la fecha de nacimiento DD/MM/AA: ");
                    String nacimiento = reader.nextLine();
                    System.out.println("Ingrese su profesión: ");
                    String profesion = reader.nextLine();
                    System.out.println("Ingrese la fecha de Entrada: ");
                    String ingreso = reader.nextLine();
                    System.out.println("Ingrese la fecha de retiro: ");
                    String retiro = reader.nextLine();
                    System.out.println("***1. Persona está activa" + 
                     "\n***0. Persona no Activa: ");
                    int numA = Integer.parseInt(reader.nextLine());
                    boolean activo;
                    if (numA == 1) {
                        activo = true;
                    } else {
                        activo = false;
                    }
                    System.out.println("***1. Persona es Contratista" + 
                     "\n***0. Persona no es Contratista: ");
                    int numC = reader.nextInt();
                    boolean contratista;
                    if (numC == 1){
                        contratista = true;
                    } else {
                        contratista = false;
                    }
                    //Se puede cambiar por un TreeMap.
                    // Se ingresan las personas en una lista enlazada.
                    Persona persona = new Persona(identificacion, nombre, nacimiento, profesion, ingreso, retiro, activo, contratista);
                    listaGeneral.Enqueue(persona);
                    System.out.println("Entra en la lista general");
                    if (persona.contratista != true){
                        listaEmpleados.Enqueue(persona);
                        System.out.println("Entra en la lista de empleados");
                    } else if (persona.contratista == true) {
                        listaContratistas.Enqueue(persona);
                        System.out.println("Entra en la lista de contratistas");
                    }
                    //Hasta aquí va el TreeMap
                    break;
                case 2:

                    System.out.println("Ingrese 0 para la lista general");
                    System.out.println("Ingrese 1 para la lista de contratistas");
                    System.out.println("Ingrese 2 para la lista de Empleados");
                    //Mostrar las listas
                    int numListas = reader.nextInt();
                    switch (numListas) {
                        case 0:
                            Queue.Listar(listaGeneral);
                            break;
                        case 1:
                            Queue.Listar(listaContratistas);
                            break;
                        case 2:
                            Queue.Listar(listaEmpleados);
                            break;
                    }
                    break;

                case 3:
                    System.out.println("Ingrese la identificacion de la persona");
                    int id = Integer.parseInt(reader.nextLine());
                    Persona prueba = listaGeneral.head;
                    while (prueba != null) {
                        if (prueba.identificacion == id) {
                            break;
                        } else {
                            prueba = prueba.siguiente;
                        }
                    }
                    if (prueba != null) {
                        if (prueba.activo == true) {
                            if (prueba.contratista == true){
                                System.out.println("Ingrese el honorario del contratista: ");
                                double sal = Integer.parseInt(reader.nextLine());
                                //Retencion en la fuente.
                                if ((honor <= sal) || (sal < honor*2)) {
                                    sal = sal - (sal*0.03);
                                } else if ((honor*2 <= sal) || (sal < honor*4)) {
                                    sal = sal - (sal * 0.07);
                                } else if (honor*4 <= sal) {
                                    sal = sal - (sal * 0.10);
                                }
                                //Solidaridad.
                                sal = sal - (sal * 0.01);
                                //Retencion ICA 
                                sal = sal - (sal * 0.02);
                                prueba.salario = sal;
                                System.out.println("El honorario quedó de: " + sal);
                            } else {
                                System.out.println("Ingrese el salario del Empleado: ");
                                double sal1 = Integer.parseInt(reader.nextLine());
                                //Aporte de salud.
                                sal1 = sal1 - (sal1 * 0.04);

                                //Aporte a Pensión.
                                sal1 = sal1 - (sal1 * 0.04);

                                //Fondo de Solidaridad.
                                sal1 = sal1 - (sal1 * 0.01);

                                //Retención.
                                if (sal1 > (sala * 4)){
                                    sal1 = sal1 - (sal1 * 0.10);
                                }
                                prueba.salario = sal1;
                                System.out.println("El salario quedó de: " + sal1);
                            }
                        }
                        else{
                            //Liquidación. 
                            System.out.println("Ingrese el salario/honorario de la persona retirada: ");
                            double salar = Integer.parseInt(reader.nextLine());
                            double vacas = (salar * 360) / 720;
                            double prima = (salar * 0.3);
                            double cesa = (salar * 180)/360 + prima - (salar * 0.1);
                            double intere = (vacas + prima + cesa) * 0.02;
                            prueba.salario = salar;
                            prueba.vacaciones = vacas;
                            System.out.println("Las vacaciones son de : $" + vacas);
                            prueba.primas = prima;
                            System.out.println("Las primas son de : $" + prima);
                            prueba.cesantias = cesa;
                            System.out.println("Las Cesantías son de: $" + cesa);
                            prueba.intereses = intere;
                            System.out.println("Los intereses son de: $" + intere);
                        }
                    } else {
                        System.out.println("La persona no está registrada");
                    }
                    break;
            }
        } while(numero != 0);
    }
}

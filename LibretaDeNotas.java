
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class LibretaDeNotas {

        /*
        Modificar la Clase Evaluador:
Renombra la clase Evaluador a LibretaDeNotas para que pueda evaluar y almacenar las calificaciones de una lista de estudiantes en lugar de una única calificación.
Utiliza un HashMap para almacenar las calificaciones de los estudiantes, donde la llave es el nombre del estudiante y el valor es un ArrayList de notas.
Solicita al usuario que ingrese la cantidad de alumnos y la cantidad de notas por alumno.
Solicita el nombre de cada alumno y las notas correspondientes, almacenándolas en el HashMap.
Recorrer el HashMap y Evaluar Calificaciones:
Utiliza un bucle (por ejemplo, for o foreach) para recorrer el HashMap de calificaciones.
Calcular Promedio, Nota Máxima y Mínima por Estudiante:
Después de evaluar las calificaciones, calcula y muestra para cada estudiante:
Promedio de Notas: Suma todas las calificaciones y divide por la cantidad total de notas.
Nota Máxima: Encuentra la calificación más alta en el ArrayList.
Nota Mínima: Encuentra la calificación más baja en el ArrayList.
Menú de Opciones:
Muestra un menú con las siguientes opciones:
1. Mostrar el Promedio de Notas por Estudiante.
2. Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.
3. Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.
0. Salir del Menú.
Utiliza un bucle para permitir al usuario seleccionar opciones hasta que ingrese 0 para salir.
Operaciones del Menú:
Opción 1: Mostrar el Promedio de Notas por Estudiante.
Muestra el promedio de notas por cada estudiante calculado previamente.
Opción 2: Mostrar si la Nota es Aprobatoria o Reprobatoria por Estudiante.
Solicita al usuario ingresar el nombre de un estudiante y una nota, luego verifica si es aprobatoria o reprobatoria según una nota de aprobación definida.
Opción 3: Mostrar si la Nota está por Sobre o por Debajo del Promedio del Curso por Estudiante.
Solicita al usuario ingresar el nombre de un estudiante y una nota, luego verifica si está por sobre o por debajo del promedio general.
Validaciones:
Implementa validaciones para asegurar que las notas ingresadas estén en un rango válido y que la cantidad de alumnos sea un número positivo.
         */


        private static HashMap<String, ArrayList<Integer>> calificaciones = new HashMap<>();
        private static int totalNotas = 0;
        private static double promedioCurso = 0.0;


        public static void main(String[] args) {
            Scanner teclado = new Scanner(System.in);

            //Aqui se va a solicitar la cantidad de alumnos a evaluar
            System.out.println("¡Hola, usuario/a!, Bienvenido a la Libreta de notas" +
                                "\n Por favor, ingresa la cantidad de alumnos: ");
            int cantidadAlumnos = teclado.nextInt();
            if (cantidadAlumnos <= 0) {
                System.out.println("La cantidad de alumnos debe ser mayor a cero.");
                return;
            }

            // Aqui la cantidad de notas por cada uno de los alumnos
            System.out.print("Por favor, ingresea la cantidad de notas por alumno: ");
            int cantidadNotas = teclado.nextInt();
            if (cantidadNotas <= 0) {
                System.out.println("Lo sentimos, la cantidad de notas debe ser mayor de cero.");
                return;
            }

            // Aqui se ingresan los nombres de cada uno de los alumnos
            for (int i = 0; i < cantidadAlumnos; i++) {
                teclado.nextLine();
                System.out.print("Por favor, ingresa el nombre del alumno " + (i + 1) + ": ");
                String nombreAlumno = teclado.nextLine();

                ArrayList<Integer> notas = new ArrayList<>();
                for (int j = 0; j < cantidadNotas; j++) {
                    System.out.print("Por favor, ingresa la nota " + (j + 1) + " de " + nombreAlumno + ": ");
                    int nota = teclado.nextInt();
                    if (nota < 0 || nota > 10) {
                        System.out.println("Lo sentimos, la nota que has ingresado resulta invalida. Las notas deben estar entre 0 y 10.");
                        j--;
                        continue;
                    }
                    notas.add(nota);
                    totalNotas++;
                    promedioCurso += nota;
                }
                calificaciones.put(nombreAlumno, notas);
            }

            promedioCurso /= totalNotas;

            // Menu
            int opcion;
            do {
                mostrarMenu();
                opcion = teclado.nextInt();
                switch (opcion) {
                    case 1:
                        mostrarPromedioDeEstudiante();
                        break;
                    case 2:
                        mostrarAprobacionReprobacion();
                        break;
                    case 3:
                        mostrarSobreOdebajoDelPromedio();
                        break;
                    case 0:
                        System.out.println("¡Hasta pronto, usuario/!, gracias por usar la Libreta de Notas");
                        break;
                    default:
                        System.out.println("Lo sentimos, la opcion no es valida, por favor, vuelve a intentarlo");
                }
            } while (opcion != 0);

            teclado.close();
        }

        private static void mostrarMenu() {
            System.out.println("******* Menu de Opciones ********" +
                                "\n 1. Muestra el promedio de Notas por Estudiante." +
                                "\n 2. Muestra si la nota es Aprobatoria o Reprobatoria por Estudiante." +
                                "\n 3. Muestra si la nota está por arriba o por debajo del promedio del curso por cada estudiante." +
                                "\n 0. Salir del Menu." +
                                "\n Por favor, usuario/a, seleccione el numero de su opcion: ");
        }

        private static void mostrarPromedioDeEstudiante() {
            for (String estudiante : calificaciones.keySet()) {
                ArrayList<Integer> notas = calificaciones.get(estudiante);
                double promedioEstudiante = calcularPromedio(notas);
                System.out.println("Has elegido la opcion de mostrar el promedio de: " + estudiante + " y este es de: " + promedioEstudiante);
            }
        }

        private static double calcularPromedio(ArrayList<Integer> notas) {
            double sumaNotas = 0;
            for (int nota : notas) {
                sumaNotas += nota;
            }
            return sumaNotas / notas.size();
        }

        private static void mostrarAprobacionReprobacion() {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Por favor, ingresa el nombre del estudiante a evaluar: ");
            String nombreAlumno = scanner.nextLine();

            if (!calificaciones.containsKey(nombreAlumno)) {
                System.out.println("Lo sentimos, el estudiante no existe.");
                return;
            }

            System.out.print("Por favor, ingresa la nota a evaluar: ");
            int nota = scanner.nextInt();
            if (nota < 0 || nota > 10) {
                System.out.println("Nota inválida. Esta debe estar entre 0 y 10.");
                return;
            }

            if (nota >= 6) {
                System.out.println("La nota: " + nota + " es Aprobatoria.");
            } else {
                System.out.println("La nota: " + nota + " es Reprobatoria.");
            }
        }

        private static void mostrarSobreOdebajoDelPromedio() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Por favor, ingresa el nombre del alumno para comparar su nota con el promedio del curso: ");
            String nombreAlumno = sc.nextLine();

            if (!calificaciones.containsKey(nombreAlumno)) {
                System.out.println("Lo sentimos, ese alumno no existe.");
                return;
            }

            System.out.print("Por favor, ingresa la nota a comparar: ");
            int nota = sc.nextInt();
            if (nota < 0 || nota > 10) {
                System.out.println("Lo sentimos, la nota que has ingresado es invalida. Debe estar entre 0 y 10.");
                return;
            }

            if (nota > promedioCurso) {
                System.out.println("La nota de " + nombreAlumno + " es " + nota + " y esta por sobre el promedio del curso.");
            } else if (nota < promedioCurso) {
                System.out.println("La nota de " + nombreAlumno + " es " + nota + " y esta por debajo del promedio del curso.");
            } else {
                System.out.println("La nota de " + nombreAlumno + " es " + nota + " y es igual al promedio del curso.");
            }
        }
    }

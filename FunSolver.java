import java.awt.*;
import javax.swing.*;
import java.util.Scanner;

public class FunSolver {

    // Método para validar entrada numérica
    public static double validarEntradaNumerica(Scanner sc, String mensaje) {
        double valor = 0;
        boolean esValido;

        do {
            System.out.print(mensaje);
            String input = sc.next(); // Leer entrada como texto
            try {
                valor = Double.parseDouble(input); // Intentar convertir a número
                esValido = true; // Si no hay excepción, la entrada es válida
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido."); // Capturar entradas no numéricas
                esValido = false;
            }
        } while (!esValido);

        return valor;
    }

    // Métodos para resolver y graficar funciones
    public static double resolverFuncionAfin(double m, double n, double x) {
        return m * x + n;
    }

    public static double resolverFuncionLineal(double m, double b, double x) {
        return m * x + b;
    }

    public static double resolverFuncionConstante(double c) {
        return c;
    }

    public static void resolverFuncionCuadratica(double a, double b, double c) {
        double discriminante = b * b - 4 * a * c;
        if (a == 0) {
            System.out.println("Nota: Con a = 0, la función no es cuadrática.");
        } else if (discriminante < 0) {
            System.out.println("La ecuación cuadrática no tiene soluciones reales.");
        } else {
            double x1 = (-b + Math.sqrt(discriminante)) / (2 * a);
            double x2 = (-b - Math.sqrt(discriminante)) / (2 * a);
            System.out.println("Soluciones de la función cuadrática: x1 = " + x1 + ", x2 = " + x2);
        }
    }

    public static void graficar(Funcion funcion, String FunSolver) {
        JFrame frame = new JFrame(FunSolver);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.BLACK);
                int width = getWidth(), height = getHeight();
                int centerX = width / 2, centerY = height / 2;
                g2d.drawLine(0, centerY, width, centerY);
                g2d.drawLine(centerX, 0, centerX, height);
                g2d.setColor(Color.BLUE);
                for (int x = -centerX; x < centerX; x++) {
                    double fx = funcion.calcular(x / 10.0);
                    int px = centerX + x; //convierte la posicion logica de x a una posicion en la pantalla
                    int py = centerY - (int) (fx * 10); //lo que hace el int dentro de parentecis es volver cualquier valor flotante que sea graficante a un valor entero 
                    g2d.fillRect(px, py, 2, 2); //se usa para dibujar un rectangulo solido en la ventana
                }
            }
        });
        frame.setVisible(true);
    }

    interface Funcion {
        double calcular(double x);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú de funciones ---");
            System.out.println("1. Resolver función afín (f(x) = mx + n)");
            System.out.println("2. Resolver función lineal (f(x) = mx + b)");
            System.out.println("3. Resolver función constante (f(x) = c)");
            System.out.println("4. Resolver función cuadrática (f(x) = ax^2 + bx + c)");
            System.out.println("5. Salir");
            System.out.print("Elige una opción: ");

            opcion = (int) validarEntradaNumerica(sc, ""); // Validar opción como número entero

            switch (opcion) {
                case 1:
                    double mAfin = validarEntradaNumerica(sc, "Introduce el valor de m: ");
                    double nAfin = validarEntradaNumerica(sc, "Introduce el valor de n: ");
                    double xAfin = validarEntradaNumerica(sc, "Introduce el valor de x: ");
                    System.out.println("Resultado de la función afín: f(x) = " + resolverFuncionAfin(mAfin, nAfin, xAfin));
                    graficar((x) -> mAfin * x + nAfin, "Función Afín");
                    break;

                case 2:
                    double mLineal = validarEntradaNumerica(sc, "Introduce el valor de m: ");
                    double bLineal = validarEntradaNumerica(sc, "Introduce el valor de b: ");
                    double xLineal = validarEntradaNumerica(sc, "Introduce el valor de x: ");
                    System.out.println("Resultado de la función lineal: f(x) = " + resolverFuncionLineal(mLineal, bLineal, xLineal));
                    graficar((x) -> mLineal * x + bLineal, "Función Lineal");
                    break;

                case 3:
                    double cConstante = validarEntradaNumerica(sc, "Introduce el valor de c: ");
                    System.out.println("Resultado de la función constante: f(x) = " + resolverFuncionConstante(cConstante));
                    graficar((x) -> cConstante, "Función Constante");
                    break;

                case 4:
                    double aCuadratica = validarEntradaNumerica(sc, "Introduce el valor de a: ");
                    double bCuadratica = validarEntradaNumerica(sc, "Introduce el valor de b: ");
                    double cCuadratica = validarEntradaNumerica(sc, "Introduce el valor de c: ");
                    resolverFuncionCuadratica(aCuadratica, bCuadratica, cCuadratica);
                    graficar((x) -> aCuadratica * x * x + bCuadratica * x + cCuadratica, "Función Cuadrática");
                    break;

                case 5:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción no válida. Elige entre 1 y 5.");
            }
        } while (opcion != 5);

        sc.close();
    }
}

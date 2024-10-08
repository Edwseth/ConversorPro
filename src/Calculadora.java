import java.util.Scanner;

public class Calculadora {
    public static void ejecutarCalculadora(Scanner teclado) {
        //Scanner teclado = new Scanner(System.in);
        double num1, num2, resultado;
        int operacion;

        String menu = """
                ********** Calculadora **********
                1. Suma
                2. Resta
                3. Multiplicación
                4. División
                9. Volver al menú principal
                **** Selecciona una operación ****
                """;

        do {
            System.out.println(menu);
            operacion = teclado.nextInt();

            if (operacion >= 1 && operacion <= 4) {
                System.out.print("Introduce el primer número: ");
                num1 = teclado.nextDouble();
                System.out.print("Introduce el segundo número: ");
                num2 = teclado.nextDouble();

                switch (operacion) {
                    case 1:
                        resultado = num1 + num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    case 2:
                        resultado = num1 - num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    case 3:
                        resultado = num1 * num2;
                        System.out.println("Resultado: " + resultado);
                        break;
                    case 4:
                        if (num2 != 0) {
                            resultado = num1 / num2;
                            System.out.println("Resultado: " + resultado);
                        } else {
                            System.out.println("Error: División por cero no permitida.");
                        }
                        break;
                }
            } else if (operacion != 9) {
                System.out.println("Opción no válida. Por favor, elige una operación válida.");
//                operacion = teclado.nextInt();
            }

        }while (operacion != 9);
        System.out.println("Volviendo al menú principal...");
//        teclado.close();
    }
}

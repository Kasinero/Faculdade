import java.util.Scanner;

public class Tabuada2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numero;
        
        System.out.print("Digite um número: ");
        numero = input.nextInt();
        
        while (numero>= 0) {
            System.out.println("Tabuada de " + numero + ":");
            
            for (int i = 1; i <= 10; i++) {
                System.out.println(numero + " x " + i + " = " + (numero * i));
            }
            
            System.out.print("\nDigite outro número: ");
            numero = input.nextInt();
        }
    }
}
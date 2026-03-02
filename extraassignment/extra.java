import java.util.Scanner;

public class extra {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prime number: ");
        int input = sc.nextInt();

        // 5 primes before
        System.out.print("5 Primes Before: ");
        int count = 0;
        int num = input - 1;
        while (count < 5) {
            boolean isPrime = true;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(num + " ");
                count++;
            }
            num--;
        }

        System.out.println("\nYour Number: " + input);

        // 5 primes after
        System.out.print("5 Primes After:  ");
        count = 0;
        num = input + 1;
        while (count < 5) {
            boolean isPrime = true;
            for (int i = 2; i < num; i++) {
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(num + " ");
                count++;
            }
            num++;
        }
    }
}
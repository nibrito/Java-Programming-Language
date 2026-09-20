package prime_number_function;

import java.util.Scanner;

public class PrimeNumber {

    public static boolean isPrime(int n){
        boolean isPrime = true;
        for(int i=2; i<=n-1;i++){  //i<=Math.sqrt(n)
            if(n % i == 0){
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a Number: ");

        int n = input.nextInt();

        System.out.println(isPrime(n));
    }
}

package binomial_coff;
import java.util.*;



public class Binomial {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter n: ");
        int x = input.nextInt();

        System.out.println();

        System.out.print("Enter r: ");
        int y = input.nextInt();

        System.out.println("Binomial Coefficient: "+coefficient(x,y));
    }

    public static int factorial(int n) {
        int  f=1;

        for(int  i=1; i<=n;i++){
            f = f*i;
        }
        return f;
    }

    public static int coefficient(int n, int r) {
        int fact_n =  factorial(n);
        int fact_r = factorial(r);
        int fact_nmr = factorial(n-r);

        return fact_n/(fact_r*fact_nmr);
    }
}
package pattern_print;
import java.util.*;

public class pattern {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number of lines(n): ");
        int n = input.nextInt();

        for(int i=1; i<=n; i++){
            System.out.println("****");
        }
    }
}

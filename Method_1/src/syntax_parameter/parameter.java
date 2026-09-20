package syntax_parameter;
import org.w3c.dom.ls.LSOutput;

import java.util.*;

public class parameter {

    public static int CalculateSum(int x, int y) {

        int sum = x+y;
        return sum;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
        int b = input.nextInt();
        System.out.println("Sum is:"+CalculateSum(a,b));
    }
}

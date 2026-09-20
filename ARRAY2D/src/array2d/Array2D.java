package array2d;
import java.util.*;

public class Array2D {
    public static void main(String[] args) {

        int [][] arr = new int[4][3];

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Element of 4x3 matrix");

        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                System.out.printf(" ELement of arr[%d][%d]: ",i,j);
                arr[i][j]= input.nextInt();
            }
        }

        for(int i=0; i<4; i++){
            for(int j=0; j<3; j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }

    }
}

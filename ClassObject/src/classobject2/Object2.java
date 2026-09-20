package classobject2;
import java.util.Scanner;

public class Object2 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter name: ");
        String n = scanner.nextLine();

        System.out.print("Enter gender:  ");
        String g = scanner.nextLine();


        System.out.print("Enter department:");
        String d = scanner.nextLine();


        System.out.print("Enter ID: ");
        int i = scanner.nextInt();

        System.out.print("Enter age: ");
        int a = scanner.nextInt();

        Student2 student1 = new Student2(n,g,d,i,a);
        student1.displayInfo();
    }
}

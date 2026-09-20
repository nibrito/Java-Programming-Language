package classobject;

public class Object {
    public static void main(String[] args) {

        Student student1 = new Student();

        student1.name = "Patha Samsul";
        student1.gender = "Male";
        student1.department = "CSE";
        student1.age = 22;
        student1.id = 24124646;

        System.out.println("Name: "+student1.name);
        System.out.println("Gender: "+student1.gender);
        System.out.println("Department: "+student1.department);
        System.out.println("Age:  "+student1.age);
        System.out.println("ID: "+student1.id);
    }
}

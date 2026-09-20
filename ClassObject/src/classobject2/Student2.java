package classobject2;

import classobject.Student;

public class Student2 {

    String name,gender,department;
    int id, age;

    Student2(String n, String g, String d, int i,  int a){
        name = n;
        gender = g;
        department = d;
        id  =  i;
        age  = a;
    }


    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);
        System.out.println("Department: " + department);
        System.out.println("Age:  " + age);
        System.out.println("ID: " + id);
    }
}

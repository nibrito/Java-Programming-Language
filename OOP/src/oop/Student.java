package oop;

public class Student {
    String name;
    int age;
    float cgpa;

    void calcPercentage(int phy,int  chem,int math){
        cgpa =  (phy  + chem + math)/3;
    }

}

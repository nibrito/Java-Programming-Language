/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.areaofsquare;

/**
 *
 * @author NIBRITO
 */
import java.util.*;

public class AreaOfSquare {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter Side of a Square:");
        float A = sc.nextFloat();
        
        float Area = A * A;
        System.out.print("Area of the Square:" +Area);
    }
}

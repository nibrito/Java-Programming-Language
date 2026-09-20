/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.avgofthree;

/**
 *
 * @author NIBRITO
 */
import java.util.*;
public class AvgOfThree {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter value of A:");
        int A = sc.nextInt();
        
        System.out.print("Enter value of B:");
        int B = sc.nextInt();
        
        System.out.print("Enter value of C:");
        int C = sc.nextInt();
        
        float avg = (float)(A+B+C)/3;
        System.out.println("The Average of Three Numbers:"+avg);
        
    }
}

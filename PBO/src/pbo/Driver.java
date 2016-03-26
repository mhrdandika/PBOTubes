/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo;

/**
 *
 * @author cadas
 */
import java.util.Scanner;
public class Driver {
    public static void main(String[] args) {
        Aplikasi a = new Aplikasi();
        Scanner s= new Scanner(System.in);
        System.out.println("1. Menu Mahasiswa ");
        System.out.println("2. Menu Dosen");
        System.out.println("3. Exit");
        System.out.println("Masukkan pilihan :");
        int as = s.nextInt();
        switch(as){
            case 1:
                a.mainmenu();
            case 2:
                a.mainmenu1();
            case 3:
                break;
        }
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo;
import java.util.Scanner;

/**
 *
 * @author cadas
 */
public class Aplikasi {
   
    public void mainmenu(){
        Scanner ob = new Scanner(System.in);
        System.out.println("Masukkan Username :");
        String as = ob.nextLine();
        System.out.println("Masukkan password :");
        String b = ob.nextLine();
        Admin ad = new Admin(as,b);
       
        
        System.out.println("Main Menu Admin :");
        System.out.println("1.Add kelas :");
        System.out.println("2.Remove Kelas :");
        System.out.println("3.Add Matkul :");
        System.out.println("4.Remove Matkul :");
        System.out.println("Masukkan pilihan :");
        Scanner pilihan = new Scanner(System.in);
        int a = pilihan.nextInt();
        switch(a){
            case 1:
        System.out.println("Masukkan Nama Dosen :");
        String da = ob.nextLine();
        System.out.println("Masukkan Notel Dosen :");
        int no=ob.nextInt();
        System.out.println("Masukkan Email Dosen :");
        String em= ob.nextLine();
        System.out.println("Masukkan NIP Dosen :");
        Long ni= ob.nextLong();
        System.out.println("Masukkan KD  :");
        String dk= ob.nextLine();
        Dosen d = new Dosen(da,no,em,ni,dk);
        System.out.println("Masukkan Nama Matkul  :");
        String nk= ob.nextLine();
        System.out.println("Masukkan KodeMatkul  :");
        String nm= ob.nextLine();
        System.out.println("Masukkan Jumlah sks  :");
        int sk = ob.nextInt();
        Matkul ma = new Matkul(nk,nm,sk);
                
                 System.out.println("Masukkan id kelas : ");
                 int kel = ob.nextInt();
                 Kelas ke = new Kelas(kel,d,ma);
                 ad.addKelas(ke);
            case 2 :
                System.out.println("Masukkan id kelas yg akan dihapus ");
                int idm = ob.nextInt();
                ad.removeKelas(idm);
            case 3 :
                System.out.println("Masukkan Nama Matkul  :");
                 nk= ob.nextLine();
                System.out.println("Masukkan KodeMatkul  :");
                nm= ob.nextLine();
                System.out.println("Masukkan Jumlah sks  :");
                sk = ob.nextInt();
                 Matkul ms = new Matkul(nk,nm,sk);
                ad.addMatkul(ms);
            case 4  :
                 System.out.println("Masukkan id matkul yg akan dihapus ");
                String idn = ob.nextLine();
                ad.removeMatkul(idn);
                
                
        }
        
    }
    public void mainmenu1(){
       
         Scanner pil = new Scanner(System.in);
         System.out.println("Masukkan nama mahasiswa :");
         String n=pil.nextLine();
         System.out.println("Masukkan email :");
         String na=pil.nextLine();
         System.out.println("Masukkan no telepon :");
        int c=pil.nextInt();
         System.out.println("Masukkan nim :");
        int ca=pil.nextInt();
          Mahasiswa m= new Mahasiswa(n,na,c,ca);
         
        System.out.println("Main Menu Mahasiswa :");
        System.out.println("1.Create kelas :");
        System.out.println("2.Remove Kelas :");
        System.out.println("Masukkan pilihan :");
        int asx = pil.nextInt();
        switch(asx){
        case 1 :
                System.out.println("Masukkan Nama Dosen :");
        String da = pil.nextLine();
        System.out.println("Masukkan Notel Dosen :");
        int no=pil.nextInt();
        System.out.println("Masukkan Email Dosen :");
        String em= pil.nextLine();
        System.out.println("Masukkan NIP Dosen :");
        Long ni= pil.nextLong();
        System.out.println("Masukkan KD  :");
        String dk= pil.nextLine();
        Dosen d = new Dosen(da,no,em,ni,dk);
        System.out.println("Masukkan Nama Matkul  :");
        String nk= pil.nextLine();
        System.out.println("Masukkan KodeMatkul  :");
        String nm= pil.nextLine();
        System.out.println("Masukkan Jumlah sks  :");
        int sk = pil.nextInt();
        Matkul ma = new Matkul(nk,nm,sk);
                
                 System.out.println("Masukkan id kelas : ");
                 int kel = pil.nextInt();
                 Kelas ke = new Kelas(kel,d,ma);
                 m.addKelas(ke);
            case 2 :
                 System.out.println("Masukkan id kelas yg akan dihapus ");
                int idn = pil.nextInt();
                m.removeKelas(idn);
        } 
    }
    
}

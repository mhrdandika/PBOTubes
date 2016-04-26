/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import pbo.Aplikasi;
import pbo.Dosen;
import pbo.Kelas;
import pbo.Mahasiswa;
import pbo.Matkul;

/**
 *
 * @author ASUS
 */
public class Console {
    private Aplikasi model;
    private Scanner sInt;
    private Scanner sStr;

    public Console(Aplikasi model) {
        this.model = model;
        sInt = new Scanner(System.in);
        sStr = new Scanner(System.in);
    }

    public int inputInteger() {
        try {
            return sInt.nextInt();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Input harus berupa angka");
        } finally {
            sInt = new Scanner(System.in);
        }
    }
    
    public void menuAdmin() {
        int pil = 0;
        while (pil != 9){
            try{
                System.out.println("\nMenu Admin");
                System.out.println("1. Add Mahasiswa");
                System.out.println("2. Add Dosen");
                System.out.println("3. Add Matkul");
                System.out.println("4. Add Kelas");
                System.out.println("5. Kelola Mahasiswa");
                System.out.println("6. Kelola Dosen");
                System.out.println("7. Kelola Matkul");
                System.out.println("8. Kelola Kelas");
                System.out.println("9. Keluar");
                System.out.print("Pilihan\t: ");
                pil = inputInteger();
                switch(pil){
                    case 1:{
                        System.out.print("\nNIM\t: ");
                        int nim = inputInteger();
                        if (model.getMahasiswa(nim)==null){
                            System.out.print("Nama\t: ");
                            String nama = sStr.nextLine();
                            System.out.print("Username\t: ");
                            String user = sStr.nextLine();
                            System.out.print("Password\t: ");
                            String pass = sStr.nextLine();
                            model.addMahasiswa(nama, user, pass, nim);
                            System.out.println("Data Berhasil Ditambahkan");
                        } else System.out.println("NIM sudah ada"); 
                        break;
                    }
                    case 2:{
                        System.out.print("\nNIP\t: ");
                        long nim = inputInteger();
                        System.out.print("Nama\t: ");
                        String nama = sStr.nextLine();
                        System.out.print("Kode\t: ");
                        String kode = sStr.nextLine();
                        if (model.getDosen(kode)==null){
                            model.addDosen(nama, nim, kode);
                            System.out.println("Data Berhasil Ditambahkan");
                        } else System.out.println("Kode dosen sudah ada");
                        break;
                    }
                    case 3:{
                        System.out.print("\nKode MK\t: ");
                        String kode = sStr.nextLine();
                        if (model.getMatkul(kode)==null){
                            System.out.print("Nama MK\t: ");
                            String nama = sStr.nextLine();
                            System.out.print("SKS\t: ");
                            int sks = inputInteger();
                            model.addMatKul(kode, nama, sks);
                            System.out.println("Data Berhasil Ditambahkan");
                        } else System.out.println("Kode MK sudah ada");
                        break;
                    }
                    case 4:{
                        System.out.print("\nID Kelas\t: ");
                        String id = sStr.nextLine();
                        if (model.getKelas(id)==null){
                            ArrayList<Dosen> d = model.getListDosen();
                            ArrayList<Matkul> m = model.getListMatkul();
                            System.out.println("Daftar Kode Dosen"); int i = 1;
                            for(Dosen a : d){
                                System.out.print(i+" "+a.getKD()+"\n");
                                i++;
                            }
                            System.out.println("Daftar Kode Matkul"); int j = 1;
                            for(Matkul a : m){
                                System.out.print(j+" "+a.getKodeMK()+"\n");
                                j++;
                            }
                            System.out.print("\nKode Dosen\t: ");
                            String kd = sStr.nextLine();
                            System.out.print("Kode Matkul\t: ");
                            String km = sStr.nextLine();
                            if (model.getDosen(kd)!=null&&model.getMatkul(km)!=null) {
                                model.addKelas(id, model.getDosen(kd), model.getMatkul(km));
                                System.out.println("Data Berhasil Ditambahkan");
                            } else {
                                System.out.println("Data dosen atau matkul tidak ada");
                            }
                        } else System.out.println("ID Kelas sudah ada");
                        break;
                    }
                    case 5:{
                        ArrayList<Mahasiswa> m = model.getListMahasiswa();
                        for (Mahasiswa a : m){
                            System.out.println(a.toString());
                        }
                        System.out.println("\n1. Edit Mahasiswa");
                        System.out.println("2. Delete Mahasiswa");
                        System.out.println("3. Keluar");
                        System.out.print("Pilihan\t: ");
                        int pil2 =inputInteger();
                        switch(pil2){
                            case 1:{
                                System.out.print("\nNIM diedit\t: ");
                                int nim = inputInteger();
                                if (model.getMahasiswa(nim)!=null){
                                    System.out.print("\nNIM baru\t: ");
                                    int nimbaru = inputInteger();
                                    if (model.getMahasiswa(nimbaru)!=null&&!model.getMahasiswa(nimbaru).equals(model.getMahasiswa(nim))){
                                        System.out.print("NIM sudah ada"); 
                                    } else {
                                        System.out.print("Nama\t: ");
                                        String nama = sStr.nextLine();
                                        System.out.print("Username\t: ");
                                        String user = sStr.nextLine();
                                        System.out.print("Password\t: ");
                                        String pass = sStr.nextLine();
                                        model.editMahasiswa(nim, nama, user, pass, nimbaru);
                                        System.out.println("Data Berhasil Diupdate");
                                    }
                                } else System.out.println("NIM tidak ada"); 
                                break;
                            }
                            case 2:{
                                System.out.print("\nNIM dihapus\t: ");
                                int nim = inputInteger();
                                if (model.getMahasiswa(nim)!=null){
                                    model.deleteMahasiswa(nim);
                                    System.out.println("Data Berhasil dihapus");
                                } else System.out.println("NIM tidak ada"); 
                                break;
                            }
                            case 3:{break;}
                            default:{System.out.println("Pilih antara 1-3");}
                        } break;
                    }
                    case 6:{
                        ArrayList<Dosen> m = model.getListDosen();
                        for (Dosen a : m){
                            System.out.println(a.toString());
                        }
                        System.out.println("\n1. Edit Dosen");
                        System.out.println("2. Delete Dosen");
                        System.out.println("3. Keluar");
                        System.out.print("Pilihan\t: ");
                        int pil2 =inputInteger();
                        switch(pil2){
                            case 1:{
                                System.out.print("\nKode diedit\t: ");
                                String kode = sStr.nextLine();
                                if (model.getDosen(kode)!=null){
                                    System.out.print("\nKode Baru\t: ");
                                    String kodeBaru = sStr.nextLine();
                                    if (model.getDosen(kodeBaru)!=null&&!model.getDosen(kodeBaru).equals(model.getDosen(kode))){
                                        System.out.println("Kode Dosen sudah ada"); 
                                    } else {
                                        System.out.print("\nNIP\t: ");
                                        long nim = inputInteger();
                                        System.out.print("Nama\t: ");
                                        String nama = sStr.nextLine();
                                        model.editDosen(kode, nama, nim, kodeBaru);
                                        System.out.println("Data Berhasil Diupdate");
                                    }
                                } else System.out.println("Kode Dosen tidak ada"); 
                                break;
                            }
                            case 2:{
                                System.out.print("\nKode dihapus\t: ");
                                String kode = sStr.nextLine();
                                if (model.getDosen(kode)!=null){
                                    model.deleteDosen(kode);
                                    System.out.println("Data Berhasil dihapus");
                                } else System.out.println("Kode tidak ada"); 
                                break;
                            }
                            case 3:{break;}
                            default:{System.out.println("Pilih antara 1-3");}
                        } break;
                    }
                    case 7:{
                        ArrayList<Matkul> m = model.getListMatkul();
                        for (Matkul a : m){
                            System.out.println(a.toString());
                        }
                        System.out.println("\n1. Edit Matkul");
                        System.out.println("2. Delete Matkul");
                        System.out.println("3. Keluar");
                        System.out.print("Pilihan\t: ");
                        int pil2 =inputInteger();
                        switch(pil2){
                            case 1:{
                                System.out.print("\nKode diedit\t: ");
                                String kode = sStr.nextLine();
                                if (model.getMatkul(kode)!=null){
                                    System.out.print("\nKode Baru\t: ");
                                    String kodeBaru = sStr.nextLine();
                                    if (model.getMatkul(kodeBaru)!=null&&!model.getMatkul(kodeBaru).equals(model.getMatkul(kode))){
                                        System.out.println("Kode Matkul sudah ada"); 
                                    } else {
                                        System.out.print("Nama MK\t: ");
                                        String nama = sStr.nextLine();
                                        System.out.print("SKS\t: ");
                                        int sks = inputInteger();
                                        model.editMatKul(kode, nama, sks, kodeBaru);
                                        System.out.println("Data Berhasil Diupdate");
                                    }
                                } else System.out.println("Kode Matkul tidak ada"); 
                                break;
                            }
                            case 2:{
                                System.out.print("\nKode dihapus\t: ");
                                String kode = sStr.nextLine();
                                if (model.getMatkul(kode)!=null){
                                    model.deleteMatkul(kode);
                                    System.out.println("Data Berhasil dihapus");
                                } else System.out.println("Kode tidak ada"); 
                                break;
                            }
                            case 3:{break;}
                            default:{System.out.println("Pilih antara 1-3");}
                        } break;
                    }
                    case 8:{
                        ArrayList<Kelas> m = model.getListKelas();
                        for (Kelas a : m){
                            System.out.println(a.toString());
                        }
                        System.out.println("\n1. Edit Kelas");
                        System.out.println("2. Delete Kelas");
                        System.out.println("3. Keluar");
                        System.out.print("Pilihan\t: ");
                        int pil2 =inputInteger();
                        switch(pil2){
                            case 1:{
                                System.out.print("\nID diedit\t: ");
                                String id = sStr.nextLine();
                                if (model.getKelas(id)!=null){
                                    System.out.print("\nID baru\t: ");
                                    String idbaru = sStr.nextLine();
                                    if (model.getKelas(idbaru)!=null&&!model.getKelas(idbaru).equals(model.getKelas(id))){
                                        System.out.println("ID Kelas sudah ada"); 
                                    } else {
                                        ArrayList<Dosen> d = model.getListDosen();
                                        ArrayList<Matkul> k = model.getListMatkul();
                                        System.out.println("Daftar Kode Dosen"); int i = 1;
                                        for(Dosen a : d){
                                            System.out.print(i+" "+a.getKD()+"\n");
                                            i++;
                                        }
                                        System.out.println("Daftar Kode Matkul"); int j = 1;
                                        for(Matkul a : k){
                                            System.out.print(j+" "+a.getKodeMK()+"\n");
                                            j++;
                                        }
                                        System.out.print("\nKode Dosen\t: ");
                                        String kd = sStr.nextLine();
                                        System.out.print("\nKode Matkul\t: ");
                                        String km = sStr.nextLine();
                                        if (model.getDosen(kd)!=null&&model.getMatkul(km)!=null) {
                                            model.editKelas(id, model.getDosen(kd), model.getMatkul(km),idbaru);
                                            System.out.println("Data Berhasil Ditambahkan");
                                        } else {
                                            System.out.println("Data dosen atau matkul tidak ada");
                                        }
                                    }
                                } else System.out.println("Kode Kelas tidak ada"); 
                                break;
                            }
                            case 2:{
                                System.out.print("\nID dihapus\t: ");
                                String id = sStr.nextLine();
                                if (model.getKelas(id)!=null){
                                    model.deleteKelas(id);
                                    System.out.println("Data Berhasil dihapus");
                                } else System.out.println("Kode tidak ada"); 
                                break;
                            }
                            case 3:{break;}
                            default:{System.out.println("Pilih antara 1-3");}
                        } break;
                    }
                    case 9:{break;}
                    default:{System.out.println("Pilih antara 1-9");}
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }
    
    public void menuMahasiswa(Mahasiswa m){
        int pil = 0;
        while (pil != 4){
            try {
                System.out.println("\nMenu Mahasiswa");
                System.out.println("1. Tambah Kelas");
                System.out.println("2. View Kelas");
                System.out.println("3. Delete Kelas");
                System.out.println("4. Keluar");
                System.out.print("Pilihan\t: ");
                pil = inputInteger();
                switch(pil){
                    case 1:{
                        System.out.println("Daftar Kelas");
                        ArrayList<Kelas> k = model.getListKelas();
                        for(Kelas a : k){
                            System.out.println(a.toString());
                        }
                        System.out.print("ID kelas diambil\t: ");
                        String id = sStr.nextLine();
                        if (model.getKelas(id)==null){
                            System.out.println("ID Kelas tidak terdaftar");
                        } else if (model.findKelasMhs(m,id)!=-1){
                            System.out.println("Kelas sudah dipilih");
                        } else {
                            model.menuMhsTambahKelas(m,model.getKelas(id));
                        } break;
                    }
                    case 2:{
                        model.menuMhsViewKelas(m);
                        break;
                    }
                    case 3:{
                        System.out.print("ID Kelas dihapus\t: ");
                        String id = sStr.nextLine();
                        if (model.findKelasMhs(m,id)!=-1){
                            model.menuMhsHapusKelas(m,id);
                        } else System.out.println("ID Kelas tidak ada");
                        break;
                    }
                    case 4:{break;}
                    default:{System.out.println("Pilih antara 1-4");}
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }
    
    public void menuUtama(){
        int pil = 0;
        while (pil != 2){
            try {
                System.out.println("\nMenu Utama");
                System.out.println("1. Login");
                System.out.println("2. Keluar");
                System.out.print("Pilihan\t: ");
                pil = inputInteger();
                switch(pil){
                    case 1:{
                        System.out.print("Username\t: ");
                        String user = sStr.nextLine();
                        System.out.print("Password\t: ");
                        String pass = sStr.nextLine();
                        if (user.equals("admin")&&pass.equals("admin")){
                            menuAdmin();
                        } else {
                            Mahasiswa m = model.login(user, pass);
                            if (m != null)
                                menuMahasiswa(m);
                            else
                                System.out.println("Login gagal");
                        }
                    }
                    case 2:{break;}
                    default:{System.out.println("Pilih antara 1-2");}
                }
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            } finally {
                sInt = new Scanner(System.in);
                sStr = new Scanner(System.in);
            }
        }
    }
}

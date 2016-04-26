/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Mahasiswa extends Orang {

    private ArrayList<Kelas> pilihan;
    private int nim;
    private int jumpil;
    private String username;
    private String password;

    public Mahasiswa(String nama, String username, String password, int nim) {
        super(nama);
        this.nim = nim;
        this.username = username;
        this.password = password;
        pilihan = new ArrayList();
        jumpil = 0;
    }

    /**
     * @return the nim
     */
    public int getNim() {
        return nim;
    }

    /**
     * @param nim the nim to set
     */
    public void setNim(int nim) {
        this.nim = nim;
    }

    /**
     * @return the jumpil
     */
    public int getJumpil() {
        return jumpil;
    }

    public void addKelas(Kelas k) {
        pilihan.add(k);
        jumpil = pilihan.size();
    }
    
        /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Kelas> getListKelas(){
        return pilihan;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public int findKelas(String idk){
        int index = -1;
        for (int i = 0; i < jumpil; i++) {
            if (pilihan.get(i).getIdk().equals(idk)) {
                index = i;
            }
        }
        return index;
    }
    
    public void removeKelas(String idk) {
        int index = findKelas(idk);
        if (index != -1) {
            pilihan.remove(index);
            jumpil = pilihan.size();
            System.out.println("Kelas berhasil terhapus");
        }
    }

    public Kelas getKelas(int x) {
        return pilihan.get(x);
    }
    
    public int hitungSks(){
        int jum = 0;
        for (Kelas k : pilihan){
            jum += k.getMatkul().getSks();
        }
        return jum;
    }

    public String toString(){
        return "\nNIM\t\t: "+nim+"\nNama\t\t: "+super.getNama()
                +"\nUsername\t: "+username+"\nPassword\t: "+password;
    }
    
}

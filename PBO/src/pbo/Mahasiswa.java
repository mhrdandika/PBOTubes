/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo;

/**
 *
 * @author ASUS
 */
public class Mahasiswa extends Orang {

    private Kelas[] pilihan;
    private int nim;
    private int jumpil;
    private String username;
    private String password;

    public Mahasiswa(String nama, String username, String password, int nim) {
        super(nama);
        this.nim = nim;
        this.username = username;
        this.password = password;
        pilihan = new Kelas[6];
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
        if (jumpil < 6) {
            pilihan[jumpil] = k;
            jumpil++;
        }
        else System.out.println("Kelas sudah penuh");
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

    public Kelas[] getListKelas(){
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
    
    public int findKelas(int idk){
        int index = -1;
        for (int i = 0; i < jumpil; i++) {
            if (pilihan[i].getIdk() == idk) {
                index = i;
            }
        }
        return index;
    }
    
    public void removeKelas(int idk) {
        int index = findKelas(idk);
        if (index != -1) {
            for (int i = index; i < jumpil - 1; i++) {
                pilihan[i] = pilihan[i + 1];
            }
            jumpil = jumpil - 1;
            System.out.println("Kelas berhasil terhapus");
        }
    }

    public Kelas getKelas(int x) {
        return pilihan[x];
    }

    public String toString(){
        return "\nNIM\t\t: "+nim+"\nNama\t\t: "+super.getNama()
                +"\nUsername\t: "+username+"\nPassword\t: "+password;
    }
    
}

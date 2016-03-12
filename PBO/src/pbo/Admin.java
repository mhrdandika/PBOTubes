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
public class Admin {
    
    private String username;
    private String password;
    private Kelas[] dKelas;
    private Matkul[] dMatkul;
    private int jumkelas;
    private int jumkul;
    
    public void createKelas(int idk, Dosen dosen, Matkul matkul) {
        dKelas[jumkelas] = new Kelas(idk, dosen, matkul);
        jumkelas++;
    }
    
    public void removeKelas(int idk) {
        int index = -1;
        for (int i = 0; i < jumkelas; i++) {
            if (dKelas[i].getIdk() == idk) {
                index = i;
            }
        }
        if (index != -1) {
            for (int i = index; i < jumkelas - 1; i++) {
                jumkelas = jumkelas - 1;
                dKelas[i] = dKelas[i + 1];
            }
            System.out.println("Kelas berhasil terhapus");
        }
    }
    
    public void createMatkul(String KodeMK, String NamaMK, int sks) {
        dMatkul[jumkul] = new Matkul(KodeMK, NamaMK, sks);
        jumkul++;
    }
    
    public void removeMatkul(String KodeMK) {
        int index = -1;
        for (int i = 0; i < jumkul; i++) {
            if (dMatkul[i].getKodeMK() == KodeMK) {
                index = i;
            }
        }
        if (index != -1) {
            for (int i = index; i < jumkul - 1; i++) {
                jumkul = jumkul - 1;
                dKelas[i] = dKelas[i + 1];
            }
            System.out.println("Matkul berhasil terhapus");
        }
    }
    
    public void updateKelas(int idk, Dosen dosen, Matkul matkul) {
        dKelas[idk].setDosen(dosen);
        dKelas[idk].setMatkul(matkul);
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
    
}

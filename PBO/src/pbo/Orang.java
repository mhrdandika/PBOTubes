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
public class Orang {

    private String nama;
    private int notel;
    private String email;

    public Orang(String nama, int notel, String email) {
        this.nama = nama;
        this.notel = notel;
        this.email = email;

    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNotel(int notel) {
        this.notel = notel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNama() {
        return nama;
    }

    public int getNotel() {
        return notel;
    }

    public String getEmail() {
        return email;
    }

}

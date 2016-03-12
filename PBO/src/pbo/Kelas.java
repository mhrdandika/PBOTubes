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
public class Kelas {

    private Dosen dosen;
    private Matkul matkul;

    /**
     * @return the dosen
     */
    public Dosen getDosen() {
        return dosen;
    }

    /**
     * @param dosen the dosen to set
     */
    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    /**
     * @return the matkul
     */
    public Matkul getMatkul() {
        return matkul;
    }

    /**
     * @param matkul the matkul to set
     */
    public void setMatkul(Matkul matkul) {
        this.matkul = matkul;
    }

    public void view() {
        System.out.println("Dosen : " + getDosen());
        System.out.println("Matkul : " + getMatkul());
    }

}

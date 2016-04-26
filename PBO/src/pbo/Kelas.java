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

    private String idk;
    private Dosen dosen;
    private Matkul matkul;

    public Kelas(String idk, Dosen dosen, Matkul matkul) {
        this.idk = idk;
        this.matkul = matkul;
        this.dosen = dosen;
    }

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

    /**
     * @return the idk
     */
    public String getIdk() {
        return idk;
    }

    /**
     * @param idk the idk to set
     */
    public void setIdk(String idk) {
        this.idk = idk;
    }
    
    public String toString(){
        return "ID Kelas\t: "+idk+
                "\nKode Dosen\t: "+dosen.getKD()+
                "\nKode MK\t: "+matkul.getKodeMK();
    }
    
    public String viewShort(){
        return idk+" "+matkul.getKodeMK();
    }
}

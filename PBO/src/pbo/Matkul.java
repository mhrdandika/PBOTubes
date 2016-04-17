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
public class Matkul {
    private String KodeMK;
    private String NamaMK;
    private int sks;
    
    public Matkul(String KodeMK, String NamaMK, int sks){
        this.KodeMK = KodeMK;
        this.NamaMK = NamaMK;
        this.sks = sks;
    }

    /**
     * @return the KodeMK
     */
    public String getKodeMK() {
        return KodeMK;
    }

    /**
     * @param KodeMK the KodeMK to set
     */
    public void setKodeMK(String KodeMK) {
        this.KodeMK = KodeMK;
    }

    /**
     * @return the NamaMK
     */
    public String getNamaMK() {
        return NamaMK;
    }

    /**
     * @param NamaMK the NamaMK to set
     */
    public void setNamaMK(String NamaMK) {
        this.NamaMK = NamaMK;
    }

    /**
     * @return the sks
     */
    public int getSks() {
        return sks;
    }

    /**
     * @param sks the sks to set
     */
    public void setSks(int sks) {
        this.sks = sks;
    }
    
    public String toString(){
        return "Kode MK\t: "+KodeMK+
                "\nNama MK\t: "+NamaMK+
                "\nSKS\t: "+sks;
    }
}

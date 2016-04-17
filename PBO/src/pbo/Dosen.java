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
public class Dosen extends Orang {
    private long nip;
    private String KD;
    
    public Dosen(String nama,long nip, String KD){
        super(nama);
        this.nip = nip;
        this.KD = KD;
    }

    /**
     * @return the nip
     */
    public long getNip() {
        return nip;
    }

    /**
     * @param nip the nip to set
     */
    public void setNip(long nip) {
        this.nip = nip;
    }

    /**
     * @return the KD
     */
    public String getKD() {
        return KD;
    }

    /**
     * @param KD the KD to set
     */
    public void setKD(String KD) {
        this.KD = KD;
    }
    
    public String toString(){
        return "Kode Dosen\t: "+KD+
                "\nNama Dosen\t: "+super.getNama()+
                "\nNIP\t\t: "+nip;
    }
}

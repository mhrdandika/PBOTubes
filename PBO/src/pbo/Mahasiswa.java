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

    public Mahasiswa(String nama, String email, int notel, int nim) {
        super(nama, notel, email);
        this.nim = nim;
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
    
    public void addKelas(Kelas k){
        if (jumpil < 6) {
            pilihan[jumpil] = k;
            jumpil++;
        }
        
    }
    
    public void removeKelas(Kelas id){
        int index = -1;
        for (int i = 0; i < jumpil; i++){
            if (pilihan[i] == id){
                index = i;
            }
        }
        if (index != -1){
            for( int i = index; i < jumpil -1; i++){
                jumpil = jumpil -1;
                pilihan[i] = pilihan[i+1];
            }
            System.out.println("Kelas berhasil terhapus");
        } 
    }
    
    public Kelas getKelas(int x){
        return pilihan[x];
    }

}

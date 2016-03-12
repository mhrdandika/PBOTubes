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
    private String nomer;
    private String email;
    
    public Orang (String nama, String nomer, String email){
        this.nama = nama;
        this.nomer = nomer;
        this.email = email;
                
    }
    
    public void setNama(String nama){
        this.nama = nama;
    }
    
    public void setNomer(String nomer){
        this.nomer = nomer;
    }
    
    public void setEmail(String email){
        this.nama = nama;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getNomer(){
        return nomer;
    }
    
    public String getEmail(){
        return email;
    }
    
}

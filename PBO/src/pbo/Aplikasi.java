/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo;
import database.Database;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author cadas
 */
public class Aplikasi {
    private ArrayList<Dosen> daftarDosen;
    private ArrayList<Mahasiswa> daftarMahasiswa;
    private ArrayList<Kelas> daftarKelas;
    private ArrayList<Matkul> daftarMatkul;
    private Database db;
   
    public Aplikasi(){
        db = new Database();
        daftarDosen = new ArrayList<>();
        daftarDosen = db.getDsn();
        daftarMatkul = new ArrayList<>();
        daftarMatkul = db.getMk();
        daftarKelas = new ArrayList<>();
        daftarKelas = db.getKls(daftarDosen, daftarMatkul);
        daftarMahasiswa = new ArrayList<>();
        daftarMahasiswa = db.getMhs(daftarKelas);
    }
    
    public ArrayList<Dosen> getListDosen(){
        return daftarDosen;
    }
    
    public ArrayList<Matkul> getListMatkul(){
        return daftarMatkul;
    }
    
    public ArrayList<Mahasiswa> getListMahasiswa(){
        return daftarMahasiswa;
    }
    
    public ArrayList<Kelas> getListKelas(){
        return daftarKelas;
    }
    
    public String[] getKodeDosen(){
        String[] s = new String[daftarDosen.size()];
        int i = 0;
        for (Dosen d : daftarDosen){
            s[i] = d.getKD();
            i++;
        }
        return s;
    }
    
    public String[] getKodeMK(){
        String[] s = new String[daftarDosen.size()];
        int i = 0;
        for (Matkul d : daftarMatkul){
            s[i] = d.getKodeMK();
            i++;
        }
        return s;
    }
    
    public void addDosen(String nama, long nip, String KD){
        daftarDosen.add(new Dosen(nama,nip,KD));
        db.saveDsn(nip, nama, KD);
    }
    
    public void addMahasiswa(String nama, String username, String password, int nim){
        System.out.println(username);
        daftarMahasiswa.add(new Mahasiswa(nama,username,password,nim));
        db.saveMhs(nim, nama, username, password);
    }
    
    public void addKelas(String idk, Dosen dosen, Matkul matkul){
        db.saveKls(idk, dosen, matkul);
        daftarKelas.add(new Kelas(idk,dosen,matkul));
    }
    
    public void addMatKul(String KodeMK, String NamaMK, int sks){
        db.saveMk(KodeMK, NamaMK, sks);
        daftarMatkul.add(new Matkul(KodeMK,NamaMK,sks));
    }

    public String[] getListIDKelas(Mahasiswa m){
        String[] s = new String[daftarKelas.size()];
        int i = 0;
        for (Kelas k : daftarKelas){
            if (m.findKelas(k.getIdk())==-1){
                s[i] = k.viewShort();
                i++;
            }
        }
        return s;
    }
    
    public ArrayList<Kelas> getListKelas(Mahasiswa m){
        ArrayList<Kelas> dk = new ArrayList();
        for (Kelas k : daftarKelas){
            if (m.findKelas(k.getIdk())==-1){
                dk.add(k);
            }
        }
        return dk;
    }
    
    public Dosen getDosen(String KD){
        for (Dosen d : daftarDosen){
            if (d.getKD().equals(KD))
                return d;
        }
        return null;
    }
    
    public Mahasiswa getMahasiswa(long nim){
        for (Mahasiswa m : daftarMahasiswa){
            if (m.getNim()==nim)
                return m;
        }
        return null;
    }
    
    public Mahasiswa findMahasiswa(String user){
        for (Mahasiswa m : daftarMahasiswa){
            if (m.getUsername().equals(user))
                return m;
        }
        return null;
    }
    
    public Dosen findDosen(int nip){
        for (Dosen d : daftarDosen){
            if (d.getNip()==nip)
                return d;
        }
        return null;
    }
    
    public Kelas getKelas(String idk){
        for (Kelas k : daftarKelas){
            if (k.getIdk().equals(idk))
                return k;
        }
        return null;
    }
    
    public int findKelas(Dosen d){
        for (Kelas k : daftarKelas){
            if (k.getDosen().equals(d))
                return daftarKelas.indexOf(k);
        }
        return -1;
    }
    
    public int findKelas2(Matkul m){
        for (Kelas k : daftarKelas){
            if (k.getMatkul().equals(m))
            return daftarKelas.indexOf(k);
        }
        return -1;
    }
    
    public Matkul getMatkul(String kodemk){
        for (Matkul m : daftarMatkul){
            if (m.getKodeMK().equals(kodemk))
                return m;
        }
        return null;
    }
    
    public void deleteDosen(String KD){
        daftarDosen.remove(daftarDosen.indexOf(getDosen(KD)));
        db.deleteDsn(KD);
    }
    
    public void deleteMahasiswa(long nim){
        daftarMahasiswa.remove(daftarMahasiswa.indexOf(getMahasiswa(nim)));
        db.deleteMhs(nim);
    }
    
    public void deleteKelas(String idk){
        db.deleteKls(idk);
        daftarKelas.remove(daftarKelas.indexOf(getKelas(idk)));
    }
    
    public void deleteKelas2(String idk){
        for (Mahasiswa m : daftarMahasiswa){
            if (m.findKelas(idk)!=-1){
                m.removeKelas(idk);
            }
        }
    }
    
    public void deleteMatkul(String kodemk){
        db.deleteMk(kodemk);
        daftarMatkul.remove(daftarMatkul.indexOf(getMatkul(kodemk)));
    }
    
    public void editDosen(String KD,String nama, long nip, String KDBaru){
        db.editDsn(nip, nama, KDBaru, KD);
        Dosen d = getDosen(KD);
        d.setNama(nama);
        d.setNip(nip);
        d.setKD(KDBaru);
    }
    
    public void editMahasiswa(int nim,String nama, String username, String password, int nim2){
        db.editMhs(nim2, nama, username, password, nim2);
        Mahasiswa m = getMahasiswa(nim);
        m.setNama(nama);
        m.setUsername(username);
        m.setPassword(password);
        m.setNim(nim2);
    }
    
    public void editKelas(String idk, Dosen dosen, Matkul matkul, String idkBaru){
        db.editKls(idkBaru, dosen, matkul, idk);
        Kelas k = getKelas(idk);
        k.setDosen(dosen);
        k.setMatkul(matkul);
        k.setIdk(idkBaru);
    }
    
    public void editMatKul(String kodeMK, String NamaMK, int sks, String kodeMKbaru){
        db.editMk(kodeMKbaru, NamaMK, sks, kodeMK);
        Matkul m = getMatkul(kodeMK);
        m.setKodeMK(kodeMKbaru);
        m.setNamaMK(NamaMK);
        m.setSks(sks);
    }
    
    public int findKelasMhs(Mahasiswa m, String id){
        return m.findKelas(id);
    }
    
    public ArrayList<Kelas> getListKelasMhs(Mahasiswa m){
        return m.getListKelas();
    }
    
    public Kelas getKelasMhs(Mahasiswa m, String id){
        return m.getKelas(m.findKelas(id));
    }
    
    public void menuMhsTambahKelas(Mahasiswa m, Kelas k){
        db.saveKelasMhs(m, k);
        m.addKelas(k);
    }
    
    public void menuMhsHapusKelas(Mahasiswa m, String idk){
        db.deleteKelasMhs(m, idk);
        m.removeKelas(idk);
    }
    
    public void menuMhsViewKelas(Mahasiswa m){
        for(int i = 0;i < m.getJumpil();i++){
            System.out.println("\nID Kelas\t: "+m.getKelas(i).getIdk());
            System.out.println("Dosen\t: "+m.getKelas(i).getDosen().getKD());
            System.out.println("Kode MK\t: "+m.getKelas(i).getMatkul().getKodeMK());
            System.out.println("Nama MK\t: "+m.getKelas(i).getMatkul().getNamaMK());
        }
    }
    
    public Mahasiswa login(String username, String password){
        for (Mahasiswa m : daftarMahasiswa){
            if(m.getUsername().equals(username)&&m.getPassword().equals(password))
                return m;
        }
        return null;
    }
    
    public int getSKSMhs(Mahasiswa m){
        return m.hitungSks();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbo;
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
   
    public Aplikasi(){
        daftarDosen = new ArrayList<>();
        daftarMahasiswa = new ArrayList<>();
        daftarKelas = new ArrayList<>();
        daftarMatkul = new ArrayList<>();
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
    }
    
    public void addMahasiswa(String nama, String username, String password, int nim){
        System.out.println(username);
        daftarMahasiswa.add(new Mahasiswa(nama,username,password,nim));
    }
    
    public void addKelas(int idk, Dosen dosen, Matkul matkul){
        daftarKelas.add(new Kelas(idk,dosen,matkul));
    }
    
    public void addMatKul(String KodeMK, String NamaMK, int sks){
        daftarMatkul.add(new Matkul(KodeMK,NamaMK,sks));
    }

    public String[] getListIDKelas(){
        String[] s = new String[daftarKelas.size()];
        int i = 0;
        for (Kelas k : daftarKelas){
            s[i] = k.viewShort();
            i++;
        }
        return s;
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
    
    public Kelas getKelas(int idk){
        for (Kelas k : daftarKelas){
            if (k.getIdk()==idk)
                return k;
        }
        return null;
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
    }
    
    public void deleteMahasiswa(long nim){
        daftarMahasiswa.remove(daftarMahasiswa.indexOf(getMahasiswa(nim)));
    }
    
    public void deleteKelas(int idk){
        daftarKelas.remove(daftarKelas.indexOf(getKelas(idk)));
    }
    
    public void deleteMatkul(String kodemk){
        daftarMatkul.remove(daftarMatkul.indexOf(getMatkul(kodemk)));
    }
    
    public void editDosen(String KD,String nama, long nip, String KDBaru){
        Dosen d = getDosen(KD);
        d.setNama(nama);
        d.setNip(nip);
        d.setKD(KDBaru);
    }
    
    public void editMahasiswa(int nim,String nama, String username, String password, int nim2){
        Mahasiswa m = getMahasiswa(nim);
        m.setNama(nama);
        m.setUsername(username);
        m.setPassword(password);
        m.setNim(nim2);
    }
    
    public void editKelas(int idk, Dosen dosen, Matkul matkul, int idkBaru){
        Kelas k = getKelas(idk);
        k.setDosen(dosen);
        k.setMatkul(matkul);
        k.setIdk(idkBaru);
    }
    
    public void editMatKul(String kodeMK, String NamaMK, int sks, String kodeMKbaru){
        Matkul m = getMatkul(kodeMK);
        m.setKodeMK(kodeMKbaru);
        m.setNamaMK(NamaMK);
        m.setSks(sks);
    }
    
    public int findKelasMhs(Mahasiswa m, int id){
        return m.findKelas(id);
    }
    
    public Kelas[] getListKelasMhs(Mahasiswa m){
        return m.getListKelas();
    }
    
    public Kelas getKelasMhs(Mahasiswa m, int id){
        return m.getKelas(m.findKelas(id));
    }
    
    public void menuMhsTambahKelas(Mahasiswa m, Kelas k){
        m.addKelas(k);
    }
    
    public void menuMhsHapusKelas(Mahasiswa m, Kelas k){
        m.removeKelas(k.getIdk());
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
    
    /*
    
    public void mainmenu(){
        Scanner ob = new Scanner(System.in);
        System.out.println("Masukkan Username :");
        String as = ob.nextLine();
        System.out.println("Masukkan password :");
        String b = ob.nextLine();
        Admin ad = new Admin(as,b);
       
        
        System.out.println("Main Menu Admin :");
        System.out.println("1.Add kelas :");
        System.out.println("2.Remove Kelas :");
        System.out.println("3.Add Matkul :");
        System.out.println("4.Remove Matkul :");
        System.out.println("Masukkan pilihan :");
        Scanner pilihan = new Scanner(System.in);
        int a = pilihan.nextInt();
        switch(a){
            case 1:
        System.out.println("Masukkan Nama Dosen :");
        String da = ob.nextLine();
        System.out.println("Masukkan Notel Dosen :");
        int no=ob.nextInt();
        System.out.println("Masukkan Email Dosen :");
        String em= ob.nextLine();
        System.out.println("Masukkan NIP Dosen :");
        Long ni= ob.nextLong();
        System.out.println("Masukkan KD  :");
        String dk= ob.nextLine();
        Dosen d = new Dosen(da,no,em,ni,dk);
        System.out.println("Masukkan Nama Matkul  :");
        String nk= ob.nextLine();
        System.out.println("Masukkan KodeMatkul  :");
        String nm= ob.nextLine();
        System.out.println("Masukkan Jumlah sks  :");
        int sk = ob.nextInt();
        Matkul ma = new Matkul(nk,nm,sk);
                
                 System.out.println("Masukkan id kelas : ");
                 int kel = ob.nextInt();
                 Kelas ke = new Kelas(kel,d,ma);
                 ad.addKelas(ke);
            case 2 :
                System.out.println("Masukkan id kelas yg akan dihapus ");
                int idm = ob.nextInt();
                ad.removeKelas(idm);
            case 3 :
                System.out.println("Masukkan Nama Matkul  :");
                 nk= ob.nextLine();
                System.out.println("Masukkan KodeMatkul  :");
                nm= ob.nextLine();
                System.out.println("Masukkan Jumlah sks  :");
                sk = ob.nextInt();
                 Matkul ms = new Matkul(nk,nm,sk);
                ad.addMatkul(ms);
            case 4  :
                 System.out.println("Masukkan id matkul yg akan dihapus ");
                String idn = ob.nextLine();
                ad.removeMatkul(idn);
                
                
        }
        
    }
    public void mainmenu1(){
        Scanner p = new Scanner(System.in);
         Scanner pil = new Scanner(System.in);
         System.out.println("Masukkan nama mahasiswa :");
         String n=pil.nextLine();
         System.out.println("Masukkan email :");
         String na=pil.nextLine();
         System.out.println("Masukkan no telepon :");
        int c=p.nextInt();
         System.out.println("Masukkan nim :");
        int ca=p.nextInt();
          Mahasiswa m= new Mahasiswa(n,na,c,ca);
         
        System.out.println("Main Menu Mahasiswa :");
        System.out.println("1.Create kelas :");
        System.out.println("2.Remove Kelas :");
        System.out.println("Masukkan pilihan :");
        int asx = p.nextInt();
        switch(asx){
        case 1 :
                System.out.println("Masukkan Nama Dosen :");
        String da = pil.nextLine();
        System.out.println("Masukkan Notel Dosen :");
        int no=p.nextInt();
        System.out.println("Masukkan Email Dosen :");
        String em= pil.nextLine();
        System.out.println("Masukkan NIP Dosen :");
        Long ni= p.nextLong();
        System.out.println("Masukkan KD  :");
        String dk= pil.nextLine();
        Dosen d = new Dosen(da,no,em,ni,dk);
        System.out.println("Masukkan Nama Matkul  :");
        String nk= pil.nextLine();
        System.out.println("Masukkan KodeMatkul  :");
        String nm= pil.nextLine();
        System.out.println("Masukkan Jumlah sks  :");
        int sk = p.nextInt();
        Matkul ma = new Matkul(nk,nm,sk);
                
                 System.out.println("Masukkan id kelas : ");
                 int kel = p.nextInt();
                 Kelas ke = new Kelas(kel,d,ma);
                 m.addKelas(ke);
            case 2 :
                 System.out.println("Masukkan id kelas yg akan dihapus ");
                int idn = p.nextInt();
                m.removeKelas(idn);
        } 
    }
    */
}

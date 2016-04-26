/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pbo.Dosen;
import pbo.Kelas;
import pbo.Mahasiswa;
import pbo.Matkul;

/**
 *
 * @author 7th
 */
public class Database {
    private String dbUser = "root";
    private String dbPass = "0145";
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;

    public Database() {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/dbRegistrasi", dbUser, dbPass);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ResultSet getData(String SQLString) {
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void query(String SQLString) throws SQLException {
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(SQLString);
        } catch (SQLException c) {
            throw new SQLException("Error eksekusi query");
        }
    }
    
    public void saveMhs(int nim, String nama, String username, String password){
        String s = "insert into mahasiswa values("+nim+",'"+username+"','"+password+"','"+nama+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void editMhs(int nim, String nama, String username, String password, int nim2){
        String s = "update mahasiswa set nim = "+nim+", nama = '"+nama+"', username = '"+username
                +"', password = '"+password+"' where nim = "+nim2;
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void deleteMhs(long nim){
        String s = "delete from kelas_pilihan where nim = "+nim;
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        s = "delete from mahasiswa where nim = "+nim;
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public ArrayList<Mahasiswa> getMhs(ArrayList<Kelas> dk){
        ArrayList<Mahasiswa> dm = new ArrayList();
        String s = "select nim, nama, username, password from mahasiswa";
        try {
            ResultSet rs = getData(s);
            while (rs.next()){
                Mahasiswa m = new Mahasiswa(rs.getString("nama"),rs.getString("username"),rs.getString("password"),rs.getInt("nim"));
                dm.add(m);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        s = "select nim, idk from kelas_pilihan";
        try {
            ResultSet rs = getData(s);
            while (rs.next()){
                Mahasiswa m = null;
                for (Mahasiswa h : dm){
                    if (h.getNim()==rs.getInt("nim"))
                        m = h;
                }
                Kelas k = null;
                for (Kelas l : dk){
                    if (l.getIdk().equals(rs.getString("idk")))
                        k = l;
                }
                m.addKelas(k);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return dm;
    }
    
    public void saveDsn(long nip, String nama, String kodedsn){
        String s = "insert into dosen values("+nip+",'"+nama+"','"+kodedsn+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void editDsn(long nip, String nama, String kodedsn, String kode){
        String s = "update dosen set nip = "+nip+", nama = '"+nama+"', kd = '"+kodedsn
                +"' where kd = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void deleteDsn(String kode){
        String s = "delete from dosen where kd = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public ArrayList<Dosen> getDsn(){
        ArrayList<Dosen> dd = new ArrayList();
        String s = "select nip, nama, kd from dosen";
        try {
            ResultSet rs = getData(s);
            while (rs.next()){
                Dosen d = new Dosen(rs.getString("nama"),rs.getLong("nip"),rs.getString("kd"));
                dd.add(d);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return dd;
    }
    
    public void saveMk(String kodemk, String namamk, int sks){
        String s = "insert into matkul values('"+kodemk+"','"+namamk+"',"+sks+")";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void editMk(String kodemk, String namamk, int sks, String kode){
        String s = "update matkul set kodemk = '"+kodemk+"', namamk = '"+namamk+"', sks = "+sks
                +" where kodemk = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void deleteMk(String kode){
        String s = "delete from matkul where kodemk = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public ArrayList<Matkul> getMk(){
        ArrayList<Matkul> dd = new ArrayList();
        String s = "select kodemk, namamk, sks from matkul";
        try {
            ResultSet rs = getData(s);
            while (rs.next()){
                Matkul mk = new Matkul(rs.getString("kodemk"),rs.getString("namamk"),rs.getInt("sks"));
                dd.add(mk);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return dd;
    }
    
    public void saveKls(String idk, Dosen d, Matkul m){
        String s = "insert into kelas values('"+idk+"','"+d.getKD()+"','"+m.getKodeMK()+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void editKls(String idk, Dosen d, Matkul m, String kode){
        String s = "update kelas set idk = '"+idk+"', kd = '"+d.getKD()+"', kodemk = '"+m.getKodeMK()
                +"' where idk = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void deleteKls(String kode){
        String s = "delete from kelas_pilihan where idk = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
        s = "delete from kelas where idk = '"+kode+"'";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public ArrayList<Kelas> getKls(ArrayList<Dosen> d, ArrayList<Matkul> mk){
        ArrayList<Kelas> dd = new ArrayList();
        String s = "select idk, kd, kodemk from kelas";
        try {
            ResultSet rs = getData(s);
            while (rs.next()){
                Dosen sn = null;
                for (Dosen n : d){
                    if (n.getKD().equals(rs.getString("kd")))
                        sn = n;
                }
                Matkul m = null;
                for (Matkul k : mk){
                    if (k.getKodeMK().equals(rs.getString("kodemk")))
                        m = k;
                }
                Kelas k = new Kelas(rs.getString("idk"),sn,m);
                dd.add(k);
            }
        } catch (Exception e){
            e.getStackTrace();
        }
        return dd;
    }
    
    public void saveKelasMhs(Mahasiswa m, Kelas k){
        String s = "insert into kelas_pilihan(nim,idk) values("+m.getNim()+",'"+k.getIdk()+"')";
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
    
    public void deleteKelasMhs(Mahasiswa m, String idk){
        String s = "delete from kelas_pilihan where idk = '"+idk+"' and nim = "+m.getNim();
        try {
            query(s);
        } catch (SQLException ex) {
            ex.getStackTrace();
        }
    }
}

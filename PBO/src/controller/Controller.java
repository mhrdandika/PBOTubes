/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import pbo.*;
import view.*;

/**
 *
 * @author ASUS
 */
public class Controller extends MouseAdapter implements ActionListener{
    private Aplikasi model;
    private PanelContainer view;

    private String currentView;
    private JPanel mainPanel;

    private LoginMenu l;
    private AdminMenu a;
    private MahasiswaMenu m;
    
    private int nomorSeleksi = -1;
    private String kodeSeleksi = "";
    private boolean edit = false;
    private Mahasiswa mhs;
        
    public Controller (Aplikasi model) {
        this.model = model;
        this.view = new PanelContainer();
        view.logoutButtonPressed().setEnabled(false);

        l = new LoginMenu();
        a = new AdminMenu();
        m = new MahasiswaMenu();

        l.addListener(this);
        a.addListener(this);
        m.addListener(this);
        a.addAdapter(this);
        m.addAdapter(this);

        mainPanel = view.getMainPanel();
        mainPanel.add(l, "0");
        mainPanel.add(a, "1");
        mainPanel.add(m, "2");
        currentView = "0";

        view.getCardLayout()
                .show(mainPanel, currentView);
        view.setVisible(true);
        view.addListener(this);
    }
    
    public void actionPerformed(ActionEvent ae) {
        Object source = ae.getSource();
        if (source.equals(view.closeButtonPressed())) {
            System.exit(0);
        } else if (source.equals(view.logoutButtonPressed())){
            currentView = "0";
            view.getCardLayout().show(mainPanel, currentView);
            a.setDosenKelasNull(model.getKodeDosen());
            a.setMKKelasNull(model.getKodeMK());
            view.logoutButtonPressed().setEnabled(false);
        }
        if (currentView.equals("0")){
            if (source.equals(l.loginButtonPressed())) {
                String user = l.getUsername();
                String pass = l.getPassword();
                if (user.equals("admin")&&pass.equals("admin")){
                    currentView = "1";
                    view.getCardLayout().show(mainPanel, currentView);
                    view.logoutButtonPressed().setEnabled(true);
                    a.setTableDosen(model.getListDosen());
                    a.setTableMahasiswa(model.getListMahasiswa());
                    a.setTableMK(model.getListMatkul());
                    a.setTableKls(model.getListKelas());
                    a.setDosenKelas(model.getKodeDosen());
                    a.setMKKelas(model.getKodeMK());
                    l.refresh();
                } else if (user.equals("")||pass.equals(""))
                    JOptionPane.showMessageDialog(view, "Username atau Password tidak boleh kosong", "Login Gagal", JOptionPane.WARNING_MESSAGE);
                else if (model.login(user, pass)!=null){
                    currentView = "2";
                    view.getCardLayout().show(mainPanel, currentView);
                    view.logoutButtonPressed().setEnabled(true);
                    mhs = model.login(user, pass);
                    m.setListKelas(model.getListIDKelas(mhs));
                    m.setTableKls(mhs.getListKelas());
                    m.setLabelSKS("Jumlah SKS : "+model.getSKSMhs(mhs));
                    l.refresh();
                }
                else {
                    JOptionPane.showMessageDialog(view, "Username atau Password anda salah", "Login Gagal", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else if (currentView.equals("1")) {
            //=================================================MENU KELOLA MAHASISWA ============================================//
            if (source.equals(a.getSubmitMhs())){
                try {
                    int nim = a.getNimMhs();
                    String nama = a.getNamaMhs();
                    String user = a.getUsername();
                    String pass = a.getPassword();
                    if (nim == 0||nama.equals("")||user.equals("")||pass.equals(""))
                        JOptionPane.showMessageDialog(view, "Data tidak boleh kosong", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    else if (nomorSeleksi==-1&&model.findMahasiswa(user)!=null)
                        JOptionPane.showMessageDialog(view, "Username sudah digunakan", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    else {
                        if (!edit){
                            if (model.getMahasiswa(nim)==null){
                                model.addMahasiswa(nama, user, pass, nim);
                                a.setTableMahasiswa(model.getListMahasiswa());
                                a.refreshMhs(); nomorSeleksi = -1;
                                JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            }  else JOptionPane.showMessageDialog(view, "NIM sudah ada", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (model.getMahasiswa(nim)==null||model.getMahasiswa(nim).equals(model.getMahasiswa(nomorSeleksi))){
                                if (model.findMahasiswa(user)==null||model.findMahasiswa(user).equals(model.getMahasiswa(nomorSeleksi))){
                                    model.editMahasiswa(nomorSeleksi, nama, user, pass, nim);
                                    a.setTableMahasiswa(model.getListMahasiswa());
                                    a.refreshMhs(); nomorSeleksi = -1; edit = false;
                                    a.getDeleteMhs().setEnabled(true);
                                    JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                } else JOptionPane.showMessageDialog(view, "Username sudah digunakan", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                            } else JOptionPane.showMessageDialog(view, "NIM sudah ada", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                        } 
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "NIM harus berupa angka", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else if (source.equals(a.getEditMhs())){
                if (nomorSeleksi == -1){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Edit Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    a.refreshEditMhs(model.getMahasiswa(nomorSeleksi));
                    a.getDeleteMhs().setEnabled(false);
                    edit = true;
                }
            } else if (source.equals(a.getDeleteMhs())){
                if (nomorSeleksi == -1){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Delete Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    int con = JOptionPane.showConfirmDialog(view, "Anda yakin ingin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (con == JOptionPane.YES_OPTION){
                        model.deleteMahasiswa(nomorSeleksi);
                        a.setTableMahasiswa(model.getListMahasiswa());
                        JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus", "Delete Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        nomorSeleksi = -1;
                    }
                }
            }
            //=================================================MENU KELOLA DOSEN ============================================//
            else if (source.equals(a.getSubmitDsn())){
                try {
                    int nip = a.getNip();
                    String nama = a.getNamaDosen();
                    String kode = a.getKodeDosen();
                    if (nip == 0||nama.equals("")||kode.equals(""))
                        JOptionPane.showMessageDialog(view, "Data tidak boleh kosong", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    else if (kodeSeleksi.equals("")&&model.findDosen(nip)!=null)
                        JOptionPane.showMessageDialog(view, "NIP sudah digunakan", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    else {
                        if (!edit){
                            if (model.getDosen(kode)==null){
                                if (model.findDosen(nip)==null) {
                                    model.addDosen(nama, nip, kode);
                                    a.setTableDosen(model.getListDosen());
                                    a.refreshDsn(); kodeSeleksi = ""; a.addDosenKelas(kode);
                                    JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                } else JOptionPane.showMessageDialog(view, "NIP sudah terdaftar", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                            }  else JOptionPane.showMessageDialog(view, "Kode Dosen sudah digunakan", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (model.getDosen(kode)==null||model.getDosen(kode).equals(model.getDosen(kodeSeleksi))){
                                if (model.findDosen(nip)==null||model.findDosen(nip).equals(model.getDosen(kodeSeleksi))){
                                    model.editDosen(kodeSeleksi, nama, nip, kode);
                                    a.setTableDosen(model.getListDosen());
                                    a.refreshDsn(); kodeSeleksi = ""; edit = false; a.addDosenKelas(kode);
                                    a.getDeleteDsn().setEnabled(true);
                                    JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                                } else JOptionPane.showMessageDialog(view, "NIP sudah terdaftar", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                            } else JOptionPane.showMessageDialog(view, "Kode Dosen sudah digunakan", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                        } 
                    }
                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(view, "NIP harus berupa angka", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                }
            } else if (source.equals(a.getEditDsn())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Edit Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    a.refreshEditDsn(model.getDosen(kodeSeleksi));
                    a.deleteDosenKelas(kodeSeleksi);
                    a.getDeleteDsn().setEnabled(false);
                    edit = true;
                }
            } else if (source.equals(a.getDeleteDsn())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Delete Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    int con = JOptionPane.showConfirmDialog(view, "Anda yakin ingin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (con == JOptionPane.YES_OPTION){
                        if (model.findKelas(model.getDosen(kodeSeleksi))==-1){
                            model.deleteDosen(kodeSeleksi);
                            a.setTableDosen(model.getListDosen());
                            a.deleteDosenKelas(kodeSeleksi);
                            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus", "Delete Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            kodeSeleksi = "";
                        } else {
                            JOptionPane.showMessageDialog(view, "Dosen sudah diset pada kelas", "Hapus Gagal", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }
            //=============================================== MENU KELOLA MATAKULIAH ============================================//
            else if (source.equals(a.getSubmitMK())){
                int sks = a.getSKS();
                String nama = a.getNamaMK();
                String kode = a.getKodeMK();
                if (sks <= 0||nama.equals("")||kode.equals(""))
                    JOptionPane.showMessageDialog(view, "Data tidak boleh kosong", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                else {
                    if (!edit){
                        if (model.getMatkul(kode)==null){
                            model.addMatKul(kode, nama, sks);
                            a.setTableMK(model.getListMatkul());
                            a.refreshMK(); kodeSeleksi = ""; a.addMKKelas(kode);
                            JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        }  else JOptionPane.showMessageDialog(view, "Kode MK sudah ada", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (model.getMatkul(kode)==null||model.getMatkul(kode).equals(model.getMatkul(kodeSeleksi))){
                            model.editMatKul(kodeSeleksi, nama, sks, kode);
                            a.setTableMK(model.getListMatkul()); a.addMKKelas(kode);
                            a.refreshMK(); kodeSeleksi = ""; edit = false;
                            a.getDeleteMK().setEnabled(true);
                            JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        } else JOptionPane.showMessageDialog(view, "Kode MK sudah ada", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    } 
                }
            } else if (source.equals(a.getEditMK())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Edit Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    a.refreshEditMK(model.getMatkul(kodeSeleksi));
                    a.deleteMKKelas(kodeSeleksi);
                    a.getDeleteMK().setEnabled(false);
                    edit = true;
                }
            } else if (source.equals(a.getDeleteMK())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Delete Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    int con = JOptionPane.showConfirmDialog(view, "Anda yakin ingin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (con == JOptionPane.YES_OPTION){
                        if (model.findKelas2(model.getMatkul(kodeSeleksi))==-1){
                            model.deleteMatkul(kodeSeleksi);
                            a.setTableMK(model.getListMatkul());
                            a.deleteMKKelas(kodeSeleksi);
                            JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus", "Delete Berhasil", JOptionPane.INFORMATION_MESSAGE);
                            kodeSeleksi = "";
                        } else JOptionPane.showMessageDialog(view, "Mata kuliah sudah diset dalam kelas", "Hapus Gagal", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            //================================================= MENU KELOLA KELAS ==================================================//
            else if (source.equals(a.getSubmitKls())){
                String kode = a.getKodeKls();
                String kodedsn = a.getDosenKelas();
                String kodemk = a.getMKKelas();
                if (kode.equals(""))
                    JOptionPane.showMessageDialog(view, "Data tidak boleh kosong", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                else {
                    if (!edit){
                        if (model.getKelas(kode)==null){
                            model.addKelas(kode, model.getDosen(kodedsn), model.getMatkul(kodemk));
                            a.setTableKls(model.getListKelas());
                            a.refreshKls(); kodeSeleksi = "";
                            JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        }  else JOptionPane.showMessageDialog(view, "Kode Kelas sudah ada", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    } else {
                        if (model.getKelas(kode)==null||model.getKelas(kode).equals(model.getKelas(kodeSeleksi))){
                            model.editKelas(kodeSeleksi, model.getDosen(kodedsn), model.getMatkul(kodemk), kode);
                            a.setTableKls(model.getListKelas());
                            a.refreshKls(); kodeSeleksi = ""; edit = false;
                            a.getDeleteKls().setEnabled(true);
                            JOptionPane.showMessageDialog(view, "Data Berhasil Disubmit", "Submit Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        } else JOptionPane.showMessageDialog(view, "Kode Kelas sudah ada", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                    } 
                }
            } else if (source.equals(a.getEditKls())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Edit Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    a.refreshEditKelas(model.getKelas(kodeSeleksi));
                    a.getDeleteKls().setEnabled(false);
                    edit = true;
                }
            } else if (source.equals(a.getDeleteKls())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Hapus Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    int con = JOptionPane.showConfirmDialog(view, "Anda yakin ingin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (con == JOptionPane.YES_OPTION){
                        model.deleteKelas2(kodeSeleksi);
                        model.deleteKelas(kodeSeleksi);
                        a.setTableKls(model.getListKelas());
                        JOptionPane.showMessageDialog(view, "Data Berhasil Dihapus", "Hapus Berhasil", JOptionPane.INFORMATION_MESSAGE);
                        kodeSeleksi = "";
                    }
                }
            }
        }
        //============================================================== VIEW MAHASISWA =================================================================//
        else if (currentView.equals("2")) {
            if (source.equals(m.buttonSubmitPressed())){
                if (nomorSeleksi==-1){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                } else if (model.getSKSMhs(mhs) + model.getListKelas(mhs).get(nomorSeleksi).getMatkul().getSks() > 20) {
                    JOptionPane.showMessageDialog(view, "Anda telah mencapai limit SKS (20)", "Submit Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    model.menuMhsTambahKelas(mhs, model.getListKelas(mhs).get(nomorSeleksi));
                    m.setLabelSKS("Jumlah SKS : "+model.getSKSMhs(mhs));
                    m.setTableKls(model.getListKelasMhs(mhs));
                    m.setListKelas(model.getListIDKelas(mhs));
                    nomorSeleksi = -1;
                }
            } else if (source.equals(m.buttonDeletePressed())){
                if (kodeSeleksi.equals("")){
                    JOptionPane.showMessageDialog(view, "Anda belum memilih data", "Delete Gagal", JOptionPane.WARNING_MESSAGE);
                } else {
                    int con = JOptionPane.showConfirmDialog(view, "Anda yakin ingin hapus data?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                    if (con == JOptionPane.YES_OPTION){
                        model.menuMhsHapusKelas(mhs, kodeSeleksi);
                        m.setLabelSKS("Jumlah SKS : "+model.getSKSMhs(mhs));
                        m.setTableKls(model.getListKelasMhs(mhs));
                        m.setListKelas(model.getListIDKelas(mhs));
                        kodeSeleksi = "";
                    }
                }
            }
        }
    }
    
    public void mousePressed(MouseEvent e) {
        Object source = e.getSource();
        if (currentView.equals("1")) {
            if(source.equals(a.getDaftarMhs())){
                nomorSeleksi = model.getListMahasiswa().get(a.getSelectedMhs()).getNim();
            } else if(source.equals(a.getDaftarDsn())){
                kodeSeleksi = model.getListDosen().get(a.getSelectedDsn()).getKD();
            } else if(source.equals(a.getDaftarMK())){
                kodeSeleksi = model.getListMatkul().get(a.getSelectedMK()).getKodeMK();
            } else if(source.equals(a.getDaftarKls())){
                kodeSeleksi = model.getListKelas().get(a.getSelectedKls()).getIdk();
            }
        } else if (currentView.equals("2")) {
            if (source.equals(m.getListKelas())){
                nomorSeleksi = m.getSelectedKelas();
            } else if (source.equals(m.getTableKelas())){
                kodeSeleksi = model.getListKelasMhs(mhs).get(m.getSelectedKelas2()).getIdk();
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import DAOdatarental.datarentalDAO;
import DAOimplement.datarentalimplement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.*;
import view.*;

public class datarentalcontroller {
    frameTampilData frame1;
    frameInputData frame2;
    frameEditData frame3;
    datarentalimplement impldatarental;
    List<datarental> all;

    public datarentalcontroller(frameTampilData frame1) {
        this.frame1 = frame1;
        this.impldatarental = new datarentalDAO();
        all = impldatarental.getAll();
    }

    public datarentalcontroller(frameInputData frame2) {
        this.frame2 = frame2;
        this.impldatarental = new datarentalDAO();
        all = impldatarental.getAll();
    }
    
    public datarentalcontroller(frameEditData frame3) {
        this.frame3 = frame3;
        this.impldatarental = new datarentalDAO();
        all = impldatarental.getAll();
    }

    public void isitabel() {
        modeltabeldatarental mp = new modeltabeldatarental(all);
        frame1.getTabelDatarental().setModel(mp);
    }
    
//    public void insert(){
//        try {
//            // Membuat "mahasiswa baru" yang isinya masih kosong
//            datarental dp = new datarental();
//            
//            /*
//              Mengambil input nama dan nim menggunakan getter yang telah dibuat di view
//              Nilai dari input kemudian disimpan ke dalam variabel "nama" dan "nim".
//            */
//            JTextField nama_customer = frame2.getJTxtNamaCustomer();
//            
//
//            /*
//              Mengecek apakah input dari nama atau nim kosong/tidak.
//              Jika kosong, maka buatlah sebuah exception.
//             */
//            
//            // Mengisi nama dan nim dari "mahasiswa baru" yang dibuat tadi.
//            dp.setJTxtNamaCustomer(nama_customer);
//            DosenBaru.setNidn(nidn);
//            
//            // Memasukkan "mahasiswa baru" ke dalam database.
//            daoDosen.Insert(DosenBaru);
//            
//            // Menampilkan pop-up ketika berhasil mengedit data
//            JOptionPane.showMessageDialog(null, "Dose baru berhasil ditambahkan.");
//
//            halamanInput.dispose();
//            new ViewDataDosen();
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
//        }
//    
//    }

    public void insert() {
        if (frame2 != null) {
            datarental dp = new datarental();
            dp.setNama_customer(frame2.getJTxtNamaCustomer().getText());
            dp.setNo_telp_customer(frame2.getJTxtnoHp().getText());

            if (frame2.getJradiobtn1().isSelected()) {
                dp.setTipe_kendaraan("Mobil");
            } else {
                dp.setTipe_kendaraan("Motor");
            }

            dp.setNama_kendaraan(frame2.getJDropKendaraan().getSelectedItem().toString());
            try {
                int biayaPerJam = Integer.parseInt(frame2.getJFieldBiayaPerjam().getText());
                int lamaSewa = Integer.parseInt(frame2.getJFieldLamaSewa().getText());
                dp.setBiaya_perjam(biayaPerJam);
                dp.setLama_waktu_sewa(lamaSewa);

                impldatarental.insert(dp);

                if (frame1 != null) {
                    isitabel();
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.err.println("Error: frame2 is null");
        }
    }
    
    public void update(int koderental){
        if (frame3 != null) {
            datarental dp = new datarental();
            dp.setKode_rental(koderental);
            dp.setNama_customer(frame3.getJTxtNamaCustomer().getText());
            dp.setNo_telp_customer(frame3.getJTxtnoHp().getText());

            if (frame3.getJradiobtn1().isSelected()) {
                dp.setTipe_kendaraan("Mobil");
            } else {
                dp.setTipe_kendaraan("Motor");
            }

            dp.setNama_kendaraan(frame3.getJDropKendaraan().getSelectedItem().toString());
            try {
                int biayaPerJam = Integer.parseInt(frame3.getJFieldBiayaPerjam().getText());
                int lamaSewa = Integer.parseInt(frame3.getJFieldLamaSewa().getText());

                dp.setBiaya_perjam(biayaPerJam);
                dp.setLama_waktu_sewa(lamaSewa);

                impldatarental.update(dp);

                if (frame1 != null) {
                    isitabel();
                }

            } catch (NumberFormatException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            System.err.println("Error: frame3 is null");
        }
        
    
}

    public void delete() {
        int selectedRow = frame1.getTabelDatarental().getSelectedRow();
        if (selectedRow != -1) {
            int kode_rental = (int) frame1.getTabelDatarental().getValueAt(selectedRow, 0);

            impldatarental.delete(kode_rental);
            isitabel();
        } else {
            System.err.println("Error: No row selected");
        }
    }
    
    
    public void search(String keyword) {
    List<datarental> result = impldatarental.search(keyword); 
    modeltabeldatarental mp = new modeltabeldatarental(result);
    if (frame1 != null) {
        frame1.getTabelDatarental().setModel(mp);
    }
}
    
}



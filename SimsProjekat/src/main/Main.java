package main;

import dao.IzvodjenjeDAO;
import gui.elementi.GlavniProzor;
import gui.dialogs.DialogPrijave;
import javax.swing.*;

import gui.elementi.NoviProzor;
import gui.elementi.UrednikovProzor;
import model.*;
import util.FConnection;
import java.io.*;
import java.sql.Connection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        //Deo koda koji se pokrece prvi put nakon inicijalizacije baze, nakon toga zakomentarisi
        /*String sep = System.getProperty("file.separator");
        IzvodjenjeDAO.updateSliku(1, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"klinka.jpg");
        IzvodjenjeDAO.updateSliku(2, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"zareGoci.jpg");
        IzvodjenjeDAO.updateSliku(3, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"rasta.jpg");
        IzvodjenjeDAO.updateSliku(4, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"aca.jpg");
        IzvodjenjeDAO.updateSliku(5, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"tuturutu.jpg");
        IzvodjenjeDAO.updateSliku(6, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"maja.jpg");
        IzvodjenjeDAO.updateSliku(7, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"haris.jpg");
        IzvodjenjeDAO.updateSliku(8, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"baja.jpg");
        IzvodjenjeDAO.updateSliku(9, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"hurricane.jpg");
        IzvodjenjeDAO.updateSliku(10, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"nastup.jpg");*/
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<Recenzija> lista = new ArrayList<Recenzija>();
                RegistrovaniKorisnik autorRecenzije = new RegistrovaniKorisnik();
                autorRecenzije.setId(5);
                Izvodjenje izvodnjenje = new Izvodjenje();
                izvodnjenje.setId(3);
                Urednik urednik = new Urednik();
                urednik.setId(3);
                MuzickoDelo delo = new MuzickoDelo();
                delo.setNazivDela("lala");
                Recenzija r = new Recenzija(1,10,"top",autorRecenzije,izvodnjenje,urednik,delo);
                lista.add(r);
                UrednikovProzor u = new UrednikovProzor(lista);
                u.setVisible(true);
                //GlavniProzor gp = new GlavniProzor();
                //gp.setVisible(true);
            }
        });
    }
}

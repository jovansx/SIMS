package main;

import dao.IzvodjenjeDAO;
import dao.ReklamaDAO;
import gui.elementi.GlavniProzor;
import kontroler.ToplistaKON;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {

        //Deo koda koji se pokrece prvi put nakon inicijalizacije baze, nakon toga zakomentarisi
        String sep = System.getProperty("file.separator");
/*
        IzvodjenjeDAO.updateSliku(1, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"majaBuba.jpg");
        IzvodjenjeDAO.updateSliku(2, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"majaJala.jpg");
        IzvodjenjeDAO.updateSliku(3, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"majaBroj.jpg");
        IzvodjenjeDAO.updateSliku(4, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"klinka.jpg");
        IzvodjenjeDAO.updateSliku(5, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"tuturutu.jpg");
        IzvodjenjeDAO.updateSliku(6, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"favorito.jpg");
        IzvodjenjeDAO.updateSliku(7, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"kupacica.jpg");
        IzvodjenjeDAO.updateSliku(8, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"baraba.jpg");
        IzvodjenjeDAO.updateSliku(9, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"prljavoKazaliste.png");
        IzvodjenjeDAO.updateSliku(10, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"tuNoc.jpg");
        IzvodjenjeDAO.updateSliku(11, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"indigo.jpg");
        ReklamaDAO.updateSliku(1, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"coca.jpg");
        ReklamaDAO.updateSliku(2, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"nike.jpg");
        ReklamaDAO.updateSliku(3, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"mc.jpg");
        ReklamaDAO.updateSliku(4, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"instagram.jpg");

        IzvodjenjeDAO.updateAudio(1, "AudioBaza" + sep + "majaBuba.mp3");
        IzvodjenjeDAO.updateAudio(2, "AudioBaza" + sep + "majaJala.mp3");
        IzvodjenjeDAO.updateAudio(3, "AudioBaza" + sep + "majaBroj.mp3");
        IzvodjenjeDAO.updateAudio(4, "AudioBaza" + sep + "klinka.mp3");
        IzvodjenjeDAO.updateAudio(5, "AudioBaza" + sep + "tuturutu.mp3");
        IzvodjenjeDAO.updateAudio(6, "AudioBaza" + sep + "favorito.mp3");
        IzvodjenjeDAO.updateAudio(7, "AudioBaza" + sep + "kupacica.mp3");
        IzvodjenjeDAO.updateAudio(8, "AudioBaza" + sep + "baraba.mp3");
        IzvodjenjeDAO.updateAudio(9, "AudioBaza" + sep + "prljavoKazaliste.mp3");
        IzvodjenjeDAO.updateAudio(10, "AudioBaza" + sep + "tuNoc.mp3");
        IzvodjenjeDAO.updateAudio(11, "AudioBaza" + sep + "indigo.mp3");

*/

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
                GlavniProzor gp = null;
                try {
                    LocalDate now = LocalDate.now();
                    if(now.getDayOfMonth() == 2){
                        ToplistaKON.napraviMesecnuToplistu(now.getMonthValue(),now.getYear());
                    }else if(now.getDayOfMonth() == 1 && now.getMonthValue() == 1){
                        ToplistaKON.napraviGodisnjuToplistu(now.getYear());
                    }
                    gp = new GlavniProzor();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                gp.setVisible(true);
            }
        });
    }
}

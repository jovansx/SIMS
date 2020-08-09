package main;

import dao.IzvodjenjeDAO;
import dao.ReklamaDAO;
import gui.elementi.GlavniProzor;
import jaco.mp3.player.MP3Player;
import model.Izvodjenje;
import util.FConnection;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        //Deo koda koji se pokrece prvi put nakon inicijalizacije baze, nakon toga zakomentarisi

        //String sep = System.getProperty("file.separator");
        /*IzvodjenjeDAO.updateSliku(1, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"klinka.jpg");
        IzvodjenjeDAO.updateSliku(2, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"zareGoci.jpg");
        IzvodjenjeDAO.updateSliku(3, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"rasta.jpg");
        IzvodjenjeDAO.updateSliku(4, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"aca.jpg");
        IzvodjenjeDAO.updateSliku(5, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"tuturutu.jpg");
        IzvodjenjeDAO.updateSliku(6, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"maja.jpg");
        IzvodjenjeDAO.updateSliku(7, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"haris.jpg");
        IzvodjenjeDAO.updateSliku(8, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"baja.jpg");
        IzvodjenjeDAO.updateSliku(9, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"hurricane.jpg");
        IzvodjenjeDAO.updateSliku(10, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneIzvodjenja" + sep +"nastup.jpg");
        ReklamaDAO.updateSliku(1, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"coca.jpg");
        ReklamaDAO.updateSliku(2, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"nike.jpg");
        ReklamaDAO.updateSliku(3, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"mc.jpg");
        ReklamaDAO.updateSliku(4, "SimsProjekat" + sep + "src" + sep + "gui" + sep + "icons" + sep + "ikoneReklama" + sep +"instagram.jpg");
*/
        /*String path = "AudioBaza" + sep + "tuturutu.mp3";
        IzvodjenjeDAO.updateAudio(1, path);
        IzvodjenjeDAO.updateAudio(2, path);
        IzvodjenjeDAO.updateAudio(3, path);
        IzvodjenjeDAO.updateAudio(4, path);
        IzvodjenjeDAO.updateAudio(5, path);
        IzvodjenjeDAO.updateAudio(6, path);
        IzvodjenjeDAO.updateAudio(7, path);
        IzvodjenjeDAO.updateAudio(8, path);
        IzvodjenjeDAO.updateAudio(9, path);
        IzvodjenjeDAO.updateAudio(10, path);
        IzvodjenjeDAO.updateAudio(11, path);*/
        //IzvodjenjeDAO.getAudioIzvodjenja(1, sep);
        //new MP3Player(new File("AudioBaza" + sep + "audio.mp3")).play();

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
                GlavniProzor gp = new GlavniProzor();
                gp.setVisible(true);
            }
        });
    }
}

package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.PlejListaDAO;
import gui.dialogs.DialogIzvodjenjaUPlejlisti;
import gui.dialogs.DialogPlaylisti;
import jaco.mp3.player.MP3Player;
import model.Izvodjac;
import model.Izvodjenje;
import model.MuzickoDelo;
import model.PlejLista;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;

public class ElementIzvodjenjaUPlejlisti extends JPanel {

    private DialogIzvodjenjaUPlejlisti dialog;
    private JLabel trajanje, muzickoDelo,slika,izvodjaci;
    private JButton pusti, pauziraj, stopiraj, izbrisi;
    private Izvodjenje izvodjenje;
    private MP3Player player;
    private File file;
    private String sep;
    private PlejLista plejlista;

    public ElementIzvodjenjaUPlejlisti(DialogIzvodjenjaUPlejlisti dialog, Izvodjenje izvodjenje, PlejLista p){
        this.dialog=dialog;
        this.izvodjenje=izvodjenje;
        this.plejlista=p;

        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setPreferredSize(new Dimension(500, 200));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setLayout(null);
        namesti();
        podesiAkcije();
    }

    private void namesti() {
        sep = System.getProperty("file.separator");
        player = new MP3Player(new File("AudioBaza" + sep + "audio.mp3"));

        String separator = System.getProperty("file.separator");
        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(izvodjenje, separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(100, 75 , Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);

        slika = new JLabel(newImage);
        slika.setBounds(30, 50, 100, 75);
        add(slika);

        muzickoDelo= new JLabel("Muzicko delo/a: "+generisiNazivDela(izvodjenje));
        muzickoDelo.setBounds(150, 30, 300, 30);
        add(muzickoDelo);

        izvodjaci=new JLabel("Izvodjaci: "+generisiIzvodjaceIzvodjenja(izvodjenje));
        izvodjaci.setBounds(150, 70, 300, 30);
        add(izvodjaci);

        trajanje=new JLabel("Trajanje: "+izvodjenje.getTrajanje());
        trajanje.setBounds(150,110, 100, 20 );
        add(trajanje);

        pusti=new JButton("Pusti");
        pusti.setBounds(150, 140, 70, 30);
        pusti.setBackground(new Color(0, 77, 102));
        pusti.setForeground(Color.white);
        add(pusti);

        pauziraj=new JButton("Pauziraj");
        pauziraj.setBounds(230, 140, 80, 30);
        pauziraj.setBackground(new Color(0, 77, 102));
        pauziraj.setForeground(Color.white);
        add(pauziraj);

        stopiraj=new JButton("Zaustavi");
        stopiraj.setBounds(320, 140, 80, 30);
        stopiraj.setBackground(new Color(0, 77, 102));
        stopiraj.setForeground(Color.white);
        add(stopiraj);

        izbrisi=new JButton("Izbrisi");
        izbrisi.setBounds(410, 140, 80, 30);
        izbrisi.setBackground(new Color(0, 77, 102));
        izbrisi.setForeground(Color.white);
        add(izbrisi);

    }

    public void podesiAkcije(){

        dialog.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                player.stop();
                if(file != null) file.delete();
            }
        });
        pusti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file == null)
                    file = IzvodjenjeDAO.getAudioIzvodjenja(izvodjenje.getId(), sep);
                player.play();
                pauziraj.setEnabled(true);
                stopiraj.setEnabled(true);
                pusti.setEnabled(false);
            }
        });

        pauziraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.pause();
                pauziraj.setEnabled(false);
                stopiraj.setEnabled(true);
                pusti.setEnabled(true);
            }
        });

        stopiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stop();
                pauziraj.setEnabled(false);
                stopiraj.setEnabled(false);
                pusti.setEnabled(true);
            }
        });

        izbrisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int retVal=JOptionPane.showConfirmDialog(null, "Zelite li da izbrisete ovo izvodjenje?", "Da li ste sigurni", JOptionPane.YES_NO_OPTION);
                if(retVal==0){
                    try {
                        PlejListaDAO.updateIzvodjenje(plejlista, izvodjenje);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }

    public static String generisiNazivDela(Izvodjenje izvodjenje) {
        StringBuilder name = new StringBuilder();
        for (MuzickoDelo mz : izvodjenje.getListaMuzickihDela()) {
            name.append(mz.getNazivDela()).append(",");
        }
        name = new StringBuilder(name.substring(0, name.length() - 1));
        return name.toString();
    }

    public static String generisiIzvodjaceIzvodjenja(Izvodjenje izvodjenje) {
        StringBuilder izvodjaci = new StringBuilder();
        for (Izvodjac izv : izvodjenje.getListaIzvodjaca()) {
            izvodjaci.append(izv.getNazivIzvodjaca()).append(",");
        }
        String line = izvodjaci.toString();
        if (!line.equals("")) {
            line = line.substring(0, line.length() - 1);
        }
        return line;
    }


}

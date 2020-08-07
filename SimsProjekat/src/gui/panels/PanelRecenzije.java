package gui.panels;

import gui.dialogs.DialogRecenzije;
import gui.elementi.GlavniProzor;
import gui.elementi.KorisnikovProzor;
import gui.elementi.UrednikovProzor;
import gui.enums.TipRecenzije;
import kontroler.RecenzijaKON;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRecenzije extends JPanel implements ActionListener {

    private GlavniProzor glavniProzor;
    private DialogRecenzije dialog;
    private String separator;
    private JLabel komentarL, ocenaL, upozorenjeL;
    private JTextArea komentarArea;
    private JButton potvrdiButton, zvezdaB1, zvezdaB2, zvezdaB3, zvezdaB4, zvezdaB5;
    private Icon zuta, bela;
    private int ocena;
    private int idIzvodjenja;

    public PanelRecenzije(DialogRecenzije dr, int id, GlavniProzor gp) {

        this.idIzvodjenja = id;
        this.dialog = dr;
        this.glavniProzor = gp;
        separator = System.getProperty("file.separator");

        setBackground(new Color(188, 204, 111));
        podesiKomponente();
        setOkvir();
        podesiLayout();
    }

    private void podesiKomponente() {

        komentarL = new JLabel("Komentar:");
        ocenaL = new JLabel("Ocena:");
        upozorenjeL = new JLabel("");
        komentarArea = new JTextArea();

        bela = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "whiteStar.jpg");
        zuta = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "yellowStar.jpg");
        zvezdaB1 = new JButton(bela);
        zvezdaB2 = new JButton(bela);
        zvezdaB3 = new JButton(bela);
        zvezdaB4 = new JButton(bela);
        zvezdaB5 = new JButton(bela);
        potvrdiButton = new JButton("Potvrdi");

        zvezdaB1.addActionListener(this);
        zvezdaB2.addActionListener(this);
        zvezdaB3.addActionListener(this);
        zvezdaB4.addActionListener(this);
        zvezdaB5.addActionListener(this);

        zvezdaB1.setBackground(new Color(188, 204, 111));
        zvezdaB2.setBackground(new Color(188, 204, 111));
        zvezdaB3.setBackground(new Color(188, 204, 111));
        zvezdaB4.setBackground(new Color(188, 204, 111));
        zvezdaB5.setBackground(new Color(188, 204, 111));
        potvrdiButton.addActionListener(this);
    }

    private void setOkvir() {

        Border inBorder = BorderFactory.createTitledBorder("Unesite podatke");
        Border outBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);

        setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
    }

    private void podesiLayout() {

        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        setLayout(bg);

        con.gridx = 0;
        con.gridy = 0;
        con.insets = new Insets(5, 10, 5, 0);
        con.anchor = GridBagConstraints.LINE_START;
        add(komentarL, con);

        con.gridy = 1;
        con.gridwidth = 5;
        con.gridheight = 2;
        con.fill = GridBagConstraints.BOTH;
        add(komentarArea, con);

        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(ocenaL, con);

        con.gridy = 4;
        add(zvezdaB1, con);

        con.gridx = 1;
        add(zvezdaB2, con);

        con.gridx = 2;
        add(zvezdaB3, con);

        con.gridx = 3;
        add(zvezdaB4, con);

        con.gridx = 4;
        add(zvezdaB5, con);

        con.gridx = 4;
        con.gridy = 5;
        add(potvrdiButton, con);

        con.gridx = 0;
        con.gridy = 6;
        con.gridwidth = 5;
        add(upozorenjeL, con);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();

        if(button == zvezdaB1) {
            ocena = 1;
            zvezdaB1.setIcon(zuta);
            zvezdaB2.setIcon(bela);
            zvezdaB3.setIcon(bela);
            zvezdaB4.setIcon(bela);
            zvezdaB5.setIcon(bela);
        }else if(button == zvezdaB2) {
            ocena = 2;
            zvezdaB1.setIcon(zuta);
            zvezdaB2.setIcon(zuta);
            zvezdaB3.setIcon(bela);
            zvezdaB4.setIcon(bela);
            zvezdaB5.setIcon(bela);
        }else if(button == zvezdaB3) {
            ocena = 3;
            zvezdaB1.setIcon(zuta);
            zvezdaB2.setIcon(zuta);
            zvezdaB3.setIcon(zuta);
            zvezdaB4.setIcon(bela);
            zvezdaB5.setIcon(bela);
        }else if(button == zvezdaB4) {
            ocena = 4;
            zvezdaB1.setIcon(zuta);
            zvezdaB2.setIcon(zuta);
            zvezdaB3.setIcon(zuta);
            zvezdaB4.setIcon(zuta);
            zvezdaB5.setIcon(bela);
        }else if(button == zvezdaB5) {
            ocena = 5;
            zvezdaB1.setIcon(zuta);
            zvezdaB2.setIcon(zuta);
            zvezdaB3.setIcon(zuta);
            zvezdaB4.setIcon(zuta);
            zvezdaB5.setIcon(zuta);
        }else {
            String text = komentarArea.getText();
            TipRecenzije tipRecenzije;
            int idAutora;
            if(glavniProzor instanceof KorisnikovProzor) {
                tipRecenzije = TipRecenzije.IZVODJENJE_REGISTROVANI;
                idAutora = ((KorisnikovProzor) glavniProzor).idKorisnika;
            } else {
                tipRecenzije = TipRecenzije.IZVODJENJE_UREDNIK;
                idAutora = ((UrednikovProzor) glavniProzor).idUrednika;
            }
            try {
                RecenzijaKON.upisiPodatke(ocena, text, idIzvodjenja, idAutora, tipRecenzije);
            } catch (Exception ex) {
                if(ex.getMessage().equals("0")) {
                    upozorenjeL.setText("Morate uneti ocenu !");
                    return;
                }else if(ex.getMessage().equals("prazno")) {
                    upozorenjeL.setText("Morate uneti komentar !");
                    return;
                }
            }
            //dialog.prikazIzvodjenja.ucitajRecenzije(iz, glavniProzor);
            dialog.prikazIzvodjenja.dodajRecenzijuButton.setEnabled(false);
            dialog.prikazIzvodjenja.panelGlavni.validate();
            dialog.prikazIzvodjenja.panelGlavni.repaint();
            dialog.dispose();
        }

    }
}

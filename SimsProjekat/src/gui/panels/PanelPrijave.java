package gui.panels;

import gui.dialogs.DialogPrijave;
import gui.elementi.AdminovProzor;
import gui.elementi.GlavniProzor;
import gui.elementi.KorisnikovProzor;
import gui.elementi.UrednikovProzor;
import kontroler.RegistrovaniKorisnikKON;
import model.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrijave extends JPanel implements ActionListener {

    private DialogPrijave dialog;
    private String separator;
    private JLabel name, lastName, labela, upozorenja, slikaL;
    private JTextField nameField;
    private JPasswordField lastNameField;
    private JButton prijavaButton;
    private GlavniProzor glavniProzor;

    public PanelPrijave(DialogPrijave dialog, GlavniProzor gp) {

        this.dialog = dialog;
        this.glavniProzor = gp;
        separator = System.getProperty("file.separator");

        setBackground(new Color(226, 206, 158));
        podesiKomponente();
        setPanel();
        podesiLayout();

    }

    private void podesiKomponente() {

        name = new JLabel("Korisnicko ime:");
        lastName = new JLabel("Sifra:");
        upozorenja = new JLabel("Unesite validne podatke");
        upozorenja.setForeground(new Color(62, 100, 103));
        nameField = new JTextField(15);
        lastNameField = new JPasswordField(15);
        prijavaButton = new JButton("Prijava");
        prijavaButton.setBackground(new Color(62, 100, 103));
        prijavaButton.setForeground(Color.white);
        prijavaButton.addActionListener(this);
        Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "ikona.png");
        labela = new JLabel(icon);
        slikaL = new JLabel();
        slikaL.setSize(200, 100);
        slikaL.setPreferredSize(new Dimension(200, 100));

    }

    private void setPanel() {

        Border inBorder = BorderFactory.createTitledBorder("Unesite podatke");
        Border outBorder = BorderFactory.createEmptyBorder(0, 10, 10, 10);

        setBorder(BorderFactory.createCompoundBorder(outBorder, inBorder));
    }

    private void podesiLayout() {

        GridBagLayout bg = new GridBagLayout();
        GridBagConstraints con = new GridBagConstraints();
        setLayout(bg);

        con.fill = GridBagConstraints.BOTH;
        con.gridx = 0;
        con.gridy = 0;
        con.gridwidth = 2;
        con.insets = new Insets(5, 20, 15, 5);
        con.anchor = GridBagConstraints.LINE_END;
        add(labela, con);

        con.fill = GridBagConstraints.NONE;
        con.gridy = 1;
        con.gridwidth = 1;
        add(name, con);

        con.gridy = 2;
        add(lastName, con);

        con.gridx = 1;
        con.gridy = 3;
        add(prijavaButton, con);

        con.gridy = 1;
        con.insets = new Insets(5, 5, 15, 20);
        con.anchor = GridBagConstraints.LINE_START;
        add(nameField, con);

        con.gridy = 2;
        add(lastNameField, con);

        con.gridx = 0;
        con.gridy = 4;
        con.gridwidth = 2;
        add(upozorenja, con);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        String korIme = nameField.getText();
        String sifra = new String(lastNameField.getPassword());

        Korisnik korisnik;
        try {
            korisnik = RegistrovaniKorisnikKON.proslediPodatkePrijave(korIme, sifra);
            if(korisnik == null) {
                JOptionPane.showMessageDialog(this, "Ne postoji takav nalog");
                return;
            }
        } catch (Exception ex) {
            String tipIzuzetka = ex.getMessage();
            if (tipIzuzetka.equals("1")) {
                upozorenja.setText("Morate popuniti sva polja !");
            }
            return;
        }

        dialog.dispose();       //Ugasi dialog prijave
        glavniProzor.dispose();     //Ugasi pocetni prozor
        GlavniProzor prozorPrijavljenog;
        if(korisnik instanceof RegistrovaniKorisnik)
            prozorPrijavljenog = new KorisnikovProzor((RegistrovaniKorisnik) korisnik);
        else if(korisnik instanceof Urednik)
            prozorPrijavljenog = new UrednikovProzor((Urednik) korisnik);
        else
            prozorPrijavljenog = new AdminovProzor((Administrator) korisnik);
        prozorPrijavljenog.setVisible(true);
    }

}
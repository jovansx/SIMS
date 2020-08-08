package gui.elementi;

import dao.RecenzijaDAO;
import dao.ZahtevDAO;
import dao.ZanrDAO;
import kontroler.RegistrovaniKorisnikKON;
import model.Recenzija;
import model.RegistrovaniKorisnik;
import model.Zahtev;
import model.Zanr;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PrikazRecenzija extends JDialog {
    private JPanel panelGlavni;
    private JComboBox comboBoxRecenzije;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textAreaOpis;
    private JLabel labelaOpisa;
    private JPanel panelUnutrasnji;

    public PrikazRecenzija(KorisnikovProzor kp, RegistrovaniKorisnik korisnik) {
        super();
        setTitle("Moje recenzije");
        setModal(true);

        //popuniKorisnika(korisnik);

        inizijalizuj(kp, korisnik);

        add(panelGlavni);

        podesiAkcije(kp, korisnik);

        pack();
        setResizable(false);
        setLocationRelativeTo(kp);

    }

    private void inizijalizuj(KorisnikovProzor kp, RegistrovaniKorisnik korisnik) {
        comboBoxRecenzije = new JComboBox<>(getNizRecenzija(RecenzijaDAO.getRecenzijeKorisnika(korisnik)));
        comboBoxRecenzije.setBackground(new Color(186, 186, 178));
        panelUnutrasnji.add(comboBoxRecenzije);

        textAreaOpis.setPreferredSize(new Dimension(200, 100));
    }

    private void podesiAkcije(KorisnikovProzor kp, RegistrovaniKorisnik korisnik) {

        buttonOK.addActionListener(e -> onOK(korisnik));
        buttonCancel.addActionListener(e -> onCancel());
    }

    private void onOK(RegistrovaniKorisnik korisnik) {

        if (!proveriUnos()) {
            return;
        }

        Recenzija r = RecenzijaDAO.getRecenzijeKorisnika(korisnik).get(comboBoxRecenzije.getSelectedIndex());

        Zahtev zahtev = new Zahtev("Zahtev "+ korisnik.getId() + " " + korisnik.getPrezime(), textAreaOpis.getText(), false, false, korisnik, r);

        try {
            ZahtevDAO.insertBezAdmina(zahtev);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Uspesno je poslat zahtev");

        dispose();
    }

    private boolean proveriUnos() {
        if (textAreaOpis.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Opis mora biti napisan");
            return false;
        }

        if (comboBoxRecenzije.getSelectedIndex() < 0){
            JOptionPane.showMessageDialog(this, "Ne postoji vasa aktivna recenzija");
            return false;
        }

        return true;
    }

    private void onCancel() {
        dispose();
    }

    public static String[] getNizRecenzija(List<Recenzija> listaRecenzija) {
        System.out.println("duzina niza je "+listaRecenzija.size()
        );
        String[] itemsArray = new String[listaRecenzija.size()];
        int index = 0;
        for (Recenzija recenzija : listaRecenzija) {
            itemsArray[index] = recenzija.getKomentar() + " " + recenzija.getOcena();
            index++;
        }
        return itemsArray;
    }



}

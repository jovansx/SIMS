package gui.elementi;

import dao.RegistrovaniKorisnikDAO;
import dao.ZanrDAO;
import kontroler.RegistrovaniKorisnikKON;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Date;

public class PrikazProfila extends JDialog {
    private JPanel panelGlavni;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textFieldImena;
    private JTextField textFieldPrezimena;
    private JTextField textFieldEmaila;
    private JTextField textFieldKontaktTelefona;
    private JTextField textFieldSifre;
    private JLabel labelaImena;
    private JLabel labelaPrezimena;
    private JLabel labelaEmaila;
    private JLabel labelaKontaktTelefona;
    private JLabel labelaRodjenja;
    private JTextField textFieldRodjenja;
    private JLabel labelaSifre;
    private JButton izmeniButton;
    private JCheckBox javanCheckBox;
    private JLabel labelaJavan;
    private Dimension dimension;

    public PrikazProfila(KorisnikovProzor kp, RegistrovaniKorisnik korisnik) {
        super();
        setTitle("Moj profil");
        setModal(true);

        inizijalizuj(kp, korisnik);

        add(panelGlavni);

        podesiAkcije(kp, korisnik);

        setSize(dimension.width / 3, dimension.height / 3);
        setResizable(false);
        setLocationRelativeTo(kp);

    }

    private void inizijalizuj(KorisnikovProzor kp, RegistrovaniKorisnik korisnik) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        dimension = tool.getScreenSize();

        textFieldImena.setText(korisnik.getIme());
        textFieldPrezimena.setText(korisnik.getPrezime());
        textFieldEmaila.setText(korisnik.getEmail());
        textFieldKontaktTelefona.setText(korisnik.getKontaktTelefon());
        textFieldRodjenja.setText(korisnik.getGodinaRodjenja().toString());
        //textFieldSifre.setText(korisnik.getNalog().getLozinka());
        if (korisnik.isJeVidljiv())
            javanCheckBox.setSelected(true);
        else
            javanCheckBox.setSelected(false);

        textFieldImena.setEnabled(false);
        textFieldPrezimena.setEnabled(false);
        textFieldEmaila.setEnabled(false);
        textFieldKontaktTelefona.setEnabled(false);
        textFieldRodjenja.setEnabled(false);
        textFieldSifre.setEnabled(false);
        javanCheckBox.setEnabled(false);
    }

    private void podesiAkcije(KorisnikovProzor kp, RegistrovaniKorisnik korisnik) {

        buttonOK.addActionListener(e -> onOK(korisnik));

        buttonCancel.addActionListener(e -> onCancel());

        izmeniButton.addActionListener(e -> onIzmeni());

    }

    private void onIzmeni() {
        textFieldImena.setEnabled(true);
        textFieldPrezimena.setEnabled(true);
        textFieldEmaila.setEnabled(true);
        textFieldKontaktTelefona.setEnabled(true);
        textFieldRodjenja.setEnabled(true);
        textFieldSifre.setEnabled(true);
        javanCheckBox.setEnabled(true);
    }


    private void onOK(RegistrovaniKorisnik korisnik)  {

        proveriIzmene(korisnik);

        korisnik.setIme(textFieldImena.getText());
        korisnik.setPrezime(textFieldPrezimena.getText());
        korisnik.setEmail(textFieldEmaila.getText());
        korisnik.setKontaktTelefon(textFieldKontaktTelefona.getText());

        //korisnik.getNalog().setLozinka(textFieldSifre.getText());

        try {
            korisnik.setGodinaRodjenja(RegistrovaniKorisnikKON.sdf.parse(textFieldRodjenja.getText()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        korisnik.setJeVidljiv(javanCheckBox.isSelected());

        RegistrovaniKorisnikDAO.update(korisnik);

        dispose();
    }

    private void proveriIzmene(RegistrovaniKorisnik korisnik) {
        //TODO proveriti podatke unete za izmene
        System.out.println("Proverio");
    }

    private void onCancel() {
        dispose();
    }

}

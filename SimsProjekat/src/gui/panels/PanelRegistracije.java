package gui.panels;

import gui.dialogs.DialogRegistracije;
import kontroler.RegistrovaniKorisnikKON;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelRegistracije extends JPanel implements ActionListener {

    private DialogRegistracije dialog;
    private String separator;
    private JLabel ime, prezime, email, telefon, godina, korIme, sifra, labelaIkone, upozorenje;
    private JTextField imeTF, prezimeTF, emailTF, telefonTF, godinaTF, korImeTF;
    private JPasswordField sifraTF;
    private JButton button;

    public PanelRegistracije(DialogRegistracije dialog) {

        this.dialog = dialog;
        separator = System.getProperty("file.separator");
        setBackground(new Color(221, 179, 164));
        podesiKomponente();
        setPanel();
        podesiLayout();

    }

    private void podesiKomponente() {

        ime = new JLabel("Ime:");
        prezime = new JLabel("Prezime:");
        email = new JLabel("Email: ");
        telefon = new JLabel("Telefon: ");
        godina = new JLabel("Godina rodjenja: ");
        korIme = new JLabel("Korisnicko ime: ");
        sifra = new JLabel("Sifra: ");
        upozorenje = new JLabel("");

        imeTF = new JTextField(15);
        prezimeTF = new JTextField(15);
        emailTF = new JTextField(15);
        telefonTF = new JTextField(15);
        godinaTF = new JTextField(15);
        korImeTF = new JTextField(15);
        sifraTF = new JPasswordField(15);
        sifraTF.setToolTipText("Slaba: manje od 4, srednja: manje od 8, velika slova, jaka: specijalni znakovi(*,_,..)");

        button = new JButton("Potvrdi");
        button.setBackground(new Color(62, 100, 103));
        button.setForeground(Color.white);
        button.addActionListener(this);
        Icon icon = new ImageIcon("SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "reg.png");
        labelaIkone = new JLabel(icon);

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
        add(labelaIkone, con);

        con.fill = GridBagConstraints.NONE;
        con.insets = new Insets(5, 20, 5, 5);
        con.gridy = 1;
        con.gridwidth = 1;
        add(ime, con);

        con.gridy = 2;
        add(prezime, con);

        con.gridy = 3;
        add(email, con);

        con.gridy = 4;
        add(telefon, con);

        con.gridy = 5;
        add(godina, con);

        con.gridy = 6;
        add(korIme, con);

        con.gridy = 7;
        add(sifra, con);

        con.gridy = 9;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 2;
        add(upozorenje, con);

        con.gridy = 1;
        con.gridx = 1;
        con.fill = GridBagConstraints.BOTH;
        con.gridwidth = 1;
        con.insets = new Insets(5, 5, 5, 20);
        add(imeTF, con);

        con.gridy = 2;
        add(prezimeTF, con);

        con.gridy = 3;
        add(emailTF, con);

        con.gridy = 4;
        add(telefonTF, con);

        con.gridy = 5;
        add(godinaTF, con);

        con.gridy = 6;
        add(korImeTF, con);

        con.gridy = 7;
        add(sifraTF, con);

        con.gridx = 1;
        con.insets = new Insets(10, 5, 15, 20);
        con.gridy = 8;
        add(button, con);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String ime, prezime, email, telefon, godina, korIme;
        ime = imeTF.getText();
        prezime = prezimeTF.getText();
        email = emailTF.getText();
        telefon = telefonTF.getText();
        godina = godinaTF.getText();
        korIme = korImeTF.getText();
        String sifra = new String(sifraTF.getPassword());

        boolean retVal = false;
        try {
            retVal = RegistrovaniKorisnikKON.proslediPodatke(ime, prezime, email, telefon, godina, korIme, sifra);
        } catch (Exception ex) {
            String tipIzuzetka = ex.getMessage();
            if (tipIzuzetka.equals("1")) {
                upozorenje.setText("Morate popuniti sva polja !");
            }else if(tipIzuzetka.equals("2")) {
                upozorenje.setText("Format datuma mora biti -> dd-MM-yyyy !");
            }else if(tipIzuzetka.equals("3")) {
                upozorenje.setText("Korisnicko ime vec postoji !");
            }
        }

        if(retVal) {
            dialog.dispose();
            JOptionPane.showMessageDialog(null, "Uspeno ste se registrovali !\n" +
                    "Mozete da se prijavite kao registrovani korisnik !");
        }
    }
}
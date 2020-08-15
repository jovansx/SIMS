package gui.panels;
import dao.AdministratorDAO;
import dao.KorisnickiNalogDAO;
import gui.dialogs.DialogProfilAdmin;
import model.Administrator;
import model.KorisnickiNalog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PanelProfilAdmina extends JPanel{
    private Administrator admin;
    private DialogProfilAdmin dialog;
    private String separator;
    private JLabel ime, prezime, email, telefon,godina,korisnicko,lozinka, slika;
    private JTextField ime1,prezime1,email1,telefon1,godina1,korisnicko1,lozinka1;
    private JButton sacuvaj,izmeni;

    public PanelProfilAdmina(DialogProfilAdmin dialog, Administrator admin){
        this.dialog = dialog;
        this.admin=admin;
        this.separator = System.getProperty("file.separator");

        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0,0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();
    }
    private void namesti() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.");
        KorisnickiNalog nalog = KorisnickiNalogDAO.getNalogAdmina(admin.getId());

        ime = new JLabel("      Ime:");
        ime.setBounds(200, 30 ,120, 23);
        add(ime);

        ime1 = new JTextField();
        ime1.setBounds(320, 30, 120, 23);
        ime1.setBackground(Color.white);
        ime1.setOpaque(true);
        ime1.setText(admin.getIme());
        ime1.setEditable(false);
        add(ime1);

        prezime = new JLabel("      Prezime:");
        prezime.setBounds(200, 80 ,120, 23);
        add(prezime);

        prezime1 = new JTextField();
        prezime1.setBounds(320, 80, 120, 23);
        prezime1.setBackground(Color.white);
        prezime1.setOpaque(true);
        prezime1.setText(admin.getPrezime());
        prezime1.setEditable(false);
        add(prezime1);

        email = new JLabel("      Email:");
        email.setBounds(200, 130 ,120, 23);
        add(email);

        email1 = new JTextField();
        email1.setBounds(320, 130, 120, 23);
        email1.setBackground(Color.white);
        email1.setOpaque(true);
        email1.setText(admin.getEmail());
        email1.setEditable(false);
        add(email1);

        telefon = new JLabel("      Telefon:");
        telefon.setBounds(200, 180 ,120, 23);
        add(telefon);

        telefon1 = new JTextField();
        telefon1.setBounds(320, 180, 120, 23);
        telefon1.setBackground(Color.white);
        telefon1.setOpaque(true);
        telefon1.setText(admin.getKontaktTelefon());
        telefon1.setEditable(false);
        add(telefon1);

        godina = new JLabel("      Godina rodjenja:");
        godina.setBounds(200, 230 ,120, 23);
        add(godina);

        godina1 = new JTextField();
        godina1.setBounds(320, 230, 120, 23);
        godina1.setBackground(Color.white);
        godina1.setOpaque(true);
        godina1.setText(dateFormat.format(admin.getGodinaRodjenja()));
        godina1.setEditable(false);
        add(godina1);

        korisnicko = new JLabel("      Korisnicko ime:");
        korisnicko.setBounds(200, 280 ,120, 23);
        add(korisnicko);

        korisnicko1 = new JTextField();
        korisnicko1.setBounds(320, 280, 120, 23);
        korisnicko1.setBackground(Color.white);
        korisnicko1.setOpaque(true);
        korisnicko1.setText(nalog.getKorisnickoIme());
        korisnicko1.setEditable(false);
        add(korisnicko1);

        lozinka = new JLabel("      Lozinka:");
        lozinka.setBounds(200, 330 ,120, 23);
        add(lozinka);

        lozinka1 = new JTextField();
        lozinka1.setBounds(320, 330, 120, 23);
        lozinka1.setBackground(Color.white);
        lozinka1.setOpaque(true);
        lozinka1.setText(nalog.getLozinka());
        lozinka1.setEditable(false);
        add(lozinka1);


        ImageIcon icon = new ImageIcon("SimsProjekat" + separator+"src"+separator +"gui"+separator+ "icons" + separator + "user.png");
        slika = new JLabel(icon);
        slika.setBounds(30, 50, 150, 150);
        add(slika);

        izmeni = new JButton("Izmeni");
        izmeni.setBackground(new Color(0, 122, 153));
        izmeni.setBounds(200, 400, 120, 25);
        izmeni.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                izmeniStanje();
            }

        });
        add(izmeni);

        sacuvaj = new JButton("Sacuvaj");
        sacuvaj.setBackground(new Color(0, 122, 153));
        sacuvaj.setBounds(350, 400, 120, 25);
        sacuvaj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                sacuvajIzmene();
            }

        });
        add(sacuvaj);


    }

    private void sacuvajIzmene() {
        admin.setIme(ime1.getText());
        admin.setPrezime(prezime1.getText());
        admin.setEmail(email1.getText());
        admin.setKontaktTelefon(telefon1.getText());
        try {
            AdministratorDAO.update(admin);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void izmeniStanje() {
        ime1.setEditable(true);
        prezime1.setEditable(true);
        email1.setEditable(true);
        telefon1.setEditable(true);
    }

}

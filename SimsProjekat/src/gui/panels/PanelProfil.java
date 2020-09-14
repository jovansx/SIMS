package gui.panels;


import dao.UrednikDAO;
import gui.dialogs.DialogProfil;
import gui.dialogs.DialogPromena;
import kontroler.UrednikovProzorKON;
import model.Urednik;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class PanelProfil extends JPanel{
    public SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private DialogProfil dialog;
    private String separator;
    private JLabel ime, prezime, email, telefon,godina,korisnicko,lozinka, slika;
    private JTextField ime1,prezime1,email1,telefon1,godina1,korisnicko1;
    private JPasswordField lozinka1;
    private JButton sacuvaj,izmeni,promeniL,otkazi;
    public int id;
    public PanelProfil(DialogProfil dialog,int idUrednika){
    this.dialog = dialog;
    this.id = idUrednika;
    separator = System.getProperty("file.separator");
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setLayout(new BorderLayout(0,0));
    setBackground(new Color(226, 206, 158));
    setLayout(null);
    namesti();
}
    private void namesti() {
        Urednik u = UrednikDAO.getUrednikPoId(id);
        ime = new JLabel("      Ime:");
        ime.setBounds(200, 30 ,120, 23);
        ime.setBackground(Color.white);
        ime.setOpaque(true);
        ime.setBorder(BorderFactory.createLineBorder(Color.black));
        add(ime);

        ime1 = new JTextField();
        ime1.setBounds(350, 30, 120, 23);
        ime1.setBorder(BorderFactory.createLineBorder(Color.black));
        ime1.setBackground(Color.white);
        ime1.setOpaque(true);
        ime1.setText(u.getIme());
        ime1.setEditable(false);
        add(ime1);

        prezime = new JLabel("      Prezime:");
        prezime.setBounds(200, 80 ,120, 23);
        prezime.setBackground(Color.white);
        prezime.setOpaque(true);
        prezime.setBorder(BorderFactory.createLineBorder(Color.black));
        add(prezime);

        prezime1 = new JTextField();
        prezime1.setBounds(350, 80, 120, 23);
        prezime1.setBorder(BorderFactory.createLineBorder(Color.black));
        prezime1.setBackground(Color.white);
        prezime1.setOpaque(true);
        prezime1.setText(u.getPrezime());
        prezime1.setEditable(false);
        add(prezime1);

        email = new JLabel("      Email:");
        email.setBounds(200, 130 ,120, 23);
        email.setBackground(Color.white);
        email.setOpaque(true);
        email.setBorder(BorderFactory.createLineBorder(Color.black));
        add(email);

        email1 = new JTextField();
        email1.setBounds(350, 130, 120, 23);
        email1.setBorder(BorderFactory.createLineBorder(Color.black));
        email1.setBackground(Color.white);
        email1.setOpaque(true);
        email1.setText(u.getEmail());
        email1.setEditable(false);
        add(email1);

        telefon = new JLabel("      Telefon:");
        telefon.setBounds(200, 180 ,120, 23);
        telefon.setBackground(Color.white);
        telefon.setOpaque(true);
        telefon.setBorder(BorderFactory.createLineBorder(Color.black));
        add(telefon);

        telefon1 = new JTextField();
        telefon1.setBounds(350, 180, 120, 23);
        telefon1.setBorder(BorderFactory.createLineBorder(Color.black));
        telefon1.setBackground(Color.white);
        telefon1.setOpaque(true);
        telefon1.setText(u.getKontaktTelefon());
        telefon1.setEditable(false);
        add(telefon1);

        godina = new JLabel("      Godina rodjenja:");
        godina.setBounds(200, 230 ,120, 23);
        godina.setBackground(Color.white);
        godina.setOpaque(true);
        godina.setBorder(BorderFactory.createLineBorder(Color.black));
        add(godina);

        godina1 = new JTextField();
        godina1.setBounds(350, 230, 120, 23);
        godina1.setBorder(BorderFactory.createLineBorder(Color.black));
        godina1.setBackground(Color.white);
        godina1.setOpaque(true);
        godina1.setText(u.getGodinaRodjenja().toString());
        godina1.setEditable(false);
        add(godina1);
        korisnicko = new JLabel("      Korisnicko ime:");
        korisnicko.setBounds(200, 280 ,120, 23);
        korisnicko.setBackground(Color.white);
        korisnicko.setOpaque(true);
        korisnicko.setBorder(BorderFactory.createLineBorder(Color.black));
        add(korisnicko);

        korisnicko1 = new JTextField();
        korisnicko1.setBounds(350, 280, 120, 23);
        korisnicko1.setBorder(BorderFactory.createLineBorder(Color.black));
        korisnicko1.setBackground(Color.white);
        korisnicko1.setOpaque(true);
        korisnicko1.setText(u.getNalog().getKorisnickoIme());
        korisnicko1.setEditable(false);
        add(korisnicko1);
        lozinka = new JLabel("      Lozinka:");
        lozinka.setBounds(200, 330 ,120, 23);
        lozinka.setBackground(Color.white);
        lozinka.setOpaque(true);
        lozinka.setBorder(BorderFactory.createLineBorder(Color.black));
        add(lozinka);

        lozinka1 = new JPasswordField();
        lozinka1.setBounds(350, 330, 120, 23);
        lozinka1.setBorder(BorderFactory.createLineBorder(Color.black));
        lozinka1.setBackground(Color.white);
        lozinka1.setOpaque(true);
        lozinka1.setText(u.getNalog().getLozinka());
        lozinka1.setEditable(false);
        add(lozinka1);


        Icon icon = new ImageIcon("SimsProjekat" + separator+"src"+separator +"gui"+separator+ "icons" + separator + "user.png");
        slika = new JLabel(icon);
        slika.setBounds(30, 50, 150, 150);
        add(slika);

        izmeni = new JButton("Promena podataka");
        izmeni.setBackground(new Color(62, 100, 103));
        izmeni.setForeground(Color.WHITE);
        izmeni.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        izmeni.setBounds(50, 400, 120, 23);
        izmeni.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ime1.setEditable(true);
                prezime1.setEditable(true);
                email1.setEditable(true);
                telefon1.setEditable(true);
                godina1.setEditable(true);
            }

        });
        add(izmeni);

        sacuvaj = new JButton("Sacuvaj");
        sacuvaj.setBackground(new Color(62, 100, 103));
        sacuvaj.setForeground(Color.WHITE);
        sacuvaj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sacuvaj.setBounds(200, 400, 120, 23);
        sacuvaj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String i,p,t,e,d;
                i = ime1.getText();
                p = prezime1.getText();
                t = telefon1.getText();
                e = email1.getText();
               // d = godina1.getText();
                Date dd = null;
                try {
                    //dd = new Date(sdf.parse(d).getTime());
                   dd = new java.sql.Date(sdf.parse(godina1.getText()).getTime());
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                boolean value = false;
                try {
                    value = UrednikovProzorKON.provera(u,i,p,e,t,dd);
                    if(value) {
                        JOptionPane.showMessageDialog(dialog, "Uspesno izmenjeni podaci!");
                        dialog.setVisible(false);
                    }else{
                        JOptionPane.showMessageDialog(dialog, "Greska!");
                    }
                } catch (Exception exception) {
                    String tipIzuzetka = exception.getMessage();
                    if (tipIzuzetka.equals("1")) {
                        JOptionPane.showMessageDialog(dialog,"Morate popuniti sva polja!");
                    }else if(tipIzuzetka.equals("2")) {
                        JOptionPane.showMessageDialog(dialog,"Datum mora biti u formi (dd-mm-yyyy)!");
                }


            }}

        });
        add(sacuvaj);
        otkazi = new JButton("Otkazi");
        otkazi.setBackground(new Color(62, 100, 103));
        otkazi.setForeground(Color.WHITE);
        otkazi.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        otkazi.setBounds(350, 400, 120, 23);
        otkazi.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                ime1.setText(u.getIme());
                ime1.setEditable(false);
                prezime1.setText(u.getPrezime());
                prezime1.setEditable(false);
                email1.setText(u.getEmail());
                email1.setEditable(false);
                telefon1.setText(u.getKontaktTelefon());
                telefon1.setEditable(false);
                godina1.setText(u.getGodinaRodjenja().toString());
                godina1.setEditable(false);
                dialog.setVisible(false);
            }

        });
        add(otkazi);
        promeniL = new JButton("Promena lozinke");
        promeniL.setBackground(new Color(62, 100, 103));
        promeniL.setForeground(Color.WHITE);
        promeniL.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        promeniL.setBounds(50, 330, 120, 23);
        promeniL.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogPromena d = new DialogPromena(id);
                d.setVisible(true);
            }

        });
        add(promeniL);


    }
}
package gui.panels;

import dao.*;
import gui.dialogs.DialogSadrzaja;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelSadrzaja extends JPanel {
    private DialogSadrzaja dialog;
    private JButton otkazi;
    private JButton dodaj;
    private String separator;
    private JLabel slika;
    private JComboBox<String> medija;
    private JTextField naziv;

    public PanelSadrzaja(DialogSadrzaja dialog){
        this.dialog=dialog;

        separator = System.getProperty("file.separator");
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0,0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();
    }

    public void namesti(){

        ImageIcon icon =new ImageIcon("SimsProjekat" + separator+"src"+separator +"gui"+separator+ "icons" + separator + "content.png");
        slika = new JLabel(icon);
        slika.setBounds(123, 10, 250, 200);
        add(slika);

        JLabel tip = new JLabel("Tip sadrzaja");
        tip.setBounds(72, 190, 100, 30);
        add(tip);

        String[] izborSadrzaja = { "Zanr", "Ucesnik", "Izvodjac", "Muzicko delo", "Album"};
        medija= new JComboBox<String>(izborSadrzaja);
        medija.setBounds(70, 230, 120, 30);
        add(medija);

        JLabel unos=new JLabel("Naziv:");
        unos.setBounds(300, 190, 140, 30);
        add(unos);

        naziv= new JTextField();
        naziv.setBounds(300, 230, 140, 30);
        add(naziv);

        otkazi=new JButton("Otkazi");
        otkazi.setBounds(260, 300, 100, 40);
        otkazi.setBackground(new Color(0, 122, 153));
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor=JOptionPane.showConfirmDialog(null,
                        "Jeste li sigurni da zelite da prekinete sa kreiranjem novog sadrzaja?", "Provera",JOptionPane.YES_NO_OPTION);
                if(odgovor==0){
                    setVisible(false);
                }
            }
        });
        add(otkazi);

        dodaj=new JButton("Dodaj");
        dodaj.setBounds(140, 300, 100, 40);
        dodaj.setBackground(new Color(0, 122, 153));
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodajSadrzaj();
                    JOptionPane.showMessageDialog(null, "Sadrzaj je kreiran!");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        add(dodaj);
    }

    private void dodajSadrzaj() throws SQLException {
        if(medija.getSelectedItem()=="Zanr"){
            Zanr z=new Zanr();
            if(!naziv.getText().equals("")){
                z.setNazivZanra(naziv.getText());
                z.setOpis(null);
                z.setDatumNastanka(null);
                ZanrDAO.insert(z);
            }else{
                JOptionPane.showMessageDialog(null,"Unesite naziv zanra");
            }

        }
        else if(medija.getSelectedItem()=="Ucesnik"){
            Ucesnik u=new Ucesnik();
            if(!naziv.getText().equals("")){
                u.setNaziv(naziv.getText());
                u.setOpis(null);
                u.setTip(null);
                UcesnikDAO.insert(u);
            }else{
                JOptionPane.showMessageDialog(null,"Unesite naziv ucesnika");
            }
        }
        else if(medija.getSelectedItem()=="Izvodjac"){
            Izvodjac i=new Izvodjac();
            if(!naziv.getText().equals("")){
                i.setNazivIzvodjaca(naziv.getText());
                i.setOpis(null);
                i.setTipIzvodjaca(null);
                i.setPripadaGrupi(null);
                IzvodjacDAO.insert(i);
            }else{
                JOptionPane.showMessageDialog(null,"Unesite naziv izvodjaca");
            }
        }
        else if(medija.getSelectedItem()=="Muzicko delo"){
            MuzickoDelo md=new MuzickoDelo();
            if(!naziv.getText().equals("")){
                md.setNazivDela(naziv.getText());
                md.setOpis(null);
                MuzickoDeloDAO.insertPart(md);
            }else{
                JOptionPane.showMessageDialog(null,"Unesite naziv muzickog dela");
            }
        }
        else if(medija.getSelectedItem()=="Album"){
            Album a=new Album();
            if(!naziv.getText().equals("")){
                a.setNazivDela(naziv.getText());
                a.setOpis(null);
                AlbumDAO.insert(a);
            }
            else {
                JOptionPane.showMessageDialog(null,"Unesite naziv albuma");
            }

        }
    }
}

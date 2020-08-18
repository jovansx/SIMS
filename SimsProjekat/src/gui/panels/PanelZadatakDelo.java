package gui.panels;
import dao.AlbumDAO;
import dao.UcesnikDAO;
import dao.ZadatakDAO;
import dao.ZanrDAO;
import gui.dialogs.DialogDodajAlbum;
import gui.dialogs.DialogDodajDela;
import gui.dialogs.DialogIzvodjenje;
import gui.dialogs.DialogZadatakDelo;
import kontroler.UrednikovProzorKON;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class PanelZadatakDelo extends JPanel {
    public SimpleDateFormat formatter1=new SimpleDateFormat("dd-mm-yyyy");
    private DialogZadatakDelo dialog;
    private JLabel naziv, opis, sadrzaj, datum, vreme, ocena,text,zanr,ucesnik,album;
    private JTextField naziv1, datum1, vreme1, ocena1;
    private JButton kreiraj, dodaj;
    private JTextArea opis1,sadrzaj1,text1;
    private JComboBox<String> combo,combo1,combo2;
    private JRadioButton al,del;
    private ButtonGroup group;
    public int id;
    private JScrollPane scroll;

    public PanelZadatakDelo(DialogZadatakDelo dialog, int idZadatka) {
        this.dialog = dialog;
        this.id = idZadatka;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(226, 206, 158));
        setLayout(null);
        namesti();

    }
    public void namesti(){

        Zadatak zadatak = ZadatakDAO.getZadatakPoId(id);
        MuzickoDelo md = zadatak.getDelo();
        ArrayList<Zanr> zanrovi = (ArrayList<Zanr>) ZanrDAO.getZanrove();
        ArrayList<Ucesnik> ucesnici = (ArrayList<Ucesnik>) UcesnikDAO.getUcesnike();
        ArrayList<Album> albumi = (ArrayList<Album>) AlbumDAO.getListaAlbuma();

        text = new JLabel("      Tekst zadatka:");
        text.setBounds(50, 30 ,120, 50);
        text.setBackground(Color.white);
        text.setOpaque(true);
        text.setBorder(BorderFactory.createLineBorder(Color.black));
        add(text);

        text1 = new JTextArea();
        text1.setBounds(200, 30, 200, 50);
        text1.setBorder(BorderFactory.createLineBorder(Color.black));
        text1.setBackground(Color.white);
        text1.setOpaque(true);
        text1.setText(zadatak.getText());
        text1.setEditable(false);
        scroll = new JScrollPane();
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBounds(200, 30, 200, 50);
        scroll.getViewport().setBackground(Color.white);
        scroll.getViewport().add(text1);
        add(scroll);

        naziv = new JLabel("      Naziv dela:");
        naziv.setBounds(50, 100 ,120, 23);
        naziv.setBackground(Color.white);
        naziv.setOpaque(true);
        naziv.setBorder(BorderFactory.createLineBorder(Color.black));
        add(naziv);

        naziv1 = new JTextField(100);
        naziv1.setBounds(200, 100, 200, 23);
        naziv1.setBorder(BorderFactory.createLineBorder(Color.black));
        naziv1.setBackground(Color.white);
        naziv1.setOpaque(true);
        naziv1.setText(md.getNazivDela());
        naziv1.setEditable(false);
        add(naziv1);

        opis = new JLabel("      Opis:");
        opis.setBounds(50, 150 ,120, 50);
        opis.setBackground(Color.white);
        opis.setOpaque(true);
        opis.setBorder(BorderFactory.createLineBorder(Color.black));
        add(opis);

        opis1 = new JTextArea();
        opis1.setBounds(200, 150, 200, 50);
        opis1.setBorder(BorderFactory.createLineBorder(Color.black));
        opis1.setBackground(Color.white);
        opis1.setOpaque(true);
        opis1.setEditable(true);
        add(opis1);

        sadrzaj = new JLabel("      Sadrzaj:");
        sadrzaj.setBounds(50, 250 ,120, 50);
        sadrzaj.setBackground(Color.white);
        sadrzaj.setOpaque(true);
        sadrzaj.setBorder(BorderFactory.createLineBorder(Color.black));
        add(sadrzaj);

        sadrzaj1 = new JTextArea();
        sadrzaj1.setBounds(200, 250, 200, 50);
        sadrzaj1.setBorder(BorderFactory.createLineBorder(Color.black));
        sadrzaj1.setBackground(Color.white);
        sadrzaj1.setOpaque(true);
        sadrzaj1.setEditable(true);
        add(sadrzaj1);

        vreme = new JLabel("      Datum nastanka(dd-mm-yyyy):");
        vreme.setBounds(420, 250 ,120, 23);
        vreme.setBackground(Color.white);
        vreme.setOpaque(true);
        vreme.setBorder(BorderFactory.createLineBorder(Color.black));
        add(vreme);

        vreme1 = new JTextField();
        vreme1.setBounds(550, 250, 120, 23);
        vreme1.setBorder(BorderFactory.createLineBorder(Color.black));
        vreme1.setBackground(Color.white);
        vreme1.setEditable(true);
        add(vreme1);

        datum = new JLabel("      Datum postavke:");
        datum.setBounds(420, 300 ,120, 23);
        datum.setBackground(Color.white);
        datum.setOpaque(true);
        datum.setBorder(BorderFactory.createLineBorder(Color.black));
        add(datum);

        datum1 = new JTextField();
        datum1.setBounds(550, 300, 120, 23);
        datum1.setBorder(BorderFactory.createLineBorder(Color.black));
        datum1.setBackground(Color.white);
        datum1.setText(md.getVremeNastanka().toString());
        datum1.setEditable(false);
        add(datum1);

        zanr = new JLabel("      Zanr:");
        zanr.setBounds(420, 100 ,120, 23);
        zanr.setBackground(Color.white);
        zanr.setOpaque(true);
        zanr.setBorder(BorderFactory.createLineBorder(Color.black));
        add(zanr);

        combo = new JComboBox<String>();
        combo.setBounds(550, 100, 120, 23);
        for(Zanr z :zanrovi){
            combo.setSelectedItem(null);
            combo.addItem(z.getNazivZanra());
        }
        combo.setSelectedItem(null);
        add(combo);


        combo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                if(!Objects.isNull(md.getListaZanrova())){
                    md.getListaZanrova().add(ZanrDAO.getZanrPoNazivu(value));
                }else{
                    md.setListaZanrova(new ArrayList<>());
                    md.getListaZanrova().add(ZanrDAO.getZanrPoNazivu(value));

                }
                }
        });

        al = new JRadioButton("Album");
        al.setBounds(550,30,100,20);
        al.setActionCommand("Album");
        al.setBackground(Color.white);
        add(al);

        del = new JRadioButton("Muzicko delo");
        del.setBounds(420,30,100,20);
        del.setActionCommand("Muzicko delo");
        del.setBackground(Color.white);
        add(del);

        group = new ButtonGroup();
        group.add(al);
        group.add(del);

        ucesnik = new JLabel("      Ucesnik:");
        ucesnik.setBounds(420, 150 ,120, 23);
        ucesnik.setBackground(Color.white);
        ucesnik.setOpaque(true);
        ucesnik.setBorder(BorderFactory.createLineBorder(Color.black));
        add(ucesnik);

        combo1 = new JComboBox<String>();
        combo1.setBounds(550, 150, 120, 23);
        for(Ucesnik u :ucesnici){
            combo1.setSelectedItem(null);
            combo1.addItem(u.getNaziv());
        }
        combo1.setSelectedItem(null);
        add(combo1);

        combo1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                if(!Objects.isNull(md.getListaUcesnika())){
                    md.getListaUcesnika().add(UcesnikDAO.getUcesnikNaziv(value));
                }else{
                    md.setListaUcesnika(new ArrayList<>());
                    md.getListaUcesnika().add(UcesnikDAO.getUcesnikNaziv(value));
                }


            }
        });
        ocena = new JLabel("      Prosecna ocena:");
        ocena.setBounds(420, 200 ,120, 23);
        ocena.setBackground(Color.white);
        ocena.setOpaque(true);
        ocena.setBorder(BorderFactory.createLineBorder(Color.black));
        add(ocena);

        ocena1 = new JTextField();
        ocena1.setBounds(550, 200, 120, 23);
        ocena1.setBorder(BorderFactory.createLineBorder(Color.black));
        ocena1.setBackground(Color.white);
        ocena1.setText(String.valueOf(md.getProsecnaOcena()));
        ocena1.setEditable(false);
        add(ocena1);


        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(100,350,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(sadrzaj1.getText() == "" || opis1.getText() == ""){
                    JOptionPane.showMessageDialog(PanelZadatakDelo.this,"Morate uneti opis i sadrzaj!");
                }else{
                    md.setSadrzaj(sadrzaj1.getText());
                    md.setOpis(opis1.getText());
                    md.setProsecnaOcena(Double.parseDouble(ocena1.getText()));
                    try {
                        md.setVremeNastanka(new java.sql.Date(formatter1.parse(vreme1.getText()).getTime()));
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    try {
                        if(del.isSelected()){
                            DialogDodajAlbum dda = new DialogDodajAlbum(md.getId());
                            dda.setVisible(true);
                        }if(al.isSelected()){
                            DialogDodajDela ddd = new DialogDodajDela(md.getId());
                            ddd.setVisible(true);
                        }
                        UrednikovProzorKON.opisiMuzickoDelo(md);
                        //JOptionPane.showMessageDialog(PanelZadatakDelo.this,"Uspesno ste opisali muzicko delo!");
                        dialog.setVisible(false);
                        ZadatakDAO.delete(zadatak);
                        PanelZadaci.refreshData();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        add(kreiraj);
        dodaj = new JButton("    Dodaj izvodjenje    ");
        dodaj.setBounds(300,350,120,23);
        dodaj.setBackground(new Color(62, 100, 103));
        dodaj.setForeground(Color.WHITE);
        dodaj.setBorder(BorderFactory.createLineBorder(Color.black));
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogIzvodjenje di = new DialogIzvodjenje(md.getId());
                di.setVisible(true);
                }

        });
        add(dodaj);

    }

}

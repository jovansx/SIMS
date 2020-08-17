package gui.panels;
import dao.MuzickoDeloDAO;
import dao.ZadatakDAO;
import gui.dialogs.DialogZadatakUcesnik;
import kontroler.UrednikovProzorKON;
import model.MuzickoDelo;
import model.Ucesnik;
import model.Zadatak;
import model.enums.TipUcesnika;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class PanelZadatakUcesnik extends JPanel{
    private DialogZadatakUcesnik dialog;
    private JLabel naziv, opis, text,grupa;
    private JTextField naziv1;
    private JButton kreiraj,odustani;
    private JTextArea opis1,text1;
    private JComboBox<String> combo;
    private JRadioButton kompozitor,aranzer,tekstopisac,produkcija;
    private ButtonGroup group;
    public int id;
    private JScrollPane scroll;

    public PanelZadatakUcesnik(DialogZadatakUcesnik dialog, int idZadatka) {
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
        Ucesnik ucesnik = zadatak.getUcesnik();
        ArrayList<MuzickoDelo> dela = (ArrayList<MuzickoDelo>) MuzickoDeloDAO.getMuzickaDela();

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

        naziv = new JLabel("      Naziv ucesnika:");
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
        naziv1.setText(ucesnik.getNaziv());
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

        kompozitor = new JRadioButton("Kompozitor");
        kompozitor.setBounds(50,280,100,20);
        kompozitor.setActionCommand("Kompozitor");
        kompozitor.setBackground(Color.white);
        add(kompozitor);

        aranzer = new JRadioButton("Aranzer");
        aranzer.setBounds(150,280,100,20);
        aranzer.setActionCommand("Aranzer");
        aranzer.setBackground(Color.white);
        add(aranzer);

        tekstopisac = new JRadioButton("Tekstopisac");
        tekstopisac.setBounds(250,280,100,20);
        tekstopisac.setActionCommand("Tekstopisac");
        tekstopisac.setBackground(Color.white);
        add(tekstopisac);

        produkcija = new JRadioButton("Muzicka produkcija");
        produkcija.setBounds(350,280,100,20);
        produkcija.setActionCommand("Muzicka produkcija");
        produkcija.setBackground(Color.white);
        add(produkcija);

        group = new ButtonGroup();
        group.add(kompozitor);
        group.add(aranzer);
        group.add(tekstopisac);
        group.add(produkcija);


        grupa = new JLabel("      Muzicka dela:");
        grupa.setBounds(420, 30 ,120, 23);
        grupa.setBackground(Color.white);
        grupa.setOpaque(true);
        grupa.setBorder(BorderFactory.createLineBorder(Color.black));
        add(grupa);

        combo = new JComboBox<String>();
        combo.setBounds(550, 30, 120, 23);
        for(MuzickoDelo md :dela){
            combo.setSelectedItem(null);
            combo.addItem(md.getNazivDela());
        }
        combo.setSelectedItem(null);
        add(combo);
        combo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                if(!Objects.isNull(ucesnik.getListaMuzickihDela())){
                   ucesnik.getListaMuzickihDela().add(MuzickoDeloDAO.getMuzickoDeloNaziv(value));
                }else{
                    ucesnik.setListaMuzickihDela(new ArrayList<>());
                    ucesnik.getListaMuzickihDela().add(MuzickoDeloDAO.getMuzickoDeloNaziv(value));
                }


            }
        });
        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(100,350,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(opis1.getText() == ""){
                    JOptionPane.showMessageDialog(PanelZadatakUcesnik.this,"Morate uneti opis!");
                }else{
                    ucesnik.setOpis(opis1.getText());
                    if(kompozitor.isSelected()){
                        ucesnik.setTip(TipUcesnika.KOMPOZITOR);
                    }if(aranzer.isSelected()){
                        ucesnik.setTip(TipUcesnika.ARANZER);
                    }if(tekstopisac.isSelected()){
                        ucesnik.setTip(TipUcesnika.TEKSTOPISAC);
                    }if(produkcija.isSelected()){
                        ucesnik.setTip(TipUcesnika.MUZICKA_PRODUKCIJA);
                    }
                    ArrayList<String> imena = new ArrayList<>();
                    for(int i =0;i< ucesnik.getListaMuzickihDela().size();i++){
                        imena.add(ucesnik.getListaMuzickihDela().get(i).getNazivDela());
                    }
                    ArrayList<String> pomocna = new ArrayList<>();
                    for(int i =0;i<imena.size();i++){
                        if(!pomocna.contains(imena.get(i))){
                            pomocna.add(imena.get(i));
                        }
                    }
                    ArrayList<MuzickoDelo> konacna = new ArrayList<>();
                    for(int i =0;i<pomocna.size();i++){
                        konacna.add(MuzickoDeloDAO.getMuzickoDeloNaziv(pomocna.get(i)));
                    }
                    ucesnik.setListaMuzickihDela(konacna);
                    try {
                        UrednikovProzorKON.opisiUcesnika(ucesnik);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(PanelZadatakUcesnik.this,"Uspesno ste opisali ucesnika!");
                    dialog.setVisible(false);
                    try {
                        ZadatakDAO.delete(zadatak);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    PanelZadaci.refreshData();
                }
            }
        });
        add(kreiraj);

        odustani = new JButton("    Odustani   ");
        odustani.setBounds(300,350,120,23);
        odustani.setBackground(new Color(62, 100, 103));
        odustani.setForeground(Color.WHITE);
        odustani.setBorder(BorderFactory.createLineBorder(Color.black));
        odustani.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.setVisible(false);
            }
        });
        add(odustani);



    }
}

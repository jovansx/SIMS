package gui.panels;
import dao.AlbumDAO;
import dao.MuzickoDeloDAO;
import dao.UcesnikDAO;
import gui.dialogs.DialogDodajDela;
import kontroler.UrednikovProzorKON;
import model.Album;
import model.MuzickoDelo;

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

public class PanelDodajDela extends JPanel{
    private DialogDodajDela dialog;
    private JLabel dela;
    private JButton kreiraj, odustani;
    private JComboBox<String> combo;
    private JRadioButton cd,ploca,online;
    private ButtonGroup group;
    public int id;

    public PanelDodajDela(DialogDodajDela dialog, int idDela) {
        this.dialog = dialog;
        this.id = idDela;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(226, 206, 158));
        setLayout(null);
        namesti();
    }
    public void namesti(){
        MuzickoDelo md = MuzickoDeloDAO.getMuzickoDelo(id);
        Album a = new Album();
        a.setId(md.getId());
        a.setOpis(md.getOpis());
        a.setSadrzaj(md.getSadrzaj());
        a.setNazivDela(md.getNazivDela());
        a.setVremeNastanka(md.getVremeNastanka());
        a.setDatumPostavljanja(md.getDatumPostavljanja());
        a.setProsecnaOcena(md.getProsecnaOcena());
        ArrayList<MuzickoDelo> lista = (ArrayList<MuzickoDelo>) MuzickoDeloDAO.getMuzickaDela();

        cd = new JRadioButton("CD");
        cd.setBounds(50,50,100,20);
        cd.setActionCommand("CD");
        cd.setBackground(Color.white);
        add(cd);

        ploca = new JRadioButton("Ploca");
        ploca.setBounds(150,50,100,20);
        ploca.setActionCommand("Ploca");
        ploca.setBackground(Color.white);
        add(ploca);

        online = new JRadioButton("Online");
        online.setBounds(250,50,100,20);
        online.setActionCommand("Online");
        online.setBackground(Color.white);
        add(online);


        group = new ButtonGroup();
        group.add(cd);
        group.add(ploca);
        group.add(online);

        dela = new JLabel("      Dela:");
        dela.setBounds(50, 100 ,120, 23);
        dela.setBackground(Color.white);
        dela.setOpaque(true);
        dela.setBorder(BorderFactory.createLineBorder(Color.black));
        add(dela);

        combo = new JComboBox<String>();
        combo.setBounds(250, 100, 120, 23);
        for(MuzickoDelo m :lista){
            combo.setSelectedItem(null);
            combo.addItem(m.getNazivDela());
        }
        combo.setSelectedItem(null);
        add(combo);

        combo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                if(!Objects.isNull(a.getListaMuzickihDela())){
                    a.getListaMuzickihDela().add(MuzickoDeloDAO.getMuzickoDeloNaziv(value));
                }else{
                    a.setListaMuzickihDela(new ArrayList<>());
                    a.getListaMuzickihDela().add(MuzickoDeloDAO.getMuzickoDeloNaziv(value));
                }

            }
        });

        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(50,200,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    UrednikovProzorKON.opisiMuzickoDelo(a);
                    JOptionPane.showMessageDialog(PanelDodajDela.this,"Uspesno ste dodali dela!");
                    dialog.setVisible(false);
                    PanelZadaci.refreshData();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        add(kreiraj);
        odustani = new JButton("    Odustani    ");
        odustani.setBounds(200,200,120,23);
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

package gui.panels;

import dao.AlbumDAO;
import dao.MuzickoDeloDAO;
import gui.dialogs.DialogDodajAlbum;
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

public class PanelDodajAlbum extends JPanel {

    private DialogDodajAlbum dialog;
    private JLabel album;
    private JButton kreiraj, odustani;
    private JComboBox<String> combo;
    public int id;

    public PanelDodajAlbum(DialogDodajAlbum dialog, int idDela) {
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
        ArrayList<Album> albumi = (ArrayList<Album>) AlbumDAO.getListaAlbuma();

        album = new JLabel("      Album:");
        album.setBounds(50, 30 ,120, 23);
        album.setBackground(Color.white);
        album.setOpaque(true);
        album.setBorder(BorderFactory.createLineBorder(Color.black));
        add(album);

        combo = new JComboBox<String>();
        combo.setBounds(50, 80, 120, 23);
        for(Album a :albumi){
            combo.setSelectedItem(null);
            combo.addItem(a.getNazivDela());
        }
        combo.setSelectedItem(null);
        add(combo);

        combo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                md.setAlbumKomPripada(AlbumDAO.getAlbumPoNazivu(value));

            }
        });
        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(30,200,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        UrednikovProzorKON.opisiMuzickoDelo(md);
                        JOptionPane.showMessageDialog(PanelDodajAlbum.this,"Uspesno ste izabrali album!");
                        dialog.setVisible(false);
                        PanelZadaci.refreshData();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

            }
        });
        add(kreiraj);
        odustani = new JButton("    Odustani    ");
        odustani.setBounds(150,200,120,23);
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

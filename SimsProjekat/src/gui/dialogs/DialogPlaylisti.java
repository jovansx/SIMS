package gui.dialogs;

import dao.PlejListaDAO;
import gui.elementi.ElementPrikazaPlejlisti;
import gui.elementi.KorisnikovProzor;
import model.PlejLista;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DialogPlaylisti extends JDialog{
    private JPanel panel;
    private JButton posalji, uredu;
    private JPanel skrolPanel;
    private JScrollPane skrol;
    private List<PlejLista> plejliste;
    private List<ElementPrikazaPlejlisti> listaElemenata;
    private RegistrovaniKorisnik korisnik;

    public DialogPlaylisti(List<PlejLista> lista, RegistrovaniKorisnik rg){
        this.plejliste=lista;
        this.listaElemenata=new ArrayList<>();
        this.korisnik=rg;

        setTitle("Playliste");
        setSize(600, 650);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ucitajSkrol();
        ucitajDugmad();
        podesiAkcije();

    }

    private void podesiAkcije() {
        posalji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    kreirajNovuPlaylistu();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        uredu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    private void ucitajDugmad() {
        panel = new JPanel();
        panel.setBackground(new Color(0, 77, 102));

        //Dodavanje dugmeta za dodavanje nove playliste
        posalji=new JButton("Dodaj playlist");
        posalji.setBackground(new Color(153, 204, 255));
        posalji.setForeground(Color.white);
        panel.add(posalji);

        //Dodavanje dugmeta za zatvaranje prozora
        uredu=new JButton("U redu");
        uredu.setForeground(Color.white);
        uredu.setBackground(new Color(153, 204, 255));
        panel.add(uredu);
        panel.setPreferredSize(new Dimension(400, 70));
        add(panel,BorderLayout.SOUTH);
    }

    private void kreirajNovuPlaylistu() throws SQLException {
        String naziv = JOptionPane.showInputDialog("Unesite naziv plejliste");
        if(naziv.equals("")){
            JOptionPane.showMessageDialog(null,"Molimo da unesete naziv plejliste");
        }
        else{
            PlejLista p=new PlejLista();
            p.setNaziv(naziv);
            p.setJeJavna(false);
            PlejListaDAO.insert(p, korisnik.getId());
        }
    }

    private void ucitajSkrol() {

        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));
        resetElemente();

        for(PlejLista p : plejliste ){
            ElementPrikazaPlejlisti ez= new ElementPrikazaPlejlisti(this, p);
            listaElemenata.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                        ");
            labela.setForeground(new Color(77, 121, 255));
            skrolPanel.add(labela);
        }

        skrolPanel.validate();
        skrolPanel.repaint();

        skrol.validate();
        skrol.repaint();

        skrol.setViewportView(skrolPanel);
        skrol.setPreferredSize(new Dimension(600, 170));
        add(skrol,BorderLayout.CENTER);
    }

    private void resetElemente() {
        listaElemenata.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }

}

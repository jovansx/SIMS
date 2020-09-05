package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.PlejListaDAO;
import gui.dialogs.DialogDodavanjaPlaylisti;
import model.Izvodjenje;
import model.PlejLista;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DodajIzvodjenje extends JPanel {
    private JLabel naziv;
    private JButton dodaj;
    private DialogDodavanjaPlaylisti dialog;
    private Izvodjenje izvodjenje;
    private PlejLista plejLista;

    public DodajIzvodjenje(DialogDodavanjaPlaylisti dialog, Izvodjenje i, PlejLista p) {
        this.dialog = dialog;
        this.izvodjenje = i;
        this.plejLista = p;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255, 255, 255));
        setPreferredSize(new Dimension(300, 90));
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setLayout(null);
        namesti();
        podesiAkcije();
    }

    public void namesti() {
        naziv = new JLabel(plejLista.getNaziv());
        naziv.setBounds(10, 30, 200, 30);
        add(naziv);

        dodaj = new JButton("Dodaj");
        dodaj.setBounds(210, 30, 70, 30);
        dodaj.setForeground(Color.white);
        dodaj.setBackground(new Color(0, 77, 102));
        add(dodaj);
    }

    public void podesiAkcije() {
        dodaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dodajIzvodjenje();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
    }

    public void dodajIzvodjenje() throws SQLException {
        int retVal = JOptionPane.showOptionDialog(null, "Da li zelite da dodate izvodjenje?",
                "Dodaj", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        int check = 0;
        if (retVal == 0) {
            for (Izvodjenje i : IzvodjenjeDAO.izvodjenjaUPlejlisti(plejLista.getId())) {
                if (i.getId() == izvodjenje.getId()) {
                    JOptionPane.showMessageDialog(null, "Ovo izvodjenje se vec nalazi u zeljenoj plejlisti.");
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                PlejListaDAO.insertIzvodjenje(plejLista, izvodjenje);
                JOptionPane.showMessageDialog(null, "Izvodjenje je dodato u " + plejLista.getNaziv());
            }

        }
    }
}
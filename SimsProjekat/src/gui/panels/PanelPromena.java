package gui.panels;

import dao.UrednikDAO;
import gui.dialogs.DialogPromena;
import kontroler.UrednikovProzorKON;
import model.Urednik;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPromena extends JPanel {
    private DialogPromena dialog;
    private JLabel labela,labela1,labela2;
    private JPasswordField lozinka;
    private JTextField lozinka1,lozinka2;
    private JButton sacuvaj,otkazi;
    public int id;

    public PanelPromena(DialogPromena dialog, int idUrednika) {
        this.dialog = dialog;
        this.id = idUrednika;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(226, 206, 158));
        setLayout(null);
        namesti();
    }
    public void namesti(){

        Urednik u = UrednikDAO.getUrednikPoId(id);

        labela = new JLabel("      Stara lozinka:");
        labela.setBounds(20, 50 ,120, 23);
        labela.setBackground(Color.white);
        labela.setOpaque(true);
        labela.setBorder(BorderFactory.createLineBorder(Color.black));
        add(labela);

        lozinka = new JPasswordField();
        lozinka.setBounds(160, 50, 120, 23);
        lozinka.setBorder(BorderFactory.createLineBorder(Color.black));
        lozinka.setBackground(Color.white);
        lozinka.setOpaque(true);
        lozinka.setEditable(true);
        add(lozinka);

        labela1 = new JLabel("      Nova lozinka:");
        labela1.setBounds(20, 100 ,120, 23);
        labela1.setBackground(Color.white);
        labela1.setOpaque(true);
        labela1.setBorder(BorderFactory.createLineBorder(Color.black));
        labela1.setToolTipText("Slaba: manje od 4, srednja: manje od 8, velika slova, jaka: specijalni znakovi(*,_,..)");
        add(labela1);

        lozinka1 = new JTextField();
        lozinka1.setBounds(160, 100, 120, 23);
        lozinka1.setBorder(BorderFactory.createLineBorder(Color.black));
        lozinka1.setBackground(Color.white);
        lozinka1.setOpaque(true);
        lozinka1.setEditable(true);
        add(lozinka1);

        labela2 = new JLabel("      Ponovi:");
        labela2.setBounds(20, 150 ,120, 23);
        labela2.setBackground(Color.white);
        labela2.setOpaque(true);
        labela2.setBorder(BorderFactory.createLineBorder(Color.black));
        add(labela2);

        lozinka2 = new JTextField();
        lozinka2.setBounds(160, 150, 120, 23);
        lozinka2.setBorder(BorderFactory.createLineBorder(Color.black));
        lozinka2.setBackground(Color.white);
        lozinka2.setOpaque(true);
        lozinka2.setEditable(true);
        add(lozinka2);

        sacuvaj = new JButton("Sacuvaj");
        sacuvaj.setBackground(new Color(62, 100, 103));
        sacuvaj.setForeground(Color.WHITE);
        sacuvaj.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        sacuvaj.setBounds(20, 200, 120, 23);
        sacuvaj.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                String l,l1,l2;
                l = lozinka.getText();
                l1 = lozinka1.getText();
                l2 = lozinka2.getText();
                boolean value;
                try {
                    value = UrednikovProzorKON.proveraLozinke(u,l,l1,l2);
                    JOptionPane.showMessageDialog(dialog,"Uspesno promenjena lozinka!");
                    dialog.setVisible(false);
                } catch (Exception exception) {
                    String tipIzuzetka = exception.getMessage();
                    if (tipIzuzetka.equals("1")) {
                        JOptionPane.showMessageDialog(dialog,"Niste uneli dobru staru lozinku!");
                    }else if(tipIzuzetka.equals("2")) {
                        JOptionPane.showMessageDialog(dialog,"Niste uneli  dobru novu lozinku!");
                    }


                }}

        });
        add(sacuvaj);
        otkazi = new JButton("Otkazi");
        otkazi.setBackground(new Color(62, 100, 103));
        otkazi.setForeground(Color.WHITE);
        otkazi.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        otkazi.setBounds(160, 200, 120, 23);
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);

            }
        });
        add(otkazi);



    }
}

package gui.panels;

import dao.AdministratorDAO;
import dao.KorisnickiNalogDAO;
import gui.dialogs.DialogPromjeneLoznikeAdmin;
import kontroler.AdminovProzorKON;
import model.Administrator;
import model.KorisnickiNalog;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PanelPromjeneLoznikeAdmin extends JPanel {
    private DialogPromjeneLoznikeAdmin dialog;
    private Administrator admin;
    private JLabel stara, nova, nova1;
    private JPasswordField staraT, novaT, nova1T;
    private JButton otkazi, promijeni;
    public JLabel oprez;

    public PanelPromjeneLoznikeAdmin(DialogPromjeneLoznikeAdmin d, Administrator a){
        this.dialog=d;
        this.admin=a;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setLayout(null);
        namesti();
    }

    private void namesti() {

        Border outBorder =  BorderFactory.createLineBorder(new Color(0, 122, 153),2, true);
        Border inBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        setBorder(BorderFactory.createCompoundBorder(inBorder, outBorder));

        stara=new JLabel("Unesite staru lozinku");
        stara.setBounds(40, 40, 120, 30);
        add(stara);

        staraT=new JPasswordField();
        staraT.setBounds(40, 80, 200, 30);
        add(staraT);

        nova=new JLabel("Unesite novu lozinku");
        nova.setBounds(40, 120, 120, 30);
        add(nova);

        novaT=new JPasswordField();
        novaT.setBounds(40, 160, 200, 30);
        add(novaT);

        nova1=new JLabel("Ponovo unesite novu lozinku");
        nova1.setBounds(40, 200, 150, 30);
        add(nova1);

        nova1T=new JPasswordField();
        nova1T.setBounds(40,240, 200, 30);
        add(nova1T);

        promijeni=new JButton("Promeni");
        promijeni.setBounds(190, 320, 80, 30);
        promijeni.setBackground(new Color(0, 122, 153));
        promijeni.setForeground(Color.white);
        promijeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int retVal = AdminovProzorKON.provjeriLozinku(admin, new String(staraT.getPassword()), new String(novaT.getPassword()),new String(nova1T.getPassword()));
                if(retVal==1){
                    JOptionPane.showMessageDialog(null,"Stara sifra se ne poklapa sa unesenom");
                    staraT.setText("");
                }
                else if(retVal==2){
                    JOptionPane.showMessageDialog(null,"Nove sifre se ne poklapaju");
                    novaT.setText("");
                    nova1T.setText("");

                }
                else if(retVal==3){
                    JOptionPane.showMessageDialog(null,"Molimo unesite sve podatke ponovo");
                    staraT.setText("");
                    nova1T.setText("");
                    novaT.setText("");
                }
                else if(retVal==0){
                    Administrator a= AdministratorDAO.getAdministrator(admin.getId());
                    String sifra = new String(novaT.getPassword());
                    a.getNalog().setLozinka(sifra);
                    KorisnickiNalog nalog=a.getNalog();
                    try {
                        KorisnickiNalogDAO.update(nalog);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        add(promijeni);

        otkazi=new JButton("Otkazi");
        otkazi.setBounds(280, 320, 80, 30);
        otkazi.setBackground(new Color(0, 122, 153));
        otkazi.setForeground(Color.white);
        otkazi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int odgovor=JOptionPane.showConfirmDialog(null,
                        "Jeste li sigurni da zelite da ponistite promenu?", "Ponisti",JOptionPane.YES_NO_OPTION);
                if(odgovor==0){
                    setVisible(false);
                }
            }
        });
        add(otkazi);

    }
}

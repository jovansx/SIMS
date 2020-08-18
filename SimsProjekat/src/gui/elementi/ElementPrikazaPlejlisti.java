package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.PlejListaDAO;
import gui.dialogs.DialogPlaylisti;
import model.PlejLista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ElementPrikazaPlejlisti extends JPanel {
    private DialogPlaylisti dialog;
    private JLabel naziv, javno, brIzvodjenja, brIzvodjenja1,slika;
    private JButton prikazi, izmeni, sacuvaj;
    private PlejLista lista;
    private JCheckBox javno1;
    private JTextField naziv1;


    public ElementPrikazaPlejlisti(DialogPlaylisti dp, PlejLista p){
        this.dialog=dp;
        this.lista=p;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255, 204, 204));
        setPreferredSize(new Dimension(500, 200));
        setLayout(null);
        namesti();
    }

    private void namesti() {
        String separator = System.getProperty("file.separator");
        ImageIcon retImageIcon = IzvodjenjeDAO.getSlikuIzvodjenja(lista.getListaIzvodjenja().get(0), separator);
        Image im = retImageIcon.getImage();
        Image myImg = im.getScaledInstance(200, 150 , Image.SCALE_DEFAULT);
        ImageIcon newImage = new ImageIcon(myImg);

        slika = new JLabel(newImage);
        slika.setBounds(30, 50, 200, 150);
        add(slika);

        naziv=new JLabel("Naziv:");
        naziv.setBounds(240, 50, 80, 30);
        add(naziv);

        naziv1=new JTextField(lista.getNaziv());
        naziv1.setBounds(320, 50, 200,30 );
        naziv1.setEditable(false);
        add(naziv1);

        javno1=new JCheckBox("Javno",lista.isJeJavna());
        javno1.setBounds(240, 80, 80, 30);
        javno1.setEnabled(false);
        add(javno1);

        brIzvodjenja=new JLabel("Broj stavki:");
        brIzvodjenja.setBounds(240, 110, 80, 30);
        add(brIzvodjenja);

        brIzvodjenja1=new JLabel(""+lista.getListaIzvodjenja().size());
        brIzvodjenja1.setBounds(320, 110, 100, 30);
        add(brIzvodjenja1);

        prikazi=new JButton("Prikazi");
        prikazi.setBackground(new Color(0, 77, 102));
        prikazi.setForeground(Color.white);
        prikazi.setBounds(240, 170, 70, 30);
        add(prikazi);

        izmeni=new JButton("Izmeni");
        izmeni.setBackground(new Color(0, 77, 102));
        izmeni.setForeground(Color.white);
        izmeni.setBounds(320, 170, 70, 30);
        add(izmeni);

        sacuvaj=new JButton("Sacuvaj");
        sacuvaj.setBackground(new Color(0, 77, 102));
        sacuvaj.setForeground(Color.white);
        sacuvaj.setBounds(400, 170, 70, 30);

        dodajAkcije();

    }

    private void dodajAkcije() {

        izmeni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                naziv1.setEditable(true);
                javno1.setEnabled(true);
                add(sacuvaj);
            }
        });

        sacuvaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(naziv1.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Molimo unesite naziv!");
                }
                else{
                    lista.setJeJavna(javno1.isSelected());
                    lista.setNaziv(naziv1.getText());
                    try {
                        PlejListaDAO.update(lista);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
    }
}

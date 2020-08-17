package gui.elementi;

import dao.IzvodjenjeDAO;
import gui.dialogs.DialogPlaylisti;
import model.PlejLista;

import javax.swing.*;
import java.awt.*;

public class ElementPrikazaPlejlisti extends JPanel {
    private DialogPlaylisti dialog;
    private JLabel naziv, javno, brIzvodjenja, naziv1, brIzvodjenja1,slika;
    private JButton prikazi;
    private PlejLista lista;
    private JCheckBox javno1;

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
        slika.setIcon(newImage);

        add(slika);

        naziv=new JLabel("Naziv:");
        naziv.setBounds(250, 60, 80, 30);
        add(naziv);

        naziv1=new JLabel(lista.getNaziv());
        naziv1.setBounds(370, 60, 100,30 );
        add(naziv1);

        javno1=new JCheckBox("Javno",lista.isJeJavna());
        javno1.setBounds(250, 110, 80, 30);
        add(javno1);

        prikazi=new JButton("Prikazi");
        prikazi.setBounds(420, 85, 70, 30);
        add(prikazi);

    }
}

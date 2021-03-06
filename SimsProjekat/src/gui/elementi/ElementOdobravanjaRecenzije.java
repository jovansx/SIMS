package gui.elementi;

import dao.MuzickoDeloDAO;
import dao.RecenzijaDAO;
import gui.dialogs.DialogAdminovihRecenzija;
import model.MuzickoDelo;
import model.Recenzija;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class ElementOdobravanjaRecenzije extends JPanel {
    private DialogAdminovihRecenzija dialog;
    private Recenzija recenzija;
    private JLabel id, ocjena, komentar, autor, id1, ocjena1, komentar1, autor1, djelo, djelo1;
    private JButton odobri, odbij;
    
    public ElementOdobravanjaRecenzije(DialogAdminovihRecenzija d, Recenzija r){
        this.dialog=d;
        this.recenzija=r;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255, 204, 204));
        setPreferredSize(new Dimension(400, 170));
        setLayout(null);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        namesti();
        podesiAkcije();
    }



    private void namesti() {
        id=new JLabel("Id recenzije: ");
        id.setBounds(20, 20, 90, 20);
        id.setFont(new Font("Courier", Font.BOLD, 12));
        add(id);

        id1=new JLabel(""+recenzija.getId());
        id1.setBounds(100, 20, 40, 20);
        add(id1);

        ocjena=new JLabel("Ocena: ");
        ocjena.setBounds(20, 40, 70, 20);
        ocjena.setFont(new Font("Courier", Font.BOLD, 12));
        add(ocjena);

        ocjena1=new JLabel(""+recenzija.getOcena());
        ocjena1.setBounds(100, 40, 100, 20);
        add(ocjena1);

        komentar=new JLabel("Komentar: ");
        komentar.setBounds(20, 60, 80, 20);
        komentar.setFont(new Font("Courier", Font.BOLD,12));
        add(komentar);

        komentar1=new JLabel(recenzija.getKomentar());
        komentar1.setBounds(100, 60, 300, 20);
        add(komentar1);

        autor=new JLabel("Urednik: ");
        autor.setBounds(20, 80, 70, 20);
        autor.setFont(new Font("Courier", Font.BOLD, 12));
        add(autor);

        autor1=new JLabel(recenzija.getUrednik().getIme()+" "+ recenzija.getUrednik().getPrezime());
        autor1.setBounds(100, 80, 200, 20);
        add(autor1);

        djelo=new JLabel("Muzicko delo: ");
        djelo.setBounds(20,100, 100, 20);
        djelo.setFont(new Font("Courier", Font.BOLD, 12));
        add(djelo);

        String nazivDjela="";
        if(recenzija.getMuzickoDelo()==null){
            nazivDjela=getNazivDjela((ArrayList<MuzickoDelo>) MuzickoDeloDAO.getMuzickaDelaIzvodjenja(recenzija.getIzvodnjenje().getId()));
        }
        else{
            nazivDjela=recenzija.getMuzickoDelo().getNazivDela();
        }
        djelo1=new JLabel(nazivDjela);
        djelo1.setBounds(120, 100, 200, 20);
        add(djelo1);

        odobri=new JButton("Odobri");
        odobri.setBackground(new Color(77, 121, 255));
        odobri.setForeground(Color.white);
        odobri.setBounds(270, 120, 80, 30);
        add(odobri);

        odbij= new JButton("Odbij");
        odbij.setBounds(360,120, 80, 30);
        odbij.setBackground(new Color(77, 121, 255));
        odbij.setForeground(Color.white);
        add(odbij);

    }
    private void podesiAkcije() {
        odobri.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recenzija.setOdobreno(true);
                try {
                    RecenzijaDAO.update(recenzija);
                    JOptionPane.showMessageDialog(null, "Rezenzija je odobrena.");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dialog.deleteComponent(ElementOdobravanjaRecenzije.this);
            }
        });

        odbij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recenzija.setOdobreno(false);
                try {
                    RecenzijaDAO.update(recenzija);
                    JOptionPane.showMessageDialog(null, "Recenzija je odbijena.");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                dialog.deleteComponent(ElementOdobravanjaRecenzije.this);
            }
        });

    }

    public static String getNazivDjela(ArrayList<MuzickoDelo> djela){
        String naziv="";
        for(MuzickoDelo d: djela){
            naziv+=d.getNazivDela()+" ";
        }
        return naziv;
    }

}

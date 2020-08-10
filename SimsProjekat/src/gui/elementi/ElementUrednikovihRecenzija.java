package gui.elementi;

import dao.MuzickoDeloDAO;
import dao.RecenzijaDAO;
import gui.dialogs.DialogAdminovihRecenzija;
import gui.dialogs.DialogUrednika;
import gui.dialogs.DialogUrednikovihRecenzija;
import model.MuzickoDelo;
import model.Recenzija;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ElementUrednikovihRecenzija extends JPanel{
    private DialogUrednikovihRecenzija dialog;
    private Recenzija recenzija;
    private double prosjecnaocjena;
    private JLabel id, ocjena, komentar, id1, ocjena1, komentar1, prOcjena, prOcjena1, muzickoDelo, muzickoDelo1;


    public ElementUrednikovihRecenzija(DialogUrednikovihRecenzija dr, Recenzija recenzija, Double prosjecnaOcjena){
        this.dialog=dr;
        this.recenzija=recenzija;
        this.prosjecnaocjena=prosjecnaOcjena;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(255, 204, 204));
        setPreferredSize(new Dimension(600, 170));
        setLayout(null);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        namesti();

    }

    private void namesti() {
        id=new JLabel("Id recenzije: ");
        id.setBounds(20, 20, 120, 20);
        id.setFont(new Font("Courier", Font.BOLD, 12));
        add(id);

        id1=new JLabel(""+recenzija.getId());
        id1.setBounds(140, 20, 40, 20);
        add(id1);

        ocjena=new JLabel("Ocena: ");
        ocjena.setBounds(20, 40, 120, 20);
        ocjena.setFont(new Font("Courier", Font.BOLD, 12));
        add(ocjena);

        ocjena1=new JLabel(""+recenzija.getOcena());
        ocjena1.setBounds(140, 40, 100, 20);
        add(ocjena1);

        prOcjena=new JLabel("Prosjecna ocena:");
        prOcjena.setBounds(20, 60, 120, 20);
        prOcjena.setFont(new Font("Courier", Font.BOLD, 12));
        add(prOcjena);

        prOcjena1=new JLabel(""+prosjecnaocjena);
        prOcjena1.setBounds(140, 60, 70, 20);
        add(prOcjena1);

        komentar=new JLabel("Komentar: ");
        komentar.setBounds(20, 80, 120, 20);
        komentar.setFont(new Font("Courier", Font.BOLD,12));
        add(komentar);

        komentar1=new JLabel(recenzija.getKomentar());
        komentar1.setBounds(140, 80, 300, 20);
        add(komentar1);

        muzickoDelo=new JLabel("Muzkocko delo/a:");
        muzickoDelo.setBounds(20, 100, 120,20);
        muzickoDelo.setFont(new Font("Courier", Font.BOLD, 12));
        add(muzickoDelo);

        muzickoDelo1=new JLabel(naziviMuzickihDela());
        muzickoDelo1.setBounds(140, 100, 300, 20);
        add(muzickoDelo1);


    }
    public String naziviMuzickihDela(){
        String naziv=" ";
        if(recenzija.getIzvodnjenje()==null){
            naziv=recenzija.getMuzickoDelo().getNazivDela();
        }
        else{
            ArrayList<MuzickoDelo> dela= (ArrayList<MuzickoDelo>) MuzickoDeloDAO.getMuzickaDelaIzvodjenja(recenzija.getIzvodnjenje().getId());
            for(MuzickoDelo m: dela){
                naziv+=m.getNazivDela()+" ";
            }
        }
        return naziv;
    }

}

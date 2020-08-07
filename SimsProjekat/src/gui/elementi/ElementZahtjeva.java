package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.MuzickoDeloDAO;
import gui.dialogs.DialogZahteva;
import model.MuzickoDelo;
import model.Zahtev;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.ArrayList;

public class ElementZahtjeva extends JPanel {
    private JLabel korisnik, naziv, opis, recenzija, muzickaDela,id, ocena, komentar, korisnik1;
    private JButton odobri, odbij;
    private Zahtev zahtev;
    private DialogZahteva dialog;

    public ElementZahtjeva(Zahtev z, DialogZahteva dh){
        this.dialog=dh;
        this.zahtev=z;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setPreferredSize(new Dimension(400, 340));
        setLayout(null);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        namesti();
    }

    private void namesti() {

        korisnik=new JLabel("Korisnik: ");
        korisnik.setBounds(20, 20, 70, 30);
        korisnik.setFont(new Font("Courier", Font.BOLD,14));
        add(korisnik);
        korisnik1= new JLabel(zahtev.getPodnosilacZahteva().getIme()+" "+
                zahtev.getPodnosilacZahteva().getPrezime()+" "+zahtev.getPodnosilacZahteva().getNalog().getKorisnickoIme());
        korisnik1.setBounds(90, 20, 200, 30);
        add(korisnik1);

        naziv= new JLabel("Naziv: ");
        naziv.setFont(new Font("Courier", Font.BOLD,12));
        naziv.setBounds(20, 60, 70, 20);
        add(naziv);

        JLabel naziv1=new JLabel(zahtev.getNaziv());
        naziv1.setBounds(90, 60, 200, 20);
        add(naziv1);

        opis=new JLabel("Opis: ");
        opis.setBounds(20, 90, 70, 20);
        opis.setFont(new Font("Courier", Font.BOLD,12));
        add(opis);

        JLabel opis1= new JLabel(zahtev.getOpis());
        opis1.setBounds(90, 90, 400, 20);
        add(opis1);

        recenzija=new JLabel("Recenzija: ");
        recenzija.setFont(new Font("Courier", Font.BOLD,12));
        recenzija.setBounds(20, 120, 100, 20);
        add(recenzija);

        id=new JLabel("    id: "+zahtev.getRecenzija().getId());
        id.setBounds(20, 160, 100, 20);
        add(id);

        ocena=new JLabel("    ocena: "+zahtev.getRecenzija().getOcena());
        ocena.setBounds(20, 180, 100, 20);
        add(ocena);

        komentar=new JLabel("    komentar: "+zahtev.getRecenzija().getKomentar());
        komentar.setBounds(20, 200, 300, 20);
        add(komentar);

        muzickaDela=new JLabel("    muzicko delo/a: "+ naziviMuzickihDela());
        muzickaDela.setBounds(20, 220, 400, 20);
        add(muzickaDela);

        odobri=new JButton("Odobri");
        odobri.setBounds(270, 280, 80, 30);
        odobri.setBackground(new Color(0, 77, 102));
        odobri.setForeground(Color.white);
        add(odobri);

        odbij= new JButton("Odbij");
        odbij.setBounds(360,280, 80, 30);
        odbij.setBackground(new Color(0, 77, 102));
        odbij.setForeground(Color.white);
        add(odbij);

    }

    public String naziviMuzickihDela(){
        String naziv=" ";
        if(zahtev.getRecenzija().getIzvodnjenje()==null){
            naziv=zahtev.getRecenzija().getMuzickoDelo().getNazivDela();
        }
        else{
            ArrayList<MuzickoDelo> dela= (ArrayList<MuzickoDelo>) MuzickoDeloDAO.getMuzickaDelaIzvodjenja(zahtev.getRecenzija().getIzvodnjenje().getId());
            for(MuzickoDelo m: dela){
                naziv+=m.getNazivDela()+" ";
            }
        }
        return naziv;
    }


}

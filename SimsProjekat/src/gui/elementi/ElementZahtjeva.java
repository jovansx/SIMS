package gui.elementi;

import dao.IzvodjenjeDAO;
import dao.MuzickoDeloDAO;
import gui.dialogs.DialogZahteva;
import model.MuzickoDelo;
import model.Zahtev;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ElementZahtjeva extends JPanel {
    private JLabel korisnik, naziv, opis, recenzija, muzickaDela,id, ocena, komentar;
    private JButton odobri, odbij;
    private Zahtev zahtev;
    private DialogZahteva dialog;

    public ElementZahtjeva(Zahtev z, DialogZahteva dh){
        this.dialog=dh;
        this.zahtev=z;
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(229, 255, 204));
        setPreferredSize(new Dimension(400, 220));
        setLayout(null);
        namesti();
    }

    private void namesti() {
        korisnik=new JLabel("Korisnik: "+zahtev.getPodnosilacZahteva().getIme()+" "+
                zahtev.getPodnosilacZahteva().getPrezime()+" "+zahtev.getPodnosilacZahteva().getNalog().getKorisnickoIme());
        korisnik.setBounds(20, 20, 300, 30);
        add(korisnik);

        naziv= new JLabel("Naziv: "+zahtev.getNaziv());
        naziv.setBounds(20, 60, 300, 30);
        add(naziv);

        opis=new JLabel("Opis: "+zahtev.getOpis());
        opis.setBounds(20, 100, 300, 30);
        add(opis);

        recenzija=new JLabel("Recenzija: ");
        recenzija.setBounds(20, 130, 40, 30);
        add(recenzija);

        id=new JLabel("    id: "+zahtev.getRecenzija().getId());
        id.setBounds(20, 150, 100, 30);
        add(id);

        ocena=new JLabel("    ocena: "+zahtev.getRecenzija().getOcena());
        ocena.setBounds(20, 180, 100, 30);
        add(ocena);

        komentar=new JLabel("    komentar: "+zahtev.getRecenzija().getKomentar());
        komentar.setBounds(20, 210, 300, 30);
        add(komentar);

        muzickaDela=new JLabel("    muzicko delo/a: "+ naziviMuzickihDela());
        muzickaDela.setBounds(20, 240, 400, 30);
        add(muzickaDela);

        odobri=new JButton("Odobri");
        odobri.setBounds(300, 300, 80, 30);
        odobri.setBackground(new Color(0, 77, 102));
        odobri.setForeground(Color.white);
        add(odobri);

        odbij= new JButton("Odbij");
        odbij.setBounds(390,300, 80, 30);
        odbij.setBackground(new Color(0, 77, 102));
        odbij.setBackground(Color.white);
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

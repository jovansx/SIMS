package gui.elementi;

import dao.PlejListaDAO;
import gui.dialogs.DialogPlaylisti;
import model.PlejLista;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;

public class KorisnikovProzor extends GlavniProzor {
    JButton profilButton,ponistiRecenzijuButton,glasanje ,playliste;
    public int idKorisnika;

    public KorisnikovProzor(RegistrovaniKorisnik korisnik) {
        super();
        idKorisnika = korisnik.getId();
        inicijalizuj();

        podesiNaknadneAkcije(korisnik);

    }

    private void podesiNaknadneAkcije(RegistrovaniKorisnik korisnik) {

        profilButton.addActionListener(e -> {
            PrikazProfila pp = new PrikazProfila(KorisnikovProzor.this, korisnik);
            pp.setVisible(true);
        });

        ponistiRecenzijuButton.addActionListener(e -> {
            PrikazRecenzija rp = new PrikazRecenzija(KorisnikovProzor.this, korisnik);
            rp.setVisible(true);
        });

        playliste.addActionListener(e->{
            prikaziPlejlisteKorisnika(korisnik);
        });
        glasanje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                if(now.getDayOfMonth() == 1){
                    // mesecno
                    Top tp = new Top(true,now.getMonthValue()-1,now.getYear(),false);
                    tp.setVisible(true);

                }else if(now.getMonthValue() == 12 && now.getDayOfMonth() == 31){
                    // godisnje
                    Top tp = new Top(false,now.getMonthValue(),now.getYear(),false);
                    tp.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(KorisnikovProzor.this,"Glasanje nije moguce!");
                }
            }
        });
    }

    /** Provjerava da li ima plejlista za prikazivanje*/
    private void prikaziPlejlisteKorisnika(RegistrovaniKorisnik korisnik) {
        if(PlejListaDAO.plejlisteKorisnika(idKorisnika).isEmpty()){
            JOptionPane.showMessageDialog(null, "Korisnik nema nijednu plejlistu!");
        }
        else{
            DialogPlaylisti dp=new DialogPlaylisti(PlejListaDAO.plejlisteKorisnika(idKorisnika),korisnik);
            dp.setVisible(true);
        }
    }

    private void inicijalizuj() {
        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);

        profilButton = new JButton("Moj profil");
        profilButton.setBackground(new Color(59, 86, 90));
        panelAkcija.add(profilButton);

        ponistiRecenzijuButton = new JButton("Ponistavanje recenzije");
        ponistiRecenzijuButton.setBackground(new Color(59, 86, 90));
        panelAkcija.add(ponistiRecenzijuButton);

        playliste=new JButton("Playliste");
        playliste.setBackground(new Color(59,86, 90 ));
        panelAkcija.add(playliste);

        glasanje = new JButton("Glasanje");
        glasanje.setBackground(new Color(59,86, 90 ));
        panelAkcija.add(glasanje);

    }
}

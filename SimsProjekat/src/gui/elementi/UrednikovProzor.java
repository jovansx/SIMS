package gui.elementi;

import gui.dialogs.DialogOdobravanje;
import gui.dialogs.DialogProfil;
import gui.dialogs.DialogZadaci;
import model.Urednik;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class UrednikovProzor extends GlavniProzor{
    public int idUrednika;
    JButton profil,odobravanje,glasanje,zadaci;

    public UrednikovProzor(Urednik urednik) {
        super();
        idUrednika = urednik.getId();
        inicijalizuj();
    }
    private void inicijalizuj() {

        panelOperacija.remove(prijavaButton);
        panelOperacija.remove(registracijaButton);
        odjavaButton.setVisible(true);

        panelAkcija.setLayout(new BoxLayout(panelAkcija,BoxLayout.PAGE_AXIS));
        profil = new JButton("Profil");
        profil.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogProfil dp = new DialogProfil(idUrednika);
                dp.setVisible(true);

            }

        });
        panelAkcija.add(profil);
        odobravanje = new JButton("Odobravanje komentara");
        odobravanje.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogOdobravanje dp = new DialogOdobravanje(idUrednika);
                dp.setVisible(true);

            }

        });
        panelAkcija.add(odobravanje);
        glasanje = new JButton("Glasanje");
        glasanje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime now = LocalDateTime.now();
                if(now.getDayOfMonth() == 1){
                    // mesecno
                    Top tp = new Top(true,now.getMonthValue()-1,now.getYear());
                    tp.setVisible(true);

                }else if(now.getMonthValue() == 12 && now.getDayOfMonth() == 31){
                    // godisnje
                    Top tp = new Top(false,now.getMonthValue(),now.getYear());
                    tp.setVisible(false);

                }else{
                    JOptionPane.showMessageDialog(UrednikovProzor.this,"Glasanje nije moguce!");
                }
            }
        });
        panelAkcija.add(glasanje);
        zadaci = new JButton("Lista zadataka");
        zadaci.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent arg0) {
                DialogZadaci dz = new DialogZadaci(idUrednika);
                dz.setVisible(true);

            }

        });
        panelAkcija.add(zadaci);

    }
}

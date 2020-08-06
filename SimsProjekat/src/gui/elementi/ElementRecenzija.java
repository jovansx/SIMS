package gui.elementi;

import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElementRecenzija extends JPanel {
    private JLabel labelaOpisa;
    private JLabel labelaOcene;
    private JLabel labelaAutora;
    private JButton prikaziPodatkeAutoraButton;
    private JPanel panelGlavni;

    public ElementRecenzija(Recenzija recenzija) {
        super();


        inicijalizuj(recenzija);

        add(panelGlavni);

        podesiAkcije(recenzija);

        add(panelGlavni);
    }

    private void podesiAkcije(Recenzija recenzija) {

        prikaziPodatkeAutoraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikaziKorisnika pk = new PrikaziKorisnika(recenzija.getAutorRecenzije(), ElementRecenzija.this);
                pk.setVisible(true);
            }
        });
    }

    private void inicijalizuj(Recenzija recenzija) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();

        //panelGlavni.setPreferredSize( new Dimension(dimension.width/4*3, dimension.height/20*3));
        panelGlavni.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 30, dimension.height / 15));

        if(recenzija.getAutorRecenzije() != null) {
            prikaziPodatkeAutoraButton.setText("Prikazi korisnika");
            labelaAutora.setText(recenzija.getAutorRecenzije().toString());
        }else {
            prikaziPodatkeAutoraButton.setText("Prikazi urednika");
            labelaAutora.setText(recenzija.getUrednik().toString());
        }
        labelaOpisa.setText(recenzija.getKomentar());
        labelaOcene.setText(recenzija.getOcena() + "");
    }
}

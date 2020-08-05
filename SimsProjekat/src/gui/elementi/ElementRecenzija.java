package gui.elementi;

import dao.IzvodjenjeDAO;
import model.Izvodjac;
import model.Korisnik;
import model.MuzickoDelo;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ElementRecenzija extends JPanel {
    private JLabel labelaOpisa;
    private JLabel labelaOcene;
    private JLabel labelaAutora;
    private JButton prikaziPodatkeAutoraButton;
    private JPanel panelGlavni;

    public ElementRecenzija(Recenzija recenzija){
        super();

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();

        //panelGlavni.setPreferredSize( new Dimension(dimension.width/4*3, dimension.height/20*3));
        panelGlavni.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 30 , dimension.height / 15));
        add(panelGlavni);

        labelaAutora.setText(recenzija.getAutorRecenzije().toString());
        labelaOpisa.setText(recenzija.getKomentar());
        labelaOcene.setText(recenzija.getOcena()+"");



        prikaziPodatkeAutoraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrikaziKorisnika pk = new PrikaziKorisnika(recenzija.getAutorRecenzije(), ElementRecenzija.this);
                pk.setVisible(true);
            }
        });
    }
}

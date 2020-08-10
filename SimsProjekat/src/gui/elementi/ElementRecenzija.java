package gui.elementi;

import dao.RegistrovaniKorisnikDAO;
import model.Recenzija;

import javax.swing.*;
import java.awt.*;

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
    }

    private void podesiAkcije(Recenzija recenzija) {

        prikaziPodatkeAutoraButton.addActionListener(e -> {
            PrikaziKorisnika pk;
            if (recenzija.getAutorRecenzije() != null)
                pk = new PrikaziKorisnika(recenzija.getAutorRecenzije());
            else
                pk = new PrikaziKorisnika(recenzija.getUrednik());
            pk.setVisible(true);
        });
    }

    private void inicijalizuj(Recenzija recenzija) {
        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();

        //panelGlavni.setPreferredSize( new Dimension(dimension.width/4*3, dimension.height/20*3));
        panelGlavni.setPreferredSize(new Dimension(dimension.width / 4 + dimension.width / 30, dimension.height / 15));

        if (recenzija.getAutorRecenzije() != null) {
            prikaziPodatkeAutoraButton.setText("Prikazi korisnika");
            labelaAutora.setText(recenzija.getAutorRecenzije().toString());
        } else {
            prikaziPodatkeAutoraButton.setText("Prikazi urednika");
            labelaAutora.setText(recenzija.getUrednik().toString());
        }
        labelaOpisa.setText(recenzija.getKomentar());
        labelaOcene.setText(recenzija.getOcena() + "");

        boolean proveraJavnosti = true;
        if (recenzija.getAutorRecenzije() != null)
            proveraJavnosti = RegistrovaniKorisnikDAO.proveraVidljivostiKorisnika(recenzija.getAutorRecenzije());

        prikaziPodatkeAutoraButton.setEnabled(proveraJavnosti);


    }
}

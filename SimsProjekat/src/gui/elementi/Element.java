package gui.elementi;

import model.Izvodjenje;
import model.Zanr;

import javax.swing.*;

public class Element extends JPanel{
    private JPanel panelGlavni;
    private JLabel labelaNaslova;
    private JLabel labelaOpisa;
    private JLabel labelaIkone;

    public Element(Zanr z) {
        super();
        add(panelGlavni);
        /*
        labelaNaslova.setText(i.getListaMuzickihDela().get(0).getNazivDela());

        labelaOpisa.setText("");
        for (Izvodjac izv: i.getListaIzvodjaca()) {
            labelaOpisa.setText(labelaOpisa.getText()+","+izv.getNazivIzvodjaca());
        }
        labelaOpisa.setText("Izvodjaci : "+ labelaOpisa.getText());

         */
        labelaNaslova.setText(z.getNazivZanra());
        //labelaOpisa.setText(z.getOpis());


    }
}

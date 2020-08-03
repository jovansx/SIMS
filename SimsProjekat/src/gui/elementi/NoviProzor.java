package gui.elementi;

import javax.swing.*;

public class NoviProzor extends GlavniProzor{
    JButton dugme, dugme2, dugme3;

    public NoviProzor(){
        super();
        panelAkcija.setLayout(new BoxLayout(panelAkcija, BoxLayout.PAGE_AXIS));
        dugme = new JButton("dugme1");
        panelAkcija.add(dugme);
        dugme2 = new JButton("Prikazi profilllllllllllllllllAAAAAAAAAAAA");
        panelAkcija.add(dugme2);
        dugme3 = new JButton("jeeeeeej");
        panelAkcija.add(dugme3);
    }
}
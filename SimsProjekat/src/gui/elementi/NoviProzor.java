package gui.elementi;

import javax.swing.*;

public class NoviProzor extends GlavniProzor{
    JButton dugme;

    public NoviProzor(){
        super();
        dugme = new JButton("dugme1");
        panelAkcija.setLayout(new BoxLayout(panelAkcija, BoxLayout.PAGE_AXIS));
        panelAkcija.add(dugme);
    }
}
package gui.elementi;


import model.Izvodjenje;
import model.TopLista;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Toop extends ElementIzvodjenja {

    public Toop(Izvodjenje iz,Top top,boolean v) {
        super(iz);
        podesi(iz,top,v);
    }
    private void podesi(Izvodjenje iz, Top top,boolean v) {

        panelGlavni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PrikazIzvodjenjaTop pi = new PrikazIzvodjenjaTop(iz, top,v);
                pi.setVisible(true);
            }
        });

    }
}









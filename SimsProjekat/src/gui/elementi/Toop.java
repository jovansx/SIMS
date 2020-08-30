package gui.elementi;


import model.Izvodjenje;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Toop extends ElementIzvodjenja {
    public Toop(Izvodjenje iz,Top top) {
        super(iz);
        podesi(iz,top);
    }
    private void podesi(Izvodjenje iz, Top top) {

        panelGlavni.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PrikazIzvodjenjaTop pi = new PrikazIzvodjenjaTop(iz, top);
                pi.setVisible(true);
            }
        });

    }
}









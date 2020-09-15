package gui.elementi;

import kontroler.ToplistaKON;
import kontroler.UrednikovProzorKON;
import model.TopLista;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PrikazTopliste extends JFrame{
    private JPanel glavniPanel;
    private JPanel panelSkrol;
    private JScrollPane skrol;
    private Dimension dimension;
    private String separator;
    private JLabel nothingFoundL;
    private int brojElemenataIzvodjenja;
    private List<TopLista> topliste;
    public PrikazTopliste() {
        super();
        this.topliste = null;
        inicijalizuj();

        setSize(dimension.width / 5 * 3, dimension.height / 5 * 3);

        namesti();
        add(glavniPanel);

        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void inicijalizuj(){
        brojElemenataIzvodjenja = 5;
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        separator = System.getProperty("file.separator");
        nothingFoundL = new JLabel(new ImageIcon(
                "SimsProjekat" + separator + "src" + separator + "gui" + separator + "icons" + separator + "nothingFound.png"));

        skrol.getVerticalScrollBar().setUnitIncrement(16);
        panelSkrol.setLayout(new BoxLayout(panelSkrol, BoxLayout.Y_AXIS));
    }
    public void osveziKomponentu(Component component) {
        component.validate();
        component.repaint();
    }

    private void namesti(){
        panelSkrol.removeAll();
        topliste = ToplistaKON.getTopliste();
        if (topliste != null && topliste.size() == 0)panelSkrol.add(nothingFoundL);

        if (topliste != null) {
            for (TopLista t : topliste) {
                panelSkrol.add(new PrikazNazivaTopliste(t,PrikazTopliste.this));
            }
        }
        osveziKomponentu(panelSkrol);
        osveziKomponentu(skrol);
    }
}



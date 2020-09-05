package gui.dialogs;

import gui.elementi.ElementIzvodjenjaUPlejlisti;
import gui.elementi.ElementPrikazaPlejlisti;
import model.Izvodjenje;
import model.PlejLista;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DialogIzvodjenjaUPlejlisti extends JDialog{
    private JPanel skrolPanel;
    private JScrollPane skrol;
    private List<Izvodjenje> izvodjenja;
    private List<ElementIzvodjenjaUPlejlisti> listaElemenata;
    private PlejLista plejlista;

    public DialogIzvodjenjaUPlejlisti(List<Izvodjenje> izvodjenja, PlejLista plejlista){
        this.izvodjenja=izvodjenja;
        this.listaElemenata=new ArrayList<>();
        this.plejlista=plejlista;

        setTitle("Playlista "+plejlista.getNaziv());
        setSize(530, 460);
        setResizable(false);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        ucitajSkrol();
    }

    private void ucitajSkrol() {
        skrol=new JScrollPane();
        skrol.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        skrolPanel=new JPanel();
        skrolPanel.setLayout(new BoxLayout(skrolPanel, BoxLayout.Y_AXIS));
        resetElemente();

        for(Izvodjenje i : izvodjenja ){
            ElementIzvodjenjaUPlejlisti ez= new ElementIzvodjenjaUPlejlisti(this, i, plejlista);
            listaElemenata.add(ez);
            skrolPanel.add(ez);
            JLabel labela = new JLabel("                                        ");
            labela.setForeground(new Color(77, 121, 255));
            skrolPanel.add(labela);
        }

        skrolPanel.validate();
        skrolPanel.repaint();

        skrol.validate();
        skrol.repaint();

        skrol.setViewportView(skrolPanel);
        skrol.setPreferredSize(new Dimension(600, 170));
        add(skrol,BorderLayout.CENTER);
    }

    private void resetElemente() {
        listaElemenata.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }
}

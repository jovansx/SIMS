package gui.dialogs;

import dao.IzvodjenjeDAO;
import dao.PlejListaDAO;
import gui.elementi.ElementPrikazaPlejlisti;
import kontroler.PlejlistaKON;
import model.PlejLista;
import model.RegistrovaniKorisnik;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DialogPlaylisti extends JDialog{
    private JPanel panel;
    private JButton posalji, uredu;
    private JPanel skrolPanel;
    private JScrollPane skrol;
    private List<PlejLista> plejliste;
    private List<ElementPrikazaPlejlisti> listaElemenata;
    private RegistrovaniKorisnik korisnik;

    public DialogPlaylisti(List<PlejLista> lista, RegistrovaniKorisnik rg){
        this.plejliste=lista;
        this.listaElemenata=new ArrayList<>();
        this.korisnik=rg;

        setTitle("Playliste");
        setSize(600, 650);
        setResizable(false);
        setModal(true);
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

        for(PlejLista p : plejliste ){
            if(!IzvodjenjeDAO.izvodjenjaUPlejlisti(p.getId()).isEmpty()){
                ElementPrikazaPlejlisti ez= new ElementPrikazaPlejlisti(this, p);
                listaElemenata.add(ez);
                skrolPanel.add(ez);
                JLabel labela = new JLabel("                                        ");
                labela.setForeground(new Color(77, 121, 255));
                skrolPanel.add(labela);
            }

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

    public void refreshDialog(int id){
        skrolPanel.remove(PlejlistaKON.getLista());
        listaElemenata.remove(PlejlistaKON.getLista());

        PlejlistaKON.resetujListu();
        PlejLista lista= PlejListaDAO.getPlejLista(id);
        assert lista != null;
        lista.setListaIzvodjenja(IzvodjenjeDAO.izvodjenjaUPlejlisti(lista.getId()));

        ElementPrikazaPlejlisti ez= new ElementPrikazaPlejlisti(this, lista);

        listaElemenata.add(ez);
        skrolPanel.add(ez);
        skrolPanel.revalidate();
        skrolPanel.repaint();


    }

}

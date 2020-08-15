package gui.dialogs;

import dao.RecenzijaDAO;
import gui.elementi.ElementUrednika;
import model.Recenzija;
import model.Urednik;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DialogUrednika extends JDialog {
    public ArrayList<Urednik> urednici;
    private ArrayList<ElementUrednika> elementiUrednika;
    private JPanel skrolPanel;
    private JScrollPane skrol;

    public DialogUrednika(ArrayList<Urednik> urednici){
        this.urednici=urednici;
        this.elementiUrednika=new ArrayList<>();

        setTitle("Pregled urednika");
        setSize(400, 650);
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

        for(Urednik u: urednici){
            u.setListaRecenzija((ArrayList<Recenzija>) RecenzijaDAO.getRecenzijeUrednika(u.getId()));
            int br=u.getListaRecenzija().size();
            ElementUrednika ez= new ElementUrednika(this, u, br);
            elementiUrednika.add(ez);
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
        skrol.setPreferredSize(new Dimension(400, 540));
        add(skrol,BorderLayout.CENTER);
    }

    private void resetElemente() {
        elementiUrednika.clear();
        skrolPanel.removeAll();
        skrolPanel.validate();
        skrolPanel.repaint();
    }
}

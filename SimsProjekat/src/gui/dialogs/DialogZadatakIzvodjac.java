package gui.dialogs;

import gui.panels.PanelZadatakIzvodjac;

import javax.swing.*;

public class DialogZadatakIzvodjac extends JDialog {
    public PanelZadatakIzvodjac panel;
    public int id;
    public DialogZadatakIzvodjac(int id) {
        setTitle("Opis izvodjaca");
        setSize(700, 450);
        setResizable(false);
        this.id = id;
        panel = new PanelZadatakIzvodjac(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

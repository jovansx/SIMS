package gui.dialogs;

import gui.panels.PanelIzvodjenje;


import javax.swing.*;

public class DialogIzvodjenje extends JDialog {
    public PanelIzvodjenje panel;
    public int id;
    public DialogIzvodjenje(int id) {
        setTitle("Opis izvodjenja muzickog dela");
        setSize(400, 400);
        setResizable(false);
        this.id = id;
        panel = new PanelIzvodjenje(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

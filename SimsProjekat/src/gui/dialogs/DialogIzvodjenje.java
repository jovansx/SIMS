package gui.dialogs;

import gui.panels.PanelIzvodjenje;
import model.MuzickoDelo;


import javax.swing.*;

public class DialogIzvodjenje extends JDialog {
    public PanelIzvodjenje panel;
    public int id;
    public DialogIzvodjenje(int id, DialogZadatakDelo dz, int idZadatka, MuzickoDelo md) {
        setTitle("Opis izvodjenja muzickog dela");
        setSize(400, 450);
        setResizable(false);
        this.id = id;
        panel = new PanelIzvodjenje(this,id,dz,idZadatka,md);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

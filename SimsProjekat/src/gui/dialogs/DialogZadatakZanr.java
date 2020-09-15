package gui.dialogs;
import gui.panels.PanelZadatakZanr;

import javax.swing.*;

public class DialogZadatakZanr extends JDialog {
    public PanelZadatakZanr panel;
    public int id;
    public DialogZadatakZanr(int id) {
        setTitle("Opis zanra");
        setSize(500, 450);
        setResizable(false);
        this.id = id;
        panel = new PanelZadatakZanr(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

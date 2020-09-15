package gui.dialogs;
import gui.panels.PanelZadatakUcesnik;

import javax.swing.*;

public class DialogZadatakUcesnik  extends JDialog{
    public PanelZadatakUcesnik panel;
    public int id;
    public DialogZadatakUcesnik(int id) {
        setTitle("Opis ucesnika");
        setSize(700, 450);
        setResizable(false);
        this.id = id;
        panel = new PanelZadatakUcesnik(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

package gui.dialogs;
import gui.panels.PanelDodajDela;

import javax.swing.*;

public class DialogDodajDela extends JDialog {
    public PanelDodajDela panel;
    public int id;
    public DialogDodajDela(int id) {
        setTitle("Izbor muzickih dela za album");
        setSize(300, 300);
        setResizable(false);
        this.id = id;
        panel = new PanelDodajDela(this,id);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

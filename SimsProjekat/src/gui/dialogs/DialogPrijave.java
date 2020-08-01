package gui.dialogs;

import gui.panels.PanelPrijave;

import javax.swing.*;

public class DialogPrijave extends JDialog {

    public DialogPrijave() {

        setTitle("Prijava na sistem");
        setResizable(false);
        add(new PanelPrijave(this));
        pack();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

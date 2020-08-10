package gui.dialogs;


import gui.panels.PanelPromena;

import javax.swing.*;

public class DialogPromena extends JDialog {
    public PanelPromena panel;

    public DialogPromena(int idUrednika) {
        setTitle("Promena lozinke");
        setSize(300, 300);
        setResizable(false);
        panel = new PanelPromena(this,idUrednika);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

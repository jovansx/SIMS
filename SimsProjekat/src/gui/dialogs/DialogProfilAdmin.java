package gui.dialogs;

import gui.panels.PanelProfilAdmina;

import javax.swing.*;

public class DialogProfilAdmin extends JDialog{
    public PanelProfilAdmina panel;

    public DialogProfilAdmin() {
        setTitle("Profil administratora");
        setSize(500, 500);
        setResizable(false);
        panel = new PanelProfilAdmina(this);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

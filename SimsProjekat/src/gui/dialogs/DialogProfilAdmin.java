package gui.dialogs;

import gui.panels.PanelProfilAdmina;
import model.Administrator;

import javax.swing.*;

public class DialogProfilAdmin extends JDialog{
    public PanelProfilAdmina panel;
    private Administrator admin;
    public DialogProfilAdmin(Administrator admin) {
        this.admin=admin;

        setTitle("Profil administratora");
        setSize(500, 500);
        setResizable(false);
        panel = new PanelProfilAdmina(this, admin);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

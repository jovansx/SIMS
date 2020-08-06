package gui.dialogs;

import gui.panels.PanelPromjeneLoznikeAdmin;
import model.Administrator;

import javax.swing.*;

public class DialogPromjeneLoznikeAdmin extends JDialog {

    public PanelPromjeneLoznikeAdmin panel;
    private Administrator admin;

    public DialogPromjeneLoznikeAdmin(Administrator admin){
        this.admin=admin;
        panel=new PanelPromjeneLoznikeAdmin(this, admin);
        setTitle("Promeni lozinku");
        setSize(400, 450);
        setResizable(false);
        setContentPane(panel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}

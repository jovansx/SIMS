package gui.dialogs;

import gui.elementi.ElementPrikazaPlejlisti;
import gui.elementi.KorisnikovProzor;
import model.PlejLista;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class DialogPlaylisti extends JDialog{
    private JPanel panel;
    private JButton posalji, otkazi;
    private JPanel skrolPanel;
    private JScrollPane skrol;
    private List<PlejLista> plejliste;
    private List<ElementPrikazaPlejlisti> listaElemenata;

    public DialogPlaylisti(KorisnikovProzor kp, List<PlejLista> lista){
        this.plejliste=lista;
        this.listaElemenata=new ArrayList<>();


    }

}

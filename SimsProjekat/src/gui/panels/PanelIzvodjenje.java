package gui.panels;

import dao.*;
import gui.dialogs.DialogIzvodjenje;
import kontroler.UrednikovProzorKON;
import model.*;
import model.enums.TipIzvodjenja;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PanelIzvodjenje extends JPanel{
    public SimpleDateFormat formatter1=new SimpleDateFormat("dd-mm-yyyy");
    private DialogIzvodjenje dialog;
    private JLabel trajanje, vreme,delo,izvodj,mest;
    private JTextField trajanje1,vreme1,delo1;
    private JButton kreiraj, odustani;
    private JComboBox<String> combo,combo1;
    private JRadioButton audio,video,zapis;
    private ButtonGroup group;
    public int id;

    public PanelIzvodjenje(DialogIzvodjenje dialog, int idMuzickogDela) {
        this.dialog = dialog;
        this.id = idMuzickogDela;
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new BorderLayout(0, 0));
        setBackground(new Color(226, 206, 158));
        setLayout(null);
        namesti();

    }
    public void namesti(){
        MuzickoDelo md = MuzickoDeloDAO.getMuzickoDelo(id);
        ArrayList<MestoIzvodjenja> mesta = (ArrayList<MestoIzvodjenja>) MestoIzvodjenjaDAO.getMesta();
        ArrayList<Izvodjac> izvodjaci = (ArrayList<Izvodjac>) IzvodjacDAO.getIzvodjaci();
        ArrayList<Izvodjac> lista = new ArrayList<>();
        Izvodjenje i = new Izvodjenje();

        delo = new JLabel("      Naziv dela");
        delo.setBounds(50, 30 ,140, 23);
        delo.setBackground(Color.white);
        delo.setOpaque(true);
        delo.setBorder(BorderFactory.createLineBorder(Color.black));
        add(delo );

        delo1 = new JTextField();
        delo1.setBounds(200, 30, 140, 23);
        delo1.setBorder(BorderFactory.createLineBorder(Color.black));
        delo1.setBackground(Color.white);
        delo1.setOpaque(true);
        delo1.setText(md.getNazivDela());
        delo1.setEditable(false);
        add(delo1);

        trajanje = new JLabel("      Trajanje izvodjenja");
        trajanje.setBounds(50, 80 ,140, 23);
        trajanje.setBackground(Color.white);
        trajanje.setOpaque(true);
        trajanje.setBorder(BorderFactory.createLineBorder(Color.black));
        add(trajanje);

        trajanje1 = new JTextField();
        trajanje1.setBounds(200, 80, 140, 23);
        trajanje1.setBorder(BorderFactory.createLineBorder(Color.black));
        trajanje1.setBackground(Color.white);
        trajanje1.setOpaque(true);
        trajanje1.setEditable(true);
        add(trajanje1);

        vreme = new JLabel("      Datum(dd-mm-yyyy)");
        vreme.setBounds(50, 130 ,140, 23);
        vreme.setBackground(Color.white);
        vreme.setOpaque(true);
        vreme.setBorder(BorderFactory.createLineBorder(Color.black));
        add(vreme);

        vreme1 = new JTextField();
        vreme1.setBounds(200, 130, 140, 23);
        vreme1.setBorder(BorderFactory.createLineBorder(Color.black));
        vreme1.setBackground(Color.white);
        vreme1.setOpaque(true);
        vreme1.setEditable(true);
        add(vreme1);

        mest = new JLabel("      Mesto");
        mest .setBounds(50, 180 ,140, 23);
        mest.setBackground(Color.white);
        mest .setOpaque(true);
        mest .setBorder(BorderFactory.createLineBorder(Color.black));
        add(mest);

        combo = new JComboBox<String>();
        combo.setBounds(200, 180, 140, 23);
        for(MestoIzvodjenja m :mesta){
            combo.setSelectedItem(null);
            combo.addItem(m.getNazivMesta());
        }
        combo.setSelectedItem(null);
        add(combo);

        combo.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                i.setMestoIzvodjenja(MestoIzvodjenjaDAO.getMestoIzvodjenjaNaziv(value));

            }
        });


        izvodj = new JLabel("      Izvodjac/i");
        izvodj.setBounds(50, 230 ,140, 23);
        izvodj.setBackground(Color.white);
        izvodj.setOpaque(true);
        izvodj.setBorder(BorderFactory.createLineBorder(Color.black));
        add(izvodj);

        combo1 = new JComboBox<String>();
        combo1.setBounds(200, 230, 140, 23);
        for(Izvodjac iz :izvodjaci){
            combo1.setSelectedItem(null);
            combo1.addItem(iz.getNazivIzvodjaca());
        }
        combo1.setSelectedItem(null);
        add(combo1);

        combo1.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent event) {
                String value = (String) event.getItem();
                lista.add(IzvodjacDAO.getIzvodjacNaziv(value));

            }
        });

        audio = new JRadioButton("Audio");
        audio.setBounds(50,280,100,20);
        audio.setActionCommand("Audio");
        audio.setBackground(Color.white);
        add(audio);

        video = new JRadioButton("Video");
        video.setBounds(150,280,100,20);
        video.setActionCommand("Video");
        video.setBackground(Color.white);
        add(video);

        zapis = new JRadioButton("Zapis");
        zapis.setBounds(250,280,100,20);
        zapis.setActionCommand("Zapis");
        zapis.setBackground(Color.white);
        add(zapis);

        group = new ButtonGroup();
        group.add(audio);
        group.add(video);
        group.add(zapis);

        kreiraj = new JButton("    Gotovo    ");
        kreiraj.setBounds(50,330,120,23);
        kreiraj.setBackground(new Color(62, 100, 103));
        kreiraj.setForeground(Color.WHITE);
        kreiraj.setBorder(BorderFactory.createLineBorder(Color.black));
        kreiraj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        if(trajanje1.getText()== "" || vreme1.getText() == ""){
                            if(!video.isSelected() && !audio.isSelected() && !zapis.isSelected()){
                                JOptionPane.showMessageDialog(PanelIzvodjenje.this,"Morate selektovati video, audio ili zapis!");
                            }else{
                                JOptionPane.showMessageDialog(PanelIzvodjenje.this,"Morate popuniti sva polja!");
                            }
                        }else{
                        i.setTrajanje(Integer.valueOf(trajanje1.getText()));
                        i.setVremeIzvodjenja(new java.sql.Date(formatter1.parse(vreme1.getText()).getTime()));
                        if(video.isSelected()){
                            i.setTipIzvodjenja(TipIzvodjenja.VIDEO);
                        }else if(audio.isSelected()){
                            i.setTipIzvodjenja(TipIzvodjenja.AUDIO);
                        }else if(zapis.isSelected()){
                            i.setTipIzvodjenja(TipIzvodjenja.ZAPIS);
                        }
                        i.setListaIzvodjaca(lista);
                        UrednikovProzorKON.dodajIzvodjenje(md.getId(),i);
                        JOptionPane.showMessageDialog(PanelIzvodjenje.this,"Uspesno ste opisali izvodjenje!");
                        dialog.setVisible(false);
                    }
                    }
                    catch (SQLException | ParseException throwables) {
                        throwables.printStackTrace();
                    }

            }
        });
        add(kreiraj);

    }
}

package kontroler;

import dao.AdministratorDAO;
import dao.KorisnickiNalogDAO;
import dao.RegistrovaniKorisnikDAO;
import dao.UrednikDAO;
import model.KorisnickiNalog;
import model.Korisnik;
import model.enums.TipKorisnika;

import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class PrijavaKON {

    public static boolean proveriKorisnickoIme(String korIme) {
        return !korIme.equals("");
    }

    public static boolean posaljiMailPovratkaLozinke(String korIme){

        KorisnickiNalog nalog = KorisnickiNalogDAO.getNalogPoKorisnickomImenu(korIme);
        Korisnik korisnik = null;
        if(nalog == null)
            return false;
        else if(nalog.getKorisnik().equals(TipKorisnika.REGISTROVANI))
            korisnik = RegistrovaniKorisnikDAO.getPoIdNaloga(nalog);
        else if(nalog.getKorisnik().equals(TipKorisnika.UREDNIK))
            korisnik = UrednikDAO.getPoIdNaloga(nalog);
        else if(nalog.getKorisnik().equals(TipKorisnika.ADMINISTRATOR))
            korisnik = AdministratorDAO.getPoIdNaloga(nalog);

        boolean uspesnoSlanje = posaljiMailKorisniku(korisnik);

        return uspesnoSlanje;
    }

    private static boolean posaljiMailKorisniku(Korisnik korisnik) {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "abuljevic8@gmail.com";
        String password = "15101999";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, password);
            }
        });

        String lozinka = "korisnikMuzSis" + korisnik.getNalog().getId();
        Message message = prepareMessage(session, myAccount, korisnik.getEmail(), lozinka);

        String proslaLozinka = korisnik.getNalog().getLozinka();
        korisnik.getNalog().setLozinka(lozinka);
        KorisnickiNalogDAO.updateLozinka(korisnik.getNalog().getId(), korisnik.getNalog().getLozinka());

        try {
            Transport.send(message);
        } catch (Exception ex) {
            korisnik.getNalog().setLozinka(proslaLozinka);
            KorisnickiNalogDAO.updateLozinka(korisnik.getNalog().getId(), korisnik.getNalog().getLozinka());
            System.out.println("neuspeh");
        }

        return true;
    }
    private static Message prepareMessage(Session session, String myAccount, String recepient, String lozinka) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Muzicki Sistem - promena lozinke");
            message.setText("Vasa nova lozinka sa kojom mozete da se ulogujete je:\n" +
                    lozinka);
            message.setSentDate(new Date(System.currentTimeMillis()));
            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

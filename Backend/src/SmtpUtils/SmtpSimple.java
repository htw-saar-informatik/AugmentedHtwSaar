package SmtpUtils;
/*


import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SmtpSimple {

	public void sendMail(String smtpHost, String username, String password,
			String to, String from, String subject, String text) {

		MailAuthenticator auth = new MailAuthenticator(username, password);
		Properties properties = new Properties();

		properties.put("mail.transport.protocol", "smtp");
		// Den Properties wird die ServerAdresse hinzugef�gt
		properties.setProperty("mail.smtp.host", smtpHost);
		// !!Wichtig!! Falls der SMTP-Server eine Authentifizierung verlangt
		// muss an dieser Stelle die Property auf "true" gesetzt werden
		properties.setProperty("mail.smtp.auth", "true");
		// Hier wird mit den Properties und dem implements Contructor
		// erzeugten MailAuthenticator eine Session erzeugt
		Session session = Session.getDefaultInstance(properties, auth);

		try {
			// Eine neue Message erzeugen
			Message msg = new MimeMessage(session);

			// Hier werden die Absender- und Empf�ngeradressen gesetzt
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to, false));

			// Der Betreff und Body der Message werden gesetzt
			msg.setSubject(subject);
			msg.setText(text);

			// Hier lassen sich HEADER-Informationen hinzuf�gen
			msg.setHeader("Test", "Test");
			msg.setSentDate(new Date());

			// Zum Schluss wird die Mail nat�rlich noch verschickt
			Transport.send(msg);
			System.out.println("mail has been sent");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void sendMail(String [] args) {

		MailAuthenticator auth = new MailAuthenticator(args[1], args[2]);
		Properties properties = new Properties();

		properties.put("mail.transport.protocol", "smtp");
		// Den Properties wird die ServerAdresse hinzugef�gt
		properties.setProperty("mail.smtp.host", args[0]);
		// !!Wichtig!! Falls der SMTP-Server eine Authentifizierung verlangt
		// muss an dieser Stelle die Property auf "true" gesetzt werden
		properties.setProperty("mail.smtp.auth", "true");
		// Hier wird mit den Properties und dem implements Contructor
		// erzeugten MailAuthenticator eine Session erzeugt
		Session session = Session.getDefaultInstance(properties, auth);

		try {
			// Eine neue Message erzeugen
			Message msg = new MimeMessage(session);

			// Hier werden die Absender- und Empf�ngeradressen gesetzt
			msg.setFrom(new InternetAddress(args[4]));
			msg.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(args[3], false));

			// Der Betreff und Body der Message werden gesetzt
			msg.setSubject(args[5]);
			msg.setText(args[6]);

			// Hier lassen sich HEADER-Informationen hinzuf�gen
			msg.setHeader("Test", "Test");
			msg.setSentDate(new Date());

			// Zum Schluss wird die Mail nat�rlich noch verschickt
			Transport.send(msg);
			System.out.println("mail has been sent");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class MailAuthenticator extends Authenticator {

		private final String user;
		private final String password;

		/**
		 * Der Konstruktor erzeugt ein MailAuthenticator Objekt
		 * aus den beiden Parametern user und passwort.
		 * 
		 * @param user String, der Username fuer den Mailaccount.
		 * @param password String, das Passwort fuer den Mailaccount.
		 

		public MailAuthenticator(String user, String password) {
			this.user = user;
			this.password = password;
		}

		/**
		 * Diese Methode gibt ein neues PasswortAuthentication Objekt zurueck.
		 
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(this.user, this.password);
		}
	}

	public static void main(String[] args) {
		SmtpSimple a = new SmtpSimple();
		a.sendMail("",
				"root",
				"",
				"alexander.huber@htwsaar.de",
				"info@ntest.com",
				"Registrierungsmail",
				"Registrierungsmail"
				);
	}
/*}
*/
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

public class SmtpCfgReader {

	public static String getSubject() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("subject");
	}

	public static String getHostname() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("smtpHost");
	}

	public static String getText() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("text");
	}

	public static String getUsername() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("username");
	}

	public static String getPassword() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("password");
	}

	public static String getFrom() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("from");
	}

	public static String getTo() throws Exception {
		Properties p = new Properties();
		BufferedInputStream b = new BufferedInputStream(new FileInputStream(
				"./config/smtp.properties"));
		p.load(b);
		b.close();
		return p.getProperty("to");
	}

}

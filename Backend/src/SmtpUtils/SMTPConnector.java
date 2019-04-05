public class SMTPConnector {
	private String username;
	private String password;
	private String to;
	private String from;
	private String text;
	private String subject;
	private String smtpHost;

	public SMTPConnector() {
	}

	public SMTPConnector(String username, String password, String to,
			String from, String text, String subject, String smtpHost) {
		username = this.username;
		password = this.password;
		to = this.to;
		from = this.from;
		text = this.text;
		subject = this.subject;
		smtpHost = this.smtpHost;
	}

	public void connect() throws Exception {
		this.username = SmtpCfgReader.getUsername();
		this.password = SmtpCfgReader.getPassword();
		this.to = SmtpCfgReader.getTo();
		this.from = SmtpCfgReader.getFrom();
		this.text = SmtpCfgReader.getText();
		this.subject = SmtpCfgReader.getSubject();
		this.smtpHost = SmtpCfgReader.getHostname();
	}

	public void setUsername(String username) {
		username = this.username;
	}

	public void setPassword(String password) {
		password = this.password;
	}

	public void setTo(String to) {
		to = this.to;
	}

	public void setFrom(String from) {
		from = this.from;
	}

	public void setText(String text) {
		text = this.text;
	}
	public void setSubject(String subject) {
		subject = this.subject;
	}

	public void setSmtpHost(String smtpHost) {
		smtpHost = this.smtpHost;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getText() {
		return text;
	}

	public String getSubject() {
		return subject;
	}

	public String getSmtpHost() {
		return smtpHost;
	}
}

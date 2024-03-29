package directedstudy.georgiacentral.Email;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import directedstudy.georgiacentral.Objects.JSSEProvider;

/**
 * Created by lakshaysharma on 1/24/18.
 */

/**
 * Represents the EmailUtility class that contains functions to send emails
 */
public class EmailUtility extends Authenticator {

    private String host = "smtp.gmail.com";
    private String user = "ecommerce4050@gmail.com";
    private String password = "ecommercecsci4050";
    private Session session;

    /**
     * Static constructor
     */
    static {
        Security.addProvider(new JSSEProvider());
    }


    /**
     * Constructor to represent an Email Utility Object
     * @return An EmailUtility Object
     */
    public EmailUtility() {
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.quitwait", "false");

        session = Session.getDefaultInstance(props, this);
    }

    /**
     * Authenticates a user
     * @return PasswordAuthnetication Onbject
     */
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

    /**
     * Sends an email to the user using the information provided
     * @param subject
     * @param body
     * @param sender
     * @param recipients
     * @throws Exception
     */
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {
        try {
            MimeMessage message = new MimeMessage(session);
            DataHandler dataHandler = new DataHandler(new ByteArrayDS(body.getBytes(), "text/plain"));
            message.setSender(new InternetAddress(sender));
            message.setSubject(subject);
            message.setDataHandler(dataHandler);
            if (recipients.indexOf(',') > 0)
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
            else
                message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
            Transport.send(message);
        } catch (Exception e) {
            Log.d("Exception:", "Error in sending: " + e.toString());
        }
    }

    /**
     * Represents the ByteArrayDS that is used to convert Strings to bytes while sending emails
     */
    public class ByteArrayDS implements DataSource {
        private byte[] bytes;
        private String type;

        /**
         * Constructor
         * @param data
         * @param type
         */
        public ByteArrayDS(byte[] data, String type) {
            super();
            this.bytes = data;
            this.type = type;
        }

        /**
         * Constructor
         * @param data
         */
        public ByteArrayDS(byte[] data) {
            super();
            this.bytes = data;
        }

        /**
         * Sets the type
         * @param type
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return ContentType
         */
        public String getContentType() {
            if (type == null)
                return "application/octet-stream";
            else
                return type;
        }

        /**
         *
         * @return An InputStream Object
         * @throws IOException
         */
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(bytes);
        }

        /**
         * @return Name of the DS
         */
        public String getName() {
            return "ByteArrayDataSource";
        }

        /**
         * @throws IOException
         */
        public OutputStream getOutputStream() throws IOException {
            throw new IOException("Not Supported");
        }
    }
}

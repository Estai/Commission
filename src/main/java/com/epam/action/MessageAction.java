package com.epam.action;

import com.epam.file.mail.SmtpMessageSender;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ResourceBundle;

public class MessageAction implements Action {
    private ResourceBundle resourceBundle=ResourceBundle.getBundle("app");
    private ResourceBundle db=ResourceBundle.getBundle("db");
    @Override
    public ActionResult execute(HttpServletRequest req, HttpServletResponse resp)  {
        String email = req.getParameter("email");
        String parameter = req.getParameter("message");

        String smtpHost = resourceBundle.getString("smtpHost");
        int smtpPort = Integer.parseInt(resourceBundle.getString("smtpPort"));
        String username = db.getString("username");
        String password = db.getString("pass");
        SmtpMessageSender messageSender = null;
        try {
            messageSender = new SmtpMessageSender();
            Session session = messageSender.createSession(smtpHost, smtpPort, username, password);

            MimeMessage message = messageSender.createMimeMessage(session, "КарГТУ", resourceBundle.getString("email"), email, Message.RecipientType.TO);

            messageSender.addText(message, parameter, "utf-8", "plain");


            messageSender.sendMimeMessage(message);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new ActionResult("admin/enrollee",true);
    }
}

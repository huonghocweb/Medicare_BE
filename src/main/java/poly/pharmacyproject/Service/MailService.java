package poly.pharmacyproject.Service;

import jakarta.mail.MessagingException;
import org.springframework.stereotype.Service;
import poly.pharmacyproject.Model.Response.MailInfo;

@Service
public interface MailService {
    void send(MailInfo mail) throws MessagingException;
    void send(String to, String subject, String body) throws MessagingException;
    void queue(MailInfo mail);

    // Chỉ khai báo jakarta.mail.MessagingException
//    void sendResetPasswordEmail(String email, String token) throws MessagingException;
    void sendResetCodeEmail(String email, String code) throws MessagingException;
    void queue(String to, String subject, String body);
}

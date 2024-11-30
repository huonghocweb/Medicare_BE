package poly.pharmacyproject.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MailInfo {
    String from = "MedicareStore@gmail.com";
    String to ;
    String[] cc;
    String[] bcc;
    String subject;
    String body;
    List<File> files = new ArrayList<>();

    public MailInfo(String to, String subject, String body) {
    }
}

package springbootjpatareapaquete.Controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactoController {
	@Autowired
	private JavaMailSender mailSender;
@GetMapping("/contacto")
public String contacto() {
	return "contacto";
}
@PostMapping("/contacto")
public String contactoenvio(HttpServletRequest request) throws MessagingException {
	String nombre = request.getParameter("nombre");
	String correo = request.getParameter("correo");
	String mensaje = request.getParameter("mensaje");
	MimeMessage message = mailSender.createMimeMessage();
	MimeMessageHelper helper = new MimeMessageHelper(message);
	String asunto = "El cliente " + nombre + " envió un nuevo mensaje.";
	String contenido = "<p><b>Nombre del cliente</b>"+nombre+"</p>" +
			"<p><b>Correo del cliente</b>"+correo+"</p>"
			+ "<p><b>Mensaje del cliente</b>"+mensaje+"</p>";
    helper.setTo("gabriela.n.stocco@gmail.com");
    helper.setSubject(asunto);
    helper.setText(contenido, true);
    mailSender.send(message);
	return "redirect:/contacto";
}
}

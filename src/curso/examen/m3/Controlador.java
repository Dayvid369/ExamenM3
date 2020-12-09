package curso.examen.m3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import curso.examen.m3.LeerDatos;

/**
 * Servlet implementation class Controlador
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Controlador() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: completar
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: completar
		
		

		String emailDestinatario=request.getParameter("emaildestino");
		String nombre=request.getParameter("nombre");
		String provincia=request.getParameter("provincia");
		String asunto=nombre+" - "+provincia;
		String mensajeDestinatario=request.getParameter("mensaje");
		String emaildeOrigen=request.getParameter("emaildeorigen");
		String pass=request.getParameter("clavemail");
		
		EmailDAO datosmensaje = new EmailDAO(nombre,emailDestinatario,provincia,mensajeDestinatario);
		
		 try {
			 datosmensaje.almacenarDatos(nombre,emailDestinatario,provincia,mensajeDestinatario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			// Propiedades de la conexion
			Properties prop = new Properties();
			// Nombre del servidor de salida
			prop.setProperty("mail.smtp.host", "smtp.gmail.com");
			// Habilitamos TLS
			prop.setProperty("mail.smtp.starttls.enable", "true");
			// Indicamos el puerto
			prop.setProperty("mail.smtp.port", "587");
			// Indicamos el usuario
			prop.setProperty("mail.smtp.user", emaildeOrigen);
			// Indicamos que requiere autenticación
			prop.setProperty("mail.smtp.auth", "true");

			// Creamos un objeto sesion
			Session sesion = Session.getDefaultInstance(prop);
			//TODO
			sesion.setDebug(true);
			// Creamos un objeto mensaje a traves de la sesion
			MimeMessage mensaje = new MimeMessage(sesion);
			
			// Indicamos la cuenta desde la que se va a enviar
			mensaje.setFrom(new InternetAddress(emaildeOrigen));

			// Añadimos el recipiente al mensaje al que va a ir dirigido el mensaje
			mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));

			// Creamos el asunto del mensaje
			mensaje.setSubject(asunto);

			// Creamos el cuerpo del mensaje
			mensaje.setText(mensajeDestinatario);

//			mensaje.setText(
//					"Esto es una prueba <br> <b>con Java Mail</b>",
//					"ISO-8859-1",
//					"html");
			
			// Utilizamos un objeto transport para hacer el envio indicando el protocolo
			Transport t = sesion.getTransport("smtp");
			// Hacemos la conexion
			t.connect(emaildeOrigen, pass);
			// Enviamos el mensaje
			t.sendMessage(mensaje, mensaje.getAllRecipients());

			// Cerramos la conexion
			t.close();

		} catch (AddressException ex) {
			Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
		} catch (MessagingException ex) {
			Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

	private void almacenarDatos(String nombre, String emailDestinatario, String provincia, String mensajeDestinatario) {
		// TODO Auto-generated method stub
		
	}

}

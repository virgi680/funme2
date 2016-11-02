package com.company.funme;




import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

	public class Correo
	{
	   public Correo() 
	   {
	       
	   }
	   /**
	    * main de prueba
	    * @param args Se ignoran.
	    */
	   
	   public void mandarCorreoBorrarEvento(String email,String usuario, String nombre,String dia,String hora)
	   {
	       try
	       {
	           //Propiedades de la conexión
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.host", "smtp.gmail.com");
	           props.setProperty("mail.smtp.starttls.enable", "true");
	           props.setProperty("mail.smtp.port", "587");
	           props.setProperty("mail.smtp.user", "proyectofunme@gmail.com");
	           props.setProperty("mail.smtp.auth", "true");
	           // Preparamos la sesion
	           Session session = Session.getDefaultInstance(props);//POR AQUI
	           // Construimos el mensaje
	           MimeMessage message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("proyectofunme@gmail.com"));
	           message.addRecipient(
	               Message.RecipientType.TO,
	               new InternetAddress(email));
	           message.setSubject("El evento "+ nombre + " ha sido borrado");
	           	           
	           message.setContent("<h3>Hola "+usuario.substring(0,1).toUpperCase()+
	        		   usuario.substring(1)+".</h3>Te informamos que el evento "
	        		   		+ "<font  size=4 color=\"0000FF\">"+ nombre +"</font> que se iba a celebrar el día "+
		               "<font size=4 color=\"0000FF\">"+ dia +
		               "</font> a las <font size=4 color=\"0000FF\">" + hora + "</font> ha sido <font size=4 color=\"FF0000\">cancelado </font>"
	               		+ " por su creador.\nDisculpe las molestias. <br><h3 style=\"color:rgb(0,0,255)\">El equipo de Funme!</h3>" ,
		               "text/html");

	           // Lo enviamos.
	           Transport t = session.getTransport("smtp");
	           t.connect("proyectofunme@gmail.com", "ProyectoFunme*");
	           t.sendMessage(message, message.getAllRecipients());
	           // Cierre.
	           t.close();
	       }
	       catch (Exception e)
	       {
	           e.printStackTrace();
	       }
	   }
	   
	   public void mandarCorreoDesunirte(String email,String nombre,String evento,String fecha,String hora)
	   {
	       try
	       {
	           // Propiedades de la conexión
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.host", "smtp.gmail.com");
	           props.setProperty("mail.smtp.starttls.enable", "true");
	           props.setProperty("mail.smtp.port", "587");
	           props.setProperty("mail.smtp.user", "proyectofunme@gmail.com");
	           props.setProperty("mail.smtp.auth", "true");
	           // Preparamos la sesion
	           Session session = Session.getDefaultInstance(props);//POR AQUI
	           // Construimos el mensaje
	           MimeMessage message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("proyectofunme@gmail.com"));
	           message.addRecipient(
	               Message.RecipientType.TO,
	               new InternetAddress(email));
	           message.setSubject("Desunido del evento: "+fecha+" a las: "+hora);
	           
	           message.setContent("<h3> Hola "+nombre.substring(0,1).toUpperCase()+nombre.substring(1)+ ",</h3>Te has <font size=4 color=\"FF0000\">desunido</font> correctamente del evento <font size=4 color=\"0000FF\">"+ evento + 
	               "</font> que se celebra el día "+ fecha + " a las " + hora + ". <h3 style=\"color:rgb(0,0,255)\">El equipo de Funme!</h3>", "text/html");
	           
	        
	           // Lo enviamos.
	           Transport t = session.getTransport("smtp");
	           t.connect("proyectofunme@gmail.com", "ProyectoFunme*");
	           t.sendMessage(message, message.getAllRecipients());
	           // Cierre.
	           t.close();
	       }
	       catch (Exception e)
	       {
	           e.printStackTrace();
	       }
	   }
	   
	   public void mandarCorreoUnirte(String email,String nombre,String evento,String fecha,String hora)
	   {
	       try
	       {
	           // Propiedades de la conexión
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.host", "smtp.gmail.com");
	           props.setProperty("mail.smtp.starttls.enable", "true");
	           props.setProperty("mail.smtp.port", "587");
	           props.setProperty("mail.smtp.user", "proyectofunme@gmail.com");
	           props.setProperty("mail.smtp.auth", "true");
	           // Preparamos la sesion
	           Session session = Session.getDefaultInstance(props);//POR AQUI
	           // Construimos el mensaje
	           MimeMessage message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("proyectofunme@gmail.com"));
	           message.addRecipient(
	               Message.RecipientType.TO,
	               new InternetAddress(email));
	           message.setSubject("Unido al evento: "+fecha+" a las: "+hora);
	           
	           message.setContent("<h3> Hola "+nombre.substring(0,1).toUpperCase()+nombre.substring(1)+ ",</h3>Te has unido correctamente al evento <font size=4 color=\"0000FF\">"+ evento + 
		               "</font> que se celebra el día <font size=4 color=\"0000FF\">"+ fecha + "</font> a las <font size=4 color=\"0000FF\">" + hora + ".</font><br><h3 style=\"color:rgb(0,0,255)\">El equipo de Funme!</h3>", "text/html");
		           
		        
	           // Lo enviamos.
	           Transport t = session.getTransport("smtp");
	           t.connect("proyectofunme@gmail.com", "ProyectoFunme*");
	           t.sendMessage(message, message.getAllRecipients());
	           // Cierre.
	           t.close();
	       }
	       catch (Exception e)
	       {
	           e.printStackTrace();
	       }
	   }
	   
	   public void mandarCorreoCrearEvento(String creador,String nombre,String evento,String fecha,String hora,String descripcion)
	   {
	       try
	       {
	           // Propiedades de la conexión
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.host", "smtp.gmail.com");
	           props.setProperty("mail.smtp.starttls.enable", "true");
	           props.setProperty("mail.smtp.port", "587");
	           props.setProperty("mail.smtp.user", "proyectofunme@gmail.com");
	           props.setProperty("mail.smtp.auth", "true");
	           // Preparamos la sesion
	           Session session = Session.getDefaultInstance(props);//POR AQUI
	           // Construimos el mensaje
	           MimeMessage message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("proyectofunme@gmail.com"));
	           message.addRecipient(
	               Message.RecipientType.TO,
	               new InternetAddress(creador));
	           message.setSubject("Evento creado el día: "+fecha+" a las: "+hora);
	           
	           message.setContent("<h3>Hola "+nombre.substring(0,1).toUpperCase()+
	        		   nombre.substring(1)+".</h3>Has creado correctamente el evento: "
	        		   		+ "<font  size=4 color=\"0000FF\">"+ evento + 
		               "</font> que se celebrará el día <font size=4 color=\"0000FF\">"+ fecha +
		               "</font> a las <font size=4 color=\"0000FF\">" + hora + "</font> <br> Esperamos que "
		               + "te diviertas<br><br><h3 style=\"color:rgb(0,0,255)\">El equipo de Funme!</h3>" ,
		               "text/html");

	           
	           
	           //message.setText("<!DOCTYPE html><html><head><title>Title of the document</title></head></html>");
	           // Lo enviamos.
	           Transport t = session.getTransport("smtp");
	           t.connect("proyectofunme@gmail.com", "ProyectoFunme*");
	           t.sendMessage(message, message.getAllRecipients());
	           // Cierre.
	           t.close();
	       }
	       catch (Exception e)
	       {
	           e.printStackTrace();
	       }
	   }
	   
	   public void mandarCorreoCrearRegistro(String email,String nombre)
	   {
	       try
	       {
	           // Propiedades de la conexión
	           Properties props = new Properties();
	           props.setProperty("mail.smtp.host", "smtp.gmail.com");
	           props.setProperty("mail.smtp.starttls.enable", "true");
	           props.setProperty("mail.smtp.port", "587");
	           props.setProperty("mail.smtp.user", "proyectofunme@gmail.com");
	           props.setProperty("mail.smtp.auth", "true");
	           // Preparamos la sesion
	           Session session = Session.getDefaultInstance(props);//POR AQUI
	           // Construimos el mensaje
	           MimeMessage message = new MimeMessage(session);
	           message.setFrom(new InternetAddress("proyectofunme@gmail.com"));
	           message.addRecipient(
	               Message.RecipientType.TO,
	               new InternetAddress(email));
	           message.setSubject("Bienvenido a Funme!");
	           message.setContent("<h3>Hola "+nombre.substring(0,1).toUpperCase()+
	        		   nombre.substring(1)+".</h3>Te has registrado correctamente. "
	        		   		+ "<h3 style=\"color:rgb(0,0,255)\">El equipo de Funme!</h3>" ,
		               "text/html");

	           
	           // Lo enviamos.
	           Transport t = session.getTransport("smtp");
	           t.connect("proyectofunme@gmail.com", "ProyectoFunme*");
	           t.sendMessage(message, message.getAllRecipients());
	           // Cierre.
	           t.close();
	       }
	       catch (Exception e)
	       {
	           e.printStackTrace();
	       }
	   }
	}
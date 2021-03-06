package com.company.funme;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.*;


public class MySQL {

	
	public int escribirDatosMySQL_REGISTRO(String email, String password, String nombre, String apellido,String fechaNacimiento, String genero){
		   
	       Connection conexion=null;

	       try{
	           Class.forName("com.mysql.jdbc.Driver");
	           conexion =  DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
	           java.sql.Statement s = conexion.createStatement(); 
     

	    	   int rs_update = s.executeUpdate("INSERT INTO usuario (email,nombre,apellido,password,fechaNacimiento,genero) VALUES ('"+email+"','"+nombre+"','"+apellido+"','"+password+"','"+fechaNacimiento+"','"+genero+"')");
	         
	           conexion.close();
	      }
	      catch(ClassNotFoundException e2){
	            System.out.println("ERROR escribirDatosMySQL_REGISTRO");
	            return -1;
	      }       
	      catch(Exception e){
	           System.out.println("ERROR escribirDatosMySQL_REGISTRO");
	           return -2;
	      }
	       Correo correo = new Correo();
           correo.mandarCorreoCrearRegistro(email,nombre);
	       return 0;
	  }
	
	public Usuario verPerfil(String email){  
	      
	      Connection conexion = null;
	      Usuario user = new Usuario ();
	      
	      try{
	         Class.forName("com.mysql.jdbc.Driver"); 
	         conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
	                     
	         java.sql.Statement s = conexion.createStatement();  
	         
	         //obtenemos la información del usuario de la base de datos.
	         ResultSet rs = s.executeQuery ("select * from usuario where usuario.email='"+ email+"';");
	         
	         
	         //Guardamos los campos en los atributos de la clase usuario.
	         
	         if(rs.next()){//si hay contenido en el rs... 
	        	  user.setEmail(rs.getString(2));
	              user.setPassword(rs.getString(3));
	              user.setNombre(rs.getString(4));
	              user.setApellidos(rs.getString(5));
	              user.setFecha(rs.getString(6));
	              user.setGenero(rs.getString(7));
	         }
	                     
	         conexion.close();
	      }
	      
	      catch(Exception e){
	             System.out.println("ERROR");
	      }
	    return user;
	}
	
	
	public String obtener_info_Evento(String nombre,String lugar) {

        Connection conexion = null;
        String respuestaJson = "{";
        int resultado = 0;
        Evento event = new Evento();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

            java.sql.Statement s = conexion.createStatement();

            // Obtenemos el numero de asistentes a un determinado evento
            ResultSet rs = s
                    .executeQuery("select * from eventos where nombre = '" + nombre + "' AND creador <> 'null'");

            rs.next();
            if (rs.getString(2) != null) {

                event.setCreador(rs.getString(2));
                event.setAforo(rs.getString(3));
                event.setDia(rs.getString(4));
                event.setHora(rs.getString(5));
                event.setNombre(rs.getString(6));
                event.setLugar(rs.getString(7));
                event.setCategoria(rs.getString(8));
                event.setDescripcion(rs.getString(9));
                event.setEmail(rs.getString(10));

                respuestaJson = respuestaJson  
                		+ "\"creador\":" + "\"" + event.getCreador() + "\"," 
                		+ "\"aforo\":"+ "" + event.getAforo() + "," 
                		+ "\"dia\":" + "\"" + event.getDia() + "\"," 
                		+ "\"hora\":"+ "\"" + event.getHora() + "\"," 
                		+ "\"nombre\":" + "\"" + event.getNombre() + "\","
                        + "\"lugar\":" + "\"" + event.getLugar() + "\"," 
                		+ "\"categoria\":" + "\""+ event.getCategoria() + "\"," 
                        + "\"descripcion\":" + "\"" + event.getDescripcion() + "\","
                        + "\"email\":" + "\"" + event.getEmail() + "\"";
            }

            conexion.close();
        } catch (ClassNotFoundException e2) {
            System.out.println("ERROR EN obtener info en EVENTOS");
            return "Error";
        } catch (Exception e) {
            System.out.println("ERROR EN obtener info en EVENTOS");
            return "Error";
        }
        try {// obtenecion del numero de asistentes a un determinado evento(lugar, nombre)
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

        java.sql.Statement s = conexion.createStatement();

        // Obtenemos el numero de asistentes a un determinado evento


        ResultSet rs_consulta = s.executeQuery("select nombre, count(*) from eventos where  (nombre = '" + nombre+ "' AND lugar = '"+lugar+"')");
        rs_consulta.next();

        respuestaJson= respuestaJson + ",\"participantes\":" + "" + rs_consulta.getInt(2) + "}";
        
        conexion.close();
        } catch (ClassNotFoundException e2) {
            System.out.println("ERROR EN contar NUM asistentes en OBTENER INFO");
            return "ERROR";
        } catch (Exception e) {
            System.out.println("ERROR EN contar NUM asistentes en OBTENER INFO");
            return "ERROR";
    }

        return respuestaJson;
    }
	
	
	public int obtenerAforoEvento(String nombre){
		
	      Connection conexion=null;
	      
	      int aforo=0;
	      
	      System.out.println("nombre: "+nombre);
	      
	      try{
	         Class.forName("com.mysql.jdbc.Driver");
	         conexion =  DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
	         
	         java.sql.Statement s = conexion.createStatement(); 
	         ResultSet rs_update  = s.executeQuery("select nombre, count(*) from eventos where nombre = '"+nombre+"'");

	        rs_update.next();
	        aforo = rs_update.getInt(2);
	        
	        conexion.close();
		    }
			catch(ClassNotFoundException e2){
				  System.out.println("ERROR EN contar NUM EVENTOS");
				  return -1;
			}       
		    catch(Exception e){
		    	 System.out.println("ERROR EN CONTAR NUM EVENTOS");
		    	 return -2;
		}     
		
		return aforo;
	}
	
	public int comprobarEvento_Creado(String dia, String hora, String email) {// FER

        Connection conexion = null;
        int check;

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

            java.sql.Statement s = conexion.createStatement();

            // Comprobamos si hay un evento creado con el mismo nombre
            
            System.out.println("select count(*) from eventos where creador = '" + email
                    + "' AND dia = '" + dia + "' AND hora = '" + hora + "'");
            ResultSet rs_update = s.executeQuery("select count(*) from eventos where creador = '" + email
                    + "' AND dia = '" + dia + "' AND hora = '" + hora + "'");
            
            
            rs_update.next();
            
            check = rs_update.getInt(1);
            

            conexion.close();
        } catch (ClassNotFoundException e2) {
            System.out.println("ERROR EN comprobar EVENTO");// error en la
                                                            // insercion de la
                                                            // BBDD
            return -1;// ERROR
        } catch (Exception e) {
            System.out.println("ERROR EN comprobar EVENTO");
            return -2;// ERROR
        }
        if (check >= 1)
            return 1;// no se puede crear el evento pq ya existe un evento con ese nombre
        else
            return 0;// OK, se puede crear evento
    }
	
	public int crearEvento_MySQL(String creador, String dia, String hora, String lugar, String nombre_evento,
            String interes, String descripcion, String email, String aforo) {// FER

        Connection conexion = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

            java.sql.Statement s = conexion.createStatement();
            
            String fecha = obtenerFecha(dia);
            
            int rs_update = s.executeUpdate(
                    "INSERT INTO eventos (creador,aforo,dia,hora,nombre,lugar,categoria,descripcion,email,fecha) VALUES ('"
                            + creador + "','" + aforo + "','" + dia + "','" + hora + "','" + nombre_evento + "','"
                            + lugar + "','" + interes + "','" + descripcion + "','" + email + "','"+fecha+  "')");

            conexion.close();
        } catch (ClassNotFoundException e2) {
            System.out.println("ERROR EN escribir EVENTOS");// error en la
                                                            // insercion de la
                                                            // BBDD
            return -1;// ERROR
        } catch (Exception e) {
            System.out.println("ERROR EN escribir EVENTOS");
            return -2;// ERROR
        }

        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

            java.sql.Statement s = conexion.createStatement();

            String consultarNombre= "select nombre from usuario where ( email = '" + email +"')";
            System.out.println(consultarNombre);
            ResultSet rs = s.executeQuery(consultarNombre);

            rs.next();
            Correo correo = new Correo();
            correo.mandarCorreoCrearEvento( creador,rs.getString(1),  nombre_evento,  dia,  hora, descripcion);
            conexion.close();
               
            }catch (ClassNotFoundException e2) {
                   System.out.println("ERROR obteniendo si esta REGISTRADO");
           } catch (Exception e) {
                   System.out.println("ERROR obteniendo si esta REGISTRADO");
            }
        
        return 0;// OK
    }
	
	public int borrarEvento(String dia, String hora,String nombre, String lugar) {//FER
	       Connection conexion = null;
	       String usuario="";
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");
	            java.sql.Statement s = conexion.createStatement();
	            
	            //OBTENER INTEGRANTES DEL GRUPO.
	            ResultSet rs_integrantes = s.executeQuery("select * from eventos where nombre = '"+ nombre + "' and dia = '" + dia + "' and hora='" + hora + "'");
	            
	            while(rs_integrantes.next())
	            {
	            	try{
	    	        	Class.forName("com.mysql.jdbc.Driver");
	    	           conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

	    	           java.sql.Statement ss = conexion.createStatement();

	    	           String consultarNombre= "select nombre from usuario where ( email = '" + rs_integrantes.getString(10) +"')";
	    	           System.out.println("rs_integrantes "+rs_integrantes.getString(10));
	    	           System.out.println(consultarNombre);
	    	           ResultSet rs = ss.executeQuery(consultarNombre);

	    	           rs.next();
	    	           usuario = rs.getString(1);
	    	           conexion.close();
	    	              
	    	           }catch (ClassNotFoundException e2) {
	    	                  System.out.println("ERROR obteniendo si esta REGISTRADO");
	    	          } catch (Exception e) {
	    	                  System.out.println("ERROR obteniendo si esta REGISTRADO");
	    	           }
	            	String email=rs_integrantes.getString(10);
	            	Correo correo = new Correo();
	            	correo.mandarCorreoBorrarEvento(email,usuario, nombre, dia, hora);
	            }
	            
	            String consulta="DELETE FROM eventos where ( dia = '"+ dia+"' AND hora = '"+ hora+"' AND nombre = '"+ nombre+"' AND lugar = '"+ lugar +"' )";
	            System.out.println(consulta);
	            int rs_update = s.executeUpdate("DELETE FROM eventos where ( dia ='"+ dia+"' AND hora ='"+ hora+"' AND nombre ='"+ nombre+"' AND lugar ='"+ lugar +"' )");

	            conexion.close();
	        } catch (ClassNotFoundException e2) {
	            System.out.println("ERROR borrando_EVENTO");
	            return -1;
	        } catch (Exception e) {
	            System.out.println("ERROR borrando_EVENTO");
	            return -2;
	        }
	        
	        return 0;
	}
	
	
	public String obtener_info_Evento(String dia, String hora,String nombre,String lugar,String email) {

		Connection conexion = null;
		String respuestaJson = "{";
		int resultado = 0;
		Evento event = new Evento();
		String unido = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

			java.sql.Statement s = conexion.createStatement();

			// Obtenemos el numero de asistentes a un determinado evento
			String consulta= "select * from eventos where ( dia = '" + dia + "' AND hora = '" + hora + "' AND nombre = '" + nombre + "' AND lugar = '" + lugar + "' AND creador <> 'null' )";
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);

			rs.next();
			if (rs.getString(2) != null) {
				
				try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");
                    java.sql.Statement ss1 = conexion.createStatement();
     
                    String consulta2="SELECT nombre, count(*) from eventos where nombre = '" + rs.getString(6)+ "' AND lugar = '" + rs.getString(7) + "' AND email = '"+email+"'";
                    System.out.println(consulta2);
 
                 ResultSet rs_unido = ss1.executeQuery(consulta2);                   
                 rs_unido.next();
                 
                 if (rs_unido.getInt(2)==1)
                     unido = "SI";
                 else
                     unido = "NO";
                 
                 System.out.println("sale");
                 System.out.println("unido "+unido);
                 System.out.println();
 
                    conexion.close();
                } 
             catch (ClassNotFoundException e2) {
                    System.out.println("ERROR obteniendo si esta REGISTRADO");
                } catch (Exception e) {
                    System.out.println("ERROR obteniendo si esta REGISTRADO");
                }
				
				event.setCreador(rs.getString(2));
				event.setAforo(rs.getString(3));
				event.setDia(rs.getString(4));
				event.setHora(rs.getString(5));
				event.setNombre(rs.getString(6));
				event.setLugar(rs.getString(7));
				event.setCategoria(rs.getString(8));
				event.setDescripcion(rs.getString(9));
				event.setEmail(rs.getString(10));
				
				System.out.println("\"creador\":" + "\"" + event.getCreador() + "\"," + "\"aforo\":"
						+ "" + event.getAforo() + "," + "\"dia\":" + "\"" + event.getDia() + "\"," + "\"hora\":"
						+ "\"" + event.getHora() + "\"," + "\"nombre\":" + "\"" + event.getNombre() + "\","
						+ "\"lugar\":" + "\"" + event.getLugar() + "\"," + "\"categoria\":" + "\""
						+ event.getCategoria() + "\"," + "\"descripcion\":" + "\"" + event.getDescripcion() + "\","
						+ "\"email\":" + "\"" + event.getEmail() + "\","+ "\"unido\":" + "\"" + unido + "\"");
				respuestaJson = respuestaJson  + "\"creador\":" + "\"" + event.getCreador() + "\"," + "\"aforo\":"
						+ "" + event.getAforo() + "," + "\"dia\":" + "\"" + event.getDia() + "\"," + "\"hora\":"
						+ "\"" + event.getHora() + "\"," + "\"nombre\":" + "\"" + event.getNombre() + "\","
						+ "\"lugar\":" + "\"" + event.getLugar() + "\"," + "\"categoria\":" + "\""
						+ event.getCategoria() + "\"," + "\"descripcion\":" + "\"" + event.getDescripcion() + "\","
						+ "\"email\":" + "\"" + event.getEmail() + "\","+ "\"unido\":" + "\"" + unido + "\"";
			}

			conexion.close();
		} catch (ClassNotFoundException e2) {
			System.out.println("ERROR EN obtener info en EVENTOS");
			return "Error";
		} catch (Exception e) {
			System.out.println("ERROR EN obtener info en EVENTOS");
			return "Error";
		}
		try {// obtenecion del numero de asistentes a un determinado evento(lugar, nombre)
		Class.forName("com.mysql.jdbc.Driver");
		conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

		java.sql.Statement s = conexion.createStatement();

		// Obtenemos el numero de asistentes a un determinado evento
		ResultSet rs_consulta = s.executeQuery("select nombre, count(*) from eventos where  (nombre = '" + nombre+ "' AND lugar = '"+lugar+"')");
		rs_consulta.next();

		respuestaJson= respuestaJson + ",\"participantes\":" + "" + rs_consulta.getInt(2) + "}";
		
		conexion.close();
		} catch (ClassNotFoundException e2) {
			System.out.println("ERROR EN contar NUM asistentes en OBTENER INFO");
			return "ERROR";
		} catch (Exception e) {
			System.out.println("ERROR EN contar NUM asistentes en OBTENER INFO");
			return "ERROR";
	}

		return respuestaJson;
	}
	
	
	
	
	public static String obtenerFecha(String fechaString)
	{
		String dia, mes, año;
		Fecha fecha = new Fecha();
		String fechaMysql="";
		
		dia = fecha.obtenerDia(fechaString);
		mes = fecha.obtenerMes(fechaString);
		año = fecha.obtenerAño(fechaString);
		
		fechaMysql = fechaMysql + año + "/" + mes + "/" + dia;
    	return fechaMysql;
	}
	
	public static String getFechaActual() {
	    Date hoy = new Date();
	    SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
	    System.out.println(formateador.format(hoy));
	    return formateador.format(hoy);
	}
	
	public static String getHoraActual() {
		Calendar now = Calendar.getInstance();
		String hora="";
		hora=hora+(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
		System.out.println("hora: "+hora);
		return hora;
	}
	
	public int desunirseEvento(String dia, String hora,String nombre, String lugar,String email) {//FER
	       Connection conexion = null;
	       String usuario="";

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");
	            java.sql.Statement s = conexion.createStatement();
	            
	            String consulta="DELETE FROM eventos where ( dia = '"+ dia+"' AND hora = '"+ hora+"' AND nombre = '"+ nombre+"' AND lugar = '"+ lugar +"' AND email = '"+email+"')";
	            System.out.println(consulta);
	            int rs_update = s.executeUpdate(consulta);

	            conexion.close();
	        } catch (ClassNotFoundException e2) {
	            System.out.println("ERROR borrando_EVENTO");
	            return -1;
	        } catch (Exception e) {
	            System.out.println("ERROR borrando_EVENTO");
	            return -2;
	        }
	        
	        try{
	        	Class.forName("com.mysql.jdbc.Driver");
	           conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

	           java.sql.Statement s = conexion.createStatement();

	           String consultarNombre= "select nombre from usuario where ( email = '" + email +"')";
	           System.out.println(consultarNombre);
	           ResultSet rs = s.executeQuery(consultarNombre);

	           rs.next();
	           usuario = rs.getString(1);
	           conexion.close();
	              
	           }catch (ClassNotFoundException e2) {
	                  System.out.println("ERROR obteniendo si esta REGISTRADO");
	          } catch (Exception e) {
	                  System.out.println("ERROR obteniendo si esta REGISTRADO");
	           }
	          
	        Correo correo = new Correo();
	        correo.mandarCorreoDesunirte( email, usuario,nombre,dia,hora);
	        return 0;
	}
	
	
	
	public String obtenerMisEventosMySQL(String email){ 
	       
	      Connection conexion = null;
	      Evento event = new Evento ();
	      String dia, mes, año, horas, minutos;
	      int num_asistentes=0;
	      String creador="";
	      String respuestaJson="[";
	      Fecha fecha = new Fecha ();

	      try{
	    	  
	      }
	      catch (Exception e) {
              System.out.println("ERROR hoy");
              return "ERROR hoy";
          }
	      try{
	         Class.forName("com.mysql.jdbc.Driver"); 
	         conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
	                    
	         java.sql.Statement s = conexion.createStatement();  
	         
	         String hoy = getFechaActual();
	         
	         String hora = getHoraActual();
	         //obtenemos la información del usuario de la base de datos.
	         
	         System.out.println("select * from eventos where eventos.email='"+ email+"' AND eventos.fecha >='"+hoy+ "' ORDER BY fecha"); 
	         
	         ResultSet rs = s.executeQuery ("select * from eventos where eventos.email='"+ email+"' AND eventos.fecha >='"+hoy+ "' ORDER BY fecha"); 
	         
	         
	         while(rs.next()){//si hay contenido en el rs...    
	             event.setCreador(rs.getString(2));
	             event.setAforo(rs.getString(3));
	             event.setDia(rs.getString(4));//Aclaración: setDia es setFecha.
	             event.setHora(rs.getString(5));
	             event.setNombre(rs.getString(6));
	             event.setLugar(rs.getString(7));
	             event.setCategoria(rs.getString(8));
	             event.setDescripcion(rs.getString(9));
	             event.setEmail(rs.getString(10));
	             
	             
	             dia=fecha.obtenerDia(event.getDia());//getDia es getFecha.
	             mes=fecha.obtenerMes(event.getDia());
	             año=fecha.obtenerAño(event.getDia());
	             horas=fecha.obtenerHoras(event.getHora());
	             minutos=fecha.obtenerMinutos(event.getHora());
	             
	             try {//obtener el de num asistentes
	                   Class.forName("com.mysql.jdbc.Driver");
	                   conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

	                   java.sql.Statement ss = conexion.createStatement();

	                   // Obtenemos el numero de asistentes a un determinado evento
	                   ResultSet rs_integrantes = ss.executeQuery("select nombre, count(*) from eventos where (nombre = '" + event.getNombre()+ "' AND lugar = '" + event.getLugar() + "')");
	                   rs_integrantes.next();
	                   num_asistentes = rs_integrantes.getInt(2);
	                   conexion.close();
	               } catch (ClassNotFoundException e2) {
	                   System.out.println("ERROR 1");
	                   return "ERROR";
	               } catch (Exception e) {
	                   System.out.println("ERROR 2");
	                   return "ERROR";
	               }
	             try{//obtener creador.
	            	 Class.forName("com.mysql.jdbc.Driver");
	                 conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

	                 java.sql.Statement sss = conexion.createStatement();

	                 
	                 ResultSet rs_creador = sss.executeQuery("select * from eventos where eventos.creador!= 'NULL' AND eventos.nombre= '" + event.getNombre()+ "' AND lugar = '" + event.getLugar() + "'");
	                 rs_creador.next();
	                 
	                 creador = rs_creador.getString(2);
	                 conexion.close();
	            	 
	            	 
	             }catch (Exception e3) {
	                   System.out.println("ERROR 3");
	                   return "ERROR";
	             }
	             
	       
	             respuestaJson = respuestaJson + "{"
	                     + "\"creador\":"+"\""+creador+"\","
	                     + "\"aforo\":"+"\""+event.getAforo()+"\","
	                     + "\"dia\":"+"\""+event.getDia()+"\","
	                     + "\"hora\":"+"\""+event.getHora()+"\","
	                     + "\"nombre\":"+"\""+event.getNombre()+"\","
	                     + "\"lugar\":"+"\""+event.getLugar()+"\","
	                     + "\"categoria\":"+"\""+event.getCategoria()+"\","
	                     + "\"descripcion\":"+"\""+event.getDescripcion()+"\","
	                     + "\"email\":"+"\""+event.getEmail()+"\","
	                     + "\"integrantes\":"+"\""+num_asistentes+"\"}," ;   
	             
	         }

	         //Para quitar la coma del final.
	         int longitud = respuestaJson.length();
	         respuestaJson=respuestaJson.substring(0,longitud-1);
	         
	         if(longitud<5){
	             return "[]";
	         }
	         else  
	             respuestaJson= respuestaJson+"]";                    
	          conexion.close();
	      }
	      
	      catch(Exception e){
	             System.out.println("ERROR");
	      }
	     return respuestaJson;
	   }
	
	public String login(String email, String password){ 
		
		Connection conexion = null;
        Usuario user = new Usuario();
       
        String contraseña;
        String respuestaJson="";
        
        try{
           Class.forName("com.mysql.jdbc.Driver"); 
           conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
                      
           java.sql.Statement s = conexion.createStatement();  
           
           //obtenemos la información del usuario de la base de datos.
           ResultSet rs = s.executeQuery ("select * from usuario where usuario.email='"+ email+"'"); 
           
           
           if(rs.next())//Si el email está registrado en la BBDD.
           {   
        	   user.setEmail(rs.getString(2));
               user.setPassword(rs.getString(3));
           }
           else
           {
        	   return "{\"content\":\"Email no encontrado. Regístrese para acceder a la aplicación\"}";
           }
           
           //Si el usuario está registrado, comprobamos la contraseña.
           
           contraseña=user.getPassword();
           
           if(contraseña.equals(password))
        	   respuestaJson = "{\"OK\":\"OK\"}";
           else
        	   respuestaJson = "{\"KO\":\"KO\"}";
                 
                                  
            conexion.close();
        }
        
        catch(Exception e){
               System.out.println("ERROR");
        }
       return respuestaJson;
	}
	
	public String buscarEventos(String categoria, String lugar){//NACHO
	       
	       Connection conexion = null;
	       Evento event = new Evento ();
	       String dia, mes, año, horas, minutos;
	       String respuestaJson="[";
	       Fecha fecha = new Fecha ();

	       try{
	          Class.forName("com.mysql.jdbc.Driver"); 
	          conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
	                     
	          java.sql.Statement s = conexion.createStatement();  
	          
	          //obtenemos la información del usuario de la base de datos.
	          ResultSet rs = s.executeQuery ("select * from eventos where eventos.categoria='"+ categoria+"' AND eventos.lugar='"+lugar+"' AND eventos.creador!='null'"); 
	          
	          while(rs.next()){//si hay contenido en el rs...    
	              event.setCreador(rs.getString(2));
	              event.setAforo(rs.getString(3));
	              event.setDia(rs.getString(4));//Aclaración: setDia es setFecha.
	              event.setHora(rs.getString(5));
	              event.setNombre(rs.getString(6));
	              event.setLugar(rs.getString(7));
	              event.setCategoria(rs.getString(8));
	              event.setDescripcion(rs.getString(9));
	              event.setEmail(rs.getString(10));
	              
	              dia=fecha.obtenerDia(event.getDia());//getDia es getFecha.
	              mes=fecha.obtenerMes(event.getDia());
	              año=fecha.obtenerAño(event.getDia());
	              horas=fecha.obtenerHoras(event.getHora());
	              minutos=fecha.obtenerMinutos(event.getHora());
	              
	              respuestaJson = respuestaJson + "{"
	                 + "\"title\":"+"\""+event.getNombre()+"\","
	                 + "\"year\":"+"\""+año+"\","
	                 + "\"creador\":"+"\""+rs.getString(2)+"\","
	                 + "\"month\":"+"\""+mes+"\","
	                 + "\"day\":"+"\""+dia+"\","
	                 + "\"hour\":"+"\""+horas+"\","
	                 + "\"categoria\":"+"\""+categoria+"\","
	                 + "\"minute\":"+"\""+minutos+"\"},";         
	          }

	          //Para quitar la coma del final.
	          int longitud = respuestaJson.length();
	          respuestaJson=respuestaJson.substring(0,longitud-1);
	          
	          if(longitud<5){
	              return "{}";
	          }
	          else
	              respuestaJson= respuestaJson+"]";
	                                 
	           conexion.close();
	       }
	       
	       catch(Exception e){
	              System.out.println("ERROR en buscar Eventos");
	       }
	      return respuestaJson;
	    }
	
	
public String busquedaAvanzada(String categoria, String lugar,String nombre){  //NACHO
        
        Connection conexion = null;
        Evento event = new Evento ();
        String dia, mes, año, horas, minutos;
        String respuestaJson="[";
        Fecha fecha = new Fecha ();

        try{
           Class.forName("com.mysql.jdbc.Driver"); 
           conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme","root","1234");
                      
           java.sql.Statement s = conexion.createStatement();  
           
           //obtenemos la información del usuario de la base de datos.
           
           
           ResultSet rs = s.executeQuery ("select * from eventos where nombre like '%"+nombre.toLowerCase()+"%' AND " +" eventos.categoria='"+ categoria.toLowerCase()+"' AND eventos.lugar='"+lugar.toLowerCase()+"' AND eventos.creador!='null'"); 
           
           while(rs.next()){//si hay contenido en el rs...    
               event.setCreador(rs.getString(2));
               event.setAforo(rs.getString(3));
               event.setDia(rs.getString(4));//Aclaración: setDia es setFecha.
               event.setHora(rs.getString(5));
               event.setNombre(rs.getString(6));
               event.setLugar(rs.getString(7));
               event.setCategoria(rs.getString(8));
               event.setDescripcion(rs.getString(9));
               event.setEmail(rs.getString(10));
               
               dia=fecha.obtenerDia(event.getDia());//getDia es getFecha.
               mes=fecha.obtenerMes(event.getDia());
               año=fecha.obtenerAño(event.getDia());
               horas=fecha.obtenerHoras(event.getHora());
               minutos=fecha.obtenerMinutos(event.getHora());
               
               respuestaJson = respuestaJson + "{"
                    + "\"title\":"+"\""+event.getNombre()+"\","
                  + "\"year\":"+"\""+año+"\","
                  + "\"month\":"+"\""+mes+"\","
                  + "\"day\":"+"\""+dia+"\","
                  + "\"hour\":"+"\""+horas+"\","
                  + "\"categoria\":"+"\""+categoria+"\","
                  + "\"minute\":"+"\""+minutos+"\"},";         
           }
  
           //Para quitar la coma del final.
           int longitud = respuestaJson.length();
           respuestaJson=respuestaJson.substring(0,longitud-1);
           
           if(longitud<5)
           {
               return "{}";
           }
           else
               respuestaJson= respuestaJson+"]";
                                  
            conexion.close();
        }
        
        catch(Exception e){
               System.out.println("ERROR");
        }
       return respuestaJson;
    }
	
	
	public int unirEnvento_MySQL(String aforo, String dia, String hora, String nombre, String lugar, String categoria,
	        String descripcion, String email) {

	        Connection conexion = null;
	        String usuario="";

	        int num_asistentes = 0;
	        int num_aforo = 0;
	        int comprobacion_ya_inscrito = 0;

	       
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

	            java.sql.Statement s = conexion.createStatement();

	            // Obtenemos el numero de asistentes a un determinado evento
	            ResultSet rs_update = s.executeQuery("select nombre, count(*) from eventos where (nombre = '" + nombre+ "' AND lugar = '" + lugar + "' AND dia = '"+ dia + "' AND hora ='"+hora + "')");
	            rs_update.next();
	            num_asistentes = rs_update.getInt(2);
	            conexion.close();
	        } catch (ClassNotFoundException e2) {
	            System.out.println("ERROR EN contar NUM asistentes");
	            return -2;
	        } catch (Exception e) {
	            System.out.println("ERROR EN contar NUM asistentes");
	            return -2;
	        }

	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

	            java.sql.Statement s = conexion.createStatement();

	            // Obtenemos el campo aforo
	            ResultSet rs_aforo = s.executeQuery("select aforo from eventos where (nombre = '" + nombre
	                    + "' AND lugar = '" + lugar + "'AND creador <> 'NULL' AND hora='"+hora+"' AND dia='"+dia+"')");
	            rs_aforo.next();
	            num_aforo = rs_aforo.getInt(1);
	            conexion.close();
	        } catch (ClassNotFoundException e2) {
	            System.out.println("ERROR EN obtener AFORO");
	            return -3;
	        } catch (Exception e) {
	            System.out.println("ERROR EN obtener AFORO");
	            return -3;
	        }
	        
	        if (num_asistentes < num_aforo) {
	        	
	        	String fecha="";
	        	fecha=obtenerFecha(dia);
	        	
	            try {
	                Class.forName("com.mysql.jdbc.Driver");
	                conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");
	                java.sql.Statement s = conexion.createStatement();

	                System.out.println("INSERT INTO eventos (AFORO,DIA,HORA,NOMBRE,LUGAR,CATEGORIA,DESCRIPCION,EMAIL,FECHA) VALUES ('"
	                                + aforo + "','" + dia + "','" + hora + "','" + nombre + "','" + lugar + "','"
	                                + categoria + "','" + descripcion + "','" + email + "'" + fecha +"')");
	                int rs_update = s.executeUpdate(
	                        "INSERT INTO eventos (AFORO,DIA,HORA,NOMBRE,LUGAR,CATEGORIA,DESCRIPCION,EMAIL,FECHA) VALUES ('"
	                                + aforo + "','" + dia + "','" + hora + "','" + nombre + "','" + lugar + "','"
	                                + categoria + "','" + descripcion + "','" + email + "','" + fecha +"')");

	                conexion.close();
	            } catch (ClassNotFoundException e2) {
	                System.out.println("ERROR escribir para unirse a EVENTO");
	                return -4;
	            } catch (Exception e) {
	                System.out.println("ERROR escribir para unirse a EVENTO");
	                return -4;
	            }
		        try{
		        	Class.forName("com.mysql.jdbc.Driver");
		           conexion = DriverManager.getConnection("jdbc:mysql://localhost/funme", "root", "1234");

		           java.sql.Statement s = conexion.createStatement();

		           String consultarNombre= "select nombre from usuario where ( email = '" + email +"')";
		           System.out.println(consultarNombre);
		           ResultSet rs = s.executeQuery(consultarNombre);

		           rs.next();
		           usuario = rs.getString(1);
		           conexion.close();
		              
		           }catch (ClassNotFoundException e2) {
		                  System.out.println("ERROR obteniendo si esta REGISTRADO");
		          } catch (Exception e) {
		                  System.out.println("ERROR obteniendo si esta REGISTRADO");
		           }
	            Correo correo = new Correo();
	            correo.mandarCorreoUnirte(email,usuario,nombre, fecha, hora);
	            return 0;
	        } else
	            return -1;
	    }    
}

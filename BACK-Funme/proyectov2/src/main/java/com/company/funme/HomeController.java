package com.company.funme;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.company.funme.Usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	

	@RequestMapping(value="/guardarRegistro",produces = MediaType.APPLICATION_JSON_VALUE)//FER
    public @ResponseBody ResponseEntity<String> guardarRegistro(HttpServletResponse response,@RequestParam(value="email",required=false) String email, @RequestParam(value="password",required=false) String password, @RequestParam(value="nombre",required=false) String nombre, @RequestParam(value="apellidos",required=false) String apellidos, @RequestParam(value="fecha",required=false) String fecha, @RequestParam(value="genero",required=false) String genero) {
       int check = 0;
       MySQL my= new MySQL();
       check = my.escribirDatosMySQL_REGISTRO(email.toLowerCase(),password.toLowerCase(),nombre.toLowerCase(),apellidos.toLowerCase(),fecha,genero.toLowerCase());
    
       response.setHeader("Access-Control-Allow-Origin", "*");
       
       if (check==0)
           return new ResponseEntity<String>("{\"content\":\"Registro correcto!\"}", HttpStatus.OK);
       else 
           return new ResponseEntity<String>("{\"content\":\"Email ya registrado!\"}", HttpStatus.NOT_IMPLEMENTED);
    }
	
	@RequestMapping(value="/crearEvento")//FER
    public @ResponseBody ResponseEntity<String> formularioPeticiones(HttpServletResponse response,@RequestParam(value="aforo",required=false) String aforo, @RequestParam(value="dia",required=false) String dia,@RequestParam(value="hora",required=false) String hora,@RequestParam(value="lugar",required=false) String lugar,@RequestParam(value="nombre",required=false) String nombre,@RequestParam(value="interes",required=false) String interes,@RequestParam(value="descripcion",required=false) String descripcion,@RequestParam(value="email",required=false) String email) {
               
         //email = "456789@gmail.com";
         MySQL my= new MySQL();
         
         int comprobacion = my.comprobarEvento_Creado(dia.toLowerCase(),hora.toLowerCase(),email.toLowerCase());//comprobacion de que no se haya creado el evento anteriormente

         if(comprobacion<0)
                return new ResponseEntity<String>("{\"content\":\"Error en consulta\"}", HttpStatus.NOT_IMPLEMENTED);
         else{
             if(comprobacion==1)
                 return new ResponseEntity<String>("{\"content\":\"Ya has creado otro evento a esa misma hora\"}", HttpStatus.SERVICE_UNAVAILABLE);
             else{//nos deja crear el evento
                 int check = 0;//
                 
                 check = my.crearEvento_MySQL(email.toLowerCase(),dia.toLowerCase(),hora.toLowerCase(),lugar.toLowerCase(),nombre.toLowerCase(),interes.toLowerCase(),descripcion.toLowerCase(),email.toLowerCase(),aforo.toLowerCase());
                  
                 response.setHeader("Access-Control-Allow-Origin", "*");
                 
                 if (check==0)
                 {
//                	 Email correo = new Email();
//                 	 correo.mandarCorreoCrearEvento(email, nombre, dia, hora);
                	 return new ResponseEntity<String>("{\"content\":\"Evento creado!\"}", HttpStatus.OK);
                 }
                 	
                 else 
                     return new ResponseEntity<String>("{\"content\":\"Error al crear el evento\"}", HttpStatus.NOT_IMPLEMENTED);     
             }
         }
    }
	

	@RequestMapping(value="/unirseAEvento") //FER
    public @ResponseBody ResponseEntity<String> unirseaEvento(HttpServletResponse response,@RequestParam(value="email",required=false) String email,@RequestParam(value="descripcion",required=false) String descripcion,@RequestParam(value="categoria",required=false) String categoria,@RequestParam(value="hora",required=false) String hora,@RequestParam(value="dia",required=false) String dia,@RequestParam(value="aforo",required=false) String aforo, @RequestParam(value="nombre",required=false) String nombre,@RequestParam(value="lugar",required=false) String lugar) {
              
          MySQL my= new MySQL();
          int dev = my.unirEnvento_MySQL(aforo.toLowerCase(),dia.toLowerCase(),hora.toLowerCase(),nombre.toLowerCase(),lugar.toLowerCase(),categoria.toLowerCase(),descripcion.toLowerCase(),email.toLowerCase());

          System.out.println("dev "+dev);
            
          response.setHeader("Access-Control-Allow-Origin", "*");
            if (dev==0)
                return new ResponseEntity<String>("{\"content\":\"Unido sin problemas \"}", HttpStatus.OK);
            else 
                if(dev==-5)
                    return new ResponseEntity<String>("{\"content\":\"ya inscrito\"}", HttpStatus.NOT_EXTENDED);
                else
                    return new ResponseEntity<String>("{\"content\":\"Error en unirse\"}", HttpStatus.NOT_IMPLEMENTED);
    }
	
	@RequestMapping(value="/verPerfil")
	public  @ResponseBody ResponseEntity<String> verPerfil(HttpServletResponse response,
	           @RequestParam(value="email",required=false) String email) 
	{
	            Usuario u =new Usuario();
	            MySQL my = new MySQL();
	            u = my.verPerfil(email.toLowerCase());
		        response.setHeader("Access-Control-Allow-Origin", "*");
	            if(u.getEmail()!=null)
	            {
	            	return new ResponseEntity<String>("{\"email\":"+"\""+u.getEmail()+"\","+"\"nombre\":"+"\""+u.getNombre()+"\","+"\"apellidos\":"+"\""+u.getApellidos()+"\","+"\"fechaNacimiento\":"+"\""+u.getFecha()+"\","+"\"genero\":"+"\""+u.getGenero()+"\"}", HttpStatus.OK);
	            }
	            else
		            return new ResponseEntity<String>("{\"No hay usuarios registrados con ese email\"}", HttpStatus.NOT_IMPLEMENTED);
	}
	
	@RequestMapping(value="/informacionEvento") //FER
    public @ResponseBody ResponseEntity<String> infoEvento(HttpServletResponse response, @RequestParam(value="dia",required=false) String dia,@RequestParam(value="hora",required=false) String hora,@RequestParam(value="nombre",required=false) String nombre, @RequestParam(value="lugar",required=false) String lugar,@RequestParam(value="email",required=false) String email) {
      
        MySQL my= new MySQL();
        String respuesta = my.obtener_info_Evento(dia.toLowerCase(),hora.toLowerCase(),nombre.toLowerCase(),lugar.toLowerCase(),email.toLowerCase());
        response.setHeader("Access-Control-Allow-Origin", "*");

        System.out.println("1111111");
        if(respuesta != "Error"){
            return new ResponseEntity<String>(respuesta, HttpStatus.OK);}
        else
        {        
            return new ResponseEntity<String>("{\"content\":\"Error\"}", HttpStatus.NOT_IMPLEMENTED);
        }
    }
	
	@RequestMapping(value="/desunirseEvento")
	   public ResponseEntity<String> desunirseEvento(
	          HttpServletResponse response,
	          @RequestParam(value="hora",required=false) String hora,
	          @RequestParam(value="dia",required=false) String dia, 
	          @RequestParam(value="nombre",required=false) String nombre, 
	          @RequestParam(value="lugar",required=false) String lugar,
	          @RequestParam(value="email",required=false) String email){
	          MySQL my= new MySQL();
	          
	          response.setHeader("Access-Control-Allow-Origin", "*");  
	          int resp=my.desunirseEvento(dia.toLowerCase(), hora.toLowerCase(),nombre.toLowerCase(),lugar.toLowerCase(),email.toLowerCase());
	          if (resp==0)
	              return new ResponseEntity<String>("{\"content\":\"Desunido correctamente\"}", HttpStatus.OK);
	          else
	              return new ResponseEntity<String>("{\"content\":\"NO se ha podido desunir\"}", HttpStatus.NOT_IMPLEMENTED);
	 }
	
	
	@RequestMapping(value="/borrarEvento")
	   public ResponseEntity<String> borrarEvento(
	          HttpServletResponse response,
	          @RequestParam(value="hora",required=false) String hora,
	          @RequestParam(value="dia",required=false) String dia, 
	          @RequestParam(value="nombre",required=false) String nombre, 
	          @RequestParam(value="lugar",required=false) String lugar){
	          MySQL my= new MySQL();
	          
	          response.setHeader("Access-Control-Allow-Origin", "*");  
	          int resp=my.borrarEvento(dia.toLowerCase(), hora.toLowerCase(),nombre.toLowerCase(),lugar.toLowerCase());
	          if (resp==0)
	              return new ResponseEntity<String>("{\"content\": \"Borrado correctamente\"}", HttpStatus.OK);
	          else
	              return new ResponseEntity<String>("{\"content\": \"NO se ha podido borrar\"}", HttpStatus.NOT_IMPLEMENTED);

	 }
	
	@RequestMapping(value="/misEventos")
	public @ResponseBody ResponseEntity<String> misEventos(HttpServletResponse response,@RequestParam(value="email",required=false) String email) {
        
		  String respuestaJson;
	      MySQL my= new MySQL();
	      respuestaJson = my.obtenerMisEventosMySQL(email.toLowerCase());
	      
	      response.setHeader("Access-Control-Allow-Origin", "*");
	      return new ResponseEntity<String>(respuestaJson, HttpStatus.OK);
	}
	
	@RequestMapping(value="/buscarEventos")
	   public ResponseEntity<String> buscarEventos(
	          HttpServletResponse response,
	          @RequestParam(value="categoria",required=false) String categoria, 
	          @RequestParam(value="lugar",required=false) String lugar){
	        
	          MySQL my= new MySQL();
	          
	          response.setHeader("Access-Control-Allow-Origin", "*");  
	          
	          String respuestaJson=my.buscarEventos(categoria.toLowerCase(),lugar.toLowerCase());
	          return new ResponseEntity<String>(respuestaJson, HttpStatus.OK);
	 }
	
	@RequestMapping(value="/busquedaAvanzada")
	   public ResponseEntity<String> busquedaAvanzada(
			  HttpServletResponse response,
	          @RequestParam(value="categoria",required=false) String categoria, 
	          @RequestParam(value="nombre",required=false) String nombre, 
	          @RequestParam(value="lugar",required=false) String lugar){
	          MySQL my= new MySQL();
	          
		      response.setHeader("Access-Control-Allow-Origin", "*");  
	          String respuestaJson=my.busquedaAvanzada(categoria.toLowerCase(),lugar.toLowerCase(),nombre.toLowerCase());
	  	      return new ResponseEntity<String>(respuestaJson, HttpStatus.OK);
	 }
	
	
	@RequestMapping(value="/login")
	public ResponseEntity<String> login(
			  HttpServletResponse response,
	          @RequestParam(value="email",required=false) String email, 
	          @RequestParam(value="password",required=false) String password){
	          MySQL my= new MySQL();
	          
		      response.setHeader("Access-Control-Allow-Origin", "*");  
	          String respuestaJson=my.login(email.toLowerCase(),password.toLowerCase());
	          if(respuestaJson.compareTo("{\"OK\":\"OK\"}")==0)
		    	  return new ResponseEntity<String>(respuestaJson, HttpStatus.OK);
		      else
		    	  return new ResponseEntity<String>(respuestaJson, HttpStatus.NOT_IMPLEMENTED);
	  	      
	}
	
	
}

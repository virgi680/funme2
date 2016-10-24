package com.mycompany.funme.controller;

import com.mycompany.funme.json.JsonTransformer;
import com.mycompany.funme.modelo.Usuario;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UsuarioController {
    
    @Autowired
    private JsonTransformer jsonTransformer;
    
    @RequestMapping(value = {"/Usuario"})
    public void prueba(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) throws IOException {
        Usuario usuario=new Usuario("julio.s@bbva.com", "1234","Julio", "Solano Velasco","05/10/1993","Hombre");
        String jsonUsuario=jsonTransformer.toJson(usuario);
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.getWriter().println(jsonUsuario);
    }
}

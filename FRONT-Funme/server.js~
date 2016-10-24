

    // importar
    var express = require('express');
     
    // instanciar
    var app = express();
     
    // ruteo

    
   app.configure(function() {  
    // Localización de los ficheros estaticos
    app.use(express.static(__dirname + '/src'));
    // Muestra un log de todos los request en la consola        
    app.use(express.logger('dev')); 
    // Permite cambiar el HTML con el método POST                   
    app.use(express.bodyParser());
    // Simula DELETE y PUT                      
    app.use(express.methodOverride());                  
});
     
    // escuchar
    app.listen(9000);
     
    console.log("Servidor Express escuchando en modo %s", app.settings.env);

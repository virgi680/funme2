package com.mycompany.funme.modelo;

public class Usuario {

    private String email,password,nombre,apellidos,fecha,genero;

    
    public Usuario(String email, String password, String nombre, String apellidos,String fecha,String genero) {
    	this.email = email;
    	this.password = password;
    	this.nombre = nombre;
    	this.apellidos = apellidos;
    	this.fecha = fecha;
    	this.genero = genero;
        
    }
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

package com.company.funme;

public class Usuario {
    
    
    private String email,password,rpassword,nombre,apellidos,fecha,genero;
    
    
    public void setEmail(String n){
        email=n;
    }
    public void setPassword(String n){
        password=n;
    }
    public void setrPassword(String n){
        rpassword=n;
    }
    public void setNombre(String n){
        nombre=n;
    }
    public void setApellidos(String n){
        apellidos=n;
    }
    public void setFecha(String n){
        fecha=n;
    }
    public void setGenero(String n){
        genero=n;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
    public String getrPassword(){
        return rpassword;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApellidos(){
        return apellidos;
    }
    public String getFecha(){
        return fecha;
    }
    public String getGenero(){
        return genero;
    }
}

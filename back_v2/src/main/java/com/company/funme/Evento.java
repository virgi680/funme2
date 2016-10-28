package com.company.funme;

public class Evento {
	   private String creador,aforo,dia, hora, nombre, lugar,categoria,descripcion,email;
	   
	   public void setCreador(String n){
	       creador=n;
	   }
	   public void setAforo(String n){
	       aforo=n;
	   }
	      
	   public void setDia(String n){
	       dia=n;
	   }
	   public void setHora(String n){
	       hora=n;
	   }
	   public void setNombre(String n){
	       nombre=n;
	   }
	   public void setLugar(String n){
	       lugar=n;
	   }
	   public void setCategoria(String n){
	       categoria=n;
	   }
	   public void setDescripcion(String n){
	       descripcion=n;
	   }
	   public void setEmail(String n){
	       email=n;
	   }
	   
	   public String getCreador() {
	        return creador;
	    }
	    public String getAforo() {
	        return aforo;
	    }
	    public String getCategoria() {
	        return categoria;
	    }
	    public String getDescripcion() {
	        return descripcion;
	    }
	    public String getEmail() {
	        return email;
	    }
	    public String getDia(){
	       return dia;
	   }
	   public String getHora(){
	       return hora;
	   }
	   public String getNombre(){
	       return nombre;
	   }
	   public String getLugar(){
	       return lugar;
	   }
	}
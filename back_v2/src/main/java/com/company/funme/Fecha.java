package com.company.funme;

public class Fecha {

	String dia,mes,año,horas,minutos;

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getAño() {
		return año;
	}

	public void setAño(String año) {
		this.año = año;
	}

	public String getHoras() {
		return horas;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public String getMinutos() {
		return minutos;
	}

	public void setMinutos(String minutos) {
		this.minutos = minutos;
	}

	public String obtenerDia(String fecha)
	{
		String dia="";
		int i=0;
		while(fecha.charAt(i)!='/')
		{
			dia=dia+fecha.charAt(i);
			i++;
		}
		return dia;
	}
	
	public String obtenerMes(String fecha)//16/03/2016
	{
		String mes="";
		int i=0;
		while(fecha.charAt(i)!='/')//Pasamos el primer '/'.
			i++;
		i++;
		while(fecha.charAt(i)!='/')
		{
			mes=mes+fecha.charAt(i);
			i++;
		}
		return mes;
	}
	
	public String obtenerAño(String fecha)//16/03/2016
	{
		String año="";
		int i=0;
		while(fecha.charAt(i)!='/')//Pasamos el primer '/'.
			i++;
		i++;
		while(fecha.charAt(i)!='/')//Pasamos el segundo '/'.
			i++;
		i++;
		while(i<fecha.length())
		{
			año=año+fecha.charAt(i);
			i++;
		}
		return año;
	}
	
	public String obtenerHoras(String hora)
	{
		String horas="";
		int i=0;
		while(hora.charAt(i)!=':')
		{
			horas=horas+hora.charAt(i);
			i++;
		}
		return horas;
	}
	public String obtenerMinutos(String hora)
	{
		String minutos="";
		int i=0;
		while(hora.charAt(i)!=':')
			i++;
		i++;
		while(i<hora.length())
		{
			minutos= minutos+hora.charAt(i);
			i++;
		}
		return minutos;
	}
}

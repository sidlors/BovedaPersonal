package mx.gob.imss.cit.bp.services.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public final class DateUtil {
	/**
	 * Formato de fecha.
	 */
	public static final String FECHA_FORMATO = "dd/MM/yyyy";
	/**
	 * Constante de formato TIMESTAMP
	 */
	public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

	/**
	 * Contructor privado
	 */
	private DateUtil(){
		
	}	
	
	/**
	 * Metodo realiza parse de un String del formato dd/MM/yyyy a Date
	 * @param dateS	Objeto que contiene el valor de la fecha en formato dd/MM/yyyy
	 * @return Date retorna la fecha
	 * @throws ParseException
	 */			
	public static Date parseStringToDate(String dateS) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_FORMATO);
		return sdf.parse(dateS);
	}	
	
	/**
	 * Metodo realiza formato dd/MM/yyyy a un objeto Date
	 * @param date	Objeto al que se le aplicara el formato dd/MM/yyyy
	 * @return sdf.format(date) retorna la fecha con formato
	 * @throws ParseException
	 */	
	public static String formatDateToString(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(FECHA_FORMATO);		
		return sdf.format(date);
	}

	/**
	 * Metodo que recibe como entrada una fecha(Date) al cual se le aplica un cast a Calendar
	 * @param date	Objeto de tipo Date a transformar en Calendar
	 * @return Calendar
	 * @throws ParseException
	 */	
	public static Calendar parseDateToCalendar(Date date){		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * Metodo que recibe como entrada una fecha tipo String con formato dd/MM/yyyy al cual se le aplica un cast a Calendar
	 * @param date	Objeto de tipo String con formato dd/MM/yyyy a transformar en Calendar
	 * @return Calendar
	 * @throws ParseException
	 */			
	public static Calendar parseStringToCalendar(String date) throws ParseException{		
		Calendar cal = Calendar.getInstance();		
		cal.setTime(parseStringToDate(date));
		return cal;
	}	
	/**
	 * Metodo que tranforma de  GregorianCalendar a String con el formato dd/MM/yyyy.
	 * @param gregorianCalendar Objeto que se va parsear.
	 * @return date en formato cadena y en cadena.
	 * @throws ParseException
	 */
	public static String parseGregorianCalendartoString(GregorianCalendar gregorianCalendar) throws ParseException{		
		return formatDateToString(gregorianCalendar.getTime());
	}
}
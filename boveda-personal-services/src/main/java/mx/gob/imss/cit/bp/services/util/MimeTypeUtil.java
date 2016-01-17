package mx.gob.imss.cit.bp.services.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

/**
 * Clase de utileria que valida que el si un valor si es un elemento ContentTypeDocument Enum.
 * @author 
 *
 */

public class MimeTypeUtil {
	
	private static final Map<String, String> map =  new HashMap<String, String>();
	
	private static MimeTypeUtil mimeType = null;
	
	private MimeTypeUtil(){
		map.put("txt", "text/plain");
		map.put("xml", "application/xml");
		map.put("html", "text/html");
		map.put("jpeg", "image/jpeg");
		map.put("bmp", "image/bmp");
		map.put("csv", "text/csv");
		map.put("gif", "image/gif");
		map.put("jp2", "image/jp2");
		map.put("doc", "application/msword");
		map.put("png", "image/png");
		map.put("css", "text/css");
		map.put("tiff", "image/tiff");
		map.put("pdf", "application/pdf");
		map.put("jpg", "image/jpeg");
	}
	
	public static Map getMimeTypeMap(){
		if(mimeType == null){
			mimeType = new MimeTypeUtil();
		}
		return MimeTypeUtil.map;
	}	
	
}
package mx.gob.imss.cit.bp.services.util;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;

import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

public class ValidationHelper {

	private static final String TRAMITE_ORG = "org";

	private static final String TRAMITE_ZONA = "zona";

	private static final String TRAMITE = "tramite";

	private static final String FOLIO = "folio";

	private static final String ACTORES = "actores";

	private static final String ACTOR_ORG = "org";

	private static final String ACTOR_ZONA = "zona";

	private static final String TIPO_ID = "tipoId";

	private static final String ACTOR_ID = "id del actor";

	private static final String ROL = "rol";

	private static final String IS_OWNER = "isOwner";

	private static final String OBJECT_ID = "id del objeto";

	private static final String CUSTOM_ID = "customId";

	private static final String PARENT_ID = "parentId";

	private static final String NAME = "nombre del archivo";

	private static final String PATH = "path";

	private static final String CONTENT = "content";

	private static final String MIME_TYPE = "mimeType";

	private static final String EXT = "extension";

	private static final String COMA = ", ";

	private static final String DOS_PUNTOS = ":";
	
	private static final String TAG_TRAMITE = "Tramite";
	
	private static final String TAG_DOCUMENTO = "Documento";
	
	private static final String TAG_ACTOR = "Actor";
	
	private static final String TAG_OBJECT = "Object";
	
	private static final String IS_FODER= "isFolder";

	/**
	 * Constante que revisa que la cadena contenga numeros, letras y punto
	 */
	public static final BigInteger MAX_FILE_SIZE = BigInteger.valueOf(5242880);

	/**
	 * Constante para alfanumericos con guion bajo y guin medio
	 */
	public static final Pattern alfaNumerioCaracteresEspeciales150 = Pattern
			.compile("(?!\\s)^[a-zA-Z0-9\\-_/\\.\\Ññ .]{1,150}$");

	/**
	 * Constante para alfanumericos con guion bajo y guin medio
	 */
	public static final Pattern alfaNumerioCaracteresEspeciales100 = Pattern
			.compile("(?!\\s)^[a-zA-Z0-9\\-_/\\.\\Ññ .]{1,100}$");

	/**
	 * Constante para Validar Extensiones permitidas
	 */
	public static final Pattern catalogoExt = Pattern
			.compile("(txt|xml|html|jpeg|bmp|csv|gif|jp2|doc|png|css|tiff|pdf|jpg)$");

	/**
	 * Constante para alfanumericos con guion bajo y guin medio
	 */
	public static final Pattern alfaNumerioCaracteresEspeciales50 = Pattern
			.compile("(?!\\s)^[a-zA-Z0-9\\-_/\\.\\Ññ .;]{1,50}$");

	/**
	 * Constante para alfanumericos con guion bajo y guin medio
	 */
	public static final Pattern alfaNumerioCaracteresEspeciales30 = Pattern
			.compile("(?!\\s)^[a-zA-Z0-9\\-_/\\.\\Ññ .]{1,30}$");
	
	/**
	 * Constante para 
	 */
	public static final Pattern regexpFecha1 = Pattern
			.compile("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
	
	/**
	 * Constante para 
	 */
	public static final Pattern regexpFecha2 = Pattern
			.compile("(19|20)[0-9]{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])");
	
	/**
	 * Constante para 
	 */
	public static final Pattern regexpFecha3 = Pattern
			.compile("(19|20)[0-9][0-9]-(0[0-9]|1[0-2])-(0[1-9]|([12][0-9]|3[01])T([01][0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9])");
	
	/**
	 * Constante para 
	 */
	public static final Pattern regexpFecha4 = Pattern
			.compile("^(-?(?:[1-9][0-9]*)?[0-9]{4})-(1[0-2]|0[1-9])-(3[0-1]|0[1-9]|[1-2][0-9])T(2[0-3]|[0-1][0-9]):([0-5][0-9]):([0-5][0-9])(\\.[0-9]+)?(Z|[+-](?:2[0-3]|[0-1][0-9]):[0-5][0-9])?$");
	
	/**
	 * Constante para alfanumericos con guion bajo y guin medio
	 */
	public static final Pattern ALFANUMERICO_100_POS = Pattern
			.compile("(?!\\s)^[a-zA-Z0-9\\-_/\\.\\Ññ .]{1,100}$");
	
	/**
	 * Expresion regular para validar valores true o false
	 */
	public static final Pattern REGEXP_TRUE_FALSE = Pattern
			.compile("^([Tt][Rr][Uu][Ee]|[Ff][Aa][Ll][Ss][Ee])$");

	private static boolean checkPattern(Pattern p, String name, String field,
			StringBuilder errorString) {
		boolean result = false;
		if (field != null && !field.isEmpty()) {
			field = field.trim();
			Matcher m = p.matcher(field);
			result = m.matches();
			if (!result) {
				errorString.append(name);
				errorString.append(COMA);
			}
		}
		return result;
	}

	public static boolean checkDate(SimpleDateFormat sdf1,
			SimpleDateFormat sdf2, String field, boolean validDate) {
		try {
			sdf2.format(sdf1.parse(field));
			validDate = Boolean.TRUE;
		} catch (ParseException e) {

		}
		return validDate;
	}

	public static void validarTramite1(TramiteTO tramite)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String org = tramite.getOrg();
		checkPattern(alfaNumerioCaracteresEspeciales100, TRAMITE_ORG, org,
				sbInvalid);

		String zona = tramite.getZona();
		checkPattern(alfaNumerioCaracteresEspeciales100, TRAMITE_ZONA, zona,
				sbInvalid);

		String tram = tramite.getTramite();
		checkPattern(alfaNumerioCaracteresEspeciales100, TRAMITE, tram,
				sbInvalid);

		String fecha = tramite.getFecha();
		if (!validarFecha(fecha)) {
			sbInvalid.append("fecha");
			sbInvalid.append(COMA);
		}

		String folio = tramite.getFolio();
		if (folio == null || folio.isEmpty()) {
			sbNull.append(FOLIO);
			sbNull.append(COMA);
		}
		checkPattern(ALFANUMERICO_100_POS, FOLIO, folio,
				sbInvalid);

		List<String> actores = tramite.getActores();
		if (actores != null) {
			for (String actor : actores) {
				checkPattern(alfaNumerioCaracteresEspeciales100, ACTORES,
						actor, sbInvalid);
			}
		}
		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_TRAMITE);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}

	public static void validarTramite2(TramiteTO tramite)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String org = tramite.getOrg();
		checkPattern(alfaNumerioCaracteresEspeciales100, TRAMITE_ORG, org,
				sbInvalid);

		String zona = tramite.getZona();
		checkPattern(alfaNumerioCaracteresEspeciales100, TRAMITE_ZONA, zona,
				sbInvalid);

		String tram = tramite.getTramite();
		checkPattern(alfaNumerioCaracteresEspeciales100, TRAMITE, tram,
				sbInvalid);

		String fecha = tramite.getFecha();
		if (!validarFecha(fecha)) {
			sbInvalid.append("fecha");
			sbInvalid.append(COMA);
		}

		String folio = tramite.getFolio();
		checkPattern(alfaNumerioCaracteresEspeciales100, FOLIO, folio,
				sbInvalid);
		
		if(folio == null || folio.isEmpty()){
			sbNull.append("folio");
			
		}

		List<String> actores = tramite.getActores();
		if (actores != null) {
			for (String actor : actores) {
				checkPattern(alfaNumerioCaracteresEspeciales100, ACTORES,
						actor, sbInvalid);
			}
		}

		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
		
		if (sbNull.length() > 0) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		
		
		
	}

	public static void validarActor1(ActorTO actor)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String org = actor.getOrg();
		checkPattern(alfaNumerioCaracteresEspeciales100, ACTOR_ORG, org,
				sbInvalid);

		String zona = actor.getZona();
		checkPattern(alfaNumerioCaracteresEspeciales100, ACTOR_ZONA, zona,
				sbInvalid);

		String tipoId = actor.getTipoId();
		if (tipoId == null || tipoId.isEmpty()) {
			sbNull.append(TIPO_ID);
			sbNull.append(COMA);
		}
		checkPattern(alfaNumerioCaracteresEspeciales50, TIPO_ID, tipoId,
				sbInvalid);

		String id = actor.getId();
		if (id == null || id.isEmpty()) {
			sbNull.append(ACTOR_ID);
			sbNull.append(COMA);
		}
		checkPattern(alfaNumerioCaracteresEspeciales50, ACTOR_ID, id,
				sbInvalid);

		String rol = actor.getRol();
		checkPattern(alfaNumerioCaracteresEspeciales100, ROL, rol, sbInvalid);

		String isOwner = actor.getIsOwner();
		if (isOwner == null || isOwner.isEmpty()) {
			sbNull.append(IS_OWNER);
			sbNull.append(" solo permitidos 'true' o 'false'");
			sbNull.append(COMA);
		}else{
			if(!checkPattern(REGEXP_TRUE_FALSE, IS_OWNER, isOwner, sbInvalid)){
				sbInvalid.append("solo permitidos 'true' o 'false'  ");				
			}
		}

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_ACTOR);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}

	public static void validarActor2(ActorTO actor)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String org = actor.getOrg();
		checkPattern(alfaNumerioCaracteresEspeciales100, ACTOR_ORG, org,
				sbInvalid);

		String zona = actor.getZona();
		checkPattern(alfaNumerioCaracteresEspeciales100, ACTOR_ZONA, zona,
				sbInvalid);

		String tipoId = actor.getTipoId();
		if (tipoId == null || tipoId.isEmpty()) {
			sbNull.append(TIPO_ID);
			sbNull.append(COMA);
		}
		
		checkPattern(alfaNumerioCaracteresEspeciales100, TIPO_ID, tipoId,
				sbInvalid);

		String id = actor.getId();
		if (id == null || id.isEmpty()) {
			sbNull.append(ACTOR_ID);
			sbNull.append(COMA);
		}
		checkPattern(alfaNumerioCaracteresEspeciales100, ACTOR_ID, id,
				sbInvalid);

		String rol = actor.getRol();
		checkPattern(alfaNumerioCaracteresEspeciales100, ROL, rol, sbInvalid);
		
		String isOwner = actor.getIsOwner();
		
		
		if(!isOwner.isEmpty()){		
			if(!ValidationHelper.validarBoolean(isOwner))
			{
				sbInvalid.append(IS_OWNER);
				sbInvalid.append(" solo permitidos 'true' o 'false' ");
				sbInvalid.append(COMA);
			}
			
		}

		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
		
		
		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2,
					sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		
		
	}

	public static void validarDocumento1(DocumentTO document)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String name = document.getName();
		String isFolder = document.isFolder();
		String content = document.getContent();
		String ext = document.getExt();

		if (name == null || name.isEmpty()) {
			sbNull.append(NAME);
			sbNull.append(COMA);
		}

		if (content == null || content.isEmpty()) {
			sbNull.append(CONTENT);
			sbNull.append(COMA);
		}

		if (ext == null || ext.isEmpty()) {
			sbNull.append("Ext");
			sbNull.append(COMA);
		}
		
		if(!isFolder.isEmpty()){
			if(!checkPattern(REGEXP_TRUE_FALSE, IS_FODER, isFolder, sbInvalid)){
				sbInvalid.append("solo permitidos 'true' o 'false'  ");
			}
		}

		String id = document.getId();
		checkPattern(alfaNumerioCaracteresEspeciales30, "ID", id, sbInvalid);

		if(!Base64.isBase64(content)){
			sbInvalid.append("content ");
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_BASE64,
							sbInvalid.toString());
		}

		checkPattern(catalogoExt, EXT, ext, sbInvalid);

		checkPattern(alfaNumerioCaracteresEspeciales100, NAME, name, sbInvalid);

		String customId = document.getCustomId();
		checkPattern(alfaNumerioCaracteresEspeciales100, CUSTOM_ID, customId,
				sbInvalid);

		String parentId = document.getParentId();
		checkPattern(alfaNumerioCaracteresEspeciales100, PARENT_ID, parentId,
				sbInvalid);

		String path = document.getPath();
		checkPattern(alfaNumerioCaracteresEspeciales150, PATH, path, sbInvalid);

		String mimeType = document.getMimeType();
		checkPattern(alfaNumerioCaracteresEspeciales100, MIME_TYPE, mimeType,
				sbInvalid);

		if (name != null && ext != null) {
			String extension = name.substring(name.lastIndexOf(".") + 1);
			if (!extension.endsWith(ext)) {
				sbInvalid.append("Las extensiones de 'name' y de 'ext' no coinciden");
				sbInvalid.append(DOS_PUNTOS);
				sbInvalid.append(TAG_DOCUMENTO);
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
								sbInvalid.toString());
			}
		}

		BigInteger val = BigInteger.valueOf(content.length());
		if (val.compareTo(MAX_FILE_SIZE) == 1) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_MB_EXCEED);
		}

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_DOCUMENTO);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}

	public static void validarDocumento2(DocumentTO document)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String id = document.getId();
		String name = document.getName();
		if (id == null || id.isEmpty()) {
			if (name == null || name.isEmpty()) {
				sbNull.append(ACTOR_ID);
				sbNull.append(COMA);
			}
			checkPattern(alfaNumerioCaracteresEspeciales100, NAME, name,
					sbInvalid);

			String customId = document.getCustomId();
			checkPattern(alfaNumerioCaracteresEspeciales100, CUSTOM_ID,
					customId, sbInvalid);

			String parentId = document.getParentId();
			checkPattern(alfaNumerioCaracteresEspeciales100, PARENT_ID,
					parentId, sbInvalid);

			String path = document.getPath();
			checkPattern(alfaNumerioCaracteresEspeciales100, PATH, path,
					sbInvalid);

			String mimeType = document.getMimeType();
			checkPattern(alfaNumerioCaracteresEspeciales100, MIME_TYPE,
					mimeType, sbInvalid);

			String ext = document.getExt();
			checkPattern(catalogoExt, EXT, ext, sbInvalid);
		}

		String content = document.getContent();
		if (content == null || content.isEmpty()) {
			sbNull.append(CONTENT);
			sbNull.append(COMA);
		}
		BigInteger val = BigInteger.valueOf(content.length());
		if (val.compareTo(MAX_FILE_SIZE) == 1) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_MB_EXCEED);
		}

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}

	public static void validarObjeto1(BaseObjectTO document)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String id = document.getId();
		String name = document.getName();
		
		String folder = document.isFolder();
		
		if(id == null || id.isEmpty()){
		
		if (name == null || name.isEmpty()) {
			sbNull.append(NAME);
			sbNull.append(COMA);
		}
		
		}
		checkPattern(ALFANUMERICO_100_POS, NAME, name, sbInvalid);

		String customId = document.getCustomId();
		checkPattern(alfaNumerioCaracteresEspeciales100, CUSTOM_ID, customId,
				sbInvalid);

		String parentId = document.getParentId();
		checkPattern(alfaNumerioCaracteresEspeciales100, PARENT_ID, parentId,
				sbInvalid);

		String path = document.getPath();
		checkPattern(alfaNumerioCaracteresEspeciales150, PATH, path, sbInvalid);
		checkPattern(alfaNumerioCaracteresEspeciales50, OBJECT_ID, id,
				sbInvalid);
		
		if(!folder.isEmpty()){		
			if(!ValidationHelper.validarBoolean(folder))
			{
				sbInvalid.append(IS_FODER);
				sbInvalid.append(" solo permitidos 'true' o 'false' ");
				sbInvalid.append(COMA);
			}
			
		}

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_OBJECT);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}
	
	public static boolean validarBoolean(String valortxt){
		return "true".equals(valortxt) || "false".equals(valortxt);
	}

	public static void validarObjeto2(BaseObjectTO document)
			throws BovedaPersonalServicesException {

		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		// Para las operaciones getFolderDescendnts, getFolderDocuments,
		// getFolderObjects
		// son obligatorios los campos id & isFolder
		
		String id = document.getId();

		String name=document.getName();

		String nameobj2 = document.getName();

		String isFolder = document.isFolder();

		
		if (id == null || id.isEmpty()) {
			if (name == null || name.isEmpty()) {
				sbNull.append(NAME);
				sbNull.append(COMA);
			}

			
			if(nameobj2 == null || nameobj2.isEmpty()){			
			 	sbNull.append("id");
			    sbNull.append(COMA);
			
				sbNull.append("nombre");
				sbNull.append(COMA);
		    
			}	
		

		} else {
			checkPattern(alfaNumerioCaracteresEspeciales50, OBJECT_ID, id,
					sbInvalid);

		}

		if (isFolder == null) {
			sbNull.append("isFolder nulo");
		}

		
		checkPattern(alfaNumerioCaracteresEspeciales100, NAME, name, sbInvalid);

		String customId = document.getCustomId();
		checkPattern(alfaNumerioCaracteresEspeciales100, CUSTOM_ID, customId,
				sbInvalid);

		String parentId = document.getParentId();
		checkPattern(alfaNumerioCaracteresEspeciales100, PARENT_ID, parentId,
				sbInvalid);

		String path = document.getPath();
		checkPattern(alfaNumerioCaracteresEspeciales150, PATH, path, sbInvalid);

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_OBJECT);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}

	}

	public static void validarMetadatos1(MetadataTO metadatos)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}

	public static String getMimeType(String key)
			throws BovedaPersonalServicesException {
		key = key.toLowerCase();

		String mimeType = (String) MimeTypeUtil.getMimeTypeMap().get(key);
		if (mimeType == null || mimeType.isEmpty()) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_ESTE_CONTENTTYPE_NOEXISTE);
		}
		return mimeType;
	}

	public static Boolean validarFecha(String fecha) {
		Boolean resultado = true;
		List<Boolean> listResultado = new ArrayList<Boolean>();
		List<Pattern> listPatternFecha = new ArrayList<Pattern>();
		listPatternFecha.add(regexpFecha1);
		listPatternFecha.add(regexpFecha2);
		listPatternFecha.add(regexpFecha3);
		listPatternFecha.add(regexpFecha4);
		if (fecha != null && !fecha.isEmpty()) {
			for (Pattern pattern : listPatternFecha) {
				Matcher matcher = pattern.matcher(fecha);
				listResultado.add(matcher.matches());
			}
			resultado = listResultado.contains(Boolean.TRUE);
		}

		return resultado;
	}
	
	public static void validarObjetoFolder(BaseObjectTO document)
			throws BovedaPersonalServicesException {
		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();

		String id = document.getId();
		
		if (!document.getName().isEmpty()) {
			checkPattern(ALFANUMERICO_100_POS, NAME, document.getName(), sbInvalid);
		}

		String customId = document.getCustomId();
		checkPattern(alfaNumerioCaracteresEspeciales100, CUSTOM_ID, customId,
				sbInvalid);

		String parentId = document.getParentId();
		checkPattern(alfaNumerioCaracteresEspeciales100, PARENT_ID, parentId,
				sbInvalid);

		String path = document.getPath();
		checkPattern(alfaNumerioCaracteresEspeciales150, PATH, path, sbInvalid);
		checkPattern(alfaNumerioCaracteresEspeciales50, OBJECT_ID, id,
				sbInvalid);

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_OBJECT);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
	}
	
	public static void validarObjetoFolder2(BaseObjectTO base) 
			                     throws BovedaPersonalServicesException {		
		StringBuilder sbInvalid = new StringBuilder();		
		String folder = base.isFolder();
		
		if(!folder.isEmpty()){
			
			boolean valor1 = !ValidationHelper.validarBoolean(folder);
			if(!ValidationHelper.validarBoolean(folder))
			{
				sbInvalid.append(IS_FODER);
				sbInvalid.append(" solo permitidos 'true' o 'false' ");
				sbInvalid.append(COMA);
			}			
		}
		
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_OBJECT);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}
			
	}
	
	public static void validarObjetoGetFolderObjects(BaseObjectTO document)
			throws BovedaPersonalServicesException {

		StringBuilder sbInvalid = new StringBuilder();
		StringBuilder sbNull = new StringBuilder();
		String id = document.getId();
		String isFolder = document.isFolder();
		String name = document.getName();

		if ((id == null || id.isEmpty()) && (name == null || name.isEmpty())) {
			sbNull.append("id nulo");
			sbNull.append(COMA);
		} else if ((name == null || name.isEmpty()) && (id == null || id.isEmpty())) {
			sbNull.append("name nulo");
			sbNull.append(COMA);
		}

		if (isFolder == null) {
			sbNull.append("isFolder nulo");
		}

		checkPattern(alfaNumerioCaracteresEspeciales50, OBJECT_ID, id,
				sbInvalid);
		
		checkPattern(alfaNumerioCaracteresEspeciales100, NAME, name, sbInvalid);

		String customId = document.getCustomId();
		checkPattern(alfaNumerioCaracteresEspeciales100, CUSTOM_ID, customId,
				sbInvalid);

		String parentId = document.getParentId();
		checkPattern(alfaNumerioCaracteresEspeciales100, PARENT_ID, parentId,
				sbInvalid);

		String path = document.getPath();
		checkPattern(alfaNumerioCaracteresEspeciales150, PATH, path, sbInvalid);

		if (sbNull.length() > 0) {
			sbNull = sbNull.delete(sbNull.length() - 2, sbNull.length());
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_OBLIGATORIO,
							sbNull.toString());
		}
		if (sbInvalid.length() > 0) {
			sbInvalid = sbInvalid.delete(sbInvalid.length() - 2,
					sbInvalid.length());
			sbInvalid.append(DOS_PUNTOS);
			sbInvalid.append(TAG_OBJECT);
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_INCORRECTO,
							sbInvalid.toString());
		}

	}

	
}

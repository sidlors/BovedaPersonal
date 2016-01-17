package mx.gob.imss.cit.bp.constant;

/**
 * Clase de constantes para la capa de servicios
 * @author Admin
 *
 */

public final class BovedaConstants {

	/**
	 * Contructor privado
	 */
	private BovedaConstants(){
		
	}
		
	/**
	 * atributo con el nombre del archivo de properties
	 */
	public static final String FILE_SERVICE_CONFIG = "service-config";
	/**
	 * Constante para la url de alfresco
	 */
	public static final String KEY_URL_ALFRESCO = "URL_ALFRESCO";
	
	public static final String KEY_ROOT_FOLDER_ALFRESCO = "ROOT_FOLDER_ALFRESCO";
	
	public static final String KEY_ADMIN_USER_ALFRESCO = "ADMIN_USER_ALFRESCO";
	
	public static final String KEY_ADMIN_PASS_ALFRESCO = "ADMIN_PASS_ALFRESCO";
	
	public final static String ROOT = "/BovedaPersonal";
	
	public final static String USER_ROOT = "/Usuarios";
	
	public final static String PROCEDURE_ROOT = "/Tramites";

	public final static String ACTOR = "ACTOR";
	
	public static final String CMIS_OBJECT = "cmis:document";

	public static final String DOCUMENT_TYPE = "cmis:document";
	
	public static final String FOLDER_TYPE = "cmis:folder";
	
	public static final String DOCUMENT_SEC_TYPE_ID = "P:bp:documento";
	
	public static final String IMSS_CUSTOM_ID = "bp:custom_id";

	public static final String IMSS_LISTA = "bp:lista";
	
	public static final String PROCEDURE_SEC_TYPE_ID = "P:bp:tramite";
	
	public static final String USER_SEC_TYPE_ID = "P:bp:usuario";
	
	public static final String PROCEDURE_QUERY_NAME = "bp:tramite";
	
	public static final String IMSS_ORG = "bp:org";
	
	public static final String IMSS_ZONA = "bp:zona";
	
	public static final String IMSS_TRAMITE = "bp:tramite";
	
	public static final String IMSS_FECHA = "bp:fecha";
	
	public static final String IMSS_FOLIO = "bp:folio";
	
	public static final String IMSS_BORRADO = "bp:borrado";
	
	public static final String IMSS_USER_BORRADO="bp:user_borrado";
	
	public static final String IMSS_ACTORS = "bp:actors";
	
	public static final String USER_FOLDER_SEC_TYPE_ID = "P:bp:usuario";

	public static final String USER_FOLDER_QUERY_NAME = "bp:usuario";
	
	public static final String IMSS_USER_ORG = "bp:user_org";
	
	public static final String IMSS_USER_ZONA = "bp:user_zona";
	
	public static final String IMSS_TIPO_ID = "bp:tipo_id";
	
	public static final String IMSS_USER_TIPO_ID = "bp:user_tipo_id";
	
	public static final String IMSS_USER_ID="bp:user_id";
	
	public static final String IMSS_USER_ROL="bp:user_rol";
	
	public static final String IMSS_ID = "bp:id";
	
	public static final String IMSS_ROL = "bp:rol";
	
	public static final String TYPEOBJECT="TYPEOBJECT";
	
	public static final int FOLDER_MAX_DEPTH = 5;
	
	public static final String IN_FOLDER = "IN_FOLDER";
	
	public static final String AND = "AND";
	
	public static final String ANY = "ANY";
}
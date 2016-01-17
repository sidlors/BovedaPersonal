package mx.gob.imss.cit.bp.services.exception;

public enum BovedaPersonalServicesCodeExceptionEnum {

	  /**
	   * Error desconocido 
	   */
	  ERROR_DESCONOCIDO(0),
	  /**
	   * Error al iniciar sesion
	   */
	  ERROR_AL_INICIAR_SESION(1),
	  /**
	   * Error al cerrar sesion
	   */
	  ERROR_AL_CERRAR_SESION(2),	
	  /**
	   * Error al obtener los documentos
	   */
	  ERROR_OBTENIENDO_DOCUMENTOS_EN_CARPETA(3),  
	  /**
	   * Error en la busqueda del folder
	   */
	  ERROR_CARPETA_NO_ENCONTRADA(4),
	  /**
	   * Error Folder Vacio
	   */	  
	  ERROR_CARPETA_VACIA(5),
	  /**
	   * Error el obtener documento.
	   */	  
	  ERROR_DOCUMENTO_NO_ENCONTRADO(6),
	  /**
	   * Error el obtener propiedades documento.
	   */	  
	  ERROR_PROPERTIES_DOCUMENT(7),
	  /**
	   * Version del documento no encontrada.
	   */	  
	  ERROR_DOCUMENT_VERSION_NOT_FOUND(8),
	  /**
	   * Version del documento no encontrada.
	   */	  
	  ERROR_DOCUMENT_TYPE(9),
	  /**
	   * Error del parsear la fecha.
	   */	  
	  ERROR_FORMAT_DATE(10),	  
	  /**
	   * Error Carpeta no se pudo crear
	   */	
	  ERROR_CARPETA_NO_CREADA(11),
	  /**
	   * Error Carpeta ya existe
	   */	
	  ERROR_FOlDER_YA_EXISTE(12),
	  /**
	   * EL IDFOLDER NO ES DE TIPO FOLDER
	   */	
	  ERROR_OBJETO_NO_ES_DEL_TIPO_CARPETA(13),
	  /**
	   * Error No es Documento
	   */
	  ERROR_NODOCUMENT(14),  
	  /**
	   * Error en la busqueda de la informacion de las versiones
	   */
	  ERROR_DOCUMENT_INFOVERSION(15),
	  /**
	   * Error Version Vacia
	   */	  
	  ERROR_VERSION_EMPTY(16),
	  /**
	   * Es un Documento
	   */	  	  
	  ERROR_IS_DOCUMENT(17),
	  /**
	   * Es un FOLDER
	   */	  	  
		  	    			
	  ERROR_IS_FOLDER(18),
	  /**
	   * No Contiene Tumbnail
	   */	  	  
	  ERROR_NOTUMBNAIL(19),
	  /**
	   * Error no hay resultados en la consulta.
	   */	  
	  ERROR_NO_RESULT_METADATA(20),
	  /**
	   * Error al obtener el metadata
	   */	  
	  ERROR_RETRIEVE_METADATA(21),
	  /**
	   * Error en el tipo de documento.
	   */	  
	  ERROR_DOCUMENT_MIME_TYPE(22),
	  /**
	   * Error No es folder
	   */
	  ERROR_METADATA_BYFOLDER(23),
	  /**
	   * Error no encontrar version
	   */	  
	  ERROR_NO_FOUND_DOCUMENT_VERSION(24),
	  /**
	   * Error obtener el stream.
	   */	  
	  ERROR_OBTENIENDO_CONTENIDO_DEL_DOCUMENTO(25),
	  /**
	   * Error  el obtener el archivo.
	   */	  
	  ERROR_NO_RESULT_STREAM_DOCUMENT(26),
	  ERROR_FIND_DOCUMENTS(27),
	  /**
	   * Error no hay resultados en la consulta.
	   */	  
	  ERROR_NO_RESULT_FIND_DOCUMENTS(28),

	  /**
	   * ERROR DOCUMENTO NO CREADO
	   */	
	  ERROR_DOCUMENTO_NOCREADO(29),
	  /**
	   * Documento ya existe
	   */	
	  ERROR_DOCUMENTO_YAEXISTE(30),
	  /**
	   * Documento ya existe
	   */	
	  ERROR_ESTE_CONTENTTYPE_NOEXISTE(31),
	  
	  /**
	   * NO SE PUDO ELIMIANR DOCUMENTO
	   */		
	  ERROR_ELDOCUEMENTO_NOSEPUDO_ELIMINAR(32),
	  /**
	   * NO SE PUDO ELIMIANR DOCUMENTO
	   */		
	  ERROR_El_CONTENTTYPE_NO_EXISTE(33),
	  /**
	   * EL CONTENTTYPE NO EXISTE
	   */	 
	  ERROR_EL_STREAM_ES_INCORRECTO(34),
	  
	  /**
	   * EL ID_DOCUMENTO NO ES DE TIPO DOCUMENTO
	   */	
	  ERROR_OBJETO_NO_ES_DEL_TIPO_DOCUMENTO(35),	  
	  /**
	   * Error el ID NO EXISTE.
	   */	  
	  ERROR_ID_NOT_FOUND(36),
	  /**
	   * Error tamaño dle archivo es mayor a 5 MB.
	   */
	  ERROR_MB_EXCEED(37),
	  /**
	   * Error al crear el objeto OperationContext.
	   */
	  ERROR_AL_CREAR_OPERATION_CONTEXT(38),
	  /**
	   * Error el tama�o del resultado es mayor al esperado.
	   */
	  ERROR_TAMAÑO_RESULTADO_ES_DISTINTO_UNO(39),
	  
	  ERROR_TRAMITE_YA_CREADO(52),
	  /**
	   * Error de conexión
	   */
	  ERROR_DE_CONEXION(100),
	  /**
	   * 
	   * Error falta de parametros
	   */
	  ERROR_PARAMETRO_OBLIGATORIO(101),
	  
	  
	  /*
	   * Error parametro incorrecto diferente al que se espera
	   */
	  
	  ERROR_PARAMETRO_FORMATO_INCORRECTO(102),
	  
	  /**
	   * Error de codificacion Base64
	   */
	  ERROR_PARAMETRO_FORMATO_BASE64(103);
	 
	  				
	  private int id;
	
	  /**
	   * Constructor interno
	   * 
	   * @param id
	   */
	  private BovedaPersonalServicesCodeExceptionEnum (int id){
		  this.id = id;
	  }
	  
	  /**
	   * @return the id 
	   */	  
	  public int getId(){
		  return id;
	  }
	
}

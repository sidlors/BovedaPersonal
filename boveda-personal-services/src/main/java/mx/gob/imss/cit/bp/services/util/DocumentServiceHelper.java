package mx.gob.imss.cit.bp.services.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.gob.imss.cit.bp.constant.BovedaConstants;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.SecondaryType;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.util.encoders.Base64;

public class DocumentServiceHelper {

	private static final Encriptador encriptador = new Encriptador();

	/**
	 * @param document
	 * @return ContentStream
	 */
	public static final ContentStream createContentStream(DocumentTO document)
			throws BovedaPersonalServicesException {
		String ext = document.getExt();
		String content = document.getContent();

		String filename = document.getName();
		BigInteger length = BigInteger.valueOf(content.length());
		String mimetype = (String) MimeTypeUtil.getMimeTypeMap().get(ext);
		byte[] bArray = null;
		byte[] eArray = null;
		try {
			bArray = Base64.decode(content);
			// eArray = encriptador.encrypt( bArray );
		} catch (Exception e) {
			StringBuilder sbInvalid = new StringBuilder();

			sbInvalid.append("content ");
			

			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_PARAMETRO_FORMATO_BASE64,
							sbInvalid.toString());
		}
		ByteArrayInputStream is = new ByteArrayInputStream(bArray);
		return new ContentStreamImpl(filename, length, mimetype, is);
	}

	/**
	 * 
	 * @param tramite
	 * @param document
	 * @return
	 */
	public static Document getDocumentInFolder(Session session,
			TramiteTO tramite, BaseObjectTO base, ActorTO actor)
			throws BovedaPersonalServicesException {
		// TODO Refactorizar creando un metodo que obtenga el folderId.
		Document result = null;
		ParOrdenado<String, String>[] fPairs = null; // Contiene propiedades de
														// alfresco
		ParOrdenado<String, String>[] sPairs = null; // Contiene propiedades de
														// un objeto de tipo
														// secundario
		String secondaryType = null;
		QueryResult folderResult = null;
		QueryResult docResult = null;
		String folderId = null;
		String docId = null;
		Boolean isOwner = Boolean.valueOf(actor.getIsOwner());

		if (isOwner) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}
		String actorId = ServiceHelper.getActorId(actor);
		folderResult = QueryUtil.searchFolder(session, secondaryType, actorId,
				fPairs, sPairs, isOwner);

		if (folderResult != null) {
			folderId = folderResult.getPropertyValueById(PropertyIds.OBJECT_ID);
			if (folderId == null) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
		}
		if (folderId != null) {
			docResult = QueryUtil.searchDocumentInFolder(session, folderId,
					base.getName());
		}
		// TODO Buscar una forma inteligente de hacer esto.
		if (docResult != null) {
			docId = docResult.getPropertyValueById(PropertyIds.OBJECT_ID);

			if (isOwner) {
				result = ServiceHelper.getDocumentById(session, docId,
						isOwner);
				if (Boolean.valueOf(result.getProperty(
						BovedaConstants.IMSS_USER_BORRADO).getValueAsString())) {
					result = null;
				}

			} else {
				result = ServiceHelper.getDocumentById(session, docId);
				if (Boolean.valueOf(result.getProperty(
						BovedaConstants.IMSS_BORRADO).getValueAsString())) {
					result = null;
				}
			}
		}
		return result;
	}

	/**
	 * 
	 * @param tramite
	 * @param document
	 * @return
	 */
	public static MetadataTO getDocumentMetadata(Session session,
			TramiteTO tramite, BaseObjectTO base, ActorTO actor)
			throws BovedaPersonalServicesException {
		// TODO Refactorizar creando un metodo que obtenga el folderId.
		MetadataTO result = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;
		String secondaryType = null;
		QueryResult folderResult = null;
		QueryResult docResult = null;
		String folderId = null;
		String docId = null;
		
		Boolean isOwner = Boolean.valueOf(actor.getIsOwner());

		if (isOwner) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}
		String actorId = ServiceHelper.getActorId(actor);
		folderResult = QueryUtil.searchFolder(session, secondaryType, actorId,
				fPairs, sPairs);

		if (folderResult != null) {
			StringBuilder sb = new StringBuilder(QueryUtil.D_PREFIX);
			sb.append(PropertyIds.OBJECT_ID);
			folderId = folderResult.getPropertyValueByQueryName(sb.toString());
			if (folderId == null) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
		}
		docResult = QueryUtil.searchDocumentInFolder(session, folderId,
				base.getName());
		// TODO Buscar una forma inteligente de hacer esto.
		if (docResult != null) {
			docId = docResult
					.getPropertyValueByQueryName(PropertyIds.OBJECT_ID);
			Document temp = ServiceHelper.getDocumentById(session, docId);
			result = createMetadataTO(temp);
		}
		return result;
	}

	public static final Document deleteDocument(Document document)
			throws BovedaPersonalServicesException {
		CmisObject object = null;
		List<SecondaryType> list = document.getSecondaryTypes();
		for (SecondaryType tmp : list) {
			if (tmp.getId().equals(BovedaConstants.DOCUMENT_SEC_TYPE_ID)) {
				Map<String, Object> properties = new HashMap<String, Object>();
				properties.put(BovedaConstants.IMSS_BORRADO, Boolean.TRUE);
				object = document.updateProperties(properties);
				if (object == null) {
					BovedaPersonalServicesExceptionBuilder
							.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO);
				}
				break;
			}
		}
		return document;
	}

	/**
	 * 
	 * @param tramite
	 * @param document
	 * @return
	 */
	public static Folder getDocumentFolder(Session session, TramiteTO tramite,
			DocumentTO document, ActorTO actor)
			throws BovedaPersonalServicesException {

		ParOrdenado<String, String>[] fPairs = null; // Contiene propiedades de
														// alfresco
		ParOrdenado<String, String>[] sPairs = null; // Contiene propiedades de
														// un objeto de tipo
														// secundario
		String secondaryType = null;
		QueryResult folderResult = null;
		String folderId = null;
		Folder parent = null;
		Folder folder = null;
		String actorId;
		
		Boolean isOwner = Boolean.valueOf(actor.getIsOwner());

		if (isOwner) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;

			actorId = ServiceHelper.getActorId(actor);
			folderResult = QueryUtil.searchFolder(session, secondaryType,
					actorId, fPairs, sPairs, isOwner);
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
			actorId = ServiceHelper.getActorId(actor);
			folderResult = QueryUtil.searchFolder(session, secondaryType,
					actorId, fPairs, sPairs);
		}

		if (folderResult != null) {
			StringBuilder sb = new StringBuilder("d.");
			sb.append(PropertyIds.OBJECT_ID);
			folderId = folderResult.getPropertyValueByQueryName(sb.toString());
			if (folderId != null) {
				folder = ServiceHelper.getFolderById(session, folderId);
			} else {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
		} else {

			if (isOwner) {
				parent = ServiceHelper.getFolderByPath(session,
						"/BovedaPersonal/Usuarios");
				Map<String, Object> properties = new HashMap<String, Object>(32);
				ServiceHelper.addPlainProperties(properties,
						actor.getId(), BovedaConstants.FOLDER_TYPE);
				ServiceHelper.addPropertiesMap(properties, tramite, actor,
						document);
				folder = parent.createFolder(properties);

			} else {
				parent = ServiceHelper.getFolderByPath(session,
						"/BovedaPersonal/Tramites");
				Map<String, Object> properties = new HashMap<String, Object>(32);
				ServiceHelper.addPlainProperties(properties,
						tramite.getFolio(), BovedaConstants.FOLDER_TYPE);
				ServiceHelper.addPropertiesMap(properties, tramite, actor,
						document);
				try{
				folder = parent.createFolder(properties);
				}catch(CmisContentAlreadyExistsException cex){
		
					throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_TRAMITE_YA_CREADO, "Mensaje Error");
					
				}
			}
		}
		return folder;
	}

	public static final BaseResponseTO addActor(Session session,
			TramiteTO tramite, BaseObjectTO document, ActorTO actor,
			ActorTO newActor) throws BovedaPersonalServicesException {
		BaseResponseTO response = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;

		String secondaryType = null;
		QueryResult folderResult = null;
		QueryResult docResult = null;
		String folderId = null;
		String docId = null;
		Document doc = null;
		List<Object> actorsList = null;

		if (Boolean.valueOf(actor.getIsOwner())) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_SEC_TYPE_ID;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}
		String actorId = ServiceHelper.getActorId(actor);
		folderResult = QueryUtil.searchFolder(session, secondaryType, actorId,
				fPairs, sPairs);

		if (folderResult != null) {
			folderId = folderResult.getPropertyValueById(PropertyIds.OBJECT_ID);
			actorsList = folderResult
					.getPropertyMultivalueById(BovedaConstants.IMSS_ACTORS);
			if (actorsList != null) {
				actorsList.add(ServiceHelper.getActorId(newActor));
			}
			if (folderId == null) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
		}

		docResult = QueryUtil.searchDocumentInFolder(session, folderId,
				document.getName());
		if (docResult != null) {
			docId = docResult
					.getPropertyValueByQueryName(PropertyIds.OBJECT_ID);
			doc = ServiceHelper.getDocumentById(session, docId);

			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put(BovedaConstants.IMSS_ACTORS, actorsList);
			doc.updateProperties(properties);
		}
		response = new BaseResponseTO();
		response.setExitoso(true);
		return response;
	}

	public static final DocumentTO createDocumentTO(Document document)
			throws BovedaPersonalServicesException {
		DocumentTO result = null;
		byte[] bArray = null;
		String fileName = null;

		if (document != null) {
			result = new DocumentTO();
			try {
				InputStream is = document.getContentStream().getStream();

				fileName = document.getName();
				// bArray = encriptador.decrypt( IOUtils.toByteArray(is) );
				bArray = IOUtils.toByteArray(is);
				result.setId(document.getId());
				if (fileName != null) {
					result.setName(fileName);
					result.setExt(ServiceHelper.getExtension(fileName));
				}
				result.setContent(new String(Base64.encode(bArray)));
				result.setMimeType(document.getContentStreamMimeType());
				result.setFolder("false");
			} catch (IOException e) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBTENIENDO_CONTENIDO_DEL_DOCUMENTO,
								e);
			}
		}
		return result;
	}

	public static final MetadataTO createMetadataTO(Document document)
			throws BovedaPersonalServicesException {
		MetadataTO result = new MetadataTO();
		String version = document.getProperty(PropertyIds.VERSION_LABEL)
				.getValueAsString();
		result.setVersion(version);
		List<Object> oActores = document.getProperty(
				BovedaConstants.IMSS_ACTORS).getValues();
		List<String> sActores = new ArrayList<String>();
		for (Object oActor : oActores) {
			String sActor = oActor.toString();
			sActores.add(sActor);
		}
		result.setActores(sActores);
		String fechaCreacion = document.getProperty(BovedaConstants.IMSS_FECHA)
				.getValue();
		Date dFechaCreacion = null;
		Date dFechaModificacion = null;
		GregorianCalendar gFechaModificacion = null;
		try {
			dFechaCreacion = QueryUtil.sdf.parse(fechaCreacion);
			gFechaModificacion = document.getProperty(
					PropertyIds.LAST_MODIFICATION_DATE).getValue();
			dFechaModificacion = gFechaModificacion.getTime();
		} catch (ParseException e) {

		}
		result.setFechaCreacion(dFechaCreacion);
		result.setFechaModificacion(dFechaModificacion);
		if (Boolean.valueOf(document.getProperty(BovedaConstants.IMSS_BORRADO)
				.getValueAsString())) {
			result = null;
		}
		return result;
	}

	public static final List<DocumentTO> createDocumentTOList(
			List<Document> documentList) throws BovedaPersonalServicesException {
		List<DocumentTO> results = new ArrayList<DocumentTO>();
		for (Document document : documentList) {
			DocumentTO temp = createDocumentTO(document);
			results.add(temp);
		}
		return results;
	}

	private static final List<MetadataTO> createMetadataTOs(
			List<QueryResult> resultsList)
			throws BovedaPersonalServicesException {
		List<MetadataTO> result = new ArrayList<MetadataTO>();
		List<String> sActores = null;
		String fechaCreacion = null;
		Date dFechaCreacion = null;
		try {
			for (QueryResult qr : resultsList) {
				MetadataTO temp = new MetadataTO();
				StringBuilder sb = new StringBuilder(QueryUtil.I_PREFIX);
				sb.append(BovedaConstants.IMSS_ACTORS);
				sActores = qr.getPropertyMultivalueByQueryName(sb.toString());
				temp.setActores(sActores);
				sb = null;
				sb = new StringBuilder(QueryUtil.I_PREFIX);
				sb.append(BovedaConstants.IMSS_FECHA);
				fechaCreacion = qr.getPropertyValueByQueryName(sb.toString());
				try {
					dFechaCreacion = QueryUtil.sdf.parse(fechaCreacion);
					temp.setFechaCreacion(dFechaCreacion);
				} catch (ParseException e) {
				}
				result.add(temp);
			}
		} catch (Exception e) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBTENIENDO_CONTENIDO_DEL_DOCUMENTO,
							e);
		}
		return result;
	}

	public static final List<DocumentTO> findDocumentsByMetadata(
			Session session, TramiteTO tramite, MetadataTO metadata,
			ActorTO actor) throws BovedaPersonalServicesException {
		List<DocumentTO> results = new ArrayList<DocumentTO>();
		String actorId = ServiceHelper.getActorId(actor);
		List<QueryResult> qrList = QueryUtil.searchByMetadata(session, actorId,
				metadata);
		for (QueryResult qr : qrList) {
			StringBuilder sb = new StringBuilder(QueryUtil.D_PREFIX);
			sb.append(PropertyIds.OBJECT_ID);
			String docId = qr.getPropertyValueByQueryName(sb.toString());
			if (docId != null) {
				Document document = ServiceHelper.getDocumentById(session,
						docId);
				DocumentTO temp = createDocumentTO(document);
				results.add(temp);
			}
		}
		return results;
	}

	public static final List<MetadataTO> getAllMetadataByMetadata(
			Session session, TramiteTO tramite, MetadataTO metadata,
			ActorTO actor) throws BovedaPersonalServicesException {
		List<MetadataTO> result = new ArrayList<MetadataTO>();
		String actorId = ServiceHelper.getActorId(actor);
		List<QueryResult> qrList = QueryUtil.searchByMetadata(session, actorId,
				metadata);
		result = createMetadataTOs(qrList);
		return result;
	}
}

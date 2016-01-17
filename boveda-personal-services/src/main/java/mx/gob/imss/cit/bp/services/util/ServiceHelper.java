package mx.gob.imss.cit.bp.services.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mx.gob.imss.cit.bp.constant.BovedaConstants;
import mx.gob.imss.cit.bp.services.constants.BodegaPersonalServicesConstants;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.DocumentResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.FolderResponseTO;
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.FileableCmisObject;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.Tree;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.exceptions.CmisObjectNotFoundException;

public final class ServiceHelper {

	private static final CmisObject getObjectById(Session session, String id)
			throws BovedaPersonalServicesException {
		CmisObject object = null;
		OperationContext operationContext = null;

		if (session == null) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}

		try {
			operationContext = session.createOperationContext();
			operationContext
					.setFilterString("cmis:objectId, cmis:versionLabel, cmis:lastModificationDate, bp:actors, bp:fecha, bp:borrado, cmis:name, cmis:contentStreamMimeType ");
			operationContext.setIncludeAcls(false);
			operationContext.setIncludeAllowableActions(false);
			operationContext.setIncludePolicies(false);
			operationContext.setLoadSecondaryTypeProperties(true);
			operationContext.setRenditionFilterString("");
			object = session.getObject(id, operationContext);
		} catch (CmisObjectNotFoundException e) {
			BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO,
							e);
		}
		return object;
	}

	private static final CmisObject getUserObjectById(Session session, String id)
			throws BovedaPersonalServicesException {
		CmisObject object = null;
		OperationContext operationContext = null;

		if (session == null) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}

		try {
			operationContext = session.createOperationContext();
			operationContext
					.setFilterString("cmis:objectId, cmis:versionLabel, cmis:lastModificationDate, bp:user_org,bp:user_zona,bp:user_tipo_id,bp:user_id,bp:user_rol,bp:user_borrado");
			operationContext.setIncludeAcls(false);
			operationContext.setIncludeAllowableActions(false);
			operationContext.setIncludePolicies(false);
			operationContext.setLoadSecondaryTypeProperties(true);
			operationContext.setRenditionFilterString("");
			object = session.getObject(id, operationContext);
		} catch (CmisObjectNotFoundException e) {
			BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO,
							e);
		}
		return object;
	}

	private static final CmisObject getObjectByPath(Session session, String path)
			throws BovedaPersonalServicesException {
		CmisObject object = null;
		if (session == null) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_AL_INICIAR_SESION);
		}

		try {
			object = session.getObjectByPath(path);
		} catch (CmisObjectNotFoundException e) {
			BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO,
							e);
		}
		return object;
	}

	public static final Folder getFolderById(Session session, String folderId)
			throws BovedaPersonalServicesException {
		CmisObject object = getObjectById(session, folderId);
		Folder folder = null;
		if (object instanceof Folder) {
			folder = (Folder) object;
		} else {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBJETO_NO_ES_DEL_TIPO_CARPETA);
		}
		return folder;
	}

	public static final Folder getFolderByPath(Session session, String path)
			throws BovedaPersonalServicesException {
		CmisObject object = getObjectByPath(session, path);
		Folder folder = null;
		if (object instanceof Folder) {
			folder = (Folder) object;
		} else {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBJETO_NO_ES_DEL_TIPO_CARPETA);
		}
		return folder;
	}

	public static final Document getDocumentById(Session session,
			String objectId) throws BovedaPersonalServicesException {
		CmisObject object = null;
		Document doc = null;
		if (objectId != null && !objectId.isEmpty()) {
			object = getObjectById(session, objectId);
			if (object instanceof Document) {
				doc = (Document) object;
			} else {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBJETO_NO_ES_DEL_TIPO_DOCUMENTO);
			}
		} else {
			// TODO Corregir mensaje de la excepción
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_ID_NOT_FOUND);
		}
		return doc;
	}

	public static final Document getDocumentById(Session session,
			String objectId, boolean isOwner)
			throws BovedaPersonalServicesException {
		CmisObject object = null;
		Document doc = null;
		String key="";
		if (objectId != null && !objectId.isEmpty()) {
			if (isOwner) {
				object = getUserObjectById(session, objectId);
				key="bp:user_borrado";
			} else {
				object = getObjectById(session, objectId);
				key="bp:borrado";
			}
			if (object instanceof Document) {
				doc = (Document) object;
				if((Boolean)doc.getProperty(key).getValue()){
					throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DOCUMENTO_NO_ENCONTRADO);
				}
			} else {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBJETO_NO_ES_DEL_TIPO_DOCUMENTO);
			}
		} else {
			// TODO Corregir mensaje de la excepción
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_ID_NOT_FOUND);
		}
		return doc;
	}

	public static final List<FolderTO> getFolderDescendantsById(
			Session session, String folderId)
			throws BovedaPersonalServicesException {
		List<FolderTO> result = null;
		OperationContext op = FolderOperationContext
				.getFolderOperationContext(session);
		try {
			CmisObject object = session.getObject(folderId);
			if (object instanceof Folder) {
				Folder folder = (Folder) object;
				List<Tree<FileableCmisObject>> descendientes = folder
						.getDescendants(BovedaConstants.FOLDER_MAX_DEPTH, op);
				result = createNodes(folderId, descendientes);
			} else {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_OBJETO_NO_ES_DEL_TIPO_CARPETA);
			}
		} catch (CmisObjectNotFoundException e) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA,
							e);
		}
		return result;
	}

	private static List<FolderTO> createNodes(String id,
			List<Tree<FileableCmisObject>> descendientes) {
		int length = descendientes.size();
		List<FolderTO> hijos = new ArrayList<FolderTO>();
		if (length > 0) {
			for (int i = 0; i < length; i++) {
				FileableCmisObject hijo = descendientes.get(i).getItem();
				FolderTO nodoHijo = new FolderTO();
				nodoHijo.setId(hijo.getId());
				nodoHijo.setName(hijo.getName());
				nodoHijo.setPath(createPath(hijo));
				if (hijo instanceof Folder) {
					nodoHijo.setFolder("true");
					nodoHijo.setChildren(createNodes(hijo.getId(),
							descendientes.get(i).getChildren()));
				} else if (hijo instanceof Document) {
					nodoHijo.setFolder("false");
				}
				hijos.add(nodoHijo);
			}
		}
		return hijos;
	}

	/**
	 * M�todo que crea el nombre completo del documento en base a los campos de
	 * DocumentoTO.
	 * 
	 */
	public static String createFolderPath(String usuario, String folio) {
		StringBuilder fileNameSB = new StringBuilder();
		fileNameSB.append("/");
		fileNameSB
				.append(PropertiesServicesUtil
						.getMessage(BodegaPersonalServicesConstants.KEY_ROOT_FOLDER_ALFRESCO));
		if (usuario != null) {
			fileNameSB.append("/");
			fileNameSB.append(usuario);
		}
		if (folio != null) {
			fileNameSB.append("/");
			fileNameSB.append(folio);
		}
		return fileNameSB.toString();
	}

	public static final String getActorId(ActorTO actor) {
		// TODO Utilizar algo mas significativo
		return actor.getId();
	}

	/**
	 * M�todo que crea el mapa de propiedades
	 * 
	 */
	public static final void addPlainProperties(Map<String, Object> properties,
			String name, String type) {
		properties.put(PropertyIds.NAME, name);
		properties.put(PropertyIds.OBJECT_TYPE_ID, type);
	}

	/**
	 * 
	 * @param tram
	 * @return
	 */
	public static final ParOrdenado<String, String>[] createActorPairs(
			ActorTO actor) {
		ParOrdenado<String, String>[] pares = new ParOrdenado[7];

		ParOrdenado<String, String> org = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_USER_ORG, actor.getOrg());
		ParOrdenado<String, String> zona = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_USER_ZONA, actor.getZona());
		ParOrdenado<String, String> tipoId = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_USER_TIPO_ID, actor.getTipoId());
		ParOrdenado<String, String> id = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_USER_ID, actor.getId());
		ParOrdenado<String, String> rol = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_USER_ROL, actor.getRol());

		ParOrdenado<String, String> borrado = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_USER_BORRADO, "false");

		pares[0] = org;
		pares[1] = zona;
		pares[2] = tipoId;
		pares[3] = id;
		pares[4] = rol;
		pares[5] = borrado;

		return pares;
	}

	/**
	 * 
	 * @param tram
	 * @return
	 */
	public static final ParOrdenado<String, String>[] createProcedurePairs(
			TramiteTO tram) {
		ParOrdenado<String, String>[] pares = new ParOrdenado[6];
		int index = 2;
		String org = tram.getOrg();
		if (org != null && !org.isEmpty()) {
			ParOrdenado<String, String> pOrg = new ParOrdenado<String, String>(
					BovedaConstants.IMSS_ORG, org);
			pares[index] = pOrg;
			index++;
		}
		String zona = tram.getZona();
		if (zona != null && !zona.isEmpty()) {
			ParOrdenado<String, String> pZona = new ParOrdenado<String, String>(
					BovedaConstants.IMSS_ZONA, zona);
			pares[index] = pZona;
			index++;
		}
		String tramite = tram.getTramite();
		if (tramite != null && !tramite.isEmpty()) {
			ParOrdenado<String, String> pTramite = new ParOrdenado<String, String>(
					BovedaConstants.IMSS_TRAMITE, tramite);
			pares[index] = pTramite;
			index++;

		}
		String fecha = tram.getFecha();
		if (fecha != null && !fecha.isEmpty()) {
			ParOrdenado<String, String> pFecha = new ParOrdenado<String, String>(
					BovedaConstants.IMSS_FECHA, fecha);
			pares[index] = pFecha;
			index++;
		}
		ParOrdenado<String, String> folio = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_FOLIO, tram.getFolio());
		pares[0] = folio;
		ParOrdenado<String, String> borrado = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_BORRADO, "false");
		pares[1] = borrado;
		return pares;
	}

	/**
	 * 
	 * @param properties
	 * @param actors
	 * @param ext
	 * @throws BovedaPersonalServicesException
	 */
	public static final void addPropertiesMap(Map<String, Object> properties,
			TramiteTO tramite, ActorTO actor, DocumentTO document)
			throws BovedaPersonalServicesException {
		List<String> secondaryTypes = null;
		List<String> actorsList = null;
		String actorId = null;

		secondaryTypes = new ArrayList<String>();

		if (Boolean.valueOf(actor.getIsOwner())) {
			/*
			 * bp:user_org bp:user_zona bp:user_tipo_id bp:user_id bp:user_rol
			 */

			// Se establece propiedades del TRAMITE
			secondaryTypes.add(BovedaConstants.USER_SEC_TYPE_ID);
			// Se agregan tipos secundarios a las propiedades del documento
			properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS,
					secondaryTypes);

			properties.put(BovedaConstants.IMSS_USER_ORG, tramite.getOrg());
			properties.put(BovedaConstants.IMSS_USER_ZONA, tramite.getZona());
			properties
					.put(BovedaConstants.IMSS_USER_TIPO_ID, actor.getTipoId());
			properties.put(BovedaConstants.IMSS_USER_ID, actor.getId());
			properties.put(BovedaConstants.IMSS_USER_ROL, actor.getRol());
			properties.put(BovedaConstants.IMSS_USER_BORRADO, Boolean.FALSE);

		} else {

			// Se establece propiedades del TRAMITE
			secondaryTypes.add(BovedaConstants.PROCEDURE_SEC_TYPE_ID);
			// Se agregan tipos secundarios a las propiedades del documento
			properties.put(PropertyIds.SECONDARY_OBJECT_TYPE_IDS,
					secondaryTypes);
			properties.put(BovedaConstants.IMSS_ORG, tramite.getOrg());
			properties.put(BovedaConstants.IMSS_ZONA, tramite.getZona());
			properties.put(BovedaConstants.IMSS_TRAMITE, tramite.getTramite());
			properties.put(BovedaConstants.IMSS_FECHA, tramite.getFecha());
			properties.put(BovedaConstants.IMSS_FOLIO, tramite.getFolio());

			// Se establece propiedad USUARIO como "dueño"
			actorsList = new ArrayList<String>();
			actorId = ServiceHelper.getActorId(actor);
			actorsList.add(actorId);
			properties.put(BovedaConstants.IMSS_ACTORS, actorsList);
			// Se establece propiedad BORRADO a FALSE
			properties.put(BovedaConstants.IMSS_BORRADO, Boolean.FALSE);
		}

	}

	/**
	 * 
	 * @param name
	 * @param ext
	 * @return
	 */
	public static String getExtension(String name) {
		int index = name.lastIndexOf(".");
		return name.substring(index + 1);
	}

	public static List<DocumentResponseTO> fillDocumentResponseTOList(
			List<DocumentTO> documentList) {
		List<DocumentResponseTO> result = new ArrayList<DocumentResponseTO>();
		for (DocumentTO document : documentList) {
			DocumentResponseTO temp = new DocumentResponseTO();
			temp.setDocument(document);
			temp.setExitoso(true);
			result.add(temp);
		}
		return result;
	}

	public static List<DocumentResponseTO> fillDocumentResponseTOListFromBaseTO(
			List<BaseObjectTO> documentList) {
		List<DocumentResponseTO> result = new ArrayList<DocumentResponseTO>();
		for (BaseObjectTO document : documentList) {
			DocumentResponseTO temp = new DocumentResponseTO();
			DocumentTO tempDoc = new DocumentTO();
			tempDoc.setId(document.getId());
			tempDoc.setName(document.getName());
			tempDoc.setFolder("false");
			tempDoc.setPath(document.getPath());
			temp.setDocument(tempDoc);
			temp.setExitoso(true);
			result.add(temp);
		}
		return result;
	}

	public static List<FolderResponseTO> fillFolderResponseTOList(
			List<FolderTO> folderList) {
		List<FolderResponseTO> result = null;
		if (folderList != null) {
			result = new ArrayList<FolderResponseTO>();

			for (FolderTO folder : folderList) {
				FolderResponseTO temp = new FolderResponseTO();
				temp.setFolderTO(folder);
				temp.setExitoso(true);
				result.add(temp);
			}
		}
		return result;
	}

	private static String createPath(FileableCmisObject hijo) {
		String org = hijo.getPropertyValue(BovedaConstants.IMSS_ORG);
		String zona = hijo.getPropertyValue(BovedaConstants.IMSS_ZONA);
		String tramite = hijo.getPropertyValue(BovedaConstants.IMSS_TRAMITE);
		// String fecha = hijo.getPropertyValue( BovedaConstants.IMSS_FECHA );
		String folio = hijo.getPropertyValue(BovedaConstants.IMSS_FOLIO);
		StringBuilder sb = new StringBuilder();
		if (org != null) {
			sb.append(org);
		}
		sb.append("/");
		if (zona != null) {
			sb.append(zona);
		}
		sb.append("/");
		if (tramite != null) {
			sb.append(tramite);
		}
		sb.append("/");
		if (folio != null) {
			sb.append(folio);
		}
		return sb.toString();
	}
	
	public static final ParOrdenado<String, String>[] createFPairsName(
			BaseObjectTO baseObject) {
		ParOrdenado<String, String>[] pares = new ParOrdenado[1];

		ParOrdenado<String, String> name = new ParOrdenado<String, String>(
				PropertyIds.NAME, baseObject.getName());
		pares[0] = name;

		return pares;
	}

}
package mx.gob.imss.cit.bp.services.util;

import java.util.ArrayList;
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
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.TramiteTO;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.exceptions.CmisContentAlreadyExistsException;

public class FolderServiceHelper {

	public static final BaseResponseTO createFolder(Session session,
			TramiteTO tramite, BaseObjectTO base, ActorTO actor)
			throws BovedaPersonalServicesException {
		BaseResponseTO response = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;
		String secondaryType = null;
		QueryResult folderResult = null;
		String folderId = null;
		Folder folder = null;
		Folder parent = null;
		boolean owner = Boolean.parseBoolean(actor.getIsOwner());

		if (Boolean.valueOf(actor.getIsOwner())) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}
		String actorId = ServiceHelper.getActorId(actor);
		folderResult = QueryUtil.searchFolder(session, secondaryType, actorId,
				fPairs, sPairs, owner);
		response = new BaseResponseTO();
		if (folderResult != null) {

				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_IS_FOLDER.getId(), (Throwable)null ,"El forder ya existe");
		
		} else {
			// No existe el folder y se debe crear
			if (Boolean.parseBoolean(actor.getIsOwner())) {
				parent = ServiceHelper.getFolderByPath(session,
						"/BovedaPersonal/Usuarios");
				Map<String, Object> properties = new HashMap<String, Object>(32);
				ServiceHelper.addPlainProperties(properties, actor.getId(),
						BovedaConstants.FOLDER_TYPE);
				ServiceHelper
						.addPropertiesMap(properties, tramite, actor, null);
				folder = parent.createFolder(properties);
			} else {

				parent = ServiceHelper.getFolderByPath(session,
						"/BovedaPersonal/Tramites");
				Map<String, Object> properties = new HashMap<String, Object>(32);
				ServiceHelper.addPlainProperties(properties,
						tramite.getFolio(), BovedaConstants.FOLDER_TYPE);
				ServiceHelper
						.addPropertiesMap(properties, tramite, actor, null);
				try {
					folder = parent.createFolder(properties);
				} catch (CmisContentAlreadyExistsException cex) {

					throw BovedaPersonalServicesExceptionBuilder
							.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_TRAMITE_YA_CREADO,
									"Mensaje Error");

				}

			}
			response.setExitoso(true);
		}
		return response;
	}

	public static final List<FolderTO> getFolderDescendants(Session session,
			TramiteTO tramite, ActorTO actor, BaseObjectTO document)
			throws BovedaPersonalServicesException {
		List<FolderTO> results = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;
		String secondaryType = null;
		QueryResult folderResult = null;
		String folderId = null;

		if (tramite == null) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}

		folderId = document.getId();
		if (folderId != null && !folderId.equals("")) {
			results = ServiceHelper.getFolderDescendantsById(session, folderId);
		} else {
			// String actorId = ServiceHelper.getActorId(actor);
			folderResult = QueryUtil.searchFolder(session, secondaryType,
					actor, fPairs, sPairs, document);
		}

		if (folderResult != null) {
			folderId = folderResult.getPropertyValueById(PropertyIds.OBJECT_ID);
			if (folderId != null) {
				results = ServiceHelper.getFolderDescendantsById(session,
						folderId);
			} else {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
		} else {

			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
		}
		return results;
	}

	public static final List<BaseObjectTO> getFolderDocuments(Session session,
			TramiteTO tramite, BaseObjectTO base, ActorTO actor)
			throws BovedaPersonalServicesException {
		List<QueryResult> resultList = null;
		// TODO Refactorizar creando un metodo que obtenga el folderId.
		List<BaseObjectTO> documentList = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;
		String secondaryType = null;
		QueryResult folderResult = null;
		String folderId = null;
		if (tramite == null) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}

		folderId = base.getId();
		if (folderId != null && !folderId.equals("")) {
			resultList = QueryUtil.searchDocumentsInFolder(session, folderId);
		} else {
			String actorId = ServiceHelper.getActorId(actor);
			folderResult = QueryUtil.searchFolder(session, secondaryType,
					actorId, fPairs, sPairs);
		}

		if (folderResult != null) {
			StringBuilder sb = new StringBuilder(QueryUtil.D_PREFIX);
			sb.append(PropertyIds.OBJECT_ID);
			folderId = folderResult.getPropertyValueByQueryName(sb.toString());
			if (folderId == null) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
			resultList = QueryUtil.searchDocumentsInFolder(session, folderId);
		}

		List<String> objectIdsList = new ArrayList<String>();

		for (QueryResult qr : resultList) {
			String id = qr.getPropertyValueById(PropertyIds.OBJECT_ID);
			if (id != null && !id.isEmpty()) {
				objectIdsList.add(id);
			}
		}

		resultList = null;
		resultList = QueryUtil.searchObjectsProperties(session, objectIdsList);

		documentList = new ArrayList<BaseObjectTO>();
		for (QueryResult qr : resultList) {
			BaseObjectTO temp = createBaseObjetcTO(qr);
			documentList.add(temp);
		}
		return documentList;
	}

	public static final List<FolderTO> getFolderObjects(Session session,
			TramiteTO tramite, BaseObjectTO base, ActorTO actor)
			throws BovedaPersonalServicesException {
		List<QueryResult> docsList = null;
		List<QueryResult> foldersList = null;
		// TODO Refactorizar creando un metodo que obtenga el folderId.
		List<FolderTO> responseList = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;
		String secondaryType = null;
		QueryResult folderResult = null;
		String folderId = null;

		Boolean isOwner = Boolean.valueOf(actor.getIsOwner());

		if (isOwner) {
			sPairs = ServiceHelper.createActorPairs(actor);
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			sPairs = ServiceHelper.createProcedurePairs(tramite);
			fPairs = ServiceHelper.createFPairsName(base);
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}

		folderId = base.getId();
		if (folderId != null && !folderId.isEmpty()) {
			docsList = QueryUtil.searchDocumentsInFolder(session, folderId);
			foldersList = QueryUtil.searchObjectsInFolder(session, folderId);
		} else {
			String actorId = ServiceHelper.getActorId(actor);
			folderResult = QueryUtil.searchFolder(session, secondaryType,
					actorId, fPairs, sPairs);
		}
		if (folderResult != null) {
			folderId = folderResult.getPropertyValueById(PropertyIds.OBJECT_ID);
			if (folderId == null) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			}
			docsList = QueryUtil.searchDocumentsInFolder(session, folderId);
			foldersList = QueryUtil.searchObjectsInFolder(session, folderId);
		}
		
		if(foldersList==null){
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
		}

		List<String> docIdsList = new ArrayList<String>();
		List<String> folderIdsList = new ArrayList<String>();

		responseList = new ArrayList<FolderTO>();

		if (docsList != null && !docsList.isEmpty()) {
			for (QueryResult qr : docsList) {
				String id = qr.getPropertyValueById(PropertyIds.OBJECT_ID);
				if (id != null && !id.isEmpty()) {
					docIdsList.add(id);
				}
			}

			docsList = null;
			docsList = QueryUtil.searchPropertiesIsFolder(session, docIdsList,
					Boolean.FALSE);

			for (QueryResult qr : docsList) {
				FolderTO temp = createFolderTO(qr, null);
				temp.setFolder("false");
				responseList.add(temp);
			}
		}

		if (foldersList != null && !foldersList.isEmpty()) {
			for (QueryResult qr : foldersList) {
				String id = qr.getPropertyValueById(PropertyIds.OBJECT_ID);
				if (id != null && !id.isEmpty()) {
					folderIdsList.add(id);
				}
			}

			foldersList = null;
			foldersList = QueryUtil.searchPropertiesIsFolder(session,
					folderIdsList, Boolean.TRUE);

			for (QueryResult qr : foldersList) {
				FolderTO temp = createFolderTO(qr, null);
				temp.setFolder("true");
				responseList.add(temp);
			}
		}
		return responseList;
	}

	public static final List<FolderTO> getUserFolder(Session session,
			TramiteTO tramite, ActorTO actor, BaseObjectTO base)
			throws BovedaPersonalServicesException {
		List<FolderTO> resultsList = null;
		String secondaryType = null;
		List<QueryResult> qrList = null;

		if (tramite == null) {
			secondaryType = BovedaConstants.USER_FOLDER_QUERY_NAME;
		} else {
			secondaryType = BovedaConstants.PROCEDURE_QUERY_NAME;
		}

		String actorId = ServiceHelper.getActorId(actor);
		qrList = QueryUtil.searchFolders(session, secondaryType, actorId, null,
				null);
		resultsList = new ArrayList<FolderTO>();
		
		if(qrList.isEmpty()){
			throw BovedaPersonalServicesExceptionBuilder.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_NO_RESULT_FIND_DOCUMENTS);
		}else{
			for(QueryResult temp : qrList){
				resultsList.add( createFolderTO( temp, QueryUtil.D_PREFIX ) );
			}
		}
		return resultsList;
	}

	public static final BaseResponseTO addFolderActor(Session session,
			TramiteTO tramite, BaseObjectTO base, ActorTO actor,
			ActorTO newActor) throws BovedaPersonalServicesException {
		BaseResponseTO response = null;
		// Contiene propiedades de alfresco
		ParOrdenado<String, String>[] fPairs = null;
		// Contiene propiedades de un objeto de tipo secundario
		ParOrdenado<String, String>[] sPairs = null;

		String secondaryType = null;
		QueryResult folderResult = null;
		String folderId = null;
		Folder folder = null;
		List<Object> actorsList = null;

		if (tramite == null) {
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

			sb = new StringBuilder(QueryUtil.I_PREFIX);
			sb.append(BovedaConstants.IMSS_ACTORS);
			actorsList = folderResult.getPropertyMultivalueByQueryName(sb
					.toString());
			if (actorsList != null) {
				actorsList.add(ServiceHelper.getActorId(newActor));
			}
			if (folderId == null) {
				throw BovedaPersonalServicesExceptionBuilder
						.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_CARPETA_NO_ENCONTRADA);
			} else {
				folder = ServiceHelper.getFolderById(session, folderId);
				Map<String, Object> properties = new HashMap<String, Object>();
				properties.put(BovedaConstants.IMSS_ACTORS, actorsList);
				folder.updateProperties(properties);
			}
		}
		response = new BaseResponseTO();
		response.setExitoso(true);
		return response;
	}

	private static BaseObjectTO createBaseObjetcTO(QueryResult qr) {
		// TODO Checar que PropertyIds.BASE_TYPE_ID sea efectivamente el tipo de
		// documento
		BaseObjectTO baseObject = new BaseObjectTO();
		String id = null;
		String name = null;
		String org = null;
		String zona = null;
		String tramite = null;
		String fecha = null;
		String folio = null;

		id = qr.getPropertyValueById(PropertyIds.OBJECT_ID);
		name = qr.getPropertyValueById(PropertyIds.NAME);
		org = qr.getPropertyValueById(BovedaConstants.IMSS_ORG);
		zona = qr.getPropertyValueById(BovedaConstants.IMSS_ZONA);
		tramite = qr.getPropertyValueById(BovedaConstants.IMSS_TRAMITE);
		fecha = qr.getPropertyValueById(BovedaConstants.IMSS_FECHA);
		folio = qr.getPropertyValueById(BovedaConstants.IMSS_FOLIO);

		StringBuilder sb = new StringBuilder(org);
		sb.append("/");
		sb.append(zona);
		sb.append("/");
		sb.append(tramite);
		sb.append("/");
		sb.append(folio);

		baseObject.setId(id);
		baseObject.setName(name);
		baseObject.setPath(sb.toString());
		return baseObject;
	}

	private static FolderTO createFolderTO(QueryResult qr, String fPrefix) {
		// TODO Checar que PropertyIds.BASE_TYPE_ID sea efectivamente el tipo de
		// documento
		FolderTO folder = new FolderTO();
		String id = null;
		String name = null;
		String org = null;
		String zona = null;
		String tramite = null;
		String fecha = null;
		String folio = null;
		String customId = null;
		String parentId = null;

		id = qr.getPropertyValueById(PropertyIds.OBJECT_ID);
		name = qr.getPropertyValueById(PropertyIds.NAME);
		org = qr.getPropertyValueById(BovedaConstants.IMSS_ORG);
		zona = qr.getPropertyValueById(BovedaConstants.IMSS_ZONA);
		tramite = qr.getPropertyValueById(BovedaConstants.IMSS_TRAMITE);
		fecha = qr.getPropertyValueById(BovedaConstants.IMSS_FECHA);
		folio = qr.getPropertyValueById(BovedaConstants.IMSS_FOLIO);
		customId = qr.getPropertyValueById(BovedaConstants.IMSS_CUSTOM_ID);
		parentId = qr.getPropertyValueById(PropertyIds.PARENT_ID);

		StringBuilder sb = new StringBuilder(org);
		sb.append("/");
		sb.append(zona);
		sb.append("/");
		sb.append(tramite);
		sb.append("/");
		sb.append(folio);

		folder.setId(id);
		folder.setName(name);
		folder.setFolder("true");
		folder.setPath(sb.toString());
		folder.setCustomId(customId);
		folder.setParentId(parentId);

		return folder;
	}
}

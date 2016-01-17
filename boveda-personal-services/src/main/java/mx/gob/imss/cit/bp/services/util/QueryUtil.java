package mx.gob.imss.cit.bp.services.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import mx.gob.imss.cit.bp.constant.BovedaConstants;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesCodeExceptionEnum;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesException;
import mx.gob.imss.cit.bp.services.exception.BovedaPersonalServicesExceptionBuilder;
import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.MetadataTO;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.QueryStatement;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QueryUtil {

	public static final String D_PREFIX = "d.";
	public static final String I_PREFIX = "i.";

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(QueryUtil.class);

	public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * 
	 * @param type
	 * @param secondaryType
	 * @param pares
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	private static StringBuilder createQuery(String properties, String type,
			String secondaryType) throws BovedaPersonalServicesException {
		StringBuilder strQuery = new StringBuilder();
		strQuery.append("SELECT ");
		strQuery.append(properties);
		strQuery.append(" FROM ");
		strQuery.append(type);
		strQuery.append(" ");
		if (secondaryType != null && !secondaryType.isEmpty()) {
			strQuery.append(" AS d JOIN ");
			strQuery.append(secondaryType);
			strQuery.append(" AS i ON d.cmis:objectId = i.cmis:objectId ");
		}
		strQuery.append("WHERE ");
		return strQuery;
	}

	/**
	 * 
	 * @param strQuery
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	private static StringBuilder addINConditions(StringBuilder strQuery,
			String property, List<String> values)
			throws BovedaPersonalServicesException {
		if (values.size() <= 0) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_NO_RESULT_METADATA);
		}

		strQuery.append(property);
		strQuery.append(" IN ");
		strQuery.append("(");
		for (String value : values) {
			strQuery.append("'");
			strQuery.append(value);
			strQuery.append("',");
		}
		strQuery.deleteCharAt(strQuery.length() - 1);
		strQuery.append(")");
		return strQuery;
	}

	/**
	 * 
	 * @param strQuery
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	private static StringBuilder addStringConditions(StringBuilder strQuery,
			String prefix, ParOrdenado<String, String>[] pairs)
			throws BovedaPersonalServicesException {
		if (pairs.length <= 0) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_NO_RESULT_METADATA);
		}

		if (pairs[0] != null && !pairs[0].getValor().isEmpty()) {
			strQuery.append(" AND ");
			strQuery.append(prefix);
			strQuery.append(pairs[0].getPropiedad());
			strQuery.append(" = '");
			strQuery.append(pairs[0].getValor());
			strQuery.append("'");
		}

		for (int i = 1; i < pairs.length; i++) {
			if (pairs[i] != null && !pairs[i].getValor().isEmpty()) {
				strQuery.append(" ");
				strQuery.append(BovedaConstants.AND);
				strQuery.append(" ");
				strQuery.append(prefix);
				strQuery.append(pairs[i].getPropiedad());
				strQuery.append(" = '");
				strQuery.append(pairs[i].getValor());
				strQuery.append("'");
			}
		}
		return strQuery;
	}

	/**
	 * 
	 * @param strQuery
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	private static StringBuilder addBooleanConditions(StringBuilder strQuery,
			String prefix, ParOrdenado<String, String>[] pairs)
			throws BovedaPersonalServicesException {
		if (pairs.length <= 0) {
			throw BovedaPersonalServicesExceptionBuilder
					.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_NO_RESULT_METADATA);
		}
		strQuery.append(prefix);
		strQuery.append(pairs[0].getPropiedad());
		strQuery.append(" = ");
		strQuery.append(pairs[0].getValor());
		return strQuery;
	}

	private static StringBuilder addANYQuery(StringBuilder query,
			String prefix, String property, String value) {
		if (property != null && value != null && !value.isEmpty()) {
			query.append(" '");
			query.append(value);
			query.append("' = ");
			query.append(BovedaConstants.ANY);
			query.append(" ");
			query.append(prefix);
			query.append(property);
		}
		return query;
	}

	/**
	 * 
	 * @param query
	 * @param structure
	 * @param folderId
	 * @return
	 */
	private static StringBuilder addINQuery(StringBuilder query,
			String structure, String folderId) {
		if (folderId != null) {
			query.append(" ");
			query.append(structure);
			query.append("('");
			query.append(folderId);
			query.append("')");
		}
		return query;
	}

	/**
	 * 
	 * @param query
	 * @param propiedad
	 * @param valor
	 * @return
	 */
	private static StringBuilder addNotEqualQuery(StringBuilder query,
			String prefix, String propiedad, String valor) {
		if (propiedad != null && valor != null) {
			query.append(" ");
			if (prefix != null) {
				query.append(prefix);
				query.append(" ");
			}
			query.append(propiedad);
			query.append(" != ");
			query.append(valor);
			query.append(" ");
		}
		return query;
	}

	/**
	 * 
	 * @param results
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	private static final List<QueryResult> query(Session session, String query)
			throws BovedaPersonalServicesException {
		List<QueryResult> resultsList = new ArrayList<QueryResult>();
		QueryStatement stmt = session.createQueryStatement(query);
		ItemIterable<QueryResult> results = stmt.query(false);
		Iterator<QueryResult> iterator = results.iterator();
		while (iterator.hasNext()) {
			QueryResult result = iterator.next();
			resultsList.add(result);
		}
		return resultsList;
	}

	/**
	 * 
	 * @param session
	 * @param secondaryType
	 * @param folderId
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	public static final List<QueryResult> searchFolders(Session session,
			String secondaryType, String actorId,
			ParOrdenado<String, String>[] fPairs,
			ParOrdenado<String, String>[] sPairs, boolean isOwner)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		String query = "";

		if (isOwner) {

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.OBJECT_ID);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.NAME);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_USER_ORG);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_USER_ZONA);

			LOG.debug("#debug0: " + propertiesSb.toString());

			sb = createQuery(propertiesSb.toString(),
					BovedaConstants.FOLDER_TYPE, secondaryType);
			// LOG.debug("#debug1: " + sb.toString());
			// addANYQuery(sb, I_PREFIX, BovedaConstants.IMSS_ACTORS, actorId);
			// LOG.debug("#debug2: " + sb.toString());

			if (fPairs != null) {
				addStringConditions(sb, D_PREFIX, fPairs);
			}
			if (sPairs != null) {
				addStringConditions(sb, I_PREFIX, sPairs);
			}

			query = sb.toString();
			query = query.replaceFirst(" AND ", " ");

		} else {

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.OBJECT_ID);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.NAME);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_ORG);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_ZONA);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_TRAMITE);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_FECHA);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_FOLIO);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}

			propertiesSb.append(BovedaConstants.IMSS_ACTORS);
			sb = createQuery(propertiesSb.toString(),
					BovedaConstants.FOLDER_TYPE, secondaryType);

			addANYQuery(sb, I_PREFIX, BovedaConstants.IMSS_ACTORS, actorId);

			if (fPairs != null) {
				addStringConditions(sb, D_PREFIX, fPairs);
			}
			if (sPairs != null) {
				addStringConditions(sb, I_PREFIX, sPairs);
			}
			query = sb.toString();

		}

		System.out.println("debug: "+query);
		return query(session, query);
	}

	/**
	 * 
	 * @param session
	 * @param secondaryType
	 * @param folderId
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	public static final List<QueryResult> searchFolders(Session session,
			String secondaryType, String actorId,
			ParOrdenado<String, String>[] fPairs,
			ParOrdenado<String, String>[] sPairs)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder();

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(D_PREFIX);
		}

		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(D_PREFIX);
		}
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}
		propertiesSb.append(BovedaConstants.IMSS_ORG);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}
		propertiesSb.append(BovedaConstants.IMSS_ZONA);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}
		propertiesSb.append(BovedaConstants.IMSS_TRAMITE);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}
		propertiesSb.append(BovedaConstants.IMSS_FECHA);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}
		propertiesSb.append(BovedaConstants.IMSS_FOLIO);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}

		propertiesSb.append(BovedaConstants.IMSS_ACTORS);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(I_PREFIX);
		}

		propertiesSb.append(BovedaConstants.IMSS_CUSTOM_ID);
		propertiesSb.append(" ,");

		if (secondaryType != null && !secondaryType.isEmpty()) {
			propertiesSb.append(D_PREFIX);
		}
		propertiesSb.append(PropertyIds.PARENT_ID);

		StringBuilder sb = createQuery(propertiesSb.toString(),
				BovedaConstants.FOLDER_TYPE, secondaryType);

		addANYQuery(sb, I_PREFIX, BovedaConstants.IMSS_ACTORS, actorId);
		LOG.debug("#debug2: " + sb.toString());
		if (fPairs != null) {
			addStringConditions(sb, D_PREFIX, fPairs);
		}
		if (sPairs != null) {
			addStringConditions(sb, I_PREFIX, sPairs);
		}
		String query = sb.toString();
		LOG.debug(query);
		return query(session, query);
	}

	public static final QueryResult searchFolder(Session session,
			String secondaryType, String actorId,
			ParOrdenado<String, String>[] fPairs,
			ParOrdenado<String, String>[] sPairs, boolean isOwner)
			throws BovedaPersonalServicesException {
		List<QueryResult> results = searchFolders(session, secondaryType,
				actorId, fPairs, sPairs, isOwner);
		QueryResult result = null;
		int size = results.size();
		if (results != null && size > 0) {
			// TODO Corregir mensaje de la excepción
			result = results.get(0);
		}
		return result;
	}

	public static final QueryResult searchFolder(Session session,
			String secondaryType, String actorId,
			ParOrdenado<String, String>[] fPairs,
			ParOrdenado<String, String>[] sPairs)
			throws BovedaPersonalServicesException {
		List<QueryResult> results = searchFolders(session, secondaryType,
				actorId, fPairs, sPairs);
		QueryResult result = null;
		int size = results.size();
		if (results != null && size > 0) {
			// TODO Corregir mensaje de la excepción
			result = results.get(0);
		}
		return result;
	}

	public static final List<QueryResult> searchDocumentsInFolder(
			Session session, String folderId)
			throws BovedaPersonalServicesException {
		StringBuffer propertiesSb = new StringBuffer();
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(", ");
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(" ");
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.DOCUMENT_TYPE, null);
		addINQuery(querySb, BovedaConstants.IN_FOLDER, folderId);
		String query = querySb.toString();
		LOG.debug(query);
		return query(session, query);
	}

	public static final List<QueryResult> searchObjectsInFolder(
			Session session, String folderId)
			throws BovedaPersonalServicesException {
		StringBuffer propertiesSb = new StringBuffer();
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(", ");
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(" ");
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.FOLDER_TYPE, null);
		addINQuery(querySb, BovedaConstants.IN_FOLDER, folderId);
		String query = querySb.toString();
		LOG.debug(query);
		return query(session, query);
	}

	public static final List<QueryResult> searchDocumentsById(Session session,
			List<String> docIds) throws BovedaPersonalServicesException {
		StringBuffer propertiesSb = new StringBuffer();
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(", ");
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(" ");
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.FOLDER_TYPE, null);
		addINConditions(querySb, PropertyIds.OBJECT_ID, docIds);
		String query = querySb.toString();
		return query(session, query);
	}

	/**
	 * 
	 * @param session
	 * @param folderId
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	public static final QueryResult searchDocumentInFolder(Session session,
			String folderId, String fileName)
			throws BovedaPersonalServicesException {
		List<QueryResult> results = null;
		QueryResult result = null;
		ParOrdenado[] pares = new ParOrdenado[1];
		ParOrdenado<String, String> par = new ParOrdenado<String, String>(
				PropertyIds.NAME, fileName);
		pares[0] = par;
		StringBuffer propertiesSb = new StringBuffer();
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(" ");
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.DOCUMENT_TYPE, null);
		addINQuery(querySb, BovedaConstants.IN_FOLDER, folderId);
		addStringConditions(querySb, "", pares);
		String query = querySb.toString();
		LOG.debug(query);
		results = query(session, query);
		if (results != null && results.size() == 1) {
			result = results.get(0);
		}
		return result;
	}

	/**
	 * 
	 * @param session
	 * @param folderId
	 * @param pairs
	 * @return
	 * @throws BovedaPersonalServicesException
	 */
	public static final List<QueryResult> searchObjectsProperties(
			Session session, List<String> objectIdList)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder();
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(" ,");
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_ORG);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_ZONA);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_TRAMITE);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_FECHA);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_FOLIO);
		propertiesSb.append(" ");
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.IMSS_TRAMITE, null);
		addINConditions(querySb, PropertyIds.OBJECT_ID, objectIdList);
		String query = querySb.toString();

		LOG.debug(query);
		return query(session, query);
	}

	public static final List<QueryResult> searchDocumentMetadata(
			Session session, String documentId)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder(D_PREFIX);
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(", ");
		propertiesSb.append(D_PREFIX);
		propertiesSb.append(PropertyIds.CREATION_DATE);
		propertiesSb.append(", ");
		propertiesSb.append(D_PREFIX);
		propertiesSb.append(PropertyIds.LAST_MODIFICATION_DATE);
		propertiesSb.append(", ");
		propertiesSb.append(D_PREFIX);
		propertiesSb.append(PropertyIds.VERSION_LABEL);
		// Crea QUERY
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.DOCUMENT_TYPE,
				BovedaConstants.DOCUMENT_SEC_TYPE_ID);
		ParOrdenado<String, String>[] fPairs = new ParOrdenado[1];
		StringBuilder objectIdSb = new StringBuilder(D_PREFIX);
		objectIdSb.append(PropertyIds.OBJECT_ID);
		ParOrdenado<String, String> parId = new ParOrdenado<String, String>(
				objectIdSb.toString(), documentId);
		fPairs[0] = parId;
		addStringConditions(querySb, D_PREFIX, fPairs);
		// Condicion documento NO BORRADO
		ParOrdenado<String, String>[] sPairs = new ParOrdenado[1];
		ParOrdenado<String, String> parBorrado = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_BORRADO, "false");
		sPairs[0] = parBorrado;
		addBooleanConditions(querySb, I_PREFIX, sPairs);
		String query = querySb.toString();
		return query(session, query);
	}

	public static final List<QueryResult> searchActorDocuments(Session session,
			String actorId) throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder(D_PREFIX);
		propertiesSb.append(PropertyIds.OBJECT_ID);
		// Crea QUERY
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.DOCUMENT_TYPE, BovedaConstants.IMSS_ACTORS);
		// TODO NOTA: El JOIN no es necesario. Agrega valor al discriminas
		// folders de documentos.
		addANYQuery(querySb, I_PREFIX, BovedaConstants.IMSS_ACTORS, actorId);
		String query = querySb.toString();
		LOG.debug(query);
		return query(session, query);
	}

	public static final List<QueryResult> searchMetadataInDocs(Session session,
			ParOrdenado<String, String>[] fPairs, List<String> docIdList)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder(D_PREFIX);
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(", ");
		propertiesSb.append(D_PREFIX);
		propertiesSb.append(PropertyIds.CREATION_DATE);
		propertiesSb.append(", ");
		propertiesSb.append(D_PREFIX);
		propertiesSb.append(PropertyIds.LAST_MODIFICATION_DATE);
		propertiesSb.append(", ");
		propertiesSb.append(D_PREFIX);
		propertiesSb.append(PropertyIds.VERSION_LABEL);
		// Crea QUERY
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.DOCUMENT_TYPE,
				BovedaConstants.PROCEDURE_QUERY_NAME);
		addStringConditions(querySb, D_PREFIX, fPairs);
		addINConditions(querySb, PropertyIds.OBJECT_ID, docIdList);
		String query = querySb.toString();
		return query(session, query);
	}

	public static final List<QueryResult> searchByMetadata(Session session,
			String actorId, MetadataTO metadata)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder(D_PREFIX);
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(" ,");
		propertiesSb.append(I_PREFIX);
		propertiesSb.append(BovedaConstants.IMSS_ACTORS);
		propertiesSb.append(" ,");
		propertiesSb.append(I_PREFIX);
		propertiesSb.append(BovedaConstants.IMSS_FECHA);

		// Crea QUERY
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.DOCUMENT_TYPE,
				BovedaConstants.PROCEDURE_QUERY_NAME);
		addANYQuery(querySb, I_PREFIX, BovedaConstants.IMSS_ACTORS, actorId);

		// Condicion documento NO BORRADO
		ParOrdenado<String, String>[] sPairs = new ParOrdenado[2];
		String creationDate = sdf.format(metadata.getFechaCreacion());
		ParOrdenado<String, String> fecha = null;
		if (creationDate != null && !creationDate.isEmpty()) {
			StringBuilder fechaSb = new StringBuilder("'");
			fechaSb.append(creationDate);
			fechaSb.append("'");
			fecha = new ParOrdenado<String, String>(BovedaConstants.IMSS_FECHA,
					fechaSb.toString());
		}
		ParOrdenado<String, String> parBorrado = new ParOrdenado<String, String>(
				BovedaConstants.IMSS_BORRADO, "false");
		sPairs[0] = fecha;
		sPairs[1] = parBorrado;
		querySb.append(" AND ");
		addBooleanConditions(querySb, I_PREFIX, sPairs);
		String query = querySb.toString();
		LOG.debug(query);
		return query(session, query);
	}
	
//	>>jmhn
	public static QueryResult searchFolder(Session session,
			String secondaryType, ActorTO actor,
			ParOrdenado<String, String>[] fPairs,
			ParOrdenado<String, String>[] sPairs,
			BaseObjectTO document)
			throws BovedaPersonalServicesException {

		boolean owner = false;
		List<QueryResult> results=null;
		QueryResult result=null;
		if (actor.getIsOwner().equals("true")) {

			owner = true;
			
			throw BovedaPersonalServicesExceptionBuilder
			.build(BovedaPersonalServicesCodeExceptionEnum.ERROR_DESCONOCIDO,
					"No implementado aun");
			
		} else {
			results = searchFoldersByName(session, secondaryType,
					actor, fPairs, sPairs, document);
			int size = results.size();
			if (results != null && size > 0) {

				result = results.get(0);
			}
			
		}

		

		return result;
	}
	
	//>> jmhn 
	private static List<QueryResult> searchFoldersByName(Session session,
			String secondaryType, ActorTO actor,
			ParOrdenado<String, String>[] fPairs,
			ParOrdenado<String, String>[] sPairs, BaseObjectTO document) throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		String query = "";

		if (actor.getIsOwner().equals("true")) { 

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.OBJECT_ID);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.NAME);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_USER_ORG);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_USER_ZONA);

			

			sb = createQuery(propertiesSb.toString(),
					BovedaConstants.FOLDER_TYPE, secondaryType);

			if (fPairs != null) {
				addStringConditions(sb, D_PREFIX, fPairs);
			}
			if (sPairs != null) {
				addStringConditions(sb, I_PREFIX, sPairs);
			}

			query = sb.toString();
			query = query.replaceFirst(" AND ", " ");

		} else {

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.OBJECT_ID);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(D_PREFIX);
			}
			propertiesSb.append(PropertyIds.NAME);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_ORG);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_ZONA);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_TRAMITE);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_FECHA);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}
			propertiesSb.append(BovedaConstants.IMSS_FOLIO);
			propertiesSb.append(" ,");

			if (secondaryType != null && !secondaryType.isEmpty()) {
				propertiesSb.append(I_PREFIX);
			}

			propertiesSb.append(BovedaConstants.IMSS_ACTORS);
			sb = createQuery(propertiesSb.toString(),
					BovedaConstants.FOLDER_TYPE, secondaryType);

			addANYQuery(sb, I_PREFIX, BovedaConstants.IMSS_ACTORS, actor.getId());

			if (fPairs != null) {
				addStringConditions(sb, D_PREFIX, fPairs);
			}
			if (sPairs != null) {
				addStringConditions(sb, I_PREFIX, sPairs);
			}
			query = sb.toString();

		}
		
		query=query+" and  i.cmis:name ='"+document.getName()+"'";
		System.out.println("#debug: "+query);
		return query(session, query);
	}
	
	
	public static final List<QueryResult> searchPropertiesIsFolder(
			Session session, List<String> objectIdList, Boolean isFolder)
			throws BovedaPersonalServicesException {
		StringBuilder propertiesSb = new StringBuilder();
		propertiesSb.append(PropertyIds.OBJECT_ID);
		propertiesSb.append(" ,");
		propertiesSb.append(PropertyIds.NAME);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_ORG);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_ZONA);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_TRAMITE);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_FECHA);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_FOLIO);
		propertiesSb.append(" ,");
		propertiesSb.append(BovedaConstants.IMSS_CUSTOM_ID);
		if(isFolder){
			propertiesSb.append(" ,");
			propertiesSb.append(PropertyIds.PARENT_ID);
			propertiesSb.append(" ");
		}else{
			propertiesSb.append(" ");
		}
		StringBuilder querySb = createQuery(propertiesSb.toString(),
				BovedaConstants.IMSS_TRAMITE, null);
		addINConditions(querySb, PropertyIds.OBJECT_ID, objectIdList);
		String query = querySb.toString();
		
		LOG.debug(query);
		return query(session, query);
	}
}
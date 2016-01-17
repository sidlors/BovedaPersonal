package mx.gob.imss.cit.bp.services.util;

import org.apache.chemistry.opencmis.client.api.OperationContext;
import org.apache.chemistry.opencmis.client.api.Session;

public class FolderOperationContext {
	

	private static OperationContext operationContext;
	
	private static FolderOperationContext folderOperationContext = null; 
	
	private FolderOperationContext(Session session){
		FolderOperationContext.operationContext = session.createOperationContext();
		FolderOperationContext.operationContext.setFilterString("cmis:objectId, cmis:name, bp:org, bp:zona, bp:tramite, bp:folio, bp:actors, bp:fecha");
		FolderOperationContext.operationContext.setIncludeAcls(false);
		FolderOperationContext.operationContext.setIncludeAllowableActions(false);
		FolderOperationContext.operationContext.setIncludePolicies(false);
		FolderOperationContext.operationContext.setLoadSecondaryTypeProperties(true);
		FolderOperationContext.operationContext.setRenditionFilterString("");
	}
	
	public static OperationContext getFolderOperationContext(Session session){
		if(FolderOperationContext.folderOperationContext == null){
			folderOperationContext = new FolderOperationContext(session);
		}
		return FolderOperationContext.operationContext;
	}

}

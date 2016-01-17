package mx.gob.imss.cit.bp.ws.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import mx.gob.imss.cit.bp.to.ActorTO;
import mx.gob.imss.cit.bp.to.BaseObjectTO;
import mx.gob.imss.cit.bp.to.BaseResponseTO;
import mx.gob.imss.cit.bp.to.DocumentTO;
import mx.gob.imss.cit.bp.to.FolderTO;
import mx.gob.imss.cit.bp.to.MetadataTO;
import mx.gob.imss.cit.bp.to.TramiteTO;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Actor;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.BaseObject;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.BaseResponse;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Document;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Folder;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Metadata;
import mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.Tramite;

public class MapperUtil {
	
	public static BaseResponse writeBaseResponse(BaseResponseTO to){
		BaseResponse response = new BaseResponse();
		boolean isExitoso = to.isExitoso();
		if( isExitoso ){
			response.setExitoso( true );
		} else {
			response.setExcepcionCausa( to.getExcepcionCausa() );
			response.setExcepcionCodigo( to.getExcepcionCodigo() );
			response.setExcepcionMensaje( to.getExcepcionMensaje() );
		}
		return response;
	}
	
	public static  mx.gob.imss.cit.bp.to.ActorTO extractActor( Actor actor ){
		ActorTO to = new ActorTO();
		to.setOrg( actor.getOrg() );
		to.setZona( actor.getZona() );
		to.setTipoId( actor.getTipoId() );
		to.setIsOwner( actor.isIsOwner() );
		to.setId( actor.getId() );
		to.setRol( actor.getRol() );
		to.setIsOwner( actor.isIsOwner() );
		return to;
	}
	
	public static Actor writeActor( ActorTO to ){
		Actor actor = new Actor();
		actor.setOrg( to.getOrg() );
		actor.setZona( to.getZona() );
		actor.setTipoId( to.getTipoId() );
		actor.setId( to.getId() );
		actor.setRol( to.getRol() );
		return actor;
	}
	
	public static TramiteTO extractTramite (Tramite tramite){
		TramiteTO to = new TramiteTO();
		to.setOrg( tramite.getOrg());
		to.setZona( tramite.getZona() );
		to.setTramite( tramite.getTramite() );
		to.setFecha( tramite.getFecha() );
		to.setFolio( tramite.getFolio() );
		to.setActores( tramite.getActores() );
		return to;
	} 
	
	public static Tramite writeTramite (TramiteTO to){
		Tramite tramite = new Tramite();
		tramite.setOrg( to.getOrg());
		tramite.setZona( to.getZona() );
		tramite.setTramite( to.getTramite() );
		tramite.setFecha( to.getFecha() );
		tramite.setFolio( to.getFolio() );
		for(String actor :  to.getActores() ){
			tramite.getActores().add(actor);			
		}
		return tramite;
	} 
	
	/**
	 * 		<xs:element name="id"	     			type="xs:string" maxOccurs="1" minOccurs="0"/>
			<xs:element name="customId"	     		type="xs:string" maxOccurs="1" minOccurs="0"/>
			<xs:element name="parentId"	     		type="xs:string" maxOccurs="1" minOccurs="0"/>
			<xs:element name="name"     			type="xs:string" maxOccurs="1" minOccurs="1"/>
            <xs:element name="isFolder"       		type="xs:boolean" maxOccurs="1" minOccurs="1"/>
			<xs:element name="path"			       	type="xs:string" maxOccurs="1" minOccurs="0"/>
	 * @param baseObject
	 * @return
	 */
	public static BaseObjectTO extractBaseObject(BaseObject baseObject ){
		BaseObjectTO to = new BaseObjectTO();
		to.setId( baseObject.getId());
		to.setCustomId( baseObject.getCustomId() );
		to.setParentId( baseObject.getParentId() );
		to.setName( baseObject.getName() );
		to.setFolder( baseObject.isIsFolder() );
		to.setPath( baseObject.getPath() );
		return to;
	} 
	
	public static BaseObject writeBaseObject(BaseObjectTO to ){
		BaseObject baseObject = new BaseObject();
		baseObject.setId( to.getId());
		baseObject.setCustomId( to.getCustomId() );
		baseObject.setParentId( to.getParentId() );
		baseObject.setName( to.getName() );
		baseObject.setIsFolder( to.isFolder() );
		baseObject.setPath( baseObject.getPath() );
		return baseObject;
	}
	
	public static DocumentTO extractDocument(Document document ){
		DocumentTO to = new DocumentTO();
		to.setId( document.getId());
		to.setCustomId( document.getCustomId() );
		to.setParentId( document.getParentId() );
		to.setName( document.getName() );
		to.setFolder( document.isIsFolder() );
		to.setPath( document.getPath() );
		to.setContent( document.getContent() );
		to.setMimeType( document.getMimeType() );
		to.setExt( document.getExt() );
		return to;
	} 
	
	public static Document writeDocument(DocumentTO to ){
		Document document = new Document();
		document.setId( to.getId());
		document.setCustomId( to.getCustomId() );
		document.setParentId( to.getParentId() );
		document.setName( to.getName() );
		document.setIsFolder( to.isFolder() );
		document.setPath( to.getPath() );
		document.setContent( to.getContent() );
		document.setMimeType( to.getMimeType() );
		document.setExt( to.getExt() );
		return document;
	} 
	
	public static Folder writeFolder(FolderTO to ){
		List<FolderTO> children = null;
		Folder folder = new Folder();
		folder.setId( to.getId());
		folder.setCustomId( to.getCustomId() );
		folder.setParentId( to.getParentId() );
		folder.setName( to.getName() );
		folder.setIsFolder( to.isFolder() );
		folder.setPath( to.getPath() );
		children = to.getChildren();
		if(children != null){
			for(FolderTO child : children ){
				Folder temp = writeFolder( child );
				folder.getChildren().add( temp );
			}
		}
		return folder;
	} 
	

	
	public static MetadataTO extractMetadata(Metadata metadata ){
		MetadataTO to = new MetadataTO();
		to.setActores( metadata.getActores() );
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			to.setFechaCreacion( sdf.parse( metadata.getFechaCreacion() ) );
			to.setFechaModificacion( sdf.parse( metadata.getFechaModificacion() ));
		} catch(ParseException e){
			
		}
		to.setVersion( metadata.getVersion() );
		return to;
	} 
	
	public static Metadata writeMetadata(MetadataTO to ){
		Metadata metadata = new Metadata();
		metadata.setFechaCreacion( metadata.getFechaCreacion() );
		metadata.setFechaModificacion( metadata.getFechaModificacion());
		metadata.setVersion( metadata.getVersion() );
		return metadata;
	}
}

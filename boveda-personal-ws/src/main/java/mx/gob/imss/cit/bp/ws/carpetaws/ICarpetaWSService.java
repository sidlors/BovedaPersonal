
package mx.gob.imss.cit.bp.ws.carpetaws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import mx.gob.imss.cit.bp.ws.AddFolderActorRequest;
import mx.gob.imss.cit.bp.ws.AddFolderActorResponse;
import mx.gob.imss.cit.bp.ws.CreateFolderRequest;
import mx.gob.imss.cit.bp.ws.CreateFolderResponse;
import mx.gob.imss.cit.bp.ws.FolderDescendantsRequest;
import mx.gob.imss.cit.bp.ws.FolderDescendantsResponse;
import mx.gob.imss.cit.bp.ws.FolderDocumentsRequest;
import mx.gob.imss.cit.bp.ws.FolderDocumentsResponse;
import mx.gob.imss.cit.bp.ws.FolderObjectsRequest;
import mx.gob.imss.cit.bp.ws.FolderObjectsResponse;
import mx.gob.imss.cit.bp.ws.UserFolderRequest;
import mx.gob.imss.cit.bp.ws.UserFolderResponse;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "CarpetaWSService", targetNamespace = "http://ws.bp.cit.imss.gob.mx/CarpetaWS")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    mx.gob.imss.cit.bp.ws.ObjectFactory.class,
    mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema.ObjectFactory.class
})
public interface ICarpetaWSService {


    /**
     * 
     * @param body
     * @return
     *     returns mx.gob.imss.cit.bp.ws.CreateFolderResponse
     */
    @WebMethod
    @WebResult(name = "CreateFolderResponse", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
    public CreateFolderResponse createFolder(
        @WebParam(name = "CreateFolderRequest", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
        CreateFolderRequest body);

    /**
     * 
     * @param body
     * @return
     *     returns mx.gob.imss.cit.bp.ws.UserFolderResponse
     */
    @WebMethod
    @WebResult(name = "UserFolderResponse", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
    public UserFolderResponse getUserFolder(
        @WebParam(name = "UserFolderRequest", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
        UserFolderRequest body);

    /**
     * 
     * @param body
     * @return
     *     returns mx.gob.imss.cit.bp.ws.FolderDocumentsResponse
     */
    @WebMethod
    @WebResult(name = "FolderDocumentsResponse", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
    public FolderDocumentsResponse getFolderDocuments(
        @WebParam(name = "FolderDocumentsRequest", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
        FolderDocumentsRequest body);

    /**
     * 
     * @param body
     * @return
     *     returns mx.gob.imss.cit.bp.ws.FolderObjectsResponse
     */
    @WebMethod
    @WebResult(name = "FolderObjectsResponse", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
    public FolderObjectsResponse getFolderObjects(
        @WebParam(name = "FolderObjectsRequest", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
        FolderObjectsRequest body);

    /**
     * 
     * @param body
     * @return
     *     returns mx.gob.imss.cit.bp.ws.FolderDescendantsResponse
     */
    @WebMethod
    @WebResult(name = "FolderDescendantsResponse", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
    public FolderDescendantsResponse getFolderDescendants(
        @WebParam(name = "FolderDescendantsRequest", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
        FolderDescendantsRequest body);

    /**
     * 
     * @param body
     * @return
     *     returns mx.gob.imss.cit.bp.ws.AddFolderActorResponse
     */
    @WebMethod
    @WebResult(name = "AddFolderActorResponse", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
    public AddFolderActorResponse addFolderActor(
        @WebParam(name = "AddFolderActorRequest", targetNamespace = "http://ws.bp.cit.imss.gob.mx/", partName = "body")
        AddFolderActorRequest body);

}


package mx.gob.imss.cit.bp.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.gob.imss.cit.bp.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddFolderActorRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AddFolderActorRequest");
    private final static QName _FolderDocumentsResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "FolderDocumentsResponse");
    private final static QName _FolderObjectsRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "FolderObjectsRequest");
    private final static QName _AddFolderActorResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AddFolderActorResponse");
    private final static QName _CreateFolderResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "CreateFolderResponse");
    private final static QName _FolderObjectsResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "FolderObjectsResponse");
    private final static QName _CreateFolderRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "CreateFolderRequest");
    private final static QName _UserFolderResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "UserFolderResponse");
    private final static QName _UserFolderRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "UserFolderRequest");
    private final static QName _FolderDocumentsRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "FolderDocumentsRequest");
    private final static QName _FolderDescendantsRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "FolderDescendantsRequest");
    private final static QName _FolderDescendantsResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "FolderDescendantsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.imss.cit.bp.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateFolderRequest }
     * 
     */
    public CreateFolderRequest createCreateFolderRequest() {
        return new CreateFolderRequest();
    }

    /**
     * Create an instance of {@link CreateFolderResponse }
     * 
     */
    public CreateFolderResponse createCreateFolderResponse() {
        return new CreateFolderResponse();
    }

    /**
     * Create an instance of {@link FolderObjectsRequest }
     * 
     */
    public FolderObjectsRequest createFolderObjectsRequest() {
        return new FolderObjectsRequest();
    }

    /**
     * Create an instance of {@link FolderDocumentsRequest }
     * 
     */
    public FolderDocumentsRequest createFolderDocumentsRequest() {
        return new FolderDocumentsRequest();
    }

    /**
     * Create an instance of {@link FolderDescendantsResponse }
     * 
     */
    public FolderDescendantsResponse createFolderDescendantsResponse() {
        return new FolderDescendantsResponse();
    }

    /**
     * Create an instance of {@link UserFolderRequest }
     * 
     */
    public UserFolderRequest createUserFolderRequest() {
        return new UserFolderRequest();
    }

    /**
     * Create an instance of {@link FolderObjectsResponse }
     * 
     */
    public FolderObjectsResponse createFolderObjectsResponse() {
        return new FolderObjectsResponse();
    }

    /**
     * Create an instance of {@link FolderDescendantsRequest }
     * 
     */
    public FolderDescendantsRequest createFolderDescendantsRequest() {
        return new FolderDescendantsRequest();
    }

    /**
     * Create an instance of {@link FolderDocumentsResponse }
     * 
     */
    public FolderDocumentsResponse createFolderDocumentsResponse() {
        return new FolderDocumentsResponse();
    }

    /**
     * Create an instance of {@link UserFolderResponse }
     * 
     */
    public UserFolderResponse createUserFolderResponse() {
        return new UserFolderResponse();
    }

    /**
     * Create an instance of {@link AddFolderActorResponse }
     * 
     */
    public AddFolderActorResponse createAddFolderActorResponse() {
        return new AddFolderActorResponse();
    }

    /**
     * Create an instance of {@link AddFolderActorRequest }
     * 
     */
    public AddFolderActorRequest createAddFolderActorRequest() {
        return new AddFolderActorRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFolderActorRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AddFolderActorRequest")
    public JAXBElement<AddFolderActorRequest> createAddFolderActorRequest(AddFolderActorRequest value) {
        return new JAXBElement<AddFolderActorRequest>(_AddFolderActorRequest_QNAME, AddFolderActorRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderDocumentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "FolderDocumentsResponse")
    public JAXBElement<FolderDocumentsResponse> createFolderDocumentsResponse(FolderDocumentsResponse value) {
        return new JAXBElement<FolderDocumentsResponse>(_FolderDocumentsResponse_QNAME, FolderDocumentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderObjectsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "FolderObjectsRequest")
    public JAXBElement<FolderObjectsRequest> createFolderObjectsRequest(FolderObjectsRequest value) {
        return new JAXBElement<FolderObjectsRequest>(_FolderObjectsRequest_QNAME, FolderObjectsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddFolderActorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AddFolderActorResponse")
    public JAXBElement<AddFolderActorResponse> createAddFolderActorResponse(AddFolderActorResponse value) {
        return new JAXBElement<AddFolderActorResponse>(_AddFolderActorResponse_QNAME, AddFolderActorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateFolderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "CreateFolderResponse")
    public JAXBElement<CreateFolderResponse> createCreateFolderResponse(CreateFolderResponse value) {
        return new JAXBElement<CreateFolderResponse>(_CreateFolderResponse_QNAME, CreateFolderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderObjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "FolderObjectsResponse")
    public JAXBElement<FolderObjectsResponse> createFolderObjectsResponse(FolderObjectsResponse value) {
        return new JAXBElement<FolderObjectsResponse>(_FolderObjectsResponse_QNAME, FolderObjectsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateFolderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "CreateFolderRequest")
    public JAXBElement<CreateFolderRequest> createCreateFolderRequest(CreateFolderRequest value) {
        return new JAXBElement<CreateFolderRequest>(_CreateFolderRequest_QNAME, CreateFolderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserFolderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "UserFolderResponse")
    public JAXBElement<UserFolderResponse> createUserFolderResponse(UserFolderResponse value) {
        return new JAXBElement<UserFolderResponse>(_UserFolderResponse_QNAME, UserFolderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserFolderRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "UserFolderRequest")
    public JAXBElement<UserFolderRequest> createUserFolderRequest(UserFolderRequest value) {
        return new JAXBElement<UserFolderRequest>(_UserFolderRequest_QNAME, UserFolderRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderDocumentsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "FolderDocumentsRequest")
    public JAXBElement<FolderDocumentsRequest> createFolderDocumentsRequest(FolderDocumentsRequest value) {
        return new JAXBElement<FolderDocumentsRequest>(_FolderDocumentsRequest_QNAME, FolderDocumentsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderDescendantsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "FolderDescendantsRequest")
    public JAXBElement<FolderDescendantsRequest> createFolderDescendantsRequest(FolderDescendantsRequest value) {
        return new JAXBElement<FolderDescendantsRequest>(_FolderDescendantsRequest_QNAME, FolderDescendantsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FolderDescendantsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "FolderDescendantsResponse")
    public JAXBElement<FolderDescendantsResponse> createFolderDescendantsResponse(FolderDescendantsResponse value) {
        return new JAXBElement<FolderDescendantsResponse>(_FolderDescendantsResponse_QNAME, FolderDescendantsResponse.class, null, value);
    }

}

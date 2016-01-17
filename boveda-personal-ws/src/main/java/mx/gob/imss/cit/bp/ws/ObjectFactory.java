
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

    private final static QName _DocumentResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "DocumentResponse");
    private final static QName _DocumentRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "DocumentRequest");
    private final static QName _MetadataByDocRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "MetadataByDocRequest");
    private final static QName _AllDocumentVersionsByDocResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AllDocumentVersionsByDocResponse");
    private final static QName _AddDocumentActorRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AddDocumentActorRequest");
    private final static QName _AllMetadataByMetadataRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AllMetadataByMetadataRequest");
    private final static QName _AddDocumentActorResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AddDocumentActorResponse");
    private final static QName _CreateDocumentResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "CreateDocumentResponse");
    private final static QName _AllDocumentVersionsMetadataByDocResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AllDocumentVersionsMetadataByDocResponse");
    private final static QName _DeleteDocumentRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "DeleteDocumentRequest");
    private final static QName _AllDocumentVersionsMetadataByDocRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AllDocumentVersionsMetadataByDocRequest");
    private final static QName _MetadataByDocResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "MetadataByDocResponse");
    private final static QName _AllDocumentVersionsByDocRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AllDocumentVersionsByDocRequest");
    private final static QName _DocumentsByMetadataResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "DocumentsByMetadataResponse");
    private final static QName _DeleteDocumentResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "DeleteDocumentResponse");
    private final static QName _DocumentsByMetadataRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "DocumentsByMetadataRequest");
    private final static QName _AllMetadataByMetadataResponse_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "AllMetadataByMetadataResponse");
    private final static QName _CreateDocumentRequest_QNAME = new QName("http://ws.bp.cit.imss.gob.mx/", "CreateDocumentRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.gob.imss.cit.bp.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AllDocumentVersionsByDocResponse }
     * 
     */
    public AllDocumentVersionsByDocResponse createAllDocumentVersionsByDocResponse() {
        return new AllDocumentVersionsByDocResponse();
    }

    /**
     * Create an instance of {@link CreateDocumentRequest }
     * 
     */
    public CreateDocumentRequest createCreateDocumentRequest() {
        return new CreateDocumentRequest();
    }

    /**
     * Create an instance of {@link CreateDocumentResponse }
     * 
     */
    public CreateDocumentResponse createCreateDocumentResponse() {
        return new CreateDocumentResponse();
    }

    /**
     * Create an instance of {@link AllDocumentVersionsMetadataByDocRequest }
     * 
     */
    public AllDocumentVersionsMetadataByDocRequest createAllDocumentVersionsMetadataByDocRequest() {
        return new AllDocumentVersionsMetadataByDocRequest();
    }

    /**
     * Create an instance of {@link AllDocumentVersionsMetadataByDocResponse }
     * 
     */
    public AllDocumentVersionsMetadataByDocResponse createAllDocumentVersionsMetadataByDocResponse() {
        return new AllDocumentVersionsMetadataByDocResponse();
    }

    /**
     * Create an instance of {@link DeleteDocumentRequest }
     * 
     */
    public DeleteDocumentRequest createDeleteDocumentRequest() {
        return new DeleteDocumentRequest();
    }

    /**
     * Create an instance of {@link AddDocumentActorResponse }
     * 
     */
    public AddDocumentActorResponse createAddDocumentActorResponse() {
        return new AddDocumentActorResponse();
    }

    /**
     * Create an instance of {@link DocumentsByMetadataRequest }
     * 
     */
    public DocumentsByMetadataRequest createDocumentsByMetadataRequest() {
        return new DocumentsByMetadataRequest();
    }

    /**
     * Create an instance of {@link AllMetadataByMetadataRequest }
     * 
     */
    public AllMetadataByMetadataRequest createAllMetadataByMetadataRequest() {
        return new AllMetadataByMetadataRequest();
    }

    /**
     * Create an instance of {@link DocumentResponse }
     * 
     */
    public DocumentResponse createDocumentResponse() {
        return new DocumentResponse();
    }

    /**
     * Create an instance of {@link AllMetadataByMetadataResponse }
     * 
     */
    public AllMetadataByMetadataResponse createAllMetadataByMetadataResponse() {
        return new AllMetadataByMetadataResponse();
    }

    /**
     * Create an instance of {@link MetadataByDocRequest }
     * 
     */
    public MetadataByDocRequest createMetadataByDocRequest() {
        return new MetadataByDocRequest();
    }

    /**
     * Create an instance of {@link DeleteDocumentResponse }
     * 
     */
    public DeleteDocumentResponse createDeleteDocumentResponse() {
        return new DeleteDocumentResponse();
    }

    /**
     * Create an instance of {@link DocumentsByMetadataResponse }
     * 
     */
    public DocumentsByMetadataResponse createDocumentsByMetadataResponse() {
        return new DocumentsByMetadataResponse();
    }

    /**
     * Create an instance of {@link AddDocumentActorRequest }
     * 
     */
    public AddDocumentActorRequest createAddDocumentActorRequest() {
        return new AddDocumentActorRequest();
    }

    /**
     * Create an instance of {@link AllDocumentVersionsByDocRequest }
     * 
     */
    public AllDocumentVersionsByDocRequest createAllDocumentVersionsByDocRequest() {
        return new AllDocumentVersionsByDocRequest();
    }

    /**
     * Create an instance of {@link MetadataByDocResponse }
     * 
     */
    public MetadataByDocResponse createMetadataByDocResponse() {
        return new MetadataByDocResponse();
    }

    /**
     * Create an instance of {@link DocumentRequest }
     * 
     */
    public DocumentRequest createDocumentRequest() {
        return new DocumentRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "DocumentResponse")
    public JAXBElement<DocumentResponse> createDocumentResponse(DocumentResponse value) {
        return new JAXBElement<DocumentResponse>(_DocumentResponse_QNAME, DocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "DocumentRequest")
    public JAXBElement<DocumentRequest> createDocumentRequest(DocumentRequest value) {
        return new JAXBElement<DocumentRequest>(_DocumentRequest_QNAME, DocumentRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadataByDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "MetadataByDocRequest")
    public JAXBElement<MetadataByDocRequest> createMetadataByDocRequest(MetadataByDocRequest value) {
        return new JAXBElement<MetadataByDocRequest>(_MetadataByDocRequest_QNAME, MetadataByDocRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllDocumentVersionsByDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AllDocumentVersionsByDocResponse")
    public JAXBElement<AllDocumentVersionsByDocResponse> createAllDocumentVersionsByDocResponse(AllDocumentVersionsByDocResponse value) {
        return new JAXBElement<AllDocumentVersionsByDocResponse>(_AllDocumentVersionsByDocResponse_QNAME, AllDocumentVersionsByDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDocumentActorRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AddDocumentActorRequest")
    public JAXBElement<AddDocumentActorRequest> createAddDocumentActorRequest(AddDocumentActorRequest value) {
        return new JAXBElement<AddDocumentActorRequest>(_AddDocumentActorRequest_QNAME, AddDocumentActorRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllMetadataByMetadataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AllMetadataByMetadataRequest")
    public JAXBElement<AllMetadataByMetadataRequest> createAllMetadataByMetadataRequest(AllMetadataByMetadataRequest value) {
        return new JAXBElement<AllMetadataByMetadataRequest>(_AllMetadataByMetadataRequest_QNAME, AllMetadataByMetadataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddDocumentActorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AddDocumentActorResponse")
    public JAXBElement<AddDocumentActorResponse> createAddDocumentActorResponse(AddDocumentActorResponse value) {
        return new JAXBElement<AddDocumentActorResponse>(_AddDocumentActorResponse_QNAME, AddDocumentActorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "CreateDocumentResponse")
    public JAXBElement<CreateDocumentResponse> createCreateDocumentResponse(CreateDocumentResponse value) {
        return new JAXBElement<CreateDocumentResponse>(_CreateDocumentResponse_QNAME, CreateDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllDocumentVersionsMetadataByDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AllDocumentVersionsMetadataByDocResponse")
    public JAXBElement<AllDocumentVersionsMetadataByDocResponse> createAllDocumentVersionsMetadataByDocResponse(AllDocumentVersionsMetadataByDocResponse value) {
        return new JAXBElement<AllDocumentVersionsMetadataByDocResponse>(_AllDocumentVersionsMetadataByDocResponse_QNAME, AllDocumentVersionsMetadataByDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocumentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "DeleteDocumentRequest")
    public JAXBElement<DeleteDocumentRequest> createDeleteDocumentRequest(DeleteDocumentRequest value) {
        return new JAXBElement<DeleteDocumentRequest>(_DeleteDocumentRequest_QNAME, DeleteDocumentRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllDocumentVersionsMetadataByDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AllDocumentVersionsMetadataByDocRequest")
    public JAXBElement<AllDocumentVersionsMetadataByDocRequest> createAllDocumentVersionsMetadataByDocRequest(AllDocumentVersionsMetadataByDocRequest value) {
        return new JAXBElement<AllDocumentVersionsMetadataByDocRequest>(_AllDocumentVersionsMetadataByDocRequest_QNAME, AllDocumentVersionsMetadataByDocRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MetadataByDocResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "MetadataByDocResponse")
    public JAXBElement<MetadataByDocResponse> createMetadataByDocResponse(MetadataByDocResponse value) {
        return new JAXBElement<MetadataByDocResponse>(_MetadataByDocResponse_QNAME, MetadataByDocResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllDocumentVersionsByDocRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AllDocumentVersionsByDocRequest")
    public JAXBElement<AllDocumentVersionsByDocRequest> createAllDocumentVersionsByDocRequest(AllDocumentVersionsByDocRequest value) {
        return new JAXBElement<AllDocumentVersionsByDocRequest>(_AllDocumentVersionsByDocRequest_QNAME, AllDocumentVersionsByDocRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentsByMetadataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "DocumentsByMetadataResponse")
    public JAXBElement<DocumentsByMetadataResponse> createDocumentsByMetadataResponse(DocumentsByMetadataResponse value) {
        return new JAXBElement<DocumentsByMetadataResponse>(_DocumentsByMetadataResponse_QNAME, DocumentsByMetadataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteDocumentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "DeleteDocumentResponse")
    public JAXBElement<DeleteDocumentResponse> createDeleteDocumentResponse(DeleteDocumentResponse value) {
        return new JAXBElement<DeleteDocumentResponse>(_DeleteDocumentResponse_QNAME, DeleteDocumentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DocumentsByMetadataRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "DocumentsByMetadataRequest")
    public JAXBElement<DocumentsByMetadataRequest> createDocumentsByMetadataRequest(DocumentsByMetadataRequest value) {
        return new JAXBElement<DocumentsByMetadataRequest>(_DocumentsByMetadataRequest_QNAME, DocumentsByMetadataRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AllMetadataByMetadataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "AllMetadataByMetadataResponse")
    public JAXBElement<AllMetadataByMetadataResponse> createAllMetadataByMetadataResponse(AllMetadataByMetadataResponse value) {
        return new JAXBElement<AllMetadataByMetadataResponse>(_AllMetadataByMetadataResponse_QNAME, AllMetadataByMetadataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateDocumentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.bp.cit.imss.gob.mx/", name = "CreateDocumentRequest")
    public JAXBElement<CreateDocumentRequest> createCreateDocumentRequest(CreateDocumentRequest value) {
        return new JAXBElement<CreateDocumentRequest>(_CreateDocumentRequest_QNAME, CreateDocumentRequest.class, null, value);
    }

}

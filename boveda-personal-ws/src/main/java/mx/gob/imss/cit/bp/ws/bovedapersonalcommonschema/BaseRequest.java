
package mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import mx.gob.imss.cit.bp.ws.AddDocumentActorRequest;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsByDocRequest;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsMetadataByDocRequest;
import mx.gob.imss.cit.bp.ws.AllMetadataByMetadataRequest;
import mx.gob.imss.cit.bp.ws.CreateDocumentRequest;
import mx.gob.imss.cit.bp.ws.DeleteDocumentRequest;
import mx.gob.imss.cit.bp.ws.DocumentRequest;
import mx.gob.imss.cit.bp.ws.DocumentsByMetadataRequest;
import mx.gob.imss.cit.bp.ws.MetadataByDocRequest;


/**
 * <p>Java class for BaseRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tramite" type="{http://ws.bp.cit.imss.gob.mx/bovedaPersonalCommonSchema}Tramite"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseRequest", propOrder = {
    "tramite"
})
@XmlSeeAlso({
    AllMetadataByMetadataRequest.class,
    DocumentRequest.class,
    MetadataByDocRequest.class,
    AddDocumentActorRequest.class,
    CreateDocumentRequest.class,
    DocumentsByMetadataRequest.class,
    AllDocumentVersionsByDocRequest.class,
    AllDocumentVersionsMetadataByDocRequest.class,
    DeleteDocumentRequest.class
})
public class BaseRequest {

    @XmlElement(name = "Tramite", required = true)
    protected Tramite tramite;

    /**
     * Gets the value of the tramite property.
     * 
     * @return
     *     possible object is
     *     {@link Tramite }
     *     
     */
    public Tramite getTramite() {
        return tramite;
    }

    /**
     * Sets the value of the tramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Tramite }
     *     
     */
    public void setTramite(Tramite value) {
        this.tramite = value;
    }

}

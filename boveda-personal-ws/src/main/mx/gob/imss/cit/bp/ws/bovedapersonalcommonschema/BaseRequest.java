
package mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import mx.gob.imss.cit.bp.ws.AddFolderActorRequest;
import mx.gob.imss.cit.bp.ws.CreateFolderRequest;
import mx.gob.imss.cit.bp.ws.FolderDescendantsRequest;
import mx.gob.imss.cit.bp.ws.FolderDocumentsRequest;
import mx.gob.imss.cit.bp.ws.FolderObjectsRequest;
import mx.gob.imss.cit.bp.ws.UserFolderRequest;


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
    FolderDescendantsRequest.class,
    FolderDocumentsRequest.class,
    UserFolderRequest.class,
    CreateFolderRequest.class,
    AddFolderActorRequest.class,
    FolderObjectsRequest.class
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


package mx.gob.imss.cit.bp.ws.bovedapersonalcommonschema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsByDocResponse;
import mx.gob.imss.cit.bp.ws.AllDocumentVersionsMetadataByDocResponse;
import mx.gob.imss.cit.bp.ws.AllMetadataByMetadataResponse;
import mx.gob.imss.cit.bp.ws.DocumentResponse;
import mx.gob.imss.cit.bp.ws.DocumentsByMetadataResponse;
import mx.gob.imss.cit.bp.ws.MetadataByDocResponse;


/**
 * <p>Java class for BaseResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="excepcionCodigo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="excepcionMensaje" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="excepcionCausa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="exitoso" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseResponse", propOrder = {
    "excepcionCodigo",
    "excepcionMensaje",
    "excepcionCausa",
    "exitoso"
})
@XmlSeeAlso({
    AllDocumentVersionsMetadataByDocResponse.class,
    AllDocumentVersionsByDocResponse.class,
    DocumentResponse.class,
    AllMetadataByMetadataResponse.class,
    DocumentsByMetadataResponse.class,
    MetadataByDocResponse.class
})
public class BaseResponse {

    protected Integer excepcionCodigo;
    protected String excepcionMensaje;
    protected String excepcionCausa;
    protected boolean exitoso;

    /**
     * Gets the value of the excepcionCodigo property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExcepcionCodigo() {
        return excepcionCodigo;
    }

    /**
     * Sets the value of the excepcionCodigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExcepcionCodigo(Integer value) {
        this.excepcionCodigo = value;
    }

    /**
     * Gets the value of the excepcionMensaje property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcepcionMensaje() {
        return excepcionMensaje;
    }

    /**
     * Sets the value of the excepcionMensaje property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcepcionMensaje(String value) {
        this.excepcionMensaje = value;
    }

    /**
     * Gets the value of the excepcionCausa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcepcionCausa() {
        return excepcionCausa;
    }

    /**
     * Sets the value of the excepcionCausa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcepcionCausa(String value) {
        this.excepcionCausa = value;
    }

    /**
     * Gets the value of the exitoso property.
     * 
     */
    public boolean isExitoso() {
        return exitoso;
    }

    /**
     * Sets the value of the exitoso property.
     * 
     */
    public void setExitoso(boolean value) {
        this.exitoso = value;
    }

}

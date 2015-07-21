
package br.com.caelum.estoque.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for buscaItemEstoqueResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="buscaItemEstoqueResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ItemEstoque" type="{http://caelum.com.br/estoquews/v1}itemEstoque" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscaItemEstoqueResponse", propOrder = {
    "itemEstoque"
})
public class BuscaItemEstoqueResponse {

    @XmlElement(name = "ItemEstoque")
    protected ItemEstoque itemEstoque;

    /**
     * Gets the value of the itemEstoque property.
     * 
     * @return
     *     possible object is
     *     {@link ItemEstoque }
     *     
     */
    public ItemEstoque getItemEstoque() {
        return itemEstoque;
    }

    /**
     * Sets the value of the itemEstoque property.
     * 
     * @param value
     *     allowed object is
     *     {@link ItemEstoque }
     *     
     */
    public void setItemEstoque(ItemEstoque value) {
        this.itemEstoque = value;
    }

}

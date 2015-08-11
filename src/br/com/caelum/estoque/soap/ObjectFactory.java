
package br.com.caelum.estoque.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.com.caelum.estoque.soap package. 
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

    private final static QName _BuscaItemEstoque_QNAME = new QName("http://caelum.com.br/estoquews/v1", "buscaItemEstoque");
    private final static QName _BuscaItensEstoqueResponse_QNAME = new QName("http://caelum.com.br/estoquews/v1", "buscaItensEstoqueResponse");
    private final static QName _TokenUsuario_QNAME = new QName("http://caelum.com.br/estoquews/v1", "tokenUsuario");
    private final static QName _BuscaItemEstoqueResponse_QNAME = new QName("http://caelum.com.br/estoquews/v1", "buscaItemEstoqueResponse");
    private final static QName _BuscaItensEstoque_QNAME = new QName("http://caelum.com.br/estoquews/v1", "buscaItensEstoque");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.com.caelum.estoque.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BuscaItemEstoqueResponse }
     * 
     */
    public BuscaItemEstoqueResponse createBuscaItemEstoqueResponse() {
        return new BuscaItemEstoqueResponse();
    }

    /**
     * Create an instance of {@link BuscaItensEstoque }
     * 
     */
    public BuscaItensEstoque createBuscaItensEstoque() {
        return new BuscaItensEstoque();
    }

    /**
     * Create an instance of {@link BuscaItemEstoque }
     * 
     */
    public BuscaItemEstoque createBuscaItemEstoque() {
        return new BuscaItemEstoque();
    }

    /**
     * Create an instance of {@link BuscaItensEstoqueResponse }
     * 
     */
    public BuscaItensEstoqueResponse createBuscaItensEstoqueResponse() {
        return new BuscaItensEstoqueResponse();
    }

    /**
     * Create an instance of {@link ItemEstoque }
     * 
     */
    public ItemEstoque createItemEstoque() {
        return new ItemEstoque();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscaItemEstoque }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://caelum.com.br/estoquews/v1", name = "buscaItemEstoque")
    public JAXBElement<BuscaItemEstoque> createBuscaItemEstoque(BuscaItemEstoque value) {
        return new JAXBElement<BuscaItemEstoque>(_BuscaItemEstoque_QNAME, BuscaItemEstoque.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscaItensEstoqueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://caelum.com.br/estoquews/v1", name = "buscaItensEstoqueResponse")
    public JAXBElement<BuscaItensEstoqueResponse> createBuscaItensEstoqueResponse(BuscaItensEstoqueResponse value) {
        return new JAXBElement<BuscaItensEstoqueResponse>(_BuscaItensEstoqueResponse_QNAME, BuscaItensEstoqueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://caelum.com.br/estoquews/v1", name = "tokenUsuario")
    public JAXBElement<String> createTokenUsuario(String value) {
        return new JAXBElement<String>(_TokenUsuario_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscaItemEstoqueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://caelum.com.br/estoquews/v1", name = "buscaItemEstoqueResponse")
    public JAXBElement<BuscaItemEstoqueResponse> createBuscaItemEstoqueResponse(BuscaItemEstoqueResponse value) {
        return new JAXBElement<BuscaItemEstoqueResponse>(_BuscaItemEstoqueResponse_QNAME, BuscaItemEstoqueResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BuscaItensEstoque }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://caelum.com.br/estoquews/v1", name = "buscaItensEstoque")
    public JAXBElement<BuscaItensEstoque> createBuscaItensEstoque(BuscaItensEstoque value) {
        return new JAXBElement<BuscaItensEstoque>(_BuscaItensEstoque_QNAME, BuscaItensEstoque.class, null, value);
    }

}

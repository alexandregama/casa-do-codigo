
package br.com.caelum.estoque.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EstoqueWS", targetNamespace = "http://caelum.com.br/estoquews/v1")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EstoqueWS {


    /**
     * 
     * @param tokenUsuario
     * @param parameters
     * @return
     *     returns br.com.caelum.estoque.soap.BuscaItensEstoqueResponse
     */
    @WebMethod
    @WebResult(name = "buscaItensEstoqueResponse", targetNamespace = "http://caelum.com.br/estoquews/v1", partName = "parameters")
    public BuscaItensEstoqueResponse buscaItensEstoque(
        @WebParam(name = "buscaItensEstoque", targetNamespace = "http://caelum.com.br/estoquews/v1", partName = "parameters")
        BuscaItensEstoque parameters,
        @WebParam(name = "tokenUsuario", targetNamespace = "http://caelum.com.br/estoquews/v1", header = true, partName = "tokenUsuario")
        String tokenUsuario);

    /**
     * 
     * @param tokenUsuario
     * @param parameters
     * @return
     *     returns br.com.caelum.estoque.soap.BuscaItemEstoqueResponse
     */
    @WebMethod
    @WebResult(name = "buscaItemEstoqueResponse", targetNamespace = "http://caelum.com.br/estoquews/v1", partName = "parameters")
    public BuscaItemEstoqueResponse buscaItemEstoque(
        @WebParam(name = "buscaItemEstoque", targetNamespace = "http://caelum.com.br/estoquews/v1", partName = "parameters")
        BuscaItemEstoque parameters,
        @WebParam(name = "tokenUsuario", targetNamespace = "http://caelum.com.br/estoquews/v1", header = true, partName = "tokenUsuario")
        String tokenUsuario);

}

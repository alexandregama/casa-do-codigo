package br.com.caelum.livraria.modelo;

import java.io.Serializable;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue 
	private Integer id;
	
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	@OneToMany(cascade=CascadeType.PERSIST)
	private Set<ItemCompra> itens;

	@OneToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(unique=true)
	private Pagamento pagamento;

	private boolean temApenasLivrosImpressos() {
		
		for (ItemCompra itemCompra : this.itens) {
			if(!itemCompra.isImpresso()) {
				return false;
			}
		}
		return true;
	}
	
	public String getStatus() {
		return this.pagamento == null ? "INDEFINIDO" : this.pagamento.getStatus();
	}

	public String toXml() {
		try {
			JAXBContext context = JAXBContext.newInstance(Pedido.class);
			Marshaller marshaller = context.createMarshaller();
			StringWriter writer = new StringWriter();
			
			marshaller.marshal(this, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new RuntimeException("Ocorreu um erro ao serializar o Pedido para xml", e);
		}
	}
	
	public void setItens(Set<ItemCompra> itens) {
		this.itens = itens;
	}

	public Set<ItemCompra> getItens() {
		return itens;
	}

	public String getFormato() {
		return this.temApenasLivrosImpressos() ? "impresso" : "ebook";
	}
	
	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).add("itens", itens).add("pagamento", pagamento).toString();
	}
	
}

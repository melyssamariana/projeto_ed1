
package ifound.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "componentes_projetos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ComponentesProjetos.findAll", query = "SELECT c FROM ComponentesProjetos c"),
    @NamedQuery(name = "ComponentesProjetos.findById", query = "SELECT c FROM ComponentesProjetos c WHERE c.id = :id"),
    @NamedQuery(name = "ComponentesProjetos.findByQuantidade", query = "SELECT c FROM ComponentesProjetos c WHERE c.quantidade = :quantidade"),
    @NamedQuery(name = "ComponentesProjetos.findByObservacao", query = "SELECT c FROM ComponentesProjetos c WHERE c.observacao = :observacao")})
public class ComponentesProjetos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantidade")
    private Integer quantidade;
    @Size(max = 2147483647)
    @Column(name = "observacao")
    private String observacao;
    @JoinColumn(name = "id_componentes", referencedColumnName = "id")
    @ManyToOne
    private Componentes idComponentes;
    @JoinColumn(name = "id_projeto", referencedColumnName = "id")
    @ManyToOne
    private Projeto idProjeto;

    
    

    public ComponentesProjetos() {
    }

    public ComponentesProjetos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Componentes getIdComponentes() {
        return idComponentes;
    }

    public void setIdComponentes(Componentes idComponentes) {
        this.idComponentes = idComponentes;
    }

    public Projeto getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(Projeto idProjeto) {
        this.idProjeto = idProjeto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ComponentesProjetos)) {
            return false;
        }
        ComponentesProjetos other = (ComponentesProjetos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id + " - " + idComponentes + " - " + idProjeto;
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iepsa.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author admin
 */
@Entity
@Table(name="TBL_COMMENTAIRE")
public class Commentaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Lob
    @Column(name="COMMENTAIRE")
    private String commentaire;

    @Column(name="DATECOM")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datecom;
    @Column(name="ISAPPROUVE")
    private boolean isapprouve;
    @ManyToOne
    @JoinColumn(name="LOGIN")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDatecom() {
        return datecom;
    }

    public void setDatecom(Date datecom) {
        this.datecom = datecom;
    }

    public boolean getIsapprouve() {
        return isapprouve;
    }

    public void setIsapprouve(boolean isapprouve) {
        this.isapprouve = isapprouve;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.test.entity.Commentaire[ id=" + id + " ]";
    }

}

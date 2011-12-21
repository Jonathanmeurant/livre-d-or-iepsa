/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iepsa.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author admin
 */
@Entity
@Table(name="TBL_UTILISATEUR")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="LOGIN")
    private String login;
    @Column(name="NOM")
    private String nom;
    @Column(name="Email")
    private String Email;
    @Column (name="Prenom")
    private String prenom;
    @Column(name="IsAdmin")
    private boolean isAdmin = new Boolean(false);
    @Column(name="Password")
    private String password;
    @OneToMany(cascade= CascadeType.ALL,mappedBy="user")
    private List<Commentaire> listeCommentaire;
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "org.test.entity.User[ id=" + login + " ]";
    }
    
}

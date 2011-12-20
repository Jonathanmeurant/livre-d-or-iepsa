/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iepsa.jsf;

import be.iepsa.model.Commentaire;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.ejb.EJB;

import be.iepsa.model.User;
import be.iepsa.session.CrudSessionBean;




/**
 *
 * @author admin
 */
@ManagedBean
@SessionScoped
public class LoginBean {

    private String nomUti = "";
    private String mdp = "";
    private Boolean isconnected = new Boolean(false);
    private Boolean isnew = new Boolean(false);
    private User utilisateur = new User();
    private Commentaire com = new Commentaire();
    private String message;
   
    //*
    @EJB //*/ /*
    private CrudSessionBean c;
    // */
    public LoginBean() {
        super();
        /*
        if(c.isNewDb()){
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setIsAdmin(true);
            admin.setEmail("admin@admin.com");
            admin.setNom("admin");
            c.createUser2(admin);
        
        }
         // */
       // setAdmin();
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNomUti() {
        return nomUti;
    }

    public void setNomUti(String nomUti) {
        this.nomUti = nomUti;
    }
    
    public Boolean getIsnew() {
        return isnew;
    }

    public User getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(User utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Commentaire getCom() {
        return com;
    }

    public void setCom(Commentaire com) {
        this.com = com;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String login(){
        if(! isConnected()){
            FacesMessage facesmsg = new FacesMessage("Le nomUtilisateur ou le mot de passe ne sont pas bon !");
            FacesContext.getCurrentInstance().addMessage(null, facesmsg);
        }
        return "";
    }

    public boolean isConnected(){
      //*
       User u = c.getUser(nomUti);
       //User u = em.find(User.class, nomUti);
       
       if(u != null){
           if(nomUti.equals(u.getLogin())&& mdp.equals(u.getPassword())){
                isconnected = true;
           }
       }
       //*/
       return isconnected;
    }
     public String logout(){
        nomUti = "";
        mdp = "";
        isconnected = false;
        return "";
    }
    public String nouveauCompte(){
        isnew = true;
        return "";
    }

    public String nouveauMessage(){
        // enregistrement dans la db via sessionbean
        Boolean test = c.createCommentaire(utilisateur, message);
        if(test){
            FacesMessage facesmsg = new FacesMessage("Message proposé en attente de la validation de l'admin !");
            FacesContext.getCurrentInstance().addMessage(null, facesmsg);
        }
        
        return "";
    }
    public String nouveauMembre(){
        // enregistrement dans la db via sessionbean
        isnew = false;
        c.createUser2(utilisateur);
        FacesMessage facesmsg = new FacesMessage("Utilisateur est créé!");
        FacesContext.getCurrentInstance().addMessage(null, facesmsg);
        return "";
    }

    private void setAdmin() {
         if(c.isNewDb()){
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setIsAdmin(true);
            admin.setEmail("admin@admin.com");
            admin.setNom("admin");
            c.createUser2(admin);
        }
    }
}

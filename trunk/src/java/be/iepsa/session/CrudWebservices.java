/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.iepsa.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import be.iepsa.model.Commentaire;
import be.iepsa.model.User;

/**
 *
 * @author admin
 */
@Stateless
//@WebService(serviceName = "CrudWebservices")
public class CrudWebservices {
    @PersistenceContext(unitName="LivreDOrPU")
    private EntityManager em;

    public User createUser(String login){
        User u= new User();        
        u.setLogin(login);        
        em.persist(u);
        return u;
    }
    public void createUser2(String nom,String prenom,String email,String login,String password,boolean isadmin){
        User u= new User();
        u.setNom(nom);
        u.setPrenom(prenom);
        u.setEmail(email);
        u.setLogin(login);
        u.setPassword(password);
        u.setIsAdmin(isadmin);
        em.persist(u);
    }
    public void createCommentaire(User u){
        Commentaire c = new Commentaire();
        c.setUser(u);
        em.persist(c);
    }


    public void test(String Login){
       User u = createUser(Login);
       createCommentaire(u);
        
    }
    public List<User> getListUsers(){
        return em.createQuery("Select u from User u").getResultList();
    }
    
    public List<Commentaire> getListCommentaires(){
        return em.createQuery("Select c from Commentaire c").getResultList();
    }
}

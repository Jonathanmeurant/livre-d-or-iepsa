/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.iepsa.session;

import be.iepsa.model.Commentaire;
import be.iepsa.model.User;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jonathan
 */
@Stateless
public class CrudSessionBean {
    
    @PersistenceContext(unitName="LivreDOrPU")
    private EntityManager em;

    public User createUser(String login){
        User u= new User();
        u.setLogin(login);
        em.persist(u);
        return u;
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

    public User getUser(String nomUti){
        User u = em.find(User.class, nomUti);
        return u;
    }

    public void createUser2(User u) {
        em.persist(u);
    }

    public boolean createCommentaire(User u, String msg) {
        Date d = new Date();
        
        Commentaire com = new Commentaire();
        com.setCommentaire(msg);
        com.setUser(u);
        com.setIsapprouve(false);
        com.setDatecom(d);
        em.persist(com);
        
        return true;
    }
}

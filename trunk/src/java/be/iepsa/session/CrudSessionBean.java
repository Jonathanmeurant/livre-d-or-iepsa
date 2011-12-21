/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.iepsa.session;

import be.iepsa.model.Commentaire;
import be.iepsa.model.User;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import sun.misc.BASE64Encoder;

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
    
    public List<Commentaire>getCommentListApproved(){

        List<Commentaire> l = new ArrayList<Commentaire>();       
        l=em.createQuery("Select c from Commentaire c").getResultList();
        System.out.println(l.size());
        int i=0;
        while( i<l.size()){
              if (l.get(i).getIsapprouve()==false){
                  l.remove(l.get(i));
                  i--;
              }
              i++;
          }
        return l;
    }
        public List<Commentaire>getCommentListNoApproved(){

        List<Commentaire> l = new ArrayList<Commentaire>();
        l=em.createQuery("Select c from Commentaire c").getResultList();
        System.out.println(l.size());
        int i=0;
        while( i<l.size()){

              if (l.get(i).getIsapprouve()==true){
                  l.remove(l.get(i));
                  i--;
              }
              i++;
          }
        return l;
    }


    public List<Commentaire> getListCommentaires(){
        return em.createQuery("Select c from Commentaire c").getResultList();
    }

    public User getUser(String nomUti){
        User u = em.find(User.class, nomUti);
        return u;
    }

    public void createUser2(User u) {
        // implanter la methode sha1
        String pass= u.getPassword();
        try {
            u.setPassword(encrypt(pass));
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CrudSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CrudSessionBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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

    public boolean isNewDb() {

       User admin = new User();
        try {
            admin = em.find(User.class, "admin");
        } catch(Exception e) {
            return true;
        }
        if(admin == null) {
            return true;
        }
        return false;
    }
    public void update(Commentaire com){
        Commentaire c = em.find(Commentaire.class, com.getId());
        c.setIsapprouve(com.getIsapprouve());
    }
 
    
    public String encrypt(String plaintext) throws NoSuchAlgorithmException, UnsupportedEncodingException 
  {
    MessageDigest md = null;
 
      md = MessageDigest.getInstance("SHA"); 
      md.update(plaintext.getBytes("UTF-8")); 
   
      byte raw[] = md.digest(); 
    String hash = (new BASE64Encoder()).encode(raw); 
    return hash; 
  }
    
    public boolean delUser(String id){
        
        User u = em.find(User.class, id);
        
        if(u.getLogin().equals("admin")||u.getLogin().equals(null)){
            return false;
        }else{
           Query q =  em.createQuery("DELETE FROM User u WHERE u.login < :id");
            q.setParameter("id", id);          
            q.executeUpdate();
            
            return true;
        }
    }
}

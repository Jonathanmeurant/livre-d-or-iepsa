/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package be.iepsa.session;

import be.iepsa.model.Commentaire;
import be.iepsa.model.User;
import java.util.List;

/**
 *
 * @author Jonathan
 */
public interface CrudSessionService {

    public User createUser(String login);
    public void createUser2(User u);
    public void createCommentaire(User u);
    public void test(String Login);
    public List<User> getListUsers();
    public List<Commentaire> getListCommentaires();
    public User getUser(String nomUti);
    public boolean createCommentaire(User u, String msg);
}

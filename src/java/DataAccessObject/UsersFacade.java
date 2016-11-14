/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObject;

import Entidad.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author edixred
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "GeoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public Users getByEmail(String correo) {
        try {
            Query q = getEntityManager().createNamedQuery("Users.findByEmail");
            q.setParameter("email", correo);
            return (Users) q.getSingleResult();
        } catch (Exception e) {
        }
        return null;
    }

    public void register(Users usuario) {
        try {
            Query qu = getEntityManager().createNativeQuery("INSERT INTO users values('" + usuario.getName() + "','" + usuario.getLastname() + "','" + usuario.getOrganitation() + "','" + usuario.getEmail() + "','" + usuario.getPassword() + "')");
            qu.executeUpdate();
        } catch (Exception e) {
        }

    }

    public void cambioContrasenia(String email,String nuevaContrasenia) {
        try {
            Query qu = getEntityManager().createNativeQuery("UPDATE users SET password='"+nuevaContrasenia+"'  WHERE email='"+email+"'");
            qu.executeUpdate();
            
        } catch (Exception e) {
        }

    }
    
}

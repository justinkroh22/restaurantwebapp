package com.restaurant.data;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.models.MenuItems;


@Repository
@Transactional
public class MenuItemsDAO {

	
    //private final PilotRepo pilotRepo;
    private SessionFactory sessionFactory;

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {
        MenuItems m = new MenuItems();
        m.setItemName("Burger");
        m.setDescription("Greazy");
        m.setPrice(100);


        Session session = sessionFactory.openSession();
        session.save(m);
        

    }

    @Autowired
    public MenuItemsDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Menu Items DAO");
        this.sessionFactory = sessionFactory;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(MenuItems m) {
        Session session = sessionFactory.getCurrentSession();
        session.save(m);
    }

    @Transactional(readOnly = true)
    public MenuItems getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (MenuItems) session.get(MenuItems.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<MenuItems> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From MenuItems");
        return hql.list();
    }
	
	
}

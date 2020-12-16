package com.restaurant.data;


import com.restaurant.models.MenuItems;
import com.restaurant.models.Orders;
import com.restaurant.models.Reservations;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

//import javax.annotation.PostConstruct;

import java.util.List;

@Repository
@Transactional
public class ReservationsDAO {

    private SessionFactory sessionFactory;

    
    /*
    @PostConstruct
    @Transactional
    public void initDB(){
        Reservations r = new Reservations();

        r.setCustomer_id(1);
        r.setDate("12/15/2020");
        r.setTime(12);

        Session session = sessionFactory.openSession();
        session.save(r);
    }
    
    */

    @Autowired
    public ReservationsDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Reservation DAO");
        this.sessionFactory = sessionFactory;
    }

    
    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(Reservations r) {
        Session session = sessionFactory.getCurrentSession();
        session.save(r);
    }

    @Transactional(readOnly = true)
    public Reservations getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Reservations) session.get(Reservations.class, id);
    }
    
    
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Reservations> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Reservations");
        return hql.list();
    }
}

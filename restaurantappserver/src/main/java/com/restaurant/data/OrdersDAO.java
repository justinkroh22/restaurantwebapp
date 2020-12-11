package com.restaurant.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.models.Orders;
import com.restaurant.models.MenuItems;


@Repository
@Transactional
public class OrdersDAO {

	
    //private final PilotRepo pilotRepo;
    private SessionFactory sessionFactory;

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {
        Orders o = new Orders();
        MenuItems burger = new MenuItems();
        MenuItems fries = new MenuItems();
        MenuItems coke = new MenuItems();
        
        burger.setItemName("Burger");
        burger.setDescription("Greazy");
        burger.setPrice(100);
        
        fries.setItemName("f");
        fries.setDescription("des");
        fries.setPrice(100);
        
        coke.setItemName("coke");
        coke.setDescription("desc");
        coke.setPrice(100);
        
        
        Set<MenuItems> itemsOrdered = new HashSet<MenuItems>();
        
        itemsOrdered.add(burger);
        itemsOrdered.add(fries);
        itemsOrdered.add(coke);

        o.setOrderType("DELIVERY");
        o.setStatus("PENDING");
        o.setDeliveryAddress("11 LALA LANE");
        o.setBillingAddress("11 LALA LANE");
        o.setCustomer_id(1);
        o.setItemsOrdered(itemsOrdered);

        Session session = sessionFactory.openSession();
        session.save(o);
        

    }

    @Autowired
    public OrdersDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Orders DAO");
        this.sessionFactory = sessionFactory;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(Orders o) {
        Session session = sessionFactory.getCurrentSession();
        session.save(o);
    }

    @Transactional(readOnly = true)
    public Orders getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Orders) session.get(Orders.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Orders> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Orders");
        return hql.list();
    }
	
	
}

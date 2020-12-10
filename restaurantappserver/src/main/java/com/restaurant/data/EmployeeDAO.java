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

import com.restaurant.models.Employee;

@Repository
@Transactional
public class EmployeeDAO {

	private SessionFactory sessionFactory;

    @PostConstruct
    @Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public void initDB() {
        Employee e = new Employee();
        e.setEmail("test5@email.com");
        e.setPassword("testpassword");
        e.setFirstName("test");
        e.setLastName("test");
        e.setAddress("test");
        e.setUser_type("EMPLOYEE");


       // Session session = sessionFactory.openSession();
        //session.save(e);
    }

    @Autowired
    public EmployeeDAO(SessionFactory sessionFactory) {
        System.out.println("Creating Employee DAO");
        this.sessionFactory = sessionFactory;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRES_NEW)
    public void save(Employee e) {
        Session session = sessionFactory.getCurrentSession();
        session.save(e);
    }

    @Transactional(readOnly = true)
    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Employee) session.get(Employee.class, id);
    }

    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public List<Employee> getAll() {
        Session s = sessionFactory.getCurrentSession();
        Query hql = s.createQuery("From Employee");
        return hql.list();
    }
	
}

package com.learning.PracHibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

	public static void main(String[] args) {
		
		Alien a = null;
		
//		a.setAid(101);
//		a.setName("hargoivnd");
//		a.setColor("black");
		
//		a.setAid(102);
//		a.setName("abhinav");
//		a.setColor("green");

		Configuration conf = new Configuration().configure().addAnnotatedClass(Alien.class);
		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory sf = conf.buildSessionFactory(reg);
        Session session1 = sf.openSession();
        session1.beginTransaction();   
        
        Query q1 = session1.createQuery("from Alien where aid=101");
        q1.setCacheable(true);
        a = (Alien)q1.uniqueResult(); 
        
        //a = (Alien) session1.get(Alien.class,101);
        System.out.println(a);
        
        session1.getTransaction().commit();
        session1.close();
        
        Session session2 = sf.openSession();
        session2.beginTransaction(); 
        
        Query q2 = session2.createQuery("from Alien where aid=101");
        q2.setCacheable(true);
        a = (Alien)q2.uniqueResult(); 
        
        //a = (Alien) session2.get(Alien.class,101);
        System.out.println(a);
        
        session2.getTransaction().commit();
        session2.close();
	}
}
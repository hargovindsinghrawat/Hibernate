package com.learning.PracHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class MainPrac {

	public static void main(String[] args) {
		AlienName fullName= new AlienName();
		fullName.setFname("hargovind");
		fullName.setMname("singh");
		fullName.setLname("rawat");
		Alien obj = new Alien();
//		Alien obj = null;
		obj.setAid(2);
		obj.setAname(fullName);
		obj.setColor("black");

		Configuration conf = new Configuration().configure().addAnnotatedClass(Alien.class);

		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        
		try (SessionFactory sf = conf.buildSessionFactory(reg)) {
            try (Session session = sf.openSession()) {
                Transaction tx = session.beginTransaction();

                // Save the entity, Hibernate will handle table creation
                session.save(obj);
                
                //fetch from database
//                obj = (Alien)session.get(Alien.class, 1);

                tx.commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
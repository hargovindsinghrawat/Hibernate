package com.learning.PracHibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class App {

	public static void main(String[] args) {
		
		Laptop laptop = new Laptop();
		laptop.setLid(101);
		laptop.setLname("Dell");
		
		Student s = new Student();
		s.setName("Hargovind");
		s.setRollno(1);
		s.setMarks(50);
		s.getLaptop().add(laptop);
		laptop.getStudent().add(s);

		Configuration conf = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);

		ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        
		try (SessionFactory sf = conf.buildSessionFactory(reg)) {
            try (Session session = sf.openSession()) {
            	
            	session.beginTransaction();

                session.save(laptop);
                session.save(s);
                
                session.getTransaction().commit();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}
}
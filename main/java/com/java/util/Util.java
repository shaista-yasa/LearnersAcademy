package com.java.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.java.details.ClassDetails;
import com.java.details.StudentDetails;
import com.java.details.SubjectDetails;
import com.java.details.TeacherDetails;

public class Util {
	
	private static SessionFactory factory;
	
	public static SessionFactory buildConnection() {
		try {
		
		factory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(StudentDetails.class)	
				.addAnnotatedClass(TeacherDetails.class)
				.addAnnotatedClass(ClassDetails.class)
				.addAnnotatedClass(SubjectDetails.class)
				.buildSessionFactory();
		
		return factory;
		}
		catch(Throwable ex) {
			System.out.println("SessionFactory creation falied");
			ex.printStackTrace();
		}
		return factory;
	}

	public static SessionFactory getSessionFactory() {
		if(factory == null)
			factory = buildConnection();

		return factory;

	}
}

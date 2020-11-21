package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;
import com.revoltcode.demo.entity.Review;
import com.revoltcode.demo.entity.Student;

public class DeleteAdamStudentApplication {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		Session session = factory.getCurrentSession();
		
		try { 
			session.beginTransaction();
			 
			//get the student Dave from the database
			Student student = session.get(Student.class, 2);
			
			if(student != null) {
				System.out.println(">> student: "+student);
				System.out.println(">> courses: "+student.getCourses());
				
				//delete student
				System.out.println("Deleting student");
				session.delete(student);
			}
			 
			//commit transaction
			session.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.close();
			factory.close();
		}
	}
}









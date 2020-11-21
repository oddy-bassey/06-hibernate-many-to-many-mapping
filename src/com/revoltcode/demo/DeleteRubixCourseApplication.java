package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;
import com.revoltcode.demo.entity.Review;
import com.revoltcode.demo.entity.Student;

public class DeleteRubixCourseApplication {

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
			
			//get Rubix course from the database
			Course course = session.get(Course.class, 15);
			
			if(course != null) {
				
				//delete course
				System.out.println(">> Deleting course: "+course);
				
				session.delete(course);
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









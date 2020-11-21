package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;
import com.revoltcode.demo.entity.Review;
import com.revoltcode.demo.entity.Student;

public class AddCourseForAdamApplication {
	
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
			Student student = session.get(Student.class, 3);
			
			if(student != null) {
				System.out.println(">> course: "+student);
				System.out.println(">> course: "+student.getCourses());
			}
			
			//create more courses
			Course course1 = new Course("Rubix cube");
			Course course2 = new Course("Game development");
			Course course3 = new Course("Distributed System");
			Course course4 = new Course("Open Reading");
			
			//add student to courses
			course1.addStudent(student);
			course2.addStudent(student);
			course3.addStudent(student);
			course4.addStudent(student);
			
			//save the courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
			session.save(course4);
			
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









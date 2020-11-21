package com.revoltcode.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.revoltcode.demo.entity.Course;
import com.revoltcode.demo.entity.Instructor;
import com.revoltcode.demo.entity.InstructorDetail;
import com.revoltcode.demo.entity.Review;
import com.revoltcode.demo.entity.Student;

public class CreateCourseAndStudentsApplication {
	
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
			 
			//create some course
			System.out.println("Creating the course and reviews");
			Course course = new Course("Java - becoming a renound programmer");
			
			//saving the course
			System.out.println(">> saving course");
			session.save(course);
			System.out.println(">> course saved");
			
			//create students
			Student student1 = new Student("Dave", "Reed", "davereed@yahoo.com");
			Student student2 = new Student("Richard", "Conny", "richardconny@outlook.com");
			Student student3 = new Student("Adam", "Anthony", "adamanthony@gmail.com");
			
			//add students to the course
			course.addStudent(student1);
			course.addStudent(student2);
			course.addStudent(student3);
			
			//save the students
			System.out.println(">> saving students");
			session.save(student1);
			session.save(student2);
			session.save(student3);
			System.out.println(">> students saved");
			System.out.println(">> Saved students: "+course.getStudents());
			
			session.getTransaction().commit();
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			session.close();
			factory.close();
		}
	}
}

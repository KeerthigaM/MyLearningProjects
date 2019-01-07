package com.service.entityRelationShip;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Department;
import com.entity.Student;

public class OneToMany {
   public static void main(String[] args) 
   {
   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_EntityRelationShip" );
   EntityManager entitymanager = emfactory.createEntityManager( );
   entitymanager.getTransaction( ).begin( );

   //Create Student1 Entity
   Student Student1 = new Student();
   Student1.setSname("Satish");
   Student1.setDeg("BE");

   //Create Student2 Entity
   Student Student2 = new Student();
   Student2.setSname("Krishna");
   Student2.setDeg("MBA");

   //Create Student3 Entity
   Student Student3 = new Student();
   Student3.setSname("Masthanvali");
   Student3.setDeg("ME");

   //Store Student
   entitymanager.persist(Student1);
   entitymanager.persist(Student2);
   entitymanager.persist(Student3);

   //Create Studentlist
   List<Student> studlist = new ArrayList();
   studlist.add(Student1);
   studlist.add(Student2);
   studlist.add(Student3);

   //Create Department Entity
   Department department = new Department();
   department.setName("Development");
   department.setStudentlist(studlist);

   //Store Department
   entitymanager.persist(department);

   entitymanager.getTransaction().commit();
   entitymanager.close();
   emfactory.close();
   }
}

/*OUTPUT:
Select * from department_Id;

Department_Id	Student_Sid
54	        	51
54	        	52
54	        	53

Select * from department;

Id	Name
54	Development

Select * from employee;

Sid	Deg	Sname	       
51	BE	Satish	       
52	MBA	Krishna	       
53	ME	Masthanvali    
*/
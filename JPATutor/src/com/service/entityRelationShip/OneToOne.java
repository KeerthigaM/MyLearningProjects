package com.service.entityRelationShip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Department;
import com.entity.Student;

public class OneToOne {
   public static void main(String[] args) {
   
   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_EntityRelationShip" );
   EntityManager entitymanager = emfactory.createEntityManager( );
   entitymanager.getTransaction( ).begin( );

   //Create Department Entity
   Department department = new Department();
   department.setName("Development");
   //Store Department
   entitymanager.persist(department);

   //Create Student Entity
   Student student = new Student();
   student.setSid(3029);
   student.setSname("Kirthi");
   student.setDeg("BE");
   student.setDepartment(department);
   //Store Student
   entitymanager.persist(student);

   entitymanager.getTransaction().commit();
   entitymanager.close();
   emfactory.close();
   }
}
/*OUTPUT:
Select * from department

Id	Name
301	Development

Select * from employee

Sid	Deg	                Sname	Department_id
302	Technical Writer	Satish	301
*/
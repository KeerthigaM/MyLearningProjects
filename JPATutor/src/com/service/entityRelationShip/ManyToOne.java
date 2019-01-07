package com.service.entityRelationShip;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Department;
import com.entity.Student;

public class ManyToOne {
   public static void main( String[ ] args ) {
   
   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_EntityRelationShip" );
   EntityManager entitymanager = emfactory.createEntityManager( );
   entitymanager.getTransaction( ).begin( );

   //Create Department Entity
   Department department = new Department();
   department.setName("Development");   
   //Store Department
   entitymanager.persist(department);

   //Create Student1 Entity
   Student Student1 = new Student();
   Student1.setSname("Satish");
   Student1.setDeg("ECE");
   Student1.setDepartment(department);
   //Create Student2 Entity
   Student Student2 = new Student();
   Student2.setSname("Krishna");
   Student2.setDeg("CSE");
   Student2.setDepartment(department);
   //Create Student3 Entity
   Student Student3 = new Student();
   Student3.setSname("Masthanvali");
   Student3.setDeg("IT");
   Student3.setDepartment(department);
   //Store Students
   entitymanager.persist(Student1);
   entitymanager.persist(Student2);
   entitymanager.persist(Student3);

   entitymanager.getTransaction().commit();
   entitymanager.close();
   emfactory.close();
   }
}
/*OUTPUT:
Select * from department;

Id	Name
101	Development

Select * from student;

Sid Deg Sname	        Department_Id
102 ECE	Satish	        101
103 CSE	Krishna	        101
104 IT	Masthan Wali	101
*/
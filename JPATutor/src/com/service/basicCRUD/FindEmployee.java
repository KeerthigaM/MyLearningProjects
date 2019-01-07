package com.service.basicCRUD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Employee;

public class FindEmployee {
   public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_Basic" );
      EntityManager entitymanager = emfactory.createEntityManager();
      Employee employee = entitymanager.find( Employee.class, 3029 );

      System.out.println("employee ID = " + employee.getEid( ));
      System.out.println("employee NAME = " + employee.getEname( ));
      System.out.println("employee SALARY = " + employee.getSalary( ));
      System.out.println("employee DESIGNATION = " + employee.getDeg( ));
   }
}
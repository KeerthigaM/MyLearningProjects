package com.service.basicCRUD;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Employee;

public class CreateEmployee {

   public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_Basic" );
      
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );

      Employee employee = new Employee( ); 
      employee.setEid( 3029 );
      employee.setEname( "kirthi" );
      employee.setSalary( 40000 );
      employee.setDeg( "ECE" );
      
      entitymanager.persist( employee );
      
      employee = new Employee( ); 
      employee.setEid( 3024 );
      employee.setEname( "Manju" );
      employee.setSalary( 60000 );
      employee.setDeg( "CSE" );
      
      entitymanager.persist( employee );
      
      employee = new Employee( ); 
      employee.setEid( 2660 );
      employee.setEname( "Shri" );
      employee.setSalary( 100000 );
      employee.setDeg( "IT" );
      
      entitymanager.persist( employee );
      
      entitymanager.getTransaction( ).commit( );

      entitymanager.close( );
      emfactory.close( );
   }
}
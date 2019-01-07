package com.service.simpleJPQL;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.entity.Employee;

public class Ordering {

   public static void main( String[ ] args ) {
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_Basic" );
      EntityManager entitymanager = emfactory.createEntityManager();
      
      //Ordering
      Query query = entitymanager.createQuery( "Select e " + "from Employee e " + "ORDER BY e.eid ASC" );
      List<Employee> list = (List<Employee>)query.getResultList( );
      for( Employee e:list ) {
         System.out.print("Employee ID :" + e.getEid( ));
         System.out.println("\t Employee Name :" + e.getEname( ));
      }
   }
}
/* OUTPUT:
Employee ID :2660	 Employee Name :Shri
Employee ID :3024	 Employee Name :Manju
Employee ID :3029	 Employee Name :kirthi
*/

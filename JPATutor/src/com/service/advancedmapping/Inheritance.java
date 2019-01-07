package com.service.advancedmapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.entity.NonTeachingStaff;
import com.entity.TeachingStaff;

public class Inheritance {
//Single Table strategy, Joined Table strategy , Table Per Class strategy
   public static void main( String[ ] args ) 
   {
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_AdvancedMapping" );
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );

      //Teaching staff entity 
      TeachingStaff ts1=new TeachingStaff(1,"Gopal","MSc MEd","Maths");
      TeachingStaff ts2=new TeachingStaff(2, "Manisha", "BSc BEd", "English");
      
      //Non-Teaching Staff entity
      NonTeachingStaff nts1=new NonTeachingStaff(3, "Satish", "Accounts");
      NonTeachingStaff nts2=new NonTeachingStaff(4, "Krishna", "Office Admin");

      //storing all entities
      entitymanager.persist(ts1);
      entitymanager.persist(ts2);
      entitymanager.persist(nts1);
      entitymanager.persist(nts2);
      
      entitymanager.getTransaction().commit();
      
      entitymanager.close();
      emfactory.close();
   }
}
//before running the program please uncomment something in Staff,TeachingStaff,NonTeachingStaff Classes in entity package
//and delete Staff,TeachingStaff,NonTeachingStaff tables from database to avoid exceptions(primary key problems)
/* Single Table strategy OUTPUT:
Staff Table:
Sid	Type	Sname	Areaexpertise	Qualification	Subjectexpertise
1	TS		Gopal		NULL 			MSC MED			Maths
2	TS		Manisha		NULL			BSC BED			English
3	NS		Satish	    Accounts		NULL			NUL
4	NS		Krishna	    Office Admin	NULL			NUL
*/

/*Joined Table strategy OUTPUT:
Staff Table:
Sid	Dtype				Sname
1	TeachingStaff		Gopal
2	TeachingStaff		Manisha
3	NonTeachingStaff	Satish
4	NonTeachingStaff	Krishna

TeachingStaff Table:
Sid	Qualification	Subjectexpertise
1	MSC MED			Maths
2	BSC BED			English

NonTeachingStaff Table:
Sid	Areaexpertise
3	Accounts
4	Office Admin
*/

/*Table Per Class strategy OUTPUT:
Staff table contains null records.

TeachingStaff Table:
Sid	Qualification	Sname		Subjectexpertise
1	MSC MED			Gopal		Maths
2	BSC BED			Manisha		English

NonTeachingStaff Table:
Sid	Areaexpertise	Sname
3	Accounts		Satish
4	Office Admin	Krishna
*/
 



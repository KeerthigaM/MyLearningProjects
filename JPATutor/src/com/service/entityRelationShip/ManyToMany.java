package com.service.entityRelationShip;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.entity.Clas;
import com.entity.Teacher;

public class ManyToMany {
   public static void main(String[] args) {
   
   EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "JPA_EntityRelationShip" );
   EntityManager entitymanager = emfactory.createEntityManager( );
   entitymanager.getTransaction( ).begin( );

   //Create Clas Entity
   Clas clas1 = new Clas(0, "1st", null);
   Clas clas2 = new Clas(0, "2nd", null);
   Clas clas3 = new Clas(0, "3rd", null);

   //Store Clas
   entitymanager.persist(clas1);
   entitymanager.persist(clas2);
   entitymanager.persist(clas3);

   //Create Clas Set1
   Set<Clas> classSet1 = new HashSet();
   classSet1.add(clas1);
   classSet1.add(clas2);
   classSet1.add(clas3);

   //Create Clas Set2
   Set<Clas> classSet2 = new HashSet();
   classSet2.add(clas3);
   classSet2.add(clas1);
   classSet2.add(clas2);

   //Create Clas Set3
   Set<Clas> classSet3 = new HashSet();
   classSet3.add(clas2);
   classSet3.add(clas3);
   classSet3.add(clas1);

   //Create Teacher Entity
   Teacher teacher1 = new Teacher(0, "Satish","Java",classSet1);
   Teacher teacher2 = new Teacher(0, "Krishna","Adv Java",classSet2);
   Teacher teacher3 = new Teacher(0, "Masthanvali","DB2",classSet3);

   //Store Teacher
   entitymanager.persist(teacher1);
   entitymanager.persist(teacher2);
   entitymanager.persist(teacher3);


   entitymanager.getTransaction( ).commit( );
   entitymanager.close( );
   emfactory.close( );
   }
}
/*OUTPUT:
Select * from teacher_clas;

Teacher _tid	Classet_cid
154	        	151
155	        	151
156	        	151
154	        	152
155	        	152
156	        	152
154	        	153
155	        	153
156	        	153

Select * from teacher;

Tid	Subject	    Tname
154	Java	    Satish
155	Adv Java    Krishna
156	DB2         Masthanvali

Select * from clas;

cid	Cname
151	1st
152	2nd
153	3rd
*/
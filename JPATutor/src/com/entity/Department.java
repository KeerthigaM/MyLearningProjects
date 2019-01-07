package com.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Department {

   @Id 
   @GeneratedValue( strategy=GenerationType.AUTO )
   
   private int id;
   private String name;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getName( ) {
      return name;
   }

   public void setName( String deptName ) {
      this.name = deptName;
   }
 
 //For One-To-Many Relationship
 /*  
   @OneToMany( targetEntity=Student.class )
   private List studentlist;
   
   public List getStudentlist() {
	  return studentlist;
   }
   public void setStudentlist(List studentlist) {
      this.studentlist = studentlist;
   }
*/   
}
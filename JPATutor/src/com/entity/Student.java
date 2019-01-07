package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Student {

   @Id
   @GeneratedValue( strategy= GenerationType.AUTO ) 	
   private int sid;
   private String sname;
   private String deg;  

   public Student(int sid, String sname, String deg) {
      super( );
      this.sid = sid;
      this.sname = sname;
      this.deg = deg;
   }

   public Student( ) {
      super();
   }

   public int getSid( ) {
      return sid;
   }
   
   public void setSid(int sid) {
      this.sid = sid;
   }

   public String getSname( ) {
      return sname;
   }
   
   public void setSname(String sname) {
      this.sname = sname;
   }

   public String getDeg( ) {
      return deg;
   }
   
   public void setDeg(String deg) {
      this.deg = deg;
   }
//For One-To-One & Many-To-One Relationship 
   
   //@OneToOne
   @ManyToOne
   private Department department;
   
   public Department getDepartment() {
      return department;
   }

   public void setDepartment(Department department) {
      this.department = department;
   }

}
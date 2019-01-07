package com.entity;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
//@Inheritance( strategy = InheritanceType.SINGLE_TABLE ) //For Single Table inheritance strategy
//@DiscriminatorColumn( name = "type" ) //For Single Table inheritance strategy
//@Inheritance( strategy = InheritanceType.JOINED ) //For Joined Table inheritance strategy
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS ) // For Table Per Class strategy

public class Staff implements Serializable {
   @Id
   @GeneratedValue( strategy = GenerationType.AUTO )
   
   private int sid;
   private String sname;
   
   public Staff( int sid, String sname ) {
      super( );
      this.sid = sid;
      this.sname = sname;
   }
   
   public Staff( ) {
      super( );
   }
   
   public int getSid( ) {
      return sid;
   }
   
   public void setSid( int sid ) {
      this.sid = sid;
   }
   
   public String getSname( ) {
      return sname;
   }
   
   public void setSname( String sname ) {
      this.sname = sname;
   }
}
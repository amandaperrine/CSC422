/* 
   Author: Amanda Perrine
   Date: 05/11/2021
   Assignment Name: Pet.java
   Program is design to represent possible pets.
*/


public class Pet {
    
    private String name;
    private int age;
    
     Pet(String name, int age){
        this.name = name;
        this.age = age;
    }
     
     public String getName(){
         return name;
     }
     
     public int getAge(){
         return age;
     }
     
     public void setName(String name){
         this.name = name;
     }
    
     public void setAge(int age){
         this.age = age;
     }
    
    
 
}

    
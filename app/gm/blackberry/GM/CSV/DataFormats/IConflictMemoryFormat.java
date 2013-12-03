/*
 * IConflictMemoryFormat.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.DataFormats;


/**
 * This class has Properties which are common to Memory and Conflict Categories
 */
abstract class IConflictMemoryFormat implements IBaseFormat{

 protected String Date;
 protected String Person;
       protected String Description;
  protected String Category;
     
       public IConflictMemoryFormat()
 {
              
       }
      
       public IConflictMemoryFormat(String date, String category, String person, String desc)
 {
              this.Date = date;
              this.Person = person;
          this.Description = desc;
               this.Category = category;
      }
      
       public String GetDate() {
              return Date;
   }
      
       public void SetDate(String date) {
             Date = date;
   }
      
       public String GetPerson() {
            return Person;
 }
      
       public void SetPerson(String person) {
         Person = person;
       }
      
       public String GetDescription() {
               return Description;
    }
      
       public void SetDescription(String description) {
               Description = description;
     }
      
       public String GetCategory()
    {
              return this.Category;
  }
      
       public void SetCategory(String category)
       {
              this.Category = category;
      }
}


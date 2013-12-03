/*
 * MemoryFormat.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.DataFormats;

/**
 * This class shows data format for Memory Category
 */
public class MemoryFormat extends IConflictMemoryFormat{

  private String Type;
   
       public MemoryFormat(String date, String category,String person, String type, String desc)
      {
              super(date,category,person,desc);
              
               this.Type = type;
              
       }
      
       public MemoryFormat(String[] args)
     {
              super();
               
               if(args.length != 5)
                   throw new ArrayIndexOutOfBoundsException("Array length to constructor must be equal to 5");
    
               this.Date = args[0];
           this.Category = args[1];
               this.Person = args[2];
         this.Type = args[3];
           this.Description = args[4];
    }
      
       public void SetMemoryType(String type)
 {
              this.Type = type;
      }
      
       public String GetMemoryType()
  {
              return this.Type;
      }
      
       public String GetData()
        {
              String ColumnDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetColumnDelimiter();
           String RowDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetRowDelimiter();
         String CRLF = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetCRLF();
         
               return this.Date + ColumnDelimiter + this.Category + ColumnDelimiter + this.Person + ColumnDelimiter + this.Type
                         + ColumnDelimiter + this.Description + CRLF + RowDelimiter;
   }
      
}


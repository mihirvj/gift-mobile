/*
 * ConflictFormat.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.DataFormats;

/**
 * This class shows data format for Conflict Category
 */
public class ConflictFormat extends IConflictMemoryFormat{

      private String ConflictReason;
 
       public ConflictFormat(String date, String category, String person, String conflictReason, String desc)
 {
              super(date,category,person,desc);
              
               this.ConflictReason = conflictReason;
          
       }
      
       public ConflictFormat(String[] args)
   {
              super();
               
               if(args.length != 5)
                   throw new ArrayIndexOutOfBoundsException("Array length to constructor must be equal to 5");
    
               this.Date = args[0];
               this.Category = args[1];
               this.Person = args[2];
               this.ConflictReason = args[3];
               this.Description = args[4];
    }
      
       /**
     * Set conflict reason
  */
    public void SetConflictReason(String reason)
   {
              this.ConflictReason = reason;
  }
      
       /**
     * Get conflict reason
  */

   public String GetConflictReason()
      {
              return this.ConflictReason;
    }
      
       /**
     * @see GM.CSV.DataFormats.IBaseFormat#GetData()
        */
    public String GetData()
        {
              String ColumnDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetColumnDelimiter();
           String RowDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetRowDelimiter();
         String CRLF = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetCRLF();
         
               return this.Date + ColumnDelimiter + this.Category + ColumnDelimiter + this.Person + ColumnDelimiter + this.ConflictReason
                               + ColumnDelimiter + this.Description + CRLF + RowDelimiter;
   }
      
}


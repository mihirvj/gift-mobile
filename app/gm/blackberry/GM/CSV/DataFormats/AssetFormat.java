/*
 * AssetFormat.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.DataFormats;


public class AssetFormat implements IBaseFormat{

     private String Member;
 private String Code;
   private String Category;
       
       
       public AssetFormat(String member, String code, String category) {
              super();
               Member = member;
               Code = code;
           Category = category;
   }

     public AssetFormat(String[] args) {
            super();
               
               if(args.length != 3)
                   throw new ArrayIndexOutOfBoundsException("Array length to constructor must be equal to 3");
    
               this.Member = args[0];
         this.Code = args[1];
           this.Category = args[2];
       }

     public void SetMember(String member)
   {
              this.Member = member;
  }
      
       public String GetMember()
      {
              return this.Member;
    }
      
       public void SetCode(String code)
       {
              this.Code = code;
      }
      
       public String GetCode()
        {
              return this.Code;
      }
      
       public void SetCategory(String category)
       {
              this.Category = category;
      }
      
       public String GetCategory()
    {
              return this.Category;
  }
      
       public String GetData()
        {
              String ColumnDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetColumnDelimiter();
           String RowDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetRowDelimiter();
         String CRLF = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetCRLF();
         
               return this.Member + ColumnDelimiter + this.Code + ColumnDelimiter + this.Category 
                            + CRLF + RowDelimiter;
        }
      
}


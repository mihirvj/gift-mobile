/*
 * LoginFormat.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.DataFormats;


public class LoginFormat implements IBaseFormat{

     private String Username;
       private String Password;
       
       public LoginFormat(String username, String password)
   {
              this.Username = username;
              this.Password = password;
      }
      
       public LoginFormat(String[] args) {

           super();
               
               if(args.length != 2)
                   throw new ArrayIndexOutOfBoundsException("Array length to constructor must be equal to 2");
    
               this.Username = args[0];
               this.Password = args[1];
               
               
       }

     public void SetUsername(String user)
   {
              this.Username = user;
  }
      
       public String GetUsername()
    {
              return this.Username;
  }
      
       public void SetPassword(String pass)
   {
              this.Password = pass;
  }
      
       public String GetPassword()
    {
              return this.Password;
  }
      
       public String GetData()
        {
              String ColumnDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetColumnDelimiter();
           String RowDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetRowDelimiter();
         String CRLF = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetCRLF();
         
               return this.Username + ColumnDelimiter + this.Password + CRLF + RowDelimiter;
 }
      
}


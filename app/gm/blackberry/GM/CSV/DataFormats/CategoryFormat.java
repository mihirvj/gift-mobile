/*
 * CategoryFormat.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.DataFormats;

/**
 * Contains common format except Conflict and Memory
 * @author Mihir Joshi
 *
 */
public class CategoryFormat implements IBaseFormat{
     
       private String Date;
   private String Category;
       private String Code;
   private String NumberOfUnits;
  private String Rate;
   private String Amount;
 private String Source;
 private String Remark;
 
       public CategoryFormat()
        {
              
       }
      
       public CategoryFormat(String date, String category, String code,
                       String numberOfUnits, String rate, String amount, String source,
                       String remark) {
               super();
               Date = date;
           Category = category;
           Code = code;
           NumberOfUnits = numberOfUnits;
         Rate = rate;
           Amount = amount;
               Source = source;
               Remark = remark;
       }

     public CategoryFormat(String[] fields) {
               super();
               
               if(fields.length != 8)
                 throw new ArrayIndexOutOfBoundsException("Array length to constructor must be equal to 8");
            
               Date = fields[0];
              Category = fields[1];
          Code = fields[2];
              NumberOfUnits = fields[3];
             Rate = fields[4];
              Amount = fields[5];
            Source = fields[6];
            Remark = fields[7];
    }

     public String GetDate() {
              return Date;
   }
      
       public void SetDate(String date) {
             Date = date;
   }
      
       public String GetCategory() {
          return Category;
       }
      
       public void SetCategory(String category) {
             Category = category;
   }
      
       public String GetCode() {
              return Code;
   }
      
       public void SetCode(String code) {
             Code = code;
   }
      
       public String GetNumberOfUnits() {
             return NumberOfUnits;
  }
      
       public void SetNumberOfUnits(String numberOfUnits) {
           NumberOfUnits = numberOfUnits;
 }
      
       public String GetRate() {
              return Rate;
   }
      
       public void SetRate(String rate) {
             Rate = rate;
   }
      
       public String GetAmount() {
            return Amount;
 }
      
       public void SetAmount(String amount) {
         Amount = amount;
       }
      
       public String GetSource() {
            return Source;
 }
      
       public void SetSource(String source) {
         Source = source;
       }
      
       public String GetRemark() {
            return Remark;
 }
      
       public void SetRemark(String remark) {
         Remark = remark;
       }
      
       /*
      * (non-Javadoc)
        * @see GM.CSV.DataFormats.IBaseFormat#GetData()
        */
    public String GetData()
        {
              String ColumnDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetColumnDelimiter();
           String RowDelimiter = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetRowDelimiter();
            String CRLF = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetCRLF();
            
               return this.GetDate() + ColumnDelimiter + this.GetCategory() +
         ColumnDelimiter + this.GetCode() + ColumnDelimiter + this.GetNumberOfUnits() +
         ColumnDelimiter + this.GetRate() + ColumnDelimiter + this.GetAmount() +
                ColumnDelimiter + this.GetSource() + ColumnDelimiter + this.GetRemark()
                +CRLF + RowDelimiter;
        }
}

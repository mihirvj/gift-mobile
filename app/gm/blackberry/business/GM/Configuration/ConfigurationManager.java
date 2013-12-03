/*
 * ConfigurationManager.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.business.GM.Configuration;



/**
 * This class is used to configure properties used for CSV Operations
 */
public class ConfigurationManager {

    private static String BaseAddress = "";
        private static String Extension = ".csv";
      private static String ColumnDelimiter = ",";
   private static String RowDelimiter = System.getProperty("line.separator");
     
       /**
     * Set Base address. CSV file locations would be relative to this address
       * @param relative address
      */
    public static void SetBaseAddress(String address)
      {
              BaseAddress = address;
 }
      
       /**
     * Get base address
     */
    public static String GetBaseAddress()
  {
              return BaseAddress;
    }
      
       /**
     * Set File Extension. By default it is .csv
    * @param extension 
    */
    public static void SetExtension(String ext)
    {
              Extension = ext;
       }
      
       /**
     * Get extension
        */
    public static String GetExtension()
    {
              return Extension;
      }
      
       /**
     * Set column delimiter. By default it is , (comma) <br>
        * This is used while separating columns in File
        */
    public static void SetColumnDelimiter(String delimiter)
        {
              ColumnDelimiter = delimiter;
   }
      
       /**
     * Get column delimiter
         */
    public static String GetColumnDelimiter()
      {
              return ColumnDelimiter;
        }
      
       /**
     * Set Row Delimiter. By default it is \n or <ENTER>
    * This is used while separating rows in File
   */
    public static void SetRowDelimiter(String delimiter)
   {
              RowDelimiter = delimiter;
      }
      
       /**
     * Get rows delimiter
   */
    public static String GetRowDelimiter()
 {
              return RowDelimiter;
   }
}


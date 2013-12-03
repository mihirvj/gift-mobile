/*
 * BaseCSV.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.IO;

import app.gm.blackberry.GM.Exceptions.CategoryNotSetException;

/**
 * Abstract class containing Common Functionality for reader and writer
 * @author Mihir Joshi
 *
 */
public abstract class BaseCSV {

      protected int category = 0;
    
       private String[] Files = {"income","expense","asset","liability","sales_of_asset","conflict"
                   ,"memories", "login"};
 
       //protected boolean UpdateBit = false;
 
       public BaseCSV()
       {
              
       }
      
       public BaseCSV(int category)
   {
              this.category = category;
      }
      /*
      * Set category.
        * Used while choosing file to operate on
       * @param Categories.INCOME/Categories.EXPENSE...
       */
    public void SetCategory(int category)
  {
              this.category = category;
      }
      
       public int GetCategory()
       {
              return this.category;
  }
      
       protected String GetFilename()
 {
              String base = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetBaseAddress();
          String ext = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetExtension();
             
               if(this.category == 0)
                 throw new CategoryNotSetException();
           
               switch(this.category)
          {
                      case Categories.INCOME:
                                        return base + Files[0] + ext;

                 case Categories.EXPENSE:
                                       return base + Files[1] + ext;

                 case Categories.ASSET:
                                 return base + Files[2] + ext;
                                  
                       case Categories.LIABILITY:
                                     return base + Files[3] + ext;

                 case Categories.SALES_OF_ASSET:
                                        return base + Files[4] + ext;

                 case Categories.CONFLICT:
                                      return base + Files[5] + ext;
                                  
                       case Categories.MEMORIES:
                                      return base + Files[6] + ext;
                  
                       case Categories.LOGIN:
                                 return base + Files[7] + ext;
                                  
                       default:
                                       throw new CategoryNotSetException();
           }
      }
}


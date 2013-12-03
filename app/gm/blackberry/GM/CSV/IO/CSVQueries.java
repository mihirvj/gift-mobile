/*
 * CSVQueries.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.IO;

import net.rim.device.api.collection.util.SparseList;
import java.util.Hashtable;

import app.gm.blackberry.GM.CSV.DataFormats.CategoryFormat;
import app.gm.blackberry.GM.CSV.DataFormats.ConflictFormat;
import app.gm.blackberry.GM.CSV.DataFormats.MemoryFormat;

import app.gm.blackberry.GM.CSV.DataFormats.IBaseFormat;
import app.gm.blackberry.GM.Exceptions.ReaderFailedException;

public class CSVQueries extends BaseCSV{

 private boolean AllMode = false;
       
       public CSVQueries()
    {
              super();
       }
      
       /*public CSVQueries(Categories category)
       {
              super(category);
       }*/
    
       public CSVQueries(int category)
        {
              super(category);
       }
      
       public void SetAllMode(boolean mode)
   {
              this.AllMode = mode;
   }
      
       public Hashtable GroupBy() throws ReaderFailedException
   {
              if(this.AllMode)
               {
                      return GetAllCategoriesData(true);
             }
              else
           {
                      throw new ReaderFailedException("All mode not set..Please set while calling this function");
           }
      }
      
       public Hashtable GroupBy(String subCategory) throws ReaderFailedException
 {
              if(this.AllMode)
               {
                      return GetAllCategoriesData(true);
             }
              else
           {
                      return GetSubCategoryData(subCategory);
                }
      }
      
       private Hashtable GetAllCategoriesData(boolean fromGroupBy) throws ReaderFailedException
  {
              CSVReader reader = new CSVReader(this.category);
               
               SparseList list = null;
            
               Hashtable mapList = new Hashtable();
               
               boolean falseCategory = false;
         
                          switch(this.category)
                          {
                                      case Categories.CONFLICT:
                                                              falseCategory = true;
                                                  break;
                                 case Categories.MEMORIES:
                                                              falseCategory = true;
                                                  break;
                                 case Categories.LOGIN:
                                                         falseCategory = true;
                                                  break;
                                 case Categories.ASSET:
                                                         falseCategory = true;
                                                  break;
                                 default:
                                                      falseCategory = false;
                                                       break;
                         }                       
                               
                               if(falseCategory)
                                      throw new ReaderFailedException("False Category");
                                      
               try
            {
                      
                       list  = reader.Read();
                 
                       for(int i=0;i<list.size();i++)
                 {
                              String groupBy = "";
                           Double amount = new Double(0.0);
                             
                          
                            if(fromGroupBy)
                               groupBy = ((CategoryFormat)list.get(i)).GetDate();
                            else
                               groupBy = ((CategoryFormat)list.get(i)).GetCategory();
                                                 
                            amount = new Double(Double.parseDouble(((CategoryFormat)list.get(i)).GetAmount()));
                                                        
                               
                               if(!mapList.containsKey(groupBy))
                              {
                                      mapList.put(groupBy, amount);
                          }
                              else
                           {
                                      Double prevValue = (Double)mapList.get(groupBy);
                                       
                                       mapList.put(groupBy,  new Double(prevValue.doubleValue() + amount.doubleValue()));
                              }
                      }
                      
                       return mapList;
                        
               }catch(ReaderFailedException rf)
               {
                      String data = (fromGroupBy) ? "all categories" : "category vs amount";
                 
                       throw new ReaderFailedException("Exception while reading " + data + " data : \nMessage : " + rf.getMessage());
                }
      }
      
       private Hashtable GetSubCategoryData(String subCategory) throws ReaderFailedException
     {
              CSVReader reader = new CSVReader(this.category);
               
               SparseList list = null;
            
               Hashtable mapList = new Hashtable();
               
               boolean falseCategory = false;
         
               switch(this.category)
                {
                  case Categories.CONFLICT:
                       falseCategory = true;
                  break;
                  case Categories.MEMORIES:
                       falseCategory = true;
                  break;
                  case Categories.LOGIN:
                       falseCategory = true;
                  break;
                  case Categories.ASSET:
                       falseCategory = true;
                  break;
                  default:
                        falseCategory  =false;                              
                  break;
                }                       
                               
                               if(falseCategory)
                                      throw new ReaderFailedException("False Category");
                             
               try
            {
                      
                       list  = reader.Read();
                 
                 
                       for(int i=0;i<list.size();i++)
                       {
                         String date = "", subCat = "";
                         Double amount = new Double(0.0);
                             
                         date = ((CategoryFormat)list.get(i)).GetDate();
                         subCat = ((CategoryFormat)list.get(i)).GetCategory();
                         amount = new Double(Double.parseDouble(((CategoryFormat)list.get(i)).GetAmount()));
                                                                     
                               
                         if(!mapList.containsKey(date) && subCat.equals(subCategory))
                           {
                                      mapList.put(date, amount);
                           }
                         else if(subCat.equals(subCategory))
                           {
                                      Double prevValue = (Double)mapList.get(date);
                                  
                                      mapList.put(date, new Double(prevValue.doubleValue() + amount.doubleValue()));
                           }
                      }
                      
                       return mapList;
                        
               }catch(ReaderFailedException rf)
               {
                      throw new ReaderFailedException("Exception while reading " + subCategory + " category data : \nMessage : " + rf.getMessage());
         }
      }
      
       public Hashtable GetCategoriesVsAmountData() throws ReaderFailedException
 {
              return GetAllCategoriesData(false);
    }
      
       public SparseList GetUniqueCategories() throws ReaderFailedException
    {
              CSVReader reader = new CSVReader(this.category);
               
               SparseList list = null;
            
               SparseList mapList = new SparseList();
               Hashtable hashList = new Hashtable();
                
               boolean falseCategory = false;
         
               try
            {
                      
                       list  = reader.Read();
                 
                       for(int i=0;i<list.size();i++)
                 {
                              String groupBy = "";
                           
                               switch(this.category)
                          {
                                      case Categories.CONFLICT:
                                                              groupBy = ((MemoryFormat)list.get(i)).GetCategory();
                                                  break;
                                 case Categories.MEMORIES:
                                                              groupBy = ((ConflictFormat)list.get(i)).GetCategory();
                                                  break;
                                 case Categories.LOGIN:
                                                         falseCategory = true;
                                                  break;
                                 case Categories.ASSET:
                                                         falseCategory = true;
                                                  break;
                                 default:
                                                              groupBy = ((CategoryFormat)list.get(i)).GetCategory();
                                                 
                                                       break;
                         }                       
                               
                               if(falseCategory)
                                      throw new ReaderFailedException("False Category");
                             
                          
                          if(!hashList.containsKey(groupBy))
                          {
                              hashList.put(groupBy,"");
                              mapList.add(groupBy);
                          }
                      }
                      
                       hashList.clear();
                       hashList = null;
                       
                       return mapList;
                        
               }catch(ReaderFailedException rf)
               {
                      throw new ReaderFailedException("Exception while aggregating category data : \nMessage : " + rf.getMessage());
                }
                      
       }
}


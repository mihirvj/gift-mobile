/*
 * RecordQueries.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.controller;

import net.rim.device.api.collection.util.SparseList;
import app.gm.blackberry.GM.CSV.IO.CSVQueries;
import app.gm.blackberry.GM.CSV.IO.CSVReader;
import app.gm.blackberry.GM.CSV.IO.Categories;
import app.gm.blackberry.GM.Exceptions.ReaderFailedException;

public class RecordQueries {

  private static int totalRecords = 0;
   private static int category;
    private static SparseList list = null;
    
    
    public RecordQueries(int cat) 
    {    
        category = cat;
    }

    public static void setCategory(int cat)
    {
        category = cat;
    }
    
    public static int getCategory()
    {
        return category;
    }
    
    public static SparseList getList()
    {
        if(!isEmpty())
            return list;
        
        return null;
    }
    public static void bindData()
    {
        CSVReader reader = new CSVReader(category);
        
        try
        {
            list = reader.Read();
            totalRecords = list.size();
            
        }catch(ReaderFailedException rf)
        {
            System.out.println( "Exception : " + rf.getMessage());
            list = null;
        }
        catch(Exception e)
        {
             System.out.println("Exception : " + e.getMessage());
             list = null;
        }
    }
    
    public static void dismiss()
    {
        
        totalRecords = 0;
        category = 0;
        list = null;
    }
    
    
    public static boolean isEmpty()
    {
        return (list == null) ? true : false;
    }    
    
    
    public static String[] getCategories()
    {
            
        CSVQueries query = new CSVQueries(getCategory());
        
       
       SparseList uniqueCategory = new SparseList();
          
       try {
                  uniqueCategory = query.GetUniqueCategories();
          } catch (ReaderFailedException e) {
                    e.getMessage();
                }
      
       String[] categoriesArray = new String[uniqueCategory.size()];
          
       for (int index = 0; index < uniqueCategory.size(); index++){
                   
               categoriesArray[index] = (String) uniqueCategory.get(index);
           }
      
       return categoriesArray; 
    }
    
    } 



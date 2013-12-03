/*
 * RecordManager.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.controller;

import net.rim.device.api.ui.component.Dialog;

import app.gm.blackberry.GM.CSV.IO.CSVReader;
import app.gm.blackberry.GM.CSV.DataFormats.IBaseFormat;
import app.gm.blackberry.GM.CSV.IO.Categories;
import app.gm.blackberry.GM.Exceptions.ReaderFailedException;
import net.rim.device.api.collection.util.SparseList;
import app.gm.blackberry.GM.CSV.IO.CSVWriter;
/**
 * 
 */
public class RecordManager {
    private static int currentRecordIndex = 0;
    private static int totalRecords = 0;
    private static int category = 0;
    private static SparseList list = null;
    
    RecordManager(int cat) 
    {    
        category = cat;
    }
    
    public static int getCurrentIndex()
    {
        return (currentRecordIndex + 1);
    }
    
    public static int getTotalRecords()
    {
        return totalRecords;
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
        currentRecordIndex = 0;
        totalRecords = 0;
        category = 0;
        list = null;
    }
    
    public static boolean isRecordPresent(int index)
    {
        try
        {
            list.get(currentRecordIndex);
            return true;
        }
        catch(ArrayIndexOutOfBoundsException a)
        {
            return false;
        }
    }
    
    public static boolean isEmpty()
    {
        return (list == null) ? true : false;
    }    
    
    public static IBaseFormat getFirstRecord()
    {
        if(!isEmpty())
        {
            currentRecordIndex = 0;
            return (IBaseFormat)list.get(0);
        }
        
        return null;
    }
    
    public static IBaseFormat getCurrentRecord()
    {
        if(!isEmpty() && isRecordPresent(currentRecordIndex))
        {
            return (IBaseFormat)list.get(currentRecordIndex);
        }
        
        return null;
    }
    
    public static IBaseFormat getNextRecord()
    {
        int nextRecordIndex = (currentRecordIndex + 1) % totalRecords;
        
        if(!isEmpty() && isRecordPresent(nextRecordIndex))
        {
            currentRecordIndex = nextRecordIndex;
            return (IBaseFormat)list.get(nextRecordIndex);
        }
        
        return null;
    }
    
    public static IBaseFormat getPreviousRecord()
    {
        int prevRecordIndex = 0;
        
        if((currentRecordIndex - 1) < 0)
            prevRecordIndex = totalRecords - 1;
        else
            prevRecordIndex = currentRecordIndex - 1;
            
        if(!isEmpty() && isRecordPresent(prevRecordIndex))
        {
            currentRecordIndex = prevRecordIndex;
            return (IBaseFormat)list.get(prevRecordIndex);
        }
        
        return null;
    }
} 

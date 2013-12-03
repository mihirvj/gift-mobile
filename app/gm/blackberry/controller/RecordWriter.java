/*
 * RecordWriter.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.controller;

import app.gm.blackberry.GM.CSV.IO.CSVReader;
import app.gm.blackberry.GM.CSV.DataFormats.IBaseFormat;
import app.gm.blackberry.GM.CSV.IO.Categories;
import app.gm.blackberry.GM.Exceptions.WriterFailedException;
import net.rim.device.api.collection.util.SparseList;
import app.gm.blackberry.GM.CSV.IO.CSVWriter;
/**
 * 
 */
public class RecordWriter {

    private static int category = 0;
    
    RecordWriter(int cat) 
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
    
    public static void write(IBaseFormat record)
    {
        CSVWriter writer = new CSVWriter(category);
        try
        {
            writer.Write(record);
        }catch(WriterFailedException wf)
        {
            System.out.println("Exception while writing\nDetails : " + wf.getMessage());
        }
        
        
    }
    
    
    
    public static void update(SparseList list)
    {
        CSVWriter writer = new CSVWriter(category);
        try
        {
            writer.Update(list);
        }catch(WriterFailedException wf)
        {
            System.out.println("Exception while updating\nDetails : " + wf.getMessage());
        }
        
     
    }
} 

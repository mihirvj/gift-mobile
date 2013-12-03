/*
 * CSVReader.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.IO;

import java.io.IOException;
import java.io.InputStreamReader;

import app.gm.blackberry.GM.CSV.DataFormats.AssetFormat;
import app.gm.blackberry.GM.CSV.DataFormats.CategoryFormat;
import app.gm.blackberry.GM.CSV.DataFormats.ConflictFormat;
import app.gm.blackberry.GM.CSV.DataFormats.IBaseFormat;
import app.gm.blackberry.GM.CSV.DataFormats.LoginFormat;
import app.gm.blackberry.GM.CSV.DataFormats.MemoryFormat;
import app.gm.blackberry.GM.Exceptions.ReaderFailedException;
import app.gm.blackberry.utils.StringUtilities;

import net.rim.device.api.collection.util.SparseList;

import net.rim.device.api.io.FileInputStream;
import net.rim.device.api.io.File;
import net.rim.device.api.io.FileNotFoundException;
import net.rim.device.api.io.FileOutputStream;
import net.rim.device.api.io.LineReader;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.Connector;
import java.io.DataInputStream;

import app.gm.blackberry.utils.StringUtilities;

/**
 * This class is used for read operations on file
 *  
 * @author Mihir Joshi
 *
 */
public class CSVReader extends BaseCSV{

        /*public CSVReader(Categories category)
        {
              super(category);
       }*/
    
       public CSVReader(int category)
       {
              super(category);
       }
      
       public CSVReader()
     {
              
       }
      /**
     * Used to read
         * @return ArrayList containing all data in file
        * @throws ReaderFailedException
        */
    public SparseList Read() throws ReaderFailedException
      {
              String line;
              String fileName = this.GetFilename();
          
               FileConnection fin = null;
                DataInputStream dis = null;
                
            LineReader br = null;
              
               try {
                          fin = (FileConnection) Connector.open(fileName,Connector.READ);
                          dis = fin.openDataInputStream();
                          
                          br = new LineReader(dis);
                           
                           SparseList list = new SparseList();
                           
                               while((line = new  String(br.readLine()))!= null && !line.equals("")) //rows are separated by newline
                               {
                                    /*if (br.lengthUnreadData() <= 0) 
                                        {
                                            break; 
                                        }
                                      */  
    
                                      String[] attr = StringUtilities.split(line,app.gm.blackberry.GM.Configuration.ConfigurationManager.GetColumnDelimiter());
                                        
                                       switch(this.category)
                                  {
                                         case Categories.CONFLICT:
                                                              list.add(new ConflictFormat(attr));
                                                            break;
                                         case Categories.MEMORIES:
                                                              list.add(new MemoryFormat(attr));
                                                              break;
                                         case Categories.LOGIN:
                                                         list.add(new LoginFormat(attr));
                                                               break;
                                         case Categories.ASSET:
                                                         list.add(new AssetFormat(attr));
                                                               break;
                                         default:
                                                               list.add(new CategoryFormat(attr));
                                                            break;
                                 }                       
                                       attr = null;
                           }
                              
                               //this.UpdateBit = false;
                              
                               /*Used for internal use of application*/

                              return list;
                           
                       }catch(FileNotFoundException e)
                        {
                              System.out.println(e);
                         throw new ReaderFailedException("File " + fileName + " not found.");
                   }
                      catch(IOException ioe)
                 {
                              System.out.println(ioe);
                               throw new ReaderFailedException("Exception while reading " + fileName + " file from Stream\nDetails : " + ioe.getMessage());
                        }
                      finally
                        {
                              try{
                                   
                                       if(fin != null)
                                                fin.close();
                                        
                                        if(dis != null)
                                                dis.close();
                                       
                                       dis = null;
                                       
                                       fin = null;
                                    br = null;
                             }catch(IOException ioe)
                                {
                                      throw new ReaderFailedException("Exception while closing Stream");
                             }
                      }
              
       }

     /**
     * 
     * @param category Category
     * @return All data in file
     * @throws ReaderFailedException
        */
    /*public ArrayList<IBaseFormat> Read(Categories category) throws ReaderFailedException
 {
              this.category = category;
              return Read();
 }*/
    public SparseList Read(int category) throws ReaderFailedException
    {
              this.category = category;
              return Read();
    }
}


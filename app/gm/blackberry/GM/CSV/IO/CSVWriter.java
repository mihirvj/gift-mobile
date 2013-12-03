/*
 * CSVWriter.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.CSV.IO;

import net.rim.device.api.collection.util.SparseList;

import java.io.DataOutputStream;
import java.io.OutputStream;
import net.rim.device.api.io.File;
import net.rim.device.api.io.FileNotFoundException;
import net.rim.device.api.io.FileOutputStream;
import net.rim.device.api.io.FileInfo;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.Connector;
import java.io.IOException;

import app.gm.blackberry.GM.CSV.DataFormats.IBaseFormat;
import app.gm.blackberry.GM.Exceptions.WriterFailedException;

/**
 * This class is used for write operations on file
 * @throws WriterFailedException
 * @author Mihir Joshi
 *
 */
public class CSVWriter extends BaseCSV{

    /*public CSVWriter(Categories category)
        {
              super(category);
       }*/
    
       public CSVWriter(int category)
 {
              super(category);
       }
      
       public CSVWriter()
     {
              
       }
      /**
     * Used to write a new record in file
   * @param object  - new record in any child of IBaseFormat 
     * @throws WriterFailedException
        */
    public void Write(IBaseFormat object) throws WriterFailedException
     {
              
               String fileName = this.GetFilename();
          
               String text = new String();

           text = object.GetData();
               FileConnection file = null;
               
               try
               {
                file =  (FileConnection) Connector.open(fileName, Connector.READ_WRITE);
                
            }catch(IOException ioe)
            {
                throw new WriterFailedException("Exception while opening connector in write");
            }
                
               if(file.exists()) {

                    try{
                            long size = file.fileSize();
                            
                            if(size > 0)
                                file.truncate(size - 1);
                                
                    }catch(IOException ioe){}
            
                               appendText(file, text, true);
                  
               }
              else
           {
                      try {
                          
                                       file.create();
                                       appendText(file, text.toString(), false);
                                      
                              
                               }catch(java.io.IOException ioe) {throw new WriterFailedException("Cannot create file " + fileName + " for Category " + this.category);}

               }
              
               file = null;
           
               /*Used for internal use of application*/
               //this.UpdateBit = true;
       }
      
       /**
     * Used to update existing record
       * @param list - Modified list containing new data
      * @throws WriterFailedException
        */
    public void Update(SparseList list) throws WriterFailedException
   {
              /*if(this.UpdateBit == true) //i.e. first a file is read in a list
                                                                     // then new record is written
                                                                  // then instead of reading new file again 
                                                                     //update operation is performed with old list
                                                                  // then throw exception
                {
                      throw new WriterFailedException("List sent to function is out dated.." +
                                       "You have written a record in between.\nPlease read file again to get modified list. ");
               }*/
    
               /*above section is for internal use of application*/
           String fileName = this.GetFilename();
          
           StringBuffer text = new StringBuffer();
              try
            {
                      for(int i=0;i<list.size();i++)
                    {
                        if(list.get(i) instanceof IBaseFormat)
                        {
                              String str = ((IBaseFormat)list.get(i)).GetData();
                            
                                if(i == list.size() -1)
                                    text.append(str);
                                else
                                    text.append(str.substring(0,str.length() - 1));
                              
                               str = null;
                        }
                        else
                        {
                            throw new WriterFailedException("Data format corrupted.. Should be of IBaseFormat type");
                        }
                    }
                      
               }catch(ArrayIndexOutOfBoundsException Ex)
              {
                      throw new WriterFailedException("List passed is Empty");
               }
              
               String base = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetBaseAddress();
          String ext = app.gm.blackberry.GM.Configuration.ConfigurationManager.GetExtension();
             
               String tempFilename = base + ".temp" + ext;
            
            FileConnection file = null;
            FileConnection oldFile = null;
           
            try
            {
               file = (FileConnection) Connector.open(tempFilename, Connector.READ_WRITE);
               oldFile = (FileConnection) Connector.open(fileName, Connector.READ_WRITE);
            }catch(IOException ioe)
            {
                throw new WriterFailedException("Exception while opening connector in update");
            }
             
               if(file.exists()) {
                       try {
                            file.delete();
                       }catch(java.io.IOException ioe) {throw new WriterFailedException("Cannot delete file " + tempFilename);}
             }
              
               try {
                          
                              file.create();
                              appendText(file, text.toString(), false);
                              
                              
                       }catch(java.io.IOException ioe) {throw new WriterFailedException("Cannot create file " + tempFilename);}

              /*update name*/
               
               try
               {
                    oldFile.delete();
                    file.rename(oldFile.getName());
               }catch(java.io.IOException ioe) {throw new WriterFailedException("Cannot create file " + tempFilename);}
               
               file = null;
               oldFile = null;
                
       }
      
       private static void appendText(FileConnection file, String log, boolean append) throws WriterFailedException
     {
              DataOutputStream fout = null;
              OutputStream out = null;
              
              /*try{
              file.truncate(file.fileSize() - 2);
            }catch(IOException ioe){}*/
            
               try {
                       if(append)
                       {
                           out = file.openOutputStream(file.fileSize());
                        
                           out.write(log.getBytes());
                           
                           out.flush();
                       }
                       else
                       {
                            fout = file.openDataOutputStream();
            
                            fout.write(log.getBytes());
                            
                            fout.flush();
                       }
                    
               }catch(FileNotFoundException fnfe) {throw new WriterFailedException("File " + file.getName() + " not found."); }
               catch(java.io.IOException ioe) { throw new WriterFailedException("Exception while writing to output stream");}
         finally
                {
                      try
                    {
                              if(fout != null)
                                       fout.close();
                                
                              if(out != null)
                                        out.close();
                                        
                               fout = null;
                               out = null;
                   }catch(java.io.IOException ioe)
                        {
                              throw new WriterFailedException("Exception while closing file " + file.getName());
                     }
              }
      }
}


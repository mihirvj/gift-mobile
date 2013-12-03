/*
 * WriterFailedException.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.Exceptions;


/**
 * Thrown if exception occurs while write operations on file
 * Use getMessage() method to know real cause of exception
 */
public class WriterFailedException extends Exception{

 /**
     * 
     */
    private static final long serialVersionUID = 1L;

      private String ERR_MSG;
        
       public WriterFailedException()
 {
              
       }
      
       public WriterFailedException(String msg)
       {
              this.ERR_MSG = msg;
    }
      
       public String toString()
       {
              return "Writer class failed";
  }
      
       public String getMessage()
     {
              return ERR_MSG;
        }
}


/*
 * ReaderFailedException.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.Exceptions;


/**
 * Thrown if exception occurs while read operations on file
 * Use getMessage() method to know real cause of exception
 */
public class ReaderFailedException extends Exception{

  /**
     * 
     */
    private static final long serialVersionUID = 1L;

      private String ERR_MSG;
        
       public ReaderFailedException()
 {
              
       }
      
       public ReaderFailedException(String msg)
       {
              this.ERR_MSG = msg;
    }
      
       public String toString()
       {
              return "Reader class failed";
  }
      
       public String getMessage()
     {
              return this.ERR_MSG;
   }
}


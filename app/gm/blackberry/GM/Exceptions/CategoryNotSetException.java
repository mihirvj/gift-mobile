/*
 * CategoryNotSetException.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.GM.Exceptions;

/**
 * This is thrown if Category is not set
 */
public class CategoryNotSetException extends RuntimeException{

       /**
     * 
     */
    private static final long serialVersionUID = 1L;

      private String ERR_MSG = "Category not set";
   
       public CategoryNotSetException()
       {
              
       }
      
       public CategoryNotSetException(String msg)
     {
              ERR_MSG = msg;
 }
      
       public String toString()
       {
              return "Category is not set";
  }
      
       public String getMessage()
     {
              return ERR_MSG;
        }
}


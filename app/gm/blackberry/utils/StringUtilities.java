/*
 * StringUtilities.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.utils;

/**
 * 
 */
public class StringUtilities {
    StringUtilities() {    }
    
    public static String[] split(String strString, String strDelimiter)
   {
              int iOccurrences = 0;
          int iIndexOfInnerString = 0;
           int iIndexOfDelimiter = 0;
             int iCounter = 0;

             // Check for null input strings.
               if (strString == null)
         {
                      throw new NullPointerException("Input string cannot be null.");
                }
              // Check for null or empty delimiter
           // strings.
            if (strDelimiter.length() <= 0 || strDelimiter == null)
                {
                      throw new NullPointerException("Delimeter cannot be null or empty.");
          }

             // If strString begins with delimiter
          // then remove it in
           // order
               // to comply with the desired format.

         if (strString.startsWith(strDelimiter))
                {
                      strString = strString.substring(strDelimiter.length());
                }

             // If strString does not end with the
          // delimiter then add it
               // to the string in order to comply with
               // the desired format.
         if (!strString.endsWith(strDelimiter))
         {
                      strString += strDelimiter;
             }

             // Count occurrences of the delimiter in
               // the string.
         // Occurrences should be the same amount
               // of inner strings.
           while((iIndexOfDelimiter= strString.indexOf(strDelimiter,iIndexOfInnerString))!=-1)
            {
                      iOccurrences += 1;
                     iIndexOfInnerString = iIndexOfDelimiter + strDelimiter.length();
               }

             // Declare the array with the correct
          // size.
               String[] strArray = new String[iOccurrences];

         // Reset the indices.
          iIndexOfInnerString = 0;
               iIndexOfDelimiter = 0;

                // Walk across the string again and this
               // time add the
                // strings to the array.
               while((iIndexOfDelimiter= strString.indexOf(strDelimiter,iIndexOfInnerString))!=-1)
            {

                     // Add string to
                       // array.
                      strArray[iCounter] = strString.substring(iIndexOfInnerString, iIndexOfDelimiter);

                     // Increment the
                       // index to the next
                   // character after
                     // the next
                    // delimiter.
                  iIndexOfInnerString = iIndexOfDelimiter + strDelimiter.length();

                      // Inc the counter.
                    iCounter += 1;
         }
            return strArray;
 }
 
    private java.io.Reader in; 
    private int bucket=-1; 

    public StringUtilities(java.io.Reader in){ 
        this.in=in; 
    } 
 
    public boolean hasLine() throws java.io.IOException{ 
        if(bucket!=-1)return true; 
        bucket=in.read(); 
        return bucket!=-1; 
    } 
 
    //Read a line, removing any /r and /n. Buffers the string 
    public String readLine() throws java.io.IOException{ 
        int tmp; 
        StringBuffer out=new StringBuffer(); 
        //Read in data 
        while(true){ 
            //Check the bucket first. If empty read from the input stream 
            if(bucket!=-1){ 
                tmp=bucket; 
                bucket=-1; 
            }else{ 
                tmp=in.read(); 
                if(tmp==-1)break; 
            } 
            //If new line, then discard it. If we get a \r, we need to look ahead so can use bucket 
            if(tmp=='\r'){ 
                int nextChar=in.read(); 
                if(tmp!='\n')bucket=nextChar;//Ignores \r\n, but not \r\r 
                break; 
            }else if(tmp=='\n'){ 
                break; 
            }else{ 
                //Otherwise just append the character 
                out.append((char) tmp); 
            } 
        } 
        return out.toString(); 
    } 
 

} 

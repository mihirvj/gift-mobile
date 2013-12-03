/*
 * GMLabelField.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.ui.controls;

import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.FontFamily;

/**
 * 
 */
public class GMEditField extends EditField{
    
    public GMEditField() 
    {    
        super();
    }
    
    public GMEditField(String text, String initialValue)
    {
        super(text,initialValue);
    }
    
    public GMEditField(long style)
    {
        super(style);
    }
    
    public void paint(Graphics g)
    {        
        super.paint(g);
    }
} 

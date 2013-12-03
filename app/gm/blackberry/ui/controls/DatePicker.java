/*
 * DatePicker.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.ui.controls;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.FieldChangeListener;


/**
 * 
 */
public class DatePicker implements FieldChangeListener{
    EditField textField;
    ButtonField btnAddDate;
    
    public DatePicker() 
    {    
        textField = new EditField("Date : ","");
        btnAddDate = new ButtonField("Add date",ButtonField.CONSUME_CLICK);
        btnAddDate.setChangeListener(this);
    }
    
     public void fieldChanged(Field field, int context) {
     
    }
} 

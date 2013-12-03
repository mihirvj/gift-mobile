/*
 * CommonFieldScreen.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

/*
 * LoginScreen.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.ui.screens;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.picker.DateTimePicker;
import net.rim.device.api.ui.UiApplication;
import java.util.Calendar;
import java.util.Date;
import net.rim.device.api.i18n.SimpleDateFormat;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public abstract class CommonFieldScreen extends MainScreen implements FieldChangeListener
{
    /**
     * Creates a new CommonFieldScreen object
     * Currently only date field is common to all screens
     */
    protected EditField textField;
    protected ButtonField btnAddDate;
     
    public CommonFieldScreen()
    {        
        // Set the displayed title of the screen       
        
        textField = new EditField("Date : ","");
        btnAddDate = new ButtonField("Add date",ButtonField.CONSUME_CLICK);
        btnAddDate.setChangeListener(this);
        
        this.add(textField);
        this.add(btnAddDate);
    
    }

    public void fieldChanged(Field field, int context) {
         if(field == btnAddDate)
        {
           UiApplication.getUiApplication().invokeLater(new Runnable()
            {
                public void run()
                {
                    DateTimePicker datePicker = DateTimePicker.createInstance();
                    datePicker.doModal();
                    Calendar cal = datePicker.getDateTime();
                    Date date = cal.getTime();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
                    Dialog.alert("You selected " + sdf.format(date));
                }
            });
        }
    }
    /**
     * Displays a dialog box to the user with the text "Goodbye!" when the
     * application is closed.
     * 
     * @see net.rim.device.api.ui.Screen#close()
     */
    public void close()
    {
        // Display a farewell message before closing the application  
        super.close();
    }   
} 

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
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;

import app.gm.blackberry.controller.RecordManager;
import app.gm.blackberry.GM.CSV.IO.Categories;
import net.rim.device.api.collection.util.SparseList;
import app.gm.blackberry.GM.CSV.DataFormats.LoginFormat;

import app.gm.blackberry.ui.controls.GMEditField;
/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
final public class LoginScreen extends MainScreen implements FieldChangeListener
{
    /**
     * Creates a new HelloWorldScreen object
     */
    LabelField lblAppName;
    LabelField lblUser;
    LabelField lblPass;
    ButtonField btnSignIn;
    GMEditField userField;
    PasswordEditField passField;
    
    public LoginScreen()
    {        
        // Set the displayed title of the screen       
        setTitle("Login");
        
        app.gm.blackberry.GM.Configuration.ConfigurationManager.SetBaseAddress("file:///SDCard/gm/");
        
        HorizontalFieldManager hfmCenter = new HorizontalFieldManager(HorizontalFieldManager.FIELD_VCENTER);
        VerticalFieldManager vfmCenter = new VerticalFieldManager(VerticalFieldManager.FIELD_VCENTER);
        
        lblUser = new LabelField("Username:");
        lblPass = new LabelField("Password:");
        userField = new GMEditField();              
        passField = new PasswordEditField();
      
        btnSignIn = new ButtonField("Login",ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER);
        
        btnSignIn.setChangeListener(this);
        // Add a read only text field (RichTextField) to the screen.  The
        // RichTextField is focusable by default. Here we provide a style
        // parameter to make the field non-focusable.

        lblUser.setMargin(130,0,0,0);
        vfmCenter.add(lblUser);
        vfmCenter.add(userField);
        vfmCenter.add(lblPass);
        vfmCenter.add(passField);
        vfmCenter.add(btnSignIn);
        
        hfmCenter.add(vfmCenter);
        
        this.add(hfmCenter);
    }

    public void fieldChanged(Field field, int context) {
        if(field == btnSignIn)
        {
            try
            {
                if(isValid())
                {
                    userField.setText("");
                    passField.setText("");
                    UiApplication.getUiApplication().pushScreen(new MenuScreen());
                }
                else
                {
                    passField.setText("");
                    Dialog.alert("Username or Password not correct");
                }
            }catch(Exception Ex)
            {
                Dialog.alert("Login Unsuccessful\nError message : " + Ex);
            }
        }
    }
    
    private boolean isValid()
    {
        RecordManager.setCategory(Categories.LOGIN);
        RecordManager.bindData();
        
        SparseList list = RecordManager.getList();
        
        if(list == null)
            return false;
            
        for(int i=0;i<list.size();i++)
        {
            String user = ((LoginFormat) list.get(i)).GetUsername();
            String pass = ((LoginFormat) list.get(i)).GetPassword();
            
            if(user.equals(userField.getText()) && pass.equals(passField.getText()))
            {
                RecordManager.dismiss();
                return true;
            }
        }
        
        RecordManager.dismiss();
        return false;
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

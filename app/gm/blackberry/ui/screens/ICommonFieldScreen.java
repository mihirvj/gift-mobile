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

import net.rim.device.api.ui.image.*;
import net.rim.device.api.ui.toolbar.*;
import net.rim.device.api.system.*;
import net.rim.device.api.command.*;
import net.rim.device.api.util.StringProvider;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.component.ObjectChoiceField;

import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.picker.DateTimePicker;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.DrawStyle;
import java.util.Calendar;
import java.util.Date;
import net.rim.device.api.i18n.SimpleDateFormat;
import net.rim.device.api.ui.MenuItem;
import app.gm.blackberry.controller.RecordManager;
import app.gm.blackberry.controller.RecordQueries;
import app.gm.blackberry.GM.CSV.IO.Categories;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public abstract class ICommonFieldScreen extends MainScreen implements FieldChangeListener
{
    /**
     * Creates a new CommonFieldScreen object
     * Currently only date field is common to all screens
     */
    protected int category = 0;
    
    protected boolean addingFlag = false;
    
    protected final EditField txtDate;
    protected ButtonField btnAddDate;
    protected ObjectChoiceField selectCategory;
    
    protected ButtonField btnLeft;
    protected ButtonField btnRight;
    protected LabelField countLabel;
    
    protected ButtonField btnSave;
    protected ButtonField btnCancel;
    protected VerticalFieldManager vfmSaveCancel;
    
    MenuItem miAddRecord = new MenuItem("New Record",110,10){
        public void run()
        {
            addRecord();
        }
    };
    
    private String getTitle()
    {
        String title = null;
        
        switch(this.category)
        {
            case Categories.INCOME:
                title = "Income";
            break;
            
            case Categories.EXPENSE:
                title = "Expense";
            break;
            
            case Categories.ASSET:
                title = "Assets";
            break;
            
            case Categories.LIABILITY:
                title = "Liability";
            break;
            
            case Categories.SALES_OF_ASSET:
                title = "Sales of Assets";
            break;
            
            case Categories.CONFLICT:
                title = "Conflict";
            break;
            
            case Categories.MEMORIES:
                title = "Memories";
            break;
        }
        
        return title;
    }
    public ICommonFieldScreen(int category)
    {
        this.category = category;       
        // Set the displayed title of the screen     
        //super(USE_ALL_WIDTH);
        
        setTitle(getTitle());
        /*Set SDCard path*/
        
        app.gm.blackberry.GM.Configuration.ConfigurationManager.SetBaseAddress("file:///SDCard/gm/");
        
        /*declare*/
        
        HorizontalFieldManager hfmRow = new HorizontalFieldManager(HorizontalFieldManager.USE_ALL_WIDTH | HorizontalFieldManager.FIELD_HCENTER);
        VerticalFieldManager vfmCol = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH);
        
        btnLeft = new ButtonField("<<",ButtonField.FIELD_LEFT | ButtonField.CONSUME_CLICK);
        btnLeft.setChangeListener(this);
        btnRight = new ButtonField(">>",ButtonField.FIELD_RIGHT | ButtonField.CONSUME_CLICK);
        btnRight.setChangeListener(this);
        countLabel = new LabelField("Showing 0 of 0 Records");
                
        txtDate = new EditField("Date : ","",20,EditField.READONLY);
        
        btnAddDate = new ButtonField("Add date",ButtonField.CONSUME_CLICK);
        btnAddDate.setChangeListener(this);
        
        RecordQueries.setCategory(this.category);
       
        String[] categories = RecordQueries.getCategories();
        
        RecordQueries.dismiss();
        
        if (categories ==null)
             categories[0] = "No Categories defined";
               
        //function returned string array as input (if list = null no categories defined
        
        selectCategory = new ObjectChoiceField("Category",categories);

        btnSave = new ButtonField("Save",ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER);
        btnCancel = new ButtonField("Clear",ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER);
        btnSave.setChangeListener(this);
        btnCancel.setChangeListener(this);
        
        vfmSaveCancel = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH);
        HorizontalFieldManager hfmSaveCancel = new HorizontalFieldManager(HorizontalFieldManager.FIELD_HCENTER);
        
        hfmSaveCancel.add(btnSave);
        hfmSaveCancel.add(btnCancel);
        
        vfmSaveCancel.add(hfmSaveCancel);
        
        /*add to screen*/
        
        vfmCol.add(btnRight);

        hfmRow.add(btnLeft);
        hfmRow.add(countLabel);
        hfmRow.add(vfmCol);

        //hfmRow.add(textField);
        //hfmRow.add(btnAddDate);
        
        this.add(hfmRow);
        
        this.add(txtDate);
        this.add(btnAddDate);
        this.add(selectCategory);
        
        //addToolBar();
        this.addMenuItem(miAddRecord);
    }
   

    public void fieldChanged(Field field, int context) {
        if(field == btnSave)
        {
            saveRecord();
        }
        
        if(field == btnCancel)
        {
            cancelRecord();
        }
        
        if(field == btnLeft)
        {
            goLeft();
        }
        
        if(field == btnRight)
        {
            goRight();
        }
        
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
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    txtDate.setText(sdf.format(date));
                }
            });
        }
    }
   
    public abstract void addRecord();
    public abstract void saveRecord();
    public abstract void cancelRecord();
    public abstract void goLeft();
    public abstract void goRight();
    /**
     * Displays a dialog box to the user with the text "Goodbye!" when the
     * application is closed.
     * 
     * @see net.rim.device.api.ui.Screen#close()
     */
    public void close()
    {
        // Display a farewell message before closing the application 
        RecordManager.dismiss(); 
        super.close();
    }   
} 

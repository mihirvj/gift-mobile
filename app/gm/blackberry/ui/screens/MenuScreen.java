/*
 * MenuScreen.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.ui.screens;

import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.RichTextField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;

import app.gm.blackberry.GM.CSV.IO.Categories;
/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
final public class MenuScreen extends MainScreen implements FieldChangeListener
{
    /**
     * Creates a new HelloWorldScreen object
     */
    private LabelField btnIncome;
    private LabelField btnExpense;
    private LabelField btnConflict;
    private LabelField btnMemories;
    private LabelField btnAssets;
    private LabelField btnLiability;
    private LabelField btnSalesOfAssets;
    private LabelField btnGraph;
    
    public MenuScreen()
    {        
        // Set the displayed title of the screen       
        setTitle("Welcome user");
        
        /*declare Vertical and Horizontal field managers for feel of table*/
        
        VerticalFieldManager rows = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH);
   
        /*Buttons*/
        btnIncome = new LabelField("Income", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new CategoryScreen(Categories.INCOME));
                 return true;
            }
        };
        
        
        btnExpense = new LabelField("Expense", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new CategoryScreen(Categories.EXPENSE));
                 return true;
            }
        };
        
        
        btnConflict = new LabelField("Conflict", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new ConflictScreen());
                 return true;
            }
        };
        
        
        btnMemories = new LabelField("Memories", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new MemoriesScreen());
                 return true;
            }
        };
        
        
        btnAssets = new LabelField("Assets", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new CategoryScreen(Categories.ASSET));
                 return true;
            }
        };
        
        
        btnLiability = new LabelField("Liability", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new CategoryScreen(Categories.LIABILITY));
                 return true;
            }
        };
        
        
        btnSalesOfAssets = new LabelField("Sales of Assets", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 UiApplication.getUiApplication().pushScreen(new CategoryScreen(Categories.SALES_OF_ASSET));
                 return true;
            }
        };
        
        
        btnGraph= new LabelField("Graph", LabelField.FOCUSABLE | LabelField.USE_ALL_WIDTH){
            public boolean navigationClick (int status , int time){
                 Dialog.alert("Graph page");
                 return true;
            }
        };
        
        
        rows.add(btnIncome);
        rows.add(btnExpense);
        rows.add(btnConflict);
        rows.add(btnMemories);
        rows.add(btnAssets);
        rows.add(btnLiability);
        rows.add(btnSalesOfAssets);
        rows.add(btnGraph);
        
        /*Add vertical panel to screen*/
        
        this.add(rows);
        this.addMenuItem(_viewItem);
    }

    private MenuItem _viewItem = new MenuItem("More Info", 110, 10)
    {
        public void run() 
        {
            Dialog.inform("Display more information");
        }
    };

    public void fieldChanged(Field field, int context) {
        if(field == btnGraph)
        {
            Dialog.alert("Graph");
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
        
        if(Dialog.YES == Dialog.ask(Dialog.D_YES_NO,"You will be logged out. Continue?"))
            super.close();
        else
            return;
        
    }   
} 

/*
 * MemoriesScreen.java
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
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.picker.DateTimePicker;
import net.rim.device.api.ui.UiApplication;

import app.gm.blackberry.GM.CSV.IO.Categories;
import app.gm.blackberry.GM.CSV.DataFormats.MemoryFormat;
import app.gm.blackberry.controller.RecordManager;
import app.gm.blackberry.controller.RecordWriter;
import net.rim.device.api.collection.util.SparseList;

/**
 * 
 */
public class MemoriesScreen extends ICommonFieldScreen
{
    EditField efPerson;
    ObjectChoiceField ocfType;
    EditField efDescription;
    String[] types = new String[] {"pleasent","bitter" };
    
    public MemoriesScreen() 
    {
        super(Categories.MEMORIES);
        
        efPerson = new EditField("Person : ","");
        ocfType = new ObjectChoiceField("Type : ",types);
        efDescription = new EditField("Description : ","");
        
        RecordManager.setCategory(Categories.MEMORIES);
        RecordManager.bindData();
        
        this.add(efPerson);
        this.add(ocfType);
        this.add(efDescription);
        this.add(vfmSaveCancel); //from ICommonFieldScreen
        
        loadData();
    }
    
    private void loadData()
    {
        if(!RecordManager.isEmpty())
        {
            showRecord((MemoryFormat) RecordManager.getFirstRecord());
            btnLeft.setEnabled(true);
            btnRight.setEnabled(true);
            btnSave.setEnabled(true);
            btnCancel.setEnabled(true);
        }
        else
        {
            btnLeft.setEnabled(false);
            btnRight.setEnabled(false);
            btnSave.setEnabled(false);
            btnCancel.setEnabled(false);
        }
    }
    
    private void showRecord(MemoryFormat mf)
    {
        countLabel.setText("Showing " + RecordManager.getCurrentIndex() + " of " + RecordManager.getTotalRecords() + " Records");
        
        try
        {
            txtDate.setText(mf.GetDate());
            this.selectCategory.setSelectedIndex(mf.GetCategory());
            efPerson.setText(mf.GetPerson());
            this.ocfType.setSelectedIndex(mf.GetMemoryType());
            efDescription.setText(mf.GetDescription());
        }catch(Exception e)
        {
            Dialog.alert("Exception : " + e.getMessage());
        }
    }
    
    public void fieldChanged(Field field, int context) 
    {
        super.fieldChanged(field,context);
    }
    
    public void addRecord()
    {
        clearAll();
        this.addingFlag = true;
        btnSave.setEnabled(true);
        btnCancel.setEnabled(true);
    }
    
    private void clearAll()
    {
        txtDate.setText("");
        efPerson.setText("");
        efDescription.setText("");
    }
    
    public void saveRecord()
    {
        try
        {
        String choice = (String)this.selectCategory.getChoice(this.selectCategory.getSelectedIndex());
        String type = (String)this.ocfType.getChoice(this.ocfType.getSelectedIndex());
        
        RecordWriter.setCategory(Categories.MEMORIES);
        
        if(this.addingFlag)
        {
            
            MemoryFormat cf = new MemoryFormat(txtDate.getText(),choice,efPerson.getText(),type,efDescription.getText());
           
            RecordWriter.write(cf);
            
            this.addingFlag = false;
            
            Dialog.alert("Record written successfully");
        }
        else
        {
            SparseList list = RecordManager.getList();
            
            if(list == null)
              {
                  this.addingFlag = true;
                  saveRecord();
                  return;
              }
            
            int curIndex = RecordManager.getCurrentIndex() - 1;
            
            if(RecordManager.isRecordPresent(curIndex))
            {
                ((MemoryFormat) list.get(curIndex)).SetDate(txtDate.getText());
                ((MemoryFormat) list.get(curIndex)).SetCategory(choice);
                ((MemoryFormat) list.get(curIndex)).SetPerson(efPerson.getText());
                ((MemoryFormat) list.get(curIndex)).SetMemoryType(type);
                ((MemoryFormat) list.get(curIndex)).SetDescription(efDescription.getText());
                
                
                RecordWriter.update(list);
            
                Dialog.alert("Record updated successfully");
            }
            
        }
        
        bindNewData();
    }catch(ArrayIndexOutOfBoundsException Ex)
    {
        Dialog.alert("Please fill all data");
    }
    }
    
    private void bindNewData()
    {
        RecordManager.dismiss();
        RecordManager.setCategory(Categories.MEMORIES);
        RecordManager.bindData();       
        loadData();
    }
    
    public void cancelRecord()
    {
        
    }
    
     public void goLeft()
    {
     
      showRecord((MemoryFormat) RecordManager.getPreviousRecord());  
      
    }
    
    public void goRight()
    {
        
        showRecord((MemoryFormat) RecordManager.getNextRecord());
        
    }
} 

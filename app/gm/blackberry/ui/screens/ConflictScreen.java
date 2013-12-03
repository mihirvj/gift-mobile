/*
 * ConflictScreen.java
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

import app.gm.blackberry.GM.CSV.DataFormats.ConflictFormat;


import app.gm.blackberry.GM.CSV.IO.Categories;

import app.gm.blackberry.controller.RecordManager;
import app.gm.blackberry.controller.RecordWriter;
import net.rim.device.api.collection.util.SparseList;

/**
 * 
 */
public class ConflictScreen extends ICommonFieldScreen
{
    EditField efPerson;
    EditField efConflictReason;
    EditField efDescription;
    
    public ConflictScreen() 
    {
        super(Categories.CONFLICT);
        
        efPerson = new EditField("Person : ","");
        efConflictReason = new EditField("Reason : ","");
        efDescription = new EditField("Description : ","");
        
        RecordManager.setCategory(Categories.CONFLICT);
        RecordManager.bindData();
        
        this.add(efPerson);
        this.add(efConflictReason);
        this.add(efDescription);
        this.add(vfmSaveCancel); //from ICommonFieldScreen
        
        loadData();
    }
    
    private void loadData()
    {
        if(!RecordManager.isEmpty())
        {
            showRecord((ConflictFormat) RecordManager.getFirstRecord());
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
    
    private void showRecord(ConflictFormat cf)
    {
        countLabel.setText("Showing " + RecordManager.getCurrentIndex() + " of " + RecordManager.getTotalRecords() + " Records");
        
        try
        {
            txtDate.setText(cf.GetDate());
            this.selectCategory.setSelectedIndex(cf.GetCategory());
            efPerson.setText(cf.GetPerson());
            efConflictReason.setText(cf.GetConflictReason());
            efDescription.setText(cf.GetDescription());
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
        efConflictReason.setText("");
        efDescription.setText("");
    }
    
    public void saveRecord()
    {
        try
        {
        String choice = (String)this.selectCategory.getChoice(this.selectCategory.getSelectedIndex());
        RecordWriter.setCategory(Categories.CONFLICT);
        
        if(this.addingFlag)
        {
            
            ConflictFormat cf = new ConflictFormat(txtDate.getText(),choice,efPerson.getText(),efConflictReason.getText(),efDescription.getText());
           
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
                ((ConflictFormat) list.get(curIndex)).SetDate(txtDate.getText());
                ((ConflictFormat) list.get(curIndex)).SetCategory(choice);
                ((ConflictFormat) list.get(curIndex)).SetPerson(efPerson.getText());
                ((ConflictFormat) list.get(curIndex)).SetConflictReason(efConflictReason.getText());
                ((ConflictFormat) list.get(curIndex)).SetDescription(efDescription.getText());
                
                
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
        RecordManager.setCategory(Categories.CONFLICT);
        RecordManager.bindData();       
        loadData();
    }
    
    public void cancelRecord()
    {
    }
    
    public void goLeft()
    {
     
      showRecord((ConflictFormat) RecordManager.getPreviousRecord());  
      
    }
    
    public void goRight()
    {
        
        showRecord((ConflictFormat) RecordManager.getNextRecord());
        
    }
} 

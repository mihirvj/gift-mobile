/*
 * CategoryScreen.java
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

import app.gm.blackberry.controller.RecordManager;
import app.gm.blackberry.controller.RecordWriter;
import app.gm.blackberry.GM.CSV.DataFormats.CategoryFormat;
import net.rim.device.api.collection.util.SparseList;
/**
 * 
 */
public class CategoryScreen extends ICommonFieldScreen
{
    EditField efCode;
    EditField efUnit;
    EditField efRate;
    EditField efAmount;
    EditField efSource;
    EditField efRemark;

    public CategoryScreen(int category) 
    {
        super(category);

        efCode = new EditField("Code : ","");
        efUnit = new EditField("Unit : ","0",20,EditField.FILTER_REAL_NUMERIC);
        efRate = new EditField("Rate : ","0",20,EditField.FILTER_REAL_NUMERIC);
        efAmount = new EditField("Amount : ","0",20,EditField.FILTER_REAL_NUMERIC);
        efSource = new EditField("Source : ","");
        efRemark = new EditField("Remark : ","");
        
        RecordManager.dismiss();
        RecordManager.setCategory(category);
        RecordManager.bindData();
        
        this.add(efCode);
        this.add(efUnit);
        this.add(efRate);
        this.add(efAmount);
        this.add(efSource);
        this.add(efRemark);
        this.add(vfmSaveCancel); //from ICommonFieldScreen
        
        loadData();
    }
    
    private void loadData()
    {
        if(!RecordManager.isEmpty())
        {
            showRecord((CategoryFormat) RecordManager.getFirstRecord());
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
    
    private void showRecord(CategoryFormat cf)
    {
        countLabel.setText("Showing " + RecordManager.getCurrentIndex() + " of " + RecordManager.getTotalRecords() + " Records");
        
        try
        {
            txtDate.setText(cf.GetDate());
            this.selectCategory.setSelectedIndex(cf.GetCategory());
            efCode.setText(cf.GetCode());
            efUnit.setText(cf.GetNumberOfUnits());
            efRate.setText(cf.GetRate());
            efAmount.setText(cf.GetAmount());
            efSource.setText(cf.GetSource());
            efRemark.setText(cf.GetRemark());
        }catch(Exception e)
        {
            Dialog.alert("Exception : " + e.getMessage());
        }
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
        efCode.setText("");
        efUnit.setText("0");
        efRate.setText("0");
        efAmount.setText("0");
        efSource.setText("");
        efRemark.setText("");
    }
    
    public void saveRecord()
    {
        try
        {
        String choice = (String)this.selectCategory.getChoice(this.selectCategory.getSelectedIndex());
        RecordWriter.setCategory(this.category);
        
        if(this.addingFlag)
        {
            
            CategoryFormat cf = new CategoryFormat(txtDate.getText(),choice,efCode.getText(),efUnit.getText(),efRate.getText(),efAmount.getText(),efSource.getText(),efRemark.getText());
           
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
                ((CategoryFormat) list.get(curIndex)).SetDate(txtDate.getText());
                ((CategoryFormat) list.get(curIndex)).SetCategory(choice);
                ((CategoryFormat) list.get(curIndex)).SetCode(efCode.getText());
                ((CategoryFormat) list.get(curIndex)).SetNumberOfUnits(efUnit.getText());
                ((CategoryFormat) list.get(curIndex)).SetRate(efRate.getText());
                ((CategoryFormat) list.get(curIndex)).SetAmount(efAmount.getText());
                ((CategoryFormat) list.get(curIndex)).SetSource(efSource.getText());
                ((CategoryFormat) list.get(curIndex)).SetRemark(efRemark.getText());
                
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
        RecordManager.setCategory(category);
        RecordManager.bindData();       
        loadData();
    }
    
    public void cancelRecord()
    {
    }
    
     public void goLeft()
    {
     
      showRecord((CategoryFormat) RecordManager.getPreviousRecord());  
      
    }
    
    public void goRight()
    {
        
        showRecord((CategoryFormat) RecordManager.getNextRecord());
        
    }
    public void fieldChanged(Field field, int context) 
    {
        super.fieldChanged(field,context);
    }
} 

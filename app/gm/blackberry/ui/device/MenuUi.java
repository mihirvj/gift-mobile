/*
 * MenuUi.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.ui.device;

import app.gm.blackberry.ui.screens.MenuScreen;
import net.rim.device.api.ui.UiApplication;

/**
 * 
 */
class MenuUi extends UiApplication
{
    /**
     * Creates a new MenuUi object
     */
    public MenuUi()
    {        
        // Push a screen onto the UI stack for rendering.
        pushScreen(new MenuScreen());
    }    
} 

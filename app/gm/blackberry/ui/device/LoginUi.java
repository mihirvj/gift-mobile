/*
 * LoginUi.java
 *
 * © <your company here>, <year>
 * Confidential and proprietary.
 */

package app.gm.blackberry.ui.device;

import app.gm.blackberry.ui.screens.LoginScreen;
import net.rim.device.api.ui.UiApplication;

/**
 * 
 */
class LoginUi extends UiApplication
{
    public static void main(String[] args)
    {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        LoginUi theApp = new LoginUi();       
        theApp.enterEventDispatcher();
    }
    

    /**
     * Creates a new HelloWorldDemo object
     */
    public LoginUi()
    {        
        // Push a screen onto the UI stack for rendering.
        pushScreen(new LoginScreen());
    }    
}


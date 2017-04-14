/*
 * NMEA_Multiplexer_v3App.java
 */

package nmea_multiplexer_v3;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class NMEA_Multiplexer_v3App extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new NMEA_Multiplexer_v3View(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of NMEA_Multiplexer_v3App
     */
    public static NMEA_Multiplexer_v3App getApplication() {
        return Application.getInstance(NMEA_Multiplexer_v3App.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(NMEA_Multiplexer_v3App.class, args);
    }
}

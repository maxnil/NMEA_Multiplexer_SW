/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nmea_multiplexer_v3;

import org.jdesktop.application.Task;

/**
 *
 * @author Max
 */
public class nmeaTask extends org.jdesktop.application.Task<Object, Void> {

    private serialPort serialPort;
    private nmeaPanel[] nmeaPanels;
    private nmea nmea;

    nmeaTask(org.jdesktop.application.Application app, serialPort serialPort, nmeaPanel[] nmeaPanels) {
        // Runs on the EDT.  Copy GUI state that
        // doInBackground() depends on from parameters
        // to StartmuxTask fields, here.
        super(app);

        this.serialPort = serialPort;
        this.nmeaPanels = nmeaPanels;
        nmea = new nmea();
    }

    @Override
    protected Object doInBackground() {
        // Your Task's code here.  This method runs
        // on a background thread, so don't reference
        // the Swing GUI from here.
        setMessage("Reading MUX data");
        System.out.println("MUX Task doInBackground");

        String str;
        String nmeaStr;
        int nmeaPortNr;
        while (!isCancelled()) {
            try {
                str = serialPort.getLine(2000);
                if (str != null && str.startsWith("$")) {
                    try {
                        nmeaPortNr = Integer.parseInt(str.substring(1, 2));
                        nmeaStr = str.substring(2);
                    } catch (NumberFormatException nfe) {
                        nmeaPortNr = 4;
                        nmeaStr = str.substring(1);
                    }
                    if (nmeaPanels.length == 1) {
                        nmea.updatePanel(nmeaStr, nmeaPanels[0]);
                    } else {
                        nmea.updatePanel(nmeaStr, nmeaPanels[nmeaPortNr-1]);
                    }
                } else {
                    System.out.println("Unknown NMEA string: " + str);
                }
            } catch (InterruptedException ie) {
            } catch (Exception e) {
                System.out.println("serialPort.getLine " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("SerialPortTask is cancelled");
        serialPort.close();
        return null;  // return your result
    }

    @Override
    protected void succeeded(Object result) {
        // Runs on the EDT.  Update the GUI based on
        // the result computed by doInBackground().
        System.out.println("muxTask succeeded");
    }

    @Override
    protected void cancelled() {
        // Runs on the EDT.  Update the GUI based on
        // the result computed by doInBackground().
        System.out.println("muxTask cancelled");
    }

    @Override
    protected void finished() {
        // Runs on the EDT.  Update the GUI based on
        // the result computed by doInBackground().
        System.out.println("muxTask finished");
    }

    @Override
    protected void failed(java.lang.Throwable cause) {
        // Runs on the EDT.  Update the GUI based on
        // the result computed by doInBackground().
        System.out.println("muxTask failed");
    }

    @Override
    protected void interrupted(java.lang.InterruptedException e) {
        // Runs on the EDT.  Update the GUI based on
        // the result computed by doInBackground().
        System.out.println("muxTask interrupted");
    }

}
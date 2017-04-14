/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nmea_multiplexer_v3;

import java.io.*;
import java.util.*;
import gnu.io.*;
import java.util.concurrent.*;


/**
 *
 * @author Max
 */
public class serialPort implements Runnable, SerialPortEventListener {

    private BlockingQueue<String> StrQueue;
    private SerialPort	          serialPort;
    private BufferedReader        bufRead;
    private boolean               portOpen = false;
    private int                   baudRate;

    /**
     * Constructor declaration
     *
     * @param port
     */
    public serialPort(int baudRate) {
        this.baudRate = baudRate;
    }


    /**
     * Constructor declaration
     * opens a serial port
     *
     * @param port
     */
//    public serialPort(String port) {
//        this.open(port);
//    }


    /**
     * Closes the current open serial port
     *
     * @return true if port could be opened, false otherwise
     */
    public boolean open(String port) {
        CommPortIdentifier  portId = null;
        boolean foundPort = false;
        portOpen = false;

        Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        // Get Serial Port Identifier
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier)portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                if (portId.getName().equals(port)) {
                    foundPort = true;
                    break;
                }
	    }
	}

        // Open port
        if (foundPort) {
            try {
                serialPort = (SerialPort)portId.open("NMEA_Multiplexer", 3000);
                StrQueue = new LinkedBlockingQueue<String>();

                bufRead = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                serialPort.addEventListener(this);
	    	serialPort.enableReceiveTimeout(1000);
	    	serialPort.enableReceiveThreshold(1);
                serialPort.notifyOnDataAvailable(true);
                serialPort.notifyOnBreakInterrupt(true);
                serialPort.notifyOnCTS(true);
                serialPort.notifyOnCarrierDetect(true);
                serialPort.notifyOnDSR(true);
                serialPort.notifyOnFramingError(false);
                serialPort.notifyOnOutputEmpty(true);
                serialPort.notifyOnOverrunError(true);
                serialPort.notifyOnParityError(true);
                serialPort.notifyOnRingIndicator(true);
                serialPort.setSerialPortParams(baudRate,
                                               SerialPort.DATABITS_8,
                                               SerialPort.STOPBITS_1,
                                               SerialPort.PARITY_NONE);
                portOpen = true;
            } catch (PortInUseException e) {
                System.err.println("SerialPort.open: Port in Use! " + e.getMessage());
                foundPort = false;
            } catch (IOException e) {
                System.err.println("SerialPort.open: IO Exception! " + e.getMessage());
                foundPort = false;
            } catch (TooManyListenersException e) {
                System.err.println("SerialPort.open: Too Many Listeners! " + e.getMessage());
                foundPort = false;
            } catch (UnsupportedCommOperationException e) {
                System.err.println("SerialPort.open: Unsupported Comm Operation! " + e.getMessage());
                foundPort = false;
            }
        }

        // If the port was not found, or did not open then invalidate references
        if (!foundPort) {
            serialPort = null;
            StrQueue = null;
            bufRead = null;
        }

        return foundPort;
    }

    /**
     * Closes the current open serial port
     */
    public void close() {
        if (portOpen) {
            serialPort.close();
        }
    }


    /**
     *
     * @return
     * @throws Exception
     */
    public String getLine(int timeOut) throws InterruptedException {
        return StrQueue.poll(timeOut, TimeUnit.MILLISECONDS);
    }


    /**
     *
     */
    public void run() { }


    /**
     * Method declaration
     *
     * @param event
     *
     * @see
     */
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {

            case SerialPortEvent.BI:
                System.err.println("BI");
                break;
            case SerialPortEvent.OE:
                System.err.println("IE");
                break;

            case SerialPortEvent.FE:
                System.err.println("FE");
                break;

            case SerialPortEvent.PE:
                System.err.println("PE");
                break;

            case SerialPortEvent.CD:
                System.err.println("CD");
                break;

            case SerialPortEvent.CTS:
                System.err.println("CTS");
                break;

            case SerialPortEvent.DSR:
                System.err.println("DSR");
                break;

            case SerialPortEvent.RI:
                System.err.println("RI");
                break;

            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                System.err.println("OUTPUT_BUFFER_EMPTY");
                break;

            case SerialPortEvent.DATA_AVAILABLE:
                try {
                    StrQueue.add(bufRead.readLine());
                } catch (IOException e) {
                    System.err.println("SerialPort.serialEvent: " + e.getMessage());
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("serialEvent: " + event.getEventType());
        }
    }


    /**
     * Searches for serial ports
     *
     * @return An array of serial port names found. If non, it returns null
     */
    public String[] findSerialPorts() {
        CommPortIdentifier  portId;
        Vector<String>      serialPorts = new Vector<String>();
        Enumeration portList = CommPortIdentifier.getPortIdentifiers();

        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier)portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                serialPorts.add(portId.getName());
	    }
	}

        if (serialPorts.size() > 0) {
            String [] serialPortsFound = new String[serialPorts.size()];
            serialPorts.toArray(serialPortsFound);
            return serialPortsFound;
        } else {
            return null;
        }
    }
}

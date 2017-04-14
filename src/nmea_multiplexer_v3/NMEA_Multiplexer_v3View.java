/*
 * NMEA_Multiplexer_v3View.java
 */

package nmea_multiplexer_v3;

import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 * The application's main frame.
 */
public class NMEA_Multiplexer_v3View extends FrameView {

    private nmeaPanel[] gpsPanel;
    private nmeaPanel[] muxPanels;
    private muxConfiguration muxConfig;
    private serialPort gpsSerialPort;
    private serialPort muxSerialPort;
    private nmeaTask gpsTask;
    private nmeaTask muxTask;

    public NMEA_Multiplexer_v3View(SingleFrameApplication app) {
        super(app);

        initComponents();

        muxConfig = new muxConfiguration();
        muxConfig.gpsPortName = new String("Kalle");
        muxConfig.muxPortName = new String("Nisse");

        gpsPanel  = new nmeaPanel[1];
        muxPanels = new nmeaPanel[4];

        gpsPanel[0] = new nmeaPanel("GPS", this.getFrame());
        muxPanels[0] = new nmeaPanel("Nexus Server", this.getFrame());
        muxPanels[1] = new nmeaPanel("AutoHelm SeaTalk", this.getFrame());
        muxPanels[2] = new nmeaPanel("Garmin GPS", this.getFrame());
        muxPanels[3] = new nmeaPanel("Unknown", this.getFrame());

        javax.swing.JScrollPane[] scrollPanes = new javax.swing.JScrollPane[5];
        scrollPanes[0] = new javax.swing.JScrollPane();
        scrollPanes[0].setViewportView(gpsPanel[0]);
        mainTabbedPane.addTab("Ext GPS", scrollPanes[0]);
        for (int i = 0; i < muxPanels.length; i++) {
            scrollPanes[i+1] = new javax.swing.JScrollPane();
            scrollPanes[i+1].setViewportView(muxPanels[i]);
            mainTabbedPane.addTab("MUX Port " + (i+1), scrollPanes[i+1]);
        }

        // This should be set after every 'addPanel'
        this.getFrame().setMinimumSize(new java.awt.Dimension(mainPanel.getMinimumSize().width,
                (menuBar.getPreferredSize().height +
                 mainPanel.getMinimumSize().height +
                 statusPanel.getPreferredSize().height +
                 32)));
        this.getFrame().setSize(this.getFrame().getMinimumSize());

        // Serial ports
        gpsSerialPort = new serialPort(4800);
        muxSerialPort = new serialPort(4800);

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = NMEA_Multiplexer_v3App.getApplication().getMainFrame();
            aboutBox = new aboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        NMEA_Multiplexer_v3App.getApplication().show(aboutBox);
    }

    @Action
    public void showPortSettingBox() {
        if (portSettingBox == null) {
            JFrame mainFrame = NMEA_Multiplexer_v3App.getApplication().getMainFrame();
            String[] serialPortsFound = gpsSerialPort.findSerialPorts();
            portSettingBox = new portSettingBox(mainFrame, muxConfig, serialPortsFound);
            portSettingBox.setLocationRelativeTo(mainFrame);
        }
        NMEA_Multiplexer_v3App.getApplication().show(portSettingBox);
        System.out.println("GPS port: " + muxConfig.gpsPortName);
        System.out.println("MUX port: " + muxConfig.muxPortName);
        // Enable/disable CheckBoxes
        gpsCheckBoxMenuItem.setEnabled(!muxConfig.gpsPortName.equalsIgnoreCase("<none>"));
        muxCheckBoxMenuItem.setEnabled(!muxConfig.muxPortName.equalsIgnoreCase("<none>"));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainTabbedPane = new javax.swing.JTabbedPane();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        settingsMenu = new javax.swing.JMenu();
        portSettingsMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        gpsCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        muxCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        mainTabbedPane.setName("mainTabbedPane"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mainTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
                .addContainerGap())
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(nmea_multiplexer_v3.NMEA_Multiplexer_v3App.class).getContext().getResourceMap(NMEA_Multiplexer_v3View.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(nmea_multiplexer_v3.NMEA_Multiplexer_v3App.class).getContext().getActionMap(NMEA_Multiplexer_v3View.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        settingsMenu.setText(resourceMap.getString("settingsMenu.text")); // NOI18N
        settingsMenu.setName("settingsMenu"); // NOI18N

        portSettingsMenuItem.setAction(actionMap.get("showPortSettingBox")); // NOI18N
        portSettingsMenuItem.setText(resourceMap.getString("portSettingsMenuItem.text")); // NOI18N
        portSettingsMenuItem.setName("portSettingsMenuItem"); // NOI18N
        settingsMenu.add(portSettingsMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        settingsMenu.add(jSeparator1);

        gpsCheckBoxMenuItem.setAction(actionMap.get("gpsCheckBox")); // NOI18N
        gpsCheckBoxMenuItem.setText(resourceMap.getString("gpsCheckBoxMenuItem.text")); // NOI18N
        gpsCheckBoxMenuItem.setToolTipText(resourceMap.getString("gpsCheckBoxMenuItem.toolTipText")); // NOI18N
        gpsCheckBoxMenuItem.setEnabled(false);
        gpsCheckBoxMenuItem.setName("gpsCheckBoxMenuItem"); // NOI18N
        settingsMenu.add(gpsCheckBoxMenuItem);

        muxCheckBoxMenuItem.setAction(actionMap.get("muxCheckBox")); // NOI18N
        muxCheckBoxMenuItem.setText(resourceMap.getString("muxCheckBoxMenuItem.text")); // NOI18N
        muxCheckBoxMenuItem.setToolTipText(resourceMap.getString("muxCheckBoxMenuItem.toolTipText")); // NOI18N
        muxCheckBoxMenuItem.setEnabled(false);
        muxCheckBoxMenuItem.setName("muxCheckBoxMenuItem"); // NOI18N
        settingsMenu.add(muxCheckBoxMenuItem);

        menuBar.add(settingsMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 499, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void gpsCheckBox() {
        if (gpsCheckBoxMenuItem.isSelected()) {
            if (gpsSerialPort.open(muxConfig.gpsPortName)) {
                portSettingsMenuItem.setEnabled(false);
                System.out.println("GPS port " + muxConfig.gpsPortName + " open");
                gpsTask = new nmeaTask(getApplication(), gpsSerialPort, gpsPanel);
                gpsTask.execute();
            } else {
                System.out.println("Could not open GPS port " + muxConfig.gpsPortName);
            }
        } else {
            gpsSerialPort.close();
            if (gpsTask != null) {
                gpsTask.cancel(true);
            }
            System.out.println("GPS port closed");
            if (!muxCheckBoxMenuItem.isSelected()) {
                portSettingsMenuItem.setEnabled(true);
            }
        }
    }

    @Action
    public void muxCheckBox() {
        if (muxCheckBoxMenuItem.isSelected()) {
            if (muxSerialPort.open(muxConfig.muxPortName)) {
                portSettingsMenuItem.setEnabled(false);
                System.out.println("mux port " + muxConfig.muxPortName + " open");
                muxTask = new nmeaTask(getApplication(), muxSerialPort, muxPanels);
                muxTask.execute();
            } else {
                System.out.println("Could not open mux port " + muxConfig.muxPortName);
            }
        } else {
            muxSerialPort.close();
            if (muxTask != null) {
                muxTask.cancel(true);
            }
            System.out.println("MUX port closed");
            if (!gpsCheckBoxMenuItem.isSelected()) {
                portSettingsMenuItem.setEnabled(true);
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBoxMenuItem gpsCheckBoxMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTabbedPane mainTabbedPane;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JCheckBoxMenuItem muxCheckBoxMenuItem;
    private javax.swing.JMenuItem portSettingsMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JMenu settingsMenu;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
    private portSettingBox portSettingBox;
}

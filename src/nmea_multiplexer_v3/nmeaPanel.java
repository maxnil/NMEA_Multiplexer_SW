package nmea_multiplexer_v3;

import java.util.*;
import javax.swing.JFrame;

/**
 *
 * @author Max
 */
public class nmeaPanel extends javax.swing.JPanel {

    private Hashtable<String,nmeaSentencePanel> nmeaSentencePanels;
//    private JFrame frame;

    /**
     *
     * @param title
     */
    public nmeaPanel(String title, JFrame frame) {
        super();
        javax.swing.JPanel thisPanel = this;
//        this.frame = frame;

        // Allocate and initialize NMEA Sentence panels
        nmeaSentencePanels = new Hashtable<String,nmeaSentencePanel>();

        // Set the border style
        thisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, title,
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", java.awt.Font.BOLD, 11)));
        
        layoutPanel();
    }

    /**
     *
     * @param nmeaType
     * @return
     */
    private nmeaSentencePanel getPanel(String nmeaType) {
        return nmeaSentencePanels.get(nmeaType);
    }

    /**
     *
     * @param title
     * @param nrSentencePanels
     */
    private void layoutPanel() {
        javax.swing.JPanel thisPanel = this;
        thisPanel.removeAll();

        // Arrange Label containers
        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(thisPanel);

        // Arrange containers horizontaly and verticaly
        javax.swing.GroupLayout.ParallelGroup hGroup = panelLayout.createParallelGroup();
        javax.swing.GroupLayout.SequentialGroup vGroup = panelLayout.createSequentialGroup();

        // Get nmea Keys
        SortedSet<String> nmeaKeys = new TreeSet<String>(nmeaSentencePanels.keySet());

        for (String nmeaType : nmeaKeys) {
            // Horizontal group
            hGroup.addComponent(nmeaSentencePanels.get(nmeaType),
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE);
            // Vertical group
            vGroup.addComponent(nmeaSentencePanels.get(nmeaType));
        }

        panelLayout.setHorizontalGroup(hGroup);
        panelLayout.setVerticalGroup(vGroup);

        thisPanel.setLayout(panelLayout);
    }

    /**
     *
     * @param title
     * @param nrSentencePanels
     */
    public void addPanel(String nmeaType, String title, String[] labelList) {
        // Allocate, initialize NMEA Sentence and panel
        if (!nmeaSentencePanels.containsKey(nmeaType)) {
            nmeaSentencePanels.put(nmeaType, new nmeaSentencePanel(title, labelList));
            layoutPanel();
        }
    }

    /**
     *
     * @param nmeaType
     * @return
     */
    public boolean contains(String nmeaType) {
        return nmeaSentencePanels.containsKey(nmeaType);
    }

    /**
     *
     * @param nmeaType
     * @param text
     */
    public void setNmeaText(String nmeaType, String text) {
        getPanel(nmeaType).setNmeaText(text);
    }

    /**
     *
     * @param nmeaType
     * @param index
     * @param text
     */
    public void setLabelText(String nmeaType, int index, String text) {
        getPanel(nmeaType).setLabelText(index, text);
    }

    /**
     *
     * @param nmeaType
     * @param index
     * @param text
     */
    public void setBorderText(String nmeaType, int index, String text) {
        getPanel(nmeaType).setBorderText(index, text);
    }

    /**
     *
     * @param nmeaType
     * @param text
     */
    public void setTitleText(String nmeaType, String text) {
       getPanel(nmeaType).setTitleText(text);
    }

    /**********************************************************************
     *
     **********************************************************************/


    /**
     * nmeaSentencePanel contains data from one NMEA sentence
     */
    private class nmeaSentencePanel extends javax.swing.JPanel {

        private javax.swing.JLabel[] dataLabelArray;
        private javax.swing.JLabel nmeaDataLabel;

        /**
         *
         * @param title
         */
        public nmeaSentencePanel(String title, String[] labelList) {
            javax.swing.JPanel thisPanel = this;

            // Set the border style
            thisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, title,
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 11)));

            if (labelList != null) {
                // Initialize data labels
                dataLabelArray = new javax.swing.JLabel[labelList.length];
                for (int i = 0; i < dataLabelArray.length; i++) {
                    dataLabelArray[i] = new javax.swing.JLabel();
                    dataLabelArray[i].setText("<none>");
                    dataLabelArray[i].setBorder(javax.swing.BorderFactory.createTitledBorder(labelList[i]));
//                dataLabel.setMinimumSize(new java.awt.Dimension(100,dataLabelArray[i].getMinimumSize().height));
                }
            }

            // Initialize NMEA label
            nmeaDataLabel = new javax.swing.JLabel();
            nmeaDataLabel.setText("<none>");
            nmeaDataLabel.setBorder(javax.swing.BorderFactory.createTitledBorder("Raw NMEA data"));

            layoutPanel();
        }

        /**
         *
         * @param labelName
         */
        public void addLabel(String labelName) {
        }

        /**
         *
         * @param title
         * @param nrDataLabel
         */
        private void layoutPanel() {
            javax.swing.JPanel thisPanel = this;

            // Arrange Label containers
            javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(thisPanel);

            // Arrange containers horizontaly
            javax.swing.GroupLayout.SequentialGroup hsGroup; // = panelLayout.createSequentialGroup();
            javax.swing.GroupLayout.ParallelGroup hpGroup; // = panelLayout.createParallelGroup();
            int i = 0;
            hpGroup = panelLayout.createParallelGroup();
            if (dataLabelArray != null) {
                for (int p = 0; (p < 6) && (i < dataLabelArray.length); p++) {
                    hsGroup = panelLayout.createSequentialGroup();
                    for (int s = 0; (s < 12) && (i < dataLabelArray.length); s++) {
                        hsGroup.addComponent(dataLabelArray[i], javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE, Short.MAX_VALUE);
                        i++;
                    }
                    hpGroup.addGroup(hsGroup);
                }
            }
            // Add Data Label group and NMEA Label and set horizontal layout
            panelLayout.setHorizontalGroup(hpGroup.addComponent(nmeaDataLabel,
                    javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE,
                    Short.MAX_VALUE));

            // Arrange containers verticaly
            javax.swing.GroupLayout.SequentialGroup vsGroup; // = panelLayout.createSequentialGroup();
            javax.swing.GroupLayout.ParallelGroup vpGroup; // = panelLayout.createParallelGroup();
            i = 0;
            vsGroup = panelLayout.createSequentialGroup();
            if (dataLabelArray != null) {
                for (int p = 0; (p < 6) && (i < dataLabelArray.length); p++) {
                    vpGroup = panelLayout.createParallelGroup();
                    for (int s = 0; (s < 12) && (i < dataLabelArray.length); s++) {
                        vpGroup.addComponent(dataLabelArray[i]); //, javax.swing.GroupLayout.PREFERRED_SIZE,
                                //javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
                        i++;
                    }
                    vsGroup.addGroup(vpGroup);
                }
            }
            // Add Data Label group and NMEA Label and set vertical layout
            panelLayout.setVerticalGroup(vsGroup.addComponent(nmeaDataLabel));

            thisPanel.setLayout(panelLayout);
        }

        /**
         *
         * @param text
         */
        public void setNmeaText(String text) {
            if (text != null) {
                nmeaDataLabel.setText(text);
            } else {
                nmeaDataLabel.setText("-");
            }
        }

        /**
         *
         * @param index
         * @param text
         */
        public void setLabelText(int index, String text) {
            if (text != null) {
                dataLabelArray[index].setText(text);
            } else {
                dataLabelArray[index].setText("-");
            }
        }

        /**
         *
         * @param index
         * @param text
         */
        public void setBorderText(int index, String text) {
            if (text != null) {
                dataLabelArray[index].setBorder(javax.swing.BorderFactory.createTitledBorder(text));
            } else {
                dataLabelArray[index].setBorder(javax.swing.BorderFactory.createTitledBorder("-"));
            }
        }
        
        /**
         * 
         * @param text
         */
        public void setTitleText(String text) {
            if (text != null) {
                this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, text,
                    javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                    javax.swing.border.TitledBorder.DEFAULT_POSITION,
                    new java.awt.Font("Tahoma", java.awt.Font.ITALIC, 11)));
           }
        }
    }
}

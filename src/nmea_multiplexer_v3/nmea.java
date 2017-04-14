/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nmea_multiplexer_v3;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author Max
 */
public class nmea {

    private Hashtable<String,String> nmeaDescriptionTable;
    private Hashtable<String,String[]> nmeaLabelsTable;
    private nmeaPanel nmeaPanel;

    /**
     *
     */
    public nmea() {
        nmeaDescriptionTable = new Hashtable<String,String>();
        nmeaLabelsTable = new Hashtable<String,String[]>();

//        nmeaLabelsTable.put("GPAAM", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPALM", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPAPA", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPAPB", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPASD", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPBEC", new String[] {"unknown"});
        nmeaLabelsTable.put("GPBOD", new String[] {"Bearing true", "Bearing magnetic", "Destination", "Start"});
//        nmeaLabelsTable.put("GPBWC", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPBWR", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPBWW", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPDBT", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPDCN", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPDPT", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPFSI", new String[] {"unknown"});
        nmeaLabelsTable.put("GPGGA", new String[] {"Time (UTC)", "Latitude", "Longitude", "Fix Quality", "Nr Satellites", "HDOP", "Altitude", "Height of geoid"});
//        nmeaLabelsTable.put("GPGLC", new String[] {"unknown"});
        nmeaLabelsTable.put("GPGLL", new String[] {"Latitude", "Longitude", "Fix taken", "Validity"});
//        nmeaLabelsTable.put("GPGRS", new String[] {"unknown"});
        nmeaLabelsTable.put("GPGSA", new String[] {"Mode", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "Sat", "PDOP", "HDOP", "VDOP"});
        nmeaLabelsTable.put("GPGSV", new String[] {"Elev", "Azim", "SNR", "Elev", "Azim", "SNR", "Elev", "Azim", "SNR", "Elev", "Azim", "SNR",
        "Elev", "Azim", "SNR", "Elev", "Azim", "SNR", "Elev", "Azim", "SNR", "Elev", "Azim", "SNR",
        "Elev", "Azim", "SNR", "Elev", "Azim", "SNR", "Elev", "Azim", "SNR", "Elev", "Azim", "SNR"});
//        nmeaLabelsTable.put("GPGXA", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPHDG", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPHDT", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPHSC", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPLCD", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPMSK", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPMSS", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPMTA", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPMTW", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPMWD", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPMWV", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPOLN", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPOSD", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPR00", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPRMA", new String[] {"unknown"});
        nmeaLabelsTable.put("GPRMB", new String[] {"Status", "Cross-track error", "Org WP ID", "Dest WP ID", "Dest Lat", "Dest Long", "Range to Dest", "True bearing to Dest", "Vellocity towards Dest", "Alarm"});
        nmeaLabelsTable.put("GPRMC", new String[] {"Time Stamp", "Validity", "Latitude", "Longitude", "Speed", "True course", "Date Stamp", "Variation"});
//        nmeaLabelsTable.put("GPROT", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPRPM", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPRSA", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPRSD", new String[] {"unknown"});
        nmeaLabelsTable.put("GPRTE", new String[] {"Active route", "WP #1", "WP #2", "WP #3", "WP #4", "WP #5", "WP #6", "WP #7", "WP #8",
        "WP #9", "WP #10", "WP #11", "WP #12", "WP #13", "WP #14", "WP #15", "WP #16", "WP #17", "WP #18", "WP #19", "WP #20", "WP #21", "WP #22", "WP #23"});
//        nmeaLabelsTable.put("GPSFI", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPSTN", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPTRF", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPTTM", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPVBW", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPVDR", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPVHW", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPVLW", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPVPW", new String[] {"unknown"});
        nmeaLabelsTable.put("GPVTG", new String[] {"True Course", "Magnetic Course", "Speed knots", "Speed km/h"});
//        nmeaLabelsTable.put("GPWCV", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPWNC", new String[] {"unknown"});
        nmeaLabelsTable.put("GPWPL", new String[] {"Latitude", "Longitude"});
//        nmeaLabelsTable.put("GPXDR", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPXTE", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPXTR", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPZDA", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPZFO", new String[] {"unknown"});
//        nmeaLabelsTable.put("GPZTG", new String[] {"unknown"});
//        nmeaLabelsTable.put("HCHDG", new String[] {"unknown"});
//        nmeaLabelsTable.put("IIAPB", new String[] {"Unknown"});  // "Unknown"
//        nmeaLabelsTable.put("IIBPI", new String[] {"Unknown"});  // "Unknown"
        nmeaLabelsTable.put("IIBWC", new String[] {"UTC of fix", "Latitude of waypoint", "Longitude of waypoint",
            "True bearing to waypoint", "Magnetic bearing to waypoint", "Distance to waypoint", "Waypoint ID"});
        nmeaLabelsTable.put("IIDBT", new String[] {"Depth in feet", "Depth in meter"});
//        nmeaLabelsTable.put("IIGLL", new String[] {"Unknown"});  // "Geographic position, Latitude and Longitude"
//        nmeaLabelsTable.put("IIHCS", new String[] {"Unknown"});  // "Unknown"
        nmeaLabelsTable.put("IIHDM", new String[] {"Heading"});  // "Heading, Magnetic"
//        nmeaLabelsTable.put("IIHDT", new String[] {"Unknown"});  // "Unknown"
        nmeaLabelsTable.put("IIHSC", new String[] {"True", "Magnetic"});
//        nmeaLabelsTable.put("IIMTW", new String[] {"Unknown"});  // "Water temperature, Celcius"
        nmeaLabelsTable.put("IIMWD", new String[] {"True", "Magnetic", "Speed", "Speed"});
//        nmeaLabelsTable.put("IIMWH", new String[] {"Unknown"});  // "Wave Height"
//        nmeaLabelsTable.put("IIMWV", new String[] {"Unknown"});  // "Wind Speed and Angle"
        nmeaLabelsTable.put("IIVDR", new String[] {"True", "Magnetic", "Speed"});
        nmeaLabelsTable.put("IIVHW", new String[] {"True", "Magnetic", "Speed", "Speed"});
        nmeaLabelsTable.put("IIVPW", new String[] {"Speed"});
        nmeaLabelsTable.put("IIVTG", new String[] {"True Course", "Magnetic Course", "Speed knots", "Speed km/h"});
        nmeaLabelsTable.put("IIVWR", new String[] {"Direction", "Speed", "Speed", "Speed"});
        nmeaLabelsTable.put("IIVWT", new String[] {"Direction", "Speed", "Speed", "Speed"});
        nmeaLabelsTable.put("IIWCV", new String[] {"Speed"});
        nmeaLabelsTable.put("IIWPL", new String[] {"Latitude", "Longitude", "Name"});
        nmeaLabelsTable.put("IIXTE", new String[] {"Status", "Status", "Cross track error", "Direction to steer"});
//        nmeaLabelsTable.put("PGRMB", new String[] {"Unknown"});  // "unknown"
//        nmeaLabelsTable.put("PGRMC1", new String[] {"Unknown"});  // "unknown"
//        nmeaLabelsTable.put("PGRMC", new String[] {"Unknown"});  // "unknown"
        nmeaLabelsTable.put("PGRME", new String[] {"HPE", "VPE", "Sperical error"});
//        nmeaLabelsTable.put("PGRMF", new String[] {"Unknown"});  // "unknown"
//        nmeaLabelsTable.put("PGRMI", new String[] {"Unknown"});  // "unknown"
        nmeaLabelsTable.put("PGRMM", new String[] {"Currently active horizontal datum"});
//        nmeaLabelsTable.put("PGRMT", new String[] {"Unknown"});  // "unknown"
//        nmeaLabelsTable.put("PGRMV", new String[] {"Unknown"});  // "unknown"
        nmeaLabelsTable.put("PGRMZ", new String[] {"Altitude", "Position fix dimensions"});
//        nmeaLabelsTable.put("PSLIB", new String[] {"Unknown"});  // "unknown"


        // Fill the NMEM sentence description table
        nmeaDescriptionTable.put("GPAAM", "Waypoint Arrival Alarm");
        nmeaDescriptionTable.put("GPALM", "GPS Almanac Data");
        nmeaDescriptionTable.put("GPAPA", "Autopilot format \"A\"");
        nmeaDescriptionTable.put("GPAPB", "Autopilot format \"B\"");
        nmeaDescriptionTable.put("GPASD", "Autopilot System Data");
        nmeaDescriptionTable.put("GPBEC", "Bearing & Distance to Waypoint, Dead Reckoning");
        nmeaDescriptionTable.put("GPBOD", "Bearing, Origin to Destination");
        nmeaDescriptionTable.put("GPBWC", "Bearing & Distance to Waypoint, Great Circle");
        nmeaDescriptionTable.put("GPBWR", "Bearing & Distance to Waypoint, Rhumb Line");
        nmeaDescriptionTable.put("GPBWW", "Bearing, Waypoint to Waypoint");
        nmeaDescriptionTable.put("GPDBT", "Depth Below Transducer");
        nmeaDescriptionTable.put("GPDCN", "Decca Position");
        nmeaDescriptionTable.put("GPDPT", "Depth");
        nmeaDescriptionTable.put("GPFSI", "Frequency Set Information");
        nmeaDescriptionTable.put("GPGGA", "Global Positioning System Fix Data");
        nmeaDescriptionTable.put("GPGLC", "Geographic Position, Loran-C");
        nmeaDescriptionTable.put("GPGLL", "Geographic Position, Latitude/Longitude");
        nmeaDescriptionTable.put("GPGRS", "GPS Range Residuals");
        nmeaDescriptionTable.put("GPGSA", "GPS DOP and Active Satellites");
        nmeaDescriptionTable.put("GPGSV", "GPS Satellites in View");
        nmeaDescriptionTable.put("GPGXA", "TRANSIT Position");
        nmeaDescriptionTable.put("GPHDG", "Heading, Deviation & Variation");
        nmeaDescriptionTable.put("GPHDT", "Heading, True");
        nmeaDescriptionTable.put("GPHSC", "Heading Steering Command");
        nmeaDescriptionTable.put("GPLCD", "Loran-C Signal Data");
        nmeaDescriptionTable.put("GPMSK", "Control for a Beacon Receiver");
        nmeaDescriptionTable.put("GPMSS", "Beacon Receiver Status");
        nmeaDescriptionTable.put("GPMTA", "Air Temperature (to be phased out)");
        nmeaDescriptionTable.put("GPMTW", "Water Temperature");
        nmeaDescriptionTable.put("GPMWD", "Wind Direction");
        nmeaDescriptionTable.put("GPMWV", "Wind Speed and Angle");
        nmeaDescriptionTable.put("GPOLN", "Omega Lane Numbers");
        nmeaDescriptionTable.put("GPOSD", "Own Ship Data");
        nmeaDescriptionTable.put("GPR00", "Waypoint active route (not standard)");
        nmeaDescriptionTable.put("GPRMA", "Recommended Minimum Specific Loran-C Data");
        nmeaDescriptionTable.put("GPRMB", "Recommended Minimum Navigation Information");
        nmeaDescriptionTable.put("GPRMC", "Recommended minimum specific GPS/TRANSIT data");
        nmeaDescriptionTable.put("GPROT", "Rate of Turn");
        nmeaDescriptionTable.put("GPRPM", "Revolutions");
        nmeaDescriptionTable.put("GPRSA", "Rudder Sensor Angle");
        nmeaDescriptionTable.put("GPRSD", "RADAR System Data");
        nmeaDescriptionTable.put("GPRTE", "Routes");
        nmeaDescriptionTable.put("GPSFI", "Scanning Frequency Information");
        nmeaDescriptionTable.put("GPSTN", "Multiple Data ID");
        nmeaDescriptionTable.put("GPTRF", "TRANSIT Fix Data");
        nmeaDescriptionTable.put("GPTTM", "Tracked Target Message");
        nmeaDescriptionTable.put("GPVBW", "Dual Ground/Water Speed");
        nmeaDescriptionTable.put("GPVDR", "Set and Drift");
        nmeaDescriptionTable.put("GPVHW", "Water Speed and Heading");
        nmeaDescriptionTable.put("GPVLW", "Distance Traveled through the Water");
        nmeaDescriptionTable.put("GPVPW", "Speed, Measured Parallel to Wind");
        nmeaDescriptionTable.put("GPVTG", "Track Made Good and Ground Speed");
        nmeaDescriptionTable.put("GPWCV", "Waypoint Closure Velocity");
        nmeaDescriptionTable.put("GPWNC", "Distance, Waypoint to Waypoint");
        nmeaDescriptionTable.put("GPWPL", "Waypoint Location");
        nmeaDescriptionTable.put("GPXDR", "Transducer Measurements");
        nmeaDescriptionTable.put("GPXTE", "Cross-Track Error, Measured");
        nmeaDescriptionTable.put("GPXTR", "Cross-Track Error, Dead Reckoning");
        nmeaDescriptionTable.put("GPZDA", "UTC Date / Time and Local Time Zone Offset");
        nmeaDescriptionTable.put("GPZFO", "UTC & Time from Origin Waypoint");
        nmeaDescriptionTable.put("GPZTG", "UTC & Time to Destination Waypoint");
        nmeaDescriptionTable.put("HCHDG", "Compass Heading");
        nmeaDescriptionTable.put("PGRMB", "DGPS Beacon Information");
        nmeaDescriptionTable.put("PGRMC1", "Additional Sensor Configuration Information");
        nmeaDescriptionTable.put("PGRMC", "Sensor Configuration Information");
        nmeaDescriptionTable.put("PGRME", "Estimated Position Error");
        nmeaDescriptionTable.put("PGRMF", "GPS Position Fix Data");
        nmeaDescriptionTable.put("PGRMI", "Sensor Initialization Information");
        nmeaDescriptionTable.put("PGRMM", "Map Datum");
        nmeaDescriptionTable.put("PGRMT", "Sensor Status Information");
        nmeaDescriptionTable.put("PGRMV", "3D Velocity Information");
        nmeaDescriptionTable.put("PGRMZ", "Altitude Information");
        nmeaDescriptionTable.put("PSLIB", "Tune DPGS Beacon Receiver");
        nmeaDescriptionTable.put("IIAPB", "Autopilot format B");
        nmeaDescriptionTable.put("IIBPI", "Unknown");
        nmeaDescriptionTable.put("IIBWC", "Bearing and distance to waypoint - great circle");
        nmeaDescriptionTable.put("IIDBT", "Depth below transducer");
        nmeaDescriptionTable.put("IIGLL", "Geographic position, Latitude and Longitude");
        nmeaDescriptionTable.put("IIHCS", "Unknown");
        nmeaDescriptionTable.put("IIHDM", "Heading, Magnetic");
        nmeaDescriptionTable.put("IIHDT", "Unknown");
        nmeaDescriptionTable.put("IIHSC", "Heading Steering Command");
        nmeaDescriptionTable.put("IIMTW", "Water temperature, Celcius");
        nmeaDescriptionTable.put("IIMWD", "Wind Direction and Speed");
        nmeaDescriptionTable.put("IIMWH", "Wave Height");
        nmeaDescriptionTable.put("IIMWV", "Wind Speed and Angle");
        nmeaDescriptionTable.put("IIVDR", "Set and Drift");
        nmeaDescriptionTable.put("IIVHW", "Water speed and heading");
        nmeaDescriptionTable.put("IIVPW", "Speed – Measured Parallel to Wind");
        nmeaDescriptionTable.put("IIVTG", "Track made good and ground speed");
        nmeaDescriptionTable.put("IIVWR", "Relative wind direction and speed");
        nmeaDescriptionTable.put("IIVWT", "True Wind Speed and Angle");
        nmeaDescriptionTable.put("IIWCV", "Waypoint Closure Velocity");
        nmeaDescriptionTable.put("IIWPL", "Waypoint Location");
        nmeaDescriptionTable.put("IIXTE", "Cross-Track Error, Measured");
    }

    /**
     * Get NMEA description
     * @param nmeaType
     * @return
     */
    public String description(String nmeaType) {
        return (String)nmeaDescriptionTable.get(nmeaType);
    }

    /**
     * Get NMEA labels
     * @param nmeaType
     * @return
     */
    public String[] labels(String nmeaType) {
        return (String[])nmeaLabelsTable.get(nmeaType);
    }

    public void updatePanel(String nmeaStr, nmeaPanel nmeaPanel) {
//                System.out.println("portNr " + portNr + " " + nmeaType);
        String nmeaType = nmeaStr.substring(0, nmeaStr.indexOf(','));
        if (this.description(nmeaType) != null) {
            // Add missing NMEA panel
            if (!nmeaPanel.contains(nmeaType)) {
                nmeaPanel.addPanel(nmeaType, this.description(nmeaType), this.labels(nmeaType));
            }

            if (nmeaType.equalsIgnoreCase("xxx")) {
//                } else if (nmeaType.equalsIgnoreCase("GPAAM")) {  // "Waypoint Arrival Alarm\n");
//                } else if (nmeaType.equalsIgnoreCase("GPALM")) {  // "GPS Almanac Data\n");
//                } else if (nmeaType.equalsIgnoreCase("GPAPA")) {  // "Autopilot format \"A\"\n");
//                } else if (nmeaType.equalsIgnoreCase("GPAPB")) {  // "Autopilot format \"B\"\n");
//                } else if (nmeaType.equalsIgnoreCase("GPASD")) {  // "Autopilot System Data\n");
//                } else if (nmeaType.equalsIgnoreCase("GPBEC")) {  // "Bearing & Distance to Waypoint, Dead Reckoning\n");
            } else if (nmeaType.equalsIgnoreCase("GPBOD")) {  // "Bearing, Origin to Destination\n");
                gpbodUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPBWC")) {  // "Bearing & Distance to Waypoint, Great Circle\n");
//                } else if (nmeaType.equalsIgnoreCase("GPBWR")) {  // "Bearing & Distance to Waypoint, Rhumb Line\n");
//                } else if (nmeaType.equalsIgnoreCase("GPBWW")) {  // "Bearing, Waypoint to Waypoint\n");
//                } else if (nmeaType.equalsIgnoreCase("GPDBT")) {  // "Depth Below Transducer\n");
//                } else if (nmeaType.equalsIgnoreCase("GPDCN")) {  // "Decca Position\n");
//                } else if (nmeaType.equalsIgnoreCase("GPDPT")) {  // "Depth\n");
//                } else if (nmeaType.equalsIgnoreCase("GPFSI")) {  // "Frequency Set Information\n");
            } else if (nmeaType.equalsIgnoreCase("GPGGA")) {  // "Global Positioning System Fix Data"
                gpggaUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPGLC")) {  // "Geographic Position, Loran-C\n");
            } else if (nmeaType.equalsIgnoreCase("GPGLL")) {  // "Geographic Position, Latitude/Longitude"
                gpgllUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPGRS")) {  // "GPS Range Residuals\n");
            } else if (nmeaType.equalsIgnoreCase("GPGSA")) {  // "GPS DOP and Active Satellites"
                gpgsaUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPGST")) {  // "GPS Pseudorange Noise Statistics\n");
            } else if (nmeaType.equalsIgnoreCase("GPGSV")) {   // GPS Satellites in View
//                    MStringTokenizer tokens = new MStringTokenizer(str, ",*");
//                    tokens.nextToken(); // Skip "GPGSV"
//                    tokens.nextToken(); // Skip number of messages
//                    String tempStr = tokens.nextToken();
//                    if (!nmeaPanel.contains("GPGSV" + tempStr)) {
//                        String[] gpgsvLabels = {"Nr Sat",
//                        "Elev", "Azim", "SNR", "Elev", "Azim", "SNR",
//                        "Elev", "Azim", "SNR", "Elev", "Azim", "SNR"};
//                        nmeaPanel.addPanel("GPGSV" + tempStr, "GPS Satellites in View #" + tempStr, gpgsvLabels);
//                    }
                gpgsvUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPGXA")) {  // "TRANSIT Position\n");
//                } else if (nmeaType.equalsIgnoreCase("GPHDG")) {  // "Heading, Deviation & Variation\n");
//                } else if (nmeaType.equalsIgnoreCase("GPHDT")) {  // "Heading, True\n");
//                } else if (nmeaType.equalsIgnoreCase("GPHSC")) {  // "Heading Steering Command\n");
//                } else if (nmeaType.equalsIgnoreCase("GPLCD")) {  // "Loran-C Signal Data\n");
//                } else if (nmeaType.equalsIgnoreCase("GPMSK")) {  // "Control for a Beacon Receiver\n");
//                } else if (nmeaType.equalsIgnoreCase("GPMSS")) {  // "Beacon Receiver Status\n");
//                } else if (nmeaType.equalsIgnoreCase("GPMTA")) {  // "Air Temperature (to be phased out)\n");
//                } else if (nmeaType.equalsIgnoreCase("GPMTW")) {  // "Water Temperature\n");
//                } else if (nmeaType.equalsIgnoreCase("GPMWD")) {  // "Wind Direction\n");
//                } else if (nmeaType.equalsIgnoreCase("GPMWV")) {  // "Wind Speed and Angle\n");
//                } else if (nmeaType.equalsIgnoreCase("GPOLN")) {  // "Omega Lane Numbers\n");
//                } else if (nmeaType.equalsIgnoreCase("GPOSD")) {  // "Own Ship Data\n");
//                } else if (nmeaType.equalsIgnoreCase("GPR00")) {  // "Waypoint active route (not standard)\n");
//                } else if (nmeaType.equalsIgnoreCase("GPRMA")) {  // "Recommended Minimum Specific Loran-C Data\n");
            } else if (nmeaType.equalsIgnoreCase("GPRMB")) {  // "Recommended Minimum Navigation Information\n");
                gprmbUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("GPRMC")) {  // "Recommended Minimum Specific GPS/TRANSIT Data"
                gprmcUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPROT")) {  // "Rate of Turn\n");
//                } else if (nmeaType.equalsIgnoreCase("GPRPM")) {  // "Revolutions\n");
//                } else if (nmeaType.equalsIgnoreCase("GPRSA")) {  // "Rudder Sensor Angle\n");
//                } else if (nmeaType.equalsIgnoreCase("GPRSD")) {  // "RADAR System Data\n");
            } else if (nmeaType.equalsIgnoreCase("GPRTE")) {  // "Routes\n");
                gprteUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPSFI")) {  // "Scanning Frequency Information\n");
//                } else if (nmeaType.equalsIgnoreCase("GPSTN")) {  // "Multiple Data ID\n");
//                } else if (nmeaType.equalsIgnoreCase("GPTRF")) {  // "TRANSIT Fix Data\n");
//                } else if (nmeaType.equalsIgnoreCase("GPTTM")) {  // "Tracked Target Message\n");
//                } else if (nmeaType.equalsIgnoreCase("GPVBW")) {  // "Dual Ground/Water Speed\n");
//                } else if (nmeaType.equalsIgnoreCase("GPVDR")) {  // "Set and Drift\n");
//                } else if (nmeaType.equalsIgnoreCase("GPVHW")) {  // "Water Speed and Heading\n");
//                } else if (nmeaType.equalsIgnoreCase("GPVLW")) {  // "Distance Traveled through the Water\n");
//                } else if (nmeaType.equalsIgnoreCase("GPVPW")) {  // "Speed, Measured Parallel to Wind\n");
            } else if (nmeaType.equalsIgnoreCase("GPVTG")) {  // "Track Made Good and Ground Speed"
                gpvtgUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPWCV")) {  // "Waypoint Closure Velocity\n");
//                } else if (nmeaType.equalsIgnoreCase("GPWNC")) {  // "Distance, Waypoint to Waypoint\n");
            } else if (nmeaType.equalsIgnoreCase("GPWPL")) {  // "Waypoint Location\n");
                gpwplUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("GPXDR")) {  // "Transducer Measurements\n");
//                } else if (nmeaType.equalsIgnoreCase("GPXTE")) {  // "Cross-Track Error, Measured\n");
//                } else if (nmeaType.equalsIgnoreCase("GPXTR")) {  // "Cross-Track Error, Dead Reckoning\n");
//                } else if (nmeaType.equalsIgnoreCase("GPZDA")) {  // "UTC Date / Time and Local Time Zone Offset\n");
//                } else if (nmeaType.equalsIgnoreCase("GPZFO")) {  // "UTC & Time from Origin Waypoint\n");
//                } else if (nmeaType.equalsIgnoreCase("GPZTG")) {  // "UTC & Time to Destination Waypoint\n");
//                } else if (nmeaType.equalsIgnoreCase("HCHDG")) {  // "Compass Heading\n");
//                } else if (nmeaType.equalsIgnoreCase("PGRMB")) {  // "DGPS Beacon Information\n");
//                } else if (nmeaType.equalsIgnoreCase("PGRMC1")) {  // "Additional Sensor Configuration Information\n");
//                } else if (nmeaType.equalsIgnoreCase("PGRMC")) {  // "Sensor Configuration Information\n");
//                } else if (nmeaType.equalsIgnoreCase("IIAPB")) {  // "Unknown"
//                } else if (nmeaType.equalsIgnoreCase("IIBPI")) {  // "Unknown"
            } else if (nmeaType.equalsIgnoreCase("IIBWC")) {  // "UTC of fix", "Latitude of waypoint", "Longitude of waypoint",
                iibwcUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIDBT")) {  // "Depth below transducer"
                iidbtUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("IIGLL")) {  // "Geographic position, Latitude and Longitude"
//                } else if (nmeaType.equalsIgnoreCase("IIHCS")) {  // "Unknown"
            } else if (nmeaType.equalsIgnoreCase("IIHDM")) {  // "Heading, Magnetic"
                iihdmUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("IIHDT")) {  // "Unknown"
            } else if (nmeaType.equalsIgnoreCase("IIHSC")) {  // "Heading Steering Command"
                iihscUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("IIMTW")) {  // "Water temperature, Celcius"
            } else if (nmeaType.equalsIgnoreCase("IIMWD")) {  // "Wind Direction and Speed"
                iimwdUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("IIMWH")) {  // "Wave Height"
//                } else if (nmeaType.equalsIgnoreCase("IIMWV")) {  // "Wind Speed and Angle"
            } else if (nmeaType.equalsIgnoreCase("IIVDR")) {  // "Set and Drift"
                iivdrUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIVHW")) {  // "Water speed and heading"
                iivhwUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIVPW")) {  // "Speed – Measured Parallel to Wind"
                iivpwUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIVTG")) {  // "Track made good and ground speed"
                iivtgUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIVWR")) {  // "Relative wind direction and speed"
                iivwrUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIVWT")) {  // "True Wind Speed and Angle"
                iivwtUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIWCV")) {  // "Waypoint Closure Velocity"
                iiwcvUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIWPL")) {  // "Waypoint Location"
                iiwplUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("IIXTE")) {  // "Cross-Track Error, Measured"
                iixteUpdate(nmeaPanel, nmeaStr);
            } else if (nmeaType.equalsIgnoreCase("PGRME")) {  // "Estimated Position Error"
                pgrmeUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("PGRMF")) {  // "GPS Position Fix Data"
//                } else if (nmeaType.equalsIgnoreCase("PGRMI")) {  // "Sensor Initialization Information"
            } else if (nmeaType.equalsIgnoreCase("PGRMM")) {  // "Map Datum"
                pgrmmUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("PGRMT")) {  // "Sensor Status Information"
//                } else if (nmeaType.equalsIgnoreCase("PGRMV")) {  // "3D Velocity Information"
            } else if (nmeaType.equalsIgnoreCase("PGRMZ")) {  // "Altitude Information"
                pgrmzUpdate(nmeaPanel, nmeaStr);
//                } else if (nmeaType.equalsIgnoreCase("PSLIB")) {  // "Tune DPGS Beacon Receiver"
            } else {
                gpxxxUpdate(nmeaPanel, nmeaType, nmeaStr);
            }
        } else if (nmeaStr != null) {
            System.out.println("Unknown NMEA sentence");
            System.out.println("  " + nmeaStr + "\n");
        }

    }

    public class MStringTokenizer extends StringTokenizer {

        private String delim;

        public MStringTokenizer(String str, String d) {
            super(str, d, true);
            delim = new String(d);
        }

        @Override
        public String nextToken() {
            if (super.hasMoreTokens() == false) {
                return null;
            }
            String str = super.nextToken();
            if (delim.contains(str)) {
                return null;
            } else {
                if (super.hasMoreTokens()) {
                    super.nextToken();  // Skip next 'delim'
                }
                return str;
            }
        }
    }

    private String[] parseNmea(String nmeaStr) {
        Vector<String> nmeaVector = new Vector();
        return new String[2];
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpbodUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPBOD";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPBOD
        tokens.nextToken();     // Skip first

        // Bearing true
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Bearing magnetic
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Destination
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken());

        // Start
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken());
    }

    /**
     * 
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpggaUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPGGA";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPGGA
        tokens.nextToken();     // Skip first

        // Time
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken());

        // Latitude
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Longitude
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());

        // Fix quality
        String tempStr = tokens.nextToken();
        if (tempStr.equalsIgnoreCase("0")) {
            nmeaPanel.setLabelText(nmeaType, 3, "Invalid");
        } else if (tempStr.equalsIgnoreCase("1")) {
            nmeaPanel.setLabelText(nmeaType, 3, "GPS Fix");
        } else if (tempStr.equalsIgnoreCase("1")) {
            nmeaPanel.setLabelText(nmeaType, 3, "DGPS Fix");
        }

        // Number of Satellites
        nmeaPanel.setLabelText(nmeaType, 4, tokens.nextToken());

        // HDOP
        nmeaPanel.setLabelText(nmeaType, 5, tokens.nextToken());

        // Altitude
        nmeaPanel.setLabelText(nmeaType, 6, tokens.nextToken() + tokens.nextToken());

        // Height of geoid above WGS84 ellipsoid
        nmeaPanel.setLabelText(nmeaType, 7, tokens.nextToken() + tokens.nextToken());

    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpgllUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPGLL";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPGLL
        tokens.nextToken();     // Skip first

        // Latitude
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Longitude
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Time
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken());

        // Fix quality
        String tempStr = tokens.nextToken();
        if (tempStr.equalsIgnoreCase("A")) {
            nmeaPanel.setLabelText(nmeaType, 3, "Valid");
        } else {
            nmeaPanel.setLabelText(nmeaType, 3, "Invalid");
        }
    }

    /**
     * 
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpgsaUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPGSA";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPGSA
        tokens.nextToken();     // Skip first

        // Mode
        String tempStr = tokens.nextToken() + tokens.nextToken();
        if (tempStr.equalsIgnoreCase("M1")) {
            nmeaPanel.setLabelText(nmeaType, 0, "Fix not available");
        } else if (tempStr.equalsIgnoreCase("M2")) {
            nmeaPanel.setLabelText(nmeaType, 0, "Manual 2D");
        } else if (tempStr.equalsIgnoreCase("M3")) {
            nmeaPanel.setLabelText(nmeaType, 0, "Manual 3D");
        } else if (tempStr.equalsIgnoreCase("A1")) {
            nmeaPanel.setLabelText(nmeaType, 0, "Fix not available");
        } else if (tempStr.equalsIgnoreCase("A2")) {
            nmeaPanel.setLabelText(nmeaType, 0, "Automatic 2D");
        } else if (tempStr.equalsIgnoreCase("A3")) {
            nmeaPanel.setLabelText(nmeaType, 0, "Automatic 3D");
        } else {
            nmeaPanel.setLabelText(nmeaType, 0, "unknown");
        }

        // PRN of Satellite Vehicles
        for (int i = 1; i <= 12; i++) {
            tempStr = tokens.nextToken();
            if (tempStr != null) {
                nmeaPanel.setLabelText(nmeaType, i, tempStr);
            } else {
                nmeaPanel.setLabelText(nmeaType, i, "-");
            }
        }

        // PDOP
        nmeaPanel.setLabelText(nmeaType, 13, tokens.nextToken());

        // HDOP
        nmeaPanel.setLabelText(nmeaType, 14, tokens.nextToken());

        // VDOP
        nmeaPanel.setLabelText(nmeaType, 15, tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpgsvUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPGSV";

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPGSV
        tokens.nextToken();     // Skip first

        tokens.nextToken();     // Skip nr msg

        int msgNr = Integer.valueOf(tokens.nextToken());

        // Total number of SV's in view
        nmeaPanel.setTitleText(nmeaType, "GPS Satellites in View #" + tokens.nextToken());
//        nmeaPanel.setLabelText(nmeaType + msgNr, 0, tokens.nextToken());

        for (int i = 0; i < 4; i++) {
            String pnrStr = tokens.nextToken();
            // PRN x Elevation
            nmeaPanel.setBorderText(nmeaType, (msgNr - 1)*4*3 + i*3 + 0, "#" + pnrStr + " Elevation");
            nmeaPanel.setLabelText(nmeaType, (msgNr - 1)*4*3 + i*3 + 0, tokens.nextToken());
            // PRN x Azimuth
            nmeaPanel.setBorderText(nmeaType, (msgNr - 1)*4*3 + i*3 + 1, "#" + pnrStr + " Azimimuth");
            nmeaPanel.setLabelText(nmeaType, (msgNr - 1)*4*3 + i*3 + 1, tokens.nextToken());
            // PRN x SNR
            nmeaPanel.setBorderText(nmeaType, (msgNr - 1)*4*3 + i*3 + 2, "#" + pnrStr + " SNR");
            nmeaPanel.setLabelText(nmeaType, (msgNr - 1)*4*3 + i*3 + 2, tokens.nextToken());
        }

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);
    }

    /**
     * 
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gprmbUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPRMB";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPRMB
        tokens.nextToken();     // Skip first

        // Status
        if (tokens.nextToken().equalsIgnoreCase("A")) {
            nmeaPanel.setLabelText(nmeaType, 0, "OK");
        } else {
            nmeaPanel.setLabelText(nmeaType, 0, "Warning");
        }

        // Cross-track error
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + " " + tokens.nextToken());

        // Origin waypoint ID
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken());

        // Destination waypoint ID
        nmeaPanel.setLabelText(nmeaType, 3, tokens.nextToken());

        // Destination waypoint Latitude
        nmeaPanel.setLabelText(nmeaType, 4, tokens.nextToken() + tokens.nextToken());

        // Destination waypoint Longitude
        nmeaPanel.setLabelText(nmeaType, 5, tokens.nextToken() + tokens.nextToken());

        // Range to destination
        nmeaPanel.setLabelText(nmeaType, 6, tokens.nextToken() + "nm");

        // True bearing to destination
        nmeaPanel.setLabelText(nmeaType, 7, tokens.nextToken() + "\u00B0");

        // Velocity towards destination
        nmeaPanel.setLabelText(nmeaType, 8, tokens.nextToken() + "kn");


        if (tokens.nextToken().equalsIgnoreCase("A")) {
            nmeaPanel.setLabelText(nmeaType, 9, "Arrived");
        } else {
            nmeaPanel.setLabelText(nmeaType, 9, "Not arrived");
        }
    }

    /**
     * 
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gprmcUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPRMC";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPRMC
        tokens.nextToken();     // Skip first

        // Time Stamp
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken());

        // Validity
        if (tokens.nextToken().equalsIgnoreCase("A")) {
            nmeaPanel.setLabelText(nmeaType, 1, "Valid");
        } else {
            nmeaPanel.setLabelText(nmeaType, 1, "Invalid");
        }

        // Current Latitude
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());

        // Current Longitude
        nmeaPanel.setLabelText(nmeaType, 3, tokens.nextToken() + tokens.nextToken());

        // Speed in knots
        nmeaPanel.setLabelText(nmeaType, 4, tokens.nextToken() + " Knots");

        // True Course
        nmeaPanel.setLabelText(nmeaType, 5, tokens.nextToken() + "\u00B0");

        // Date Stamp
        nmeaPanel.setLabelText(nmeaType, 6, tokens.nextToken());

        // Variation
        nmeaPanel.setLabelText(nmeaType, 7, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gprteUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPRTE";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPRTE
        tokens.nextToken();     // Skip first

        tokens.nextToken();     // Skip numer of sentences

        int setNr = Integer.parseInt(tokens.nextToken());

        // Active route
        if (tokens.nextToken().equalsIgnoreCase("C")) {
            nmeaPanel.setBorderText(nmeaType, 0, "Active route");
        } else {
            nmeaPanel.setBorderText(nmeaType, 0, "Waypoint list");
        }

        // Name of active route
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken());

        for (int i = 1; (i < 16) && tokens.hasMoreTokens(); i++) {
            nmeaPanel.setLabelText(nmeaType, i, tokens.nextToken());
        }
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpvtgUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPVTG";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPVTG
        tokens.nextToken();     // Skip first

        // True Course Made Good
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + "\u00B0");
        tokens.nextToken();     // Skip "T"

        // Magnetic Course Made Good
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + "\u00B0");
        tokens.nextToken();     // Skip "M"

        // Ground Speed
        String speedStr = tokens.nextToken();
        if (tokens.nextToken().equalsIgnoreCase("N")) {
            speedStr += " Knots";
        } else {
            speedStr += " km/h";
        }
        nmeaPanel.setLabelText(nmeaType, 2, speedStr);

        // Ground Speed
        speedStr = tokens.nextToken();
        if (tokens.nextToken().equalsIgnoreCase("N")) {
            speedStr += " Knots";
        } else {
            speedStr += " km/h";
        }
        nmeaPanel.setLabelText(nmeaType, 3, speedStr);
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void gpwplUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "GPWPL";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPWPL
        tokens.nextToken();     // Skip first

        // Latitude
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Longitude
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Waypoint name
        nmeaPanel.setTitleText(nmeaType, "Waypoint \"" + tokens.nextToken() + "\" Location");
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iibwcUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIBWC";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIBWC
        tokens.nextToken();     // Skip first

        // UTC
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken());
        
        // Latitude
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());
        
        // Longitude
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());
        
        // Bearing to waypoint, degrees true
        nmeaPanel.setLabelText(nmeaType, 3, tokens.nextToken() + tokens.nextToken());
        
        // Bearing to waypoint, degrees magnetic
        nmeaPanel.setLabelText(nmeaType, 4, tokens.nextToken() + tokens.nextToken());
        
        // Distance to waypoint, Nautical miles
        nmeaPanel.setLabelText(nmeaType, 5, tokens.nextToken() + tokens.nextToken());
        
        // Waypoint ID
        nmeaPanel.setLabelText(nmeaType, 6, tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iidbtUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIDBT";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIDBT
        tokens.nextToken();     // Skip first

        // Depth in feet
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Depth in meter
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iihdmUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIHDM";

        // Raw NMEA data
        System.out.println("HDM: " + str);
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIHDM
        tokens.nextToken();     // Skip first

        // Heading magnetic
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iihscUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIHSC";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIHSC
        tokens.nextToken();     // Skip first

        // Direction true
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Direction magnetic
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iimwdUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIMWD";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIMWD
        tokens.nextToken();     // Skip first

        // Direction true
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Direction magnetic
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Speed in knots
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());

        // Speed in m/s
        nmeaPanel.setLabelText(nmeaType, 3, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iivdrUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIVDR";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIVDR
        tokens.nextToken();     // Skip first

        // Direction true
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Direction magnetic
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Speed in knots
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iivhwUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIVHW";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIVHW
        tokens.nextToken();     // Skip first

        // Direction true
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Direction magnetic
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Speed in knots
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());

        // Speed in km/h
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iivpwUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIVPW";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIVPW
        tokens.nextToken();     // Skip first

        // Speed knots
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Speed m/s
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iivtgUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIVTG";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $GPVTG
        tokens.nextToken();     // Skip first

        // True Course Made Good
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + "\u00B0");
        tokens.nextToken();     // Skip "T"

        // Magnetic Course Made Good
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + "\u00B0");
        tokens.nextToken();     // Skip "M"

        // Ground Speed
        String speedStr = tokens.nextToken();
        if (tokens.nextToken().equalsIgnoreCase("N")) {
            speedStr += " Knots";
        } else {
            speedStr += " km/h";
        }
        nmeaPanel.setLabelText(nmeaType, 2, speedStr);

        // Ground Speed
        speedStr = tokens.nextToken();
        if (tokens.nextToken().equalsIgnoreCase("N")) {
            speedStr += " Knots";
        } else {
            speedStr += " km/h";
        }
        nmeaPanel.setLabelText(nmeaType, 3, speedStr);
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iivwrUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIVWR";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIVWR
        tokens.nextToken();     // Skip first

        // Wind direction
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Speed knots
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Speed m/s
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());

        // Speed km/h
        nmeaPanel.setLabelText(nmeaType, 3, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iivwtUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIVWT";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIVWT
        tokens.nextToken();     // Skip first

        // Wind direction
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Speed knots
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Speed m/s
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());

        // Speed km/h
        nmeaPanel.setLabelText(nmeaType, 3, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iiwcvUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIWCV";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIWCV
        tokens.nextToken();     // Skip first

        // Speed knots
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iiwplUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIWPL";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIWPL
        tokens.nextToken();     // Skip first

        // Latitude
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // Longitude
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Name
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void iixteUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "IIXTE";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $IIXTE
        tokens.nextToken();     // Skip first

        // Status
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken());

        // Status
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken());

        // Cross track error magnutude
        String magnStr = tokens.nextToken(); // magnitude
        String steerStr = tokens.nextToken(); // steer
        String unitStr = tokens.nextToken(); // unit
        nmeaPanel.setLabelText(nmeaType, 2, magnStr + unitStr);

        // Direction to steer
        if (steerStr.equals("L")) {
            nmeaPanel.setLabelText(nmeaType, 2, "Left");
        } else {
            nmeaPanel.setLabelText(nmeaType, 2, "Right");
        }
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void pgrmeUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "PGRME";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $PGRME
        tokens.nextToken();     // Skip first

        // HPE
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        // VPE
        nmeaPanel.setLabelText(nmeaType, 1, tokens.nextToken() + tokens.nextToken());

        // Spherical error
        nmeaPanel.setLabelText(nmeaType, 2, tokens.nextToken() + tokens.nextToken());
    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void pgrmmUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "PGRMM";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $PGRMM
        tokens.nextToken();     // Skip first

        // Currently active horizontal datum
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken());
    }

    /**
     * 
     * @param nmeaPanel
     * @param nmeaStr
     */
    private void pgrmzUpdate(nmeaPanel nmeaPanel, String nmeaStr) {
        String str = new String(nmeaStr);
        String nmeaType = "PGRMZ";

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);

        MStringTokenizer tokens = new MStringTokenizer(str, ",*");

        // $PGRMZ
        tokens.nextToken();     // Skip first

        // Altitude
        nmeaPanel.setLabelText(nmeaType, 0, tokens.nextToken() + tokens.nextToken());

        if (tokens.nextToken().equalsIgnoreCase("2")) {
            nmeaPanel.setLabelText(nmeaType, 1, "user altitude");
        } else {
            nmeaPanel.setLabelText(nmeaType, 1, "GPS altitude");
        }

    }

    /**
     *
     * @param nmeaPanel
     * @param nmeaType
     * @param nmeaStr
     */
    private void gpxxxUpdate(nmeaPanel nmeaPanel,String nmeaType, String nmeaStr) {
        String str = new String(nmeaStr);

        // Raw NMEA data
        nmeaPanel.setNmeaText(nmeaType, str);
    }

}

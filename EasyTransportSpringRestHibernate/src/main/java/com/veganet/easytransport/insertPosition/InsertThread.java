/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.insertPosition;

//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.time;
//import com.sun.xml.internal.ws.dump.LoggingDumpTube;
import com.veganet.easytransport.controller.LoginController;
import com.veganet.easytransport.dao.impl.DriverplanningDaoImpl;
import com.veganet.easytransport.dao.impl.PositionDao;
import com.veganet.easytransport.dao.impl.TransportDaoImpl;
import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Journey;
import com.veganet.easytransport.entities.Line;
import com.veganet.easytransport.entities.Positions;
import com.veganet.easytransport.entities.Station;
import com.veganet.easytransport.entities.Transport;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import static java.time.LocalDateTime.now;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author asus
 */
@Component
@Scope("prototype")
public class InsertThread extends Thread {
//

    double radius = 0.002;

    int device_id =1;
    String stationStart;
    String stationEnd;

    @Autowired
    TransportDaoImpl transportDao;
    @Autowired
    DriverplanningDaoImpl driverplanningDao;
    @Autowired
    PositionDao positionDao;

    private static String url = "jdbc:mysql://localhost:3306/easytransportdb";
    private static String driverName = "com.mysql.jdbc.Driver";
    private static String username = "root";
    private static String password = "";
    /*
     private static String url = "jdbc:mysql://localhost:3306/easytransportdb";
     private static String driverName = "com.mysql.jdbc.Driver";
     private static String username = "root";
     private static String password = "";
     */

    @Override
    public void run() {
        double longitude = 10.613083333333334;
        double latitude = 35.836037777777776;
        double lastlongitude = longitude;
        double lastlatitude = latitude;
        double course = 250;
        int nbPosition = 0;
        java.util.Date date = new java.util.Date();
        int id = 8025;
//        Transport device = transportDao.findOne(device_id);
//        System.out.println("transport name " + device.getName());
//        Driverplanning driverplanning = driverplanningDao.searchByTrain(device.getName()); // searchByTrain(device.getName());
//        System.out.println("driverplanning " + driverplanning.getPlanningId());
//        List<Station> lineStations = driverplanningDao.searchStations(stationStart, stationEnd);
//        System.out.println(" line stations " + lineStations.get(0).getStationName());
        System.out.println(getName() + " is running");

        try {
            Class.forName(driverName);
            try {
                Connection con = DriverManager.getConnection(
                        url, username, password);
                Statement stmt = con.createStatement();
                Statement stmt2 = con.createStatement();

                while (true) {
                    //
                    if (nbPosition < 30) {
                        longitude = longitude + 0.001;

                    } else if (nbPosition < 60) {
                        latitude = latitude + 0.001;
                    } else if (nbPosition < 90) {
                        longitude = longitude - 0.001;
                    } else if (nbPosition < 120) {
                        latitude = latitude - 0.001;

                    } else {
                        longitude = 10.613083333333334;
                        latitude = 35.836037777777776;
                        nbPosition = 0;
                    }
                    nbPosition++;

                    //
                    long time = System.currentTimeMillis();
                    java.sql.Timestamp timestamp = new java.sql.Timestamp(time);
                    System.out.println("Time in milliseconds :" + timestamp);

                    //logitude = logitude + 0.001;
                    int rs = stmt.executeUpdate("INSERT INTO `positions` ( `altitude`, `course`,`latitude`, `longitude`, `other`, `POWER`, "
                            + "`speed`, `time`, `valid`, `device_id`) VALUES"
                            + "("+0+", " + course + ", " + latitude + ", " + longitude + ", '<info><sat>5</sat><acc>true</acc><mcc>605</mcc><mnc>2</mnc><lac>130</lac><cell>1352</cell><index>536</index><ip>197.7.13.6</ip></info>', 0, 0, '" + timestamp + "', 1,  1);");
                    // System.out.println(" insertion done");
                           // id++;
                    System.out.println("test");
                    //   System.out.println("station long " + LoginController.stationStartLong);
                    //   System.out.println("station lat " + LoginController.stationStartLat);
//test in or out station start 
                    if (nbPosition != 0) {
                    //    System.out.println("current long " + longitude);
                        //   System.out.println("current lat " + latitude);
                        //    System.out.println("last long " + lastlongitude);
                        // System.out.println("last lat " + lastlatitude);

                        //test if out => in false => true 
                        Boolean test = checkPositionInsideCircle(lastlongitude, lastlatitude, LoginController.stationStartLong, LoginController.stationStartLat, radius);
                        System.out.println("Station start test 1 " + test);
                        if (test == false) {
                            Boolean test2 = checkPositionInsideCircle(longitude, latitude, LoginController.stationStartLong, LoginController.stationStartLat, radius);
                            System.out.println("Station start test 2 " + test);

                            if (test2) {
                                System.out.println("Station start test out => in");

                                java.sql.Timestamp thTime = new java.sql.Timestamp(LoginController.startDate.getTime());
                                System.out.println("theorique time " + thTime);
                                System.out.println("creation time " + timestamp);
                                long diff = Math.abs(timestamp.getTime() - thTime.getTime());
                                System.out.print("start " + diff + " long,  ");

                                long diffSeconds = diff / 1000;
                                long diffMinutes = diff / (60 * 1000);

                                System.out.print("start " + diffMinutes + " minutes,  ");
                                System.out.print("start " + diffSeconds + " seconds.");
                                long diffHours = diff / (60 * 60 * 1000);
                                System.out.print("start " + diffHours + " hr,  ");

                                //   System.out.println("retard : ");
                            }
                        }
                        // test if in => out
                        if (test) {
                            Boolean test2 = checkPositionInsideCircle(longitude, latitude, LoginController.stationStartLong, LoginController.stationStartLat, radius);
                            System.out.println("Station start test 2 " + test);

                            if (test2 == false) {
                                System.out.println("Station start test in => out");

                                java.sql.Timestamp thTime = new java.sql.Timestamp(LoginController.startDate.getTime());
                                long diff = Math.abs(timestamp.getTime() - thTime.getTime());
                                System.out.println("theorique time " + thTime);
                                System.out.println("creation time " + timestamp);
                                System.out.print("start " + diff + " long,  ");

                                long diffSeconds = diff / 1000;
                                int diffMinutes = (int) (diff / (60 * 1000));
                                long diffHours = diff / (60 * 60 * 1000);
                                System.out.print("start " + diffHours + " hr,  ");

                                System.out.print("start " + diffMinutes + " minutes,  ");
                                System.out.print("start " + diffSeconds + " seconds.");
                                //   System.out.println("retard : ");
                               // String updateTableSQL = "UPDATE transport SET DELAY = "+diffMinutes+" AND REALTIMESTART= "+ timestamp+" WHERE TRANSPORT_ID = "+device_id;

                            //  int rs2= stmt.executeUpdate(updateTableSQL);
/*
                                  for (Station col : LoginController.linesStation) {
                                   Boolean test3 = checkPositionInsideCircle(longitude, latitude, col.getLongitude(), col.getLatitude(), radius);
                            System.out.println("Station start test 3 " + test);

                            if (test3 == false) {
                                System.out.println("Station start test in => out");

                                java.sql.Timestamp thTime = new java.sql.Timestamp(LoginController.startDate.getTime());
                                long diff = Math.abs(timestamp.getTime() - thTime.getTime());
                                System.out.println("theorique time " + thTime);
                                System.out.println("creation time " + timestamp);
                                System.out.print("start " + diff + " long,  ");

                                long diffSeconds = diff / 1000;
                                int diffMinutes = (int) (diff / (60 * 1000));
                                long diffHours = diff / (60 * 60 * 1000);
                                System.out.print("start " + diffHours + " hr,  ");

                                System.out.print("start " + diffMinutes + " minutes,  ");
                                System.out.print("start " + diffSeconds + " seconds.");
                                //   System.out.println("retard : ");
                                String updateTableSQL = "UPDATE transport SET DELAY = "+diffMinutes+" AND REALTIMESTART= "+ timestamp+" WHERE TRANSPORT_ID = "+device_id;
 
                              */
                    System.out.println(" update transport delay done");
                  
                            }
                        }
                    }

                    if (nbPosition != 0) {
                    //    System.out.println("current long " + longitude);
                        //   System.out.println("current lat " + latitude);
                        //    System.out.println("last long " + lastlongitude);
                        // System.out.println("last lat " + lastlatitude);

                        //test if out => in false => true 
                        Boolean test = checkPositionInsideCircle(lastlongitude, lastlatitude, LoginController.stationEndLong, LoginController.stationEndLat, radius);
                        System.out.println("Station End test 1 " + test);
                        if (test == false) {
                            Boolean test2 = checkPositionInsideCircle(longitude, latitude, LoginController.stationEndLong, LoginController.stationEndLat, radius);
                            System.out.println("Station End test 2 " + test);

                            if (test2) {
                                System.out.println("Station End test out => in");

                                java.sql.Timestamp thTime = new java.sql.Timestamp(LoginController.startDate.getTime());
                                long diff = timestamp.getTime() - thTime.getTime();

                                long diffSeconds = diff / 1000 % 60;
                                long diffMinutes = diff / (60 * 1000) % 60;

                                // System.out.print(diffMinutes + " minutes, ");
                                //   System.out.print(diffSeconds + " seconds.");
                                //   System.out.println("retard : ");
                            }
                        }
                        // test if in => out
                        if (test) {
                            Boolean test2 = checkPositionInsideCircle(longitude, latitude, LoginController.stationEndLong, LoginController.stationEndLat, radius);
                            System.out.println("Station End test 2 " + test);

                            if (test2 == false) {
                                System.out.println("Station End test in => out");

                                java.sql.Timestamp thTime = new java.sql.Timestamp(LoginController.startDate.getTime());
                                long diff = timestamp.getTime() - thTime.getTime();

                                long diffSeconds = diff / 1000 % 60;
                                long diffMinutes = diff / (60 * 1000) % 60;

//                                System.out.print(diffMinutes + " minutes, ");
//                                System.out.print(diffSeconds + " seconds.");
                                //   System.out.println("retard : ");
                            }
                        }
                    }

                    //   latitude = latitude + 0.001;
                    //   java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
                    // System.out.println("done num ");
                    //
                    lastlatitude = latitude;
                    lastlongitude = longitude;

                    //
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(InsertThread.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(InsertThread.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
//                ResultSet rs = stmt.executeQuery("SELECT * FROM `APPLICATIONSETTING`");
            } catch (SQLException ex) {
                // log an exception. fro example:
                System.out.println(ex);
            }
            System.out.println(getName() + " is running");
        } catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
        }
    }

    public static Boolean checkPositionInsideCircle(double pos_x, double pos_y, double circle_center_x, double circle_center_y, double circle_radius) {
        if (Math.pow((pos_x - circle_center_x), 2) + Math.pow((pos_y - circle_center_y), 2) < Math.pow(circle_radius, 2)) {
            return true;
        } else {
            return false;
        }
    }

    public void testStart() {
        //circle center houwa les coord mta3 station
        for (int i = 0; i < LoginController.listOLists.size(); i++) {
            System.out.println(" checkPositionInsideCircle " + checkPositionInsideCircle(i, i, device_id, device_id, device_id));
        }

        //out => in check False then true 
        //lors de chaque insert tester la position actuelle avec la précedante
    }
     public String sumTime(String myTime, int min) {
        //     String myTime = "14:10";
      //  Format formatter = new SimpleDateFormat("HH:mm");
        //String myTime = formatter.format(time);
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        java.util.Date d;
        try {
            d = df.parse(myTime);
            Calendar cal = Calendar.getInstance();
            cal.setTime(d);
            cal.add(Calendar.MINUTE, min);
            String newTime = df.format(cal.getTime());
            return newTime;

        } catch (ParseException ex) {
            Logger.getLogger(DriverplanningDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";

    }

}

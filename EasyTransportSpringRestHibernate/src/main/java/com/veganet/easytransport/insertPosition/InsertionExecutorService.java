/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.insertPosition;

import com.veganet.easytransport.dao.impl.PositionDao;
import com.veganet.easytransport.entities.Positions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
@Service
public class InsertionExecutorService implements Runnable {

    int nThreads = 10;

    @Autowired
    PositionDao positionDao;

   // static class Work implements Runnable {

        int i;

        public void run() {
            try {
                long sleepTime = 1000L;
                i++;
                System.out.println("i " + i);
                Positions p = null;
         double longitude = 10.613083333333334;
         double latitude = 35.836037777777776;
         p.setLatitude(latitude);
         p.setLongitude(longitude);
          positionDao.add(p);
                Thread.sleep(sleepTime);
                System.out.println("Finished sleeping after  milliseconds." + sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }
        /*  @Override
         public void run() {
         ExecutorService executorService = Executors.newFixedThreadPool(nThreads);

         double longitude = 10.613083333333334;
         double latitude = 35.836037777777776;
         double lastlongitude = longitude;
         double lastlatitude = latitude;
         double course = 250;
         int nbPosition = 0;
         java.util.Date date = new java.util.Date();

         System.out.println(getName() + " is running");
         while (true) {
         executorService.submit(new Runnable() {
         int i=0;

         @Override
         public void run() {
                    
         try {
         Positions p = null;
         double longitude = 10.613083333333334;
         double latitude = 35.836037777777776;
         p.setLatitude(latitude);
         p.setLongitude(longitude);
         // positionDao.add(p);
         i++;
         System.out.println(getName() + " add **"+i);
         try {
         Thread.sleep(500);
         } catch (InterruptedException ex) {
         Logger.getLogger(InsertThread.class.getName()).log(Level.SEVERE, null, ex);
         }
         } finally {
         //releaseCamera(elem);
         }
         }

         });
         executorService.shutdown();
         }

         }*/
    }

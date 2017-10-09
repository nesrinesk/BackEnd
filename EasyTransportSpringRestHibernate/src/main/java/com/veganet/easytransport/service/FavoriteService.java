/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.service;

import com.veganet.easytransport.dao.impl.FavoriteDaoImpl;
import com.veganet.easytransport.entities.Driverplanning;
import com.veganet.easytransport.entities.Favorite;
import com.veganet.easytransport.pushNotification.helpers.AndroidPushNotificationsService;
import com.veganet.easytransport.pushNotification.helpers.FirebaseResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author asus
 */
@Service("favoriteService")

public class FavoriteService {

    private static final org.jboss.logging.Logger logger = org.jboss.logging.Logger.getLogger(FavoriteService.class);

    @Autowired
    FavoriteDaoImpl favoriteDao;
    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @Transactional
    public ResponseEntity<String> send() {

        JSONObject body = new JSONObject();
        // JsonArray registration_ids = new JsonArray();
        // body.put("registration_ids", registration_ids);
        body.put("to", "d-126NwqQzg:APA91bFRIPdevWD2sYb6QXO9XeCJc2LhtkpwDuZFQH1jY-5xnhnKmGYxaZiKavrKDBEwjMOGdYubAP08hVuwu2KBojUFxogsbAQ4cVKuzY62B0hKElcBKKVX_7fXYQU5HFwG68yxQS5Y");
        body.put("priority", "high");
        // body.put("dry_run", true);

        JSONObject notification = new JSONObject();
        notification.put("body", "body string here");
        notification.put("title", "title string here");
        // notification.put("icon", "myicon");

        JSONObject data = new JSONObject();
        data.put("key1", "value1");
        data.put("key2", "value2");

        body.put("notification", notification);
        body.put("data", data);

        HttpEntity<String> request = new HttpEntity<>(body.toString());

        CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            FirebaseResponse firebaseResponse = pushNotification.get();
            if (firebaseResponse.getSuccess() == 1) {
                logger.info("push notification sent ok!");
            } else {
                logger.error("error sending push notifications: " + firebaseResponse.toString());
            }
            return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
    }

    @Transactional
    public List<Favorite> findAll() {
        return favoriteDao.findAll();
    }

    @Transactional
    public Favorite findOne(int id) {
        return favoriteDao.findOne(id);
    }

    @Transactional
    public void create(Favorite favorite) {
        favoriteDao.create(favorite);
    }

    @Transactional
    public void update(Favorite favorite) {
        favoriteDao.update(favorite);

    }

    @Transactional
    public void deleteById(int id) {
        favoriteDao.deleteById(id);
    }

    @Transactional
    public List<Driverplanning> getAllByUser(int id) {
       return favoriteDao.getAllByUser(id);
    }

}

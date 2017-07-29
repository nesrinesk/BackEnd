/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.config;

import java.util.concurrent.ExecutorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.task.support.ExecutorServiceAdapter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author asus
 */
@Configuration
public class JavaConfigurator {
    protected static int nbThreadPool=5;    
    /***
     * Use l'implementation spring 
       ThreadPoolTaskScheduler de l'interface TaskExecutor
    */
    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor(); 
       tpte.setCorePoolSize(nbThreadPool);
       tpte.setMaxPoolSize(nbThreadPool*2);
       tpte.setQueueCapacity(nbThreadPool*10);
       return tpte;
    }
    /* Use l'adapter de spring ExecutorServiceAdapter pour 
         retourner une instance ExecutorService
         au lieu de Executors.newFixedThreadPool de java
    */
    @Bean
    public ExecutorService executorService() {
      return new ExecutorServiceAdapter(taskExecutor()); 
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.veganet.easytransport.config;

import com.fasterxml.jackson.databind.util.StdConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author asus
 */
public class MyDateConverter extends StdConverter<String, Date> {
    @Override
    public Date convert(final String value) {
        if (value == null || value.equals("NULL")) {
            return null;
        }
        try {
            return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(value);
        } catch (ParseException e) {
            throw new IllegalStateException("Unable to parse date", e);
        }
    }
}

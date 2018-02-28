package com.sauzny.dbutils.entity.randomdata;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.apache.commons.lang3.RandomUtils;

public class RandomDateTimeUtils {

    public static LocalDateTime getTime(){
        
        LocalDateTime start = LocalDateTime.parse("1980-01-01T00:00:00.000");
        LocalDateTime end = LocalDateTime.now();
        
        return getTime(start, end);
    }
    
    public static LocalDateTime getTime(LocalDateTime start, LocalDateTime end){
        
        long startInclusive = start.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long endExclusive = end.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        
        long random = RandomUtils.nextLong(startInclusive, endExclusive);
        
        LocalDateTime triggerTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(random), ZoneId.systemDefault());
        
        return triggerTime;
    }
}

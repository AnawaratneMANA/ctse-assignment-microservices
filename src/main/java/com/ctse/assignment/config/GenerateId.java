package com.ctse.assignment.config;

import java.util.UUID;

public class GenerateId {
    public static String generateId(){
        String uniqueKey = UUID.randomUUID().toString();
        System.out.println (uniqueKey);
        return uniqueKey;
    }
}

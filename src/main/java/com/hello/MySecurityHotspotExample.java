package com.hello;

public class MySecurityHotspotExample {

     public static void main(String args[]){

        System.getenv();  // Sensitive
        System.getenv("myvar");  // Sensitive

        Runtime.getRuntime().exec("ping", new String[]{"env=val"});   // Sensitive

     }

}

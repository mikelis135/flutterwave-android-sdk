/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flutterwave;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Map;
import java.util.logging.Level;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import org.json.JSONObject;

/**
 *
 * @author tofun
 */
public class index {
    private static Object logger;
    index(){
    
    }
    public String initialize(){
        try{
            URL url= new URL("https://flutterwaveapi.herokuapp.com/initialize");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response = reader.readLine();
            JSONObject returnedObject = new JSONObject(response);
            String xTag= returnedObject.getJSONObject("data").getString("x-tag");
            return xTag;
        }
        catch(Exception ex){
            return "Error- could not generate x-tag";
        }
    }
    
    public String decodeXTag(String xTag){
        try{
            byte[] bytes= xTag.getBytes("utf-8");
            byte[] decodedBytes=  Base64.getDecoder().decode(bytes);
            String decodedString= new String(decodedBytes, "utf-8");
            return decodedString;
        }
        catch(Exception ex){
            return "Error- could not decode xTag";
        }
    }
    
    public String encrypt(String text, String decodedString){
        try{
            byte[] data= text.getBytes();
            String[] splitKey = decodedString.split("//////");
            String key = splitKey[0];
            byte[] convertedKey = DatatypeConverter.parseHexBinary(key);
            int number = Integer.parseInt(splitKey[1]);
            int counter = ((number*1)*10)/5;
            Cipher aes= Cipher.getInstance("AES/CTR/NoPadding");
            int secondLast= counter>>8;
            int last= counter%256;
            int[] counterBytesString = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,secondLast, last};
            byte[] counterBytes= new byte[16];
            for(int i=0;i<counterBytesString.length;i++){
                counterBytes[i]= (byte) counterBytesString[i];  
            }
            SecretKeySpec secretKey = new SecretKeySpec(convertedKey, "AES");
            aes.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(counterBytes));
            byte[] encryptedData= aes.doFinal(data);
            String encryptedDataHexString = bytesToHex(encryptedData);
//            System.out.println(encryptedDataHexString);
            return encryptedDataHexString;
        }
        catch(Exception ex){
            return "Error- could not encrypt"+ex;
        }
    }
    
    public String decrypt(String text, String decodedString){
        try{
            byte[] data= DatatypeConverter.parseHexBinary(text);
            String[] splitKey = decodedString.split("//////");
            String key = splitKey[0];
            byte[] convertedKey = DatatypeConverter.parseHexBinary(key);
            int number = Integer.parseInt(splitKey[1]);
            int counter = ((number*1)*10)/5;
            Cipher aes= Cipher.getInstance("AES/CTR/NoPadding");
            int secondLast= counter>>8;
            int last= counter%256;
            int[] counterBytesString = new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,secondLast, last};
            byte[] counterBytes= new byte[16];
            for(int i=0;i<counterBytesString.length;i++){
                counterBytes[i]= (byte) counterBytesString[i];  
            }
            SecretKeySpec secretKey = new SecretKeySpec(convertedKey, "AES");
            aes.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(counterBytes));
            byte[] decryptedData= aes.doFinal(data);
            String decryptedText= new String(decryptedData);
            return decryptedText;
        }
        catch(Exception ex){
            return "Error "+ex;
        }
    
    }
    
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}

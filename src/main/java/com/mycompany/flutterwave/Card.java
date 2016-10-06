/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flutterwave;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;
/**
 *
 * @author tofun
 */
public class Card {
    
    public static String tokenize(String cardNumber, String cvv, String expiryMonth, String expiryYear, String validateOption, String authModel) throws Exception{
        URL url = new URL("http://flutterwaveapi.herokuapp.com/card/tokenize");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try{
            String xTag = new index().initialize();
            String decodedXTag = new index().decodeXTag(xTag);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("x-tag", xTag);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            JSONObject request= new JSONObject();
            JSONObject data = new JSONObject();
            data.put("cardno", cardNumber);
            data.put("cvv", cvv);
            data.put("expirymonth", expiryMonth);
            data.put("expiryyear", expiryYear);
            data.put("validateoption", validateOption);
            data.put("authmodel", authModel);
            request.put("secureData", new index().encrypt(data.toString(), decodedXTag));
            DataOutputStream writer= new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(request.toString());
            writer.flush();
            writer.close();
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response= reader.readLine();
            JSONObject responseObject = new JSONObject(response);
            String encryptedString = responseObject.getString("data");
            String decryptedString= new index().decrypt(encryptedString, decodedXTag);
            return decryptedString;
        }
        catch(Exception ex){
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String response= reader.readLine();
            JSONObject responseObject = new JSONObject(response); 
            return response;
            
        }
        
    
    }
    
    public static String charge(String amount, String cardNumber, String cvv, String expiryMonth, String expiryYear, String currency, String customerId, String authModel, String narration, String country) throws Exception{
        URL url = new URL("http://flutterwaveapi.herokuapp.com/card/charge");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try{
            String xTag = new index().initialize();
            String decodedXTag = new index().decodeXTag(xTag);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("x-tag", xTag);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            JSONObject request= new JSONObject();
            JSONObject data = new JSONObject();
            data.put("cardno", cardNumber);
            data.put("cvv", cvv);
            data.put("expirymonth", expiryMonth);
            data.put("expiryyear", expiryYear);
            data.put("amount", amount);
            data.put("narration", narration);
            data.put("country", country);
            data.put("customerid", customerId);
            data.put("currency", currency);
            data.put("custid", customerId);
            data.put("authmodel", authModel);
            request.put("secureData", new index().encrypt(data.toString(), decodedXTag));
            DataOutputStream writer= new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(request.toString());
            writer.flush();
            writer.close();
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response= reader.readLine();
            JSONObject responseObject = new JSONObject(response);
            String encryptedString = responseObject.getString("data");
            String decryptedString= new index().decrypt(encryptedString, decodedXTag);
            return decryptedString;
        }
        catch(Exception ex){
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String response= reader.readLine();
            JSONObject responseObject = new JSONObject(response); 
            return response;
            
        }
    }
    
    public static String validate(String otpTransactionIdentifier, String otp) throws Exception{
        URL url = new URL("http://flutterwaveapi.herokuapp.com/card/charge");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        try{
            String xTag = new index().initialize();
            String decodedXTag = new index().decodeXTag(xTag);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("x-tag", xTag);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            JSONObject request= new JSONObject();
            JSONObject data = new JSONObject();
            data.put("otptransactionidentifier", otpTransactionIdentifier);
            data.put("otp", otp);
            request.put("secureData", new index().encrypt(data.toString(), decodedXTag));
            DataOutputStream writer= new DataOutputStream(connection.getOutputStream());
            writer.writeBytes(request.toString());
            writer.flush();
            writer.close();
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response= reader.readLine();
            JSONObject responseObject = new JSONObject(response);
            String encryptedString = responseObject.getString("data");
            String decryptedString= new index().decrypt(encryptedString, decodedXTag);
            return decryptedString;
        }
        catch(Exception ex){
            BufferedReader reader= new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            String response= reader.readLine();
            JSONObject responseObject = new JSONObject(response); 
            return response;
            
        }
    }
    
}

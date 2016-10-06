/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.flutterwave;

/**
 *
 * @author tofun
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        try{
            String xTag = new index().initialize();
            String decodedXTag = new index().decodeXTag(xTag);
            System.out.println(Card.tokenize("5377283645077450", "428","08","19","SMS","NOAUTH"));
        }
        catch(Exception ex){
            System.out.println("Error occured: "+ex);
            
        }
         
    }
    
}

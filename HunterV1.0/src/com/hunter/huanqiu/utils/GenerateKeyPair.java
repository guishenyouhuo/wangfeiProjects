package com.hunter.huanqiu.utils;

import java.util.Map;

public class GenerateKeyPair {

   static String publicKey;  
   static String privateKey;  
	  
	public static String[] getKeyPair(){
		try {
			Map<String, Object> keyMap =  RSAUtils.genKeyPair(); 
			publicKey = RSAUtils.getPublicKey(keyMap);  
	        privateKey = RSAUtils.getPrivateKey(keyMap);  
		} catch (Exception e) {
			e.printStackTrace();
		}   
       String[] keys={publicKey,privateKey};
        return keys;
	}
}

package com.hunter.huanqiu.utils;

import it.sauronsoftware.base64.Base64;

public class EncryptAndDecrypt {
	 public static String encrypt(String source,String publicKey) throws Exception{
		 byte[] data=source.getBytes();
		 byte[] encodedData=RSAUtils.encryptByPublicKey(data, publicKey);
		 byte[] base64Data=Base64.encode(encodedData);
		 String result=new String(base64Data);
        return result; 
	}
	 public static String decrypt(String encodedData,String privateKey) throws Exception{
		 byte[] base64Data=encodedData.getBytes();
		 byte[] decodeBase64=Base64.decode(base64Data);
		 byte[] decodedData = RSAUtils.decryptByPrivateKey(decodeBase64, privateKey);    
		 String result=new String(decodedData);
	      return result;  
	 }
}

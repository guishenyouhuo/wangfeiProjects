package com.hunter.huanqiu.utils;
import java.util.*;
public class MyTools {

	public static String getNewFileName(String fileName){
		//获得需要截取的位置
		int beginIndex=fileName.lastIndexOf(".");
		//将随机得到的文件名和截取到得文件名后缀拼接
		String newFileName=UUID.randomUUID().toString()+fileName.substring(beginIndex, fileName.length());
		return newFileName;
		
	}
}

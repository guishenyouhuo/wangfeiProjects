package com.yashun.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.yashun.bean.MessageBean;
import com.yashun.util.ReadExcel;


public class TestMutil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C://Users//Administrator//Desktop//¡Ù—‘+»»œﬂ//hao315//2.xls");
		try {
			InputStream is = new FileInputStream(file);
			List<MessageBean> list = ReadExcel.readXls315(is);
			for(MessageBean km : list)
			{
				System.out.println(km.getKh_name()+"---"+km.getKh_tel()+"---"+km.getKh_address()+"---"+km.getKh_ly());
//				System.out.println(km.getKh_tel());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

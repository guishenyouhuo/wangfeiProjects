package com.yashun.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.yashun.bean.MessageBean;
import com.yashun.util.ReadExcel;

public class TestExcel {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("C://Users//Administrator//Desktop//test.xls");
		try {
			InputStream is = new FileInputStream(file);
			List<MessageBean> list = ReadExcel.readXls23(is);
			for (MessageBean mb : list) {
				System.out.println(mb.getKh_name());
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

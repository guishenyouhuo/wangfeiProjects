package com.yashun.util;

import java.io.File;
import java.util.TimerTask;

import com.yashun.db.SqlHelper;

public class MyTask extends TimerTask {

	@Override
	public void run() {
		 String allPath = "/home/yitong1jyqiptyo1n7gs1/logs/";
	     File dir= new File(allPath);
	     String[] filelist = dir.list(); 
	     for (int i = 0; i < filelist.length; i++) { 
	    	 String delpath = allPath+filelist[i];
	    	 File delfile = new File(delpath); 
	    	 delfile.delete();
	     }
	     
	     //×Ô¶¯Î´»Ø·Ã
	     String sql = "update khmessage set type = 0 where (fp_hf='' || fp_hf is null) and (lasthf='' || lasthf is null)";
	     SqlHelper sqlHelper = new SqlHelper();
	     sqlHelper.executeUpdate(sql, null);
	     
	}

}

package com.yashun.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class TimerManager {
	// 时间间隔
	private static final long PERIOD_DAY =  24*60*60* 1000;

	public TimerManager() {
		Calendar calendar = Calendar.getInstance();
		// 指定执行时间
		calendar.set(Calendar.HOUR_OF_DAY, 1);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();// 第一次执行任务的时间
		//如果第一次执行定时任务的时间 小于 当前的时间 
		//此时要在 第一次执行定时任务的时间 加一天，以便此任务在下个时间点执行。如果不加一天，任务会立即执行。 
		if(date.before(new Date()))
		{
			date = this.addDay(date, 1);
		}
		Timer timer = new Timer();
		MyTask task = new MyTask();
		timer.schedule(task, date,PERIOD_DAY);

	}

	// 增加或减少天数
	public Date addDay(Date date, int num) {
		Calendar startDT = Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}

}

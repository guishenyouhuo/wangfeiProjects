package com.yashun.util;

import java.util.Timer;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyListener implements ServletContextListener {
  
  public void contextInitialized(ServletContextEvent event) {
	  new TimerManager(); 
  }
  public void contextDestroyed(ServletContextEvent event) {
  }
}

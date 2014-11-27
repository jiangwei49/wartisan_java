package com.systemSupport;

import java.io.IOException;
import java.util.Timer;

public class timeControl {
	
	public void main(String[] args) throws Exception{
		Timer timer = new Timer();
		timer.schedule(new caseRun(), 1000, 30000);//在1秒后执行此任务,每次间隔1分钟,如果传递一个Data参数,就可以在某个固定的时间执行这个任务.
		while (true) {// 这个是用来停止此任务的,否则就一直循环执行此任务了
			try {
				int ch = System.in.read();
				if (ch - 'c' == 0) {
					timer.cancel();// 使用这个方法退出任务
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

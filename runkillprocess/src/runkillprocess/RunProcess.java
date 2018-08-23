package runkillprocess;

import constant.CMDConstant;
import constant.SpiderConstant;
import utils.RunUtils;

//使用runtime调用第三方程序
public class RunProcess {
	public static void main(String[] args) {
		// 执行无阻塞调用
		// 子进程调用notepad++,打开一个文件
		long pid = runJavaProcess(1);
		System.out.println(pid);

		// 十秒后杀掉进程
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		stopProcess(pid);

		// 执行阻塞调用

	}

	public static long runJavaProcess(int flag) {
		String cmd = "\"C:\\Program Files (x86)\\Notepad++\\notepad++.exe\" C:\\RICO\\icon2.txt";
		RunUtils.run(cmd, flag);
		// 获取pid
		return (long) RunUtils.messageMap.get("pid");
	}

	public static void stopProcess(long pid) {
		// 拼接命令
		String cmd = "taskkill /PID " + pid + " /F";
		// 运行命令
		String[] returnContent = RunUtils.run2(cmd);
	}
}

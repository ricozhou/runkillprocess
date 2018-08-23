package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import constant.CodingConstant;
import constant.CommonSymbolicConstant;

//另起线程截取程序流
public class ConsoleSimulator implements Runnable {
	public boolean isStop = false;
	public int INFO = 0;
	public int ERROR = 1;
	public InputStream is;
	public int type;
	public StringBuilder returnPrintContent = new StringBuilder();
	public StringBuilder returnErrorContent = new StringBuilder();
	public StringBuilder returnCommonContent = new StringBuilder();

	public ConsoleSimulator(InputStream is, int type) {
		this.is = is;
		this.type = type;
	}

	public void run() {
		InputStreamReader isr = null;
		try {
			isr = new InputStreamReader(is, CodingConstant.CODING_UTF_8);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		BufferedReader reader = new BufferedReader(isr);
		String s;
		try {
			// 此地方执行python的时候阻塞，不知为何线程会停止
			while ((!isStop) && (s = reader.readLine()) != null) {
				if (s.length() != 0) {
					if (type == INFO) {
						System.out.println(s);
						returnPrintContent.append(s + CommonSymbolicConstant.LINEBREAK2);
					} else {
						returnErrorContent.append(s + CommonSymbolicConstant.LINEBREAK2);
					}
				}
			}
			isStop = true;

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				isr.close();
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String getReturnPrintContent() {
		return returnPrintContent.toString();
	}

	public String getReturnErrorContent() {
		return returnErrorContent.toString();
	}

	public String getReturnCommonContent() {
		return returnCommonContent.toString();
	}

	public void stop() {
		isStop = true;
	}

}

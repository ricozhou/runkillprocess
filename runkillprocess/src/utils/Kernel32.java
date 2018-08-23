package utils;

import com.sun.jna.Library;
import com.sun.jna.Native;

import constant.OtherConstant;

public interface Kernel32 extends Library {
	public static Kernel32 INSTANCE = (Kernel32) Native.loadLibrary("kernel32", Kernel32.class);

	public long GetProcessId(Long hProcess);
}

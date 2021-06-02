package testBase;

import java.util.Locale;

public class OperatingSystem {
	private static String OS = System.getProperty("os.name", "unknown").toLowerCase(Locale.ROOT);
			
	public static boolean isWindows() {
		return OS.contains("win");
	}
	
	public static boolean isLinux() {
		return OS.contains("linux");
	}
}

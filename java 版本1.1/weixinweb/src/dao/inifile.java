package dao;
/**
 * 2017-5-25
 *
 * getinifile.java
 *
 * @author GFQH-XL
 *
 */
/**
 * 
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 2017-5-25
 * 
 * getinifile.java
 * 
 * @author GFQH-XL
 * 
 */
public class inifile {

	/**
	 * 打开配置文件 并读取相关信息
	 * 
	 * @param fileName
	 *            打开配置文件的位置
	 */
	public String[] readerfile(String fileName) {
		String infostr[] = new String[6];
		File file = new File(fileName);
		BufferedReader reader = null;

		int line = 0;
		// 一次读入一行，直到读入null为文件结束
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				infostr[line] = disposeStr(tempString);
				// System.out.println("line " + line + ": " + infostr[line]);
				line++;
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}

		}
		return infostr;
	}

	/**
	 * 处理读取配置文件里面的信息 例如： IP=10.38.36.40 -> 10.38.36.40
	 * 
	 * @param str
	 *            从配置文件读取的信息字符串
	 * @return 返回处理后的信息 例如：10.38.36.40
	 */
	public String disposeStr(String str) {
		// str.indexOf("=");
		return str.substring(str.indexOf("=") + 1);
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param logFileAdr
	 *            打开配置文件的位置
	 */
	public boolean cheakfile(String logFileAdr) {
		// 判断log文件夹是否存在
		File folder = new File(logFileAdr);
		if (!folder.exists()) {
			return false;
		}

		// 判断txt文件是否存在
		java.io.File file = new java.io.File(logFileAdr + "\\" + "ini.txt");
		if (file.exists()) {
			// System.out.println(file + " is already exists!");
			return true;
		} else {
			// System.out.println(file + " not found!");
			return false;
		}
	}

	/**
	 * 
	 * @return 获取系统前一日 日期时间
	 */
	public String getSystemDate() {
		Date date = new Date();
		Date yesterdaydate = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		yesterdaydate = calendar.getTime();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		String dateStr = dateFormat.format(yesterdaydate);
		// System.out.println(dateStr);
		return dateStr;
	}

	/**
	 * 打开配置文件 并读取相关信息
	 * 
	 * @param fileName
	 *            打开配置文件的位置
	 */
	public String readertimesetfile(String fileName) {
		String infostr = null;
		File file = new File(fileName);
		BufferedReader reader = null;

		// 一次读入一行，直到读入null为文件结束
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				infostr = disposeStr(tempString);
				// System.out.println("line " + line + ": " + infostr[line]);
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}

		}
		return infostr;

	}
	
	
}

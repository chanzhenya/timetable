package com.app.timetable.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ClassObjectUtils {
	public static int G_U_ID = 10000;
	public static int FLOW_ID = 1000;
	private static Object lock = new Object();

	public static String _toURLDecoder(Object obj) throws Exception {
		return URLDecoder.decode(ClassObjectUtils._objTostr(obj), "UTF-8");
	}

	public static String _objTostr(Object obj) {
		if (obj != null) {
			String _str = obj.toString().trim();
			if ("null".equalsIgnoreCase(_str)) {
				return "";
			}
			return _str;
		}
		return "";
	}

	public static String _strTostr(String str) {
		if (str == null) {
			str = "";
		}
		return str;
	}

	public static int _strToint(String str) {
		int i = 0;
		if (str != null && !str.equals("")) {
			i = Integer.parseInt(str);
		}
		return i;
	}

	public static BigDecimal _strToBigDecimal(String str) {
		BigDecimal b = new BigDecimal(0);
		if (str != null) {
			b = new BigDecimal(str);
		}
		return b;
	}
	//交易UUID
	public static String getTradeUUID() {
		synchronized (lock) {
			char ZI_MU = (char)(Math.random()*26+'A');
			G_U_ID += 1;
			long _NOW = System.currentTimeMillis();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
			String _YEAR = dateFormat.format(_NOW);
			String _INFO = String.valueOf(_NOW);
			if (G_U_ID > 99999) {
				G_U_ID = 10000;
			}
			return _YEAR + _INFO.substring(2, _INFO.length()) + String.valueOf(ZI_MU) + G_U_ID;
		}
	}
	//订单流水号生成
	public static String getTradeFlow(String ORDER_ID) {
		char ZI_MU_1 = (char)(Math.random()*26+'A');
		char ZI_MU_2 = (char)(Math.random()*26+'A');
		FLOW_ID += 1;
		long _NOW = System.currentTimeMillis();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String _YEAR = dateFormat.format(_NOW);
		String _INFO = String.valueOf(_NOW);
		if (FLOW_ID > 9999) {
			FLOW_ID = 1000;
		}
		StringBuffer _buffer = new StringBuffer("");
		_buffer.append(ORDER_ID).append(String.valueOf(ZI_MU_1)).append(_YEAR)
				.append(_INFO.substring(2, _INFO.length())).append(String.valueOf(ZI_MU_2)).append(FLOW_ID);
		return  _buffer.toString();
	}
	
	//退款流水号生成
	public static String getRefundFlow(String ORDER_ID) {
		String tradeFlow=getTradeFlow(ORDER_ID);
		return  tradeFlow+"TK";
	}
	
	// UUID
	public static String getUUID() {
		synchronized (lock) {
			return UUID.randomUUID().toString().replace("-", "");
		}
	}
	//单号生成
	public static String getOrderId() {
		long _NOW = System.currentTimeMillis();
		String ZI_MU=String.format("%04d", Math.random()*9999);
		StringBuffer _buffer=new StringBuffer("");
		_buffer.append('R').append(_NOW).append(ZI_MU);
		return _buffer.toString();
	}

	public static int _objToInt(Object obj) {
		return (obj == null ? null : Integer.parseInt(obj + ""));
	}

	public static Double _objToDouble(Object obj) {
		return (obj == null ? null : Double.parseDouble(obj + ""));
	}

	public static String get_now_time() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(new Date());
	}

	public static String getNowTimeMillisecond() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		return sf.format(new Date());
	}

	public static String get_squence_time() {
		synchronized (lock) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
			return sf.format(new Date());
		}
	}

	/**
	 * MultipartFile转File
	 * @param ins
	 * @param file
	 */
	public static void inputStreamToFile(InputStream ins, File file) {
		try {
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
			os.close();
			ins.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * string转date
	 * @param str
	 * @return
	 */
	public static Date _strTodate(String str) {
		if (str != null && str != "") {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ParsePosition pos = new ParsePosition(0);
			return sdf.parse(str, pos);
		} else {
			return null;
		}
	}
	
	/**
	 * date转string
	 * @param date
	 * @return
	 */
	public static String _dateTostr(Date date) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		} else {
			return null;
		}
	}
	
	public static byte[] _strTobyte(String str) {
		if (str != null && str != "") {
			return str.getBytes();
		} else {
			return null;
		}
	}
}

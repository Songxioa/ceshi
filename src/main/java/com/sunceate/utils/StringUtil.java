package com.sunceate.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Title: StringUtil.java
 * </p>
 * <p>
 * Description字符串处理工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * 
 * @author zhanzhao
 * @date 2015-04-24 14:36:25
 * @version V1.0
 */
public final class StringUtil implements java.io.Serializable {
	private static final long serialVersionUID = -6797551630821181968L;

	public static boolean isNullOrBlank(String value) {
		return value == null || "".equals(value.trim());
	}

	public static boolean isNotEmpty(String value) {
		return !isNullOrBlank(value);
	}

	/**
	 * 将字符串按照指定的间隔符组成list
	 * @param str
	 * @param split
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<String> strToList(String str, String split) {
		String[] strs = {};
		if (str != null && !str.equals("")) {
			strs = str.split(split);
		}
		List tokenList = new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			String temp = strs[i];
			if (temp != null) {
				temp = strs[i].trim();
				if (!temp.equals("")) {
					tokenList.add(temp);
				}
			}
		}
		return tokenList;
	}
	
	/**
	 * 将一个整数拼装成str,不够位数的， 在左边，或者右边补0；
	 * @param totalLength
	 * @param source
	 * @return
	 */
	public static String parseStrLength(int totalLength , int source , String direct){
		StringBuffer resultBuffer = new StringBuffer();
		if (direct.equalsIgnoreCase("left")){// 在左边补0
			for(int i = String.valueOf(source).length(); i < totalLength ; i ++){
				resultBuffer.append("0");
			}
			resultBuffer.append(source);
		}else{// 在右边补0
			resultBuffer.append(source);
			for(int i = String.valueOf(source).length(); i < totalLength ; i ++){
				resultBuffer.append("0");
			}
		}
		
		return resultBuffer.toString();
	}

	/**
	 * 获取Long型主键值
	 * @return
	 */
	public static synchronized long getLongId() {
		return System.nanoTime();
	}
	
	/**
	 * 获取String 型主键值
	 * @return
	 */
	public static String getStringId(){
		return String.valueOf(getLongId());
	}
}
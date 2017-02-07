package com.framework.core.utils;

/**
 * 负责提取汉字的首字母
 * 
 * @author gao
 * 
 */
public final class GB2Alpha {
	private   char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发',
			'噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦', '啪', '期', '然', '撒',
			'塌', '塌', '塌', '挖', '昔', '压', '匝', '座' };
	private   char[] alphatable = { 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	private  int[] table = new int[27];
	// 初始化
	{
		for (int i = 0; i < 27; ++i) {
			table[i] = gbValue(chartable[i]);
		}
	}

	public GB2Alpha() {
	}

	private  char Char2Alpha(char ch) {
		if (ch < 128)
			return ch;
		if (ch >= 'a' && ch <= 'z') {
			return (char) (ch - 'a' + 'A');
		}
		if (ch >= 'A' && ch <= 'Z') {
			return ch;
		}
		int gb = gbValue(ch);
		if (gb < table[0]) {
			return '0';
		}
		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb)) {
				break;
			}
		}
		if (i >= 26) {
			return '0';
		} else {
			return alphatable[i];
		}
	}

	public  String String2Alpha(String SourceStr) {
		String Result = "";
		int StrLength = SourceStr.length();
		int i;
		try {
			for (i = 0; i < StrLength; i++) {
				Result += Char2Alpha(SourceStr.charAt(i));
			}
		} catch (Exception e) {
			Result = "";
		}
		return Result.toUpperCase();
	}

	// 取出汉字的编码
	private  int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2) {
				return 0;
			}
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	private  boolean match(int i, int gb) {
		if (gb < table[i]) {
			return false;
		}
		int j = i + 1;
		// 字母Z使用了两个标签
		while (j < 26 && (table[j] == table[i])) {
			++j;
		}
		if (j == 26) {
			return gb <= table[j];
		} else {
			return gb < table[j];
		}
	}
}

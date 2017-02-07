package com.framework.core.daos.jdbc;

/**
 * 可以返回多重语言的分页形式
 * 
 * @author gaofeng
 * 
 */
public class Dialect {
	/**
	 * 返回Oracle分页形式
	 * 
	 * @param sql
	 * @param hasOffset
	 * @return
	 */
	private static String getOracleString(String sql, int begin, int end) {
		sql = sql.trim();
		StringBuffer pagingSelect = new StringBuffer();
		if (begin > 0) {
			pagingSelect
					.append("select * from ( select row_.*, rownum rownum_ from ( ");
		} else {
			pagingSelect.append("select * from ( ");
		}
		pagingSelect.append(sql);
		if (begin > 0) {
			pagingSelect.append(" ) row_ ) where rownum_ <= "+end+" and rownum_ > "+begin);
		} else {
			pagingSelect.append(" ) where rownum <= "+end);
		}
		return pagingSelect.toString();
	}

	/**
	 * 返回MySql的分页形式
	 * 
	 * @param sql
	 * @param hasOffset
	 * @return
	 */
	private static String getMySqlString(String sql, int begin, int end) {
		return new StringBuffer(sql.length() + 20).append(sql).append(
				begin>0 ? " limit "+begin+", "+end+"" : " limit "+end).toString();
	}

	/**
	 * 根据不同的数据库返回相应的分页形式
	 * 
	 * @param sql
	 * @param hasOffset
	 * @param db
	 * @return
	 */
	public static String getLimitString(String sql, int pageNo, int pageSize,
			String dbName) {
		// 开始记录
		int begin = pageSize * (pageNo - 1);
		// 结束记录
		int end = pageSize * pageNo;

		if (dbName.toUpperCase().contains("ORACLE")) {
			return getOracleString(sql, begin, end);
		} else if (dbName.toUpperCase().contains("MYSQL")) {
			return getMySqlString(sql, begin, end);
		}
		return null;
	}
}

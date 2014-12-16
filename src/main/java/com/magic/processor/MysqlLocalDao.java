package com.magic.processor;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.magic.response.ColumnResponse;

public class MysqlLocalDao {
	public static Connection getLocalConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties pros = loadPros();
			String url = pros.getProperty("jdbc.url");
			String usr = pros.getProperty("jdbc.username");
			String pwd = pros.getProperty("jdbc.password");
			conn = DriverManager.getConnection(url, usr, pwd);
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {

		}
	}

	private static Properties loadPros() throws IOException {
		Properties pros = new Properties();
		pros.load(MysqlLocalDao.class.getClassLoader().getResourceAsStream(
				File.separator + "pros" + File.separator + "db-mysql.properties"));
		return pros;
	}

	final static String INSERT_SQL = "INSERT INTO test.kuai3 (termId,num1,num2,num3,sum,hittime) VALUES(%d,%d,%d,%d,%d,%s)";

	public static void insert163(int[] nums, Date time, int termId) throws SQLException {
		Connection conn = getLocalConnection();
		String sql = String.format(INSERT_SQL, termId, nums[0], nums[1], nums[2], (nums[0]
				+ nums[1] + nums[2]), getDate(time));
		System.out.println(sql);
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.execute();
		conn.close();
	}

	private static String getDate(Date time) {
		return new SimpleDateFormat("yyyyMMdd").format(time);
	}

	final static String GETSUMSBYDATEORDER_SQL = "SELECT * FROM test.kuai3 WHERE hittime='%s' ORDER BY termId,hittime DESC";

	public static List<Integer> getSumsByDateOrder(String date) throws SQLException {
		List<Integer> sums = Lists.newArrayList();
		String sql = String.format(GETSUMSBYDATEORDER_SQL, date);
		// System.out.println(sql);
		Connection conn = getLocalConnection();
		PreparedStatement psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		while (rs.next()) {
			sums.add(rs.getInt("sum"));
		}
		conn.close();
		return sums;
	}

	final static String INSERTKUAI3BACKTREND_SQL = "INSERT INTO test.kuai3backtrend (target, count, up, down, equals, hitDate) VALUES (%d,%d,%d,%d,%d,%s)";

	public static void insertKuai3BackTrend(int target, int count, int up, int down, int equals,
			String date) throws Exception {
		String sql = String.format(INSERTKUAI3BACKTREND_SQL, target, count, up, down, equals, date);
		System.out.println(sql);
		Connection conn = getLocalConnection();
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.execute();
		conn.close();
	}

	final static String INSERTKUAI3BACKNUMPERCENTAGE_ONEDAY_SQL = "INSERT INTO test.kuai3backnumpercentage (target, targetHit, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, num17, num18, historyDate) "
			+ "VALUES (%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,'%s')";

	public static void insertKuai3BehindNumPercentage(int target, int targetHit, int c3, int c4,
			int c5, int c6, int c7, int c8, int c9, int c10, int c11, int c12, int c13, int c14,
			int c15, int c16, int c17, int c18, String date) throws SQLException {
		String sql = "";
		sql = String.format(INSERTKUAI3BACKNUMPERCENTAGE_ONEDAY_SQL, target, targetHit, c3, c4, c5,
				c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, date);
		executeInsert(sql);
	}

	final static String GETPERCENTAGESUMS_SQL = "SELECT sum(num3),sum(num4),sum(num5),sum(num6),sum(num7),sum(num8),sum(num9),sum(num10),"
			+ "sum(num11),sum(num12),sum(num13),sum(num14),sum(num15),sum(num16),sum(num17),sum(num18)"
			+ "FROM test.kuai3backnumpercentage where historyDate between '%s' and '%s' and target=%d";

	public static int[] getPercentageSumsByDateOrder(int target, String startDate, String endDate)
			throws SQLException {
		int[] sums = new int[16];
		String sql = String.format(GETPERCENTAGESUMS_SQL, startDate, endDate, target);
		// System.out.println(sql);
		Connection conn = getLocalConnection();
		PreparedStatement psmt = conn.prepareStatement(sql);
		ResultSet rs = psmt.executeQuery();
		int i = 0;
		while (rs.next()) {
			sums[i++] = rs.getInt("sum");
		}
		conn.close();
		return sums;
	}

	private static int insertCount = 0;

	private static void executeInsert(String sql) throws SQLException {
		if (++insertCount == 1000) {
			insertCount = 0;
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
		}
		System.out.println(sql);
		Connection conn = getLocalConnection();
		PreparedStatement psmt = conn.prepareStatement(sql);
		psmt.execute();
		conn.close();
	}

	final static String INSERTTERMDATA_SQL = "INSERT INTO test.kuai3termdata (historyDate, term1, term2, term3, term4, term5, term6, term7, term8, term9, term10, term11, term12, term13, term14, term15, term16, term17, term18, term19, term20, term21, term22, term23, term24, term25, term26, term27, term28, term29, term30, term31, term32, term33, term34, term35, term36, term37, term38, term39, term40, term41, term42, term43, term44, term45, term46, term47, term48, term49, term50, term51, term52, term53, term54, term55, term56, term57, term58, term59, term60, term61, term62, term63, term64, term65, term66, term67, term68, term69, term70, term71, term72, term73, term74, term75, term76, term77, term78, term79, term80) "
			+ "VALUES (%s,%d,%d,%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d,	%d)";

	static String INSERTTERMDATA_SQL_FRONT = "REPLACE INTO test.kuai3termdata (historyDate,";
	static String INSERTTERMDATA_SQL_VALUE = "VALUES (";
	static String WHERE_SQL = "WHERE historyDate=";

	public static void insertTermData(List<Integer> sums, String date) throws SQLException {
		StringBuilder sb = new StringBuilder();
		StringBuilder values = new StringBuilder();
		for (int i = 0; i < sums.size(); i++) {
			values.append(sums.get(i) + ",");
			sb.append("term" + (i + 1) + ",");
		}
		String sql = INSERTTERMDATA_SQL_FRONT + sb.substring(0, sb.length() - 1) + ") "
				+ INSERTTERMDATA_SQL_VALUE + date + "," + values.substring(0, values.length() - 1)
				+ ")";

		executeInsert(sql);
	}

	public static void insertTermData2(List<Integer> sums, String date) throws SQLException {
		String sql = "";
		switch (sums.size()) {
		case 70:
			sql = String.format(INSERTTERMDATA_SQL, date, sums.get(0), sums.get(1), sums.get(2),
					sums.get(3), sums.get(4), sums.get(5), sums.get(6), sums.get(7), sums.get(8),
					sums.get(9), sums.get(10), sums.get(11), sums.get(12), sums.get(13),
					sums.get(14), sums.get(15), sums.get(16), sums.get(17), sums.get(18),
					sums.get(19), sums.get(20), sums.get(21), sums.get(22), sums.get(23),
					sums.get(24), sums.get(25), sums.get(26), sums.get(27), sums.get(28),
					sums.get(29), sums.get(30), sums.get(31), sums.get(32), sums.get(33),
					sums.get(34), sums.get(35), sums.get(36), sums.get(37), sums.get(38),
					sums.get(39), sums.get(40), sums.get(41), sums.get(42), sums.get(43),
					sums.get(44), sums.get(45), sums.get(46), sums.get(47), sums.get(48),
					sums.get(49), sums.get(50), sums.get(51), sums.get(52), sums.get(53),
					sums.get(54), sums.get(55), sums.get(56), sums.get(57), sums.get(58),
					sums.get(59), sums.get(60), sums.get(61), sums.get(62), sums.get(63),
					sums.get(64), sums.get(65), sums.get(66), sums.get(67), sums.get(68),
					sums.get(69), 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			break;
		case 76:
			sql = String.format(INSERTTERMDATA_SQL, date, sums.get(0), sums.get(1), sums.get(2),
					sums.get(3), sums.get(4), sums.get(5), sums.get(6), sums.get(7), sums.get(8),
					sums.get(9), sums.get(10), sums.get(11), sums.get(12), sums.get(13),
					sums.get(14), sums.get(15), sums.get(16), sums.get(17), sums.get(18),
					sums.get(19), sums.get(20), sums.get(21), sums.get(22), sums.get(23),
					sums.get(24), sums.get(25), sums.get(26), sums.get(27), sums.get(28),
					sums.get(29), sums.get(30), sums.get(31), sums.get(32), sums.get(33),
					sums.get(34), sums.get(35), sums.get(36), sums.get(37), sums.get(38),
					sums.get(39), sums.get(40), sums.get(41), sums.get(42), sums.get(43),
					sums.get(44), sums.get(45), sums.get(46), sums.get(47), sums.get(48),
					sums.get(49), sums.get(50), sums.get(51), sums.get(52), sums.get(53),
					sums.get(54), sums.get(55), sums.get(56), sums.get(57), sums.get(58),
					sums.get(59), sums.get(60), sums.get(61), sums.get(62), sums.get(63),
					sums.get(64), sums.get(65), sums.get(66), sums.get(67), sums.get(68),
					sums.get(69), sums.get(70), sums.get(71), sums.get(72), sums.get(73),
					sums.get(74), sums.get(75), 0, 0, 0, 0);
			break;
		case 77:
			sql = String.format(INSERTTERMDATA_SQL, date, sums.get(0), sums.get(1), sums.get(2),
					sums.get(3), sums.get(4), sums.get(5), sums.get(6), sums.get(7), sums.get(8),
					sums.get(9), sums.get(10), sums.get(11), sums.get(12), sums.get(13),
					sums.get(14), sums.get(15), sums.get(16), sums.get(17), sums.get(18),
					sums.get(19), sums.get(20), sums.get(21), sums.get(22), sums.get(23),
					sums.get(24), sums.get(25), sums.get(26), sums.get(27), sums.get(28),
					sums.get(29), sums.get(30), sums.get(31), sums.get(32), sums.get(33),
					sums.get(34), sums.get(35), sums.get(36), sums.get(37), sums.get(38),
					sums.get(39), sums.get(40), sums.get(41), sums.get(42), sums.get(43),
					sums.get(44), sums.get(45), sums.get(46), sums.get(47), sums.get(48),
					sums.get(49), sums.get(50), sums.get(51), sums.get(52), sums.get(53),
					sums.get(54), sums.get(55), sums.get(56), sums.get(57), sums.get(58),
					sums.get(59), sums.get(60), sums.get(61), sums.get(62), sums.get(63),
					sums.get(64), sums.get(65), sums.get(66), sums.get(67), sums.get(68),
					sums.get(69), sums.get(70), sums.get(71), sums.get(72), sums.get(73),
					sums.get(74), sums.get(75), sums.get(76), 0, 0, 0);
			break;
		case 78:
			sql = String.format(INSERTTERMDATA_SQL, date, sums.get(0), sums.get(1), sums.get(2),
					sums.get(3), sums.get(4), sums.get(5), sums.get(6), sums.get(7), sums.get(8),
					sums.get(9), sums.get(10), sums.get(11), sums.get(12), sums.get(13),
					sums.get(14), sums.get(15), sums.get(16), sums.get(17), sums.get(18),
					sums.get(19), sums.get(20), sums.get(21), sums.get(22), sums.get(23),
					sums.get(24), sums.get(25), sums.get(26), sums.get(27), sums.get(28),
					sums.get(29), sums.get(30), sums.get(31), sums.get(32), sums.get(33),
					sums.get(34), sums.get(35), sums.get(36), sums.get(37), sums.get(38),
					sums.get(39), sums.get(40), sums.get(41), sums.get(42), sums.get(43),
					sums.get(44), sums.get(45), sums.get(46), sums.get(47), sums.get(48),
					sums.get(49), sums.get(50), sums.get(51), sums.get(52), sums.get(53),
					sums.get(54), sums.get(55), sums.get(56), sums.get(57), sums.get(58),
					sums.get(59), sums.get(60), sums.get(61), sums.get(62), sums.get(63),
					sums.get(64), sums.get(65), sums.get(66), sums.get(67), sums.get(68),
					sums.get(69), sums.get(70), sums.get(71), sums.get(72), sums.get(73),
					sums.get(74), sums.get(75), sums.get(76), sums.get(77), 0, 0);
			break;
		case 79:
			sql = String.format(INSERTTERMDATA_SQL, date, sums.get(0), sums.get(1), sums.get(2),
					sums.get(3), sums.get(4), sums.get(5), sums.get(6), sums.get(7), sums.get(8),
					sums.get(9), sums.get(10), sums.get(11), sums.get(12), sums.get(13),
					sums.get(14), sums.get(15), sums.get(16), sums.get(17), sums.get(18),
					sums.get(19), sums.get(20), sums.get(21), sums.get(22), sums.get(23),
					sums.get(24), sums.get(25), sums.get(26), sums.get(27), sums.get(28),
					sums.get(29), sums.get(30), sums.get(31), sums.get(32), sums.get(33),
					sums.get(34), sums.get(35), sums.get(36), sums.get(37), sums.get(38),
					sums.get(39), sums.get(40), sums.get(41), sums.get(42), sums.get(43),
					sums.get(44), sums.get(45), sums.get(46), sums.get(47), sums.get(48),
					sums.get(49), sums.get(50), sums.get(51), sums.get(52), sums.get(53),
					sums.get(54), sums.get(55), sums.get(56), sums.get(57), sums.get(58),
					sums.get(59), sums.get(60), sums.get(61), sums.get(62), sums.get(63),
					sums.get(64), sums.get(65), sums.get(66), sums.get(67), sums.get(68),
					sums.get(69), sums.get(70), sums.get(71), sums.get(72), sums.get(73),
					sums.get(74), sums.get(75), sums.get(76), sums.get(77), sums.get(78), 0);
			break;
		default:
			break;
		}
		if (StringUtils.isNotEmpty(sql)) {

		}
		executeInsert(sql);
	}

	// ---------------------Verify-------------------------------------------------
	static final String VERIFY_CRAWLER_SQL = "SELECT count(id) count,hittime FROM test.kuai3 GROUP BY hittime ORDER BY hittime DESC";
	static final String VERIFY_TREND_SQL = "SELECT count(*) count,hitDate FROM test.kuai3backtrend GROUP BY hitDate ORDER BY hitDate DESC";
	static final String VERIFY_TERM_SQL = "SELECT count(id) count,historyDate FROM test.kuai3termdata GROUP BY historyDate ORDER BY historyDate DESC";
	static final String VERIFY_PERCENTAGE_SQL = "SELECT count(id) count,historyDate FROM test.kuai3backnumpercentage GROUP BY historyDate ORDER BY historyDate DESC";

	public static List<ColumnResponse> verifyCrawler(String type) throws SQLException {
		Connection conn = getLocalConnection();
		String sql = "";
		if (type.equals("crawl")) {
			sql = VERIFY_CRAWLER_SQL;
		} else if (type.equals("trend")) {
			sql = VERIFY_TREND_SQL;
		} else if (type.equals("term")) {
			sql = VERIFY_TERM_SQL;
		} else if (type.equals("percetBtn")) {
			sql = VERIFY_PERCENTAGE_SQL;
		}
		PreparedStatement psmt = conn.prepareStatement(sql);
		System.out.println(sql);
		ResultSet rs = psmt.executeQuery();
		List<ColumnResponse> result = Lists.newArrayList();
		ColumnResponse res = null;
		while (rs.next()) {
			res = new ColumnResponse();
			res.setCount(rs.getInt(1));
			res.setHitTime(rs.getDate(2));
			result.add(res);
		}
		conn.close();
		return result;
	}
}

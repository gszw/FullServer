package cn.com.gszw.impl;

import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;
import com.xiaobo.util.SQLHelper;

import cn.com.gszw.dao.UserDAO;

public class UserDAOImpl implements UserDAO {
	private String sql;

	public String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	// 为安全考虑YzKey和GetKey需要进一步优化
	// 根据人员编码产生Key
	public String GetKey(String s) {
		long i = 0;
		i = Long.parseLong(s);
		i = i + 720806;
		String key = KL(String.valueOf(i));
		// System.out.println("KEY:="+key);

		return key;
	}

	// 根据Key验证是不是本系统用户发来的数据，如果是则返加真，否则返回假
	public boolean YzKey(String s) {
		boolean flag = false;

		if (!s.equals("0")) {
			String swry_dm = JM(s);
			long ss = Long.parseLong(swry_dm);
			ss = ss - 720806;
			swry_dm = String.valueOf(ss);

			// 把税务人员编码解密，再在系统中查是否是该税务人员
			sql = "select * from t_dm_gy_swry ry where  SWRY_DM=? ";
			try {
				ResultSet rs = SQLHelper.getResultSet(sql, swry_dm);
				if (rs.next()) {
					flag = true;
				} else {
					flag = false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			}

		} else {
			flag = false;
		}

		return flag;
	}

	// 可逆的加密算法
	public static String KL(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 'w');
		}
		String s = new String(a);
		return s;
	}

	// 加密后解密
	public static String JM(String inStr) {
		if (inStr == null) {
			return "";
		}
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 'w');
		}
		String k = new String(a);
		return k;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#validateYHYZ(java.lang.Object[])
	 */
	@Override
	public boolean validateYHYZ(String swrydm, String password) {
		String mString = "select 1 from t_dm_gy_swry ry where ry.swry_dm=? and ry.userpassword=?";
		ResultSet mResultSet = SQLHelper
				.getResultSet(mString, swrydm, password);
		try {
			if (mResultSet.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#validateSJYZ(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject validateSJYZ(String swrydm, String imsi, String imei)
			throws JSONException, SQLException {
		String mString = "select 1 from t_mobile_validatesjyz yz where yz.swrydm=? and yz.imsi=? and yz.imei=? and yz.xybj='Y'";
		ResultSet mResultSet = SQLHelper.getResultSet(mString, swrydm, imsi,
				imei);
		if (mResultSet.next()) {
			// 更新用户最近登录时间
			mString = "update t_mobile_validatesjyz yz set yz.lasttime=sysdate where yz.swrydm=? and yz.imsi=? and yz.imei=? and yz.xybj='Y'";
			if (SQLHelper.ExecSql(mString, swrydm, imsi, imei) > -1)
				// 手机验证成功
				return new JSONObject().put("fhjg", "000");
			else {
				return new JSONObject().put("fhjg", "999");
			}
		} else {
			mString = "select 1 from t_mobile_validatesjyz yz where yz.swrydm=? and yz.imsi=? and yz.imei=? and yz.xybj='N'";
			mResultSet = SQLHelper.getResultSet(mString, swrydm, imsi, imei);
			if (mResultSet.next()) {
				// 手机已注册但未通过审核
				return new JSONObject().put("fhjg", "010");
			} else {
				// 手机未注册
				return new JSONObject().put("fhjg", "011");
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#getSwjg(java.lang.String)
	 */
	@Override
	public JSONObject getSwjgSwry(String swrydm) {
		String mString = "select jg.swjg_dm swjgdm,jg.mc swjgmc,ry.mc swrymc,ry.xb from t_dm_gy_swry ry ,t_dm_gy_swjg jg where jg.swjg_dm=ry.swjg_dm and ry.swry_dm=?";
		ResultSet mResultSet = SQLHelper.getResultSet(mString, swrydm);
		try {
			if (mResultSet.next()) {
				JSONObject mJsonObject = new JSONObject();
				mJsonObject.put("swrymc", mResultSet.getString("swrymc"));
				mJsonObject.put("swjgdm", mResultSet.getString("swjgdm"));
				mJsonObject.put("swjgmc", mResultSet.getString("swjgmc"));
				mJsonObject.put("xb", mResultSet.getString("xb"));
				mJsonObject.put("userKey", GetKey(swrydm));
				return mJsonObject;
			} else {
				return null;
			}
		} catch (SQLException e) {
			return null;
		} catch (JSONException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.com.gszw.dao.UserDAO#insertSJYH(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public JSONObject insertSJYH(String swrydm, String imsi, String imei)
			throws SQLException, JSONException {
		String mString = "insert into t_mobile_validatesjyz(swrydm,imsi,imei,registtime,lasttime,xybj)values(?,?,?,sysdate,sysdate,'N')";
		if (SQLHelper.ExecSql(mString, swrydm, imsi, imei) > -1) {
			return new JSONObject().put("fhjg", "0110");
		} else {
			return new JSONObject().put("fhjg", "0111");
		}
	}

}

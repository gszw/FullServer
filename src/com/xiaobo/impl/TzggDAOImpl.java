package com.xiaobo.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.xiaobo.dao.TzggDAO;
import com.xiaobo.dao.TzggDAO;
import com.xiaobo.model.Tzgg;
import com.xiaobo.model.Tzgg;
import com.xiaobo.util.SQLHelper;

public class TzggDAOImpl implements TzggDAO {

	public List<Tzgg> getTzggList() {
		String sql = "select no.xh,no.image,no.title,no.notice,no.create_time,no.area"+
	                 " from t_mobile_notice no order by create_time desc";

		List<Tzgg> list = new ArrayList<Tzgg>();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Tzgg gg = new Tzgg();
					gg.setXh(rs.getString("xh"));
					gg.setImage(rs.getString("image"));
					gg.setTitle(rs.getString("title"));
					gg.setNotice(rs.getString("notice"));
					try {
						gg.setCreate_time(sdf.parse(rs.getString("create_time")));
					} catch (ParseException e) {
						e.printStackTrace();
					};
					gg.setArea(rs.getString("area"));
					list.add(gg);
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return null;

	}
	
	public List<Tzgg> getTzggmx(String xh) {
		String sql = "select no.xh,no.image,no.title,no.notice,no.create_time,no.contxt"+
	                 " from t_mobile_notice no where xh = ? ";

		List<Tzgg> list = new ArrayList<Tzgg>();
		try {
			ResultSet rs = SQLHelper.getResultSet(sql);
			if (rs == null) {
				return null;
			} else {
				while (rs.next()) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Tzgg gg = new Tzgg();
					gg.setXh(rs.getString("xh"));
					gg.setImage(rs.getString("image"));
					gg.setTitle(rs.getString("title"));
					gg.setNotice(rs.getString("notice"));
					try {
						gg.setCreate_time(sdf.parse(rs.getString("create_time")));
					} catch (ParseException e) {
						e.printStackTrace();
					};
					gg.setContxt(rs.getString("context"));
					list.add(gg);
				}
				return list;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		}
		return null;

	}
}

package com.xiaobo.dao;

import java.util.List;

import com.xiaobo.model.PzCx;
import com.xiaobo.model.Tzgg;

public interface TzggDAO {
	public List<Tzgg> getTzggList() ;
	public List<Tzgg> getTzggmx(String xh);
}

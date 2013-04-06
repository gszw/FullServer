package com.xiaobo.dao;

import com.xiaobo.model.Nsrxx;

public interface PubDAO {
	// 查询纳税人基本信息
	public Nsrxx getNsrByBm(String nsrbm);
}

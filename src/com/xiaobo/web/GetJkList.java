package com.xiaobo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.url.ajax.json.JSONArray;
import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;
import com.xiaobo.dao.NsrxxDAO;
import com.xiaobo.impl.NsrxxDAOImpl;
import com.xiaobo.impl.UserDAOImpl;
import com.xiaobo.model.NsrJkxx;
import com.xiaobo.model.NsrSb;

public class GetJkList extends HttpServlet {
	// TODO Auto-generated method stub
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub	
	req.setCharacterEncoding("utf-8");
	resp.setContentType("text/html;charset=utf-8");
	System.out.println("Begin GetJkList!!!");
	String nsrnbm = req.getParameter("nsrnbm");

	PrintWriter out = resp.getWriter();
	// 以下代码是后台验证Key信息，如果验证不通过，则直接返回null;
	// 所有的Servlet都要调用这段代码，主要是为了数据安全考虑
	UserDAOImpl ud = new UserDAOImpl();
	if (!ud.YzKey(req.getParameter("userKey"))) {
		out.write("");
		out.flush();
		out.close();
	} else {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		// 返回nsrxx列表
		System.out.println("tmd");
		//获得缴款记录
		List<NsrJkxx> nsrxxList = nsrxxDao.getJkxxList(nsrnbm);
		if (nsrxxList.size() == 0) {
			out.write("-1");
		} else {
			JSONArray array = new JSONArray();
			for (NsrJkxx nsr : nsrxxList) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("sbrq", nsr.getSbrq());
					obj.put("sbpzs", nsr.getSbpzs());
					obj.put("sbsz", nsr.getSbsz());
					obj.put("sbje", nsr.getSbje());
					obj.put("wkpje", nsr.getWkpje());

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				array.put(obj);
			}
			out.write(array.toString());
		}

		out.flush();
		out.close();
	}
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, resp);
}
}

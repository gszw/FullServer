package com.xiaobo.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;
import com.xiaobo.dao.NsrxxDAO;
import com.xiaobo.impl.NsrxxDAOImpl;


public class GetIfZgy extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		String sqlstr = req.getParameter("zgy_dm");
		
	
		PrintWriter out = resp.getWriter();
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		String number = nsrxxDao.getIfZgy(sqlstr);

		JSONObject obj = new JSONObject();
		try {
			obj.put("Zgy_dm", number);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(obj.toString());
		out.flush();
		out.close();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}
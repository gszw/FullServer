package com.xiaobo.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.url.ajax.json.JSONException;
import com.url.ajax.json.JSONObject;
import com.xiaobo.dao.UserDAO;
import com.xiaobo.impl.UserDAOImpl;


public class LoginZc extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		
		String account = req.getParameter("account");
		String jgjc = req.getParameter("jgjc");
		String telId = req.getParameter("telId");
		UserDAO dao = new UserDAOImpl();
		String flag = dao.login_zc(account, telId,jgjc);


		out.write(flag);
		out.flush();
		out.close();

		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		this.doGet(req, resp);
	}
	
}

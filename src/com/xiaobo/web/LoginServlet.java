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
import com.xiaobo.model.User;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();

		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String telId = req.getParameter("telId");
		UserDAO dao = new UserDAOImpl();
		User user = dao.login(account, password, telId);
		System.out.println(user.getUserKey());
		if (user != null) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("account", user.getAccount());
				obj.put("username", user.getUsername());
				obj.put("password", user.getPassword());
				obj.put("swjgdm", user.getSwjgdm());
				obj.put("swjgmc", user.getSwjgmc());
				obj.put("userKey", user.getUserKey());
				obj.put("Xb", user.getXb());
				out.write(obj.toString());
				out.flush();
				out.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doGet(req, resp);
	}

}

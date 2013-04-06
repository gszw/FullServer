<%@page import="com.xiaobo.impl.NsrxxDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="com.xiaobo.dao.NsrxxDAO,com.xiaobo.impl.NsrxxDAOImpl,com.xiaobo.model.Nsrxx,com.xiaobo.impl.UserDAOImpl"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String nsrnbm = request.getParameter("nsrnbm");
	String userKey = request.getParameter("userKey");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		Nsrxx nsrxx = nsrxxDao.getNsrBasicInfo(nsrnbm);
		if (nsrxx != null) {
			session = request.getSession();
			session.setAttribute("nsrxx", nsrxx);
			%>
			<label>纳税人识别码:</label>${nsrxx.nsrsbm}</br>
			<label>纳税人名称:</label>${nsrxx.nsrmc}</br>
			<label>登记类型:</label>${nsrxx.djlx}</br>
			<label>登记状态:</label>${nsrxx.djzt}</br>
			<label>管理机关:</label>${nsrxx.gljg}</br>
			<label>专管员:</label>${nsrxx.zgy}</br>	
			<p>----请点击左上角图标查看详细！-----</p>		
			<%
		} else {
			out.println("没有信息！");
		}
	} else {
		out.println("用户校验错误！");
	}
%>
<html>
<head>
</head>
<body>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="com.xiaobo.dao.NsrxxDAO,com.xiaobo.impl.NsrxxDAOImpl,java.util.List,com.xiaobo.model.Rkfx_jc,com.xiaobo.impl.UserDAOImpl,com.xiaobo.dao.UserDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String swry = request.getParameter("swry");
	String userKey = request.getParameter("userKey");
	UserDAOImpl dao = new UserDAOImpl();
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<Rkfx_jc> jkxxlist = nsrxxDao.getRk_jc(swry);
		if (jkxxlist != null  ) {
			if(jkxxlist.size() != 0){
				session.removeAttribute("jkxxlist");
				session.setAttribute("jkxxlist", jkxxlist);	
				%>

				<c:forEach var="jkxx" items="${jkxxlist}">
					<label>分析单位:</label>${jkxx.dw}</br>
					<label>总计:</label>${jkxx.zje}</br>
					<label>　其中:</label></br>
					<label>　　中央级:</label>${jkxx.zyj}</br>
					<label>　　省     级:</label>${jkxx.ssj}</br>
					<label>　　地市级:</label>${jkxx.dsj}</br>
					<label>　　区县级:</label>${jkxx.qxj}</br>
					<label>　　乡镇级:</label>${jkxx.xzj}</br>
					<p>----------------------------</p>
				</c:forEach>				
				<%
			}else{
				out.println("没有信息！");
			}

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
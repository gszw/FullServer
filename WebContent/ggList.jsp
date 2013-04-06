<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="com.xiaobo.impl.UserDAOImpl,com.xiaobo.impl.TzggDAOImpl,java.util.List,com.xiaobo.model.Tzgg"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	
	//UserDAOImpl dao = new UserDAOImpl();
	//System.out.println(dao.YzKey(userKey));
	//if (dao.YzKey(userKey)) {
	 TzggDAOImpl gg = new TzggDAOImpl();
		List<Tzgg> list = gg.getTzggList(); 
		if (list != null  ) {
			if(list.size() != 0){
				session.removeAttribute("gglist");
				session.setAttribute("gglist", list);
				%>
				<c:forEach var="gg" items="${gglist}">
					<label>序号：</label>${gglist.xh}</br>
					<label>标题</label>${gglist.title}</br>
					<label>图片</label>${gglist.iamge}</br>
					<label>简介</label>${gglist.notice}</br>
					<p>----------------------------</p>
				</c:forEach>		
				<%
			}else{
				out.println("没有信息！");
			}
		} else {
			out.println("没有信息！");
		}	
%>
<html>
<head>
</head>
<body>

</body>
</html>
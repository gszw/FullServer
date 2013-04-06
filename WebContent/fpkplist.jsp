<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="com.xiaobo.dao.NsrxxDAO,com.xiaobo.impl.NsrxxDAOImpl,java.util.List,com.xiaobo.model.Fpfplist,com.xiaobo.impl.UserDAOImpl"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String fpdm = request.getParameter("fpdm");
	String fphm = request.getParameter("fphm");
	String userKey = request.getParameter("userKey");
	System.out.println("userKey=" + userKey);
	UserDAOImpl dao = new UserDAOImpl();
	System.out.println(dao.YzKey(userKey));
	if (dao.YzKey(userKey)) {
		NsrxxDAO nsrxxDao = new NsrxxDAOImpl();
		List<Fpfplist> fpfxlist = nsrxxDao.getFpKpList(fphm,fpdm);
		if (fpfxlist != null  ) {
			if(fpfxlist.size() != 0){
				session.removeAttribute("fpfxlist");
				session.setAttribute("fpfxlist", fpfxlist);
				%>
				<c:forEach var="fpfx" items="${fpfxlist}">
					<label>发票状态:</label>${fpfx.xm}</br>
					<label>付款方名称:</label>${fpfx.fk}</br>
					<label>收款方名称:</label>${fpfx.sk}</br>
					<label>金额:</label>${fpfx.je}</br>
					
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
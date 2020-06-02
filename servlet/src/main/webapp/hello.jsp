<%--
  Created by IntelliJ IDEA.
  User: jiaxinli
  Date: 2020/6/2
  Time: 7:20 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="header.jsp" %>
<div>hello jsp</div>
<div>
    <%= request.getRemoteAddr() %>
</div>
<%@ include file="footer.jsp" %>

</body>
</html>

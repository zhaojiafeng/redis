<%--
  Created by IntelliJ IDEA.
  User: dllo
  Date: 17/12/28
  Time: 下午1:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<body>

<h1>student</h1>

<form action="/findAllStudents">
    <s:iterator value="data" var="stu">
        <tr>
            <td align="center">${stu.name}</td>
            <td align="center">${stu.id}</td>
        </tr>
    </s:iterator>
</form>

</body>
</html>

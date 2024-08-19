<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.springservlet.domain.member.Member" %>
<html>
<head>
    <title>save</title>
</head>
<body>
성공
<ul>
    <!--
        <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>
        <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>
        <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>
    -->

    <!-- JSP가 제공하는 EL 문법 -->
    <li>id=${member.id}</li>
    <li>username=${member.username}</li>
    <li>age=${member.age}</li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>

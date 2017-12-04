<%--
  Created by IntelliJ IDEA.
  User: Настя
  Date: 03.12.2017
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="emptyAuthor" value="${errorsView.get('emptyAuthor')}"/>
<c:set var="emptyName" value="${errorsView.get('emptyName')}"/>
<c:set var="wrongDuration" value="${errorsView.get('wrongDuration')}"/>

<c:choose>
    <c:when test="${not empty object}">
        <c:set var="author" value="${object.author}"/>
        <c:set var="name" value="${object.name}"/>

    </c:when>
    <c:otherwise>
        <c:set var="author" value=""/>
        <c:set var="name" value=""/>

    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${not empty object and not wrongDuration} ">
        <c:set var="duration" value="${object.duration}"/>
    </c:when>
    <c:otherwise>
        <c:set var="duration" value=""/>
    </c:otherwise>
</c:choose>

<c:if test="${not empty object and not wrongDuration}">
    <c:set var="duration" value="${object.duration}"/>
</c:if>

<HTML>
<HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <TITLE>Тест JSP</TITLE>
</HEAD>
<BODY>
<FORM action="save.html" method="post">

    <c:if test="${not empty object}">
        <INPUT type="hidden" name="id" value="${object.id}">
    </c:if>

    <P>Автор</P>
    <INPUT type="text" name="author-data" value="${author}">

    <c:if test="${emptyAuthor}" >
        <SPAN style='color:red;'>Введите автора</SPAN><BR>
    </c:if>

    <P>Название</P>
    <INPUT type="text" name="name-data" value="${name}">

    <c:if test="${emptyName}" >
        <SPAN style='color:red;'>Введите название</SPAN><BR>
    </c:if>

    <P>Длительность</P>
    <INPUT type="text" name="duration-data" value="${duration}">

    <c:if test="${wrongDuration}" >
        <SPAN style='color:red;'>Заполните поле правильно</SPAN>
    </c:if>
    <P>


    <BUTTON type="submit">Сохранить</BUTTON>
    <A href="index.html">Назад</A>
    </P>
</FORM>
</BODY>
</HTML>

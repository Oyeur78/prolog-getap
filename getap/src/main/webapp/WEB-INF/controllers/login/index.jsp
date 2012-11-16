<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="logolycee">
	<img src="<c:url value="${logo}"/>" width="80px"
		height="80px" />
</div>
<img id="photo_accueil" src="<c:url value="${img}"/>"/>
<h1 style="text-align:center;">${titre}</h1>
<p id="acceuil_text">${texte}</p>

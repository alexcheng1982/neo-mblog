<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<div id="header" class="navbar">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="#"> Neo-mblog </a>
			<ul class="nav">
				<li id="homeLink"><a href="/user/<c:out value="${currentUser.loginName }" />">Home</a></li>
				<li id="publicLink"><a href="/">Public</a></li>
				<li id="settingsLink"><a href="/settings">Settings</a></li>
			</ul>
		</div>
	</div>
</div>
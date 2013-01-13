<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" /></title>
<link href="/resources/bootstrap/css/bootstrap.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    
<script src="/resources/jquery.js"></script>    
<script src="/resources/jquery.timeago.js"></script>
<script src="/resources/bootstrap/js/bootstrap.js"></script>
<script src="/resources/ejs.js"></script>
<script src="/resources/neomblog.js"></script>
</head>
<body>

	<tiles:insertAttribute name="header" />
	<div class="container">
	<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>
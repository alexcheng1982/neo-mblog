<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="com.chengfu.neomblog.model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<ul id="settingsTab" class="nav nav-pills">
		<li class="active"><a href="#users" data-toggle="pill">Users</a></li>
		<li><a href="#messages" data-toggle="pill">Messages</a></li>
	</ul>
	<div class="tab-content">
		<div class="tab-pane active" id="users">
			<c:forEach items="${users}" var="user">
				<c:if test="${ !(currentUser.loginName == user.loginName) }">
					<div
						style="border-bottom: 1px solid #E8E8E8; padding: 9px 12px 9px;">
						<div>
							<a href="/user/<c:out value="${user.loginName }" />"><strong><c:out
										value="${user.displayName }" /></strong></a>
						</div>
						<c:set var="followed"
							value="${currentUser.followed.contains(user)}" />
						<c:choose>
							<c:when test="${followed}">
							 <div>
                                    <span><a href="#"
                                        onclick="Neomblog.unfollow('<c:out value="${user.loginName }" />')">Unfollow</a></span>
                                </div>
							</c:when>
							<c:otherwise>
								<div>
									<span><a href="#"
										onclick="Neomblog.follow('<c:out value="${user.loginName }" />')">Follow</a></span>
								</div>
							</c:otherwise>
						</c:choose>

					</div>
				</c:if>
			</c:forEach>
		</div>
		<div class="tab-pane active" id="messages"></div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		Neomblog.activeLink("publicLink");
	});
</script>
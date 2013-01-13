<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="name" value="${currentUser.displayName}" />

<div class="container">
	<div class="row-fluid">
		<div class="span12">
			<ul id="settingsTab" class="nav nav-pills">
				<li class="active"><a href="#following" data-toggle="pill">Following</a></li>
				<li><a href="#follower" data-toggle="pill">Follower</a></li>
				<li><a href="#profileImage" data-toggle="pill">Profile Image</a></li>
			</ul>
			<div class="tab-content">
				<div class="tab-pane active" id="following">
					<c:set var="followings" value="${currentUser.followed}" />
					<c:forEach items="${followings}" var="following">
						<div
							style="border-bottom: 1px solid #E8E8E8; padding: 9px 12px 9px;">
							<div>
								<a href="/user/<c:out value="${following.loginName }" />"><strong><c:out
											value="${following.displayName }" /></strong></a>
							</div>
							<div>
								<span><a href="#"
									onclick="settings.unfollow('<c:out value="${following.loginName }" />')">Unfollow</a></span>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="tab-pane" id="follower">
					<c:set var="followers" value="${currentUser.followers}" />
					<c:forEach items="${followers}" var="follower">
						<div
							style="border-bottom: 1px solid #E8E8E8; padding: 9px 12px 9px;">
							<div>
								<a href="/user/<c:out value="${follower.loginName }" />"><strong><c:out
											value="${follower.displayName }" /></strong></a>
							</div>
							<div>
								<span><a href="#"
									onclick="Neomblog.unfollow('<c:out value="${following.loginName }" />')">Unfollow</a></span>
							</div>
						</div>
					</c:forEach>
				</div>
				<div class="tab-pane" id="profileImage">
				    <label>Input your email address</label>
				    <input id="email">
				    <button id="saveImageButton" type="submit" class="btn btn-primary">Save</button>
				</div>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
	$(function() {
		Neomblog.activeLink("settingsLink");
		
		$("#saveImageButton").click(function() {
			var email = $('#email').val();
			$.ajax({
				url : '/user/!email',
				type : 'POST',
				data : {
					email : email
				}
			});
		});
	});
</script>

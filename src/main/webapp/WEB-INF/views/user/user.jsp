<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="name" value="${profiled.displayName}" />

<div class="row">
	<div class="span3">
		<p>${profiled.messageCount} MESSAGES</p>
		<p>${profiled.followedCount} FOLLOWING</p>
		<p>${profiled.followerCount} FOLLOWERS</p>

        <c:if test="${canPostMessage}">
		<div>
			<div id="postMessage" class="alert" style="display: none;">
				<a class="close" data-dismiss="alert">×</a>
				<div></div>
			</div>
			<form class="form-horizontal" action="#" onsubmit="return false;">
				<fieldset>
					<legend>Post your status</legend>
					<div class="control-group">
						<div>
							<textarea class="input-xlarge" id="content" rows="3"
								style="width: 99%;"></textarea>
						</div>
					</div>
					<div class="form-actions">
						<button id="postButton" type="submit" class="btn btn-primary">Post
							it!</button>
					</div>
				</fieldset>
			</form>

		</div>
		</c:if>
	</div>

	<div class="span8">
		<div id="stream"></div>
	</div>

	<script type="text/javascript">
		$(function() {
			
			
			$("#postButton")
					.click(
							function() {
								var content = $.trim($("#content").val());
								if (content) {
									$
											.ajax({
												url : "/user/!message",
												type : "POST",
												data : {
													loginName : "<c:out value="${profiled.loginName}" />",
													content : content
												},
												success : function() {
													$("#postMessage").show()
															.removeClass(
																	"alert-error")
															.addClass(
																	"alert-success")
															.children("div")
															.html(
																	"Your status has been posted successfully!");
													$("#content").val("");
													Neomblog.refreshStream('${profiled.loginName}');
												},
												error : function() {
													$("#postMessage").show()
															.removeClass(
																	"alert-success")
															.addClass(
																	"alert-error")
															.children("div")
															.html(
																	"An error occurred when posting your status. Please try again.");
												}
											});
								}
							});
			Neomblog.refreshStream('${profiled.loginName}');
			Neomblog.activeLink("homeLink");
		});
	</script>
</div>

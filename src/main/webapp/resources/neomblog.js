(function($) {
	window.Neomblog = {
		activeLink : function(id) {
			$("#header ul.nav li.active").removeClass("active");
			$("#" + id).addClass("active");
		},
		follow : function(loginName) {
			$.ajax({
		    	url : "/user/!follow",
		    	type : "POST",
		    	data : {
		    		loginName : loginName
		    	},
		    	success : function() {
		    		window.location.reload();
		    	},
		    	error : function() {
		    		alert("Failed to unfollow this user, please try again.");
		    	}
		    });
		},
		unfollow : function(loginName) {
			$.ajax({
		    	url : "/user/!unfollow",
		    	type : "POST",
		    	data : {
		    		loginName : loginName
		    	},
		    	success : function() {
		    		window.location.reload();
		    	},
		    	error : function() {
		    		alert("Failed to unfollow this user, please try again.");
		    	}
		    });
		},
		refreshStream : function(loginName) {
			$.ajax({
				url : '/stream/' + loginName,
				dataType : 'json',
				success : function(data) {
					$.each(data, function(index, item) {
						item.displayDate = $.timeago(new Date(item.publishedAt));
					});
					var html = new EJS({url: '/resources/templates/stream.ejs'}).render({
						publishes : data
					});
					$('#stream').html(html);
				}
			});
		}
	};
})(jQuery);
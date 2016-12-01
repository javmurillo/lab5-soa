$(document).ready(function() {
	registerSearch();
	registerTweet();
	registerTemplate();
});

function registerSearch() {
	$("#search").submit(function(ev){
		event.preventDefault();
		$.get($(this).attr('action'), {q: $("#q").val()}, function(data) {
			$("#resultsBlock").html(Mustache.render(template, data));
		});	
	});
}

function registerTweet() {
	$("#tweet").submit(function(ev){
		event.preventDefault();
		$.get($(this).attr('action'), {q: $("#q").val()}, function(data) {
			$("#resultsBlock").html(Mustache.render(template, data));
		});
	});
}

function registerTemplate() {
	template = $("#template").html();
	Mustache.parse(template);
}

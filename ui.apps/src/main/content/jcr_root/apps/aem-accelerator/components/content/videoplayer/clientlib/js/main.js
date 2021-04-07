$(document).ready(function(){
	$('iframe').on("load", function() {
	    var cat = $(".ytCategoryGTM").html(),
            enable = $(".ytCategoryGTM").attr("enable");
	    	eventCategory = $(".ytCategoryGTM").attr("eventCategory");
	    	event = $(".ytCategoryGTM").attr("event");
	    	eventAction = $(".ytCategoryGTM").attr("eventAction");
	    	eventLabel = $(".ytCategoryGTM").attr("eventLabel");
	    trackAnalytics(enable, event, eventAction, eventCategory, eventLabel);
    });
});
function category(ytCat, enable){
    if(enable=="A-true"){
    	window.dataLayer = window.dataLayer || [];
    	window.dataLayer.push({
    	  'categoryVariable': ytCat
    	});
    }
}

$(document).ready(function(){
    var cat = $(".carouselanalyticsCategoryGTM").html(),
        enable =$(".carouselanalyticsCategoryGTM").attr("analytics");
    category(cat, enable);
});
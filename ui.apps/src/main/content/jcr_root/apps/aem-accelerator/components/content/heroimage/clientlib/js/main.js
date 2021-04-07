//function category(ytCat, enable){
//    if(enable=="A-true"){
//    	window.dataLayer = window.dataLayer || [];
//    	window.dataLayer.push({
//    	  'categoryVariable': ytCat
//    	});
//    }
//}
//
//$(document).ready(function(){
//    var cat = $(".heroanalyticsCategoryGTM").html(),
//        enable =$(".heroanalyticsCategoryGTM").attr("analytics");
//    category(cat, enable);
//});
function redirect(link){
    window.open(
      link,
      '_blank' // <- This is what makes it open in a new window.
    );
}
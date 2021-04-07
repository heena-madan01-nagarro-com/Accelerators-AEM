console.log("Dialog 2 js");
(function ($, $document) {
    "use strict";
    $document.on("dialog-ready", function() {
    	
        $(document).on("change", ".addButtonClass", function (e) {
        	var name = $(this).attr("name");
            name = name.split("/");
            name.pop();
            name.pop();
            name = name.join("/");
            if($(this).is(':checked')){
                showElements(name);
            }
            else{
                hideElements(name);
            }
        });
        	
        function showElements(ele){

            $("input[name='"+ele+"/./buttontitle']").parent().show();
            $("input[name='"+ele+"/./buttonlink']").parent().parent().parent().show();
        }

        function hideElements(ele){
                console.log(ele);
             $("input[name='"+ele+"/./buttontitle']").parent().hide();
             $("input[name='"+ele+"/./buttonlink']").parent().parent().parent().hide();
        }
        
        
        function showhide(){
        	setTimeout(
                    function()
                    {
                        var ele = $(".addButtonClass").length;
                        console.log(ele);
                        let i=0;
                        $(".addButtonClass").each(function(){
                      	  name = "./carouselComponents/item"+i;
                      	  if($(".addButtonClass:eq("+i+")").is(':checked')){
                      		  console.log("show : ",name);
                      		  showElements(name);
                      	  }
                      	  else{
                      		  console.log("hide : ",name);
                      		  hideElements(name);
                      	  }
                      	  i++;
                        });
                    }, 50);
        }

        $(".CarouselAddMultifield").click(function(){
            showhide();

            setTimeout(function(){
                var i =0;
                            let len = $(".coral-Collapsible").length;
                            $(".coral-Collapsible").each(function(){
                                if(i==len-1){
                                    if(!($(this).is(".opened"))){
                                        $(this).addClass("opened");
                                        $(this).find(".coral-Icon").removeClass("coral-Icon--chevronRight").addClass("coral-Icon--chevronDown");
                                        $(this).find(".coral-Collapsible-content").css({"display":"block"});
                                    }
                                }
                                i++;
                            });
            }, 100);

        });
        showhide();



    });
})($, $(document));

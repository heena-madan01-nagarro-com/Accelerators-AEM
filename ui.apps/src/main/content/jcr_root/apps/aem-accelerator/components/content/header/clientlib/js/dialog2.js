console.log("Dialog 2 js");
(function ($, $document) {
    "use strict";
    $document.on("dialog-ready", function() {

        $(".UtilityAddMultifield").click(function(){
            setTimeout(function(){
                var i =0;
                console.log($(".coral-Collapsible").length);
                let len = $(".coral-Collapsible").length;
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
     });
})($, $(document));

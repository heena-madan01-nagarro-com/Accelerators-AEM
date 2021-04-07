console.log('Client library called');

(function ($, $document) {
    "use strict";
    $document.on("dialog-ready", function() {
        if($("input[name='./addButton']").is(':checked')){
            $(".multifieldbuttons").parent().show();
        }
        else{
            $(".multifieldbuttons").parent().hide();
        }


        if($("input[name='./addSearch']").is(':checked')){
                    $(".customSearch").parent().show();
                }
                else{
                    $(".customSearch").parent().hide();
                }


        $(document).on("change", "input[name='./addButton']", function (e) {
            if($(this).is(':checked')){
               $(".multifieldbuttons").parent().show();
            }
            else{
                $(".multifieldbuttons").parent().hide();
            }
        });

        $(document).on("change", "input[name='./addSearch']", function (e) {
                    if($(this).is(':checked')){
                       $(".customSearch").parent().show();
                    }
                    else{
                        $(".customSearch").parent().hide();
                    }
                });

    });

})($, $(document));

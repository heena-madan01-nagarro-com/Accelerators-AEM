console.log("validing fields");
(function (document, $, ns) {
    "use strict";
   $(document).on("click", ".cq-dialog-submit", function (e) {
        e.stopPropagation();
        e.preventDefault();

        var $form = $(this).closest("form.foundation-form"),
            enable = $form.find("[name='./enable']").val(),
            eventCategory = $form.find("[name='./eventCategory']"),
            eventAction = $form.find("[name='./eventAction']"),
            eventLabel = $form.find("[name='./eventLabel']"),
            event = $form.find("[name='./event']"),
           	message, clazz = "coral-Button ";

        console.log(enable, eventCategory, eventAction, eventLabel, event);

        var arlene2 = new Array(eventCategory,eventAction, eventLabel,event );

  		if($form.find("[name='./enable']").is(':checked')) {
            if(checkValidation(arlene2)){
                $form.submit();
            }
        }else{
                 $form.submit();
        }
    });
})(document, Granite.$, Granite.author);


function checkValidation(inputArray){

for (var i=0; i < inputArray.length; i++) { 
        if (inputArray[i].val().length<1) { 
            inputArray[i].addClass('errorClass');
            inputArray[i].after('<span class="error">This field is required</span>');
            return false; 
        } else {
              $(".error").remove();
				inputArray[i].removeClass('errorClass');
        }
    } 
    return true;
}









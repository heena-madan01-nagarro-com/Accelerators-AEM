$(document).ready(function () {



    $("#btnSitemap").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();
        var path= $("#sitepathContainerId").val();
        var originPath= location.origin;

        window.open(

            originPath + path +'.sitemap.xml',
  			'_blank' // <- This is what makes it open in a new window.
);


  });

});
function keyWordsearch() {
    gapi.client.setApiKey('AIzaSyD7OMVVykh5BDZy7HYuVUtY0mCrO8zcuYU');
    $('#results').empty();
    $('#results').append('<div class="loader"></div>');
    gapi.client.load('youtube', 'v3', function() {
        makeRequest();
    });

}

function makeRequest() {
    var channel = $('#youtubeChannel').find(":selected").val();
    var q = $('#query').val();
    q = q + " " + channel;
    if(q!=""){
        var request = gapi.client.youtube.search.list({
        q: q,
        part: 'snippet',
        type: 'video',
        maxResults: 50
    });
    request.execute(function(response) {
        console.log(response);
        $('#results').empty();
        var srchItems = response.result.items;
        var result = '';
        var nextPageToken = response.result.nextPageToken;
        var prevPageToken = response.result.prevPageToken;
        console.log(nextPageToken + "," + prevPageToken);
        result = getOutput(srchItems);
        $('#results').append(result);
        $(".youtubeVideos").click(function(){
            $("#youtubeid").val($(this).val());
        });
        /*var buttons = getButtons(prevPageToken, nextPageToken, q);
        $('#buttons').append(buttons);*/
    });
    }


}

function getButtons(prevPageToken, nextPageToken, q) {
    if (!prevPageToken) {
        var btnoutput = '<div class="button-container">' +
            '<button id="next-button" class="paging-button" data-token="' + nextPageToken + '" data-query="' + q + '"' +
            'onclick = "nextPage();">Next Page</button>' +
            '</div>';
    } else {
        var btnoutput = '<div class="button-container">' +
            '<button id="prev-button" class="paging-button" data-token="' + prevPageToken + '" data-query="' + q + '"' +
            'onclick = "prevPage();">Prev Page</button>' +
            '<button id="next-button" class="paging-button" data-token="' + nextPageToken + '" data-query="' + q + '"' +
            'onclick = "nextPage();">Next Page</button>' +
            '</div>';
    }

    return btnoutput;
}

function nextPage() {
    var token = $('#next-button').data('token');
    var q = $('#next-button').data('query');


    // clear
    $('#results').html('');
    $('#buttons').html('');

    // get form input
    q = $('#query').val(); // this probably shouldn't be created as a global

    // run get request on API
    $.get(
        "https://www.googleapis.com/youtube/v3/search", {
            part: 'snippet, id',
            q: q,
            pageToken: token,
            type: 'video',
            key: gapikey
        },
        function(data) {

            var nextPageToken = data.nextPageToken;
            var prevPageToken = data.prevPageToken;

            // Log data
            console.log(data);

            $.each(data.items, function(i, item) {

                // Get Output
                var output = getOutput(item);

                // display results
                $('#results').append(output);
            });

            var buttons = getButtons(prevPageToken, nextPageToken, q);

            // Display buttons
            $('#buttons').append(buttons);
        });
}

function getOutput(srchItems) {
    var result = '';
    $.each(srchItems, function(index, item) {
        vidTitle = '<h4>' + item.snippet.title + '</h4>';
        vidId = item.id.videoId;
        vidDesc = '<p>' + item.snippet.description + '</p>';
        vidChannelTite = '<p> By <span style="color: red;">' + item.snippet.channelTitle + '</span> </p>';
        vidThumburl = item.snippet.thumbnails.default.url;
        vidThumbimg = '<pre><img id="thumb" src="' + vidThumburl + '" alt="No  Image  Available." style="width:204px;height:128px"></pre>';
        result += "<label class='labl'><input type='radio' class='youtubeVideos' name='youtubeVideos' value='"+ vidId +"' checked='checked'/><div  style='padding: 10px;'><li>" + vidTitle + "" + vidThumbimg + "" + vidChannelTite + "" + vidDesc + "</li></div></label><hr>";
    });

    return result;
}
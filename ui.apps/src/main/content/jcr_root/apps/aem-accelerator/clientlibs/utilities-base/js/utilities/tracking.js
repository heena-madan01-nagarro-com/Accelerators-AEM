console.log("utility tracking file");

function trackAnalytics(enable, event, action, category, label){
    if(enable){

    window.dataLayer = window.dataLayer || []; 

window.dataLayer.push({
         'event' : event,
         'eventAction' : action,
  	   	'eventCategory' : category,
		  'eventLabel' : label

         });
  alert('this has pushed to the dataLayer: '+JSON.stringify(dataLayer[dataLayer.length -1]));
    }

}
$( document ).ready(function() {
	$( ".generateToken" ).click(function() {
			var newToken = createToken(5);
		  $( ".token" ).val(newToken);
	});
	
	$(".confirm").confirm({
	    confirmButton: "Ja",
	    cancelButton: "Nee",
	});
	
	$('#Rafeling').change(function() {
		console.log("Changed");
		if (!$(this).is(':checked')) {
			console.log('unchecked');
			$('.eind').hide();
			$('.mrafeling').hide();
		} else {
			console.log('checked');
			$('.eind').show();
			$('.mrafeling').show();
		}
    });
	
	$('#Naden').change(function() {
		console.log("Changed");
		if (!$(this).is(':checked')) {
			console.log('unchecked');
			$('.eind').hide();
			$('.mnaden').hide();
		} else {
			console.log('checked');
			$('.eind').show();
			$('.mnaden').show();
		}
    });
	
	$('#Gaten').change(function() {
		console.log("Changed");
		if (!$(this).is(':checked')) {
			console.log('unchecked');
			$('.gaten').hide();
		} else {
			console.log('checked');
			$('.gaten').show();
		}
    });
	
});

function createToken(l) {
    var token = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < l; i++ )
    	token += possible.charAt(Math.floor(Math.random() * possible.length));

    return token;
}
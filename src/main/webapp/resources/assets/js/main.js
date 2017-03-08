$( document ).ready(function() {
	$( ".generateToken" ).click(function() {
			var newToken = createToken(5);
		  $( ".token" ).val(newToken);
	});
	
	$(".confirm").confirm({
	    confirmButton: "Ja",
	    cancelButton: "Nee",
	});
	
});

function createToken(l) {
    var token = "";
    var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    for( var i=0; i < l; i++ )
    	token += possible.charAt(Math.floor(Math.random() * possible.length));

    return token;
}
$(document).ready(function() {
	$("div.quem input[type='checkbox']").click(function() {
	if($("#paraQuem1").is(':checked') && $("#paraQuem2").is(':checked')){
		$("#saida").val('Para Ambos');
	}
	else if ($("#paraQuem1").is(':checked')){
		$("#saida").val('Para Médico');
	}
	else if ($("#paraQuem2").is(':checked')){
		$("#saida").val('Para Clínica');
	}
	else {
		$("#saida").val('');
	}
	});
});
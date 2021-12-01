$(document).ready(function() {
	var d = $("#datalAtual").val();
	var h = $("#horaAtual").val();
	$("button[name=altera]").click(function() {
            $("#numConsulta").val($(this).val());
            $("#novaData").val('');
            $("#novaHora").val('');
	});
});
$(document).ready(function() {
	$("#cidade").change(function() {
		var idcid = $(this).val();
		var s = '<option value=' + "" + '>Selecione a clinica</option>';
		if (idcid > 0) {
			$.ajax({
				url: '/clinicas',
				data: { "idcid": idcid },
				success: function(result) {
					var result = JSON.parse(result);
					for (var i = 0; i < result.length; i++) {
						s += '<option value="' + result[i][0] + '">' + result[i][1] + '</option>';
					}
					$('#clinicas').html(s);			
				}
			});
		}
	});
});
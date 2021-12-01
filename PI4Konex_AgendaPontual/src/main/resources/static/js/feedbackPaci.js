var slider = document.getElementById("nota");
var slider2 = document.getElementById("nota2");
var output = document.getElementById("valNotaGeral");
var output2 = document.getElementById("valNotaPont");

output.innerHTML = slider.value;
output2.innerHTML = slider2.value;

slider.oninput = function() {
    output.innerHTML = slider.value;
}

slider2.oninput = function slide2() {
    output2.innerHTML = slider2.value;
}


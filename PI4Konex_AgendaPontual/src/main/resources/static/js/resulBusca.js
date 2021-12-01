const target = document.querySelectorAll('[data-anima]');
const animationClass = 'animacao';
(function() {

    var parent = document.querySelector("#rangeSlider");
    if(!parent) return;
  
    var
    rangeS = parent.querySelectorAll("input[type=range]"),
      numberS = parent.querySelectorAll("input[type=number]");
  
    rangeS.forEach(function(el) {
      el.oninput = function() {
        var slide1 = parseFloat(rangeS[0].value),
          slide2 = parseFloat(rangeS[1].value);
  
        if (slide1 > slide2) {
          [slide1, slide2] = [slide2, slide1];
          // var tmp = slide2;
          // slide2 = slide1;
          // slide1 = tmp;
        }
  
        numberS[0].value = slide1;
        numberS[1].value = slide2;


        target.forEach(function (elemento) {
        
            if ((rangeS[1].value != 60) && (rangeS[0].value != 60)) {
                elemento.classList.add(animationClass)
            } else {
                elemento.classList.remove(animationClass)
            }
            console.log(elemento.offsetTop);
        })

      }
    });
  
    
  
  })();
  
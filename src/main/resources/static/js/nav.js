const navOculto = document.querySelector('.nav_oculto');
const menuOculto = document.querySelector('.menu');
const iconOculto = document.querySelector('.icon-oculto');
const closeMenu = document.querySelector('.closeMenu');
const openMenu = document.querySelector('.openMenu');

openMenu.addEventListener('click', show);
closeMenu.addEventListener('click', close);

// function disableScroll(){  
//     var x = window.scrollX;
//     var y = window.scrollY;
//     window.onscroll = function(){ window.scrollTo(x, y) };
// }

// function enableScroll(){  
//     window.onscroll = null;
// }

function show(){
    navOculto.classList.toggle("ocultar");
    setTimeout(function(){
        menuOculto.classList.toggle("ocultar_items");
        closeMenu.classList.toggle("ocultar_items");
        iconOculto.classList.toggle("ocultar_items");
    }, 200);
    
}

function close(){
    navOculto.classList.toggle("ocultar");
    setTimeout(function(){
        menuOculto.classList.toggle("ocultar_items");
        closeMenu.classList.toggle("ocultar_items");
        iconOculto.classList.toggle("ocultar_items");
    }, 100);
}
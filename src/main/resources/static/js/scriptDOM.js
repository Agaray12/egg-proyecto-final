function limitador() {
    let limite = 140;
    let textos = document.querySelectorAll(".card-text");
    textos.forEach(element => {
        if(element.textContent.length > limite){
            let aux = element.textContent.substring(0, limite)+ " ...";
            element.textContent = aux;
        }
    })
}

limitador();

function diferencia(aux1, aux2) {
    return (aux1 - aux2);
}

const poemDate = document.querySelectorAll(".fecha");
let fechaActual = new Date(Date.now());
poemDate.forEach(element => {
    let fechaElemento = new Date(element.textContent);
    let diferenciaAnio = diferencia(fechaActual.getFullYear(), fechaElemento.getFullYear());
    let diferenciaDia = diferencia(fechaActual.getDate(), fechaElemento.getDate());
    let diferenciaMes = diferencia(fechaActual.getMonth() + 1, fechaElemento.getMonth() + 1);

    let diferenciaHora = diferencia(fechaActual.getHours(), fechaElemento.getHours());
    let diferenciaMinutos = diferencia(fechaActual.getMinutes(), fechaElemento.getMinutes());
    let diferenciaSegundos = diferencia(fechaActual.getSeconds(), fechaElemento.getSeconds());

    if(diferenciaAnio > 0){
        if(diferenciaAnio > 1){
            element.textContent = `Hace ${diferenciaAnio} años`;
        }
        else{
            element.textContent = `Hace ${diferenciaAnio} año`;
        }
    }else if(diferenciaMes > 0 && diferenciaMes < 12){
        if(diferenciaMes > 1){
            element.textContent = `Hace ${diferenciaMes} meses`;
        }
        else{
            element.textContent = `Hace ${diferenciaMes} mes`;
        }
    }else if(diferenciaDia > 0 && (diferenciaDia < 31 || diferenciaDia < 30)){
        if(diferenciaDia > 1){
            element.textContent = `Hace ${diferenciaDia} días`;
        }
        else{
            element.textContent = `Hace ${diferenciaDia} día`;
        }
    }else if(diferenciaHora > 0 && diferenciaHora < 24){
        if(diferenciaHora > 1){
            element.textContent = `Hace ${diferenciaHora} horas`;
        }
        else{
            element.textContent = `Hace ${diferenciaHora} hora`;
        }
    }else if(diferenciaMinutos > 0 && diferenciaMinutos < 60){
        if(diferenciaMinutos > 1){
            element.textContent = `Hace ${diferenciaMinutos} minutos`;
        }
        else{
            element.textContent = `Hace ${diferenciaMinutos} minuto`;
        }
    }else if(diferenciaSegundos > 0 && diferenciaSegundos < 60){
        if(diferenciaSegundos > 1){
            element.textContent = `Hace ${diferenciaSegundos} segundos`;
        }
        else{
            element.textContent = `Hace ${diferenciaSegundos} segundo`;
        }
    }
})
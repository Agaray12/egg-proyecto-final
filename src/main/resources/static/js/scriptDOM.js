function limitador() {
    let limite = 140;
    let textos = document.querySelectorAll(".card-text");
    textos.forEach(element => {
        if(element.textContent.length > limite){
            console.log("entra aca")
            let aux = element.textContent.substring(0, limite)+ " ...";
            element.textContent = aux;
        }
    })
}

limitador();
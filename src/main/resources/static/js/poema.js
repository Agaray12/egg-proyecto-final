const myTexts = document.querySelectorAll("textarea");
myTexts.forEach(element => {
    element.style.cssText = `height: ${element.scrollHeight}px; overflow-y: hidden`;

    element.addEventListener("input", function () {
        this.style.height = "auto";
        this.style.height = `${this.scrollHeight}px`;
    })
})

//Fuente: https://medium.com/@ishitaa.ashley4/simple-star-rating-css-and-javascript-8c450046ba2b
var list = ["uno", "dos", "tres", "cuatro", "cinco"];
list.forEach(function (element) {
    document.getElementById(element).addEventListener("click", function () {
        var cls = document.getElementById(element).className;
        if (cls.includes("unchecked"))
        {
            document.getElementById(element).classList.remove("unchecked");
            document.getElementById(element).classList.add("checked");
        } else
        {
            document.getElementById(element).classList.remove("checked");
            document.getElementById(element).classList.add("unchecked");
        }
    });
});
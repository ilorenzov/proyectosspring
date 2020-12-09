(function () {
    var retirarDinero = function (montoRetirar) {
        var dineroActual = 1000;
        // @ts-ignore
        return new Promise(function (resolve, reject) {
            if (montoRetirar > dineroActual) {
                reject('No hay suficientes fondos');
            }
            else {
                dineroActual -= montoRetirar;
                resolve(dineroActual);
            }
        });
    };
    retirarDinero(1500)
        .then(function (montoActual) { return console.log("Me queda " + montoActual); })["catch"](console.warn);
})();
//# sourceMappingURL=promesas-201.js.map
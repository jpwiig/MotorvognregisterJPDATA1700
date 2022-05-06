let biler;
let bilene;
let cars;
$(() => {
    $.get("/api/hentBiler", (biler) => {
        printBilmerke(biler);
        printBiltype(biler);
    })
    let nyreg;
    $("#register").click(() => {
        nyreg = {
            skiltNr: $("#skiltnr").val(),
            bilmerke: $("#bilmerke").val(),
            biltype: $("#bilType").val(),
            personnr: $("#personnr").val(),
            navn: $("#navn").val(),
            adresse: $("#adresse").val()
        };
        console.log(nyreg);
        validering(nyreg);
        if (altok === true) {
            $.post("/api/registere", nyreg, () => {
                $.get("/api/show", (cars) => {
                    printCar(cars);
                })
            })
        } else {
            console.log("moscott og dior")
        }

    })

})

function printBilmerke(bilene) {
    let ut;
    for (const bil of bilene) {
        ut += "<option id='" + bil.bilmerke + "'>" + bil.bilmerke + "</option>";
    }
    ut += "</select>"
    $("#bilmerke").append(ut);

}

function printBiltype(bilene) {
    let ut;
    for (const bil of bilene) {
        ut += "<option id='" + bil.biltype + "'>" + bil.biltype + "</option>";
    }
    ut += "</select>"
    $("#bilType").append(ut);

}

function printCar(cars) {
    let ut = "";
    ut += "<table><tr> <th>Personnr</th> <th>navn</th> <th>adresse</th> <th>SkiltNr</th> <th>Merke</th> <th>Type</th><th>slett</th> </tr>"
    for (const newreg of cars) {
        ut += "<tr><td>" + newreg.personnr + "</td>" + "<td>" + newreg.navn + "</td>" + "<td>" + newreg.adresse + "</td>" + "<td>" + newreg.skiltNr + "</td>" + "<td>" + newreg.bilmerke + "</td>" + "<td>" + newreg.biltype + "</td>" + "<td>" + "<button onclick=slett(this.newreg)>Slett denne</button>" + "</td></tr>"
    }
    ut += "</table>"
    $("#Visregister").append(ut);

}

function validering(test) {
    const regexppersonnr = /^[0-9]{11}$/;
    const regexpnavn = /^[a-zæøåA-ZÆØÅ. /-]{0,50}$/;
    const regexpadresse = /^[a-zæøåA-ZÆØÅ, 0-9]{0, 50}$/;

    const personnrok = regexppersonnr.test(test.personnr);
    const navnok = regexpnavn.test(test.navn);
    const adresseok = regexpadresse.test(test.adresse);
    altok = false;
    if (!personnrok) {
        altok = false;
        const personfeil = "skriv inn riktig personnr"
        $("#personnr").append(personfeil);
        return false
    }
    if (!navnok) {
        altok = false;
        return false
    }
    if (!adresseok) {
        altok = false;
        return false
    } else {
        altok = true;
    }

}

function slett(newreg) {
    const sikker = confirm("er du sikker på at du vil slette");
    if (sikker === true) {
        console.log("start sletting!");
        //ajax call
        return true;
    } else {
        return false;
    }

}
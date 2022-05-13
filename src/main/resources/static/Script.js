let biler;
let bilene;
let cars;
$(() => {
    //innlogging
    let credentials = {
        username:$("#brukernavn").val(),
        password: $("#passord").val()
    };


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
        console.log(nyreg.adresse)
        validering(nyreg);
        if (altok) {
            $.post("/api/registere", nyreg, () => {
                console.log(nyreg)
                $.get("/api/show", (cars) => {
                    printCar(cars);
                })
            })
            clearreg();
        } else {
            console.log("fakker ikke med det");
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

//no scuffed
function printCar(cars) {
    let ut = "";
    ut += "<table><tr> <th>Personnr</th> <th>navn</th> <th>adresse</th> <th>SkiltNr</th> <th>Merke</th> <th>Type</th><th>slett</th> </tr>"
    for (const newreg of cars) {
        ut += "<tr><td>" + newreg.personnr + "</td>" + "<td>" + newreg.navn + "</td>" + "<td>" + newreg.adresse + "</td>" + "<td>" + newreg.skiltNr + "</td>" + "<td>" + newreg.bilmerke + "</td>" + "<td>" + newreg.biltype + "</td>" + "<td>" + "<button onclick=slett(this.newreg)>Slett denne</button>" + "</td></tr>";
    }
    ut += "</table>"
    $("#Visregister").append(ut);
}

function validering(test) {
    const regexppersonnr = /^[0-9]{11}$/;
    const regexpnavn = /^[a-zæøåA-ZÆØÅ. /-]{0,50}$/;
    const regexpadresse = /^[a-zæøåA-ZÆØÅ ]+[ 0-9]{0,50}$/;
    const regexpskiltnr = /^[A-Z]{2}[0-9]{6}$/;

    const personnrok = regexppersonnr.test(test.personnr);
    const navnok = regexpnavn.test(test.navn);
    const adresseok = regexpadresse.test(test.adresse);
    const skiltnummerok = regexpskiltnr.test(test.skiltNr);

    if (!personnrok || !navnok || !adresseok || !skiltnummerok){
        if (!personnrok) {
            altok = false;
            console.log("alt er ikke riktig - personnr")
            const personfeil = "skriv inn riktig personnr"
            $("#personnr").append(personfeil);
            return altok;
        }
        if (!navnok) {
            console.log("alt er ikke riktig - navn")
            altok = false;
            return altok;
        }
        if (!adresseok) {
            console.log("alt er ikke riktig - adresse")
            altok = false;
            return altok;
        }
        if (!skiltnummerok) {
            console.log("alt er ikkke riktig - skiltnummer")
            altok = false;
            return altok;
        }
    } else {
        altok = true;
        clearreg();
        console.log("alt er riktig - altok")
        return altok;
    }

}

function slett(newreg) {
    const sikker = confirm("er du sikker på at du vil slette");
    if (sikker === true) {
        console.log("start sletting!");
        $.post("/api/deleteone", newreg, () => {

        })
        return true;
    } else {
        return false;
    }

}

function clearreg() {
    $("#skiltnr").val("");
    $("#personnr").val("");
    $("#navn").val("");
    $("#adresse").val("");
    $("#bilmerke").select(0);
    $("#bilType").select(0);
}
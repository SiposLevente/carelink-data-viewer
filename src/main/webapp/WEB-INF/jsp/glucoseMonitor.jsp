<!DOCTYPE html>
<html lang="en">

<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>${bg} ${unit} (${bg_delta} ${unit})</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>

<body>
    <p>Current glucose: <span id="view">${bg}</span> <span class="unit">${unit}</span></p>
    <p>Offset: <span id="bg_delta">${bg_delta}</span> <span class="unit">${unit}</span></p>
    <p>Insulin units in reservoir: <span id="reservoir_ammount">${reservoir_ammount}</span> U</p>
    <p>IOB: <span id="IOB">${IOB}</span> U</p>

    <script>
        let is_timer_correctly_set = false;
        function updateCounter() {
            $.ajax({
                method: "GET",
                url: "currentData",
            }).done(function (response) {
                if (response['bg'] != document.getElementById("view").innerHTML && response['bg'] != "0") {

                    document.getElementById("view").innerHTML = response['bg'];
                    document.title = response['bg'] + " " + response['unit'] + " (" + response['bg_delta'] + " " + response['unit'] + ")";
                    document.getElementById("bg_delta").innerHTML = response['bg_delta'];
                    document.getElementById("reservoir_ammount").innerHTML = response['reservoir_ammount'];
                    document.getElementById("IOB").innerHTML = response['IOB'];

                    let unit_classes = document.querySelectorAll(".unit");
                    if (unit_classes[0].innerHTML != response['unit']) {
                        document.querySelectorAll(".unit").forEach((value) => { value.innerHTML = response['unit'] });
                    }

                    if (!is_timer_correctly_set) {
                        clearInterval(timer);
                        timer = setInterval(updateCounter, 1000 * 60 * 6);
                        is_timer_correctly_set = true;
                        // console.log("Updated timer!");
                    }
                    // console.log("Got data!");
                } else {
                    // console.log("Tried to update data, but data is the same!");
                }

            });
        }
        timer = setInterval(updateCounter, 1000 * 10);
        updateCounter();
    </script>

</body>

</html>
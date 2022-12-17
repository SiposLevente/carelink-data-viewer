<!DOCTYPE html>
<html lang="en">

<head>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>${bg} mmol/L (${bg_delta} mmol/L)</title>
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
</head>

<body>
    <p>Current glucose: <span id="view">${bg}</span> mmol/L
    </p>

    <p>Offset: <span id="bg_delta">${bg_delta}
        </span>
    </p>

    <script>
        var is_timer_correctly_set = false;
        function updateCounter() {
            $.ajax({
                method: "GET",
                url: "currentData",
            }).done(function (response) {
                if (response['bg'] != document.getElementById("view").innerHTML) {
                    document.getElementById("view").innerHTML = response['bg'];
                    document.title = response['bg'] + " mmol/L (" + response['bg_delta'] + " mmol/L)";
                    document.getElementById("bg_delta").innerHTML = response['bg_delta'];

                    if (!is_timer_correctly_set) {
                        clearInterval(timer);
                        timer = setInterval(updateCounter, 1000 * 5 * 60);
                        is_timer_correctly_set = true;
                        console.log("Updated timer!");
                    }
                    console.log("Got data!");
                } else {
                    console.log("Tried to update data, but data is the same!");
                }

            });
        }
        timer = setInterval(updateCounter, 1000 * 30);
        updateCounter();
    </script>

</body>

</html>
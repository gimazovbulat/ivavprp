<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">

    </script>

    <script>
        function search() {
            let page = 0;
            let size = 10;
            let url = "/students?search=";
            let results = document.getElementById('results')

            let name = $('#name').val();
            console.log(name)
            if (name !== undefined) {
                let nameAr = name.split(" ")
                if (nameAr.length === 2){
                    url += "firstName~" + nameAr[0] + "," + "lastName~" + nameAr[1] + ",";
                } else {
                    url += "firstName~" + nameAr[0];
                }
            }
            let courses = $('#courses')
            let coursesVals = courses.val()
            if (coursesVals !== undefined) {
                let coursesValsJoined = coursesVals.join("'")
                url += "course:" + coursesValsJoined;
            }
            url = url + "&page=" + page;
            url = url + "&size=" + size;
            $.ajax({
                url: url,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success:
                    function (response) {
                        for (let i = 0; i < response.length; i++) {
                            results.innerHTML += "<a href='/students/'" + response[i].id + ">"
                                + response[i].firstName + " " + response[i].lastName + '</a><br>';
                        }
                    }
            })
        }

        function pagesAmount() {
            $.ajax({
                url: "/",
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success:
                    function (response) {
                        for (let i = 0; i < response.length; i++) {
                            $('#pages').append('<option value=' + i + '>' + (i + 1) + '</option>')
                        }
                    }
            })
        }
    </script>
</head>
<body>
<form id="search"></form>
<select id="course" multiple>
    <option value="1">1 course</option>
    <option value="2">2 course</option>
    <option value="3">3 course</option>
    <option value="4">4 course</option>
</select>
<input name="name" form="search" id="name">

<button onclick="search()">Find</button>
<div id="results"></div>
</body>
</html>
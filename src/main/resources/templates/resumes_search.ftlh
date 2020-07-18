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
        function onLoad() {
            getEmplTypes()
            getWorkSchedule()
            getSkills()
        }
    </script>

    <script>
        function getSkills() {
            $.ajax({
                url: "/skills",
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    for (let i = 0; i < response.length; i++) {
                        $('#skills').append('<option value=' + response[i].name + '>' + response[i].nameToShow + '</option>')
                    }
                }
            })
        }
    </script>


    <script>
        function getEmplTypes() {
            $.ajax({
                url: "/employment_type",
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    for (let i = 0; i < response.length; i++) {
                        $('#empl_types').append('<option value=' + response[i].actual + '>' + response[i].show + '</option>')
                    }
                }
            })
        }

        function getWorkSchedule() {
            $.ajax({
                url: "/work_schedule",
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success: function (response) {
                    for (let i = 0; i < response.length; i++) {
                        $('#work_schedule').append('<option value=' + response[i].actual + '>' + response[i].show + '</option>')
                    }
                }
            })
        }</script>

    <script>
        function search() {
            let page = 0;
            let size = 10;
            let workSchedule = $('#work_schedule');
            let emplTypes = $('#empl_types');
            let skills = $('#skills');
            let url = "/resumes?search=";

            let name = $('#name').val()
            console.log(name)
            if (name !== "" && name !== undefined) {
                name = name.split(" ").join("_");
                url = url + "name~" + name + ",";
                console.log(url)
            }

            let skillsVals = skills.val()
            if (skillsVals !== undefined) {
                let skillsValsJoined = skillsVals.join("*")
                url = url + "skills:" + skillsValsJoined + ","
                console.log(url)
            }

            let workScheduleVals = workSchedule.val()
            if (workScheduleVals !== undefined) {
                if (workScheduleVals.length === 0) {
                    url = url + "workSchedule:" + "FULL_DAY" + ","

                } else {
                    let workScheduleValsJoined = workScheduleVals.join("'")
                    url = url + "workSchedule:" + workScheduleValsJoined + ","
                    console.log(url)
                }
            }

            let emplTypeVals = emplTypes.val()
            if (emplTypeVals !== undefined) {
                if (emplTypeVals.length === 0) {
                    url = url + "employmentType:" + "FULL_TIME"
                    console.log(url)
                } else {
                    let emplTypeValsJoined = emplTypeVals.join("'")
                    url = url + "employmentType:" + emplTypeValsJoined
                    console.log(url)
                }
            }
            /*if ($('#pages').val() !== undefined) {
                page = $('#pages').val()
            }*/
            url = url + "&page=" + page;
            url = url + "&size=" + size;
            if (skillsVals !== undefined) {
                url += "&coll=1"
            } else {
                url += "&coll=0"
            }
            console.log(url)

            let results = document.getElementById('results')
            $.ajax({
                url: url,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success:
                    function (response) {
                    $('#results').html("")
                        for (let i = 0; i < response.length; i++) {
                            results.innerHTML += "<a href='/resumes/'" + response[i].id + ">" + response[i].name + '</a><br>';
                            results.innerHTML += response[i].text + '<br>';
                            results.innerHTML += response[i].workScheduleToShow + '<br>';
                            results.innerHTML += response[i].emplTypeToShow + '<br>';
                            results.innerHTML += '<br>';
                        }
                    }
            })
        }
    </script>
</head>
<body onload="onLoad()">
<select id="empl_types" form="search" multiple>
</select>
<select id="work_schedule" form="search" multiple>
</select>
<select id="skills" form="search" multiple>
</select>
<select id="pages" form="search">
</select>
<select id="size" form="size" multiple>
    <option name="size" value="10">10</option>
    <option name="size" value="20">20</option>
    <option name="size" value="30">30</option>
</select>
<input name="name" form="search" id="name">
<button type="submit" onclick="search();" form="search">Find</button>
<div id="results">
</div>
</body>
</html>
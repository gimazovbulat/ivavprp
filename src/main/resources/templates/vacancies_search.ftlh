<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title style="  padding-top: 50px;
    padding-bottom: 70px;
    font-weight: 700;
    padding-left: 30px;">search</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../static/css/search.css">
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>


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
            let url = "/vacancies?search=";

            let name = $('#name').val()
            console.log(name)
            if (name !== "" && name !== undefined) {
                name = name.split(" ").join("_");
                url = url + "name~" + name + ",";
                console.log(url)
            }

            let skillsVals = skills.val()
            if (document.getElementById('skills').selectedIndex !== -1) {
                let skillsValsJoined = skillsVals.join("*")
                url = url + "skills:" + skillsValsJoined + ","
                console.log(url)
            } else {
                let options = document.getElementById('skills').options
                let optionsValues = [];
                for (let i = 0; i < options.length; i++) {
                    optionsValues[i] = options[i].value;
                }
                let skillsValsJoined = optionsValues.join("*")
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
            url = url + "&page=" + page;
            url = url + "&size=" + size;
            if (skillsVals !== undefined) {
                url += "&coll=1"
            } else {
                url += "&coll=0"
            }
            console.log(url)
            let results = $('#results');
            $.ajax({
                url: url,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success:
                    function (response) {
                        results.html("")
                        for (let i = 0; i < response.length; i++) {
                            let salary = "";
                            if (response[i].minSalary !== 0 && response[i].maxSalary !== 0) {
                                salary += response[i].minSalary;
                                salary += "-" + response[i].maxSalary;
                            } else if (response[i].minSalary === 0 && response[i].maxSalary === 0) {

                            } else if (response[i].maxSalary === 0) {
                                salary += "from " + response[i].minSalary;
                            } else {
                                salary += "to " + response[i].maxSalary;
                            }
                            results.append(" <div class=\"list-group col-md-10\">\n" +
                                "        <a href='/vacancies/" + response[i].id + "' class=\"list-group-item list-group-item-action flex-column align-items-start\">" +
                                "            <div class=\"d-flex w-100 justify-content-between\">\n" +
                                "                <h5 class=\"mb-1\" id=\"vacName\">" + response[i].name + "</h5>\n" +
                                "                <small id=\"date\">" + response[i].day + " " +
                                response[i].month.charAt(0) + response[i].month.substring(1).toLowerCase() + "</small>\n" +
                                "            </div>\n" +
                                "            <p class=\"mb-1\" id=\"text\">" + response[i].text + "</p>\n" +
                                "            <small id=\"empl_sch\">" + response[i].workScheduleToShow + " " + response[i].emplTypeToShow + "</small>\n" +
                                "            <small id=\"salary\">" + salary + "</small>\n" +
                                "        </a>\n" +
                                "    </div>")
                        }
                    }
            })
        }
    </script>
</head>
<body onload="onLoad()">
<header style=" background-color: #fff;">
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img style="   width: 216px;
    height: 72px;" src="../img/logo.png" alt="logo"></a>
        <#if isStudent == true>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="/students/${user.id}">Профиль</a>
            </nav>
        </#if>
        <#if isCompany == true>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="/company_page/${user.id}">Профиль</a>
            </nav>
        </#if>
        <#if isTeacher == true>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="/teachers/${user.id}">Профиль</a>
            </nav>
        </#if>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>

    </div>
</header>

<main>
    <div class="container-fluid">
        <div class="row searchblock justify-content-center">
            <input type="text" class="form-control" placeholder="Search" style="margin-top: 2%; width: 75%" name="name"
                   form="search" id="name">
            <button type="submit" class="btn btn-info" onclick="search();" form="search"
                    style="height: 80%; margin-top: 2%; margin-left: 1%">Find
            </button>
            <div class="form-check-inline">
                <select id="empl_types" form="search" multiple>
                </select>
                <select id="work_schedule" form="search" multiple>
                </select>
                <select id="skills" form="search" multiple>
                </select>
                <select id="size" form="size" multiple>
                    <option name="size" value="10">10</option>
                    <option name="size" value="20">20</option>
                    <option name="size" value="30">30</option>
                </select>
            </div>
        </div>
        <div class="row offset-1 mt-5 mb-4">
            <h2>Vacancies </h2>
        </div>
        <div class="row justify-content-center carousel" id='results'></div>
    </div>
</main>
</body>
</html>
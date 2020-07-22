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
        function search() {
            let page = 0;
            let size = 10;
            let url = "/students?search=";
            let name = $('#name').val();
            console.log(name)
            if (name !== undefined) {
                let nameAr = name.split(" ")
                if (nameAr.length === 2) {
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
                            results.append("<div class=\"list-group col-md-10\">\n" +
                                "                <a href='/students/" + response[i].id + "' class=\"list-group-item list-group-item-action flex-column align-items-start\" id=\"resHref\">\n" +
                                "                    <div class=\"d-flex w-100 justify-content-between\">\n" +
                                "                        <h5 class=\"mb-1\">" + response[i].firstName + " " + response[i].lastName + "</h5>" +
                                "                    </div>\n" +
                                "                </a>\n" +
                                "        </div>")
                        }
                    }
            })
        }

    </script>
</head>
<body>
<header style=" background-color: #fff;">
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img style="   width: 216px;
    height: 72px;" src="/img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Profile</a>
            <a class="p-2 text-dark" href="#">Notifications</a>
        </nav>
        <a class="btn btn-outline-info" href="#">Log out</a>
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
                <select id="course" multiple>
                    <option value="1">1 course</option>
                    <option value="2">2 course</option>
                    <option value="3">3 course</option>
                    <option value="4">4 course</option>
                </select>
                <select id="size" multiple >
                    <option name="size" value="10">10</option>
                    <option name="size" value="20">20</option>
                    <option name="size" value="30">30</option>
                </select>
            </div>
        </div>
        <div class="row offset-1 mt-5 mb-4">
            <h2>Students </h2>
        </div>
        <div class="row justify-content-center carousel" id='results'></div>
    </div>
</main>
</body>
</html>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>offer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/offer.css">
</head>
<body>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="../img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Профиль</a>
            <a class="p-2 text-dark" href="#">Уведомления</a>
        </nav>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>
    </div>
</header>
<main>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-sm-10 profile">
                <div class="row justify-content-center">
                    <div class="row text-center justify-content-center">
                        <h1 id="name"></h1>
                    </div>
                    <div class="row offset-1">
                        <p id="text-about"></p>
                    </div>
                    <div class="row">
                        <p class="font-weight-bold">Тип трудоустройства: </p>
                        <p id="employment-type"></p>
                    </div>
                    <div class="row offset-1">
                        <p class="font-weight-bold">Занятость: </p>
                        <p id="work-schedule"></p>
                    </div>
                    <div class="row offset-1">
                        <p class="font-weight-bold">Минимальная зарплата: </p>
                        <p id="min"></p>
                    </div>
                    <div class="row offset-1">
                        <p class="font-weight-bold">Максимальная зарплата: </p>
                        <p id="max"></p>
                    </div>
                    <div class="row offset-1">
                        <p class="font-weight-bold">Опубликовано: </p>
                        <p id="time"></p>
                    </div>
                </div>
                <div class="row justify-content-center">
                    <a id="company-profile"></a>
                </div>
                <div class="row justify-content-center">
                    <h1 class="title">Компетенции</h1>
                    <div id="skills-list">
                    </div>
                </div>
            </div>
        </div>

    </div>

</main>
<footer>
    <div class="container-fluid">
        <div class="row footer justify-content-center">
            <h2>Футер</h2>
        </div>
    </div>
</footer>

<script>
    window.onload = function () {
        let token = localStorage.getItem("token");
        if (token == null) {
            logout()
        }
        let request = new XMLHttpRequest();
        let id = document.location.pathname.slice(11);


        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let vacancy = JSON.parse(this.responseText);
                document.getElementById("employment-type").innerHTML = vacancy.emplTypeToShow;
                document.getElementById("work-schedule").innerHTML = vacancy.workScheduleToShow;
                document.getElementById("text-about").innerHTML = vacancy.text;
                document.getElementById("name").innerHTML = vacancy.name;
                document.getElementById("min").innerHTML = vacancy.minSalary
                document.getElementById("max").innerHTML = vacancy.maxSalary
                document.getElementById("time").innerHTML = vacancy.time
                let stud_url = document.getElementById("company-profile");
                stud_url.href = "/company_page/" + vacancy.companyId;
                stud_url.innerHTML = "Перейти к компании";
                let skills_list = document.getElementById("skills-list");
                for (let i = 0; i < vacancy.skills.length; i++) {
                    let skill = document.createElement("div");
                    skill.innerHTML = vacancy.skills[i].name;
                    skills_list.appendChild(skill)
                }
            }
        };

        request.open("GET", "/restApi/vacancies/" + id);
        request.setRequestHeader('AUTH', localStorage.getItem("token"));
        request.send();
    };
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>

</body>
</html>
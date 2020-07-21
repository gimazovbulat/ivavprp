<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Company</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/profile_company.css">
</head>
<body>
<script type="text/javascript" language="javascript">


    function logout() {
        localStorage.removeItem("token")
        if (localStorage.getItem("token") == null) {
            let request = new XMLHttpRequest();
            request.open("GET", "/signOut");
            request.send();
            location.replace("/login");
        }
    }
</script>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="../img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Профиль</a>
            <a class="p-2 text-dark" href="#">Избранное</a>
            <a class="p-2 text-dark" href="/notif/comp">Уведомления</a>
        </nav>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>
    </div>
</header>
<main>

    <div class="container-fluid">
        <div class="row justify-content-center align-content-center">
            <div class="col-sm-10 profile">

                <div class="row justify-content-center align-content-center">
                    <div class="col-sm-10 profile">
                        <h1>Уведомления</h1>
                    </div>
                </div>


                <div class="row justify-content-center align-content-center">
                    <div class="col-sm-10 profile">
                        <ul class="list-group list" id="info">


                        </ul>
                    </div>
                </div>


            </div>
        </div>


    </div>


</main>


<script>
    window.onload = function () {
        let token = localStorage.getItem("token");
        if (token == null) {
            logout()
        }
        let request = new XMLHttpRequest();
        let id = document.location.pathname.slice(12);


        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var resumeVacancies = JSON.parse(this.responseText);

                resumeVacancies.forEach(resumeVacancy => {
                    document.getElementById("info").innerHTML +=
                        " <li class='list-group-item'>" +
                        "<a href='http://localhost:8080/vacancy/" +
                        resumeVacancy.vacancy["id"] +
                        "'>" + resumeVacancy.vacancy["name"] + "</a>" + "<br>" +
                        "<a href='http://localhost:8080/resume/" +
                        resumeVacancy.resume["id"] +
                        "'>" + resumeVacancy.resume["name"] + "</a>"+ "<br>" +
                        resumeVacancy.status
                        +"</li>";
                });


            }
        };

        request.open("GET", "../../api/notif/stud/" + id);
        request.setRequestHeader('AUTH', localStorage.getItem("token"));
        request.send();
    };
</script>


<script
        src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>

<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
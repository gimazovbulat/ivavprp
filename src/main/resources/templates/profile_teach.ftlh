<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Профиль</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/profile_teach.css">
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
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="/img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/teachers/${user.id}">Профиль</a>
        </nav>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>
    </div>
</header>
<main>

    <div class="container-fluid">
        <div class="row justify-content-center align-content-center">
            <div class="col-sm-10 profile">
                <div class="row">
                    <div class="col-2 col-sm-4 avatar ">
                        <img id="avatar" src="/img/avatar.png" class="img-fluid align-middle" alt="avatar">
                    </div>
                    <div class="col-4 col-sm-6 text-box">
                        <h1 id="full-name"></h1>
                        <#if isTeacher == true>
                            <a href="/teacher/edit" type="submit" class="btn btn-info">Редактировать профиль</a>
                        </#if>
                    </div>
                </div>
            </div>
        </div>


    </div>
    </section>
    </div>
    <div class="row space">

    </div>

</main>
<footer class="fixed-bottom">
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
        let id = document.location.pathname.slice(10);


        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                var teacher = JSON.parse(this.responseText);
                document.getElementById("full-name").innerHTML = teacher.firstName + " " + teacher.lastName;
                document.getElementById("avatar").src = teacher.photo;
            }
        };

        request.open("GET", "/restApi/teachers/" + id);
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
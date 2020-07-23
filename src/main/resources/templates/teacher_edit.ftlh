<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/profile_stud.css">
</head>
<body>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="/img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/teachers/${user.id}">Профиль</a>
        </nav>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>
    </div>
</header>

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

<script type="text/javascript" language="javascript">


    window.onload = function () {
        let token = localStorage.getItem("token");
        if (token == null) {
            logout()
        }
    }


    function sendEditReq() {
        let request = new XMLHttpRequest();

        let name = document.getElementById("inputName").value;
        let surname = document.getElementById("inputSurname").value;
        let photo = null;
        let body = '{"firstName": ' + '"' + name + '"' + ', "lastName": ' + '"' + surname + '" ' + ', "photo": '
            + '"' + photo + '"' + '}';
        request.open("POST", "http://localhost:8080/teachers");
        request.setRequestHeader("AUTH", localStorage.getItem("token"))
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);
    }
</script>
<main>

    <div class="container-fluid">
        <div class="row justify-content-center align-content-center">
            <div class="col-sm-10 profile">
                <div class="row">
                    <div class="col-2 col-sm-4 avatar ">
                        <img src="/img/avatar.png" class="img-fluid align-middle" alt="avatar">
                    </div>
                    <div class="col-4 col-sm-6 text-box">
                        <form>
                            <div class="form-group">
                                <label for="InputName" class="mt-4">Имя</label>
                                <input type="text" class="form-control" id="inputName" placeholder="Введите ваше имя">

                                <label for="InputSurname" class="mt-4">Фамилия</label>
                                <input type="text" class="form-control" id="inputSurname"
                                       placeholder="Введите вашу фамилию">

                            </div>

                            <button type="button" onclick="sendEditReq()" class="btn btn-primary  mt-4">Submit</button>
                        </form>
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

</div>


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
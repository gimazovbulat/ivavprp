<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/profile_stud.css">
    <script type="text/javascript" language="javascript">


        window.onload = function () {
            let token = localStorage.getItem("token");
            if (token == null) {
                logout()
            }
        }

        function update() {
            let request = new XMLHttpRequest();
            request.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 405) {
                    location.replace("/students/" + id);
                }
            };
            let id = document.location.pathname.slice(10,11);
            let name = document.getElementById("name").value;
            let surname = document.getElementById("surname").value;
            let photo = null;
            let body = '{"firstName": ' + '"' + name + '"' + ', "lastName": ' + '"' + surname + '" ' + ', "photo": '
                + '"' + photo + '"' + '}';
            request.open("POST", "/restApi/students/" + id + "/edit");
            request.setRequestHeader('AUTH', localStorage.getItem("token"));
            request.setRequestHeader('Content-Type', 'application/json');
            alert(body)
            request.send(body);
        }
    </script>
</head>
<body>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="/img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Профиль</a>
            <a class="p-2 text-dark" href="#">Избранное</a>
            <a class="p-2 text-dark" href="#">Уведомления</a>
        </nav>
        <a class="btn btn-outline-info" href="#">Выход</a>
    </div>
</header>
<main>

    <div class="container-fluid justify-content-center text-center">
        <form method="post" class="form-group col-4 offset-4 mt-4">
            <label for="photo">Фото: </label>
            <input class="form-control" id="photo">
            <label for="inputName">Имя: </label>
            <input class="form-control" id="name">
            <label for="inputSurname">Фамилия: </label>
            <input class="form-control" id="surname">
            <button class="btn btn-info mt-2 type="button" onclick="update()">Сохранить</button>
        </form>
    </div>
    <div class="row space">
    </div>

</main>


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
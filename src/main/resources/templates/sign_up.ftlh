<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>sign Up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/sign_up.css">

</head>
<body class="text-center">
<script type="text/javascript" language="javascript">


    function send() {
        let request = new XMLHttpRequest();

        var email = document.getElementById("inputEmail").value;
        var password = document.getElementById("inputPassword").value;
        var rad = document.getElementsByName('radioRole');
        let role;

        for (var i = 0; i < rad.length; i++) {
            if (rad[i].checked) {
                role = rad[i].value.toString()
            }
        }
        let body = '{"email": ' + '"' + email + '"' + ', "password": ' + '"' + +password + '"' + ', "role": ' + '"' + role + '"}';
        request.open("POST", "/reg");
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);
    }


</script>
<form class="form-signin">
    <img class="mb-4" src="/img/logo.png" alt="" width="216" height="72">
    <input type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="">
    <label for="inputPassword" class="sr-only">Пароль</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required="">


    <div class="form-check">
        <input class="form-check-input" type="radio" value="STUDENT" name="radioRole" id="radio1" checked>
        <label class="form-check-label" for="radio1">
            Студент
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" value="TEACHER" name="radioRole" id="radio2">
        <label class="form-check-label" for="radio2">
            Преподаватель
        </label>
    </div>
    <div class="form-check">
        <input class="form-check-input" type="radio" value="COMPANY" name="radioRole" id="radio3">
        <label class="form-check-label" for="radio3">
            Компания
        </label>
    </div>
    <button class="btn btn-lg btn-block mt-2" type="button" onclick=javascript:send()>Регистрация</button>
    <p class="text-muted">Уже с нами?</p>
    <a class="btn btn-lg btn-block" href="/login">Вход</a>
    <p class="text-muted">© 2020</p>
</form>


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
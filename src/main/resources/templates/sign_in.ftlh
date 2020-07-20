<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Вход</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/sign_in.css" type="text/css">
    <style>
        html,
        body {
            height: 100%;
        }

        body {
            display: -ms-flexbox;
            display: -webkit-box;
            display: flex;
            -ms-flex-align: center;
            -ms-flex-pack: center;
            -webkit-box-align: center;
            align-items: center;
            -webkit-box-pack: center;
            justify-content: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #d1eced;
        }

        .form-signin {
            background-color: white;
            border-radius: 10px;
            width: 100%;
            max-width: 330px;
            padding: 32px;
            margin: 0 auto;
        }

        .form-signin .checkbox {
            font-weight: 400;
        }

        .form-signin .form-control {
            position: relative;
            box-sizing: border-box;
            height: auto;
            padding: 10px;
            font-size: 16px;
        }

        .form-signin .form-control:focus {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }

        .btn {
            background-color: #02c2cb;
            color: white;
        }

        p.text-muted {
            margin: 16px;
        }
    </style>
</head>

<body class="text-center">
<script type="text/javascript" language="javascript">


    function sendReq() {
        let request = new XMLHttpRequest();

        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let slice = this.responseText.slice(10, 46)
                localStorage.setItem("token", slice)
                location.replace("/main")
            }
        };
        let email = document.getElementById("inputEmail").value;
        let password = document.getElementById("inputPassword").value;
        let body = '{"email": ' + '"' + email + '"' + ', "password": ' + '"' + password + '"}'
        request.open("POST", "/login");
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);


    }


</script>
<form class="form-signin">
    <img class="mb-4" src="../img/logo.png" alt="" width="216" height="72">
    <label for="inputEmail" class="sr-only">Email</label>
    <input type="email" id="inputEmail" class="form-control" placeholder="Email" required="" autofocus="">
    <label for="inputPassword" class="sr-only">Пароль</label>
    <input type="password" id="inputPassword" class="form-control" placeholder="Пароль" required="">
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Запомнить меня
        </label>
    </div>
    <button class="btn btn-lg btn-block" type="button" onclick="javascript: sendReq()">Вход
    </button>

    <button class="btn btn-lg btn-block">Регистрация</button>
    <p class="mt-5 mb-3 text-muted">© 2020</p>
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
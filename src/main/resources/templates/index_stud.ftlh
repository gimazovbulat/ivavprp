<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/index_stud.css">
    <style>
        html, body, main, .container-fluid {
            width: 100%;
            height: 100%;
        }

        html {
            overflow-y: hidden;
        }

        .searchblock {
            height: 100%;
            max-height: 100%:
            max-width: 100%;
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
            background: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(../img/logo_books_2.jpeg);
            background-size: 100%;
            background-repeat: no-repeat;
        }

        .navbar-brand img {
            width: 216px;
            height: 72px;
        }

        .carousel {
            margin-top: 2%;
            margin-bottom: 4%;
        }

        .footer {
            background-color: black;
            height: 10%;
        }

        .form-control {
            border: 0 none;
            border-radius: 15px;
            height: 50px;
            font-size: 20px;
        }

        /*Стили формы входа*/
        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
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
    </style>
</head>
<body>
<script type="text/javascript" language="javascript">


    function logout() {
        localStorage.removeItem("token")
        if (localStorage.getItem("token") == null) {
            let request = new XMLHttpRequest();


            request.open("GET", "http://localhost:8080/signOut");
            request.send();
            location.replace("http://localhost:8080/login");
        }
    }
</script>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="../img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Профиль</a>
            <a class="p-2 text-dark" href="#">Избранное</a>
            <a class="p-2 text-dark" href="#">Уведомления</a>
        </nav>

        <a class="btn btn-outline-info" onclick="logout()">Выход</a>
    </div>
</header>
<main>

    <div class="container-fluid">
        <div class="row searchblock justify-content-center align-content-center">
            <form class="col-md-6 text-center">
                <input type="text" class="form-control" placeholder="Поиск вакансии">
                <!--	<button type="submit" class="btn btn-info mt-2 col-md-2">Найти</button> -->
            </form>
        </div>
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/index_stud.css">
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>index</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="../static/css/admin_page.css">
</head>
<body>
<script type="text/javascript" language="javascript">


    function changeStatus(id, isActive) {
        let request = new XMLHttpRequest();

        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
            }
        };


        let request_body = {
            id: id,
            isActive: isActive,

        }
        let body = JSON.stringify(request_body);
        request.open("POST", "/admin/teachers");
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);

    }

    function addSkill() {
        let request = new XMLHttpRequest();
        let request_body = {
            name: document.getElementById("inputName").value,
            points: document.getElementById("inputRating").value,
        }
        let body = JSON.stringify(request_body);
        request.open("POST", "/admin/skill");
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);
    }


</script>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="../img/logo.png" alt="logo"></a>
        <a class="btn btn-outline-success" href="#">Выход</a>
    </div>
</header>
<main>
    <div class="container-fluid text-center col-md-4">
        <form class="mt-3">
            <div class="form-group">
                <label><h2>Добавить: </h2></label>
                <input type="text" class="form-control" id="inputName" placeholder="Название">
                <input type="text" class="form-control" id="inputRating" placeholder="Рейтинг">
            </div>
            <button type="submit" onclick="addSkill()" class="btn btn-info">Добавить скилл</button>
        </form>
    </div>
    <h2 class="text-center mt-5">Преподаватели: </h2>
    <div class="row justify-content-center mt-3">
        <div class="list-group col-md-10 text-center">
            <a href="#" id="teacher-list" class="list-group-item list-group-item-action flex-column align-items-start">
            </a>
        </div>
</main>
<script>
    window.onload = function () {
        let token = localStorage.getItem("token");
        if (token == null) {
            logout()
        }
        let request = new XMLHttpRequest();
        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let teachers = JSON.parse(this.responseText);
                let teacher_list = document.getElementById("teacher-list")
                for (let i = 0; i < teachers.length; i++) {
                    let teacher = document.createElement("div");
                    teacher.innerHTML = '<a href="#" id="teacher-list" class="list-group-item list-group-item-action flex-column align-items-start">\n' +
                        '                <div class="d-flex w-100 justify-content-center">\n' +
                        '                    <h5 class="mb-1" id="full-name">' + teachers[i].firstName + ' '
                        + teachers[i].lastName +
                        '</h5>\n' +
                        '                </div>\n' +
                        '                <p class="mb-1" id="email">' + teachers[i].email +
                        '</p>\n' +
                        '                <p class="mb-1" id="status">' + teachers[i].isActive +
                        '</p>\n' +
                        '                <button type="button" class="btn btn-info" value="' + teachers[i].id +
                        '" id="form-button" onclick="changeStatus(' + teachers[i].id + ',' + teachers[i].isActive +
                        ')">Изменить статус</button>\n' +
                        '            </a>'
                    teacher_list.appendChild(teacher)
                }
            }
        };

        request.open("GET", "/admin/teachers");
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
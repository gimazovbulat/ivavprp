<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>profile_student</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="/css/profile_stud.css" media="all">
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

    function addSkills() {
        let id = document.location.pathname.slice(10);
        let request = new XMLHttpRequest();

        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                location.replace("/students/" + id);
            }
        };
        let studentId = document.location.pathname.slice(10);
        let skills_list = document.getElementsByClassName("form-check-input");
        let skills_checked = [];
        for (let index = 0; index < skills_list.length; index++) {
            if (skills_list[index].checked) {
                skills_checked.push(skills_list[index].value);
            }
        }

        let request_body = {
            skills: skills_checked,
            studentId: studentId
        }

        let body = JSON.stringify(request_body);
        request.open("POST", "/restApi/students/add");
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);

    }


</script>
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="/img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/profile">Профиль</a>
            <a class="p-2 text-dark" href="/favorites">Избранное</a>
            <a class="p-2 text-dark" href="/notifications">Уведомления</a>
        </nav>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>
    </div>
</header>
<main>

    <div class="container-fluid">
        <div class="row justify-content-center align-content-center ">
            <div class="col-sm-10 profile">
                <div class="row justify-content-center">
                    <h1 class="mt-2" id="full-name"></h1>
                </div>
                <div class="row">
                    <div class="col-2 col-sm-4 avatar mb-4 justify-content-center text-center">
                        <img id="avatar" src="/img/avatar.png" class="img-fluid align-middle" alt="avatar">
                        <span id="redact" class="mb-5 offset-1 mt-5"></span>
                    </div>
                    <div class="col-4 text-box">
                        <h5>Список резюме: </h5>
                        <p id="resume" class="justify-content-center mb-5"></p>
                        <span id="add_skill" class="mt-5"></span>
                    </div>
                    <div class="col-4 text-box justify-content-center text-center">
                        <h5>Список компетенций: </h5>
                        <span id="skills"></span>
                        <h5>Добавить компетенции:</h5>
                        <ul id="skills-list" class="list-group list"></ul>
                        <div class="row justify-content-center">
                            <button type="button" onclick="addSkills()" class="btn btn-info mb-5 mt-2">Добавить</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row space">

    </div>

</main>

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
                let id = document.location.pathname.slice(10);
                var student = JSON.parse(this.responseText);
                var skills = student.skills;
                skills.forEach(skill => {
                    document.getElementById("skills").innerHTML +=
                        "<p>" + skill.name + " " + skill.confirmed + "<a href='/restApi/students/" + id + "/" + skill.id + "'>Удалить</a></p>";
                })

                var resumes = student.resumes;
                resumes.forEach(resume => {
                        document.getElementById("resume").innerHTML +=
                            "<a href = '../resume/" + resume["id"] + "'>" +
                            resume["name"] + "<br>";
                    }
                );
                //document.getElementById("resume").innerHTML = student.resumes[0].name;
                document.getElementById("full-name").innerHTML = student.firstName + " " + student.lastName;
            }
            document.getElementById("redact").innerHTML = "<a href='" + id + "/edit'><button class='btn btn-info mt-4'>Редактировать профиль</button></a>";
        };
        //alert(localStorage.getItem("token"))
        request.open("GET", "/restApi/students/" + id);
        request.setRequestHeader('AUTH', localStorage.getItem("token"));
        request.send();

        let request1 = new XMLHttpRequest();
        request1.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let skills = JSON.parse(this.responseText);
                let skills_list = document.getElementById("skills-list")
                for (let i = 0; i < skills.length; i++) {
                    let skill = document.createElement("div");
                    skill.innerHTML = "<div class='form-check'>\n" +
                        "                            <input class='form-check-input' type='checkbox' value=" +
                        skills[i].id +
                        ">\n" +
                        "                            <label class='form-check-label' for='defaultCheck1'>\n" +
                        skills[i].name +
                        "                            </label>\n" +
                        "                        </div>"
                    skills_list.appendChild(skill)
                }
            }
        };

        request1.open("GET", "/skills");
        request1.setRequestHeader('AUTH', localStorage.getItem("token"));
        request1.send();
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>resume</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/resume.css">
</head>
<body>
<script type="text/javascript" language="javascript">


    function sendResumeInfo() {
        let request = new XMLHttpRequest();

        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                location.replace("/main")
            }
        };
        let name = document.getElementById("name").value;
        let text = document.getElementById("text-about").value;
        let employment_type_index = document.getElementById("employment_type").options.selectedIndex;
        let employment_type = document.getElementById("employment_type").options[employment_type_index].value
        let work_schedule_index = document.getElementById("work-schedule").options.selectedIndex;
        let work_schedule = document.getElementById("work-schedule").options[work_schedule_index].value
        let skills_list = document.getElementsByClassName("form-check-input");
        let skills_checked = [];
        for (let index = 0; index < skills_list.length; index++) {
            if (skills_list[index].checked) {
                skills_checked.push(skills_list[index].value);
            }
        }
        let request_body = {
            name: name,
            text: text,
            employmentType: employment_type,
            workSchedule: work_schedule,
            skills: skills_checked
        }
        let body = JSON.stringify(request_body);
        request.open("POST", "/restApi/resume");
        request.setRequestHeader('Content-Type', 'application/json');
        request.send(body);

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
        <a class="btn btn-outline-info" href="#">Выход</a>
    </div>
</header>
<main>
    <div class="container-fluid">
        <div class="row justify-content-center">
            <div class="col-sm-10 profile">

                <div class="row justify-content-center">
                    <h1 class="title">Создать резюме</h1>
                </div>

                <div class="row justify-content-center">
                    <div class="col-12 text-box">
                        <h1 class="pod_title">Название резюме</h1>
                        <input id="name" type="text" class="form-control" name="text"/>
                    </div>
                </div>

                <div class="row justify-content-center">
                    <div class="col-12 text-box">
                        <h1 class="pod_title">Дополнительная информация</h1>
                        <p><textarea class="form-control" id="text-about" name="text"></textarea></p>
                    </div>
                </div>


                <div class="row justify-content-start">
                    <select id="employment_type" size="5">
                        <option value="FULL_TIME" selected="selected">Full time</option>
                        <option value="PART_TIME">Part time</option>
                        <option value="TEMPORARY">Temporary</option>
                        <option value="WORK_PLACEMENT">Work placement</option>
                        <option value="VOLUNTEERING">Volunteering</option>
                    </select>
                </div>

                <div class="row justify-content-start">
                    <select id="work-schedule" size="3">
                        <option value="FULL_DAY" selected="selected">Full day</option>
                        <option value="FLEXIBLE_SCHEDULE">Flexible schedule</option>
                        <option value="REMOTE_WORKING">Remote working</option>
                    </select>
                </div>

                <div class="row justify-content-start">
                    <h1 class="pod_title">Компетенции</h1>
                </div>

                <div class="row justify-content-start">
                    <div id="skills-list" class="list-group list">

                    </div>
                </div>

                <button type="button" onclick="sendResumeInfo()" class="but btn-info btn">Сохранить</button>

            </div>


        </div>

    </div>

    </div>
</main>
<footer>
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
        request.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                let skills = JSON.parse(this.responseText);
                let skills_list = document.getElementById("skills-list")
                for (let i = 0; i < skills.length; i++) {
                    let skill = document.createElement("div");
                    skill.innerHTML = "<div class=\"form-check\">\n" +
                        "                            <input class=\"form-check-input\" type=\"checkbox\" value=" +
                        skills[i].id +
                        ">\n" +
                        "                            <label class=\"form-check-label h4\" for=\"defaultCheck1\">\n" +
                        skills[i].name +
                        "                            </label>\n" +
                        "                        </div>"
                    skills_list.appendChild(skill)
                }
            }
        };

        request.open("GET", "/students" + "/skills?confirmed=true");
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
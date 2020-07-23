<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Company</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/profile_company.css">
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <script>
        function f() {
            let url = window.location.pathname.toString();
            let urlArr = url.split("/");
            let id = urlArr[urlArr.length - 1]
            let edit_link = $('#edit_link');
            edit_link.attr('href', "/company_page/" + id + "/edit")
            let vacancies = document.getElementById('vacancies');
            let nameDiv = document.getElementById('nameDiv');
            let photo = $('#photo')
            let aboutDiv = document.getElementById('aboutDiv');
            $.ajax({
                url: "/companies/" + id,
                method: "GET",
                dataType: "json",
                contentType: "application/json",
                success:
                    function (response) {
                        nameDiv.innerHTML = response.name;
                        aboutDiv.innerHTML = response.about;
                        photo.attr("src", "/files/" + response.photo);
                        for (let i = 0; i < response.vacancies.length; i++) {
                            let li = document.createElement('li');
                            li.classList.add("list-group-item")
                            let a = document.createElement('a');
                            a.setAttribute("href", '/vacancies_page/' + response.vacancies[i].id)
                            a.innerHTML += response.vacancies[i].name;
                            li.appendChild(a);
                            vacancies.appendChild(li);
                        }
                    }
            })
        }
    </script>

</head>
<body onload="f()">
<header>
    <div class="d-flex flex-column flex-md-row align-items-center px-md-4">
        <a href="#" class="navbar-brand my-0 mr-md-auto"><img src="../img/logo.png" alt="logo"></a>
        <nav class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="#">Profile</a>
            <a class="p-2 text-dark" href="#">Notifications</a>
        </nav>
        <a class="btn btn-outline-info" onclick="logout()">Выход</a>

    </div>
</header>
<div class="container-fluid">
    <div class="row justify-content-center align-content-center">
        <div class="col-sm-10 profile">
            <div class="row">
                <div class="col-2 col-sm-4 avatar ">
                    <img id="photo" src="../img/avatar.png" class="img-fluid align-middle" alt="avatar">
                </div>
                <div class="col-4 col-sm-6 text-box">
                    <h1 id="nameDiv"></h1>
                    <p id="aboutDiv"></p>
                    <#if isCompany == true>
                        <a href="/company_page/${user.id}/edit" id="edit_link" class="btn btn-info">Edit</a>
                        <a href="/vacancies/add" class="btn btn-info">edit vacancies</a>
                    </#if>
                </div>

                <div class="col-2 col-sm-2 text-box">
                    <i class="fa fa-star" aria-hidden="true" style="font-size: 40px "> 25</i>
                </div>
            </div>

            <div class="row justify-content-center align-content-center">
                <div class="col-sm-10 profile">
                    <h1>Vacancies</h1>
                </div>
            </div>
            <div class="row justify-content-center align-content-center">
                <div class="col-sm-10 profile">
                    <ul class="list-group list" id="vacancies">
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</main>
</body>
</html>
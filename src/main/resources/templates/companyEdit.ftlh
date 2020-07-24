<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Редактирование</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous">
    </script>
    <script>
        function sendFile() {
            // данные для отправки
            let formData = new FormData();
            // забрал файл из input
            let files = ($('#file'))[0]['files'];
            // добавляю файл в formData
            [].forEach.call(files, function (file, i, files) {
                formData.append("file", file);
            });
            formData.append("userType", "2")
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/files",
                data: formData,
                processData: false,
                contentType: false
            })
        }

        function edit() {
            let vacanciesDiv = document.getElementById('vacanciesDiv');
            let nameInput = $('#nameInput').val();
            let aboutInput = $('#aboutInput').val()
            let data = {
                "name": nameInput,
                "about": aboutInput
            }
            console.log(nameInput)
            console.log(aboutInput)
            $.ajax({
                url: "/companies",
                method: "PUT",
                dataType: "json",
                data: JSON.stringify(data),
                contentType: "application/json",
                success:

                    function (response) {
                    }
            })
        }

    </script>
</head>
<body>
Change info
<div>
    <input type="file" id="file" name="file" placeholder="File name"/>
    <button onclick="sendFile()">
        Upload file
    </button>
    <input type="hidden" id="file_hidden">
    <div class="filename"></div>
</div>
<form id="editForm">
    <input id="nameInput" placeholder="name">
    <input id="aboutInput" placeholder="about">
</form>
<button onclick="edit()">edit</button>
</body>
</html>

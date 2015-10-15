<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script src="//code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src="/marked.js"></script>

    <script>
        function setdesc() {
            $.get("/puzzle/" + $("#puzzle option:selected").val() + ".md",
                    function (res, err) {
                        document.getElementById('content').innerHTML = marked(res)
                    })

            $.get("/results?puzzle=" + $("#puzzle option:selected").val(),
                    function (res, err) {
                        document.getElementById('leader').innerHTML = res;
                    });
        }

        $(document).ready(function () {
            $("#puzzle").change(setdesc);
            setdesc();
        })
    </script>
</head>
<body>
<form method="POST" action="/submit" enctype="multipart/form-data">
    <fieldset>
        <p>
            <select id="puzzle" name="puzzle">
                <option value="longString">Long to String</option>
                <option value="lexStrings">Sort Strings</option>
            </select>
        </p>
        <p>
            <label for="name">Your Name: </label><input id="name" type="text" name="name"/>
        </p>

        <p>
            <input type="file" name="file"/>
        </p>

        <p>
            <input type="submit"/>
        </p>
    </fieldset>
</form>
<div>
    <h4>Puzzle Description</h4>

    <div id="content"></div>
    <div id="leader"></div>

</div>
</body>
</html>

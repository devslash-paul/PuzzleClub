<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pault
  Date: 9/10/2015
  Time: 13:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        .success {
            color: green;
        }

        .fail {
            color: red;
        }

        .result {
            height: 35px;
        }

    </style>
</head>
<body>
<h2>Results</h2>
The tests took ${results.getTime()}
<table>
    <thead>
    <th>Test</th>
    <th>Result</th>
    <th>Notes</th>
    </thead>
    <c:forEach items="${results.getTestResults()}" var="entry">
        <tr class="result">
            <td>${entry.getTestName()}</td>
            <c:choose>
                <c:when test="${entry.success}">
                    <td class="success">Pass</td>
                </c:when>
                <c:otherwise>
                    <td class="fail">Fail</td>
                </c:otherwise>
            </c:choose>
            <td>${entry.getNotes()}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

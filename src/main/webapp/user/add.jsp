<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ include file="/user/header.jsp" %>
<div class="container-fluid">

    <!-- Page Heading -->
    <div class="d-sm-flex align-items-center justify-content-between mb-4">
        <h1 class="h3 mb-0 text-gray-800">UsersCRUD</h1>
        <a href='<c:url value="/userAdd"/>' class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
                class="fas fa-download fa-sm text-white-50"></i> Dodaj użytkownika</a>
    </div>
    <div>

        <form method="post">


                <p><input type="text" name="name" placeholder="imię"></p>
                <p> <input type="text" name="email" placeholder="email"></p>

<%--                UWAGA zmienić na type="password"--%>
                <p> <input type="text" name="password" placeholder="hasło"></p>
            <p href="<c:url value="/userList" />"> <input type="submit" name="save"  value="Zapisz"></p>

        </form>
    </div>

</div>

<%@ include file="/user/footer.jsp" %>


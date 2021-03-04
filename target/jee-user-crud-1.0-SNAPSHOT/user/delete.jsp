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
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="table-responsive">
                <table class="table">
                    <tr>
                        <th>Id</th>
                        <th>Imię</th>
                        <th>Email</th>
                        <th>Akcja</th>
                    </tr>
                    <tr>
<c:forEach items="${id}" var="user">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                     <form>
                         <input type="submit" value="Usuń">
                     </form>
                    </tr>

                    </c:forEach>
                </table>
            </h6>
        </div>
    </div>

</div>

<%@ include file="/user/footer.jsp" %>


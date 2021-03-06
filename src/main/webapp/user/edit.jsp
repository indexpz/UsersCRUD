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
                    <h6 class="m-0 font-weight-bold text-primary">Edycja użytkownika</h6>
                </div>
                <div class="card-body">
                    <form method="post">
                        <input type="hidden" name="id" value="${user.id}"/>
                        <div class="form-group">
                            <label for="userName">Nazwa</label>
                            <input value="${user.name}" name="name" type="text" class="form-control" id="userName" placeholder="Nazwa użytkownika">
                        </div>
                        <div class="form-group">
                            <label for="userEmail">Email</label>
                            <input value="${user.email}" name="email" type="email" class="form-control" id="userEmail" placeholder="Email użytkownika">
                        </div>
                        <div class="form-group">
                            <label for="password">Hasło</label>
                            <input name="password" type="password" class="form-control" id="password" placeholder="Hasło użytkownika">
                        </div>

                        <button type="submit" class="btn btn-primary">Edytuj</button>
                    </form>

                </div>
            </div>





<%--    <div class="card shadow mb-4">--%>
<%--        <div class="card-header py-3">--%>
<%--            <h6 class="table-responsive">--%>
<%--                <table class="table">--%>
<%--                    <form method="post">--%>
<%--                        <tr>--%>
<%--                            &lt;%&ndash;                        <th>Id</th>&ndash;%&gt;--%>
<%--                            <th>Imię</th>--%>
<%--                            <th>Email</th>--%>
<%--                            <th>Hasło</th>--%>
<%--                            &lt;%&ndash;                        <th>Akcja</th>&ndash;%&gt;--%>
<%--                        </tr>--%>

<%--                        <tr>--%>
<%--                            <td><input value="${user.name}" name="userName" type="text"/></td>--%>
<%--                            <td><input value="${user.email}" name="userEmail" type="text"/></td>--%>
<%--                            <td><input value="${user.password}" name="userPasword" type="text"/></td>--%>

<%--                        </tr>--%>

<%--                        <tr>--%>
<%--                            <td></td>--%>
<%--                            <td></td>--%>
<%--                            <td>--%>
<%--                                <a href='<c:url value="/userShow?id=${user.id}"/>'><input type="submit" value="Zmień"></a>--%>
<%--                                 </td>--%>
<%--                        </tr>--%>
<%--                    </form>--%>
<%--                </table>--%>
<%--            </h6>--%>
<%--        </div>--%>
<%--    </div>--%>

</div>

<%@ include file="/user/footer.jsp" %>


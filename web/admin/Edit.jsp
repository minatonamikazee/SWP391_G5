
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit person</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <jsp:include page="Menu.jsp"></jsp:include>
            <div class="row">
                <div class="col-md-3">
                </div>
                <div class="col-md-6">
                    <form action="editUser" method="post">
                        <h2>Edit person</h2>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Full Name</label>
                            <input value="${requestScope.user.fullname}" name="fullname" type="text" class="form-control" id="exampleInputEmail1">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Email</label>
                            <input value="${requestScope.user.email}" name="email" type="text" class="form-control" id="exampleInputEmail1">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Phone</label>
                            <input value="${requestScope.user.phone}"  name="phone" type="text" class="form-control" id="exampleInputEmail1">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Password</label>
                            <input value="${requestScope.user.password}"  name="password" type="text" class="form-control" id="exampleInputEmail1">
                        </div>
                        <div class="row">
                            <div class="col-md-4">
                                <label for="exampleInputEmail1">Gender</label>
                            </div>
                            <div class="form-check col-md-4">
                                <input class="form-check-input" type="radio" name="gender" id="exampleRadios1" value="male" 
                                       <c:if test="${requestScope.user.gender}">
                           checked="checked" 
                           </c:if>>
                                <label class="form-check-label" for="exampleRadios1">
                                    Male
                                </label>
                            </div>
                            <div class="form-check col-md-4">
                                <input class="form-check-input" type="radio" name="gender" id="exampleRadios2" value="female"  
                                       ${requestScope.user.gender?"":"checked=\"checked\""}
                                <label class="form-check-label" for="exampleRadios2">
                                    Female
                                </label>
                            </div>
                        </div>
                    <button type="submit" class="btn btn-primary">Edit person</button>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    </body>
</html>
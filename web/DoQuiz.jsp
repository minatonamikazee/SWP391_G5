<%-- 
    Document   : AddQuiz
    Created on : Sep 11, 2021, 3:38:44 PM
    Author     : Admin
    Fix        : 22/10/2021
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Quiz Handle</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/prettyPhoto.css" rel="stylesheet">
        <link href="css/price-range.css" rel="stylesheet">
        <link href="css/animate.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/responsive.css" rel="stylesheet">
        <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->
        <link rel="shortcut icon" href="images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
    </head>
    <!--/head-->

    <body>       
        <section>
            <div class="container">
                <br><br><br><br><br><br><br><hr>
                <div class="row">
                    <div>
                        <h1>${quiz.title}</h1> 
                    </div>

                </div>

                <div class="row">
                    <!--Question left -->
                    <div class="col-sm-2">   
                        <h4>Question ${page}</h4>
                        <h5>${answer.get(page-1).status}</h5>
                        <h5><i class="fa fa-flag"></i>  Flag</h5>
                    </div>

                    <!-- Question mid -->
                    <div class="col-sm-6" style="border-right: 1px solid #CCCCCC; border-left: 1px solid #CCCCCC">
                        <h4 style="color: cadetblue">${question.content}</h4>
                        <h5> Select one:</h5>
                        <div>                               
                            <input type="radio" id="html" name="fav_language" value="${question.option1}" ${question.option1 eq answer.get(page-1).option_correct?"checked":""}>
                            <label for="html">${question.option1}</label><br>
                            <input type="radio" id="css" name="fav_language" value="${question.option2}" ${question.option2 eq answer.get(page-1).option_correct?"checked":""}>
                            <label for="css">${question.option2}</label><br>
                            <input type="radio" id="javascript" name="fav_language" value="${question.option3}" ${question.option3 eq answer.get(page-1).option_correct?"checked":""}>
                              <label for="javascript">${question.option3}</label><br>
                            <input type="radio" id="c#" name="fav_language" value="${question.option4}" ${question.option4 eq answer.get(page-1).option_correct?"checked":""}>
                              <label for="c#">${question.option4}</label>
                        </div>
                        <br>  
                        
                        <c:if test="${page eq 1}">
                            <button class="btn btn-default" style="margin-top: 10px" onclick="changePage(${quiz.id},${page},${page+1})">Next</button>
                        </c:if>
                        <c:if test="${page eq pageSize}">
                            <button class="btn btn-default" style="margin-top: 10px" onclick="changePage(${quiz.id},${page},${page-1})">Previous</button>
                        </c:if>
                            <c:if test="${page ne 1 && page ne pageSize}">
                                <button class="btn btn-default" style="margin-top: 10px" onclick="changePage(${quiz.id},${page},${page-1})">Previous</button>
                                <button class="btn btn-default" style="margin-top: 10px" onclick="changePage(${quiz.id},${page},${page+1})">Next</button>                                
                            </c:if>
                            
                    </div>

                    <!-- question right -->
                    <div class="col-sm-4">
                        <h5>Question Navigation</h5>
                        <div class="pagination-area">
                            <ul class="pagination">      
                                <c:forEach begin="1" end="${pageSize}" var="i">
                                    <c:choose>
                                        <c:when test="${answer.get(i-1).status eq 'Answered'}">
                                            <li><a class="active" onclick="changePage(${quiz.id},${page},${i})">${i}</a></li>
                                        </c:when>
                                        <c:otherwise>
                                            <li><a onclick="changePage(${quiz.id},${page},${i})">${i}</a></li> 
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </ul>
                        </div>
                        <button id="durationx" hidden="true">${timer.duration}</button>
                        <div><span id="display" style="color:#FF0000;font-size:15px"></span></div>
                        <div><span id="submitted" style="color:#FF0000;font-size:15px"></span></div>
                        <button onclick="changePage(${quiz.id},${page},-1)"><a><h5 style="color: crimson">Finish attempt</h5></a></button>
                    </div>
                </div>
                <hr>
            </div>
        </section>


        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
        <script src="js/TimerHelper.js"></script>
        <script>
            function changePage(id,current,change){
                var selectcol = document.getElementsByName('fav_language');
                var select = "";
                var timer = document.getElementById('display').innerHTML;
                for(i = 0; i < selectcol.length; i++) {
                    if(selectcol[i].checked)
                        select = selectcol[i].value;
                }
                window.location.href = "SaveQuizHandle?id="+id+"&pre="+current+"&next="+change+"&select="+select+"&timer="+timer;
            }
        </script>
    </body>

</html>
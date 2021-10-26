<%-- 
    Document   : Login
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
        <title>User Detailed</title>
        <link href="css/customize.css" rel="stylesheet">
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
        <header id="header"><!--header-->


            <div class="header-middle"><!--header-middle-->
                <div class="container">
                    <div class="row">
                        <div class="col-sm-4">
                            <div class="logo pull-left">
                                <a href="Home"><img src="images/home/partner1.png" alt="" /></a>
                            </div>

                        </div>
                        <div class="col-sm-8">
                            <div class="shop-menu pull-right">
                                <ul class="nav navbar-nav">
                                    <li><a href="">Take test</a></li>
                                    <li><a href="PracticeList">Practice List</a></li>
                                    <li><a href="DocumentList">Document</a></li>
                                    <li><a href="Personal?email=${user.email}">Account</a></li>
                                    <li><a href="Logout">Log out</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!--/header-middle-->


        </header>
        <section>
            <div>

                <div class="container">
                    <div class="signup-form">
                        <!--sign up form-->
                        <h2>Practice Detailed</h2>
                        <form enctype="multipart/form-data" id="myForm" action="" method="post">
                            <img id="profile-ava" class="imageAvatar" src="${quiz.getThumbnail()}">
                            <br>

                            Title
                            <input name="title" type="text" placeholder="Tittle" readonly value="${quiz.getTitle()}" />
                            Description
                            <textarea name="description"type="text" readonly>${quiz.getDescription()}</textarea>

                            Subject

                            <input name="subject" type="text" placeholder="Tittle" readonly value="${sub.title}" />

                            Category
                            <input name="category" type="text" placeholder="Tittle" readonly value="${quiz.category}" />

                            Level
                            <input name="level"type="text" placeholder="Level" value="${quiz.getLevel()}" readonly />							
                            
                            Number of Questions: 
                            <input readonly value="${quiz.getNumber_of_question()}"/>

                            <a href="javascript:void(0)" onclick="Show('myTable')"><h2>Quiz Content</h2></a>


                            <table  class="table" id="myTable" style="display: none">
                                <tr>
                                    <th><strong><a href="javascript:void(0);" onclick="sort(0)">Sub-Category</a></strong></th>
                                    <th><strong><a href="javascript:void(0);" onclick="sort(1)"># Question</a></strong></th>                                   
                                </tr>

                                <c:forEach begin="0" end="${listCategory.size()-1}" var="i">
                                    <tr>
                                        <td>${listCategory.get(i).value}</td>
                                        <td>${array[i]}</td>                                        
                                    </tr>     
                                </c:forEach>
                            </table>

                        </form>
                        
                        <button class="btn btn-primary" style="margin-top: 10px;margin-bottom: 30px" onclick="Submit(${quiz.id})">Practice Result</button>    

                    </div>                       
                </div>





            </div>

        </section>
        <!--/form-->
        <footer id="footer"><!--Footer-->
            <div class="footer-top">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="companyinfo">
                                <h2><span>Mega</span>-Deal</h2>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,sed do eiusmod tempor</p>
                            </div>
                        </div>

                        <div class="col-sm-6">
                            <div class="address">
                                <img src="images/home/map.png" alt="" />
                                <p>505 S Atlantic Ave Virginia Beach, VA(Virginia)</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="footer-bottom">
                <div class="container">
                    <div class="row">
                        <p class="pull-left">Copyright © 2021 Mega-Deal Inc. All rights reserved.</p>
                        <p class="pull-right">Designed by <span><a target="_blank" href="http://www.themeum.com">Group 5</a></span></p>
                    </div>
                </div>
            </div>

        </footer>

        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
        <script>
            function Submit(id){
                window.location.href = "PracticeResult?id=" + id;
            }
        </script>
    </body>

</html>
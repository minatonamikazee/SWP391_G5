<%-- 
    Document   : Registration
    Created on : Sep 24, 2021, 8:57:25 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Landing page</title>
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
        <section id="form">
            <!--form-->
            <div class="container">
                <div class="row">
                    <div class="signup-form centerDiv">
                        <!--sign up form-->
                        <h2 class="centerImgLanding">New registration</h2>
                        <p class="text-primary">${mess}</p>
                        <form action="register" method="post">
                            <input name="email" type="text" placeholder="Email" required="true" />
                            <input name="name" type="text" placeholder="Full Name" required="true" />
                            <p>
                                <select name="gender" id="gender">
                                    <option value="male">Male</option>
                                    <option value="female">Female</option>
                                </select>
                            </p>
                            <input name="phone" type="text" placeholder="Phone Number" />
                            <input name="pass" type="password" placeholder="Password" required="true" />
                            <input name="repass" type="password" placeholder="Re-Password" required="true" />
                            <br>
                            <button type="submit" class="btn btn-default centerImgLanding">Signup</button>

                        </form>
                    </div>

                </div>
            </div>
        </section>
        <!--/form-->


        <script src="js/jquery.js"></script>
        <script src="js/price-range.js"></script>
        <script src="js/jquery.scrollUp.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jquery.prettyPhoto.js"></script>
        <script src="js/main.js"></script>
    </body>

</html>

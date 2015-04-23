<%-- 
    Document   : base
    Created on : 21 avr. 2015, 17:10:30
    Author     : hadware
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="header" fragment="true" %>

<%-- any content can be specified here e.g.: --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    <style>
        .navbar-header {
            float: left;
            padding: 15px;
            text-align: center;
            width: 100%;
        }
        .navbar-brand {float:none;}
    </style>
    <body style="background-color: whitesmoke">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    Gestionnaire AMAP
                </a>
            </div>
        </div><!-- /.container-fluid -->
    </nav>
    <div class="container">
        <div class="row">
            <div id="menu" class="col-md-3 panel">
                <jsp:include page="menu.jsp" />  
            </div>
            <div id="body" class="col-md-9">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><jsp:invoke fragment="header"/></h3>
                    </div>
                    <div class="panel-body container-fluid">
                        <jsp:doBody/>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="pagefooter" class="text-center col-md-offset-2 col-md-8">
                <p class="text-center">Site de gestion de je sais pas trop quoi pour le WEB-ACVL</p>
                © 2015 - Equipe 8
            </div>
        </div> 
    </div>


</body>
</html>
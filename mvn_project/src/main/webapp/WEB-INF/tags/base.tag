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
  <body>
    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="menu">
        <ul>
            <li>Acceuil</li>
            <li>Profil</li>
            <li>Truc</li>
        </ul>    </div>
    <div id="body">
      <jsp:doBody/>
    </div>
    <div id="pagefooter">
        Site de gestion de je sais pas trop quoi pour le WEB-ACVL </br>
        Equipe 8
    </div>
  </body>
</html>
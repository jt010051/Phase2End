<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
</head>
<body>
<center><h2>Please Login</h2></center>
<form name="form" action="LoginServlet" method="post" onsubmit="return validate()">
        <table align="center">
<tr>
         <td>Username</td>
         <td><input type="text" name="username" /></td>
         </tr>
         <tr>
         <td>Password</td>
         <td><input type="password" name="password" /></td>
         </tr>
         <tr>
         <td><input type="submit" value="Login"></input><input
         type="reset" value="Reset"></input></td>
         </tr>
          </table>
    </form>
</body>
</html>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>
    <h1>Log In</h1>
    <p><i>${message}</i></p>
    <form action="check" method="post">
    	<input type="hidden" name="action" value="add">
    	<table>
	    	<tr>
	    		<td><label class="pad_top">Username:</label>
		        <td><input type="text" name="username"><br>        
		    </tr>
		    <tr>
		    	<td><label class="pad_top">Password:</label>
		        <td><input type="password" name="password"><br>
		    </tr>
		    <tr>    
		        <td><label>&nbsp;</label>
		        <td><input type="submit" value="Log In" class="margin_left">
		    </tr>
        </table>
    </form>
    <h2>Password info</h2>
    <p>Hash:<br>${hashedPassword}</p>
    <p>Salt:<br>${salt}</p>
    <p>Salted Hash:<br>${saltedAndHashedPassword}</p>
</body>
</html>

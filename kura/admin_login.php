<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=yes, width=device-width"/>
    <link href="bootstrap.min.css" rel="stylesheet">
     <link href="styleb.css" rel="stylesheet">
	<script src="jquery.js"></script>
    <script src="livevalidation_standalone.js"></script>
    <script src="bootstrap.min.js"></script>
<title>Admin</title>
<?php
	require_once("funguo.php");
require_once("validation.php");

	session_start();
	
	if(isset($_SESSION['chocolate'])){
	?>
		    <meta http-equiv="refresh" content="0; url = admin_main.php"/>
      <?php }
?>   
</head>

<nav class="navbar navbar-default" role="navigation">
<div class="col-lg-12 navii  navbar-fixed-top  navbar-header container-fluid">

<p class="navbar-brand navbar-left">
	<a href="#"><img src="anigif.png"></a>
</p>

      <button type="button" class="navbar-toggle navbar-right" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle Navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

<div  class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

    
    <ul class="nav navbar-nav navbar-left">
    	<li>&nbsp;</li>

    </ul>
    

 <div class="hidden-lg hidden-md  visible-sm navbar-right"> 
  
</div>
      
   

    </div>
    
</div>
</nav>

<body>
<div align="center"  class="container">
<div class="row" align="center">
<font color="#ffffff" class="titlea col-lg-12 col-md-12 col-sm-12 col-xs-12" align="right">Please Login</font>
</div>
<br><br>
<form action="process.php" method="post">
	<table class="table-responsive" width="80%">
    	<tr>
        	<td align="center">Admin Username</td>    <td style="padding:5px"><input id="username" type="text" name="admin_name" class="form-control" placeholder="Admin Username" required></td>
            </tr>
             <script type="text/javascript">
		            var username = new LiveValidation('username', { validMessage: 'Ok...', wait: 500});
		            username.add(Validate.Presence, {failureMessage: "Numbers, special characters and spaces are not allowed"});
		            username.add(Validate.Format, {pattern: /^[a-zA-Z_]+$/, failureMessage: "Numbers, special characters and spaces are not allowed, use underscores (_) for spaces" }
					 );
		          </script> 
           
            <tr>
            <td align="center">Password</td>    <td style="padding:5px"><p><br></p><input id="password" type="password" name="password" class="form-control" placeholder="Password" required><p><br></p></td>
        </tr>
           <script type="text/javascript">
		            var password = new LiveValidation('password', { validMessage: 'Ok...', wait: 500});
		           password.add(Validate.Format, {pattern: /^(?=.*\d)(?=.*[a-zA-Z]).{4,20}$/, failureMessage: "Password must have atleast one Numeric and one Character and the  length of text string must be between 4 to 20 characters" } );					
		          </script>
        
        <tr>
        	<td colspan="2" align="center" ><input type="submit" value="Login" class="btn btn-danger wid-ya-btn" style="width:40%"><br><p></p></td>
        </tr>
        
      </table>
      
    </form>
    
       <p><br></p>
        
   
    </div>
    
		<!-- script references -->
        <div class="footer">
		
    </div>
</body>
</html>
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
<title>Admin</title><?php
session_start();
	if(!isset($_SESSION['chocolate'])){
	?>
		    <meta http-equiv="refresh" content="0; url = admin_login.php"/>
      <?php }
	  
require_once("funguo.php");
require_once("validation.php");

try{
	$conn = db_connect();

	  
?>
<div class="container-fluid">
	<table border="1" cellpadding="10" class="table table-responsive" width="80%">
    	<tr align="center" bgcolor="#000066">
        	<td> <font color="#FFFFFF">Actions</font></td>
        	<td><font color="#FFFFFF">id</font></td>
            <td><font color="#FFFFFF">First Name</font></td>
            <td><font color="#FFFFFF">Last Name</font></td>
            <td><font color="#FFFFFF">Gender</font></td>
            <td><font color="#FFFFFF">Phone Number</font></td>
            <td><font color="#FFFFFF">Email</font></td>
     
        </tr>
        
        <?php
		 $result = $conn->query("select * from register");
	  while($row = $result->fetch(PDO::FETCH_ASSOC)){
		?>
        <tr align="center">
        	<td>
            <form method="post" action="user_delete.php">
            <input type="hidden" name="first_name" value="<?php echo $row['first_name']; ?>">
            <input type="hidden" name="last_name" value="<?php echo $row['last_name']; ?>">
            <input type="hidden" name="phone_number" value="<?php echo $row['phone_number']; ?>">
            <input type="submit" value="delete">
            </form>
            </td>
        	<td><?php echo $row['id']; ?></td>
            <td><?php echo $row['first_name']; ?></td>
            <td><?php echo $row['last_name']; ?></td>
            <td><?php echo $row['gender']; ?></td>
            <td><?php echo $row['phone_number']; ?></td>
            <td><?php echo $row['email']; ?></td>

        </tr>
        <?php
	  }
		?>
    </table>
<?php
}catch(Exception $e){
     echo "Oops it seems an error occurred"; 
     exit;
	}catch(PDOException $e){
     echo "Oops it seems an error occurred"; 
     exit;
		}
?></div>
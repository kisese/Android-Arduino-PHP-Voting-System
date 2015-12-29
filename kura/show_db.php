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
	<div class="container">
	<table border="1" cellpadding="10" class="table table-responsive" width="80%">
    	<tr align="center" bgcolor="#000066">
        	<td> <font color="#FFFFFF">Actions</font></td>
        	<td><font color="#FFFFFF">id</font></td>
            <td><font color="#FFFFFF">name</font></td>
            <td><font color="#FFFFFF">vote_a</font></td>
            <td><font color="#FFFFFF">vote_b</font></td>
            <td><font color="#FFFFFF">vote_c</font></td>
            <td><font color="#FFFFFF">vote_d</font></td>
            <td><font color="#FFFFFF">vote_e</font></td>
            <td><font color="#FFFFFF">vote_e</font></td>
        </tr>
        <?php
		 $result = $conn->query("select * from votes");
	  while($row = $result->fetch(PDO::FETCH_ASSOC)){		  
		?>
        
        
        <tr align="center">
        	<td>
            <form method="post" action="delete.php">
            <input type="hidden" name="name" value="<?php echo $row['name']; ?>">
            <input type="submit" value="delete">
            </form>
            </td>
        	<td><?php echo $row['id']; ?></td>
            <td><?php echo $row['name']; ?></td>
            <td><?php echo $row['vote_a']; ?></td>
            <td><?php echo $row['vote_b']; ?></td>
            <td><?php echo $row['vote_c']; ?></td>
            <td><?php echo $row['vote_d']; ?></td>
			<td><?php echo $row['vote_e']; ?></td>
            <td><?php echo $row['vote_f']; ?></td>
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
?>
</div>
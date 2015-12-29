<?php

require_once("funguo.php");
require_once("validation.php");



	$conn = db_connect();
	
	$first_name = $_POST['first_name'];
	$last_name = $_POST['last_name'];
	$phone_number  =$_POST['phone_number'];
	
	$result = $conn->exec("delete from register where first_name = '$first_name' and last_name = '$last_name' and phone_number = '$phone_number'");
?>
<?php

require_once("funguo.php");
require_once("validation.php");



	$conn = db_connect();
	
	$name = $_POST['name'];
	
	$result = $conn->exec("delete from votes where name = '$name'");
?>
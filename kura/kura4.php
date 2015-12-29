<?php
//flag processing
require_once("funguo.php");
require_once("validation.php");

try{
	
	$re = $_POST['results'];
	 	
	$conn = db_connect();
		
		
	  $result = $conn->query("select * from flags");
	  while($row = $result->fetch(PDO::FETCH_ASSOC)){
		    $flag_a = $row['flag_a'];
			$flag_b = $row['flag_b'];
		  	$flag_c = $row['flag_c'];
			$flag_d = $row['flag_d'];
			$flag_e = $row['flag_e'];
			$flag_f = $row['flag_f'];
		  }

		  
	 if($flag_a == "true" && $flag_b == "true"){
		 	echo "pair 1";
		 }else if($flag_c == "true" && $flag_d == "true"){
			 	echo "pair 2";
			 }else if($flag_e == "true" && $flag_f == "true"){
				 echo "pair 3";
				 }else{
					 	echo "Exception";
					 }
	
	
	}catch(Exception $e){
     echo "Oops it seems an error occurred"; 
     exit;
	}catch(PDOException $e){
     echo "Oops it seems an error occurred"; 
     exit;
		}
	?>
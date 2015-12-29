<?php
//vote counting
require_once("funguo.php");
require_once("validation.php");

try{
	
	$re = $_POST['results'];
	 	$conn = db_connect();
		
		
 	  $result = $conn->query("select * from votes where vote_a = '1'");
	  $rows_a = $result->rowCount();
    	
	  $result2 = $conn->query("select * from votes where vote_b = '1'");
	  $rows_b = $result2->rowCount();
		
	  $result3 = $conn->query("select * from votes");
	  $rows_total = $result3->rowCount();
	  
	  $result4 = $conn->query("select * from register where gender = 'male'");
	  $rows_male = $result4->rowCount();
	  	  	  
	  $result5 = $conn->query("select * from register where gender = 'female'");
	  $rows_female = $result5->rowCount();
	  
	  $result6 = $conn->query("select * from register");
	  $rows_voters = $result6->rowCount();
	  
	  $result7 = $conn->query("select * from votes where vote_c = '1'");
	  $rows_c = $result7->rowCount();
	  
	  $result8 = $conn->query("select * from votes where vote_d = '1'");
	  $rows_d = $result8->rowCount();
	  
	  $result9 = $conn->query("select * from votes where vote_e = '1'");
	  $rows_e = $result9->rowCount();
	  
	  $result10 = $conn->query("select * from votes where vote_f = '1'");
	  $rows_f = $result10->rowCount();
		
	
	echo $rows_total."-".$rows_a."-".$rows_b."-".$rows_male."-".$rows_female."-".$rows_voters."-".$rows_c."-".$rows_d."-".$rows_e."-".$rows_f;

	}catch(Exception $e){
     echo "Oops it seems an error occurred"; 
     exit;
	}catch(PDOException $e){
     echo "Oops it seems an error occurred"; 
     exit;
		}
	?>
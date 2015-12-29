<?php
//vote insertion to database
require_once("funguo.php");
require_once("validation.php");

try{

	$conn = db_connect();
	

	$user = $_POST['user'];
	$candidate = $_POST['candidate'];
	$no_vote = $_POST['no_vote'];
	$vote = $_POST['vote'];
	
	
	/*
	$user = 'userc';
	$candidate = 'b';
	$no_vote = '0';
	$vote = '1';
	*/
	
	strtolower($user);
	strtolower($candidate);
			
	$result1 = $conn->query("select * from votes where name ='$user'");
	  if ($result1->rowCount()>0)  
  $result = $conn->exec("delete from votes where name like '%$user%'");
	
	
 	

	if($candidate=="a"){
	$result = $conn->exec("insert into votes(name, vote_a, vote_b, vote_c, vote_d, vote_e, vote_f) 
	values('$user', '$vote', '$no_vote', '$no_vote', '$no_vote', '$no_vote', '$no_vote')");
	}elseif($candidate=="b"){
		$result = $conn->exec("insert into votes(name, vote_a, vote_b, vote_c, vote_d, vote_e, vote_f) 
	values('$user', '$no_vote', '$vote', '$no_vote', '$no_vote', '$no_vote', '$no_vote')");
		}elseif($candidate=="c"){
		$result = $conn->exec("insert into votes(name, vote_a, vote_b, vote_c, vote_d, vote_e, vote_f) 
	values('$user', '$no_vote',  '$no_vote', '$vote', '$no_vote', '$no_vote', '$no_vote')");
		}elseif($candidate=="d"){
		$result = $conn->exec("insert into votes(name, vote_a, vote_b, vote_c, vote_d, vote_e, vote_f) 
	values('$user', '$no_vote',  '$no_vote', '$no_vote', '$vote', '$no_vote', '$no_vote')");
		}elseif($candidate=="e"){
		$result = $conn->exec("insert into votes(name, vote_a, vote_b, vote_c, vote_d, vote_e, vote_f) 
	values('$user', '$no_vote',  '$no_vote', '$no_vote', '$no_vote', '$vote', '$no_vote')");
		}elseif($candidate=="f"){
		$result = $conn->exec("insert into votes(name, vote_a, vote_b, vote_c, vote_d, vote_e, vote_f) 
	values('$user', '$no_vote',  '$no_vote', '$no_vote', '$no_vote', '$no_vote', '$vote')");
		}

		echo $candidate;

	
}catch(Exception $e){
     echo "Oops it seems an error occurred"; 
     exit;
	}catch(PDOException $e){
     echo "Oops it seems an error occurred"; 
     exit;
		}

?>
<?php
	//clear a users vote
require_once("funguo.php");
require_once("validation.php");

try{
	
		$vote = $_POST['vote'];
	 	$conn = db_connect();
		
		
 	  $result = $conn->exec("delete from votes where name like '%$vote%'");
	  echo "success";
	 
	  }catch(Exception $e){
    echo $e->getMessage(); 
     exit;
	}catch(PDOException $e){
   echo  $e->getMessage();  
     exit;
		}

?>
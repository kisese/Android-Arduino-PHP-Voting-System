<?php
	//see who the user voted for

require_once("funguo.php");
require_once("validation.php");

try{
	
		$vote = $_POST['vote'];
	 	$conn = db_connect();
		
		
 	  $result = $conn->query("select * from votes where name like '%$vote%'");
	  $row = $result->fetchObject();
      $id = $row->id;
	  $name = $row->name;
	  $vote_a = $row->vote_a;
	  $vote_b = $row->vote_b;
	  $vote_c = $row->vote_c;
	  $vote_d = $row->vote_d;
	  $vote_e = $row->vote_e;
	  $vote_f = $row->vote_f;
	  
	  if($vote_a==1){
		  echo "A"."-".$id;
		  }else if($vote_b==1){
			  echo "B";
			  }else if($vote_c==1){
			  echo "C";
			  }else if($vote_d==1){
			  echo "D";
			  }else if($vote_e==1){
			  echo "E";
			  }else if($vote_f==1){
			  echo "F";
			  }else{
				  echo "null";
				  }
	  
	  
	  
	  }catch(Exception $e){
    echo $e->getMessage(); 
     exit;
	}catch(PDOException $e){
   echo  $e->getMessage();  
     exit;
		}

?>
<?php


require_once("funguo.php");
require_once("validation.php");

try{

	$conn = db_connect();

	$first_name = $_POST['name'];
	$phone_number = $_POST['phone'];
	
	//$first_name = 'brian';
	//$phone_number = '0728762287';
	
	

	  // check forms filled in
	
	$result = $conn->prepare("SELECT * FROM register WHERE first_name = ? and phone_number = ?");
			 $statement = $result->execute(array($first_name, $phone_number));
			 
			if($statement){
					//if they are in the database register the user id
					$row = $result->fetch(PDO::FETCH_ASSOC);
      				$last_name = $row['last_name'];
					
							echo $first_name." ".$last_name;				
				}else{
					throw new Exception('Your username or phone number is incorrect please try again.');
					}
	
}catch(PDOException $e){
	 do_html_header_3("Problem");
	  echo $e->getMessage();
     echo "Oops it seems an error occurred"; 
     exit;
	}catch(Exception $e){
		 echo $e->getMessage();
	 echo "Oops it seems an error occurred";
     echo $e->getMessage(); 
     exit;
		}
	


?>
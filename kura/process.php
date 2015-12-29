<?php
$lifetime = 600;
session_start();
setcookie(session_name(), session_id(),NULL,NULL,NULL,true,true);
 

require_once("funguo.php");
require_once("validation.php");

try{

	$conn = db_connect();

	$first_name = $_POST['admin_name'];
	$phone_number = $_POST['password'];
	
	//$first_name = 'brian';
	//$phone_number = '0728762287';
	
	

	  // check forms filled in
	
	$result = $conn->prepare("SELECT * FROM admin WHERE user_name = ? and password = ?");
			 $statement = $result->execute(array($first_name, $phone_number));
			 
			if($statement){
					//if they are in the database register the user id
					
					$_SESSION['chocolate']  = $first_name;
					?>
                     <meta http-equiv="refresh" content="0; url = admin_main.php"/>
                    <?php				
				}else{
					throw new Exception('Your username or password is incorrect please try again.');
					}
	
}catch(PDOException $e){
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
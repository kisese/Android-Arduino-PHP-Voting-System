<?php

require_once("funguo.php");
require_once("validation.php");

try{

	$conn = db_connect();

$first_name = $_POST['first_name'];
$last_name = $_POST['last_name'];
$phone_number = $_POST['phone_number'];
$email = $_POST['email'];
$gender = $_POST['gender'];
$key = $first_name." ".$last_name; 


/*
$first_name = 'first name';
$last_name = 'last name';
$phone_number = '0728762287';
$email = 'email@email.com';
*/

	
	
	if (!valid_name($first_name)){
		throw new Exception('Numbers, special characters and spaces are not allowed and First Name must be 2 characters or more');
	}
	
	
	if (!valid_name($last_name)){
		throw new Exception('Numbers, special characters and spaces are not allowed and Last Name must be 2 characters or more');
	}
	
	if (!valid_phonenumber($phone_number)){
		throw new Exception('Please enter a valid phone number');
	}


	if (!valid_email($email)){
		throw new Exception('Please enter a valid email');
	}

ucfirst($first_name);
ucfirst($last_name);




	$result1 = $conn->query("select first_name, last_name from register where first_name ='$first_name' and last_name='$last_name'");
	  if ($result1->rowCount()>0) 
    throw new Exception('That user is already registered'); 

	$result = $conn->exec("insert into register(first_name, last_name, gender, phone_number, email) 
	values('$first_name', '$last_name', '$gender' ,'$phone_number', '$email')");
	

		echo $first_name." ".$last_name;

	
}catch(PDOException $e){
	 //do_html_header_3("Problem");
     echo "Oops it seems an error occurred"; 
     //do_html_footer();
     exit;
	}catch(Exception $e){
	 //do_html_header_3("Problem");
     echo $e->getMessage(); 
     //do_html_footer();
     exit;
		}

?>
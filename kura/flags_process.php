<?php
//user registration 
require_once("funguo.php");
require_once("validation.php");

try{

	$conn = db_connect();

	$flag = $_GET['flag'];


	if($flag == 'a'){
		$result = $conn->exec("update flags set flag_a = 'true', flag_b = 'true',
												flag_c = 'false', flag_d = 'false',
												flag_e = 'false', flag_f = 'false' 
												where id = '1'");
					echo "Flag set, active candidates A, B";
	}else if($flag == 'b'){
				$result = $conn->exec("update flags set flag_a = 'false', flag_b = 'false',
												flag_c = 'true', flag_d = 'true',
												flag_e = 'false', flag_f = 'false' 
												where id = '1'");
						echo "Flag set, active candidates C, D";
		}else if($flag == 'c'){
						$result = $conn->exec("update flags set flag_a = 'false', flag_b = 'false',
												flag_c = 'false', flag_d = 'false',
												flag_e = 'true', flag_f = 'true' 
												where id = '1'");
							echo "Flag set, active candidates E, F";
			}else{
					echo "<font color='#FF0000'>Invalid pair, please type in either a, b or c</font>";
					?>
                    <table border="1">
                     	<tr>
                	<td>Value</td><td>Representation</td>
                </tr>
                    	<tr>
                        	<td>a</td> <td>A, B</td>
                        </tr>
                        <tr>
                        	<td>b</td> <td>C, D</td>
                        </tr>
                        <tr>
                        	<td>c</td> <td>E, F</td>
                        </tr>
                    </table>
                    <?php
				}

		
}catch(Exception $e){
     echo "Oops it seems an error occurred"; 
     exit;
	}catch(PDOException $e){
     echo "Oops it seems an error occurred"; 
     exit;
		}
?>
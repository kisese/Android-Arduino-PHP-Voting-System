<?php
function db_connect()
{
   
	//$result = new PDO('mysql:host=sql309.byethost7.com;dbname=b7_13771430_kura;charset=utf8', 'b7_13771430', 'zrdx4fv6');
	$result = new PDO('mysql:host=localhost;dbname=kura;charset=utf8', 'root', '');
	$result->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
	$result->setAttribute(PDO::ATTR_EMULATE_PREPARES, false); 
   if (!$result)
      return false;
   return $result;
}
?>
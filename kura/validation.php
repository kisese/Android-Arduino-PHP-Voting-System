<?php

function filled_out($form_vars)
{
  // test that each variable has a value
  foreach ($form_vars as $key => $value)
  {
     if (!isset($key) || ($value == '')) 
        return false;
  } 
  return true;
}


	
function valid_name($user){
	 if (preg_match("/^[a-zA-Z' ]{1,}+$/", $user))
    return true;
  else 
    return false;
	}
	
	
function valid_phonenumber($num){
	if (preg_match('/^\s*(?:\+?(\d{1,3}))?[-. (]*(\d{3})[-. )]*(\d{3})[-. ]*(\d{4})(?: *x(\d+))?\s*$/', $num))
    return true;
  else 
    return false;
	}


function valid_email($address)
{
  // check an email address is possibly valid
  if (ereg('^[a-zA-Z0-9_\.\-]+@[a-zA-Z0-9\-]+\.[a-zA-Z0-9\-\.]+$', $address))
    return true;
  else 
    return false;
}



?>

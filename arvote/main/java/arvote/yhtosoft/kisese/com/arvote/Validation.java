package arvote.yhtosoft.kisese.com.arvote;

import android.widget.EditText;

import java.util.regex.Pattern;

/**
 * Created by Brayo on 1/5/2015.
 */
public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{3}-\\d{7}";
    private static final String NAME_REGEX = "^[a-zA-Z']{1,}+$";

    // Error Messages
    private static final String REQUIRED_MSG = "required";
    private static final String EMAIL_MSG = "invalid email";
    private static final String NAME_MSG = "numbers and special characters are not allowed";
    private static final String PHONE_MSG = "###-###-####";


    //call this method to check email validation
    public static boolean isEmailAddress(EditText editText, boolean required){
        return isValid(editText, EMAIL_REGEX, EMAIL_MSG, required);
    }

    //call this method to check email validation
    public static boolean isName(EditText editText, boolean required){
        return isValid(editText, NAME_REGEX, NAME_MSG, required);
    }


    //call this method when you need to check phone number validation
    public static boolean isPhoneNumber(EditText editText, boolean required){
        return isValid(editText, PHONE_REGEX, PHONE_MSG, required);
    }

    //return true if input field is valid based on the passed parameter
    public static boolean isValid(EditText editText, String regex, String errMsg, boolean required){

        String text = editText.getText().toString().trim();
        //clearing the error if it was previously set by other values
        editText.setError(null);

        //text required and ediText is blank, so return false
        if(required && !hasText(editText)) return false;

        //pattern doesnt match so returnning false
        if(required && !Pattern.matches(regex, text)){
            editText.setError(errMsg);
            return false;
        }

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean hasText(EditText editText) {

        String text = editText.getText().toString().trim();
        editText.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
            editText.setError(REQUIRED_MSG);
            return false;
        }

        return true;
    }
}
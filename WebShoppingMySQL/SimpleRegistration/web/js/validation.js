function validateForm(){
    
    //Using a regular expression to check for the pattern of the admin number
var regex = "^[0-9]{6}[a-zA-Z]{1}$";
var adminnumber = document.forms["student"]["adminno"].value;
var match = adminnumber.match(regex);

//If admin number does not match the admin number pattern
//Note: equality comparison sign in JavaScripts comprises three equal signs
if(match === null)
{
    alert("The admin number is not given in the correct format. Please ensure that is has 6 digits followed by an alphabet.");
    // This will tell the form not to proceed to the servlet
    return false;
}
}


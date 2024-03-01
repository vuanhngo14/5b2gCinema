
var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Login failed!");
	}
}
displayFailMessage();
 // Function to fetch user details based on the userId
 function fetchUserDetails(userId, callback) {
    if (typeof callback !== 'function') {
        console.error('Error: callback is not a function');
        return;
    }
    $.ajax({
        url: "viewAcc",
        type: "POST",
        data: {accID: userId},
        "dataType": "json",
        complete : (data) => {
        		var user = data.responseJSON;
	    	callback(user);
	    },
	    error: function(error) {
            console.log("Error fetching user details: " + error);
            callback(null);
        }
	    });
}

// Retrieve user details from URL parameters
const userId = localStorage.getItem("accID");

function handleUserDetails(user) {

    // Display the user details on the page
    document.getElementById('role').value = user.profile.name;
    document.getElementById('accID').value = user.accountID;
    document.getElementById('uname').value = user.username;
 
}

fetchUserDetails(userId, handleUserDetails);


function submitForm(){
	var userId = localStorage.getItem("accID");
	$.ajax({
        url: "logoutAdmin",
        type: "POST",
        data: {accID: userId},
        success: function(response) {
			var result= response;
			
			if(result){
				window.location.href = './loginPage.html';
			}else{
				window.location.href = './logoutPage.html?status=fail';
			}
		    
		  }
	    });
}
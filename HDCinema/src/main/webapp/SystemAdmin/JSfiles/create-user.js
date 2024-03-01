var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to create account!");
	}
}
displayFailMessage();

  
  
  function submitForm(){
	   var accidinput = document.getElementById("accID").value;
	    var nameInput = document.getElementById("name").value;
	    var roleInput = document.getElementById("role").value;
	    var uNameInput = document.getElementById("username").value;
	    var pWordInput = document.getElementById("password").value;
	    if (accidinput.trim() == "" || nameInput.trim() == "" || roleInput.trim() == "" || uNameInput.trim() == "" || pWordInput.trim() == "") {
	      
	       alert("Please enter all field");
	       return false;
	      
	    } else {
	
	      		$.ajax({
			    type: "POST",
			    url: "../createAcc",
			    data:{
					accID: accidinput,
					name: nameInput,
					type: roleInput,
					username:uNameInput,
					password: pWordInput
				},
			    dataType: "json",
			    success: function(response) {
			      var result = response;
			      console.log(response);
			      // Use the boolean value as needed
			      if (result) {
					  window.location.href = "./create-confirmation.html";
			       
			      } else {
			        window.location.href = "./create-user.html?status=fail";
			      }
			    },
			    error: function(xhr, status, error) {

			    }
			  });
	 }
  }
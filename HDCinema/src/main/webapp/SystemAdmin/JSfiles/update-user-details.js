var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to update account!");
	}
}
displayFailMessage();

const userId = urlParams.get('userId');
document.getElementById("accID").value = userId;
document.getElementById("accID").innerText = userId;
  
  
  
  
  function submitForm() {
    var accidinput = document.getElementById("accID").value;
    var nameInput = document.getElementById("name").value;
    var roleInput = document.getElementById("role").value;
    var uNameInput = document.getElementById("uName").value;
    var pWordInput = document.getElementById("pWord").value;
    var statusInput = document.getElementById("status").value;
    if (nameInput.trim() == "" || roleInput.trim() == "" || uNameInput.trim() == "" || pWordInput.trim() == "" || statusInput.trim() == "") {
      
       alert("Please enter all field");
       return false;
      
    } else {
			$.ajax({
			    type: "POST",
			    url: "../updateAcc",
			    data:{
					accID: accidinput,
					name: nameInput,
					type: roleInput,
					username:uNameInput,
					password: pWordInput,
					status: statusInput
				},
			    dataType: "json",
			    success: function(response) {
			      var result = response;
			      if (result) {
					  window.location.href = "./update-confirmation.html";
			       
			      } else {
			        window.location.href = "./update-user-details.html?status=fail&userId="+userId;
			      }
			    },
			    error: function(xhr, status, error) {
			    }
			  });
    }
    
   
  }
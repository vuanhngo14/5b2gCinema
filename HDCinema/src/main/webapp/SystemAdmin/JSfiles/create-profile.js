var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to create profile!");
	}
}
displayFailMessage();

  
  function submitForm() {
	
    var profidinput = document.getElementById("profID").value;
    var nameInput = document.getElementById("name").value;
    if (profidinput.trim() == "" || nameInput.trim() == "") {
       alert("Please enter all field");
       return false;
      
    } else{
		$.ajax({
	    type: "POST",
	    url: "../createProf",
	    data:{
			profID: profidinput,
			name: nameInput
		},
	    dataType: "json",
	    success: function(response) {
	      var result = response;
	      console.log(response);

	      if (result) {
			  window.location.href = "./create-profile-confirmation.html";
	       
	      } else {
	        window.location.href = "./create-profile.html?status=fail";
	      }
	    },
	    error: function(xhr, status, error) {

	    }
	  });
	}
    
    
   
  }
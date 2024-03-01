var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to update profile!");
	}
}
displayFailMessage();

const profId = urlParams.get('profID');
document.getElementById("profID").value = profId;
document.getElementById("profID").innerText = profId;


  
  function submitForm(){
	  var nameInput = document.getElementById("name").value;
    var statusInput = document.getElementById("status").value;
    if (nameInput.trim() == ""  || statusInput.trim() == "") {
      
       alert("Please enter all field");
       return false;
      
    }else{
		$.ajax({
	    type: "POST",
	    url: "../updateProf",
	    data:{
			profID: profId,
			name: nameInput,
			status: statusInput
		},
	    dataType: "json",
	    success: function(response) {
	      var result = response;
	      console.log(response);
	      if (result) {
			  window.location.href = "./update-profile-confirmation.html";
	       
	      } else {
	        window.location.href = "./update-profile-details.html?status=fail&profID="+profId;
	      }
	    },
	    error: function(xhr, status, error) {
	    }
	  });
	}
  }
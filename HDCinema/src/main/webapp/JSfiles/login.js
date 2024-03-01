localStorage.setItem("accID", null);
localStorage.setItem("userName", null);

var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Login failed!");
	}
}
displayFailMessage();

function populateProfileList() {
    $.ajax({
        async : true,
        "url": "getProfileList",
        "type": "get",
        "dataType": "json",
        "complete" : (data) => {
            var profile_list = data.responseJSON;
            var select = document.getElementById("profile-list");
            for (index in profile_list) {
                var option = document.createElement("option");
                option.value = profile_list[index].name;
                option.innerText = profile_list[index].name;
                select.appendChild(option);
            }
        }
    });
}

populateProfileList();

function updateAction () {
    var form = document.getElementById("login-form");
    var roleID = document.getElementById("profile-list").value;

    if (roleID == "SystemAdmin"){
        form.action = "loginAdmin";
    }
    else if (roleID == "Manager"){
        form.action = "loginMan";
    }
    else if (roleID == "Staff"){
        form.action = "loginStaff";
    }
    else if (roleID == "Customer"){
        form.action = "loginCust";
    }
    
}

var username = "";
var userId ="";

function setKeyValue(val){
	localStorage.setItem("userName", val);
	username = val;
	setIdByUsername(username);
}


function setIdByUsername(userName){
	console.log("hi");
	$.ajax({
    type: "POST",
    url: "getIDByUSer",
    data:{
		username: userName
	},
    dataType: "text",
    success: function(response) {
      var result = response;
      console.log(result);
        localStorage.setItem("accID", result);
    },
    error: function(xhr, status, error) {
    }
  });
  

}


function submitForm(){
	console.log("button click");
	 var username = document.getElementById("usernameInput").value;
      var password = document.getElementById("passwordInput").value;
      var roleID = document.getElementById("profile-list").value;
      var url="";
      if (roleID == "SystemAdmin"){
        url = "loginAdmin";
    }
    else if (roleID == "Manager"){
        url = "loginMan";
    }
    else if (roleID == "Staff"){
        url = "loginStaff";
    }
    else if (roleID == "Customer"){
        url = "loginCust";
    }
	
	$.ajax({
    type: "POST",
    url: url,
    data:{
		username: username,
		password: password,
		roleID: roleID
	},
    dataType: "json",
    success: function(response) {
      var result = response;
      console.log(response);
      if (result) {
		  if (roleID == "SystemAdmin"){
		         window.location.href = "./SystemAdmin/systemAdminDashboard.html";
		    }
		    else if (roleID == "Manager"){
		        window.location.href = "./Manager/movies.html";
		    }
		    else if (roleID == "Staff"){
		        window.location.href = "./Staff/staff.html";
		    }
		    else if (roleID == "Customer"){
		        window.location.href = "./Customer/custHome.html";
		    }
       
      } else {
        window.location.href = "./loginPage.html?status=fail";
      }
    },
    error: function(xhr, status, error) {
    }
  });
}
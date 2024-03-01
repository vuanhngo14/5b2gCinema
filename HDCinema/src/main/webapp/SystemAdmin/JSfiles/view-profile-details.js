var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to suspend profile!");
	}
}
displayFailMessage();

// Function to fetch user details based on the userId
 function fetchProfileDetails(profId, callback) {
    if (typeof callback !== 'function') {
        console.error('Error: callback is not a function');
        return;
    }
    $.ajax({
        url: "../viewProf",
        type: "POST",
        data: {profID: profId},
        "dataType": "json",
        complete : (data) => {
        		var profile = data.responseJSON;
        		console.log(profile);
	    	callback(profile);
	    },
	    error: function(error) {
            console.log("Error fetching user details: " + error);
            callback(null);
        }
	    });
}

// Retrieve profile details from URL parameters
const profId = urlParams.get('profId');

function handleProfileDetails(profile) {

    // Display the user details on the page
    document.getElementById('name').value = profile.name;
    document.getElementById('profID').value = profile.profileID;
    document.getElementById('status').value = profile.status;
}

fetchProfileDetails(profId, handleProfileDetails);

document.addEventListener("DOMContentLoaded", function () {
    var userForm = document.getElementById("userForm");
    var updateLink = document.getElementById("updateLink");


    var urlParams = new URLSearchParams(window.location.search);
    var profID = urlParams.get("profId");
    
      // Update the "Update" button link with the user ID
      fetchProfileDetails(profId, handleProfileDetails);
      updateLink.href = "./update-profile-details.html?profID=" + profID;
    
  }
)



// for buttons
  const suspendButton = document.getElementById('suspendButton');

  suspendButton.addEventListener('click', function() {
    const confirmation = confirm('Are you sure you want to suspend the profile?');
    if (confirmation) {
       $.ajax({
        url: "../suspendProf",
        type: "POST",
        data: {profID: profId},
        success: function(response) {
    		var result = response;
    		
    		if(result){
				window.location.href =  "./suspend-profile-confirmation.html";
			}else{
				window.location.href =  "./view-profile-details.html?status=fail&profId="+profId;
			}
		}
		});
    }
  });



// for NavBar

let menu = document.querySelector('#menu-bars');
let navbar = document.querySelector('.navbar');

menu.onclick = () =>{
    menu.classList.toggle('fa-times');
    navbar.classList.toggle('active');
}
window.onscroll = () =>{
    menu.classList.remove('fa-times');
    navbar.classList.remove('active');
}
// Initialize Swiper 
var swiper = new Swiper(".home-slider", {
  spaceBetween: 30,
  centeredSlides: true,
  autoplay: {
    delay: 7500,
    disableOnInteraction: false,
  },
  pagination: {
    el: ".swiper-pagination",
    clickable: true,
  },
  loop:true,
});
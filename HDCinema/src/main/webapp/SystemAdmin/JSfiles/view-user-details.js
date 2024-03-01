var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to suspend account!");
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
        url: "../viewAcc",
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
const userId = urlParams.get('userId');

function handleUserDetails(user) {

    // Display the user details on the page
    document.getElementById('name').value = user.name;
    document.getElementById('role').value = user.profile.name;
    document.getElementById('accID').value = user.accountID;
    document.getElementById('status').value = user.status;
}

fetchUserDetails(userId, handleUserDetails);


document.addEventListener("DOMContentLoaded", function () {
    var userForm = document.getElementById("userForm");
    var updateLink = document.getElementById("updateLink");

    var urlParams = new URLSearchParams(window.location.search);
    var userId = urlParams.get("userId");

   fetchUserDetails(userId, handleUserDetails);
      updateLink.href = "./update-user-details.html?userId=" + userId;
  }
)


// for buttons
  const suspendButton = document.getElementById('suspendButton');

  suspendButton.addEventListener('click', function() {
    const confirmation = confirm('Are you sure you want to suspend the user?');
    if (confirmation) {
      $.ajax({
        url: "../suspendAcc",
        type: "POST",
        data: {accID: userId},
        success: function(response) {
    		var result = response;
    		if(result){
				window.location.href = "./suspend-confirmation.html";
			}else{
				window.location.href = "./view-user-details.html?status=fail&userId="+userId;
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
document.querySelector('#search-icon').onclick = () =>{
    document.querySelector('#search-form').classList.toggle('active');
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
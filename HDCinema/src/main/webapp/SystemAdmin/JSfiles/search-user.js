var urlParams = new URLSearchParams(window.location.search);
var status = urlParams.get('status');
console.log(status);

function displayFailMessage(){
	if(status == "fail"){
		alert("Failed to search account!");
	}
}
displayFailMessage();


function handleKeyPress(event) {
  if (event.key === 'Enter') {
	  console.log("enter pressed");
    event.preventDefault(); // Prevent form submission or other default behavior
    var username = document.getElementById("uName").value;
    console.log(username);
     $.ajax({
        url: "../searchAcc",
        type: "POST",
        data: {username: username},
        "dataType": "json",
        complete : (data) => {
			if(data.responseJSON == null){
				window.location.href = "./search-user.html?status=fail";
			}else{
				var user = data.responseJSON;
	    	document.getElementById('name').value = user.name;
			document.getElementById('role').value = user.profile.name;
			document.getElementById('accID').value = user.accountID;
			}
	    },
	    error: function(error) {
            console.log("Error fetching user details: " + error);
        }
	    });
	    console.log("wow");
  }
}

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
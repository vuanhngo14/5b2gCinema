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
document.querySelector('#close').onclick = () =>{
    document.querySelector('#search-form').classList.remove('active');
}
// starter js end

const accID = localStorage.getItem('accID');
function submitReview(){
	            //var accId_val = $("#accID").val();
	            //var accId_val = document.getElementById("accId").value;
	            var reward_val = document.getElementById("choice").value;
	           
	            //console.log(accId_val);
	            console.log(reward_val);

                $.ajax({
                    url: "../redeemLoyaltyPoint",  // Replace with your servlet URL
                    type: "GET",
                    data: {
                        accId: accID,
                        points: reward_val,
                    },
                    success: function(response) {
                        console.log("AJAX request successful");
                        window.location.href = "./custHome.html";
                    },
                    error: function(xhr, status, error) {
                        console.log("AJAX request failed: " + error);
                    }
                });
}


console.log("hi");
console.log(accID);


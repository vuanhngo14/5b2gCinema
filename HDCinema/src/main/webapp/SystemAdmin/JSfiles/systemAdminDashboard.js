var urlParams = new URLSearchParams(window.location.search);


function myFunction() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[0];
      if (td) {
        txtValue = td.textContent || td.innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      }
    }
  }
  
  function populateAccountList() {
    $.ajax({
        async : true,
        "url": "../getAccountList",
        "type": "get",
        "dataType": "json",
        "complete" : (data) => {
            var account_list = data.responseJSON;
              var tableBody = document.getElementById("tableBody");
  tableBody.innerHTML = ""; // Clear previous table rows
            console.log(account_list);
			for (index in account_list) {
				console.log(account_list[index]);
				var row = document.createElement("tr");
			      var nameCell = document.createElement("td");
			      var nameLink = document.createElement("a");
			      nameLink.href = "./view-user-details.html?userId=" + account_list[index].accountID;
			      nameLink.textContent = account_list[index].name; 
			
			      // Attach event listener to handle click event
			      nameLink.addEventListener("click", function (event) {
			      event.preventDefault(); // Prevent the default hyperlink behavior
			      var userId = account_list[index].accountID; // Get the user ID from the user object

			      displayUserDetails(userId);
			      });
			
			      nameCell.appendChild(nameLink);
			      row.appendChild(nameCell);
			      row.innerHTML += `
			      <td>${account_list[index].profile.name}</td>
			      <td>${account_list[index].accountID}</td>
			      `;
			      tableBody.appendChild(row);
			}
        }
    });
}
 populateAccountList();
 
 function handleKeyPress(event) {
  if (event.key === 'Enter') {
    event.preventDefault(); // Prevent form submission or other default behavior
    
    console.log('Enter key pressed!');
  }
}

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
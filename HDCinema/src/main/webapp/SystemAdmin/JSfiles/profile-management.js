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
  
  function populateProfileList() {
    $.ajax({
        async : true,
        "url": "../getProfileList",
        "type": "get",
        "dataType": "json",
        "complete" : (data) => {
            var profile_list = data.responseJSON;
              var tableBody = document.getElementById("tableBody");
  				tableBody.innerHTML = ""; // Clear previous table rows
            console.log(profile_list);
			for (index in profile_list) {
				console.log(profile_list[index]);
			      
			      var row = document.createElement("tr");
			  
			      var roleCell = document.createElement("td");
			      var roleLink = document.createElement("a");
			      roleLink.href = "./view-profile-details.html?profId=" + profile_list[index].profileID; 
			      roleLink.textContent = profile_list[index].profileID; 
			  
			      roleCell.appendChild(roleLink);
			      
			      row.innerHTML += `
			      <td>${profile_list[index].name}</td>
			      `;
			      row.appendChild(roleCell);
			      tableBody.appendChild(row);
			}
        }
    });
}
 populateProfileList();


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
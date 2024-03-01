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
  
  function populateMovieList() {
    $.ajax({
        async : true,
        "url": "../getMovieName	",
        "type": "get",
        "dataType": "json",
        "complete" : (data) => {
            var movie_list = data.responseJSON;
            console.log(movie_list);
              var tableBody = document.getElementById("tableBody2");
  			tableBody.innerHTML = ""; // Clear previous table rows
			for (index in movie_list) {
				var row = document.createElement("tr");
			      row.innerHTML += `
			      <td>${movie_list[index].id}</td>
			      <td>${movie_list[index].title}</td>
			      `;
			      tableBody.appendChild(row);
			}
        }
    });
}
populateMovieList();
  
  function populateRRList() {
    $.ajax({
        async : true,
        "url": "../getRRList",
        "type": "get",
        "dataType": "json",
        "complete" : (data) => {
            var RR_list = data.responseJSON;
              var tableBody = document.getElementById("tableBody");
  tableBody.innerHTML = ""; // Clear previous table rows
            console.log(RR_list);
			for (index in RR_list) {
				console.log(RR_list[index]);
				var row = document.createElement("tr");
					
				
			      row.innerHTML += `
			      <td>${RR_list[index].movieID}</td>
			      <td>${RR_list[index].rating}</td>
			       <td>${RR_list[index].review}</td>
			      `;
			      tableBody.appendChild(row);
			}
        }
    });
}
 populateRRList();
 
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
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

const allStar = document.querySelectorAll('.rating .star')
const ratingValue = document.querySelector('.rating input')

allStar.forEach((item, idx)=> {
	item.addEventListener('click', function () {
		let click = 0
		ratingValue.value = idx + 1

		allStar.forEach(i=> {
			i.classList.replace('bxs-star', 'bx-star')
			i.classList.remove('active')
		})
		for(let i=0; i<allStar.length; i++) {
			if(i <= idx) {
				allStar[i].classList.replace('bx-star', 'bxs-star')
				allStar[i].classList.add('active')
			} else {
				allStar[i].style.setProperty('--i', click)
				click++
			}
		}
	})
})

const accID = localStorage.getItem('accID');
function submitReview(){
	
	            //var accId_val = document.getElementById("accID").value;
	            var accId_val = accID;
	            var movieId_val = document.getElementById("movieID").value;
	            var review_val = document.getElementById("review_opinion").value;
	            //var rating_val = document.getElementsByName('rating');
	            
	            var selectedRadioButton = document.querySelector('input[name="rating"]:checked');
	            var selectedValue = "";
    			// Check if a radio button is selected
    			if (selectedRadioButton) {
      				selectedValue = selectedRadioButton.value;
      				console.log('Selected value:', selectedValue);
    			}
	            console.log(accId_val);
	            console.log(movieId_val);
	            //console.log(selected_rating);
	            console.log(review_val);

                $.ajax({
                    url: "../submitReview",  // Replace with your servlet URL
                    type: "GET",
                    data: {
                        accId: accId_val,
                        movieId: movieId_val,
                        opinion: review_val,
                        rating: selectedValue 
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




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

function toggleValueField(checkbox) {
  var quantityField = checkbox.parentNode.querySelector("input[type='number']");
  quantityField.disabled = !checkbox.checked;
}

var namesString; 
var quantitiesString; 
console.log("hello wolrd ");
function checkOut(){
	
	var names = $('[name="FoodDrink"]:checked').map(function() {
	  return this.value;
	}).get();
	

	var quantities = $('[name="quantity"]:not(:disabled)').map(function() {
    	return this.value;
	}).get();
	
	
	
	namesString = JSON.stringify(names);
	quantitiesString = JSON.stringify(quantities);



     
     $.ajax({
        url: '../checkOutC', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
           names: namesString,
           quantities: quantitiesString,
           
        },
        success: function (resultText) {
			const encodedResultText = encodeURIComponent(resultText);
     		window.location.href = `makePaymentCust2.html?result=${encodedResultText}`;
			//window.location.href = 'makePaymentCust2.html';
			
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
     
     
}



function listItem() {

  	
     $.ajax({
        url: '../SystemListOrderC', // Sending to Ajax Handler 
        method: 'POST',	  // Using POST Method 
        data: {
			
        },
        success: function (result) {
           var foodDrink = JSON.parse(result); 
           var foodDrinkContainer =$(`#food-list`);
           
           foodDrink.forEach(function(fd){
				
				var div = $('<div class="inputBox"></div)');
				var inputDiv = $('<div class="input"></div>'); 
				var inputDiv2 = $('<div class="input"></div>'); 

        		var checkBox = $('<input type="checkbox" name="FoodDrink" value="'+fd.name+'" onclick="toggleValueField(this)">');
                var label = $('<label for="FoodDrink">'+fd.name+'</label>');
                var quantity = $('<input type="number" name="quantity" min="1" max="10" value="1" disabled>');

				div.append(inputDiv); 
				inputDiv.append(label); 
				

				div.append(checkBox); 
				div.append(quantity); 
				
				
				foodDrinkContainer.append(div); 
                
			
			});
        },
        error: function (jqXHR, exception) {
           console.log('Error occured!!');
        }
     });
  }

/*
function submitReview(){
	
		var checkboxes = document.getElementsByClassName("fDCheckboc");
              
              // Create an array to store the values of checked checkboxes
        var checkedValues = [];

        // Iterate through the checkboxes
        for (var i = 0; i < checkboxes.length; i++) {
          // Check if the current checkbox is checked
            if (checkboxes[i].checked) {
            // Retrieve the value of the checked checkbox
            var checkboxValue = checkboxes[i].value;
            // Add the value to the array of checked values
            checkedValues.push(checkboxValue);
            
            console.log("Checked value: " + checkboxValue);
            console.log(checkedValues);
            }
            
        }
        console.log(checkedValues);

                $.ajax({
                    url: "purchaseFoodDrink",  // Replace with your servlet URL
                    type: "GET",
                    data: {
						
                        checkboxValues: JSON.stringify(checkedValues)
                        
                    },
                    success: function(response) {
                        console.log("AJAX request successful");
                        window.location.href = "./makePaymentCust2.html";
                    },
                    error: function(xhr, status, error) {
                        console.log("AJAX request failed: " + error);
                    }
                });
}

*/
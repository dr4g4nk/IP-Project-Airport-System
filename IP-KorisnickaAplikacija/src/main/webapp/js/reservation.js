/**
 * 
 */
function getStartCities(account) {
	var select = document.getElementById("startCountry");
	var country = select.options[select.selectedIndex].value;
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status == 200)) {
			let response = JSON.parse(request.responseText);

			let element = document.getElementById("startCity");
			
			element.innerHTML = '';
			
			let html = " ";

			for (let i = 0; i < response.length; ++i) {
				html += "<option"+ (i==0 ? " selected":"") +" value='"+response[i]+"'>" + response[i] + "</option>";
			}
			element.innerHTML = html;
			getEndCountries(account);
		}
	};

	request.open("get", "/IP-KorisnickaAplikacija/fligths-locations?account="+account+"&action=getStartCities&country=" + country, true);
	request.send();
}

function getEndCountries(account) {

	var selectCountry = document.getElementById("startCountry");
	var selectCity = document.getElementById("startCity");
	var country = selectCountry.options[selectCountry.selectedIndex].value;
	var city = selectCity.options[selectCity.selectedIndex].value;

	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status == 200)) {
			let response = JSON.parse(request.responseText);

			let element = document.getElementById("endCountry");

			let html = "";

			for (let i = 0; i < response.length; ++i) {
				html += "<option"+ (i==0 ? " selected":"") +" value='"+response[i]+"'>" + response[i] + "</option>";
			}

			element.innerHTML = html;
			getEndCities(account);
		}
	};

	request.open("get", "/IP-KorisnickaAplikacija/fligths-locations?account="+account+"&action=getEndCountries&country=" + country + "&city=" + city, true);
	request.send();
}

function getEndCities(account) {
	
	var select = document.getElementById("endCountry");
	var country = select.options[select.selectedIndex].value;

	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status == 200)) {
			let response = JSON.parse(request.responseText);

console.log(response);

			let element = document.getElementById("endCity");
			let html = "";
			for (let i = 0; i < response.length; ++i) {
				html += "<option"+ (i==0 ? " selected":"") +" value='"+response[i]+"'>" + response[i] + "</option>";
			}

			element.innerHTML = html;
			
			getSchedules(account);
		}
	};

	request.open("get", "/IP-KorisnickaAplikacija/fligths-locations?account="+account+"&action=getEndCities&country=" + country, true);
	request.send();
}

function getSchedules(account) {
	var select = document.getElementById("startCountry");
	var startCountry = select.options[select.selectedIndex].value;
	select = document.getElementById("startCity");
	var startCity = select.options[select.selectedIndex].value;
	select = document.getElementById("endCountry");
	var endCountry = select.options[select.selectedIndex].value;
	select = document.getElementById("endCity");
	var endCity = select.options[select.selectedIndex].value;

	console.log(startCity);
	console.log(startCountry);
	console.log(endCity);
	console.log(endCountry);

	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status == 200)) {
			var response = JSON.parse(request.responseText);
			console.log(response);
			let element = document.getElementById("schedule");
			
			let html = "";
			
			for(let i=0; i<response.length; ++i)
			html+="<option"+ (i==0 ? " selected":"") +" value='"+JSON.stringify(response[i])+"'>" + response[i].startTime + ", " + response[i].day + "</option>";
			
			element.innerHTML = html;
			
		} else if((request.readyState == 4) && (request.status == 400)){
			let element = document.getElementById("schedule");
			while(element.firstChild)
				element.removeChild(element.firstChild);
		}
	}

	request.open("get", "/IP-KorisnickaAplikacija/fligths-locations?account="+account+"&action=schedules&startCountry=" + startCountry + "&startCity=" + startCity + "&endCountry=" + endCountry + "&endCity=" + endCity);
	request.send();
}
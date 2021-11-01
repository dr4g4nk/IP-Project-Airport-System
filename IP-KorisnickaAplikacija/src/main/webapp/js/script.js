/**
 * 
 */
var interval = 0;
function get() {	
	getFligths("today", "arrivals5");
	getFligths("today", "departures5");
	interval = setInterval(function() {
		console.log("from get interval " + interval);
		getFligths("today", "arrivals5");
		getFligths("today", "departures5");
	}, 60000);
	console.log("Set interval " + interval)
}

function clear() {
	console.log("clear interval " + interval);
	clearInterval(interval);
}

function getCountries() {
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status = 200)) {
			console.log(request.responseText);
			let result = JSON.parse(request.responseText);
console.log(result);
			let element = document.getElementById("country");

			let html = "";
			for (let i = 0; i < result.length; i++) {
				html += '<option value="' + result[i].name + '" ' + (i == 0 ? 'selected' : '') + '>' + result[i].name + '</option>'
			}
			element.innerHTML = html;
		}
	};

	request.open("GET", "http://api.countrylayer.com/v2/region/europe?access_key=a95e2b95a0629d7fdd0ab2acdbee4339");
	request.send(null);
}

function getFligths(day, action) {
	var request = new XMLHttpRequest();

	request.onreadystatechange = function() {
		if ((request.readyState == 4) && (request.status = 200)) {
			let result = JSON.parse(request.responseText);
			let element = document.getElementById(action == "arrivals" || action == "arrivals5" ? "arrivals" : "departures");

			let html = "";
			for (let i = 0; i < result.length; i++) {
				html += "<tr><td>" + result[i].fligth.route.start.city + "," + result[i].fligth.route.start.country + "</td>" +
					"<td>" + result[i].fligth.route.end.city + "," + result[i].fligth.route.end.country + "</td>" +
					"<td>" + result[i].startTime + "</td>" +
					"<td>" + result[i].endTime + "</td>" +
					"<td>" + result[i].status + "</td>" +
					"<td>" + result[i].fligth.type + "</td></tr>";
			}
			element.innerHTML = html;
		}
	};

	request.open("GET", "/IP-KorisnickaAplikacija/schedule?action=" + action + "&day=" + day, true);
	request.send(null);
	
}

function initMap() {
	const airport = { lat: 44.93362871517553, lng: 17.303789625877275 };
	const map = new google.maps.Map(document.getElementById("map"), {
		zoom: 13,
		center: airport,
	});
	const marker = new google.maps.Marker({
		position: airport,
		map: map,
	});
}

function getYesterdayArrivalsFligths() {
	clear();
	document.getElementById("day").innerText = "juce";
	document.getElementById("yesterday").hidden = true;
	document.getElementById("tomorrow").innerHTML = "Danas&gt;"
	document.getElementById("tomorrow").onclick = getTodayArrivalsFligths;
	getFligths("yesterday", "arrivals");
	interval = setInterval(function() {
		console.log("interval yesterday" + interval);
		getFligths("yesterday", "arrivals");
	}, 60000);
	console.log("interval yesterday" + interval);
}
function getTodayArrivalsFligths() {
	clear();
	document.getElementById("day").innerText = "danas";
	document.getElementById("tomorrow").hidden = false;
	document.getElementById("yesterday").hidden = false;
	document.getElementById("tomorrow").innerHTML = "Sutra&gt;";
	document.getElementById("yesterday").innerHTML = "&lt;Juce";
	document.getElementById("tomorrow").onclick = getTomorrowArrivalsFligths;
	document.getElementById("yesterday").onclick = getYesterdayArrivalsFligths;

	getFligths("today", "arrivals");
	interval = setInterval(function() {
		getFligths("today", "arrivals");
		console.log("interval today" + interval);
	}, 60000);
	console.log("interval today" + interval);
}
function getTomorrowArrivalsFligths() {
	clear();
	document.getElementById("day").innerText = "sutra";
	document.getElementById("tomorrow").hidden = true;
	document.getElementById("yesterday").innerHTML = "&lt;Danas";
	document.getElementById("yesterday").onclick = getTodayArrivalsFligths;
	getFligths("tomorrow", "arrivals");
	interval = setInterval(function() {
		console.log("interval tomorrow" + interval);
		getFligths("tomorrow", "arrivals");
	}, 60000);
	console.log("interval tomorrow" + interval);
}

function getYesterdayDeparturesFligths() {
	clear();
	document.getElementById("day").innerText = "juce";
	document.getElementById("yesterday").hidden = true;
	document.getElementById("tomorrow").innerHTML = "Danas&gt;";
	document.getElementById("tomorrow").onclick = getTodayDeparturesFligths;
	getFligths("yesterday", "departures");
	interval = setInterval(function() {
		console.log("interval yesterday" + interval);
		getFligths("yesterday", "departures");
	}, 60000);
	console.log("interval yesterday" + interval);
}
function getTodayDeparturesFligths() {
	clear();
	document.getElementById("day").innerText = "danas";
	document.getElementById("tomorrow").hidden = false;
	document.getElementById("yesterday").hidden = false;
	document.getElementById("tomorrow").innerHTML = "Sutra&gt;";
	document.getElementById("yesterday").innerHTML = "&lt;Juce";
	document.getElementById("tomorrow").onclick = getTomorrowDeparturesFligths;
	document.getElementById("yesterday").onclick = getYesterdayDeparturesFligths;

	getFligths("today", "departures");
	interval = setInterval(function() {
		getFligths("today", "departures");
		console.log("interval today" + interval);
	}, 60000);
	console.log("interval today" + interval);
}
function getTomorrowDeparturesFligths() {
	clear();
	document.getElementById("day").innerText = "sutra";
	document.getElementById("tomorrow").hidden = true;
	document.getElementById("yesterday").innerHTML = "&lt;Danas"
	document.getElementById("yesterday").onclick = getTodayDeparturesFligths;
	getFligths("tomorrow", "departures");
	interval = setInterval(function() {
		console.log("interval tomorrow" + interval);
		getFligths("tomorrow", "departures");
	}, 60000);
	console.log("interval tomorrow" + interval);
}

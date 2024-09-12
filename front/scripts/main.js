import validator from "./validator.js";

"use strict";

const mainBtn = document.querySelector('button');
mainBtn.addEventListener('click', function(e) {
	e.preventDefault();
	console.log("button pressed!");
	const xVal = document.querySelector('#xInp').value;
	const yVal = document.querySelector('input[name="y"]:checked').value;
	const rVal = document.querySelector('input[name="r"]:checked').value;
	let val = new validator();
	val.validate(xVal, yVal, rVal);
	if (val.getRespCode() == 0) {
		fetch('http://localhost:8080/fcgi-bin/server.jar', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify({
				x: xVal,
				y: yVal,
				r: rVal
			})
		}).then(response => response.json()).then(data => {
			console.log("SUCCESS! ", data);
		}).catch((error) => {
			console.error("ERROR! ", error);
		});
	}
});

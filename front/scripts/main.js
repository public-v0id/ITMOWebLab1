import validator from "./validator.js";

"use strict";

const saveData = (data) => {
	window.localStorage.setItem('Data', JSON.stringify(data));
};

const loadData = () => {
	const savedData = window.localStorage.getItem('Data');
	return savedData ? JSON.parse(savedData) : [];
};

const mainBtn = document.querySelector('button');
let savedData = loadData();
let savedLen = savedData.length;
for (let i = 0; i < savedLen; ++i) {
	const tbody = document.querySelector('#prevData');
	const newRow = document.createElement('tr');
	const tx = document.createElement('td');
	const ty = document.createElement('td');
	const tr = document.createElement('td');
	const tres = document.createElement('td');
	const textime = document.createElement('td');
	const tservtime = document.createElement('td');
	console.log(savedData[i]);
	tx.textContent = savedData[i].x;
	ty.textContent = savedData[i].y;
	tr.textContent = savedData[i].r;
	tres.textContent = savedData[i].res;
	textime.textContent = savedData[i].exTime;
	let localDate = new Date(savedData[i].servTime);
	tservtime.textContent = localDate.toLocaleTimeString();
	newRow.appendChild(tx);
	newRow.appendChild(ty);
	newRow.appendChild(tr);
	newRow.appendChild(tres);
	newRow.appendChild(textime);
	newRow.appendChild(tservtime);
	tbody.appendChild(newRow);
}
mainBtn.addEventListener('click', function(e) {
	e.preventDefault();
	console.log("button pressed!");
	const xVal = document.querySelector('#xInp').value;
	const yVal = document.querySelector('input[name="y"]:checked').value;
	const rVal = document.querySelector('input[name="r"]:checked').value;
	let val = new validator();
	val.validate(xVal, yVal, rVal);
	if (val.getRespCode() == 0) {
		const url = new URL('./fcgi-bin/lab1.jar', window.location.href);
		fetch(url.href, {
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
			const tbody = document.querySelector('#prevData');
			const newRow = document.createElement('tr');
			const tx = document.createElement('td');
			const ty = document.createElement('td');
			const tr = document.createElement('td');
			const tres = document.createElement('td');
			const textime = document.createElement('td');
			const tservtime = document.createElement('td');
			const xS = xVal.substring(15);
			tx.textContent = xS;
			ty.textContent = yVal;
			tr.textContent = rVal;
			tres.textContent = data.res;
			textime.textContent = data.exTime;
			let localDate = new Date(data.servTime);
			tservtime.textContent = localDate.toLocaleTimeString();
			newRow.appendChild(tx);
			newRow.appendChild(ty);
			newRow.appendChild(tr);
			newRow.appendChild(tres);
			newRow.appendChild(textime);
			newRow.appendChild(tservtime);
			tbody.appendChild(newRow);
			savedData.push({ x: xVal, y: yVal, r: rVal, res: data.res, exTime: data.exTime, servTime: data.servTime});
			saveData(savedData);
		}).catch((error) => {
			console.error("ERROR! ", error);
		});
	}
});

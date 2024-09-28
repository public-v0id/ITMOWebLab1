export default class validator {
	constructor(respCode, msg) {
		this.respCode = respCode
		this.msg = msg
	}
	validate(xVal, yVal, rVal) {
		let yFlVal = parseFloat(yVal);
		let rFlVal = parseFloat(rVal);
		if ((isNaN(xVal)) || (xVal.trim() == "") || parseFloat(xVal) < -3 || parseFloat(xVal) > 5) {
			alert("Ошибка! Введено неправильное значение X!");
			this.respCode = 1;
		}
		else if (![-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2].includes(yFlVal)) {
			alert("Ошибка! Введено неправильное значение Y!");
			this.respCode = 1;
		}
		else if (![1, 1.5, 2, 2.5, 3].includes(rFlVal)) {
			alert("Ошибка! Введено неправильное значение R!");
			this.respCode = 1;
		}
		else {
			this.respCode = 0;
		}
	}

	getRespCode() {
		return this.respCode;
	}

	getMessage() {
		return this.msg;
	}
}

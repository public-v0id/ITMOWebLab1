document.querySelector("button").onclick = function () {
  if (isNaN(document.querySelector("#textInp").value)) {
  	alert("Ошибка! Введены неверные данные!");
  };
};

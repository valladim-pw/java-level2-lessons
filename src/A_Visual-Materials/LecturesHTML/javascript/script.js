
	hljs.highlightAll();

	let header = document.getElementById("header");
	let btns = header.getElementsByClassName("btn");
	let left100 = btns[0];
	let leftZ = btns[1];
	let rightZ = btns[2];
	let right100 = btns[3];
	let zoom50 = btns[4];
	let zoomStep = 10;
	let elements = document.getElementsByClassName("column");
	let bool = false;	

	for (let i = 0; i < btns.length; i++) {
		btns[i].addEventListener("click", addActive);
	}

	function addActive() {
		let current = document.getElementsByClassName("active");
		if(bool == false) {
			current[0].className = current[0].className.replace(" active", "");
			this.className += " active";
		} else {
			this.className = this.className.replace(" active", "");
			bool = false;
		}
	}

	for (let i = 0; i < btns.length; i++) {
		btns[i].addEventListener("click", changeCursor);
	}

	function setButsAttr(text) {
		for (let i = 0; i < btns.length; i++) {
			if(btns[i].innerText.indexOf(text) != -1)
				btns[i].setAttribute("lang", "ru");
		}
	}

	function removeAttr(attr) {
		for (let i = 0; i < btns.length; i++) {
			btns[i].removeAttribute(attr);
		}
	}

	function changeCursor() {
		removeAttr("lang");
		if(this.innerText.indexOf("100R") != -1) {
			setButsAttr("100R");
		} else if(this.innerText.indexOf("100L") != -1) {
			setButsAttr("100L");
		}
	}

	function zoomLeft() {
		let zoomUp = parseInt(elements[0].style.flex.substring(3, 7).trim());
		let zoomDown = parseInt(elements[1].style.flex.substring(3, 7).trim());

		if(zoomUp <= 100) {
			if(zoomUp == 100) {
				zoomUp += 0;
			} else {
				zoomUp += zoomStep;
				zoomDown -= zoomStep;
			}
			for(let i = 0; i < elements.length; i++) {
				if(i % 2 == 0)
					elements[i].style.flex = zoomUp +"%";
				else
					elements[i].style.flex = zoomDown + "%";
			}
			if(zoomUp == 100) {
				bool = true;
				if(rightZ.className != "btn active")
					rightZ.className += " active";
				zoomUp += 0;
			}
		}
		leftZ.innerHTML = "<i class=\"material-icons sign\">zoom_in</i> " + zoomUp + "L";
		rightZ.innerHTML = zoomDown + "R <i class=\"material-icons sign\">zoom_in</i>";
	}

	function zoomRight() {
		let zoomUp = parseInt(elements[0].style.flex.substring(3, 7).trim());
		let zoomDown = parseInt(elements[1].style.flex.substring(3, 7).trim());

		if(zoomDown <= 100) {
			if(zoomDown == 100) {
				zoomDown += 0;
			} else {
				zoomDown += zoomStep;
				zoomUp -= zoomStep;
			}
			for(let i = 0; i < elements.length; i++) {
				if(i % 2 == 0)
					elements[i].style.flex = zoomUp +"%";
				else
					elements[i].style.flex = zoomDown + "%";
			}
			if(zoomDown == 100) {
				bool = true;
				if(leftZ.className != "btn active")
					leftZ.className += " active";
				zoomDown += 0;
			}
		}
		leftZ.innerHTML = "<i class=\"material-icons sign\">zoom_in</i> " + zoomUp + "L";
		rightZ.innerHTML = zoomDown + "R <i class=\"material-icons sign\">zoom_in</i>";
	}

	function removeActive(elem) {
		for (let i = 0; i < btns.length; i++) {
			btns[i].className = btns[i].className.replace(" active", "");
		}
		bool = true;
		if(elem.className != "btn active")
			elem.className += " active";
	}

	function oneLeft() {
		for(let i = 0; i < elements.length; i++) {
			if(i % 2 == 0)
				elements[i].style.flex = "100%";
			else
				elements[i].style.flex = "0%";
		}

		let zoomUp = parseInt(elements[0].style.flex.substring(3, 7).trim());
		let zoomDown = parseInt(elements[1].style.flex.substring(3, 7).trim());
		leftZ.innerHTML = "<i class=\"material-icons sign\">zoom_in</i> " + zoomUp + "L";
		rightZ.innerHTML = zoomDown + "R <i class=\"material-icons sign\">zoom_in</i>";
		removeActive(rightZ);
	}

	function oneRight() {
		for(let i = 0; i < elements.length; i++) {
			if(i % 2 == 0)
				elements[i].style.flex = "0%";
			else
				elements[i].style.flex = "100%";
		}
		let zoomUp = parseInt(elements[0].style.flex.substring(3, 7).trim());
		let zoomDown = parseInt(elements[1].style.flex.substring(3, 7).trim());
		leftZ.innerHTML = "<i class=\"material-icons sign\">zoom_in</i> " + zoomUp + "L";
		rightZ.innerHTML = zoomDown + "R <i class=\"material-icons sign\">zoom_in</i>";
		removeActive(leftZ);
	}

	function two() {
		for (let i = 0; i < elements.length; i++) {
			elements[i].style.flex = "50%";
		}
		let zoomUp = parseInt(elements[0].style.flex.substring(3, 7).trim());
		let zoomDown = parseInt(elements[1].style.flex.substring(3, 7).trim());
		leftZ.innerHTML = "<i class=\"material-icons sign\">zoom_in</i> " + zoomUp + "L";
		rightZ.innerHTML = zoomDown + "R <i class=\"material-icons sign\">zoom_in</i>";
	}

	function insertLabel() {
		let len = elements.length;
		let html = "<div class=\"column\"><img src=\"images/empty.png\"></div>";
		if(len % 2 != 0) {
			elements[len - 1].insertAdjacentHTML("afterend", html);
		}
		for(let i = 0; i < elements.length;i++){
			let label = "<span class=\"badge bg-primary\">Рис."+ (i + 1) + "&nbsp;<i class=\"material-icons\">&#xe5db;</i></span>";
			if(elements[i].firstElementChild.tagName != "SPAN")
				elements[i].insertAdjacentHTML("afterbegin", label);
			let col_id = "<b class=\"column-id\" id=\"" + (i + 1) + "\"></b>";	
			elements[i].insertAdjacentHTML("afterbegin", col_id);	
		}
	}

$(document).ready(function() {
	let butWrap = $(".but-wrap");

	$(window).scroll(function(){
		if($(document).scrollTop() > 100){
			butWrap.addClass("fix");
		} else {
			butWrap.removeClass("fix");
		}
	});	

  $("a.github").click(function(event){
    event.preventDefault();
  });
});
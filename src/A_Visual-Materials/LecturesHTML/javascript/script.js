	
	/*
	Zoom buttons on every page opening in frame
	
	<div class="head-buttons" id="header">
		<p>Кнопками можно регулировать размер колонок L(левая) и R(правая) от 100% до 0% с шагом 10% (по умолчанию 50%/50%).</p>
		<div class="but-wrap">
			<div class="back-fix"></div>
		  <div class="but100-wrap left">
				<button class="btn" onclick="oneLeft();">100L</button>
			</div>
		  <div class="zoomBut">
				<button class="btn" onclick="zoomLeft();" >50L</button>
				<button class="btn" onclick="zoomRight();" >50R</button>
			</div>
			<div class="but100-wrap right">
				<button class="btn" onclick="oneRight();">100R</button>
			</div>
			<div class="but50-wrap">
				<button class="btn active" onclick="bothLeftRight();">50L&nbsp;&nbsp;&nbsp;&nbsp;50R<div class="divider"></div></button>
			</div>
		</div>
	</div>
	
	*/

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
		
		setCode(false);
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
		
		setCode(false);
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
		
		setCode(false);
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
		
		setCode(false);
	}
	
	/*
Every page html file opening in frame

<body onload="setCode(true); insertLabel(); bothLeftRight();">
*/	

	function bothLeftRight() {		
		
		for (let i = 0; i < elements.length; i++) {
			elements[i].style.flex = "50%";
		}
		
		let zoomUp = parseInt(elements[0].style.flex.substring(3, 7).trim());
		let zoomDown = parseInt(elements[1].style.flex.substring(3, 7).trim());
		leftZ.innerHTML = "<i class=\"material-icons sign\">zoom_in</i> " + zoomUp + "L";
		rightZ.innerHTML = zoomDown + "R <i class=\"material-icons sign\">zoom_in</i>";
		
		setCode(false);
	}

	function insertLabel() {
		let len = elements.length;
		let html = "<div class=\"column\"><img src=\"images/empty.png\"></div>";
		
		if(len % 2 != 0 && window.innerWidth > 500) {
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

	
	
/* 
Вспомогательные функции для упрощения преобразований классов элементов средствами Javascript
*/
function hasClass(el, attr) {
  let x = el.classList.contains(attr);
  if (x)
		return true;
  return false;
}
function addClass(el, attr) {
  let list = el.classList;
  list.add(attr);
}
function removeClass(el, attr) {
  let list = el.classList;
  list.remove(attr);
}

/* 
Функции для для вставки в верхней части страницы совета по применению кнопок Run для запуска кода
*/

function setRunCodeTip() {
  let header = document.getElementById("header");
	let attr = document.body.getAttribute("onload");
	
	if (attr.includes("setCode"))
		header.insertAdjacentHTML("afterbegin", "<p>Кнопкой <img class=\"but-run\" src=\"images/run-button.png\"> на картинке можно скопировать код в буфер обмена и запустить его в браузере.</p>");
}


/* Функция для установки атрибута onclick в те теги <p>, внутри которых есть ссылка 
с классом github, для открытия ресурса по ссылке в отдельном окне

document.body.onload = function() {setOpenWinAttr(); insertLabel(); bothLeftRight(); setCode(true);};	(см. выше)
*/


function setOpenWinAttr() {
	let par = document.getElementsByTagName("P");
	
	for (let i = 0; i < par.length; i++) {
		let p = par[i];
		let script = p.querySelector("script");
		let a = p.querySelector(".github");
		
		if (a != null) {
			let href = a.getAttribute("href");
			
			if (window.parent.innerWidth > 1100)
				p.setAttribute("onclick", "openWin(\"" + href + "\")");			
		}
		if (script != null) 
			p.removeChild(script);
	}
	//setRunCodeTip();
}

/*
Функция для открытия ресурса по ссылке на странице в отдельном окне 
с определенными параметрами

<div class="column">
		<span class="badge bg-primary">Рис.3&nbsp;<i class="material-icons">&#xe5db;</i></span>
		<img src="images/images_08.1/08.1.3.png">	
		<p>			
		* <b>Дерево</b> представляет из себя <b>связанный ацикличный граф</b>.</br>
		* <b>Связанный</b> - значит каждый компонент дерева связан со всеми остальными компонентами.</br>
		* <b>Ацикличный</b> - значит в графе отсутствуют циклы. <b>Циклы</b> - это замкнутые контуры в графе, по которым движение в одном направлении может происходить бесконечно.</br>			
		</p>
		<p onclick='openWin("https://skillbox.ru/media/code/teoriya-grafov-derevya-planarnost-raznovidnosti-grafov/")'>				
			* <a class="github" href="https://skillbox.ru/media/code/teoriya-grafov-derevya-planarnost-raznovidnosti-grafov/">
			Теория графов: деревья, планарность, разновидности графов</a>
		</p>
	</div>
*/

let githubWin;

function openWin(resource) {
	let specs = "toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=700, height=700";
	
	if (githubWin) {
		
		githubWin.focus();
		githubWin.close();
		githubWin = window.open(resource, "githubWin", specs);
		
	} else
	  githubWin = window.open(resource, "githubWin", specs);
}

/*
	Функция для работы кликкаунтера в локальном хранилище браузера 

*/

let demo = document.getElementById("demo");

function clickCounter() {  
   
  if (typeof(Storage) !== "undefined") { 	 
    if (localStorage.clickcount) {
      localStorage.clickcount = Number(localStorage.clickcount)+1;
    } else {
      localStorage.clickcount = 1;
    } 
    //demo.insertAdjacentHTML("beforeend", localStorage.getItem("clickcount"));
  } else {
   demo.innerHTML = "Sorry, no Web storage support!";
  }
}

/*
	Функция для копирования кода из рамки с кодом, а также для запуска кода на ресурсе w3schools.com

	<div class="column" >
		<b class="column-id" id="19"></b>
		<span class="badge bg-primary">Рис.19&nbsp;<i class="material-icons"></i></span>
		<img src="images/images_08.1/08.1.19.png" usemap="#map">
		<i class="material-icons but-gif but-copy" onclick="copyCode(event)" >content_copy</i>
		<span class="copy-msg msg-left">Скопировано в буфер обмена</span>
		<div id="code19" class="code-wrap">
			<pre>
			<code class="hljs language-java">
	*/


async function copyCode(event) {
	
	let e = event.target;
	let code_wrap = e.nextElementSibling.nextElementSibling;
	let copy_msg = e.nextElementSibling;
		
	let code_id = code_wrap.getAttribute("id");
	let id_elem = document.body.querySelector(".page-id");
	let page_id = id_elem.getAttribute("id");
	
	let dir = "./code/code_" + page_id + "/";	
	//let store_id = page_id + "*" + code_id;
	let file = dir + code_id + ".txt";		
	
	let x = await fetch(file);
	let copy_text = await x.text();
	arrClass = copy_text.match("public class");
	
	if (Array.isArray(arrClass) && arrClass.length !== 0 && !code_id.includes("copy"))						
		copy_text = copy_text.replaceAll("public class", "class");
	
	navigator.clipboard.writeText(copy_text);
	
	let count = localStorage.getItem("clickcount");
	//let store_val = localStorage.getItem(store_id);
	let store_no_all = localStorage.getItem("no-all");
	
	if (hasClass(e, "but-test")) {
		
		let msg_copy = copy_msg.children[0];
		let guide_choice = copy_msg.children[1];
		let msg_alert = copy_msg.children[2];
		let select = msg_alert.querySelector("select");
		//let a = e.parentElement.querySelector(".link-test");
		
		closeAllSettings();
		
		if (+count <= 1 || select.value == "no-choice") {
			removeClass(guide_choice, "msg-hide");
			
			if (!hasClass(msg_copy, "msg-hide"))
				addClass(msg_copy, "msg-hide");
			if (!hasClass(msg_alert, "msg-hide"))
				addClass(msg_alert, "msg-hide");
		
		} else {		
			removeClass(msg_copy, "msg-hide");
		
			if (select.value == "guide-yes")
				setTimeout(() => startWithGuide(msg_copy), 2000);					
			
			if (select.value == "guide-no" || select.value == "guide-no-all")
				setTimeout(() => startWithoutGuide(msg_copy), 2000);	
		}
	} else {
		
		removeClass(copy_msg, "msg-hide");
		setTimeout(function() {addClass(copy_msg, "msg-hide")}, 2000);
	}
				
}

let myWindow;

function openW3Schools() {
	
	let dir = "https://www.w3schools.com/java/tryjava.asp?filename=demo_helloworld";
	
	if (myWindow) {
		myWindow.close();
		myWindow = window.open(dir, "myWindow");
	} else
	  myWindow = window.open(dir, "myWindow");
}

function startWithGuide(msg) {	
	openGuide();
	addClass(msg, "msg-hide");
}

function startWithoutGuide(msg) {	
	//anchor.click();
	openW3Schools();
	addClass(msg, "msg-hide");
}

function openGuide() {
	
	let guide_elem = parent.document.getElementById("guide-wrap");
	let guide_elem_mob = parent.document.getElementById("guide-mob-wrap");
	
	if (window.parent.innerWidth > 500)
		addClass(guide_elem, "open-guide");
	else 
		addClass(guide_elem_mob, "open-guide");
}

/*
Функции для настроек с помощью значений селекта способа открытия кода для запуска (с инструкцией по запуску или без нее)
а также установки значений настроек в локальном хранилище
*/
//let demo = document.getElementById("demo");

function openSettings(event) {
	
	let e = event.target;
	let guide_choice = e.nextElementSibling.nextElementSibling.children[1];
	let msg_alert = e.nextElementSibling.nextElementSibling.children[2];
	let copy_msg = msg_alert.parentElement;
	let select = msg_alert.querySelector("select");
	let close = msg_alert.querySelector(".setting-close");
	let count = localStorage.getItem("clickcount");
	
	if (!hasClass(guide_choice, "msg-hide"))
		addClass(guide_choice, "msg-hide");
	
	closeAllSettings();
	
	removeClass(msg_alert, "msg-hide");
	if (select.value == "no-choice") {
		
		if (!hasClass(close, "msg-hide"))
			addClass(close, "msg-hide");
	} else
		removeClass(close, "msg-hide");
}

function closeSettings(event) {
	
	let e = event.target;	
	let msg_alert = e.parentElement.parentElement;
	
	if (!hasClass(msg_alert, "msg-hide"))
		addClass(msg_alert, "msg-hide");	
}

function closeAllSettings() {
	let msg_alerts = document.getElementsByClassName("msg-alert");
	
	for (let i = 0; i < msg_alerts.length; i++) {
		let msg_alert = msg_alerts[i];
		let guide_choice = msg_alert.previousElementSibling;
		
		if (!hasClass(msg_alert, "msg-hide"))
			addClass(msg_alert, "msg-hide");
		
		if (!hasClass(guide_choice, "msg-hide"))
			addClass(guide_choice, "msg-hide");
	}
}

function changeSelect(event) {		
	
	let e = event.target;
	let id_elem = document.body.querySelector(".page-id");
	let page_id = id_elem.getAttribute("id");	
  let msg_alert = e.parentElement;	
	
	if (typeof(Storage) !== "undefined") {
		
		switch(e.value) {			
			
			case "guide-yes":
				setSelectAllValue("guide-yes");
				break;
			case "guide-no":
				setSelectAllValue("guide-no");
				break;
			case "guide-no-all":
				setSelectAllValue("guide-no-all");
				break;	
			default:
				setSelectAllValue("clear-all");
		}		
		
		setTimeout(function() {addClass(msg_alert, "msg-hide")}, 1000);
	
	} else
		alert("Sorry, no Web storage support!");
	
}

function removeItem(storageKey) {
	
	if (localStorage.getItem(storageKey) != null)
		localStorage.removeItem(storageKey);	
}

function clearAndSetClickcount() {
	let clickcount = 0;
	
	if (localStorage.getItem("clickcount") != null) {
		clickcount = localStorage.getItem("clickcount");
		localStorage.clear();
		localStorage.setItem("clickcount", clickcount);
	}	
}

function setSelectAllValue(value) {
	let selects = document.getElementsByTagName("select");
	let id_elem = document.body.querySelector(".page-id");
	let page_id = id_elem.getAttribute("id");
	
	for (let i = 0; i < selects.length; i++) {
		let select = selects[i];
		
		switch(value) {
			
			case "clear-all":
				select.value = "no-choice";
				localStorage.clear();
				localStorage.setItem("clear", "clear-all");
				break;
			case "guide-yes":
				select.value = "guide-yes";
				removeItem("clear");
				removeItem("no-all");
				localStorage.setItem("yes-all", "guide-yes");				
				break;
			case "guide-no":
				select.value = "guide-no";
				removeItem("clear");
				removeItem("no-all");
				localStorage.setItem("yes-all", "guide-yes");
				localStorage.setItem(page_id, "guide-no");
				break;
			case "guide-no-all":
				select.value = "guide-no-all";
				clearAndSetClickcount();
				localStorage.setItem("no-all", "guide-no-all");
				break;			
		}		
	}
}

function loadWithStorageValues() {
	
	let code_tests = document.getElementsByClassName("code-test");
	let selects = document.getElementsByTagName("SELECT");
	let id_elem = document.body.querySelector(".page-id");
	let page_id = id_elem.getAttribute("id");
	
	for (let i = 0; i < code_tests.length && i < selects.length; i++) {
		let code_test = code_tests[i];
		let select = selects[i];
		let code_id = code_test.getAttribute("id");
		
		if (localStorage.getItem("clickcount") != null) {
			
			if (localStorage.getItem("clear") != null)
				select.value = "no-choice";
			else if (localStorage.getItem("no-all") != null)
				select.value = "guide-no-all";
			else if (localStorage.getItem("yes-all") != null) {
				
				if (localStorage.getItem(page_id) == "guide-no")
					select.value = "guide-no";
				else
					select.value = "guide-yes";
										
			}
		} else if (localStorage.getItem("clickcount") == null)
		   select.value = "no-choice";
		    
	}
}	

/*
Functions for start or pause gif-images

<div id="gif13" class="code-wrap" data-pos="030" >
	<i class="material-icons but-gif pause-gif" onclick="pauseGif(event)">pause_circle_outline</i>
	<i class="material-icons but-gif start-gif" onclick="startGif(event)">play_circle_outline</i>
	<img class="code-img" src="./code/code_08.1/gif13.gif">
</div>
*/

function startGif(event) {
	let e = event.target;
	let img = e.parentElement.querySelector("img");
	
	addClass(e, "invisible");
	
	let url = img.src;
	let new_url = url.replace("gif", "gifstart");
	img.src = new_url;	
}	

function pauseGif(event) {
	let e = event.target;
	let img = e.parentElement.querySelector("img");	
	
	let url = img.src;
	let new_url = url.replace("gifstart", "gif");
	img.src = new_url;
	
	removeClass(e.nextElementSibling, "invisible");
}

/*
Функция инициализирует плагины для подсветки и нумерации кода вставляемого в рамку поверх картинки
на странице.
Также функция устанавливает размеры рамки с кодом или gif, размеры кнопки для запуска/паузы gif, кнопки для копирования кода (отталкиваясь от размеров картинки),
а также расположение рамки над картинкой в зависимости от параметров обертки code-wrap

document.body.onload = function() {setOpenWinAttr(); insertLabel(); bothLeftRight(); setCode(true);};	(см. выше)

<div class="column" >
		<b class="column-id" id="19"></b>
		<span class="badge bg-primary">Рис.19&nbsp;<i class="material-icons"></i></span>
		<img src="images/images_08.1/08.1.19.png" usemap="#map">
		<i class="material-icons but-gif but-copy" onclick="copyCode(event)" >content_copy</i>
		<span class="copy-msg msg-left">Скопировано в буфер обмена</span>
		<div id="code19" class="code-wrap">
			<pre>
			<code class="hljs language-java">
*/	


function setCode(init) {
	
	if (init) {
		hljs.highlightAll();  // init plugin for hightlight code on page
		hljs.initLineNumbersOnLoad(); // plugin to add numbers to plugin
	}	
	
	let cols = document.getElementsByClassName("column");	
	
	for(let i = 0; i < cols.length; i++) {
		let col = cols[i];
		let badge = col.querySelector(".badge");
		let img = col.querySelector("img");		
		let code_wraps = col.querySelectorAll(".code-wrap");
		let but_content = col.querySelectorAll(".material-icons.but-gif");
		
		const indent = 10;
		const indent_half = indent / 2;
		const margin = 8;
		const margin_half = margin / 2;
		const border = 5;
		const coeff_ratio = 5;
		const coeff_font = 30;
		const coeff_button = 8;
		const coeff_button2 = 10;
		const coeff_msg = 20;
		let badge_h = badge.offsetHeight;
		let img_h = img.clientHeight;
		let img_w = img.clientWidth;
		let but_msg;
		
		let indent_v = img_h / coeff_ratio;
		let font_size = img_h / coeff_font;
		
		let offset_top = badge_h + margin + border + indent_v;
		let offset_left = margin_half + border + indent_half;
		let width = img_w - indent;	
		
		if (but_content != null) { // buttons for start/pause gif and for copy code
			
			for (let x = 0; x < but_content.length; x++) {
				let but = but_content[x];		
				
				if (hasClass(but, "but-copy")) {
					
					but.style.fontSize = (img_h / coeff_button) + "px";					
					but.style.top = offset_top + "px";
					if (!hasClass(but, "right50"))
						but.style.right = (indent_half + margin) + "px";
					else
						but.style.right = ((indent_half + margin_half) + img_w / 2) + "px";
					
				} else if (hasClass(but, "but-setting")) {
					
					but.style.fontSize = (img_h / coeff_button2) + "px";					
					but.style.top = (offset_top + indent_v * 1.3) + "px";	
					if (!hasClass(but, "right50"))
						but.style.right = (indent_half + margin) + "px";
					else
						but.style.right = ((indent_half + margin_half) + img_w / 2) + "px";
				} else {
				   if (!hasClass(but, "setting-close"))
				        but.style.fontSize = (img_h / coeff_button) + "px";
				} 
				    
			}
		}
		
		if (code_wraps != null) {
			
			for (let j = 0; j < code_wraps.length; j++) {
				let code_wrap = code_wraps[j];
				
				if (hasClass(code_wrap.previousElementSibling, "copy-msg"))
					but_msg = code_wrap.previousSibling;
				
				let pre = code_wrap.querySelector("pre");
				let width_part = (width - indent_half) / code_wraps.length;
				
				if (!hasClass(code_wrap, "code-null")) {
					
					if (hasClass(code_wrap, "h-full")) {
						
						code_wrap.style.height = (img_h - margin) + "px";
						code_wrap.style.top = (badge_h + margin + border + indent_half) + "px";
					
					} else {
						
						code_wrap.style.height = (img_h - (indent_v + 2)) + "px";
						code_wrap.style.top = offset_top + "px";
					}
						
				} else {
					code_wrap.style.height = 0 + "px";
					code_wrap.style.top = offset_top + "px";
				}
					
				
				
				
				if (but_msg != null) {
					but_msg.style.top = ((badge_h + margin + border) + img_h / 1.6) + "px";
					but_msg.style.fontSize = img_h / coeff_msg + "px";
					
					if (hasClass(code_wrap, "code-test")) {
						but_msg.style.height = ((img_h / coeff_msg) * 5) + "px";
						but_msg.firstElementChild.style.height = ((img_h / coeff_msg) * 2.6) + "px";
						but_msg.lastElementChild.style.height = ((img_h / coeff_msg) * 5.5) + "px";
						but_msg.lastElementChild.style.top = ((img_h / coeff_msg) * 2) + "px";
					} else
						but_msg.style.height = ((img_h / coeff_msg) * 2.6) + "px";
						
				}				
				
				code_wrap.style.fontSize = (font_size + 1.5) + "px";
				
				if (pre != null)
					pre.style.fontSize = font_size + "px";
				
				if (code_wraps.length == 1) {
					
					if (code_wrap.hasAttribute("data-pos")) {
						
						let pos = code_wrap.getAttribute("data-pos");
						
						switch(pos) {							
							case "020":
								code_wrap.style.width = (width / 2) + "px";
								code_wrap.style.left = (offset_left + (width / 4)) + "px";
								if (but_msg != null) {
									but_msg.style.width = (width / 2) + "px";
									but_msg.style.left = (offset_left + (width / 4)) + "px";
								}								
								break;
							case "20":
								code_wrap.style.width = (width / 2) + "px";
								code_wrap.style.left = offset_left + "px";
								if (but_msg != null) {
									but_msg.style.width = (width / 2) + "px";
									but_msg.style.left = offset_left + "px";
								}								
								break;
							case "02":
								code_wrap.style.width = (width / 2) + "px";
								code_wrap.style.left = (offset_left + (width / 2)) + "px";
								if (but_msg != null) {
									but_msg.style.width = (width / 2) + "px";
									but_msg.style.left = (offset_left + (width / 2)) + "px";
								}								
								break;
							case "030":
								code_wrap.style.width = (width / (3 / 2)) + "px";
								code_wrap.style.left = (offset_left + (width / 3) / 2) + "px";
								if (but_msg != null) {
									but_msg.style.width = (width / (3 / 2)) + "px";
									but_msg.style.left = (offset_left + (width / 3) / 2) + "px";
								}								
								break;	
							case "30":
								code_wrap.style.width = (width / (3 / 2)) + "px";
								code_wrap.style.left = offset_left + "px";
								if (but_msg != null) {
									but_msg.style.width = (width / (3 / 2)) + "px";
									but_msg.style.left = offset_left + "px";
								}								
								break;
							case "03":
								code_wrap.style.width = (width / (3 / 2)) + "px";
								code_wrap.style.left = (offset_left + (width / 3)) + "px";
								if (but_msg != null) {
									but_msg.style.width = (width / (3 / 2)) + "px";
									but_msg.style.left = (offset_left + (width / 3)) + "px";
								}								
								break;	
							default:
								code_wrap.style.width = width + "px";
								code_wrap.style.left = offset_left + "px";
								if (but_msg != null) {
									but_msg.style.width = width + "px";
									but_msg.style.left = offset_left + "px";
								}								
						}					
						
					} else {
						code_wrap.style.width = width + "px";
						code_wrap.style.left = offset_left + "px";
						if (but_msg != null) {
							if (hasClass(code_wrap, "code-null")) {
								but_msg.style.width = img_w + "px";
								but_msg.style.left = (margin_half + border) + "px";
							} else {
								but_msg.style.width = width + "px";
								but_msg.style.left = offset_left + "px";
							}								
						}						
					}					
				
				} else {
					if (hasClass(code_wrap, "back-author")) {
						let img_but_author = code_wrap.querySelector(".eye");
						let but_author_h = img_but_author.offsetHeight;
						
						code_wrap.style.height = but_author_h + "px";
						code_wrap.style.top = (badge_h + margin + border + (img_h / 2)) + "px";
						code_wrap.style.width = (width / 3) + "px";
						code_wrap.style.left = (offset_left + ((width / 3) * 2)) + "px";
								break;	
					} else if (hasClass(code_wrap, "author")) {
						
						code_wrap.style.height = (img_h - indent) + "px";
						code_wrap.style.top = ((offset_top + 6) - indent_v) + "px";
						code_wrap.style.width = (width / 2) + "px";
						code_wrap.style.left = offset_left + "px";
					} else {
						
						code_wrap.style.width = width_part + "px";
						//if (but_msg != null)
							//but_msg.style.width = width_part + "px";
						
						if (j < 1 ) {							
							code_wrap.style.left = offset_left + "px";
							
							if (but_msg != null) {
								
								if (hasClass(code_wrap, "code-null"))
									but_msg.style.left = (offset_left - border) + "px";
								else
									but_msg.style.left = offset_left + "px";
							}								
						
						} else {							
							code_wrap.style.left = (offset_left + width_part + indent_half) + "px";
							
							if (but_msg != null) {
								
								if (hasClass(code_wrap, "code-null"))
									but_msg.style.right = (border + margin_half) + "px";
								else
									//but_msg.style.left = (offset_left + width_part + indent_half) + "px";
									but_msg.style.right = (border + margin) + "px";
							}
								
						}						
					}				
				}				
			}			
		}		
	}
}
	


$(document).ready(function() {
	
	let butWrap = document.querySelector(".but-wrap");
	let butZoomWrap = parent.document.querySelector(".buts-zoom-wrap");
	let butZoomClose = parent.document.getElementById("close-zoom");
	let p = document.getElementsByTagName("P");

	$(window).scroll(function(){
		
		if($(document).scrollTop() > 100){
			
			$(butWrap).addClass("fix");
			$(butZoomWrap).removeClass("invisible");
			
			if ($(butZoomWrap).attr("data-close") == "yes") {
				$(butWrap).addClass("top-up");
				$(butZoomClose).addClass("invisible");
			}					
			
			if(window.parent.innerWidth > 1200) {
				butWrap.style.left = (-100) + "px";
			}
					
		} else {
			
			$(butWrap).removeClass("fix");
			$(butZoomWrap).addClass("invisible");
			butWrap.style.left = 0 + "px";			
		}
	});
	
	$(window.parent).resize(function(){
		
		setCode(false);
		
		if(window.parent.innerWidth > 1200) {
			
			if($(butWrap).hasClass("fix"))
				butWrap.style.left = (-100) + "px";
			else
				butWrap.style.left = 0 + "px";
		} else 
			
			butWrap.style.left = 0 + "px";
		
		if(window.parent.innerWidth <= 1200 && window.parent.innerWidth > 500) {
			
			if($(butWrap).hasClass("fix")) {
				
				$(butZoomWrap).removeClass("invisible");
				
				if ($(butZoomWrap).attr("data-close") == "yes") {
					
					$(butWrap).addClass("top-up");
					$(butZoomClose).addClass("invisible");
				}
			}	else
				
				$(butZoomWrap).addClass("invisible");
		}
	});

  $("a.github").click(function(event){
		
		if(window.parent.innerWidth > 1100)
			event.preventDefault();		
  });
	
	$(".back-author").click( function(){
	
    $(".eye").toggleClass("blind-eye");
    $(".code-wrap.author").toggleClass("full-visible");    
		
  });
	
});









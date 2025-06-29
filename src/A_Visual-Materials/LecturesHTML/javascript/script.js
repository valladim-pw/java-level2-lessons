	
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
	setRunCodeTip();
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

function openWin(resource) {
	
	window.open(resource,"_blank","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=700, height=700");
	
}

/*
Функция для открытия ресурса (W3Schools) на котором можно в режиме онлайн запустить скопированный 
в рамке или из файла код, а также закрытия после определенного времени
*/

let msgWindow;
function openW3Schools(event) {
	
	let e = event.target;
	
	let a = e.parentElement.querySelector(".link-test");
	a.click();
	//window.open("https://www.w3schools.com/java/tryjava.asp?filename=demo_helloworld","_parent","toolbar=yes, location=yes, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, top=50");
	
	msgWindow = window.open("", "", "toolbar=no, menubar=no, left=100, width=550, height=200");
	msgWindow.document.write("<p style=\"font-family:'Source Sans Pro',sans-serif;color:black;\"><b>Как запустить скопированный код:</b><br></p>" +
	" <ul style=\"font-family:'Source Sans Pro',sans-serif;\"><li style=\"margin-bottom:5px;\">удалить код в белом окне</li>" +
	" <li style=\"margin-bottom:5px;\"> вставить в белое окно код из буфера обмена:<br>" +	
	" - на мобильных устройствах нажать кнопку " + 
	"<span style=\"display:inline-block;margin:0 5px;position:relative;width:22px;height:22px;\"><img src=\"images/clipboard.png\" style=\"width:100%;position:absolute;top:3px;\"></span><b>\"Буфер обмена\"</b>," +
	"<br> - на остальных устройствах <b>Ctrl + V</b></li>" +
	" <li style=\"margin-bottom:5px;\">нажать <span style=\"font-family:'Source Sans Pro',sans-serif;display:inline-block;background-color:#04AA6D;padding:3px 12px;color:white;border-radius:5px;margin:0;\">Run ❯</span></li>" +
	" <li style=\"margin-bottom:5px;\">при необходимости код можно редактировать</li></ul>");
	setTimeout(closeMsgWindow, 12000);
}

function closeMsgWindow() {
	msgWindow.close();
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
		const coeff_ratio = 5.72;
		const coeff_font = 31.5;
		const coeff_button = 10;
		const coeff_msg = 24;
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
				
				but.style.fontSize = (img_h / coeff_button) + "px";
				
				if (hasClass(but, "but-copy")) {
					but.style.top = offset_top + "px";
					
					if (!hasClass(but, "right50"))
						but.style.right = (indent_half + margin) + "px";
					else
						but.style.right = ((indent_half + margin_half) + img_w / 2) + "px";
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
				
				if (!hasClass(code_wrap, "code-null"))
					code_wrap.style.height = (img_h - (indent_v + margin_half)) + "px";
				else
					code_wrap.style.height = 0 + "px";
				
				code_wrap.style.top = offset_top + "px";
				
				if (but_msg != null) {
					but_msg.style.top = ((badge_h + margin + border) + img_h / 2) + "px";
					but_msg.style.fontSize = img_h / coeff_msg + "px";
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
						
						code_wrap.style.height = (img_h / 6) + "px";
						code_wrap.style.top = (img_h / 1.7) + "px";
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
						if (but_msg != null)
							but_msg.style.width = width_part + "px";
						
						if (j < 1 ) {							
							code_wrap.style.left = offset_left + "px";
							if (but_msg != null)
								but_msg.style.left = offset_left + "px";
						
						} else {
							
							code_wrap.style.left = (offset_left + width_part + indent_half) + "px";
							if (but_msg != null)
								but_msg.style.left = (offset_left + width_part + indent_half) + "px";
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










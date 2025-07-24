let anchors = document.getElementsByClassName("anchor");
let anchors_block = document.getElementsByClassName("anchors");
let anchs = document.getElementsByTagName("a");
let doc_elem = document.documentElement;

let menu = document.getElementById("menu");
let page = document.getElementById("block-page");
let icon = document.getElementById("icon");
let head = document.getElementById("head");
let frame = document.getElementById("frame");	
let buts_mobile = document.getElementById("buts-mobile");
let buts_desk = document.getElementById("buts-desk");
let empty_panel = document.getElementById("empty-panel");
let panel_fixed = document.getElementById("panel-fix");
let buts_back = document.getElementById("buts-mob-back");
let loader = document.getElementById("loader");
let start_loader = document.getElementById("start-loader");
let link_ids = document.getElementById("link-ids");

let menu_desk_button = document.getElementById("switch1");
let menu_add_button = document.getElementById("switch-mob-1");
let menu_button = document.getElementById("switch-mob-2");
let showhead_button = document.getElementById("switch-mob-3");
let hidehead_button = document.getElementById("switch-mob-4");
let fullscreen_button = document.getElementById("switch-mob-6");
let fullscreen_exit_button = document.getElementById("switch-mob-5");
let highlight_menu_button = document.getElementById("but-menu-desk");
let screen_full = false;
let head_open = true;
let inn_link = false;
let back_link = false;
let back_link_active;
let load_menu = false;
let repeat_link = false;

let buts_index = document.getElementById("page-indexes");
let but_back = document.querySelector(".but-back");
let but_counter = document.getElementById("but-counter");
let link_repeat_up = document.getElementById("link-repeat-up");
let link_repeat_down = document.getElementById("link-repeat-down");
let link_back = document.getElementById("link-back");
let page_this = document.getElementById("page-this");
let link_index = document.getElementById("link-index");
let buts_indexes = document.getElementById("indexes");
let loc_button = document.getElementById("loc-address");
let buts_control_wrap = document.querySelector(".buts-control-wrap");
let but_close = document.getElementById("close-panel");
let but_open = document.getElementById("open-panel");
let buts_zoom_wrap = document.querySelector(".buts-zoom-wrap");
let but_close_zoom = document.getElementById("close-zoom");
let but_open_zoom = document.getElementById("open-zoom");
let elem_tip = document.getElementsByClassName("tip");



function addCounter(elem) {  
	let add = elem.innerText;
	
	if (add == "")
		add = 1;
	else
		add = +add + 1;		
	
	elem.innerText = add;		
}

function subCounter(elem) {
  
	let sub = elem.innerText;
  sub = +sub - 1;
	
	elem.innerText = sub;
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
openZoomPanel()
closeZoomPanel()

(index.html)

<div class="buts-control-wrap buts-zoom">
	<button id="open-zoom" class="but-control" onclick="openZoomPanel()">&nbsp;&nbsp;&nbsp;&nbsp;<i class="material-icons dir-arrow to-down">play_arrow</i></button>
	<button id="close-zoom" class="but-control tip" onclick="closeZoomPanel()" tooltip="Убрать кнопки">&nbsp;&nbsp;&nbsp;&nbsp;<i class="material-icons dir-arrow to-up">play_arrow</i></button>
</div>
*/

function closeZoomPanel() {
	
	let buts_wrap = frame.contentWindow.document.querySelector(".but-wrap");
	
	addClass(but_close_zoom, "invisible");
	addClass(buts_wrap, "top-up");
	buts_zoom_wrap.setAttribute("data-close", "yes");	
}

function openZoomPanel() {
	
	let buts_wrap = frame.contentWindow.document.querySelector(".but-wrap");
	
	removeClass(but_close_zoom, "invisible");
	removeClass(buts_wrap, "top-up");
	buts_zoom_wrap.removeAttribute("data-close");	
}


/*
openIndexPanel()
closeIndexPanel()
setControlButsPos()
(index.html)

<body onload="setPanelImgPreload();setPageHeading(); controlTip(); highlightActiveLink(); addHighlightFunction();">
...
<div class="buts-control-wrap">
	<button id="open-panel" class="but-control" onclick="openIndexPanel()">&nbsp;&nbsp;&nbsp;&nbsp;<i class="material-icons dir-arrow to-left">play_arrow</i></button>
	<button id="close-panel" class="but-control tip" onclick="closeIndexPanel()" tooltip="Убрать панель">&nbsp;&nbsp;&nbsp;&nbsp;<i class="material-icons dir-arrow">play_arrow</i></button>
</div>
...
<iframe id="frame" onload="setControlButsPos(); loadFrame(); setTimeout(linkIndexClick, 50); setTimeout(clickHighlightMenuBut, 50);" ...>
*/

function closeIndexPanel() {
	
	addClass(but_close, "invisible");
	buts_index.style.right = (1 - 500) + "px";	
}

function openIndexPanel() {
	
	let h = buts_index.offsetHeight;
	
	removeClass(but_close, "invisible");
	buts_index.style.right = (h/2) + "px";		
}

function setControlButsPos() {	
	
	let h = buts_index.offsetHeight;
	
	if (hasClass(but_close, "invisible"))
		buts_index.style.right = (1 - 500) + "px";
	else
		buts_index.style.right = (h/2) + "px";		
	
	removeClass(buts_control_wrap, "wrap-opacity");
	
	buts_control_wrap.style.right = 0  + "px";		
	buts_control_wrap.style.height = h + "px";
	buts_control_wrap.style.width = h + "px";
	but_close.style.borderRadius = h + "px";
	but_open.style.borderRadius = h + "px";
	
	if (window.innerWidth < 1200 && window.innerWidth >= 500) {
		
		addClass(buts_index, "index-bottom");
		addClass(buts_control_wrap, "control-bottom");
		
		addClass(buts_zoom_wrap, "invisible");
		removeClass(buts_zoom_wrap, "wrap-opacity");
		
		buts_zoom_wrap.style.right = 0  + "px";
		buts_zoom_wrap.style.height = h + "px";
		buts_zoom_wrap.style.width = h + "px";
		but_close_zoom.style.borderRadius = h + "px";
		but_open_zoom.style.borderRadius = h + "px";
		
	} else {
		
		removeClass(buts_index, "index-bottom");
		removeClass(buts_control_wrap, "control-bottom");	
	}	
}

/* 
Функция для изменения адреса текущей темы для записи в панель, фиксированную поверх фрейма
(чтобы адрес был одинаковым с адресом на кнопке меню с темой, нажатой в данный момент) 
*/

function setPageLocation(elem, address) {
	
	let index1 = address.indexOf("-");
	let index2 = address.indexOf(".html");

	address = address.substring(index1, index2);
	if (address.charAt(1) == "0")
		index1 += 1;
	
	address = address.substring(index1, index2);
	let first = address.charAt(0);
	let first_plus = first * 1 + 1;
	address = address.replace(first, first_plus);
	
	if (window.innerWidth <= 500) {			
		if (address.length > 30) {
			elem.setAttribute("tooltip", address);			
			address = address.substring(0, 30);
			address += "...";			
		}	else
			elem.removeAttribute("tooltip");
	} else if (window.innerWidth <= 800 && window.innerWidth > 500) {	
		if (address.length > 33) {
			elem.setAttribute("tooltip", address);			
			address = address.substring(0, 33);
			address += "...";			
		}	else
			elem.removeAttribute("tooltip");		
	} else if (window.innerWidth <= 1300 && window.innerWidth > 800) {		
		if (address.length > 35) {
			elem.setAttribute("tooltip", address);			
			address = address.substring(0, 35);
			address += "...";			
		}	else
			elem.removeAttribute("tooltip");		
	}	else if (window.innerWidth > 1300) {
		if (address.length > 40) {
			elem.setAttribute("tooltip", address);			
			address = address.substring(0, 40);
			address += "...";			
		}	else
			elem.removeAttribute("tooltip");	
	}	
	
	address += "&nbsp;&nbsp;&nbsp;";
	elem.innerHTML = address;

	setControlButsPos();		
}


function setBackButton() {
	
	if (hasClass(link_back, "invisible")) {
		removeClass(link_back, "invisible");
		addClass(but_back, "blue-but-back");
		addClass(page_this, "invisible");		
	}
}

function setThemeButton() {
	
	if (hasClass(page_this, "invisible")) {
		removeClass(page_this, "invisible");
		removeClass(but_back, "blue-but-back");
		addClass(link_back, "invisible");		
	}	
}

function getPageIndex() {
  
	let head = frame.contentWindow.document.getElementsByTagName("HEAD")[0];
	let title = head.querySelector("title");
	let title_txt = title.innerText;
	
	title_txt = title_txt.substring(0, title_txt.indexOf(" "));
	
	let page_id = +title_txt + 1;
 
	return "" + page_id;
}

function addLinkToIdsString(elem, add_str) {
	
	let ids_txt = elem.innerText;
	let ids_arr = [];
	
	if (ids_txt.length != 0) {
		ids_arr = ids_txt.split(",");
		if (!ids_arr.includes(add_str)) {
			ids_arr.push(add_str);
			elem.innerText = ids_arr.join(",");
		}	
	} else
		elem.innerText = add_str;	
}

function checkIdAvail(elem, id) {
	
	let str = elem.innerText;
	let arr = str.split(",");
	
	if (arr.includes(id))
		return true;
	
	return false;
}

/* 
При переходе по внутренней ссылке в теме во фрейме на нужный раздел в другой теме, в
в панели, фиксированной поверх фрейма (с названием темы), в которой есть кнопка
с заглавием (Тема), эта кнопка меняется на кнопку Back со ссылкой на исходную тему.
*/

function changeBackLink(elem, back_ref) {	
		
  let repeat_id;	
	let id_elem = elem.parentElement.parentElement.parentElement.querySelector("B");
	let back_id = id_elem.getAttribute("id");	
	let elem_href = elem.getAttribute("href");
	
	if (back_ref.indexOf("#") != -1) {
		
		back_ref = back_ref.substring(0, back_ref.indexOf("#"));				
		back_ref += "#" + back_id;
		
	} else		
		back_ref += "#" + back_id;	
		
	link_back.setAttribute("href", back_ref);

	return back_ref;
}

function addRepeatLink(elem, add_href) {
	
	let txt = elem.innerText;	
	
	if (!txt.includes(add_href))
		txt += add_href;	
	
	elem.innerText = txt;
	
	return txt;
}


function getRepeatLinkUp(elem) {
	
	let txt = elem.innerText;		
	
	let get = txt.substring(txt.lastIndexOf("N"));
	txt = txt.substring(0, txt.lastIndexOf("N"));
	
	elem.innerText = txt;	
	
	return get;
}

function getRepeatLinkDown(elem) {
	
	let txt = elem.innerText;
	
	txt = txt.substring(0, txt.lastIndexOf("N"));
	let get = txt.substring(txt.lastIndexOf("N"));
	
	elem.innerText = txt;	
	
	return get;
}

function clearRepeat() {	
  
	if (repeat_link) {
		
		link_repeat_up.innerText = "";
		link_repeat_down.innerText = "";
		
		repeat_link = false;	
		
		setThemeButton();
	}		
}

function toggleHideTip() {	
  
	if (hasClass(link_back, "hide-tip") && hasClass(link_back, "tip-left")) {
		removeClass(link_back, "hide-tip");
		removeClass(link_back, "tip-left");
	}	
	let x = setTimeout(addHideTip, 1500);
	let y = setTimeout(addToLeftTip, 3000);	
}

function addHideTip() {	
  
	addClass(link_back, "hide-tip");		
}

function addToLeftTip() {	
  
	addClass(link_back, "tip-left");		
}



/*
(index.html)
linkIndexClick()
<iframe id="frame" onload="setControlButsPos(); loadFrame(); setTimeout(linkIndexClick, 50); setTimeout(clickHighlightMenuBut, 50);"...
*/

function linkIndexClick() {
	
  let up_href = "";
	let down_href = "";
	let back_href = "";
	let back_id;	
	let repeat_id = "";
	let href = "";
	let back_count = link_back.querySelector(".count");
	let anchor = null;
	
	if (inn_link) {		
		
		let idx1 = but_counter.getAttribute("data-inner-idx1");
		let idx2 = but_counter.getAttribute("data-inner-idx2");
		idx1--;
		idx2--;		
		
		changePageLinksTarget(idx1, idx2);					
		
		inn_link = false;
		back_link_active = true;
		
		setBackButton();		
		
		addCounter(back_count);		
		
		openIndexPanel();
		
	} else if (back_link) {	
	
		toggleHideTip();
		
		subCounter(back_count);
		
		back_href = link_back.getAttribute("href");
		link_index.setAttribute("href", back_href);
		
		back_id = back_href.substring(back_href.indexOf("#") + 1);				
		
		if (back_href.indexOf("#") != -1) {				
			
			up_href = getRepeatLinkUp(link_repeat_up);
			down_href = getRepeatLinkDown(link_repeat_down);
			
			if (down_href != "")				
				link_back.setAttribute("href", down_href);
			else			
				repeat_link = false;					
			
			anchor = frame.contentWindow.document.querySelector(".inner-anchor[href='" + up_href + "']");				
			anchor.setAttribute("data-back-id", back_id);
			
			if (anchor.hasAttribute("data-back-id"))
				addClass(anchor.parentElement, "focus");
			
			back_id--;		
			changePageLinksTarget(back_id, 0);
		}	
		
		back_link = false;
		back_link_active = false;
		
		if (!repeat_link)
			setThemeButton();		
	}	
}

/*
loadFrame()
(index.html)
<iframe id="frame" onload="setControlButsPos(); loadFrame(); setTimeout(linkIndexClick, 50); setTimeout(clickHighlightMenuBut, 50);"...
*/

function loadFrame() {
	
	let inner_anchors = frame.contentWindow.document.getElementsByClassName("inner-anchor");
	let but_wrap = frame.contentWindow.document.querySelector(".but-wrap");
	let head_anchors = document.getElementsByClassName("head-anchor");
	let panels = document.getElementsByClassName("panel");
	let html = frame.contentWindow.document.documentElement;	
	let cols = frame.contentWindow.document.getElementsByClassName("column");	
	
	for (let i = 0; i < head_anchors.length; i++) {		
		head_anchors[i].addEventListener("click", function() {			
			
			let back_href = this.getAttribute("href");
			
			setPageLocation(loc_button, back_href);
			
			let link_id = loc_button.innerText;
			link_id = link_id.substring(0, link_id.indexOf(" "));									
			
			if (!checkIdAvail(link_ids, link_id))				
				openLoader(false);				
			else
				openLoader(true);	
			
			for (let j = 0; j < panels.length; j++) {						
				panels[j].querySelector("P").removeAttribute("id");			
			}
			
			for (let y = 0; y < head_anchors.length; y++) {						
				head_anchors[y].removeAttribute("id");			
			}
			
			let this_panel = this.parentElement.parentElement.parentElement.parentElement.previousElementSibling.querySelector("P");
			this_panel.setAttribute("id", "panel-a");		
			
			this.setAttribute("id", "head-a");			
			
			link_back.setAttribute("href", back_href);
			
			if (back_link_active)
				setThemeButton();	
			
			clearRepeat();						
		});
	}
	
	for (let i = 0; i < inner_anchors.length; i++) {
		
		let inner_anchor = inner_anchors[i];
		let preload_ids = [];		
		
		if (inner_anchor.hasAttribute("data-preload")) {
			
			let index = inner_anchor.getAttribute("data-preload");
			
			if (!preload_ids.includes(index))
				preload_ids.push(index);
			
			setAddPanelImgPreload(preload_ids);// preload-img.js, в корне сайта
		}
		
		inner_anchor.addEventListener("click", function(event) {
      let e = event.currentTarget;		
			let index_href = e.getAttribute("href");
			let ref_index = link_index.getAttribute("href");
			let ref_back = link_back.getAttribute("href");
			let index_data = e.getAttribute("data-target-img");
			let idx1, idx2;			
			let repeat_back = "";			
			
			openLoader(true);
			toggleHideTip();
			
			if (index_data != null && index_data.indexOf(",") != -1) {
				idx1 = index_data.substring(0, index_data.indexOf(","));
				idx2 = index_data.substring(index_data.indexOf(",") + 1);
			} else
				idx1 = idx2 = index_data;			
			
			but_counter.setAttribute("data-inner-idx1", idx1);
			but_counter.setAttribute("data-inner-idx2", idx2);		
			
			if (hasClass(buts_indexes, "blue-but-back"))
				repeat_link = true;
				
			addRepeatLink(link_repeat_up, index_href);				
			
			if (repeat_link)				
				repeat_back = changeBackLink(e, ref_index);						
			else			
				repeat_back = changeBackLink(e, ref_back);			
			
			addRepeatLink(link_repeat_down, repeat_back);

			link_index.setAttribute("href", index_href);
			inn_link = true;
			
			setPageLocation(loc_button, index_href);			
		});		
	}
	
	link_back.addEventListener("click", function() {
		
		let back_href = this.getAttribute("href");
		back_link = true;		
		
		openLoader(true);		
		
		setPageLocation(loc_button, back_href);		
	});
	
	for (let j = 0; j < anchors.length; j++) {
		
		if (anchors[j].hasAttribute("id")) {			
			
			let idx1 = link_back.getAttribute("data-anchor-idx1");
			let idx2 = link_back.getAttribute("data-anchor-idx2");
			
			changePageLinksTarget(idx1, idx2);
			
			if (head_open)
				setPageHeading();
			else
				removePageHeading();			
		}
	}
	
	if (!hasClass(but_wrap, "fix")) {
		
		if (!hasClass(buts_zoom_wrap, "invisible")) {
			addClass(buts_zoom_wrap, "invisible");
			removeClass(but_close_zoom, "invisible");
		}				
	} else {
		
		if (hasClass(but_close_zoom, "invisible"))
			addClass(buts_wrap, "top-up");
	}
	
	let scroll_top = 0;
	let diff1, diff2;
	let data_scroll = false;
	let imags = frame.contentWindow.document.images;
	const load_sum = 5;	
	
	for (let i = 0; i < cols.length; i++) {
		
		let col = cols[i];
		let img = col.querySelector("IMG");
		
		if (col.hasAttribute("data-html-scroll")) {
			
			data_scroll = true;
			scroll_top = col.getAttribute("data-html-scroll");
			diff1 = +scroll_top - html.scrollTop;
			diff2 = +scroll_top - diff1;
			
			if (diff2 == html.scrollTop) {				
				
				closeLoader();
				setTimeout(removeLoaderOpacity, 1000);				
			}							
		} else {
			
			if (i <= load_sum && img.complete)				
				img.setAttribute("data-load", "loaded");		
			 						
		}		
	}
	
	let page_id = getPageIndex();	
	
	if (!data_scroll)	{
		if (scroll_top == 0 && !checkIdAvail(link_ids, page_id)) {
			
			addLinkToIdsString(link_ids, page_id);
			
			if (imags[load_sum].hasAttribute("data-load")) { // берем атрибут предпоследней картинки, учитывая, что последней может быть empty-img, которая загружается отдельно	
			
				closeLoader();
				setTimeout(removeLoaderOpacity, 1000);				
			}
		}
	} else
		data_scroll = false;	
	
	toggleMobileMenu();	
}


/*
highlightByHash(idx1, idx2, event)
All themes in index.html
Example in one of themes:
(theme05.html)
<a onclick="highlightByHash(2, 2, event)" class="anchor" href="N-05.1 Тестирование кода, JUnit.html#3" target="frame" >...
*/

function highlightByHash(idx1, idx2, event) {
	
	let inner_anchors = frame.contentWindow.document.getElementsByClassName("inner-anchor");
	let head_anchors = document.getElementsByClassName("head-anchor");
	let panels = document.getElementsByClassName("panel");
	let e = event.currentTarget;
	let back_href = "";	
	let this_anchor = e.parentElement.parentElement.parentElement.previousElementSibling.querySelector(".head-anchor");
	let this_panel_p = e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling.querySelector("P");
	let this_panel = e.parentElement.parentElement.parentElement.parentElement.parentElement.previousElementSibling;
	
	let this_siblings = e.parentElement.parentElement.parentElement.getElementsByClassName("anchor");
	let is_id = false;
	
	for (let s = 0; s < this_siblings.length; s++) {	
		let sibling = this_siblings[s];	
		
		if (sibling.hasAttribute("id") || sibling.hasAttribute("data-first")) {
			is_id = true;
			break;
		}	
	}
	
	if (!is_id && !this_anchor.hasAttribute("id")) {		
		openLoader(false);
	}	else
		is_id = false;
	
	for (let i = 0; i < anchs.length; i++) {
		
		if (anchs[i].getAttribute("id") == "a")
			anchs[i].removeAttribute("id");
		
		if (anchs[i].hasAttribute("data-first"))
			anchs[i].removeAttribute("data-first");
	}
	
	for (let i = 0; i < panels.length; i++) {					
		panels[i].querySelector("P").removeAttribute("id");			
	}
	
	for (let i = 0; i < head_anchors.length; i++) {					
		head_anchors[i].removeAttribute("id");			
	} 
	
	for (let i = 0; i < inner_anchors.length; i++) {					
		removeClass(inner_anchors[i].parentElement, "focus");			
	}
	
	this_anchor.setAttribute("id", "head-a");	
	
	this_panel_p.setAttribute("id", "panel-a");		
	
	if (e.hasAttribute("href")) {
		e.setAttribute("id", "a");
		back_href = e.getAttribute("href"); 
	}		
	
	if (e.parentElement.hasAttribute("href")) {
		e.parentElement.setAttribute("id", "a");
		back_href = e.parentElement.getAttribute("href");
	}
	
	if (back_link_active)
		setThemeButton();
	
	link_back.setAttribute("href", back_href);		
	setPageLocation(loc_button, back_href);
	
	let link_href = link_back.getAttribute("href");	
	
	link_back.setAttribute("data-anchor-idx1", idx1); 	
	link_back.setAttribute("data-anchor-idx2", idx2);
  
	changePageLinksTarget(idx1, idx2);	
	
	toggleMobileMenu();
	clearRepeat();
}

function columnHeight(elem) {
	
	let height = 0;
	let p_height = 0;
	let col = elem.parentElement;
	let par = col.getElementsByTagName("p");
	
	for (let i = 0; i < par.length; i++) {
		let p = par[i];
		let a = p.querySelector("a");
		
		if (a != null && a.hasAttribute("data-back-id")) {
			height = p_height;
			return elem.clientHeight + height;
		}		
		p_height += p.clientHeight;	
	}
	return 0;	
}

function changePageLinksTarget(id1, id2) {
	
	let html = frame.contentWindow.document.documentElement;
	let cols = frame.contentWindow.document.getElementsByClassName("column");	
	let height = 0;
	let cols_height = 0;
	let cols_height_max = 0;	
	
	for (let i = 0; i < cols.length; i++) {
		
		let img_curr = cols[i].querySelector("img");
		//img_curr.removeAttribute("style");
		let img_next;
		cols[i].removeAttribute("data-html-scroll");
		removeClass(cols[i], "img-select");
		
		if (i >= id1 && i <= id2 && id2 != 0)	{
			addClass(cols[i], "img-select");
			//img_curr.style.borderTop = "15px solid #b4b4fc";	
			//img_curr.style.borderLeft = "15px solid #7575e1";
		}
		
		if (window.innerWidth <= 500) {
			
			if (i == id1) {
				height = cols_height;
				
				if (id2 != 0) {
					
					html.scrollTop = height;
					
					cols[i].setAttribute("data-html-scroll", height);
					
				}	else {
					
					html.scrollTop = height + columnHeight(img_curr);
					cols[i].setAttribute("data-html-scroll", height + columnHeight(img_curr));
				}	
			}	
			
			cols_height += cols[i].clientHeight;
		} else {			
			
			img_next = cols[i + 1].querySelector("img");
			//img_next.removeAttribute("style");
			removeClass(cols[i + 1], "img-select");
			
			if (i + 1 >= id1 && i + 1 <= id2 && id2 != 0)	{
				addClass(cols[i + 1], "img-select");
				//img_next.style.borderTop = "15px solid #b4b4fc";	
				//img_next.style.borderLeft = "15px solid #7575e1";
			}
			
			if (i == id1 || i + 1 == id1) {
				
				height = cols_height_max;		
				
				if (id2 != 0) {
					
					html.scrollTop = height;
					cols[i].setAttribute("data-html-scroll", height);
				
				} else {
					
					if (i == id1) {
						html.scrollTop = height + columnHeight(img_curr);
						
						cols[i].setAttribute("data-html-scroll", height + columnHeight(img_curr));						
					}	
					
					if (i + 1 == id1) {
						
						html.scrollTop = height + columnHeight(img_next);
						
						cols[i + 1].setAttribute("data-html-scroll", height + columnHeight(img_next));							
					}														
				}				
			}
			
			cols_height_max += Math.max(cols[i].clientHeight, cols[i + 1].clientHeight);			
			i++;				
		}		
	}			
}

/*
removeId()
All themes in index.html
Example in one of themes:
(theme05.html)
...
<span class="anchor-wrap"><a onclick="removeId()" class="head-anchor" href="N-05.1 Тестирование кода, JUnit.html" target="frame" >
			Тестирование кода, JUnit</a></span>...
*/

function removeId() {	
	
	for (let i = 0; i < anchs.length; i++) {
		
		if (anchs[i].getAttribute("id") == "a")
			anchs[i].removeAttribute("id");	
	}	
	
	toggleMobileMenu();
}

function toggleMobileMenu() {
	
	if (hasClass(menu, "menu-full")) {
		removeClass(menu, "menu-full");
		removeClass(menu_button, "invisible");		
	}
}

/*
toggleArrow(event)
All themes in index.html
Example in one of themes:
(theme05.html)
1.
<div class="panel panel-default header" data-toggle="collapse" data-target="#panel6">
	<div class="panel-body">
		<p onclick="toggleArrow(event)">...
2.
<div class="collapse collapse-custom"  id="panel6">	
	<div class="anchors">		
		<p class="heading-wrap">
			<span onclick="toggleArrow(event)" class="anchors-button" data-toggle="collapse" data-target="#panel61">...		
*/

function toggleArrow(event) {
  
	let e = event.currentTarget;
	let arrows = e.getElementsByClassName("material-icons");
	
	for (let i = 0; i < arrows.length; i++) {
		
		let arr = arrows[i];
		
		if (hasClass(arr, "invisible"))
			removeClass(arr, "invisible");
		else
			addClass(arr, "invisible");
	}	
}

/* Function to open fullscreen mode */
/*
closeFullscreen(), openFullscreen()
(index.html)
<button id="switch-mob-5" class="switcher fullscreen"  title="" onclick="closeFullscreen();"><i class="material-icons">fullscreen_exit</i></button>
<button id="switch-mob-6" class="switcher fullscreen"  title="" onclick="openFullscreen();"><i class="material-icons">fullscreen</i></button>
*/

function openFullscreen() {	
	
	addClass(fullscreen_button, "invisible");
	screen_full = true;
  
	if (doc_elem.requestFullscreen)
		doc_elem.requestFullscreen();
	else if (doc_elem.webkitRequestFullscreen) /* Safari */
		doc_elem.webkitRequestFullscreen();
	else if (doc_elem.mozRequestFullscreen) /* Mozilla */
		doc_elem.mozRequestFullscreen();
	else if (doc_elem.msRequestFullscreen) /* IE11 */
		doc_elem.msRequestFullscreen();
	
	if (head_open)
		setPageHeading();
	else
		removePageHeading();
}
/* Close fullscreen */

function closeFullscreen() {	
	
	removeClass(fullscreen_button, "invisible");
	screen_full = false;
  
	if (document.exitFullscreen)
		document.exitFullscreen();
	else if (document.webkitExitFullscreen) /* Safari */
		document.webkitExitFullscreen();
	else if (document.mozExitFullscreen) /* Mozilla */
		document.mozExitFullscreen();
	else if (document.msExitFullscreen) /* IE11 */
		document.msExitFullscreen();
	
	if (head_open)
		setPageHeading();
	else
		removePageHeading();
}

/*
Function for set height of block-page depending on the height of the icon-image
*/
/*
setPageHeading()
(index.html)
<body onload="setPanelImgPreload();setPageHeading(); controlTip(); highlightActiveLink(); addHighlightFunction();">
*/

function setPageHeading() {
	
	let half_img = icon.offsetHeight / 2;
	let half_but = menu_desk_button.offsetHeight / 2;
	let buts_top = half_img - half_but;
	head_open = true;
	head.style.top = 0 + "px";
	page.style.top = 0 + "px";
	head.style.height = icon.offsetHeight + "px";	
	
	if (screen_full)		
		page.style.height = (window.outerHeight - icon.offsetHeight) + "px";
	else		
		page.style.height = (window.innerHeight - icon.offsetHeight) + "px";
	
	if (!hasClass(empty_panel, "panel-out"))
			addClass(empty_panel, "panel-out");	
	
	if (window.innerWidth < 500) {
		buts_mobile.style.top = 3 + "px";
		buts_back.style.height = menu_add_button.offsetHeight + "px";
		buts_back.style.display = "none";
	} else 	
		buts_mobile.style.top = (icon.offsetHeight / 5) + "px";
	
	buts_desk.style.top = buts_top + "px";	
	panel_fixed.style.height = menu_desk_button.offsetHeight + "px";	
}

/*Function to remove height of page heading*/

function removePageHeading() {	
	
	head_open = false;
	head.style.height = 0 + "px";
	head.style.top = 0 + "px";
	page.style.top = 0 + "px";
	buts_mobile.style.top = 1 + "px";
	
	if (screen_full)
		page.style.height = window.outerHeight + "px";		
	else
		page.style.height = window.innerHeight + "px";
		
	if (hasClass(empty_panel, "panel-out"))
		removeClass(empty_panel, "panel-out");
	
	if (window.innerWidth < 500) {
		buts_back.style.height = menu_add_button.offsetHeight + "px";
		buts_back.style.display = "block";
	}	else
		buts_back.style.display = "none";	
	
	buts_desk.style.top = 0 + "px";	
}

/* 
Function to control tooltip attributes behavior
*/
/*
controlTip()
(index.html)
<body onload="setPanelImgPreload();setPageHeading(); controlTip(); highlightActiveLink(); addHighlightFunction();">
*/

function controlTip(){
	
	for (let i = 0; i < elem_tip.length; i++) {
		
		elem_tip[i].addEventListener("mouseenter", function(event) {
			
			e = event.target;				
			if (e.hasAttribute("tooltip"))
				addClass(e, "hide-tip");								
		});
		
		elem_tip[i].addEventListener("mouseleave", function(event) {
			
			e = event.target;			
			removeClass(e, "hide-tip");			
		});
	}
}

/*
 - Функция установки размеров панели с заголовком и блока с контентом, рассчитываемых по высоте иконки в зафисимости от 
изменений размеров окна (т.е. при смене ориентации мобильного устройства)	 
*/

window.addEventListener("resize", function(){
	
	if (head_open)
		setPageHeading();
	else
		removePageHeading();
		
	setControlButsPos();
});


/* 
- Функции открытия меню на исходной ссылке из панели, фиксированной поверх фрейма (с названием темы)
*/

/*
clickHighlightMenuBut()
(index.html)
<iframe id="frame" onload="setControlButsPos(); loadFrame(); setTimeout(linkIndexClick, 50); setTimeout(clickHighlightMenuBut, 50);"...
*/

function clickHighlightMenuBut() {
	highlight_menu_button.click();
}

/*
addHighlightFunction()
(index.html)
<body onload="setPanelImgPreload();setPageHeading(); controlTip(); highlightActiveLink(); addHighlightFunction();">
*/

function addHighlightFunction() {
	let anchors = menu.getElementsByTagName("a");
	
	for (let i = 0; i < anchors.length; i++) {
		
		anchors[i].addEventListener("click", function() {		
				
			setTimeout(clickHighlightMenuBut, 100);				
		});
	}

}

/*
highlightActiveLink()
(index.html)
1.<body onload="setPanelImgPreload();setPageHeading(); controlTip(); highlightActiveLink(); addHighlightFunction();">>
2.<div id="buts-desk" class="buts-wrap">
		...
		<button id="but-menu-desk" style="visibility:hidden;" onclick="highlightActiveLink()" >button</button>
3.<div id="buts-mobile" class="buts-wrap mobile">				
		<button id="switch-mob-1" class="switcher"><i class="material-icons">chevron_left</i></button>
		<button id="switch-mob-2" class="switcher btn-menu tip" onclick="highlightActiveLink()" tooltip="Меню" ><i class="material-icons">menu</i></button>

*/

function highlightActiveLink() {
	
	let anchors = menu.getElementsByTagName("a");
	let panels = menu.getElementsByClassName("panel");
	let a_first = document.getElementById("first-panel-a");
	let anchors_but, badge, a_badge, collapse, cust_collapse, panel, panel_but, height;
	let panels_height = 0;	
	
	if (a_first != null) {
		a_first.click();
		a_first.setAttribute("id", "panel-a");
	}	
	
	for (let j = 0; j < panels.length; j++) {
		
		let p = panels[j];
		
		panel_but = p.querySelector("P");
		cust_collapse = p.nextElementSibling;
		
		if (!panel_but.hasAttribute("id") && hasClass(cust_collapse, "in")) {
			panel_but.click();
		}
		
		if (panel_but.hasAttribute("id"))	
			height = panels_height;
		
		panels_height += p.clientHeight + 6;	
	}		
	
	for (let i = 0; i < anchors.length; i++) {
		
		let a = anchors[i];	
		
		if (a.getAttribute("id") == "head-a")		
			menu.scrollTop = height;		
			
		if (hasClass(a, "head-anchor")) {
			
			anchors_but = a.parentElement.previousElementSibling;
			badge = anchors_but.querySelector(".b-second");
			collapse = a.parentElement.parentElement.nextElementSibling;
			cust_collapse = a.parentElement.parentElement.parentElement.parentElement;
			panel = cust_collapse.previousElementSibling;
			panel_but = panel.querySelector("P");			
			
			if (panel_but.hasAttribute("id")) {
				if (a.hasAttribute("id")) {
					
					a.style.textDecoration = "underline";
					a.style.color = "#131737";
					badge.style.backgroundColor = "#282f6c";
					
					if (!hasClass(collapse, "in"))
						anchors_but.click();
				} else {
					if (hasClass(collapse, "in"))
						anchors_but.click();
					
					a.setAttribute("style", "");
					badge.setAttribute("style", "");
				}						
			} else {
				a.setAttribute("style", "");
				badge.setAttribute("style", "");
				
				if (!a.hasAttribute("id") && hasClass(collapse, "in")) {
					anchors_but.click();					
				}				
			}			
		}	
		if (hasClass(a, "anchor")) {
			
			head_a = a.parentElement.parentElement.parentElement.previousElementSibling.querySelector(".head-anchor"); 
			a_badge = a.parentElement.previousElementSibling.querySelector(".b-second");
			a_nums = a.querySelector(".inums");			
			
			if (a.hasAttribute("id")) {
				
				if (head_a.hasAttribute("id")) {
					head_a.style.textDecoration = "none";
				}
				a.style.textDecoration = "underline";
				a.style.color = "#131737";
				a_badge.style.backgroundColor = "#282f6c";
				a_nums.style.backgroundColor = "#282f6c";
				
			} else {
			
				a.setAttribute("style", "");	
				a_badge.setAttribute("style", "");
				a_nums.setAttribute("style", "");
			}
		}
	}	
}

function openLoader(autoclose) {
	
	if (!hasClass(loader, "open-loader"))
		addClass(loader, "open-loader");
	
	addClass(buts_control_wrap, "blue-after");
	
	if (autoclose) {
		setTimeout(closeLoader, 2000);
		setTimeout(removeLoaderOpacity, 3000);
	}	
}

function closeLoader() {
	
	removeClass(loader, "open-loader");
	addClass(loader, "loader-opacity");
	removeClass(buts_control_wrap, "blue-after");
}

function removeLoaderOpacity() {	
	
	removeClass(loader, "loader-opacity");	
}

function openStartLoader() {	
	
	setTimeout(closeStartLoader, 2000);
	setTimeout(removeStartLoaderOpacity, 3000);	
}

function closeStartLoader() {
	
	removeClass(start_loader, "open-loader");
	addClass(start_loader, "loader-opacity");	
}

function removeStartLoaderOpacity() {	
	
	removeClass(start_loader, "loader-opacity");
}

/* 
  Функция закрытия окна с инструкцией для запуска кода 
*/

function closeGuide() {	
	let guide_elem = document.getElementById("guide-wrap");
	let guide_elem_mob = document.getElementById("guide-mob-wrap");	
	
	if (window.innerWidth > 500)
		removeClass(guide_elem, "open-guide");
	else 
		removeClass(guide_elem_mob, "open-guide");
}

/* 
  Функция для перехода на ресурс w3schools.com
*/

function openW3Schools(event) {
	closeGuide();
	let e = event.target;
	let w3s_link = e.parentElement.querySelector("A");
	
	w3s_link.click();
	//setTimeout(closeGuide, 0);
}


/* 
- Функции регулировки скрытия -появления меню и панели с заголовком в дектопном и мобильном вариантах 
*/
 
$(document).ready( function(){

// Регулировка кнопками скрытия-повления меню в дектопном варианте
	
	$("#switch2").click( function(){
    
		$(this).addClass("invisible");
    $(".menu").addClass("menu-hidden");
    $(".frame").addClass("frame-full");	
		
  });
  $("#switch1").click( function(){
	
    $("#switch2").removeClass("invisible");
    $(".menu").removeClass("menu-hidden");
    $(".frame").removeClass("frame-full");
		
  });
	
// Регулировка кнопками скрытия-повления панели с заголовком в дектопном варианте	
	
	$("#switch4").click( function(){
	
    $(this).addClass("invisible");
		$(".buts-wrap").addClass("shift-right");		
		
		removePageHeading();
		
  });
  $("#switch3").click( function(){
	
    $("#switch4").removeClass("invisible");
    $(".buts-wrap").removeClass("shift-right");		
		
		setPageHeading();
		
  });
	
// Регулировка кнопками скрытия-повления меню в мобильном варианте
	
	$("#switch-mob-2").click( function(){
	
    $(this).addClass("invisible");
    $(".menu").addClass("menu-full");
    
  });
	$("#switch-mob-1").click( function(){
	
    $("#switch-mob-2").removeClass("invisible");
    $(".menu").removeClass("menu-full");		
		   
  });
	
// Регулировка кнопками скрытия-повления панели с заголовком в мобильном варианте
	
	$("#switch-mob-4").click( function(){
	
    $(this).addClass("invisible");
		$(".buts-wrap").addClass("shift-right");		
    
		removePageHeading();
		
  });
  $("#switch-mob-3").click( function(){
	
    $("#switch-mob-4").removeClass("invisible");
    $(".buts-wrap").removeClass("shift-right");		
    
		setPageHeading();
		
  });		
});
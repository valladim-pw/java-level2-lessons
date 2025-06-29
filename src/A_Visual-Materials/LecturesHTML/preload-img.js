async function getImages(file) {	
	const preload = document.getElementById("preloaded-images");
	let x = await fetch(file);
	let y = await x.text();
	
	if (preload != null)
		preload.insertAdjacentHTML("beforeend", y);			
}

/*
setPanelImgPreload()
index.html
<body onload="setPanelImgPreload(); setPageHeading(); controlTip(); highlightActiveLink(); addHighlightFunction();">
*/

function setPanelImgPreload() {
	
	const files = ["pre-img0.html", "pre-img1.html", "pre-img2.html", "pre-img3.html", "pre-img4.html", "pre-img5.html", "pre-img6.html", "pre-img7.html"];

	let col_panels = document.getElementsByClassName("panel");
	const panels = Array.from(col_panels);
	panels.shift();

	//const demo = document.getElementById("demo");
		
	for (let i = 0; i < panels.length; i++) {	
		
		let panel = panels[i];		
		let p = panel.querySelector("P");
		
		p.addEventListener("click", function() {	
			
			for (let j = 0; j < files.length; j++) {
				
				if (i == j && !p.hasAttribute("data-img-loaded")) {
					
					getImages(files[j]);
					p.setAttribute("data-img-loaded", "yes");
						
					//demo.insertAdjacentHTML("beforeend", "<br>panel: " + j);
				}				
			}
		});
	}	
}

/*
setAddPanelImgPreload(arr)
javascript/script-index.js
in loadFrame()
*/ 

function setAddPanelImgPreload(arr) {
	//const demo = document.getElementById("demo");
	let col_panels = document.getElementsByClassName("panel");
	
	const files = ["pre-img0.html", "pre-img1.html", "pre-img2.html", "pre-img3.html", "pre-img4.html", "pre-img5.html", "pre-img6.html", "pre-img7.html"];
	const panels = Array.from(col_panels);
	panels.shift();	
		
	for (let i = 0; i < arr.length; i++) {	
		
		let index = +arr[i];
		let panel = panels[index];		
		let p = panel.querySelector("P");	
				
		if (!p.hasAttribute("data-img-loaded")) {
			
			getImages(files[index]);
			p.setAttribute("data-img-loaded", "yes");
			
			//demo.insertAdjacentHTML("beforeend", "<br>panel: " + index);							
		}	
	}	
} 

/*
const demo = document.getElementById("demo");
demo.insertAdjacentHTML("beforeend", "<br>panel: " + index);
document.getElementById("demo").insertAdjacentHTML("beforeend", "<br>panel: " + index);
*/










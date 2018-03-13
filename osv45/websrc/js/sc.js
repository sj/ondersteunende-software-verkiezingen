// setzeCursor() == sc

/*
 * set cursor and focus into first input field
 */
function sc(){
	scWithCount(0);
}

/*
 * set cursor and focus in any input field
 */
function scWithCount(countOfField) {
	// wait 250 ms before setting focus to prevent IE8 problem (OSV-941) 
	window.setTimeout("doSetCursorWithCount("+countOfField+")", 250);
}

function doSetCursorWithCount(countOfField) {
	if (document.forms.length > 0) {
		var field = document.forms[0];
		var selectedCountOfField = 0; 
		for (var i = 0; i < field.length; i++) {
			if (((field.elements[i].type == "text")
					|| (field.elements[i].type == "textarea")
					|| (field.elements[i].type == "password")
					|| (field.elements[i].type.toString().charAt(0) == "s")) 
					&& (field.elements[i].disabled == false)
					&& (field.elements[i].parentNode.parentNode.style.visibility != "hidden")
					&& (field.elements[i].parentNode.parentNode.style.visibility != "collapse")
					&& (field.elements[i].readOnly == false)) {
				if (selectedCountOfField == countOfField){
					document.forms[0].elements[i].focus();
					if (field.elements[i].type.toString().charAt(0) != "s") { // not on submit buttons
						document.forms[0].elements[i].select();
					}
					break;
				} else {
					selectedCountOfField++;
				}
			}
		}
	}
}

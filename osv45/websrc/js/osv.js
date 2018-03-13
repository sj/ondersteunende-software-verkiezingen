function doit(i, j, msec)
{
  var k = i;
  var l = j;
  if (k <= 50)
  {
    eval("document.p" + k + ".src = '"+contextPath+"/img/lila.gif'");
    k++;
    window.setTimeout("doit(" + k + "," + l + "," + msec + ")", msec);
  } else {
	 eval("document.p" + l + ".src = '"+contextPath+"/img/leer.gif'");
     if (k == 101) {
		k = -1;
		l = -1;
  	 }
     k++;
  	 l++;
  	 window.setTimeout("doit(" + k + "," + l + "," + msec +")", msec);
  } 
}

function transp() {
	var divTag  = document.getElementById("content");
	divTag.style.filter = "alpha(Opacity=60, FinishOpacity=0, Style=0, StartX=0, StartY=0, FinishX=1000, FinishY=1000)";
	
	var divTag  = document.getElementById("trans");
	divTag.className = "trans";
	
	var statusbalken  = document.getElementById("statusbalken");
	statusbalken.style.display = 'inline';
	doit(0,0,100);       	

}

function moveCursor(sourceId, destId) {
    if (document.selection && document.selection.empty) {
      document.selection.empty();
    } else {
    	 source = document.getElementById(sourceId);
    	 if (source != null) {
    		 var tmp = source.value;
    		 source.value = "";
    		 source.value = tmp;
    	 }
    }
    if (destId != null) {
        document.getElementById(destId).focus();
        if (document.getElementById(destId).type == "text") { // only on text input
        	  document.getElementById(destId).select();
        }
    }
}
  
function navigateEnter(sourceId, evt) {
	var keycode = 0;
	if (!evt) {
	   var evt = window.event;
	}
	if (evt) {
	   keycode = evt.keyCode;
	}
	if (keycode == 13) { // enter
		navigateNext(sourceId, false, true);
		return false;
    }
    return true;
}
	  
function navigateCursor(sourceId, evt) {
    return navigateCursorWithToggle(sourceId, evt, null, null);
}
  
function navigateCursorWithToggle(sourceId, evt, group, numberOfChildren) {
    var keycode = 0;
    if (!evt) {
    	var evt = window.event;
    }
    if (evt) {
    	keycode = evt.keyCode;
    }
    if (keycode == 40) { // cursor down
    	navigateNext(sourceId, false, false);
    	return false;
    } else if (keycode == 38) { // cursor up
		navigatePrevious(sourceId, false);
		return false;
	} else if (keycode == 39 && group != null && numberOfChildren != null) { // cursor right (expand)
		var imgCollapseExpand = document.getElementById(sourceId);
		if (imgCollapseExpand != null && imgCollapseExpand.src != null && imgCollapseExpand.src.match(/expand.gif/)) {
			toggledisplay(group, numberOfChildren);
		}
		return false;
	} else if (keycode == 37 && group != null && numberOfChildren != null) { // cursor left (collapse)
		var imgCollapseExpand = document.getElementById(sourceId);
		if (imgCollapseExpand != null && imgCollapseExpand.src != null && imgCollapseExpand.src.match(/collapse.gif/)) {
			toggledisplay(group, numberOfChildren);
		}
		return false;
	}
    return true;
}
  
function navigateNext(sourceId, onlyCaretLastPosition, isEnterKey) {
	var tmp = sourceId.substr(3).split("_");
	var group = parseInt(tmp[0]);
	var j = parseInt(tmp[1]) + 1;
	if (onlyCaretLastPosition && !isCaretAtLastPosition(sourceId)) {
		return true;
	}
	if (document.getElementById("id_" + group + "_" + j) == null) {
	 	group++;
	 	j = 0;
	 	if (isEnterKey && document.getElementById("id_" + group + "_1") != null) {
	 		j = 1;
	 	}
	}
	while (document.getElementById("id_" + group + "_" + j) != null 
			&& (document.getElementById("id_" + group + "_" + j).disabled
			|| document.getElementById("id_" + group + "_" + j).type == "hidden"
			|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.visibility == "collapse"
			|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.display == "none"
			|| document.getElementById("id_" + group + "_" + j).style.display == "none")
			|| document.getElementById("id_" + group + "_" + j) == null && group < 100) {
		++j;
	    if (document.getElementById("id_" + group + "_" + j) == null) {
	    	group++;
	    	j = 0;
		 	if (isEnterKey && document.getElementById("id_" + group + "_1") != null) {
		 		j = 1;
		 	}
	    }
	}
	if (document.getElementById("id_" + group + "_" + j) != null
		&& !(document.getElementById("id_" + group + "_" + j).disabled 
	 	|| document.getElementById("id_" + group + "_" + j).type == "hidden"
	 	|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.visibility == "collapse"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.display == "none"
	    || document.getElementById("id_" + group + "_" + j).style.display == "none")) {
	    moveCursor(sourceId, "id_" + group + "_" + j);
	}
	return false;
}

function navigatePrevious(sourceId, onlyCaretFirstPosition) {
	var tmp = sourceId.substr(3).split("_");
	var group = parseInt(tmp[0]);
	var j = parseInt(tmp[1]) - 1;
	if (onlyCaretFirstPosition && !isCaretAtFirstPosition(sourceId)) {
		return true;
	}
	if (j < 0) {
	 	group--;
	 	j = 81;
	}
	while (document.getElementById("id_" + group + "_" + j) != null 
		&& (document.getElementById("id_" + group + "_" + j).disabled
		|| document.getElementById("id_" + group + "_" + j).type == "hidden"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.visibility == "collapse"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.display == "none"
	   	|| document.getElementById("id_" + group + "_" + j).style.display == "none")
	   	|| document.getElementById("id_" + group + "_" + j) == null && j >= 0 && group >= 0) {
	    --j;
	    if (j < 0) {
	     	group--;
	     	j = 81;
	    }
	}
	if (document.getElementById("id_" + group + "_" + j) != null
		&& !(document.getElementById("id_" + group + "_" + j).disabled 
		|| document.getElementById("id_" + group + "_" + j).type == "hidden"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.visibility == "collapse"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.display == "none"
	   	|| document.getElementById("id_" + group + "_" + j).style.display == "none")) {
	     moveCursor(sourceId, "id_" + group + "_" + j);
	}
	return false;
}

function navigateCursorThroughTable(sourceId, evt, includeCaretPosition) {
	if (!isAutocompletionVisible) {
	    var keycode = 0;
	    if (!evt) {
	       var evt = window.event;
	    }
	    if (evt) {
	       keycode = evt.keyCode;
	    }
	    if (keycode == 40) { // cursor down
			navigateNextGroup(sourceId);
	    	return false;
		} else if (keycode == 38) { // cursor up
			navigatePreviousGroup(sourceId);
			return false;
		} else if (keycode == 39) { // cursor right
			return navigateNext(sourceId, includeCaretPosition, false);
		} else if (keycode == 37) { // cursor left
			return navigatePrevious(sourceId, includeCaretPosition);
		}
    }
    return true;
}
  
function navigateNextGroup(sourceId) {
	var tmp = sourceId.substr(3).split("_");
	var group = parseInt(tmp[0]) + 1;
	var j = parseInt(tmp[1]);
	if (document.getElementById("id_" + group + "_" + j) == null) {
	 	j = 0;
	}
	if (document.getElementById("id_" + group + "_" + j) != null
		&& !(document.getElementById("id_" + group + "_" + j).disabled 
	 	|| document.getElementById("id_" + group + "_" + j).type == "hidden"
	 	|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.visibility == "collapse"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.display == "none"
	    || document.getElementById("id_" + group + "_" + j).style.display == "none")) {
	    moveCursor(sourceId, "id_" + group + "_" + j);
	}
}
  
function navigatePreviousGroup(sourceId) {
	var tmp = sourceId.substr(3).split("_");
	var group = parseInt(tmp[0]) - 1;
	var j = parseInt(tmp[1]);
	if (group < 0) {
		return false;
	}
	if (document.getElementById("id_" + group + "_" + j) != null
		&& !(document.getElementById("id_" + group + "_" + j).disabled 
		|| document.getElementById("id_" + group + "_" + j).type == "hidden"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.visibility == "collapse"
		|| document.getElementById("id_" + group + "_" + j).parentNode.parentNode.style.display == "none"
	   	|| document.getElementById("id_" + group + "_" + j).style.display == "none")) {
	     moveCursor(sourceId, "id_" + group + "_" + j);
	}
}

function sumEligibleVotes() {
	var colNumber = 3;
	var sum = 0;
	var group = 0;
	var elementFound = true;
	while (elementFound) {
		if (document.getElementById("id_" + group + "_" + colNumber) != null
				&& !(document.getElementById("id_" + group + "_" + colNumber).disabled 
				|| document.getElementById("id_" + group + "_" + colNumber).type == "hidden"
			   	|| document.getElementById("id_" + group + "_" + colNumber).style.display == "none")) {
			var currentValue = parseInt(document.getElementById("id_" + group + "_" + colNumber).value, 10);
			if (!isNaN(currentValue)) {
				sum += parseInt(document.getElementById("id_" + group + "_" + colNumber).value, 10);
			}
		} else if (document.getElementById("id_" + group + "_" + colNumber) == null) {
			elementFound = false;
		}
		group++;
	}
	if (document.getElementById("id_sum") != null) {
		document.getElementById("id_sum").innerHTML = sum;
	}
	return false;
}

function isCaretAtLastPosition(elementID) {
	if (document.getElementById(elementID) != null) {
		var input = document.getElementById(elementID);
		var caretPosition = getCaretPosition(input);
		if (caretPosition != 'undefined' && caretPosition != input.value.length) {
			return false;
		}
	}
	return true;
}
  
function isCaretAtFirstPosition(elementId) {
	if (document.getElementById(elementId) != null) {
		var input = document.getElementById(elementId);
		var caretPosition = getCaretPosition(input);
		if (caretPosition != 'undefined' && caretPosition != 0) {
			return false;
		}
	}
	return true;
}

function getCaretPosition(input) {
	var caretPos = 'undefined';
		
	if(typeof document.selection != 'undefined') {
		/* Internet Explorer */
		input.focus ();
		var sel = document.selection.createRange();
		sel.moveStart('character', -input.value.length);
		caretPos = sel.text.length;
		
	} else if(typeof input.selectionEnd != 'undefined') {
		/* Gecko based browser */
		caretPos = input.selectionEnd;

	}
	
	return caretPos;
}

var onOff = "";

function capsLock(e){
 var cl = document.getElementById('capsLock');    
 var kc = e.keyCode?e.keyCode:e.which;
 var sk = e.shiftKey?e.shiftKey:((kc == 16)?true:false);

// if(onOff == ""){
   if(((kc >= 65 && kc <= 90) && !sk) || ((kc >= 97 && kc <= 122) && sk)) {
	cl.style.visibility = 'visible';
    onOff = "on";
   } else {
	cl.style.visibility = 'hidden';
	if ((kc >= 65 && kc <= 90) || (kc >= 97 && kc <= 122)) {
	  onOff = "off";
	} else {
	  onOff = "";
	}
   }
// }
}

//Hides/shows Caps Lock warning when Caps Lock key is pressed once Caps Lock
//state is determined.
function hideMsg(e){
  var cl = document.getElementById('capsLock');    
  var kc = (window.event) ? event.keyCode : e.keyCode; 

  if(kc==20 && onOff == "on")
  {
    cl.style.visibility = 'hidden';
    onOff = "off";
  }
  else if(kc==20 && onOff == "off")
  {
    cl.style.visibility = 'visible';
    onOff = "on";
  }
}

window.onkeydown=hideMsg

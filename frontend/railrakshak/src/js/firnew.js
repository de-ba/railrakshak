function parameters(){
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == "username"){
                var r = pair[1];

        document.getElementById("username").value=r; }
        }

}
function getLocation() {
var x = document.getElementById("stationf");


    if (navigator.geolocation) {
        navigator.geolocation.watchPosition(showPosition);
    } else {
        x.innerHTML = "Geolocation is not supported by this browser.";}
    }

function showPosition(position) {
var x = document.getElementById("stationf");
    x.value=position.coords.latitude +
    "  ,  " + position.coords.longitude;
}
     function checkForm(form)
      {
        if(form.firstnamein.value == "")
        {
          alert("Error: Username cannot be blank!");
          form.firstnamein.focus();
          return false;
        }

        else if(form.middlenamein.value == "")
        {
          alert("Error: Username cannot be blank!");
          form.middlenamein.focus();
          return false;
        }

        else if(form.lastnamein.value == "")
        {
          alert("Error: Username cannot be blank!");
          form.lastnamein.focus();
          return false;
        }


else if(form.fathersnamein.value == "")
        {
          alert("Error: Username cannot be blank!");
          form.fathersnamein.focus();
          return false;
        }




var radios = document.getElementsByName("genderin");
    var formValid = false;

    var i = 0;
    while (!formValid && i < radios.length) {
        if (radios[i].checked) formValid = true;
        i++;
    }

    if (!formValid) alert("Must check some option!");
    return formValid;

 re = /[0-9]/;
      if(!re.test(form.aadhaarnoin.value)) {
        alert("Error: password must contain numbers (0-9)!");
        form.aadhaarnoin.focus();
        return false;
      }}


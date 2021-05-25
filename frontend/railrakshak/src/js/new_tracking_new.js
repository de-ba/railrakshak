window.onload = parameters;
  function parameters() {
var x = localStorage.getItem("firid");
      var sta = new XMLHttpRequest();

        sta.onreadystatechange = function()
        {
            if (this.readyState == 4 &&this.status==200)
            {

                var cha =JSON.parse(sta.responseText);
                     console.log(cha);


                     document.getElementById(cha[0].status+'circle').style.backgroundColor='#64DD17';
                     console.log(cha);

            }
        }

sta.open('GET', 'http://192.168.43.57:4567/track?firid='+x, true);
sta.send();
}
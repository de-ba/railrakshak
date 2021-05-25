function getUrlParameter(name) {
    return (decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null)
    }

    (function(){
        var name = "verified";
        var x = (decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null)
        if(x == 1)
            alert("User verification successful!!!");
        else
        if(x == 0)
            alert("WRONG OTP!!!  Please register again!!");
    }());

    (function(){
        var name = "pdfcheck";
        var a = (decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null)
        if(a == 1)
            alert("FIR downloaded succesfully");
        else
        if(a == 0)
            alert("details entered are wrong, please try again");
    }());

function checkValidation()
        {


                                                if ($('#inputPnrNo').val() == null
                                                                || $('#inputPnrNo').val() == '') {
                                                        isError = "true";
                                                        errorMessage = BlankPNRError;
                                                }

                                                else  if ($('#inputPnrNo').val() != null
                                                                && $('#inputPnrNo').val() != ''
                                                                && $('#inputPnrNo').val().length < 10) {
                                                        isError = "true";
                                                        errorMessage = PNRLengthError;
                                                }
                                                else if(captchaConfig != '0')
                        		        		{
                        						if ($('#inputCaptcha').val() == null
                        								|| $('#inputCaptcha').val() == '') {
                        							isError = "true";
                        							errorMessage = BlankCaptchaError;
                        						}}

         }
window.onload = function() {
 var pie= new XMLHttpRequest();

 pie.onreadystatechange = function()
 {
 if (this.readyState==4 && this.status==200)
     {  var pies = JSON.parse(this.responseText);
        document.getElementById('Statusid');
        var status = {};


            for (var i=0;i< pies.length;i++)
            {
                var obj = pies[i];
                status[obj['status']] = parseInt(obj['counting']);
            }

                <!--var total=+submitted + +accepted;-->
                piechart(status.submitted,status['FIR Verified'],status['Under Process'],status['Investigation Complete'],status['Case Closed']);


     }
 }
 <!--pie.open('GET','http://192.168.43.57:4567/pie',true);-->
 pie.open('GET','http://192.168.43.57:4567/pie',true);
 pie.send();


function piechart(s,a,u,i,c){
var chart = new CanvasJS.Chart("chartContainer", {
	animationEnabled: true,
	title:{
		text: "Fir Status",
		horizontalAlign: "center"
	},
	data: [{
		type: "doughnut",
		startAngle: 60,
		//innerRadius: 60,
		indexLabelFontSize: 17,
		indexLabel: "{label} - #percent%",
		toolTipContent: "<b>{label}:</b> {y} (#percent%)",
		dataPoints: [
		{ y: s, label: "Submitted" },
			{ y: a, label: "Accepted" },
			{ y: u, label: "Underprocess" },
			{ y: i, label: "Investigating" },
			{ y: s, label: "Case Closed" },


		]
	}]
});
chart.render();

}

        var bar= new XMLHttpRequest();
        bar.onreadystatechange = function()
 {
 if (this.readyState==4 && this.status==200)
     {  var bars = JSON.parse(this.responseText);
        document.getElementById('firid');
                var firtypef = {};


            for (var i=0;i< bars.length;i++)
            {
                var obj = bars[i];
                firtypef[obj['firtypef']]= parseInt(obj['fircount']);
            }
barchart(firtypef['DACOITY'],firtypef['GAMBLING'],firtypef['RIOTS'],firtypef['MURDER'],firtypef['ATTEMPT TO MURDER'],firtypef['DACOITY WITH FIREARMS'],firtypef.ROBBERY,firtypef.BURGLARY,firtypef.THEFT,firtypef.RAPE,firtypef.HURT,firtypef.RIOTS,firtypef['ASSAULT ON GOVT. SERVANT ACT'],firtypef['COMMUNAL RIOTS'],firtypef['MONEY LENDERS ACT'], firtypef['ESSENTIAL COMMODITIES ACT'],firtypef['ROBBERY WITH FIREARMS'])


     }
 }
 <!--bar.open('GET','http://192.168.43.57:4567/bar',true);-->
  bar.open('GET','http://192.168.43.57:4567/bar',true);
 bar.send();
function barchart(d,g,mo,mu,atm,dwf,rob,bur,the,rape,hurt,riots,aogsa,cr,mla,eca,rwf)
{

<!--function barchart(d,g,riots,mu,atm){-->
<!--console.log(d,g,riots,mu);-->
var chart = new CanvasJS.Chart("chartContainer1", {
	animationEnabled: true,
	theme: "light2", // "light1", "light2", "dark1", "dark2"
	title:{
		text: "FIRs lodged in Different Categories"
	},
	axisY: {
		title: "No. of FIRs"
	},
	data: [{
		type: "column",
		showInLegend: true,
		legendMarkerColor: "grey",
		legendText: "Type of Crime",
		dataPoints: [
		    { label: "DACOITY" , y: d },
		    { label: "GAMBLING", y: g },
		    { label: "RIOTS", y: riots },
			{ label: "MURDER",y: mu },
			{ label: "ATTEMPT TO MURDER",           y: atm },
			{ label: "DACOITY WITH FIREARMS",       y: dwf },
			{ label: "ROBBERY" ,                    y: rob },
			{ label: "ROBBERY WITH FIREARMS",       y: rwf },
			{ label: "BURGLARY",                    y: bur },
			{ label: "THEFT",                       y: the },
			{ label: "RAPE",                        y: rape },
		    { label: "HURT" ,                       y: hurt },
			{ label: "MOLESTATION",                       y:mo},
			{ label: "COMMUNAL RIOTS",              y: cr },
			{ label: "ASSAULT ON GOVT. SERVANT ACT",y: aogsa },
			{ label: "MONEY LENDERS ACT",           y: mla },
			{ label: "ESSENTIAL COMMODITIES ACT" ,  y: eca }

		]
	}]
});
chart.render();

}
}


function validate()
 {
var user=document.getElementById("user");
var usr=document.getElementById("usr").value;
var pwd=document.getElementById("pwd").value;
if(usr=="" || pwd=="")
{alert ("please fill all the required field")
return false;}
return true;
}

function local()
{
var location= document.getElementById("location");
localStorage.setItem("location", location.value);
var ans = confirm('Do u want to proceed?');
if (ans == true) {return true;}
else {
return false;
}

}

    function local1() {
var firid= document.getElementById("firid1");
localStorage.setItem("firid", firid.value);
var ans = confirm('Do u want to proceed?');
if (ans == true) {document.location.href='http://localhost:63342/railrakshak/src/html/new_tracking_new.html',true}
else {
return false;
}
}
 function validate1()
 {
var usr2=document.getElementById("usr1").value;
var pwd2=document.getElementById("pwd1").value;
if(usr2=="" || pwd2=="")
{alert( "please fill all the required field");
return false;
}
else{
localStorage.setItem("username",usr2);

 return true;
 }
}
function clickedButton()
            {

                window.location = 'http://localhost:63342/railrakshak/src/html/OnlineRegistration1.html?_ijt=7h28s2lhssvvfkh8e820e6uj3'

            }
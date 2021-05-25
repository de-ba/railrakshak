
 var register= new XMLHttpRequest();
var data=['firstname','middlename','lastname','gender','dob','address','city','pincode','district','state','country','useremail','usermobile'];
 register.onreadystatechange = function()
 {
 if (this.readyState==4 && this.status==200)
     {

      var myarr = JSON.parse(this.responseText);
//      var abc = myarr[0].gender;
//      document.getElementById('gender').value = abc;
//      document.getElementById('gender').value = myarr[0][gender];
      for(i=0;i<=100;i++)
     {
     for (var key in myarr[i])
     {

     if(data.includes(key))
     document.getElementById(key).value = myarr[i][key];

     }

     }

     }
 };

 register.open('GET','http://192.168.43.57:4567/getaadhar?aadharno=774488559966',true);
 register.send();

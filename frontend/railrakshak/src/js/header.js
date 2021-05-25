var navbar = document.createElement('nav');
navbar.classList.add('navbar');
navbar.classList.add('navbar-inverse')

var div = document.createElement('div');
div.classList.add('container=fluid');

var button = document.createElement('button');
button.classList.add('navbar-btn');
button.classList.add('pull-right');
button.classList.add('btn');
button.classList.add('btn-danger');
button.innerText = "Logout";
button.onclick = function(){
    localStorage.clear();
    window.location="http://localhost:63342/railrakshak/a1/src/html/mysitefinal.html?_ijt=pa97qnord7bss723cu8cn1q42i"
}

navbar.appendChild(div.appendChild(button));

document.getElementsByTagName('body')[0].appendChild(navbar);
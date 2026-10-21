window.pages = new Array('one', 'two', 'three', 'four', 'five', 'six');

window.toggleMenu = function(){
 document.getElementsByClassName('wrapper')[0].classList.toggle('menu-open');
}

window.goToPage = function(page) {
  var wrapper = document.getElementsByClassName('wrapper')[0];
  var sections = document.getElementsByTagName('section');
  for (var i = 0; i < sections.length; i++) {
    sections[i].classList.remove('before','after');
    if (i > page) {
      sections[i].classList.add('after');
    }
  }
  wrapper.classList.remove('menu-open','page-one','page-two');
  wrapper.classList.add('page-' + pages[page]);
}
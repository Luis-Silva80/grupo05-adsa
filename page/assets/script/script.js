console.log("i'm here!");

function carousel(){
  $('#teste').slick({
      dots: false,
      infinite: true,
      arrows: true,
      speed: 300,
      slidesToShow: 1,
      slidesToScroll: 1,
  });
}

function darkMode() {
  $("#body").toggleClass("dm")
}

$(document).ready( function(){
  carousel();
});
function initInst(){
  $("#header").load("./templates/header.html");
  $("#footer").load("./templates/footer.html"); 
};
// function initApp() {
//   $("#sideBar").load("./templates/sideBar.html");
//   $("#footer").load("./templates/footer.html"); 
// }

// function carousel(){
//   $('#teste').slick({
//       dots: false,
//       infinite: true,
//       arrows: true,
//       speed: 300,
//       slidesToShow: 1,
//       slidesToScroll: 1,
//   });
// }

$(document).ready( function(){
  // carousel();
  initInst();
});
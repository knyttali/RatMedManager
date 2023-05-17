$(document).ready(function () {
  // Hämta referensen till elementen
var overlay = $(".overlay");
  var overlayContent = $(".overlay-content");

  // Göm overlay som standard
  overlay.hide();

  $(".goto-rat-btn").click(function () {
    var name = $(this).data("name");
    var age = $(this).data("age");

    $("#rat-name").text(name);
    $("#rat-age").text(age);

    overlay.show();
  });

  $(".overlay").click(function (event) {
    if (event.target === this) {
      $(this).hide();
    }
  });

  // Klickhändelse för att byta sektioner
  $(".myrats-btn, .home-btn, .addrat-btn, .logout-btn-link").click(function () {
    toggleSections(this);
  });

  function toggleSections(clickedLink) {
    // Ta bort "active" klass från alla länkar
    $(".myrats-btn, .home-btn, .addrat-btn, .logout-btn-link").removeClass(
      "active"
    );

    // Lägg till "active" klass på klickad länk
    $(clickedLink).addClass("active");

    // Göm alla sektioner och visa den valda sektionen baserat på länkens klass
    $(
      ".myrats-section, .main-section, .add-rat-section, .logout-section"
    ).addClass("hidden");
    if ($(clickedLink).hasClass("myrats-btn")) {
      $(".myrats-section").removeClass("hidden");
    } else if ($(clickedLink).hasClass("home-btn")) {
      $(".main-section").removeClass("hidden");
    } else if ($(clickedLink).hasClass("addrat-btn")) {
      $(".add-rat-section").removeClass("hidden");
    } else if ($(clickedLink).hasClass("logout-btn-link")) {
      $(".logout-section").removeClass("hidden");
    }
  }
});

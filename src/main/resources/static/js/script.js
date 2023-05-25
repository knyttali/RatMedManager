$(document).ready(function () {
  // Hämta referensen till elementen

  var overlay = $(".overlay");
  var overlayContent = $(".overlay-content");
  var ratId;
  overlay.hide();

  if (ratId) {
    paragrafElement.text("Råttans ID: " + ratId);
  }

  /* klickhändelse för att gå till edit-rat-sidan */
  $("a.add-diagnos-link").click(function () {
    var ratId = $(this).data("ratId");
    window.location.href = '/add-diagnos?ratId=' + ratId;
});




  $(".goto-rat-btn").click(function () {
    var ratItem = $(this).closest(".rat-item"); //
    var name = ratItem.find(".rat-details h3").text();
    var age = ratItem.find(".rat-details span").text();
    var ratId = ratItem.find("rat-details h4").text();
  
  
    showOverlay(name, age, ratId); // Visa overlay med råttans namn och ålder
  });
  
  function showOverlay(name, age, ratId) {
    overlayContent.find("#rat-name").text(name);
    overlayContent.find("#rat-age").text(age);
    overlayContent.find("#rat-id").text(ratId);
  
    overlay.show();
  }
  
  /* stäng profilen */ 
  $(".overlay").click(function (event) {
    if ($(event.target).hasClass("overlay")) {
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

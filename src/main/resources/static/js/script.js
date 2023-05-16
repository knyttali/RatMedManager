$(document).ready(function () {
  $("#add-treatment-form").submit(function (e) {
    e.preventDefault();
    var form = $(this);
    var url = form.attr("action");
    $.ajax({
      type: "POST",
      url: url,
      data: form.serialize(),
      success: function (data) {
        // Uppdatera tabellen med nya behandlingar här
        alert(data); // Visa meddelande om att behandlingen har lagts till
      },
      error: function (xhr, status, error) {
        alert("Error: " + error); // Visa felmeddelande om det uppstår ett problem
      },
    });
  });

  // Hämta referensen till elementen
  var overlay = $(".overlay");
  var overlayContent = $(".overlay-content");
  var gotoRatBtn = $(".goto-rat-btn");

  // Göm overlay som standard
  overlay.hide();

  // Klickhändelse för att visa den synliga rutan
  gotoRatBtn.click(function () {
    overlay.show();
  });

  // Klickhändelse för att gömma den synliga rutan när man klickar på det synliga området
  overlay.click(function (event) {
    if (event.target === overlay[0]) {
      overlay.hide();
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

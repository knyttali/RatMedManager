
$(document).ready(function () {
  // Hämta referensen till elementen
  var ratId;
  var diagnosId;

  /* klickhändelse för att gå till edit-rat-sidan */
  $("a.add-diagnos-link").click(function () {
    var ratId = $(this).data("ratId");
    window.location.href = "/add-diagnos?ratId=" + ratId;
  });
  /* klickhändelse för att gå till edit-rat-sidan */
  $("a.add-medicin-link").click(function () {
    var diagnosId = $(this).data("diagnosId");
    window.location.href = "/add-medicin?diagnosId=" + diagnosId;
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

function toggleMedicinStatus(medicinId) {
  // Hämta elementet för medicinen med hjälp av medicinId
  var medicinElement = document.getElementById(medicinId.toString());

  // Om medicinen är markerad (har klassen 'completed'), ta bort klassen, annars lägg till klassen
  if (medicinElement.classList.contains('completed')) {
    medicinElement.classList.remove('completed');
  } else {
    medicinElement.classList.add('completed');
  }
}

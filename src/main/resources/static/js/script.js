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
});




/* flytta hidden klassen */
// Hämta elementen som behöver manipuleras
const myRatsLinks = document.querySelectorAll(".myrats-btn");
const homeLinks = document.querySelectorAll(".home-btn");
const addRatLinks = document.querySelectorAll(".addrat-btn");
const myRatsSection = document.querySelector(".myrats-section");
const mainSection = document.querySelector(".main-section");
const addRatSection = document.querySelector(".add-rat-section");

myRatsLinks.forEach(link => {
  link.addEventListener("click", () => toggleSections(link));
});
homeLinks.forEach(link => {
  link.addEventListener("click", () => toggleSections(link));
});
addRatLinks.forEach(link => {
  link.addEventListener("click", () => toggleSections(link));
});

function toggleSections(clickedLink) {
  // remove "active" class from all links
  myRatsLinks.forEach(link => link.classList.remove("active"));
  homeLinks.forEach(link => link.classList.remove("active"));
  addRatLinks.forEach(link => link.classList.remove("active"));

  // add "active" class to clicked link
  clickedLink.classList.add("active");

  // hide all sections and show the one for the clicked link
  if (clickedLink.classList.contains("myrats-btn")) {
    myRatsSection.classList.remove("hidden");
    mainSection.classList.add("hidden");
    addRatSection.classList.add("hidden");
  } else if (clickedLink.classList.contains("home-btn")) {
    myRatsSection.classList.add("hidden");
    mainSection.classList.remove("hidden");
    addRatSection.classList.add("hidden");
  } else if (clickedLink.classList.contains("addrat-btn")) {
    myRatsSection.classList.add("hidden");
    mainSection.classList.add("hidden");
    addRatSection.classList.remove("hidden");
  }
}

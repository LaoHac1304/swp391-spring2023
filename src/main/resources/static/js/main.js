// Header Scroll
let nav = document.querySelector(".navbar");
window.onscroll = function () {
    if (document.documentElement.scrollTop > 50) {
        nav.classList.add("header-scrolled");
    } else {
        nav.classList.remove("header-scrolled");
    }
}

// nav hide 
let navBar = document.querySelectorAll(".nav-link");
let navCollapse = document.querySelector(".navbar-collapse.collapse");
navBar.forEach(function (a) {
    a.addEventListener("click", function () {
        navCollapse.classList.remove("show");
    })
})

// Swiper Slider
var swiper = new Swiper(".mySwiper", {
    direction: "vertical",
    loop: true,
    pagination: {
        el: ".swiper-pagination",
        clickable: true,
    },
    autoplay: {
        delay: 3500,
    },
});

// counter design
document.addEventListener("DOMContentLoaded", () => {
    function counter(id, start, end, duration) {
        let obj = document.getElementById(id),
            current = start,
            range = end - start,
            increment = end > start ? 1 : -1,
            step = Math.abs(Math.floor(duration / range)),
            timer = setInterval(() => {
                current += increment;
                // obj.textContent = current;
                if (current == end) {
                    clearInterval(timer);
                }
            }, step);
    }
    counter("count1", 0, 1287, 3000);
    counter("count2", 100, 50, 2500);
    counter("count3", 0, 1140, 3000);
    counter("count4", 0, 110, 3000);
});

// Our Partner
var swiper = new Swiper(".our-partner", {
    slidesPerView: 5,
    spaceBetween: 30,
    loop: true,
    autoplay: {
        delay: 2000,
    },
    breakpoints: {
        '991': {
            slidesPerView: 5,
            spaceBetween: 10,
        },
        '767': {
            slidesPerView: 3,
            spaceBetween: 10,
        },
        '320': {
            slidesPerView: 2,
            spaceBetween: 8,
        },


    },
});


//Validate password
function validatePassword() {
    var password = document.getElementById("psw").value;
    var repeatPassword = document.getElementById("pswRepeat").value;

    if (password !== repeatPassword) {
        alert("Passwords do not match.");
        return false;
    }

    return true;
}

//Selected seats
const checkboxes = document.querySelectorAll('input[type="checkbox"]');
const selectedSeat = document.getElementById('selectedSeat');

checkboxes.forEach(checkbox => {
    checkbox.addEventListener('click', () => {
        let count = 0;
        checkboxes.forEach(checkbox => {
            if (checkbox.checked) {
                count++;
            }
        });
        selectedSeat.textContent = `Selected seats: ${count}`;
    });
});

//Slider
$('.slider-nav').slick({
    slidesToShow: 4,
    slidesToScroll: 3,
    infinite: true,
    // slidesToShow: 3,
    // slidesToScroll: 3,
    dots: true,
    focusOnSelect: true
});




function checkSubmitButton() {
    var checkboxes = document.getElementsByName("selectedSeats");
    var submitButton = document.getElementById("submit-button");

    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            submitButton.disabled = false;

            return;
        }
    }
    // submitButton.title = disabled;
    submitButton.disabled = true;
}

$(document).ready(function () {
    $('#sex').select2();
});

const pickUpPlaceSelect = document.getElementById("pickUpPlace");
const dropoffPlaceSelect = document.getElementById("dropoffPlace");

pickUpPlaceSelect.addEventListener("change", (event) => {
    const selectedOptionText = event.target.options[event.target.selectedIndex].text;
    for (const option of dropoffPlaceSelect.options) {
        if (option.text === selectedOptionText) {
            option.disabled = true;
        } else {
            option.disabled = false;
        }
    }
});

dropoffPlaceSelect.addEventListener("change", (event) => {
    const selectedOptionText = event.target.options[event.target.selectedIndex].text;
    for (const option of pickUpPlaceSelect.options) {
        if (option.text === selectedOptionText) {
            option.disabled = true;
        } else {
            option.disabled = false;
        }
    }
});



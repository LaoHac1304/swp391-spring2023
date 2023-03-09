function sortTripsByPriceDescending() {
    var list = document.getElementById("list1");
    var items = list.getElementsByClassName("container-search-trip");
    var arr = [].slice.call(items);
  
    arr.sort(function(a, b) {
      var priceA = parseInt(a.querySelector(".fare").textContent);
      var priceB = parseInt(b.querySelector(".fare").textContent);
      return priceB - priceA;
    });
  
    for (var i = 0; i < arr.length; i++) {
      list.appendChild(arr[i]);
    }
  }
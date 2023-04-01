
// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';
var listMembers = /*[[${listMembers}]]*/ []; // get listMembers from model attribute


// Important
// var div = document.querySelector('div[data-total]');
// var total = parseInt(div.getAttribute('data-total'));


console.log(profit);

//Set up data for Labels and data
var months = ["January", "February", "March", "April", "May", "June"];
var profitValues = Object.values(profit);
var priceValues = months.map(function(month) {
  var monthData = profitValues.find(function(data) {
    return data.month === month;
  });
  return monthData.totalPrice;
});

// Set up the max options 
var maxPrice = 0;
for (var month in profit) {
  if (profit.hasOwnProperty(month)) {
    var totalPrice = profit[month].totalPrice;
    if (totalPrice > maxPrice) {
      maxPrice = totalPrice;
    }
  }
}

// Area Chart Example
var ctx = document.getElementById("myAreaChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    // labels: ["Mar 1", "Mar 2", "Mar 3", "Mar 4", "Mar 5", "Mar 6", "Mar 7", "Mar 8", "Mar 9", "Mar 10", "Mar 11", "Mar 12", "Mar 13"],
    // labels: ["January", "February", "March", "April", "May", "June"],
    labels: months ,
    datasets: [{
      label: "VND",
      lineTension: 0.3,
      backgroundColor: "rgba(2,117,216,0.2)",
      borderColor: "rgba(2,117,216,1)",
      pointRadius: 5,
      pointBackgroundColor: "rgba(2,117,216,1)",
      pointBorderColor: "rgba(255,255,255,0.8)",
      pointHoverRadius: 5,
      pointHoverBackgroundColor: "rgba(2,117,216,1)",
      pointHitRadius: 50,
      pointBorderWidth: 2,
      // data: [2, total -4, total - 3, total - 2, total -1, total],
      data: priceValues,
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'date'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 10
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          // max: 40000,
          max: Math.ceil(maxPrice + maxPrice * 50 / 100), // code function here
          maxTicksLimit: 10,
        },
        gridLines: {
          color: "rgba(0, 0, 0, .125)",
        }
      }],
    },
    legend: {
      display: false
    }
  }
});


console.log(maxPrice)
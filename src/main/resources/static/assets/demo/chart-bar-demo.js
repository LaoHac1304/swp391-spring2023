// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

//Set up data for Labels and data
var months = ["January", "February", "March", "April", "May", "June"];
var profitValues = Object.values(profit);
var ticketValues = months.map(function(month) {
  var monthData = profitValues.find(function(data) {
    return data.month === month;
  });
  return monthData.totalTicket;
});

// Set up the max options 
var maxTicket = 0;
for (var month in profit) {
  if (profit.hasOwnProperty(month)) {
    var totalTicket = profit[month].totalTicket;
    if (totalTicket > maxTicket) {
      maxTicket = totalTicket;
    }
  }
}

// Bar Chart Example
var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    // labels: ["January", "February", "March", "April", "May", "June"],
    labels:months,
    datasets: [{
      label: "Tickets",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      // data: [4215, 5312, 6251, 7841, 9821, 14984],
      data:ticketValues,
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'month'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: Math.ceil(maxTicket + maxTicket * 30 / 100),
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});

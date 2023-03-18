window.addEventListener('DOMContentLoaded', event => {
    // Simple-DataTables
    // https://github.com/fiduswriter/Simple-DataTables/wiki

    const datatablesSimple = document.getElementById('datatablesSimple');
    if (datatablesSimple) {
        new simpleDatatables.DataTable(datatablesSimple, {
            responsive: true,
        });
    }
});

$(document).ready(function() {
    $('#myTable').DataTable({
      "pageLength": 10, // Display 10 rows per page
      "ordering": true, // Enable sorting
      "searching": true // Enable searching
    });
  });


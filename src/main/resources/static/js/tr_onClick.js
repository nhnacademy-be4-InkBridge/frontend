document.querySelectorAll('tr[role="button"]').forEach(function (tr) {
  tr.addEventListener('click', function () {
    const dataIndex = tr.querySelector('td:first-child').innerText;
    console.log('/admin/book/' + dataIndex);
    window.location.assign('/admin/books/' + dataIndex);
  });
});

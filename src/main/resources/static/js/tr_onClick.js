document.querySelectorAll('tr[role="button"]').forEach(function (tr) {
  tr.addEventListener('click', function () {
    const dataIndex = this.getAttribute('data-index');
    console.log('/admin/books/' + dataIndex);
    // window.location.assign('/admin/books/' + dataIndex);
  });
});

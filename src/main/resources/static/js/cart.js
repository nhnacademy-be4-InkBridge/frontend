const setCookie = () => {
  console.log('hello')
  const checkboxes = document.querySelectorAll(
      'input[type="checkbox"]:checked');
  let existingCookie = [];
  checkboxes.forEach((checkbox) => {
    let row = checkbox.closest('tr');
    let cookie = {
      bookId: row.querySelector('#bookId').value,
      thumbnail: row.querySelector('#thumbnail').getAttribute('src'),
      bookTitle: row.querySelector('#bookTitle').textContent,
      price: row.querySelector('#price').textContent,
      regularPrice: row.querySelector('#regularPrice').textContent,
      amount: row.querySelector('input[name="amount"]').value,
      isPackagable: row.querySelector('#isPackagable').value
    };
    console.log(cookie);
    existingCookie.push(cookie);
  });
  document.cookie = 'info=' + encodeURIComponent(JSON.stringify(existingCookie))
      + '; path=/;';
};

document.querySelectorAll('.quantity button').forEach(function (button) {
  button.addEventListener('click', function () {
    var oldValue = parseFloat(
        this.parentElement.parentElement.querySelector('input').value);
    var newVal;
    if (this.classList.contains('btn-plus')) {
      newVal = oldValue + 1;
    } else {
      newVal = oldValue > 0 ? oldValue - 1 : 0;
    }
    this.parentElement.parentElement.querySelector('input').value = newVal;
  });
});


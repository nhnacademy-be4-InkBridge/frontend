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

const setCookie = () => {
  const bookId = document.getElementById('bookId').value;
  const thumbnail = document.getElementById('thumbnail').getAttribute('src');
  const bookTitle = document.getElementById('bookTitle').textContent;
  const price = document.getElementById('price').textContent;
  const regularPrice = document.getElementById('regularPrice').textContent;
  const amount = document.getElementById('amount').value;
  const isPackagable = document.getElementById('isPackagable').value;

  document.cookie =
      'cart=' + '{"bookId": "' + bookId + '", "thumbnail": "' + thumbnail
      + '", "bookTitle": "' + bookTitle + '", "price": "' + price
      + '", "regularPrice": "' + regularPrice + '", "amount": "' + amount
      + '", "isPackagable": "' + isPackagable + '"}; path=/;';
};
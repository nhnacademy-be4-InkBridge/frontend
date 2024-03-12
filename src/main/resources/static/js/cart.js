document.getElementById('order').addEventListener('click', function (event) {
  const checkboxes = document.querySelectorAll(
      'input[type="checkbox"]:checked');
  if (checkboxes.length === 0) {
    alert('최소 하나의 상품을 선택해주세요.');
    event.preventDefault();
    return;
  }
  let existingCookie = [];
  checkboxes.forEach((checkbox) => {
    let row = checkbox.closest('tr');
    let cookie = {
      bookId: row.querySelector('#bookId').value,
      amount: row.querySelector('input[name="amount"]').value,
    };
    console.log(cookie);
    existingCookie.push(cookie);
  });
  document.cookie = 'info=' + encodeURIComponent(JSON.stringify(existingCookie))
      + '; path=/;';

});

document.querySelectorAll('.quantity button').forEach(function (button) {
  button.addEventListener('click', function () {
    var oldValue = parseInt(this.parentElement.parentElement.querySelector(
        'input[name="amount"]').value);
    const stock = parseInt(this.parentElement.parentElement.querySelector(
        'input[name="stock"]').value);
    var newVal = oldValue;
    // price
    var price = parseInt(
        this.parentElement.parentElement.parentElement.parentElement.querySelector(
            '#price').textContent);
    var totalPrice = parseInt(
        document.getElementById('totalPrice').textContent);

    if (this.classList.contains('btn-plus')) {
      if (oldValue + 1 < stock) {
        newVal = oldValue + 1;
      }
    } else {
      newVal = oldValue > 0 ? oldValue - 1 : 0;
    }
    totalPrice = totalPrice + price;
    this.parentElement.parentElement.querySelector('input').value = newVal;
    document.getElementById('totalPrice').textContent = totalPrice;
    const bookId = this.parentElement.parentElement.parentElement.parentElement.querySelector(
        '#bookId').value;
    fetch('/cart/book/' + bookId + '?amount=' + newVal, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => {
      if (!response.ok) {
        alert('error');
      }
    });
  });
});

window.onload = function () {
  let totalPrice = 0;
  document.querySelectorAll('#price').forEach(function (element) {
    let amountValue = element.parentElement.parentElement.querySelector(
        'input[name="amount"]').value;
    totalPrice += parseInt(element.textContent) * amountValue;
  });
  document.getElementById('totalPrice').textContent = totalPrice;
};

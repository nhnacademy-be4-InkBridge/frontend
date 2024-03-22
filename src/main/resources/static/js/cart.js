document.getElementById('order').addEventListener('click', function (event) {
  const replaceNotInt = /^-?\d+$/;


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
    const amount = row.querySelector('input[name="amount"]').value;

    if (!amount.match(replaceNotInt)) {
      event.preventDefault();
      console.log('amount: ' + row.querySelector('input[name="amount"]').value);
      alert('숫자만 입력 가능합니다.: '+ amount);
      row.querySelector('input[name="amount"]').value =  1;
      setTotalPrice();
      return;
    }
    if (parseInt(amount, 10) < 1) {
      event.preventDefault();
      alert('최소 한 개 이상의 수량을 담아야 합니다.');
      row.querySelector('input[name="amount"]').value = 1;
      setTotalPrice();
      return;
    }

    let cookie = {
      bookId: row.querySelector('#bookId').value,
      amount: amount,
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
    var newVal = oldValue;
    // price
    var price = parseInt(
        this.parentElement.parentElement.parentElement.parentElement.querySelector(
            '#price').textContent);
    var totalPrice = parseInt(
        document.getElementById('totalPrice').textContent);

    if (this.classList.contains('btn-plus')) {
        newVal = oldValue + 1;
        totalPrice = totalPrice + price;
    } else {
      newVal = oldValue > 1 ? oldValue - 1 : 1;
      totalPrice = newVal > 1 ? totalPrice - price : totalPrice;
    }
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

window.onload = setTotalPrice();

function setTotalPrice() {
  let totalPrice = 0;
  document.querySelectorAll('#price').forEach(function (element) {
    let amountValue = element.parentElement.parentElement.querySelector(
        'input[name="amount"]').value;
    totalPrice += parseInt(element.textContent.replaceAll(',', '')) * amountValue;

    console.log('amount: ' + amountValue);
    console.log('total: ' + parseInt(element.textContent));
  });
  document.getElementById('totalPrice').textContent = totalPrice.toLocaleString('en-US');
}
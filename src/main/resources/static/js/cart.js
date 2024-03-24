// 주문하기 눌렀을 때
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
    const amount = row.querySelector('input[name="amount"]').value;

    if (amountValidCheck(amount, row)) {
      event.preventDefault();
    }

    let cookie = {
      bookId: row.querySelector('#bookId').value,
      amount: amount,
    };
    existingCookie.push(cookie);
  });
  document.cookie = 'info=' + encodeURIComponent(JSON.stringify(existingCookie))
      + '; path=/;';

});

// 수량 변동 시
document.querySelectorAll('input[name="amount"]').forEach(function (input) {
  input.addEventListener('input', function () {
    let row = input.closest('tr');

    const bookId = row.querySelector('#bookId').value;

    if (amountValidCheck(input.value, row)) {
      return;
    }

    fetch('/cart/book/' + bookId + '?amount=' + input.value, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      }
    }).then(response => {
      if (!response.ok) {
        alert('error');
      }
    });
  setTotalPrice();
  });
});

function amountValidCheck(amount, row) {
  const replaceNotInt = /^-?\d+$/;

  if (!amount.match(replaceNotInt)) {
    alert('숫자만 입력 가능합니다.');
    row.querySelector('input[name="amount"]').value =  1;
    setTotalPrice();
  }
  if (parseInt(amount, 10) < 1) {
    alert('최소 한 개 이상의 수량을 담아야 합니다.');
    row.querySelector('input[name="amount"]').value = 1;
    setTotalPrice();
  }
  return false;
}

document.querySelectorAll('.quantity button').forEach(function (button) {
  button.addEventListener('click', function () {
    var oldValue = parseInt(this.parentElement.parentElement.querySelector(
        'input[name="amount"]').value);
    var newVal = oldValue;
    // price
    var price = parseInt(
        this.parentElement.parentElement.parentElement.parentElement.querySelector(
            '#price').textContent.replaceAll(',', ''));
    var totalPrice = parseInt(
        document.getElementById('totalPrice').textContent.replaceAll(',', ''));
    const bookId = this.parentElement.parentElement.parentElement.parentElement.querySelector(
        '#bookId').value;

    if (this.classList.contains('btn-plus')) {
        newVal = oldValue + 1;
        totalPrice = totalPrice + price;
        update(bookId, newVal);
    } else {
      if (oldValue > 1) {
        newVal = oldValue - 1;
        totalPrice =totalPrice - price;
        update(bookId, newVal);
      }
    }
    this.parentElement.parentElement.querySelector('input').value = newVal; // amount
    document.getElementById('totalPrice').textContent = totalPrice.toLocaleString('en-US');
  });
});

function update(bookId, amount) {
  fetch('/cart/book/' + bookId + '?amount=' + amount, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    }
  }).then(response => {
    if (!response.ok) {
      alert('error');
    }
  });
}

document.addEventListener('DOMContentLoaded', setTotalPrice);

function setTotalPrice() {
  let totalPrice = 0;
  document.querySelectorAll('#price').forEach(function (element) {
    let amountValue = element.parentElement.parentElement.querySelector(
        'input[name="amount"]').value;
    totalPrice += parseInt(element.textContent.replaceAll(',', '')) * amountValue;
  });
  document.getElementById('totalPrice').textContent = totalPrice.toLocaleString('en-US');
}
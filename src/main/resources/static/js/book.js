// 수량을 증가
document.querySelectorAll('.quantity button').forEach(function (button) {
  button.addEventListener('click', function () {
    var oldValue = parseFloat(
        this.parentElement.parentElement.querySelector('input').value);
    var newVal;
    if (this.classList.contains('btn-plus')) {
      newVal = oldValue + 1;
    } else {
      newVal = oldValue > 1 ? oldValue - 1 : 1;
    }
    this.parentElement.parentElement.querySelector('input').value = newVal;
  });
});

// 쿠키를 설정
const setCookie = (event) => {
  const amount = document.getElementById('amount').value;
  if (amount === '0') {
    alert('최소 한 개 이상의 수량을 담아야 합니다.');
    return false;
  }
  let cookies = {
    bookId: document.getElementById('bookId').value,
    amount: amount,
  };
  let existingCookie = [];
  existingCookie.push(cookies);
  document.cookie = 'info=' + encodeURIComponent(JSON.stringify(existingCookie))
      + '; path=/;';
};

// markdownit 라이브러리를 사용해 markdown을 html로 변환
window.addEventListener('load', convertMarkdown);

function convertMarkdown() {
  const md = window.markdownit();
  const description = document.getElementById('description');
  const content = [];
  const splitContent = description.innerHTML.split('\n');

  for (var i = 0; i < splitContent.length; i++) {
    content.push(md.render(splitContent[i]));
  }
  description.innerHTML = content.join('');
}


// document.querySelectorAll('.fa-star').forEach(function (star) {
//   star.addEventListener('click', function () {
//     var rating = parseInt(this.getAttribute('data-rating'));
//     document.querySelectorAll('.fa-star').forEach(function (star, index) {
//       if (index < rating) {
//         star.classList.remove('text-muted');
//       } else {
//         star.classList.add('text-muted');
//       }
//     });
//   });
// });

document.getElementById('amount').addEventListener('change', function () {
  const amount = document.getElementById('amount').value;
  const replaceNotInt = /[^0-9]/gi;

  if (amount < 1) {
    alert('최소 한 개 이상의 수량을 담아야 합니다.');
    document.getElementById('amount').value = 1;
  }
  if (amount.match(replaceNotInt)) {
    alert('숫자만 입력 가능합니다.');
    document.getElementById('amount').value = 1;
  }
});

document.getElementById('cartButton').addEventListener("click", async (e) => {
  const replaceNotInt = /[^0-9]/gi;

  const bookId = document.getElementById('bookId').value;
  const amount = document.getElementById('amount').value;

  if (amount < 1) {
    alert('최소 한 개 이상의 수량을 담아야 합니다.');
    document.getElementById('amount').value = 1;
    e.preventDefault();
    return;
  }

  if (amount.match(replaceNotInt)) {
    alert('숫자만 입력 가능합니다.');
    document.getElementById('amount').value = 1;
    e.preventDefault();
    return;
  }

  console.log(bookId);
  console.log(amount);

  await fetch("/cart", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      "bookId": bookId,
      "amount": amount
    }),
  })
  .then((response) => response.json)
  .then((data) => console.log(data))
})
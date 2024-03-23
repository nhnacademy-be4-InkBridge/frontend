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

function loadReview(element) {
  var pageNumber = element.getAttribute('data-page-number');

  var nextPageNumber = parseInt(pageNumber, 10) + 1; // 다음 페이지 번호 계산
  var pageSize = element.getAttribute('data-page-size');
  var totalPage = element.getAttribute('data-total-page');
  var bookId = document.getElementById('bookId').value/* 여기에 책 ID */;
  element.setAttribute('data-page-number', nextPageNumber);

  if (totalPage <= (nextPageNumber + 1) * pageSize) {
    element.style.display = 'none';
  }
  // AJAX 요청 보내기
  var xhr = new XMLHttpRequest();
  xhr.open('GET',
      '/review?bookId=' + bookId + '&page=' + nextPageNumber + '&size='
      + pageSize);
  xhr.onload = function () {
    if (xhr.status === 200) {
      // 성공적으로 데이터를 받았을 때, 받아온 HTML을 적용합니다.
      var receivedHtml = xhr.responseText;
      document.getElementById('reviewChunk').insertAdjacentHTML('beforebegin',
          receivedHtml);
    } else {
      // 오류 처리
      console.error('Error while fetching data:', xhr.statusText);
    }
  };
  xhr.onerror = function () {
    // 오류 처리
    console.error('Error while fetching data:', xhr.statusText);
  };
  xhr.send();
}
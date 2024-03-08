// 수량을 증가
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

// 쿠키를 설정
const setCookie = () => {
  let cookies = {
    bookId: document.getElementById('bookId').value,
    thumbnail: document.getElementById('thumbnail').getAttribute('src'),
    bookTitle: document.getElementById('bookTitle').textContent,
    price: document.getElementById('price').textContent,
    regularPrice: document.getElementById('regularPrice').textContent,
    amount: document.getElementById('amount').value,
    isPackagable: document.getElementById('isPackagable').value
  }

  document.cookie =
      'info=' + JSON.stringify(cookies) + '; path=/;';
};

// markdownit 라이브러리를 사용해 markdown을 html로 변환
window.onload = function () {
  const md = window.markdownit();
  const description = document.getElementById('description');
  const content = [];
  const splitContent = description.innerHTML.split('\n');

  for (var i = 0; i < splitContent.length; i++) {
    content.push(md.render(splitContent[i]));
  }
  description.innerHTML = content.join('');
};

document.querySelectorAll('.fa-star').forEach(function (star) {
  star.addEventListener('click', function () {
    var rating = parseInt(this.getAttribute('data-rating'));
    document.querySelectorAll('.fa-star').forEach(function (star, index) {
      if (index < rating) {
        star.classList.remove('text-muted');
      } else {
        star.classList.add('text-muted');
      }
    });
  });
});

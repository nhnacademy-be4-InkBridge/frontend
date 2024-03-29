function updateStar(element) {
  // 초기값 설정
  let currentRating = element.getAttribute('data-score') === null ? 1
      : element.getAttribute('data-score');

  let reviewId = element.getAttribute('data-review-id');
  document.getElementById('score' + reviewId).value = currentRating;
  // 별점 요소 선택
  var starElements = document.getElementById(
      'score' + reviewId).parentElement.querySelectorAll('.update-star');
  // 초기화 함수 호출
  initializeRatingStars(currentRating, starElements);

  // 마우스 이벤트 리스너 추가
  Array.from(starElements).forEach(function (star, index) {
    star.addEventListener('mouseenter', function () {
      // 현재 별을 기준으로 색상 변경
      changeRatingStars(index, starElements);
    });

    star.addEventListener('mouseleave', function () {
      // 초기값으로 돌아가기
      initializeRatingStars(currentRating, starElements);
    });

    star.addEventListener('click', function () {
      // 클릭한 별까지 고정하기
      currentRating = index + 1;
      initializeRatingStars(currentRating, starElements);
      document.getElementById('score' + reviewId).value = currentRating;
    });
  });

  // 초기값에 따라 별 색상 초기화 함수
  function initializeRatingStars(rating, elements) {
    elements.forEach(function (star, index) {
      if (index < rating) {
        star.style.color = '#FFD700'; // 노란색으로 설정
      } else {
        star.style.color = '#bbb'; // 회색으로 설정
      }
    });
  }

  // 별점을 변경하는 함수
  function changeRatingStars(index, elements) {
    elements.forEach(function (star, i) {
      if (i <= index) {
        star.style.color = '#FFD700'; // 노란색으로 설정
      } else {
        star.style.color = '#bbb'; // 회색으로 설정
      }
    });
  }
}

function updateStarOnCreate(element) {
  let currentRating = 1;
  const bookId = element.getAttribute('data-book-id');
  // 별점 요소 선택
  // var starElements = document.querySelectorAll('.update-star');
  var starElements = document.getElementById(
      'score' + bookId).parentElement.querySelectorAll('.update-star');


  // 초기화 함수 호출
  initializeRatingStars(currentRating, starElements);

  // 마우스 이벤트 리스너 추가
  Array.from(starElements).forEach(function (star, index) {
    star.addEventListener('mouseenter', function () {
      // 현재 별을 기준으로 색상 변경
      changeRatingStars(index, starElements);
    });

    star.addEventListener('mouseleave', function () {
      // 초기값으로 돌아가기
      initializeRatingStars(currentRating, starElements);
    });

    star.addEventListener('click', function () {
      // 클릭한 별까지 고정하기
      currentRating = index + 1;
      initializeRatingStars(currentRating, starElements);
      document.getElementById('score' + bookId).value = currentRating;
    });
  });

  // 초기값에 따라 별 색상 초기화 함수
  function initializeRatingStars(rating, elements) {
    elements.forEach(function (star, index) {
      if (index < rating) {
        star.style.color = '#FFD700'; // 노란색으로 설정
      } else {
        star.style.color = '#bbb'; // 회색으로 설정
      }
    });
  }

  // 별점을 변경하는 함수
  function changeRatingStars(index, elements) {
    elements.forEach(function (star, i) {
      if (i <= index) {
        star.style.color = '#FFD700'; // 노란색으로 설정
      } else {
        star.style.color = '#bbb'; // 회색으로 설정
      }
    });
  }
}

function setBookId(element) {
  document.getElementById('bookId').value = element.getAttribute(
      'data-book-id');
  document.querySelector(
      'input[name="orderDetailId"]').value = element.getAttribute(
      'data-order-detail');
}

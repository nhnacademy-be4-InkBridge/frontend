"use strict";

let selectedCoupon = [];

// input 엔터 방지
document.addEventListener('keydown', function (event) {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}, true);

// 페이지 로드 시 정보 초기화 함수
function paymentInfo() {
  let sumTotalRegular = 0;
  let sumTotalPrice = 0;

  orderBooks.forEach(book => {
    sumTotalRegular += book.regularPrice * book.amount;
    sumTotalPrice += book.price * book.amount;
  });

  document.getElementById("total_regular_price").innerText = priceToString(sumTotalRegular);
  document.getElementById("product_discount").innerText = priceToString(sumTotalRegular - sumTotalPrice);
  document.getElementById("package_price").innerText = '0';
  document.getElementById("coupon_discount").innerText = '0';
  document.getElementById("use_point").innerText = '0';

  // 배송비 계산
  let deliveryPrice = sumTotalPrice < deliveryPolicy.freeDeliveryPrice
      ? deliveryPolicy.deliveryPrice : 0;

  document.getElementById("delivery_price").innerText = priceToString(deliveryPrice);
  document.getElementById("deliveryPrice").value = deliveryPrice.toString();

  // 예상 포인트 적립률
  let accumulatePoint = Math.round(
      sumTotalPrice * accumulationRatePolicy.accumulationRate / 100);

  document.getElementById("accumulate_point").innerText = priceToString(accumulatePoint);

  // 결제 금액 = 판매가 + 배송비 + 포장비 - 쿠폰할인 - 사용 포인트
  document.getElementById("pay_amount").innerText = priceToString(sumTotalPrice + deliveryPrice);

  let orderName = orderBooks[0].bookTitle;

  if (orderBooks.length > 1) {
    orderName = orderName + " 외 " + (orderBooks.length - 1) + " 상품";
  }

  document.getElementById("orderName").value = orderName;
}

// 상품 금액 - 상품 할인 금액 계산 함수
function calcBookPrice() {
  let sumTotalPrice = 0;

  orderBooks.forEach(book => {
    sumTotalPrice += book.price * book.amount;
  });

  return sumTotalPrice;
}

// 페이지 로드 이벤트 추가
document.addEventListener("DOMContentLoaded", paymentInfo);

// 포인트 사용 시 event -> onchange
// 보유 포인트 내에서 사용할 수 있어야 함. (분기문 작성 필요)
function calcPoint() {
  let point = document.getElementById("using_point");

  // 비회원
  if (point == null) {
    return 0;
  }

  // 아무것도 입력 되지 않은 상태
  if (point.value === "") {
    point.value = "0";
  }

  // 입력값이 허용될 수 없는 문자인지 확인
  let chkStyle = /^[0-9]+$/;
  // 상품 금액을 넘어서는지 확인
  let sumTotalPrice = calcBookPrice();
  if (sumTotalPrice < parseInt(point.value) || !chkStyle.test(point.value) || parseInt(userPoint.point) < parseInt(point.value)) {
    alert("포인트를 다시 입력해 주세요.");
    point.value = "0";
  }

  // 보유 포인트 이상 사용 불가 확인
  document.getElementById("use_point").innerText = priceToString(point.value);

  document.getElementById("point").value = point.value;
  return parseInt(point.value);
}

// 포장 가격 계산
function calcWrapping() {

  let totalWrappingPrice = 0;

  for (let i = 0; i < orderBooks.length; i++) {
    if (orderBooks[i].isPackagable) {
      let orderBook = "bookOrderList[" + i + "]";
      let wrappingId = document.getElementById(orderBook + ".wrappingId");
      let bookWrappingPrice = document.getElementById(
          orderBook + ".wrappingPrice");

      if (wrappingId.value == null || wrappingId.value == "") {
        continue;
      }

      let wrappingPrice = getWrappingPrice(wrappingId.value);
      if (wrappingPrice == null) {
        alert("잘못된 포장지 번호입니다. 다시 선택해주세요.");
        return null;
      }

      totalWrappingPrice += wrappingPrice;
      bookWrappingPrice.value = wrappingPrice;
    }
  }

  document.getElementById("package_price").innerText = priceToString(totalWrappingPrice);
  return totalWrappingPrice;
}

function getWrappingPrice(id) {
  for (let i = 0; i < wrappingList.length; i++) {
    if (wrappingList[i].wrappingId == id && wrappingList[i].isActive) {
      return wrappingList[i].price;
    }
  }

  return null;
}

// 결제 금액 계산 함수
function calcPayAmount() {
  let wrappingPrice = calcWrapping();
  let couponDiscountPrice = calcCoupon();
  let pointDiscountPrice = calcPoint();
  let sumBookPrice = calcBookPrice();

  if (wrappingPrice == null || couponDiscountPrice == null || pointDiscountPrice
      == null || couponDiscountPrice == null) {
    return;
  }

  let deliveryPrice = sumBookPrice < deliveryPolicy.freeDeliveryPrice
      ? deliveryPolicy.deliveryPrice : 0;

  let totalPrice = sumBookPrice + deliveryPrice + wrappingPrice
      - couponDiscountPrice - pointDiscountPrice;

  if (totalPrice < 0) {
    alert("결제 금액은 0원 미만일 수 없습니다.");
    return;
  }

  document.getElementById("payAmount").value = totalPrice;
  document.getElementById("pay_amount").innerText = priceToString(totalPrice);
  return totalPrice;
}

// 배송 예정일 익일부터 선택 가능 (기본 값 : 익일)
function setDeliveryDate() {
  let deliveryDate = document.getElementById("delivery_date");

  let tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);

  let maxDate = new Date();
  maxDate.setDate(maxDate.getDate() + 15);

  deliveryDate.setAttribute("min", tomorrow.toISOString().split("T")[0]);
  deliveryDate.setAttribute("max", maxDate.toISOString().split("T")[0]);
  deliveryDate.value = tomorrow.toISOString().split("T")[0];

}

document.addEventListener("DOMContentLoaded", setDeliveryDate);

// 주문서 내용 확인
function doOrder() {

  let receiverName = document.getElementById("receiverName").value;

  if (receiverName == null || receiverName == "") {
    alert("수취인 이름을 작성해 주세요.")
    return;
  }

  let receiverNumber = document.getElementById("receiverNumber").value;

  if (receiverNumber == null || receiverNumber == "") {
    alert("수취인 전화번호를 작성해주세요")
    return;
  }

  let zipCode = document.getElementById("zipCode").value;

  if (zipCode == null || zipCode == "") {
    alert("주소를 선택해주세요");
    return;
  }

  let detailAddress = document.getElementById("detailAddress").value;

  if (detailAddress == null || detailAddress == "") {
    alert("상세주조를 입력해주세요");
    return;
  }

  let senderName = document.getElementById("receiverName").value;

  if (senderName == null || senderName == "") {
    alert("발송인 이름을 입력해주세요");
    return;
  }

  let senderNumber = document.getElementById("senderPhoneNumber").value;

  if (senderNumber == null || senderNumber == "") {
    alert("발송인 전화번호를 입력해주세요");
    return;
  }

  let senderEmail = document.getElementById("senderEmail").value;

  if (senderEmail == null || senderEmail == "") {
    alert("발송인 이메일을 입력해주세요");
    return;
  }

  let deliveryDate = document.getElementById("delivery_date").value;

  if (deliveryDate == null || deliveryDate == "") {
    alert("배송 예정일을 선택해주세요");
    return;
  }

  calcPayAmount();

  document.getElementById("order_form").submit();
}

// 쿠폰 적용 함수
function applyCoupon(bookId, index) {
  let radioGroup = document.getElementsByName("radio" + bookId);


  let selectCouponId = null;

  for (let i = 0; i < radioGroup.length; i++) {
    if (radioGroup[i].checked) {
      selectCouponId = radioGroup[i];
    }
  }

  // 선택된 라디오 버튼이 있는지 확인
  if (!selectCouponId) {
    return;
  }

  // 해당 쿠폰이 다른 상품에서 선택된 적이 있는지 여부 확인
  if (selectedCoupon.find(
      coupon => coupon.couponId == selectCouponId.value && bookId != coupon.bookId)) {
    alert("다른 상품에 적용된 쿠폰입니다. 다른 쿠폰을 선택해주세요.");
    initCoupon(bookId, index);
    return;
  }

  // 쿠폰 가격이 쿠폰 사용 최소 금액보다 큰지 확인
  let selectCoupon = couponList.find(coupon => coupon.bookId == bookId);
  let selectCouponInfo = selectCoupon.memberCouponReadResponseDtos.find(coupon => coupon.memberCouponId == selectCouponId.value);
  if (calcOneBookPrice(bookId) < selectCouponInfo.minPrice) {
    alert("적용할 수 없는 쿠폰입니다. 다른 쿠폰을 사용해주세요.");
    initCoupon(bookId, index);
    return;
  }

  // 현재 상품에 적용된 다른 쿠폰이 존재하는지 확인
  let findBookCoupon = selectedCoupon.find(coupon => coupon.bookId == bookId);
  if (findBookCoupon) {
    // 적용되있던 쿠폰을 제거
    selectedCoupon.splice(selectedCoupon.indexOf(findBookCoupon));
  }

  // 쿠폰 적용
  let coupon = {
    bookId: bookId,
    couponId: selectCouponId.value
  };

  // 적용 쿠폰 목록에 추가
  selectedCoupon.push(coupon);


//   해당 도서 쿠폰 Input 요소 가져오기
  let couponInput = document.getElementById(
      "bookOrderList[" + index + "].couponId");
  couponInput.value = selectCouponId.value;

//   결제금액 계산 함수 호출
  calcPayAmount();
}

// 쿠폰 할인 금액 계산
function calcCoupon() {
  let totalCouponDiscountPrice = 0;

  for (let i = 0; i < orderBooks.length; i++) {
    let orderBook = "bookOrderList[" + i + "]";
    let couponId = document.getElementById(orderBook + ".couponId");

    if (!couponId.value) {
      continue;
    }

    let price = calcOneBookPrice(orderBooks[i].bookId);
    let coupon = couponList.find(coupon => coupon.bookId == orderBooks[i].bookId);

    if (coupon) {
      totalCouponDiscountPrice += calcCouponPrice(price,
          coupon.memberCouponReadResponseDtos.find(coupon => coupon.memberCouponId == couponId.value));
    }
  }

  document.getElementById(
      "coupon_discount").innerText = totalCouponDiscountPrice;
  return totalCouponDiscountPrice;
}

// 도서의 한권의 가격을 계산하는 함수
function calcOneBookPrice(bookId) {
  let book = orderBooks.find(book => book.bookId == bookId);
  return book.price * book.amount;
}

function calcCouponPrice(price, coupon) {
  let result = 0;

  if (coupon.couponTypeName == "PERCENT") {
    // 퍼센트 쿠폰 할인 금액 계산
    let discount = (100 - coupon.discountPrice) / 100 * price;

    result = discount > coupon.maxDiscountPrice ? coupon.maxDiscountPrice : discount;
  } else {
  //   금액 쿠폰 할인 금액 계산
    result = coupon.discountPrice;
  }

  return result;
}

function initCoupon(bookId, index) {
  // 라디오 버튼 초기화
  $("input[name='radio" + bookId + "'").prop('checked', false);

  let findBookCoupon = selectedCoupon.find(coupon => coupon.bookId == bookId);
  if (findBookCoupon) {
    // 적용되있던 쿠폰을 제거
    selectedCoupon.splice(selectedCoupon.indexOf(findBookCoupon));
  }

  let couponInput = document.getElementById(
      "bookOrderList[" + index + "].couponId").value = null;

  calcPayAmount();
}

// 페이지 로드 시 쿠폰 null값으로 초기화
function loadCoupon() {
  for (let i = 0; i < orderBooks.length; i++) {
    document.getElementById("bookOrderList[" + i + "].couponId").value = null;
  }
}

function priceToString(price) {
  return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
}

document.addEventListener("DOMContentLoaded", loadCoupon);
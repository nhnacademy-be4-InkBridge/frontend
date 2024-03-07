"use strict";

// input 엔터 방지
document.addEventListener('keydown', function(event) {
  if (event.keyCode === 13) {
    event.preventDefault();
  };
}, true);

// 페이지 로드 시 결제 정보 초기화 함수
function paymentInfo() {
  let sumTotalRegular = 0;
  let sumTotalPrice = 0;

  orderBooks.forEach(book => {
    sumTotalRegular += book.regularPrice * book.amount;
    sumTotalPrice += book.price * book.amount;
  });

  document.getElementById("total_regular_price").innerText = sumTotalRegular;
  document.getElementById("product_discount").innerText = sumTotalRegular - sumTotalPrice;
  document.getElementById("package_price").innerText = '0';
  document.getElementById("coupon_discount").innerText = '0';
  document.getElementById("use_point").innerText = '0';

  // 배송비 계산
  let deliveryPrice = sumTotalPrice < deliveryPolicy.freeDeliveryPrice
      ? deliveryPolicy.deliveryPrice : 0;

  document.getElementById("delivery_price").innerText = deliveryPrice;
  document.getElementById("deliveryPrice").value = deliveryPrice.toString();

  // 예상 포인트 적립률
  let accumulatePoint = Math.round(
      sumTotalPrice * accumulationRatePolicy.accumulationRate / 100);

  document.getElementById("accumulate_point").innerText = accumulatePoint;

  // 결제 금액 = 판매가 + 배송비 + 포장비 - 쿠폰할인 - 사용 포인트
  document.getElementById("pay_amount").innerText = sumTotalPrice
      + deliveryPrice;

  let orderName = orderBooks[0].bookTitle;

  if (orderBooks.length > 1) {
    orderName = orderName + " 외 " + (orderBooks.length - 1) + " 상품";
  }


  console.log(orderName);

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

  // 아무것도 입력 되지 않은 상태
  if (point.value === null || point.value === "") {
    point.value = "0";
  }

  // 입력값이 허용될 수 없는 문자인지 확인
  let chkStyle = /^[0-9]+$/;
  // 상품 금액을 넘어서는지 확인
  let sumTotalPrice = calcBookPrice();
  if (sumTotalPrice < parseInt(point.value) || !chkStyle.test(point.value)) {
    alert("포인트를 다시 입력해 주세요.");
    point.value = "0";
  }

  // 보유 포인트 이상 사용 불가 확인
  document.getElementById("use_point").innerText = point.value;
  return parseInt(point.value);
}

// 쿠폰 할인 금액 계산
function calcCoupon() {


  return 0;
}

// 포장 가격 계산
function calcWrapping() {
  let totalWrappingPrice = 0;

  for (let i = 0; i < orderBooks.length; i++) {
    if (orderBooks[i].isPackagable) {
      let orderBook = "bookOrderList[" + i + "]";
      let wrappingId = document.getElementById(orderBook + ".wrappingId");
      let bookWrappingPrice = document.getElementById(orderBook + ".wrappingPrice");

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
      console.log(bookWrappingPrice);
    }
  }

  document.getElementById("package_price").innerText = totalWrappingPrice;
  return totalWrappingPrice;
}

function getWrappingPrice(id) {
  for (let i = 0; i < wrappingList.length; i++) {
    if (wrappingList[i].wrappingId == id && wrappingList[i].isActive) {
      console.log("일치하는 포장지 찾음")
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

  if (wrappingPrice == null || couponDiscountPrice == null || pointDiscountPrice == null) {
    return;
  }

  let deliveryPrice  = sumBookPrice < deliveryPolicy.freeDeliveryPrice
      ? deliveryPolicy.deliveryPrice : 0;

  let totalPrice = sumBookPrice + deliveryPrice + wrappingPrice - couponDiscountPrice - pointDiscountPrice;

  if (totalPrice < 0) {
    alert("결제 금액은 0원 미만일 수 없습니다.");
    return;
  }

  document.getElementById("payAmount").value = totalPrice;
  document.getElementById("pay_amount").innerText = totalPrice.toString();
  return totalPrice;
}

// 배송 예정일 익일부터 선택 가능
function setDeliveryDate() {
  let deliveryDate = document.getElementById("delivery_date");

  let tomorrow = new Date();
  tomorrow.setDate(tomorrow.getDate() + 1);

  deliveryDate.setAttribute("min", tomorrow.toISOString().split("T")[0]);
}

document.addEventListener("DOMContentLoaded", setDeliveryDate);

// 주문서 내용 확인
document.addEventListener("submit", function (event) {
  event.preventDefault();

  let receiverName = document.getElementById("receiverName").value;

  if (receiverName == null || receiverName == "")  {
    alert("수취인 이름을 작성해 주세요.")
    return;
  }

  let receiverNumber = document.getElementById("receiverPhoneNumber").value;

  if (receiverNumber == null || receiverNumber == "")  {
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

  document.getElementById("order_form").submit();
})
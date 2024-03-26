function findOrder() {
  const orderCode = document.getElementById("orderCode");

  if (orderCode == null || orderCode.value.length != 32) {
    alert("주문 번호는 32자 입니다.")
    return;
  }

  alert("주문 정보를 조회합니다..");
  window.location.href="/anonymous-orders/" + orderCode.value;
}
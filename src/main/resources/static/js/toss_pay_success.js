const urlParams = new URLSearchParams(window.location.search);

console.log("success start");

async function confirm() {
  var requestData = {
    paymentKey: urlParams.get("paymentKey"),
    orderId: urlParams.get("orderId"),
    amount: urlParams.get("amount"),
  };

  const tossResponse = await fetch("/pays/confirm/toss", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestData),
  });

  const json = await tossResponse.json();

  if (!tossResponse.ok) {
    alert("결제에 실패했습니다. 다시 시도해 주세요.");
    alert("메인 페이지로 이동합니다.");
    window.location.href = '/';
  }

  const dto = {
    payKey: json.paymentKey,
    orderCode: json.orderCode,
    totalAmount: json.totalAmount,
    balanceAmount: json.balanceAmount,
    approvedAt: json.approvedAt,
    requestedAt: json.requestedAt,
    vat: json.vat,
    isPartialCancelable: json.isPartialCancelable,
    vendor: "toss"
  };

  const response = await fetch("/pays", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(dto)
  });

}
confirm();

const paymentKeyElement = document.getElementById("paymentKey");
const orderIdElement = document.getElementById("orderId");
const amountElement = document.getElementById("amount");

orderIdElement.textContent = "주문번호: " + urlParams.get("orderId");
amountElement.textContent = "결제 금액: " + urlParams.get("amount");
paymentKeyElement.textContent =
    "paymentKey: " + urlParams.get("paymentKey");
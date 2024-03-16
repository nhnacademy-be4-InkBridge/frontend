// const urlParams = new URLSearchParams(window.location.search);
//
// async function confirm() {
//
//   var requestData = {
//     paymentKey: urlParams.get("paymentKey"),
//     orderId: urlParams.get("orderId"),
//     amount: urlParams.get("amount"),
//   };
//
//   const tossResponse = await fetch("/pays/confirm/toss", {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//     },
//     body: JSON.stringify(requestData),
//   });
//
//   const json = await tossResponse.body;
//
//   if (!tossResponse.ok) {
//     alert("결제에 실패했습니다. 다시 시도해 주세요.");
//     alert("메인 페이지로 이동합니다.");
//     window.location.href = '/';
//   }
//
//   const dto = {
//     payKey: json.getPaymentKey(),
//     orderCode: json.getOrderId(),
//     totalAmount: json.getTotalAmount(),
//     balanceAmount: json.getBalanceAmount(),
//     approvedAt: json.getApprovedAt,
//     requestedAt: json.getRequestedAt(),
//     vat: json.getVat(),
//     isPartialCancelable: json.getPartialCancelable(),
//     vendor: json.getProvider()
//   };
//
//   console.log(dto);
//
//   const response = await fetch("/pays", {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//     },
//     body: JSON.stringify(dto)
//   });
//
// }
// confirm();
//

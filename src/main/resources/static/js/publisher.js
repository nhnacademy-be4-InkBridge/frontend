const addButton = document.querySelector(".publisher-add-btn");
const updateButtons = document.querySelectorAll(".publisher-update-btn");

addButton.addEventListener("click", async (e) => {
  const publisherName = prompt("출판사 이름을 입력해주세요.");
  await fetch("/admin/publishers", {
    method: "POST",
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({publisherName}),
  });
  window.location.reload()
})

updateButtons.forEach((updateButton)=>{
  updateButton.addEventListener("click",async ()=>{
    const row = updateButton.closest('tr');
    const publisherId = row.querySelector("td:nth-child(2)").innerText;
    const publisherName = prompt("출판사 이름을 입력해주세요.");
    await fetch(`/admin/publishers/${publisherId}`,{
      method:"PUT",
      headers:{
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({publisherName}),
    })
    window.location.reload();
  })
})
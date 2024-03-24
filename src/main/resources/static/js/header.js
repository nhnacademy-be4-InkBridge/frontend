const userButton=document.querySelector(".user-btn");
const actionButtonMenu = document.querySelector(".action-btn-menu");
userButton.addEventListener("click",()=>{
    actionButtonMenu.classList.toggle("menu-hidden");
})

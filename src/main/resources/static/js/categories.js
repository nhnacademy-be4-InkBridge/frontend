const arrowButtons = document.querySelectorAll(".arrow");
const allOpen = document.querySelector(".all_open");
const allClose = document.querySelector(".all_close");

const toggleCategory = (arrowButton, isOpen) => {
  const parentCategory = arrowButton.parentElement;
  const childrenCategory = parentCategory.nextElementSibling;
  const arrowImg = arrowButton.firstElementChild;
  arrowImg.classList.toggle("img_rotate", isOpen);
  childrenCategory.classList.toggle("hidden", !isOpen);
}

arrowButtons.forEach((arrowButton) => {
  arrowButton.addEventListener("click", (e) => {
    toggleCategory(arrowButton, !arrowButton.isOpen);
    arrowButton.isOpen = !arrowButton.isOpen;
    console.log(arrowButton.isOpen);
  })
})

allOpen.addEventListener("click", () => {
  arrowButtons.forEach((arrowButton) => {
    toggleCategory(arrowButton, true);
    arrowButton.isOpen = true;
  })
})

allClose.addEventListener("click", () => {
  arrowButtons.forEach((arrowButton) => {
    toggleCategory(arrowButton, false);
    arrowButton.isOpen = false;
  })
})

const addButtons = document.querySelectorAll(".add-btn");

addButtons.forEach((addButton) => {
  addButton.addEventListener("click", (e) => {
    let parentId= e.target.parentElement.parentElement.firstElementChild.innerText;
    if(parentId===0){
      parentId = null;
    }
    document.querySelector(".parent-id").value=parentId;
  })
});

const updateButtons = document.querySelectorAll(".update-btn");

updateButtons.forEach(updateButton=>{
  updateButton.addEventListener("click",e=>{
    let parentId = e.target.parentElement.parentElement.firstElementChild.innerText;
    if(parentId===0){
      parentId = null;
    }
    document.querySelector(".update-form").action+="/"+parentId;
  })
})
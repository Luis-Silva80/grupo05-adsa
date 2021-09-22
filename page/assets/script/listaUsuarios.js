
function getStoreId() {
  let teste = localStorage.getItem('productId')
  console.log("id aqui", teste);
}
window.onload = getStoreId() 
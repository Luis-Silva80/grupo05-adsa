function Gamification(points) {
  let level = document.getElementById('perfilLevel')
  let bar = document.getElementById('barLevel')
  switch (points) {
    case 5:
      level.innerText = 1
      bar.style.width = "0%"
      break;
    case 10:
      level.innerText = 2
      bar.style.width = "0%"
      break;
    case 15:
      level.innerText = 2
      bar.style.width = "50%"
      break;
    case 20:
      level.innerText = 3
      bar.style.width = "0%"
      break;
    case 25:
      level.innerText = 3
      bar.style.width = "50%"
      break;
    case 30:
      level.innerText = 4
      bar.style.width = "0%"
      break;
    case 35:
      level.innerText = 4
      bar.style.width = "50%"
      break;
    case 40:
      level.innerText = 5
      bar.style.width = "0%"
      break;
    case 45:
      level.innerText = 5
      bar.style.width = "50%"
      break;
    case 50:
      level.innerText = 6
      bar.style.width = "0%"
      break;
    default:
      level.innerText = 0
      break;
  }
}
export default Gamification;
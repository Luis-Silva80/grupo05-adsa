function Gamification(points) {
  let level = document.getElementById('perfilLevel')
  switch (points) {
    case 5:
      level.innerText = 1
      break;
    case 10:
      level.innerText = 2
      break;
    case 20:
      level.innerText = 3
      break;
    case 30:
      level.innerText = 4
      break;
    case 40:
      level.innerText = 5
      break;
    case 50:
      level.innerText = 6
      break;
    default:
      level.innerText = 0
      break;
  }
}
export default Gamification;
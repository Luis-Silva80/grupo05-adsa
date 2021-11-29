import React, { useEffect, useState } from 'react';
import './style.scss';
import { Bar} from 'react-chartjs-2'

// import api and links
// import api from "../../services/api";

// import components-
import Footer from '../../components/footer/Footer';
import SideBar from '../../components/sideBar/SideBar';
import Autentication from "../../services/autentication";

function Graficos() {
  // const [userData, setUserData] = useState([]);

  // useEffect(() => {
  //   api
  //     .get("/aluno")
  //     .then((response) => {
  //       setUserData(response.data);
  //       console.log(response.data);
  //     })
  //     .catch((err) => {
  //       console.error("ops! ocorreu um erro" + err);
  //     });
  // }, []);

  // userData.sort((a, b) => (a.pontos > b.pontos) ? -1 : 1)
  
  return (

    <div id="rootGraficos">
      <SideBar />
      <main class="main container">
        <h1 class="main_title">Gráficos</h1>
        <canvas id="myChart" width="400" height="400"></canvas>

        


        <div>
          <Bar
            data={{
              labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
              datasets: [
                {
                  label: '# of votes',
                  data: [12, 19, 3, 5, 2, 3],
                  backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                  ],
                  borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                  ],
                  borderWidth: 1,
                },
                // {
                //   label: 'Quantity',
                //   data: [47, 52, 67, 58, 9, 50],
                //   backgroundColor: 'orange',
                //   borderColor: 'red',
                // },
              ],
            }}
            height={400}
            width={600}
            options={{
              maintainAspectRatio: false,
              scales: {
                yAxes: [
                  {
                    ticks: {
                      beginAtZero: true,
                    },
                  },
                ],
              },
              legend: {
                labels: {
                  fontSize: 25,
                },
              },
            }}
          />
        </div>



      </main>
      <Footer />
    </div>
  );
}

export default Graficos;











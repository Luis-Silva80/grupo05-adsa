'use strict';
// import React from 'react';



export function defineNetExterno() {



const { networkInterfaces } = require('os');

const nets = networkInterfaces();
const results = Object.create(null); // Or just '{}', an empty object
let netExterno = []

for (const name of Object.keys(nets)) {
    for (const net of nets[name]) {
        // Skip over non-IPv4 and internal (i.e. 127.0.0.1) addresses
        if (net.family === 'IPv4' && !net.internal) {
            if (!results[name]) {
                results[name] = [];
            }
            results[name].push(net.address);
        }
        netExterno.push(net)
    }
}

var ipWeb2 = false

netExterno.forEach(element => {

    if(element.family == "IPv4" && element.address == "172.31.92.61"){

        ipWeb2 = true
    }   
});

localStorage.setItem('ipWeb2', ipWeb2)

    return (
        <>
        </>
    )
}


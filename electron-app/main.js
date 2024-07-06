const { app, BrowserWindow, ipcMain } = require('electron');
const path = require('node:path');
const axios = require('axios').default;

const createWindow = () => {
    const win = new BrowserWindow({
    //   width: 500,
    //   height: 1000,
      webPreferences: {
        preload: path.join(__dirname, 'preload.js'),
        nodeIntegration: true,
        contextIsolation: true
      }
    })
  
    win.loadFile('index.html');
}  

app.whenReady().then(() => {
    createWindow();
})
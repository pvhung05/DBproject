import React from 'react';
import { createRoot } from 'react-dom/client'; // Make sure this line is included
import './App.css';
import App from './App';

const rootElement = document.getElementById('root');
const root = createRoot(rootElement);

root.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);